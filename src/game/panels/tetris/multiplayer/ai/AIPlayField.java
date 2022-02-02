
package game.panels.tetris.multiplayer.ai;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.controller.*;
import game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel;
import game.start.Main;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class AIPlayField extends JPanel implements Runnable {

    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0, RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1, NEW_STYLE_RANDOM = 2;
    public static final byte DEFAULT = 0, CW = 1, DCW = 2, CCW = 3, DCCW = 4;

    public boolean suspendFlag;
    public boolean interruptFlag;
    public volatile boolean gameOver;
    public boolean grid;
    public boolean paintShadow;
    public volatile boolean clearAnimation;

    public byte[][] fieldMatrix;
    public byte[] usedTetrominoes;

    public byte amountUsedTetrominoes;
    public byte randomType = 1;
    public int amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte helperForDeleting;
    public byte clearLinesAnimationType;
    public long score;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;
    public Thread thread;

    Color backgroundColor = new Color(0, 0, 0, 100);
    Color transparentColor = new Color(0, 0, 0, 100);
    Color transparentColor2 = new Color(0, 0, 0, 2);

    private final int FPS = 60;
    private final long NS_PER_UPDATE = (long) ((1.0d / FPS) * 1000000000);

    private long counterOldForFalling;
    private double stepY;
    private double stepX;
    double radius;
    public byte typeOfSquare = 0;

  //  ArrayList<Pair<Byte, ArrayList<Byte>>> steps = null;
   // Pair<Byte,ArrayList<Byte>> botStep = null;

    ArrayList<Checker.Info> steps;

    public AIPlayField() {

        setOpaque(false);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));

        indexesOfDeletingLines = new ArrayList<>();
        setForeground(transparentColor);

    }

    private void lastMove() {

        SquareOfTetromino squareOfTetromino;
        for (int j = 0; j < 4; j++) {
            squareOfTetromino = new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y), currentTetromino.tetrominoType);
            elementsStayOnField.add(squareOfTetromino);

           // if (currentTetromino.coordinates[j].y > -2)
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
        }

        checkGameOver();

        updateCurrentTetromino();
        updateNextTetromino();

        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;

      //  Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);

    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " start");
        gameOver = false;

        long old = System.nanoTime();
        long counterOld = System.nanoTime();

        long missedTime;

        double frames = 0;

        long current;
        long delta;
        long counterDelta;

        //falling
        counterOldForFalling = System.nanoTime();
        long counterTimeOfFalling;

        //clearing
        long counterOldForClearing = System.nanoTime();
        long counterTimeOfClearing;

        stepY = 0;
        stepX = 0;
        int speedLevel;


        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }

                current = System.nanoTime();

                delta = current - old;

                // fps
                counterDelta = current - counterOld;

                // falling
                counterTimeOfFalling = current - counterOldForFalling;

                //clearing
                counterTimeOfClearing = current - counterOldForClearing;

                //time of clearing
                if (clearAnimation) {
                    if (counterTimeOfClearing >= 55555555) {
                        stepY = 0;
                        clearAnimation();
                        counterOldForClearing = System.nanoTime();
                    }
                }

                //time of falling tetromino

                if (level < 21)
                    speedLevel = level;
                else speedLevel = 20;

                if (counterTimeOfFalling >= 1000000000 / (speedLevel + 1) * 2) {

                    System.out.println("fall");
                    if (checkIsElementAlmostFell()) {
                        lastMove();
                        repaint();
                    } else {
                        if (!clearAnimation) {



                            //////////////////////
                            steps = Checker.getMoves(currentTetromino, fieldMatrix);

                            Tetromino oldTetromino = new Tetromino(Arrays.copyOf(currentTetromino.coordinates,
                                    currentTetromino.coordinates.length), currentTetromino.tetrominoType,
                                    currentTetromino.rotationType, currentTetromino.stepY, currentTetromino.stepX);

                          /*int j
                                    = ThreadLocalRandom.current().nextInt(steps.size()) % steps.size();*///ThreadLocalRandom.current().nextInt(0,steps.size());

                            for (Checker.Info step : steps) {

                                currentTetromino = new Tetromino(Arrays.copyOf(oldTetromino.coordinates,
                                        oldTetromino.coordinates.length), oldTetromino.tetrominoType,
                                        oldTetromino.rotationType, oldTetromino.stepY, oldTetromino.stepX);;
                                repaint();

                                if (step.getRotation_type() == CW) {
                                    currentTetromino.rotationType = CW;
                                    //  Rotation.doRotation(currentTetromino);
                                } else if (step.getRotation_type() == CCW) {
                                    currentTetromino.rotationType = CCW;
                                    // Rotation.doRotation(currentTetromino);
                                } else if (step.getRotation_type() == DCW) {
                                    currentTetromino.rotationType = CCW;
                                    // Rotation.doRotation(currentTetromino);
                                }

                                int size = step.getMoves().size();
                                int move;

                                for (int i = 0; i < size; i++) {
                                    move = step.getMoves().get(i);
                                    if (move == Moving.LEFT) {
                                        Moving.pressLeftKey(currentTetromino, fieldMatrix);
                                        repaint();
                                    } else if (move == Moving.RIGHT) {
                                        Moving.pressRightKey(currentTetromino, fieldMatrix);
                                        repaint();
                                    } else if (move == Moving.DOWN) {
                                        Moving.pressDownKey(currentTetromino, fieldMatrix);
                                        repaint();
                                    }


                                    try {
                                        Thread.sleep(40/*409999999*/);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }


                                }

                                repaint();
                                // Thread.sleep(300);


                                /////////////////////
                                //  Moving.pressDownKey(currentTetromino, fieldMatrix);

                            }
                        }
                    }

                    stepY = 0;
                    stepX = 0;
                    counterOldForFalling = System.nanoTime();
                    counterOldForFalling = 555 ;
                }

                //print FPS

