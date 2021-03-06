package game.panels.tetris.multiplayer.ai;

import game.dialogs.ScoreDialog;
import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.*;
import game.serial.OptionsSaver;
import game.start.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class PlayField extends JPanel implements Runnable {
    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0;
    public static final byte RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1;
    public static final byte NEW_STYLE_RANDOM = 2;

    private static final Color transparentColor = new Color(0, 0, 0, 100);
    private static final Color transparentColor2 = new Color(0, 0, 0, 2);
    private Color backgroundColor = new Color(0, 0, 0, 100);

    public volatile boolean gameOver;
    public volatile boolean clearAnimation;
    public boolean suspendFlag;
    public boolean interruptFlag;
    public boolean grid;
    public boolean paintShadow;

    public byte[][] fieldMatrix;
    public byte[] usedTetrominoes;
    public byte amountUsedTetrominoes;
    public byte randomType;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte helperForDeleting;
    public byte clearLinesAnimationType;
    public byte music;
    public byte typeOfSquare = 0;

    public int amountOfDeletingLinesBetweenTetrominoes;
    public int amountOfDeletingLinesBetweenLevels;
    public int level;
    private final int FPS = 60;

    private final long NS_PER_UPDATE = 16666666L;
    private long counterOldForFalling;
    public long score;

    public Tetromino currentTetromino;

    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;

    public Thread thread;

    private OptionsSaver optionsGetter = null;

    public Stack<Byte> tetrominoesStack;
    private Stack<Byte> extraTetrominoesStack;

    public KeyHandler keyHandler;

    private double stepY;
    private double stepX;

    public PlayField() {
        setOpaque(false);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        indexesOfDeletingLines = new ArrayList<>();
        keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setForeground(transparentColor);
    }

    private void lastMove() {
        Main.audioPlayer.playHardDrop();

        for (int j = 0; j < 4; ++j) {
            SquareOfTetromino squareOfTetromino = new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y), currentTetromino.tetrominoType);
            elementsStayOnField.add(squareOfTetromino);
            if (currentTetromino.coordinates[j].y > -2) {
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
            }
        }

        checkGameOver();
        updateCurrentTetromino();

        if (extraScore > 0)
            score += extraScore;

        extraScore = 0;
    }

    public void run() {
        Main.audioPlayer.playMusic(music);
        System.out.println(Thread.currentThread().getName() + " start");
        gameOver = false;
        long old = System.nanoTime();
        long counterOld = System.nanoTime();
        double frames = 0.0D;
        counterOldForFalling = System.nanoTime();
        long counterOldForClearing = System.nanoTime();
        stepY = 0.0D;
        stepX = 0.0D;

        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {

                        Thread.sleep(30);
                            if(keyHandler.isPause()){
                                Main.audioPlayer.resumeMusic(music);
                                keyHandler.resetValues();
                                Main.multiplayerPanel2.battlePanel.mainMenuButton.setVisible(true);
                                myResume();
                                Main.multiplayerPanel2.battlePanel.aiPlayField.myResume();
                                Main.multiplayerPanel2.battlePanel.aiPlayField.repaint();
                                repaint();
                            }
                       // wait();
                    }
                }

                long current = System.nanoTime();
                long delta = current - old;
                long counterDelta = current - counterOld;
                long counterTimeOfFalling = current - counterOldForFalling;
                long counterTimeOfClearing = current - counterOldForClearing;
                if (clearAnimation && counterTimeOfClearing >= 55555555L) {
                    stepY = 0.0D;
                    clearAnimation();
                    counterOldForClearing = System.nanoTime();
                }

                int speedLevel;
                if (level < 21) {
                    speedLevel = level;
                } else {
                    speedLevel = 20;
                }

                if (counterTimeOfFalling >= (long) (1000000000 / (speedLevel + 1) * 2)) {
                    System.out.println("fall");
                    if (checkIsElementAlmostFell()) {
                        lastMove();
                        repaint();
                    } else if (!clearAnimation) {
                        Moving.pressDownKey(currentTetromino, fieldMatrix);
                        repaint();
                    }

                    stepY = 0.0D;
                    stepX = 0.0D;
                    counterOldForFalling = System.nanoTime();
                }

                if (counterDelta >= 333333333L) {
                    System.out.println(frames / ((double) counterDelta / 1.0E9D));
                    Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanel.fps = frames / ((double) counterDelta / 1.0E9D);
                    Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanel.repaint();
                    frames = 0.0D;
                    counterOld = System.nanoTime();
                }

                if (delta >= NS_PER_UPDATE) {
                    long missedTime = delta - NS_PER_UPDATE;
                    old = System.nanoTime() - missedTime;
                    update();
                    checkGameOver();
                    checkLine();
                    clearAnimation = indexesOfDeletingLines.size() > 0;

                    checkScore();
                    if (checkIsElementAlmostFell()) {
                        stepY = 0.0D;
                        stepX = 0.0D;
                    }

                    ++frames;
                } else {
                    Thread.sleep(1L);
                }
            } catch (InterruptedException ignored) {
            }
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException ignored) {
        }

        if (!interruptFlag) {
            Main.audioPlayer.stopMusic();
            gameOverRepaint(elementsStayOnField);
            goLeaderBoardPanel();
        }

    }

    private void update() {
        if (!gameOver && !clearAnimation) {
            if (keyHandler.isLeft()) {
                Moving.pressLeftKey(currentTetromino, fieldMatrix, true);
                keyHandler.setLeft(false);
                repaint();
            } else if (keyHandler.isRight()) {
                Moving.pressRightKey(currentTetromino, fieldMatrix, true);
                keyHandler.setRight(false);
                repaint();
            } else if (keyHandler.isHardDrop()) {
                extraScore += Moving.pressHardDropKey(fieldMatrix, currentTetromino);
                lastMove();
                keyHandler.setHardDrop(false);
                stepY = 0.0D;
                counterOldForFalling = System.nanoTime();
                repaint();
            } else if (keyHandler.isCcw_rotation()) {
                Rotation.pressCCWKey(fieldMatrix, currentTetromino);
                keyHandler.setCcw_rotation(false);
                repaint();
            } else if (keyHandler.isCw_rotation()) {
                Rotation.pressCWKey(fieldMatrix, currentTetromino);
                keyHandler.setCw_rotation(false);
                repaint();
            } else if (keyHandler.isExit()) {
                mySuspend();
                Main.audioPlayer.playClick();
                gameOver = true;
                myInterrupt();
                Main.multiplayerPanel2.battlePanel.aiPlayField.mySuspend();
                Main.multiplayerPanel2.battlePanel.aiPlayField.myInterrupt();
                Main.audioPlayer.stopMusic();
                Main.tetrisFrame.remove(Main.multiplayerPanel2.battlePanel);
                Main.tetrisFrame.add(Main.menuPanel);
                Main.tetrisFrame.revalidate();
                Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
                Main.tetrisFrame.repaint();
                Main.menuPanel.selectCurrentButton();
                Main.menuPanel.requestFocusInWindow();
                keyHandler.setExit(false);
            } else if (keyHandler.isDown()) {
                ++currentTetromino.stepY;

                int i;
                for (i = 0; i < 4; ++i) {
                    ++currentTetromino.coordinates[i].y;
                }

                if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {
                    --currentTetromino.stepY;

                    for (i = 0; i < 4; ++i) {
                        --currentTetromino.coordinates[i].y;
                    }

                    lastMove();
                    counterOldForFalling = System.nanoTime();
                } else {
                    --currentTetromino.stepY;

                    for (i = 0; i < 4; ++i) {
                        --currentTetromino.coordinates[i].y;
                    }

                    extraScore += Moving.pressDownKey(currentTetromino, fieldMatrix);
                    System.out.println("DOWN");
                    Main.audioPlayer.playMove();
                    stepY = 0.0D;
                    counterOldForFalling = System.nanoTime();
                }

                keyHandler.setDown(false);
                repaint();
            } else if (keyHandler.isPause()) {

                Main.audioPlayer.playPause();
                Main.audioPlayer.pauseMusic();
                Main.multiplayerPanel2.battlePanel.mainMenuButton.setVisible(false);
                keyHandler.setPause(false);
                Main.multiplayerPanel2.battlePanel.aiPlayField.thread.interrupt();
                Main.multiplayerPanel2.battlePanel.aiPlayField.mySuspend();
                Main.multiplayerPanel2.battlePanel.aiPlayField.repaint();
                mySuspend();
                repaint();
                System.out.println("waiting cancel pause");
            }

            if (keyHandler.isDown_released()) {
                extraScore = 0;
            }
        }
    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {
        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; --i) {
            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / (long) amount / 1000L);
            } catch (InterruptedException ignored) {
            }

            if (elementsStayOnField.size() > 0) {
                elementsStayOnField.remove(i);
            }

            repaint();
        }

    }

    private void clearAnimation() {
        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;
            if (helperForDeleting < 5) {
                if (helperForDeleting == 0) {
                    playClearLinesAudio();
                }

                ++helperForDeleting;
                if (indexesOfDeletingLines.size() == 4) {
                    if (backgroundColor == transparentColor2) {
                        backgroundColor = transparentColor;
                    } else {
                        backgroundColor = transparentColor2;
                    }
                }
            } else {
                clearAnimation = false;
                helperForDeleting = 0;
                if (indexesOfDeletingLines.size() == 4) {
                    Main.multiplayerPanel2.battlePanel.playfield.setForeground(transparentColor);
                    backgroundColor = transparentColor;
                }

                for (int el : indexesOfDeletingLines) {
                    deleteLine(el);
                }

                indexesOfDeletingLines.clear();
                counterOldForFalling = System.nanoTime();
            }
            repaint();
        }
    }

    private void playClearLinesAudio() {
        if (indexesOfDeletingLines.size() == 4) {
            Main.audioPlayer.playTetris();
        } else {
            Main.audioPlayer.playClearLine();
        }

    }

    private void goLeaderBoardPanel() {
        Main.tetrisFrame.remove(Main.multiplayerPanel2.battlePanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
        ScoreDialog scoreDialog = new ScoreDialog(Main.tetrisFrame, true, score);
        Main.leaderBoardPanel.newPotentialLeader = scoreDialog.playerNameField.getText();
        if (scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader)) {
            Main.leaderBoardPanel.newPotentialLeader = "player";
        }

        Main.leaderBoardPanel.saveLeaderBoardAfterGameOverBot();
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);
        Main.audioPlayer.playClick();
    }

    public void startNewGame() {

        /*int width = (int) (getWidth() * 4.7);
        int height = (int) (getHeight() * 1.2);

        if(width >= Main.monitorWidth || height >= Main.monitorHeight)
            Main.tetrisFrame.setExtendedState(Main.tetrisFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        else {
            Main.tetrisFrame.setSize(width, height);
            Main.tetrisFrame.setLocationRelativeTo(null);
        }*/

        deserializeOptionsForNewGame();
        extraTetrominoesStack = new Stack<>();
        Main.tetrisPanel.setVisible(false);
        keyHandler.resetValues();
        resetPlayValues();
        setFieldMatrix();
        typeOfSquare = Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare;

        updateCurrentTetromino();
        setFirstCurrentTetrominoStepsAndColor();
        setTetrisLabels();
        startNewTread();
        requestFocusInWindow();
    }

    private void updateCurrentTetromino() {
        if (tetrominoesStack.size() > 0) {
            currentTetromino.tetrominoType = tetrominoesStack.pop();
            extraTetrominoesStack.push(currentTetromino.tetrominoType);
        }
        if(tetrominoesStack.size() == 0) {
            tetrominoesStack.addAll(extraTetrominoesStack);
            extraTetrominoesStack = new Stack<>();
        }

        System.out.println("size of original stack: " + tetrominoesStack.size() + "------------------");
        System.out.println("size of extra stack: " + extraTetrominoesStack.size() + "------------------");

        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanel.nextTetromino = tetrominoesStack.peek();
        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanel.repaint();
        Main.multiplayerPanel2.battlePanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);
        System.out.println(tetrominoesStack.size());
        currentTetromino.rotationType = 0;
        setFirstCurrentTetrominoStepsAndColor();
    }

    public void setTetrominoesStack() {

        usedTetrominoes = new byte[7];
        amountUsedTetrominoes = 0;

        for (int i = 0; i < 7; ++i) {
            usedTetrominoes[i] = -1;
        }
        tetrominoesStack = new Stack<>();

        for (int i = 0; i < 504; ++i) {
            Object[] randomObject = Randomizer.newStyleRandomTetromino(usedTetrominoes, amountUsedTetrominoes);
            usedTetrominoes = (byte[]) randomObject[0];
            amountUsedTetrominoes = (Byte) randomObject[1];
            tetrominoesStack.push((Byte) randomObject[2]);
        }
    }

    private void setTetrisLabels() {
        setScore();
        setLevel();
        setLines();
    }

    private void startNewTread() {
        thread = new Thread(this, "repainting thread");
        thread.start();
    }

    private void resetPlayValues() {
        // Main.tetrisPanel.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.multiplayerPanel2.battlePanel.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0.0D;
        elementsStayOnField = new ArrayList<>();
        amountOfDeletingLinesBetweenTetrominoes = 0;
        amountOfDeletingLinesBetweenLevels = 0;
        score = 0L;
        extraScore = 0;
        suspendFlag = false;
        interruptFlag = false;
        usedTetrominoes = new byte[7];
        fieldMatrix = new byte[22][12];
        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        int i;
        for (i = 0; i < 4; ++i) {
            coordinates[i] = new ByteCoordinates();
        }


        //  if (randomType == NEW_STYLE_RANDOM) {
        for (i = 0; i < 7; ++i) {
            usedTetrominoes[i] = -1;
        }
        // }

        //  updateNextTetromino();
        currentTetromino = new Tetromino(coordinates,
                Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanel.nextTetromino, (byte) 0, (byte) 0, (byte) 0);
    }



    private void getSettingsNotAffectingTheGame(OptionsSaver optionsSaver) {
        keyHandler.cwKey = optionsSaver.getCwKey();
        keyHandler.ccwKey = optionsSaver.getCcwKey();
        keyHandler.rightKey = optionsSaver.getRightKey();
        keyHandler.leftKey = optionsSaver.getLeftKey();
        keyHandler.downKey = optionsSaver.getDownKey();
        keyHandler.hardDropKey = optionsSaver.getHardDropKey();
        keyHandler.pauseKey = optionsSaver.getPauseKey();
        keyHandler.exitMenuKey = optionsSaver.getExitMenuKey();
        Main.multiplayerPanel2.battlePanel.backgroundType = optionsSaver.getBackgroundType();
        clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        paintShadow = optionsSaver.getShadow();
        grid = optionsSaver.getGrid();
        music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double) optionsSaver.getMusicVolume() / 100.0D;
        Main.audioPlayer.soundsVolume = (double) optionsSaver.getSoundsVolume() / 100.0D;
    }

    private void deserializeOptionsForNewGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            optionsGetter = (OptionsSaver) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException var2) {
            var2.printStackTrace();
        }

        assert optionsGetter != null;

        level = optionsGetter.getStartLevel();
        randomType = optionsGetter.getRandomType();
        getSettingsNotAffectingTheGame(optionsGetter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double radius = (double) getHeight() / 20.0D;

        if(suspendFlag) {
            Painting.paintLyingElementsForGameOver(g2d, elementsStayOnField, radius, typeOfSquare);
            drawCenteredString(g2d,  "[Pause]", new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT, 1f);
            return;
        }

        if (grid) {
            Painting.drawLines(g2d, getWidth(), getHeight(), radius);
        }

        if (clearAnimation) {
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION) {
                Painting.showRandomColorClearLinesAnimation(g2d, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);
            } else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION) {
                Painting.showDisappearClearLinesAnimation(g2d, helperForDeleting, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);
            }
        } else {
            Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);
            if (!gameOver) {
                Painting.paintCurrentTetrominoForRepainting(currentTetromino, stepX, stepY, g2d, radius, typeOfSquare);
                if (paintShadow) {
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius, typeOfSquare);
                }
            }
        }

    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, float fontSizeCoefficient) {
        font = Main.FONT.deriveFont((float) font.getSize() * fontSizeCoefficient);

        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    private void checkGameOver() {
        for (int i = 0; i < 10; ++i) {
            if (fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                gameOver = true;
                break;
            }
        }

    }

    private void checkLine() {
        for (int i = 0; i < 21; ++i) {
            int counter = 0;

            for (int j = 1; j < 11; ++j) {
                if (fieldMatrix[i][j] == 1) {
                    ++counter;
                }
            }

            if (counter == 10 && !indexesOfDeletingLines.contains(i)) {
                indexesOfDeletingLines.add(i);
            }
        }

    }

    private void setLines() {
       // Main.tetrisPanel.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
        Main.multiplayerPanel2.battlePanel.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            ++level;
            setLevel();
        }

    }

    private void setLevel() {
        Main.multiplayerPanel2.battlePanel.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + level);
    }

    private void checkScore() {
        if (amountOfDeletingLinesBetweenTetrominoes == 1) {
            score += 40L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 2) {
            score += 100L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 3) {
            score += 300L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 4) {
            score += 1200L * (long) (level + 1);
        }

        amountOfDeletingLinesBetweenTetrominoes = 0;
        setScore();
    }

    private void setScore() {
        if(score > Main.multiplayerPanel2.battlePanel.aiPlayField.score) {
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabel.setForeground(Color.GREEN);
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>"
                    + score + "<br/>(+" + (score - Main.multiplayerPanel2.battlePanel.aiPlayField.score) + ")</html>");

            Main.multiplayerPanel2.battlePanel.tetrisScoresLabelOpponent.setForeground(Color.RED);
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>"
                    + Main.multiplayerPanel2.battlePanel.aiPlayField.score + "<br/>("
                    + (Main.multiplayerPanel2.battlePanel.aiPlayField.score - score ) + ")</html>");

        }
        else if(score < Main.multiplayerPanel2.battlePanel.aiPlayField.score) {
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabel.setForeground(Color.RED);
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>"
                    + score + "<br/>(" + (score - Main.multiplayerPanel2.battlePanel.aiPlayField.score) + ")</html>");

            Main.multiplayerPanel2.battlePanel.tetrisScoresLabelOpponent.setForeground(Color.GREEN);
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>"
                    + Main.multiplayerPanel2.battlePanel.aiPlayField.score + "<br/>(+"
                    + (Main.multiplayerPanel2.battlePanel.aiPlayField.score - score) + ")</html>");
        }
        else {
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabel.setForeground(Color.WHITE);
            Main.multiplayerPanel2.battlePanel.tetrisScoresLabelOpponent.setForeground(Color.WHITE);
        }
    }

    private void deleteLine(int deletingLine) {
        ++amountOfDeletingLinesBetweenTetrominoes;
        ++amountOfDeletingLinesBetweenLevels;

        for (int i = deletingLine; i > 0; --i) {
            System.arraycopy(fieldMatrix[i - 1], 1, fieldMatrix[i], 1, 10);
        }

        elementsStayOnField.removeIf((elx) -> elx.coordinates.y == deletingLine - 1);

        for (SquareOfTetromino el : elementsStayOnField) {
            if (el.coordinates.y < deletingLine - 1) {
                ++el.coordinates.y;
            }
        }

        setLines();
        checkLevel();
    }

    private boolean checkIsElementAlmostFell() {
        for (int i = 0; i < 4; ++i) {
            ++currentTetromino.coordinates[i].y;
        }

        boolean isFell = Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);

        for (int i = 0; i < 4; ++i) {
            --currentTetromino.coordinates[i].y;
        }

        return isFell;
    }


    private void setFirstCurrentTetrominoStepsAndColor() {
        boolean stepYBack = false;

        for (int i = 1; i < 11; ++i) {
            if (fieldMatrix[2][i] == 1 && currentTetromino.tetrominoType != 0) {
                stepYBack = true;
                break;
            }
        }

        if (stepYBack) {
            currentTetromino.stepY = -1;
        } else {
            currentTetromino.stepY = 0;
        }

        if (currentTetromino.tetrominoType == 3) {
            currentTetromino.stepX = 4;
        } else {
            currentTetromino.stepX = 3;
        }

        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);
        currentTetromino = Rotation.doRotation(currentTetromino);
    }

    public synchronized void myInterrupt() {
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
        for (int i = 0; i < 22; ++i) {
            for (int j = 0; j < 12; ++j) {
                if (i == 21) {
                    fieldMatrix[i][j] = 1;
                } else if (j != 0 && j != 11) {
                    fieldMatrix[i][j] = 0;
                } else {
                    fieldMatrix[i][j] = 1;
                }
            }
        }

    }

    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
            double w = d.getWidth();
            double h = d.getHeight();
            double s = Math.min(w, h);
            return new Dimension((int) Math.round(s * 0.41 / 10.0D) * 10, (int) Math.round(s * 0.82 / 20.0D) * 20);
        } else {
            return new Dimension(10, 20);
        }
    }
}
