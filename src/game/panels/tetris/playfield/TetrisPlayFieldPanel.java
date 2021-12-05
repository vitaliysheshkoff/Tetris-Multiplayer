package game.panels.tetris.playfield;

import game.dialogs.ScoreDialog;
import game.helperclasses.ByteCoordinates;
import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.panels.tetris.playfield.controller.*;
import game.serial.GameSaver;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisPlayFieldPanel extends JPanel implements Runnable, KeyListener {

    public static final int[] MILLI_SPEED = {798, 715, 632, 549, 465, 382, 299, 216, 133, 99, 83, 66, 49, 33, 16};
    public static final int[] NANO_SPEED = {684832, 488496, 292159, 95822, 899486, 703149, 506812, 310475, 114139, 835604, 196337, 557069, 917802, 278535, 639268};

    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0, RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1, NEW_STYLE_RANDOM = 2;
    public static final byte DEFAULT = 0, CW = 1, DCW = 2, CCW = 3, DCCW = 4;

    public boolean suspendFlag;
    public boolean interruptFlag;
    public boolean gameOver;
    public boolean grid;
    public boolean paintShadow;
    public boolean clearAnimation;

    public byte[][] fieldMatrix;
    public byte[] usedTetrominoes;

    public byte amountUsedTetrominoes;
    public byte randomType;
    public byte amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte helperForDeleting;
    public byte clearLinesAnimationType;
    public byte music;

    public int cwKey = KeyEvent.VK_D, ccwKey = KeyEvent.VK_A, rightKey = KeyEvent.VK_RIGHT, leftKey = KeyEvent.VK_LEFT, downKey = KeyEvent.VK_DOWN,
            hardDropKey = KeyEvent.VK_SPACE, pauseKey = KeyEvent.VK_ENTER, exitMenuKey = KeyEvent.VK_ESCAPE;

    public long score;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;
    public Thread thread;

    static Color transparentColor = new Color(0,0,0,100);

    static Color transparentColor2 = new Color(0,0,0,2);

    public TetrisPlayFieldPanel() {

        setOpaque( false );
       // setBackground(new Color(255,255,255));
       // setBackground(Color.BLACK);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        indexesOfDeletingLines = new ArrayList<>();
        addKeyListener(this);
        setForeground(transparentColor);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {

        if (!gameOver && !clearAnimation) {

            if (e.getKeyCode() == ccwKey) {

                Rotation.pressCCWKey(fieldMatrix, currentTetromino);
                repaint();

            } else if (e.getKeyCode() == cwKey) {

                Rotation.pressCWKey(fieldMatrix, currentTetromino);
                repaint();

            } else if (e.getKeyCode() == leftKey) {

                Moving.pressLeftKey(currentTetromino, fieldMatrix);
                repaint();

            } else if (e.getKeyCode() == rightKey) {

                Moving.pressRightKey(currentTetromino, fieldMatrix);
                repaint();

            } else if (e.getKeyCode() == downKey) {


                extraScore = Moving.pressDownKey(currentTetromino, extraScore);

                if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {

                    lastMove();
                    wakeUpThreadFromSleeping();
                    return;
                }

                repaint();

            } else if (e.getKeyCode() == hardDropKey) {

                extraScore = Moving.pressHardDropKey(fieldMatrix, currentTetromino, extraScore);

                lastMove();
                wakeUpThreadFromSleeping();


            } else if (e.getKeyCode() == pauseKey) {
                Pause.pressPauseKey();
            } else if (e.getKeyCode() == exitMenuKey) {
                Main.tetrisPanel.mainMenuLabelMousePressed();
            }
        }
    }

    private synchronized void lastMove() {

        Main.audioPlayer.playHardDrop();

        for (int j = 0; j < 4; j++) {
            elementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, (byte) (currentTetromino.coordinates[j].y - 1)), currentTetromino.tetrominoType));
            fieldMatrix[currentTetromino.coordinates[j].y][currentTetromino.coordinates[j].x + 1] = 1;
        }

        updateCurrentTetromino();
        updateNextTetromino();

        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;

        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            extraScore = 0;
        }
    }


    @Override
    public void run() {

        Main.audioPlayer.playMusic(music);
        System.out.println("Thread start " + thread.getId());

        gameOver = false;

        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }

                currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);
                currentTetromino = Rotation.doRotation(currentTetromino);

                checkGameOver();
                checkLine();
                clearAnimationInThread();
                checkScore();
                repaint();



                Thread.sleep(MILLI_SPEED[getSpeedIndex()], NANO_SPEED[getSpeedIndex()]);

                Moving.move(currentTetromino, (byte) 1);

            } catch (InterruptedException e) {
                if (interruptFlag)
                    return;
                System.out.println("Thread resume after sleeping!");
            }
        }

        // gameover:
        Main.tetrisPanel.saveGame();
        Main.audioPlayer.stopMusic();

        gameOverRepaint(elementsStayOnField);
        goLeaderBoardPanel();
        clearDatFile();

    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {

        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; i--) {

            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / amount / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elementsStayOnField.size() > 0)
                elementsStayOnField.remove(i);

            repaint();
        }
    }

    private void clearAnimationInThread() throws InterruptedException {

        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;

            if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                helperForDeleting = 0;

            playClearLinesAudio();

            for (int i = 0; i < 5; i++) {

                if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                    helperForDeleting++;

                if (indexesOfDeletingLines.size() == 4) {

                    if (color == transparentColor2) {
                        color = transparentColor;
                    }
                    else {
                        color = transparentColor2;
                    }

                }

                repaint();
                Thread.sleep(55);
            }
            clearAnimation = false;

            if (indexesOfDeletingLines.size() == 4) {
                Main.tetrisPanel.tetrisPlayFieldPanel.setForeground(transparentColor);
                color = transparentColor;
            }

            for (int el : indexesOfDeletingLines)
                deleteLine(el);

            indexesOfDeletingLines.clear();
        }
    }

    private void playClearLinesAudio() {

        if (indexesOfDeletingLines.size() == 4)
            Main.audioPlayer.playTetris();
        else
            Main.audioPlayer.playClearLine();
    }

    private int getSpeedIndex() {
        if (level == 0)
            return 0;
        else if (level == 1)
            return 1;
        else if (level == 2)
            return 2;
        else if (level == 3)
            return 3;
        else if (level == 4)
            return 4;
        else if (level == 5)
            return 5;
        else if (level == 6)
            return 6;
        else if (level == 7)
            return 7;
        else if (level == 8)
            return 8;
        else if (level == 9)
            return 9;
        else if (level >= 10 && level <= 12)
            return 10;
        else if (level >= 13 && level <= 15)
            return 11;
        else if (level >= 16 && level <= 18)
            return 12;
        else if (level >= 19 && level <= 28)
            return 13;
        else // (level >= 29)
            return 14;
    }


    private void goLeaderBoardPanel() {

        Main.tetrisFrame.remove(Main.tetrisPanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();

        ScoreDialog scoreDialog = new ScoreDialog(Main.tetrisFrame, true);

        Main.leaderBoardPanel.newPotentialLeader = scoreDialog.playerNameField.getText();
        if (scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader))
            Main.leaderBoardPanel.newPotentialLeader = "player";

        Main.leaderBoardPanel.saveLeaderBoardAfterGameOver();
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);

        Main.audioPlayer.playClick();
    }


    private void clearDatFile() {
        try {
            PrintWriter writer = new PrintWriter(new File(System.getProperty("user.dir"), "resume.dat").getAbsolutePath());
            writer.print("");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startNewGame() {


        deserializeOptionsForNewGame();
        Main.tetrisPanel.setVisible(false);
        resetPlayValues();
        setFieldMatrix();
        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);
        getRandom();
        setFirstCurrentTetrominoStepsAndColor();
        setTetrisLabels();
        startNewTread();
        Main.tetrisPanel.setVisible(true);
        requestFocusInWindow();
    }


    private void setTetrisLabels() {
        setScore();
        setLevel();
        setLines();
    }

    private void startNewTread() {
        thread = new Thread(this);
        thread.start();
    }

    private void resetPlayValues() {

        Main.tetrisPanel.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0;

        elementsStayOnField = new ArrayList<>();
        amountOfDeletingLinesBetweenTetrominoes = 0;
        amountOfDeletingLinesBetweenLevels = 0;
        score = 0;
        extraScore = 0;
        suspendFlag = false;
        interruptFlag = false;

        usedTetrominoes = new byte[7];
        fieldMatrix = new byte[22][12];

        ByteCoordinates[] coordinates = new ByteCoordinates[4];
        for (int i = 0; i < 4; i++)
            coordinates[i] = new ByteCoordinates();

        if (randomType == NEW_STYLE_RANDOM) {

            for (int i = 0; i < 7; i++)
                usedTetrominoes[i] = -1;
        }

        updateNextTetromino();
        currentTetromino = new Tetromino(coordinates, Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino,  DEFAULT, (byte) 0, (byte) 0);
    }

    public void resumeGame() {
        deserializeOptionsToResumeGame();
        deserializeGame();
        setTetrisLabels();
        interruptFlag = false;
        startNewTread();
        myResume();
        requestFocusInWindow();
    }

    private void deserializeGame() {
        GameSaver gameSaver = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), "resume.dat").getAbsolutePath()));
            gameSaver = (GameSaver) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert gameSaver != null;
        amountUsedTetrominoes = gameSaver.getAmountUsedTetrominoes();
        usedTetrominoes = gameSaver.getUsedTetrominoes();
        randomType = gameSaver.getRandomType();
        fieldMatrix = gameSaver.getFieldMatrix();
        Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = gameSaver.getNextTetromino();
        Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
        currentTetromino = gameSaver.getCurrentTetromino();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_I = gameSaver.getAmount_I();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_O = gameSaver.getAmount_O();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_L = gameSaver.getAmount_L();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_J = gameSaver.getAmount_J();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_S = gameSaver.getAmount_S();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_Z = gameSaver.getAmount_Z();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_T = gameSaver.getAmount_T();
        Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = gameSaver.getNextTetromino();
        Main.tetrisPanel.tetrisPlayFieldPanel.stepYBeforePause = gameSaver.getStepYBeforePause();
        Main.audioPlayer.musicFramePosition = (gameSaver.getMusicFramePosition());
        elementsStayOnField = gameSaver.getElementsStayOnField();
        amountOfDeletingLinesBetweenTetrominoes = gameSaver.getAmountOfDeletingLinesBetweenTetrominoes();
        amountOfDeletingLinesBetweenLevels = gameSaver.getAmountOfDeletingLinesBetweenLevels();
        score = gameSaver.getScore();
        level = gameSaver.getLevel();
        extraScore = gameSaver.getExtraScore();
    }


    private void deserializeOptionsToResumeGame() {
        OptionsSaver optionsSaver = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), "options.dat").getAbsolutePath()))) {
            optionsSaver = (OptionsSaver) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert optionsSaver != null;

        getSettingsNotAffectingTheGame(optionsSaver);

    }

    private void getSettingsNotAffectingTheGame(OptionsSaver optionsSaver) {
        cwKey = optionsSaver.getCwKey();
        ccwKey = optionsSaver.getCcwKey();
        rightKey = optionsSaver.getRightKey();
        leftKey = optionsSaver.getLeftKey();
        downKey = optionsSaver.getDownKey();
        hardDropKey = optionsSaver.getHardDropKey();
        pauseKey = optionsSaver.getPauseKey();
        exitMenuKey = optionsSaver.getExitMenuKey();
        Main.tetrisPanel.backgroundType = optionsSaver.getBackgroundType();
        clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        paintShadow = optionsSaver.getShadow();
        grid = optionsSaver.getGrid();
        music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double) optionsSaver.getMusicVolume() / 100;
        Main.audioPlayer.soundsVolume = (double) optionsSaver.getSoundsVolume() / 100;
    }

    private void deserializeOptionsForNewGame() {
        OptionsSaver optionsSaver = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), "options.dat").getAbsolutePath()));
            optionsSaver = (OptionsSaver) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert optionsSaver != null;

        level = optionsSaver.getStartLevel();
        randomType = optionsSaver.getRandomType();

        getSettingsNotAffectingTheGame(optionsSaver);
    }



    Color color = new Color(0,0,0,100);
    public synchronized void paintComponent(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        double radius = getHeight() / 20.;


        if (grid)
            Painting.drawLines(g2d,getWidth(), getHeight(), radius);

        //clear game animations:
        if (clearAnimation) {

            // first type of clear lines animation:
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
                Painting.showRandomColorClearLinesAnimation(g2d, elementsStayOnField, indexesOfDeletingLines, radius);

                // second type of clear lines animation:
            else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                Painting.showDisappearClearLinesAnimation(g2d, helperForDeleting, elementsStayOnField, indexesOfDeletingLines, radius);

        } else {

            Painting.paintLyingElements(g2d, elementsStayOnField, radius);

            if (!gameOver) {

                if (checkIsElementFell()) {

                    lastMove();
                    Painting.paintLyingElements(g2d, elementsStayOnField, radius);
                    wakeUpThreadFromSleeping();

                } else {
                    Painting.paintCurrentTetromino(currentTetromino, g2d, radius);

                    if (paintShadow)
                        Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius);
                }
            }
        }
    }

    public void wakeUpThreadFromSleeping() {
        thread.interrupt();
    }

    private void checkGameOver() {
        for (ByteCoordinates el : currentTetromino.coordinates) {
            if (el.y == 0) {
                if (fieldMatrix[1][el.x + 1] == 1) {
                    System.out.println("game over!");
                    gameOver = true;
                    break;
                }
            }
        }
    }

    private void checkLine() {

        int counter;
        int deletingLine;
        for (int i = 0; i < 21; i++) {
            counter = 0;
            for (int j = 1; j < 11; j++) {
                if (fieldMatrix[i][j] == 1) {
                    counter++;
                }
            }
            if (counter == 10) {
                deletingLine = i;
                indexesOfDeletingLines.add(deletingLine);
            }
        }
    }

    private void setLines() {
        Main.tetrisPanel.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            level++;
            setLevel();
        }
    }

    private void setLevel() {
        Main.tetrisPanel.tetrisGameLevelLabel.setText("<html>Level: <br/>" + level);
    }

    private void checkScore() {
        if (amountOfDeletingLinesBetweenTetrominoes == 1)
            score += 40L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 2)
            score += 100L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 3)
            score += 300L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 4)
            score += 1200L * (level + 1);

        amountOfDeletingLinesBetweenTetrominoes = 0;
        setScore();
    }

    private void setScore() {
        Main.tetrisPanel.tetrisScoresLabel.setText("<html>Score: <br/>" + score);
    }

    private void deleteLine(int deletingLine) {

        amountOfDeletingLinesBetweenTetrominoes++;
        amountOfDeletingLinesBetweenLevels++;

        for (int i = deletingLine; i > 0; i--) {
            System.arraycopy(fieldMatrix[i - 1], 1, fieldMatrix[i], 1, 10);
        }
        elementsStayOnField.removeIf(el -> el.coordinates.y == deletingLine - 1);
        for (SquareOfTetromino el : elementsStayOnField) {
            if (el.coordinates.y < deletingLine - 1) {
                el.coordinates.y += 1;
            }
        }
        setLines();
        checkLevel();
    }

    private boolean checkIsElementFell() {

        return Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);
    }


    private void updateNextTetromino() {
        getRandom();
        Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
    }

    private void getRandom() {
        if (randomType == OLD_STYLE_RANDOM)
            Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = Randomizer.oldStyleRandomTetromino();
        else {
            Object[] randomObject;
            randomObject = Randomizer.newStyleRandomTetromino(usedTetrominoes, amountUsedTetrominoes);
            usedTetrominoes = (byte[]) randomObject[0];
            amountUsedTetrominoes = (byte) randomObject[1];
            Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = (byte) randomObject[2];
        }
    }

    private void updateCurrentTetromino() {
        currentTetromino.tetrominoType = Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino;
        currentTetromino.rotationType = DEFAULT;
        setFirstCurrentTetrominoStepsAndColor();
    }

    private void setFirstCurrentTetrominoStepsAndColor() {

        boolean stepYBack = false;
        for (int i = 1; i < 11; i++) {
            if (fieldMatrix[2][i] == 1 && currentTetromino.tetrominoType != TetrisNextTetrominoPanel.I) {
                stepYBack = true;
                break;
            }
        }
        if (stepYBack)
            currentTetromino.stepY = -1;
        else
            currentTetromino.stepY = 0;

        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.O)
            currentTetromino.stepX = 4;
        else
            currentTetromino.stepX = 3;

    }

    public void myInterrupt() {
        interruptFlag = true;
        thread.interrupt();
        System.out.println("tread interrupted!");
    }

    public void mySuspend() {
        stepYBeforePause = currentTetromino.stepY;
        suspendFlag = true;
        System.out.println("suspend");
    }

    public synchronized void myResume() {
        currentTetromino.stepY = stepYBeforePause;
        suspendFlag = false;
        notify();
        System.out.println("resume");
    }


    private void setFieldMatrix() {

  /*[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]*/

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 21)
                    fieldMatrix[i][j] = 1;
                else if (j == 0 || j == 11)
                    fieldMatrix[i][j] = 1;
                else
                    fieldMatrix[i][j] = 0;
            }
        }
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 20);
        }

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);

      //  System.out.println("prefered size" + s / 2 + " " + s);
        return new Dimension((int) (s * 0.4), (int) (s * 0.8));
    }
}