/*if (counterDelta >= 1000000000 / 3) {
                    System.out.println(frames / (counterDelta / 1000000000.0));
                    Main.tetrisPanel.tetrisNextTetrominoPanel.fps = frames / (counterDelta / (1000000000.0));
                    Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
                    frames = 0;
                    counterOld = System.nanoTime();
                }*/


                if (delta >= NS_PER_UPDATE) {
                    missedTime = delta - NS_PER_UPDATE;
                    old = System.nanoTime() - missedTime;

                    //render
                    update();
                    checkGameOver();
                    checkLine();

                    //clearAnimation
                    if (indexesOfDeletingLines.size() > 0) clearAnimation = true;
                    else clearAnimation = false;

                    checkScore();
                    if (checkIsElementAlmostFell()) {
                        stepY = 0;
                        stepX = 0;
                    }

                    // repaint();

                    frames++;


                } else {
                    Thread.sleep(1);
                }
            } catch (InterruptedException ignored) {
            }
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {

        }

        // gameover:
        if (!interruptFlag) {
            gameOverRepaint(elementsStayOnField);
        }
        startNewGame();
    }

    private void update() {


        /*try {

          //  steps = Checker.getAllSteps(fieldMatrix, currentTetromino);


            botStep = steps.get(
*//*ThreadLocalRandom.current().nextInt(0,steps.size())*//*
0);

            if (botStep.getKey() == CW) {
                currentTetromino.rotationType = CW;
               // Rotation.doRotation(currentTetromino);
            } else if (botStep.getKey() == CCW) {
                currentTetromino.rotationType = CCW;
              //  Rotation.doRotation(currentTetromino);
            } else if (botStep.getKey() == DCW) {
                currentTetromino.rotationType = CCW;
              //  Rotation.doRotation(currentTetromino);
            }

            int size = botStep.getValue().size();
            int move;

            for (int i = 0; i < size; i++) {
                move = botStep.getValue().get(i);
                if (move == Moving.LEFT) {
                    Moving.pressLeftKey(currentTetromino, fieldMatrix);
                    repaint();
                } else if (move == Moving.RIGHT) {
                    Moving.pressRightKey(currentTetromino, fieldMatrix);
                    repaint();
                } else if (move == Moving.DOWN) {
                    Moving.pressDownKey(currentTetromino, fieldMatrix);
                    repaint();
                }
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception exception){

        }*/



/* if (!gameOver && !clearAnimation) {
            // moving
            if (keyHandler.isLeft()) {

                Moving.pressLeftKey(currentTetromino, fieldMatrix);
                keyHandler.setLeft(false);
                repaint();

            } else if (keyHandler.isRight()) {

                Moving.pressRightKey(currentTetromino, fieldMatrix);
                keyHandler.setRight(false);
                repaint();
            } else if (keyHandler.isDown()) {
                currentTetromino.stepY += 1;
                for (int i = 0; i < 4; i++)
                    currentTetromino.coordinates[i].y += 1;

                if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {

                    currentTetromino.stepY -= 1;
                    for (int i = 0; i < 4; i++)
                        currentTetromino.coordinates[i].y -= 1;

                    lastMove();
                    counterOldForFalling = System.nanoTime();

                } else {

                    currentTetromino.stepY -= 1;
                    for (int i = 0; i < 4; i++)
                        currentTetromino.coordinates[i].y -= 1;

                    extraScore += Moving.pressDownKey(currentTetromino, fieldMatrix);
                    System.out.println("DOWN");
                    Main.audioPlayer.playMove();
                    stepY = 0;
                    counterOldForFalling = System.nanoTime();
                }
                keyHandler.setDown(false);
                repaint();

            } else if (keyHandler.isHardDrop()) {
                extraScore += Moving.pressHardDropKey(fieldMatrix, currentTetromino);
                lastMove();
                keyHandler.setHardDrop(false);
                stepY = 0;
                counterOldForFalling = System.nanoTime();
                repaint();

            }
            //rotation
            else if (keyHandler.isCcw_rotation()) {
                Rotation.pressCCWKey(fieldMatrix, currentTetromino);
                keyHandler.setCcw_rotation(false);
                repaint();
            } else if (keyHandler.isCw_rotation()) {
                Rotation.pressCWKey(fieldMatrix, currentTetromino);
                keyHandler.setCw_rotation(false);
                repaint();
            } else if (keyHandler.isExit()) {
                // Main.tetrisPanel.mainMenuButton.selectButton();
                mySuspend();

                Main.audioPlayer.playClick();
                gameOver = true;
                myInterrupt();
                Main.tetrisPanel.saveGame();
                Main.audioPlayer.stopMusic();
                Main.tetrisFrame.remove(Main.tetrisPanel);
                Main.tetrisFrame.add(Main.menuPanel);

                Main.tetrisFrame.revalidate();
                Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
                Main.tetrisFrame.repaint();

                Main.menuPanel.selectCurrentButton();
                Main.menuPanel.requestFocusInWindow();
                keyHandler.setExit(false);
            } else if (keyHandler.isPause()) {
                Pause.pressPauseKey();
                keyHandler.setPause(false);
                repaint();
            }

            if (keyHandler.isDown_released())
                extraScore = 0;

        }*/


    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {

      //  Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; i--) {

            try {
                Thread.sleep(
/*Main.audioPlayer.GAME_OVER_SOUND_LENGTH / amount / 1000*/
25);
            } catch (InterruptedException ignored) {
            }

            if (elementsStayOnField.size() > 0)
                elementsStayOnField.remove(i);

            repaint();
        }
    }

    private void clearAnimation() {
        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;

            if (helperForDeleting < 5) {


/* if (helperForDeleting == 0)
                    playClearLinesAudio();*/


                helperForDeleting++;

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
                    setForeground(transparentColor);
                    backgroundColor = transparentColor;
                }

                for (int el : indexesOfDeletingLines)
                    deleteLine(el);

                indexesOfDeletingLines.clear();
                counterOldForFalling = System.nanoTime();

            }
            repaint();
        }
    }

    public void startNewGame() {

        resetPlayValues();
        setFieldMatrix();
      //  Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);

        getRandom();
        setFirstCurrentTetrominoStepsAndColor();
        setTetrisLabels();
        startNewTread();
      //  Main.tetrisPanel.setVisible(true);
        requestFocusInWindow();
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

        level = 1;

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

     //   currentTetromino = new Tetromino(coordinates, (byte)0, DEFAULT, (byte) 0, (byte) 0);

        updateNextTetromino();

        currentTetromino = new Tetromino(coordinates, Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.nextTetromino, DEFAULT, (byte) 0, (byte) 0);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

       paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        radius = getHeight() / 20.0;

        if (grid)
            Painting.drawLines(g2d, getWidth(), getHeight(), radius);

        //clear game animations:
        if (clearAnimation) {

            // first type of clear lines animation:
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
                Painting.showRandomColorClearLinesAnimation(g2d, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);

                // second type of clear lines animation:
            else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                Painting.showDisappearClearLinesAnimation(g2d, helperForDeleting, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);

        } else {


            Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);

            if (!gameOver) {
                Painting.paintCurrentTetrominoForRepainting(currentTetromino, stepX, stepY, g2d, radius, typeOfSquare);

                if (paintShadow)
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius, typeOfSquare);
            }
        }
    }


    private void checkGameOver() {
        for (int i = 0; i < 10; i++) {
            if (fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                gameOver = true;
                break;
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
                if (!indexesOfDeletingLines.contains(deletingLine))
                    indexesOfDeletingLines.add(deletingLine);
            }
        }
    }

    private void setLines() {
      //  Main.tetrisPanel.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            level++;
            setLevel();
        }
    }

    private void setLevel() {
       // Main.tetrisPanel.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + level);
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
      //  Main.tetrisPanel.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>" + score);
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

    private boolean checkIsElementAlmostFell() {

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        boolean isFell = Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y -= 1;

        return isFell;

    }

    private void updateNextTetromino() {
        getRandom();
        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.repaint();
    }

    private void getRandom() {
       if (randomType == OLD_STYLE_RANDOM)
          // currentTetromino.tetrominoType = Randomizer.oldStyleRandomTetromino();
            Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.nextTetromino = Randomizer.oldStyleRandomTetromino();
        else {
            Object[] randomObject;
            randomObject = Randomizer.newStyleRandomTetromino(usedTetrominoes, amountUsedTetrominoes);
            usedTetrominoes = (byte[]) randomObject[0];
            amountUsedTetrominoes = (byte) randomObject[1];

          //  currentTetromino.tetrominoType = (byte) randomObject[2];

           Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.nextTetromino = (byte) randomObject[2];
        }
    }

    private void updateCurrentTetromino() {

       currentTetromino.tetrominoType = Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.nextTetromino;
      //  getRandom();//
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

    Dimension d;
    Container c;
    double w;
    double h;
    double s;

    @Override
    public Dimension getPreferredSize() {
        d = super.getPreferredSize();
        c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 20);
        }

        w =
/*(int)*/
 d.getWidth();
        h =
/*(int)*/
 d.getHeight();
        s = (Math.min(w, h));

        return new Dimension((int) Math.round(s * 0.41 / 10) * 10, (int) Math.round(s * 0.82 / 20) * 20);
    }
}
