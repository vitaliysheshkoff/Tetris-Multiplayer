/*
package game.panels.tetris.singleplayer.playfield;

import game.dialogs.ScoreDialog;
import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.panels.tetris.controller.Moving;
import game.panels.tetris.controller.Pause;
import game.panels.tetris.controller.Randomizer;
import game.panels.tetris.controller.Rotation;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.start.Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class RepaintingThread implements Runnable {
    private long counterOldForFalling;
    private double stepY;
    private double stepX;
    private final int FPS = 60;
    private final long NS_PER_UPDATE = (long) ((1.0d / FPS) * 1000000000);

    private boolean gameOver;
    public byte amountUsedTetrominoes;
    public byte randomType;
    public byte amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte helperForDeleting;
    public byte clearLinesAnimationType;
    public byte music;
    public long score;

    @Override
    public void run() {

        byte music;
        Main.audioPlayer.playMusic(music);
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
                            */
/*new Thread(()->{
                                stepY = 0;
                                stepX = 0;
                                for(int i = 0; i <FPS/3; i++){
                                    stepY +=1./FPS/3;
                                    update();
                                    repaint();
                                    try {
                                        Thread.sleep(3);
                                    } catch (InterruptedException ignored) {
                                    }
                                }
                                Moving.pressDownKey(currentTetromino, fieldMatrix);
                                stepY = 0;
                                stepX = 0;
                                counterOldForFalling = System.nanoTime();
                            }).start();
                            counterOldForFalling = System.nanoTime();*//*

                            */
/*stepY = 0;
                            stepX = 0;
                            for(int i = 0; i <FPS/3; i++){
                                stepY +=1./FPS/3;
                                update();
                                repaint();
                                try {
                                    Thread.sleep(3);
                                } catch (InterruptedException ignored) {
                                }
                            }
                            Moving.pressDownKey(currentTetromino, fieldMatrix);
                            stepY = 0;
                            stepX = 0;
                            counterOldForFalling = System.nanoTime();*//*


                           */
/* new Thread(()->{
                                stepY = 0;
                                stepX = 0;
                                for(int i = 0; i <FPS/3; i++){
                                    stepY +=1./FPS/3;
                                    update();
                                    repaint();
                                    try {
                                        Thread.sleep(3);
                                    } catch (InterruptedException ignored) {
                                    }
                                }
                                Moving.pressDownKey(currentTetromino, fieldMatrix);
                                stepY = 0;
                                stepX = 0;
                                counterOldForFalling = System.nanoTime();
                                thread.interrupt();

                            }).start();
                            thread.join();*//*


                            Moving.pressDownKey(currentTetromino, fieldMatrix);
                            repaint();
                        }
                    }

                    stepY = 0;
                    stepX = 0;
                    counterOldForFalling = System.nanoTime();
                }

                //print FPS
                if (counterDelta >= 1000000000 / 3) {
                    System.out.println(frames / (counterDelta / 1000000000.0));
                    Main.tetrisPanel.tetrisNextTetrominoPanel.fps = frames / (counterDelta / (1000000000.0));
                    Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
                    frames = 0;
                    counterOld = System.nanoTime();
                }

                if (delta >= NS_PER_UPDATE) {
                    missedTime = delta - NS_PER_UPDATE;
                    old = System.nanoTime() - missedTime;

                    //render
                    update();
                    checkGameOver();
                    checkLine();

                    //clearAnimation
                    if (indexesOfDeletingLines.size() > 0)
                        clearAnimation = true;
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
            Main.tetrisPanel.saveGame();
            Main.audioPlayer.stopMusic();

            gameOverRepaint(elementsStayOnField);
            goLeaderBoardPanel();
            clearDatFile();
        }
    }

    private void clearAnimation() {
        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;

            if (helperForDeleting < 5) {

                if (helperForDeleting == 0)
                    playClearLinesAudio();

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

    private boolean checkIsElementAlmostFell() {

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        boolean isFell = Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y -= 1;

        return isFell;

    }

    private void lastMove() {

        Main.audioPlayer.playHardDrop();

        SquareOfTetromino squareOfTetromino;
        for (int j = 0; j < 4; j++) {
            squareOfTetromino = new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y), currentTetromino.tetrominoType);
            elementsStayOnField.add(squareOfTetromino);

            if (currentTetromino.coordinates[j].y > -2)
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
        }

        checkGameOver();

        updateCurrentTetromino();
        updateNextTetromino();

        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;

        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);

    }

    private void update() {

        if (!gameOver && !clearAnimation) {
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
        Main.tetrisPanel.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>" + score);
    }

    private void goLeaderBoardPanel() {

        Main.tetrisFrame.remove(Main.tetrisPanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();

        scoreDialog = new ScoreDialog(Main.tetrisFrame, true);

        Main.leaderBoardPanel.newPotentialLeader = scoreDialog.playerNameField.getText();
        if (scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader))
            Main.leaderBoardPanel.newPotentialLeader = "player";

        Main.leaderBoardPanel.saveLeaderBoardAfterGameOver(false);
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);

        Main.audioPlayer.playClick();
    }

    private void clearDatFile() {
        try {
            PrintWriter writer = new PrintWriter(new File(System.getProperty("user.dir"), Main.RESUME_FILE_NAME).getAbsolutePath());
            writer.print("");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void playClearLinesAudio() {

        if ( indexesOfDeletingLines.size() == 4)
            Main.audioPlayer.playTetris();
        else
            Main.audioPlayer.playClearLine();
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

    private void setLines() {
        Main.tetrisPanel.tetrisLinesAmountLabel.setText("Lines: " +  amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            level++;
            setLevel();
        }
    }

    private void setLevel() {
        Main.tetrisPanel.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" +  level);
    }

    private void updateNextTetromino() {
        getRandom();
        Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
    }

    private void getRandom() {
        if ( randomType == OLD_STYLE_RANDOM)
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
            if ( fieldMatrix[2][i] == 1 &&  currentTetromino.tetrominoType != TetrisNextTetrominoPanel.I) {
                stepYBack = true;
                break;
            }
        }
        if (stepYBack)
             currentTetromino.stepY = -1;
        else
              currentTetromino.stepY = 0;

        if ( currentTetromino.tetrominoType == TetrisNextTetrominoPanel.O)
             currentTetromino.stepX = 4;
        else
             currentTetromino.stepX = 3;

         currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates( currentTetromino);
          currentTetromino = Rotation.doRotation( currentTetromino);
    }
}
*/
