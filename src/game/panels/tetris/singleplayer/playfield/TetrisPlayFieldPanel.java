package game.panels.tetris.singleplayer.playfield;

import game.dialogs.ScoreDialog;
import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.*;
import game.serial.GameSaver;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class TetrisPlayFieldPanel extends JPanel implements Runnable {
    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0;
    public static final byte RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1;
    public static final byte NEW_STYLE_RANDOM = 2;
    public static final byte DEFAULT = 0;
    public static final byte CW = 1;
    public static final byte DCW = 2;
    public static final byte CCW = 3;
    public static final byte DCCW = 4;
    public boolean suspendFlag;
    public boolean interruptFlag;
    public volatile boolean gameOver;
    public boolean grid;
    public boolean paintShadow;
    public volatile boolean clearAnimation;
    public byte[][] fieldMatrix;
    public byte[] usedTetrominoes;
    public byte amountUsedTetrominoes;
    public byte randomType;
    public int amountOfDeletingLinesBetweenTetrominoes;
    public int amountOfDeletingLinesBetweenLevels;
    public int level;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte helperForDeleting;
    public byte clearLinesAnimationType;
    public byte music;
    public long score;
    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;
    public Thread thread;
    Color backgroundColor = new Color(0, 0, 0, 100);
    Color transparentColor = new Color(0, 0, 0, 100);
    Color transparentColor2 = new Color(0, 0, 0, 2);
    ScoreDialog scoreDialog;
    GameSaver gameSaver = null;
    OptionsSaver optionsSaver = null;
    OptionsSaver optionsGetter = null;
    public KeyHandler keyHandler;
    private final int FPS = 60;
    private final long NS_PER_UPDATE = 16666666L;
    private long counterOldForFalling;
    private double stepY;
    private double stepX;
    double radius;
    public byte typeOfSquare = 0;
    Dimension d;
    Container c;
    double w;
    double h;
    double s;

    public TetrisPlayFieldPanel() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        this.indexesOfDeletingLines = new ArrayList();
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setForeground(this.transparentColor);
    }

    private void lastMove() {
        Main.audioPlayer.playHardDrop();

        for(int j = 0; j < 4; ++j) {
            SquareOfTetromino squareOfTetromino = new SquareOfTetromino(new ByteCoordinates(this.currentTetromino.coordinates[j].x, this.currentTetromino.coordinates[j].y), this.currentTetromino.tetrominoType);
            this.elementsStayOnField.add(squareOfTetromino);
            if (this.currentTetromino.coordinates[j].y > -2) {
                this.fieldMatrix[this.currentTetromino.coordinates[j].y + 1][this.currentTetromino.coordinates[j].x + 1] = 1;
            }
        }

        this.checkGameOver();
        this.updateCurrentTetromino();
        this.updateNextTetromino();
        if (this.extraScore > 0) {
            this.score += (long)this.extraScore;
        }

        this.extraScore = 0;
        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(this.currentTetromino.tetrominoType);
    }

    public void run() {
        Main.audioPlayer.playMusic(this.music);
        System.out.println(Thread.currentThread().getName() + " start");
        this.gameOver = false;
        long old = System.nanoTime();
        long counterOld = System.nanoTime();
        double frames = 0.0D;
        this.counterOldForFalling = System.nanoTime();
        long counterOldForClearing = System.nanoTime();
        this.stepY = 0.0D;
        this.stepX = 0.0D;

        while(!this.gameOver) {
            try {
                synchronized(this) {
                    while(this.suspendFlag) {
                        this.wait();
                    }
                }

                long current = System.nanoTime();
                long delta = current - old;
                long counterDelta = current - counterOld;
                long counterTimeOfFalling = current - this.counterOldForFalling;
                long counterTimeOfClearing = current - counterOldForClearing;
                if (this.clearAnimation && counterTimeOfClearing >= 55555555L) {
                    this.stepY = 0.0D;
                    this.clearAnimation();
                    counterOldForClearing = System.nanoTime();
                }

                int speedLevel;
                if (this.level < 21) {
                    speedLevel = this.level;
                } else {
                    speedLevel = 20;
                }

                if (counterTimeOfFalling >= (long)(1000000000 / (speedLevel + 1) * 2)) {
                    System.out.println("fall");
                    if (this.checkIsElementAlmostFell()) {
                        this.lastMove();
                        this.repaint();
                    } else if (!this.clearAnimation) {
                        Moving.pressDownKey(this.currentTetromino, this.fieldMatrix);
                        this.repaint();
                    }

                    this.stepY = 0.0D;
                    this.stepX = 0.0D;
                    this.counterOldForFalling = System.nanoTime();
                }

                if (counterDelta >= 333333333L) {
                    System.out.println(frames / ((double)counterDelta / 1.0E9D));
                    Main.tetrisPanel.tetrisNextTetrominoPanel.fps = frames / ((double)counterDelta / 1.0E9D);
                    Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
                    frames = 0.0D;
                    counterOld = System.nanoTime();
                }

                if (delta >= 16666666L) {
                    long missedTime = delta - 16666666L;
                    old = System.nanoTime() - missedTime;
                    this.update();
                    this.checkGameOver();
                    this.checkLine();
                    if (this.indexesOfDeletingLines.size() > 0) {
                        this.clearAnimation = true;
                    } else {
                        this.clearAnimation = false;
                    }

                    this.checkScore();
                    if (this.checkIsElementAlmostFell()) {
                        this.stepY = 0.0D;
                        this.stepX = 0.0D;
                    }

                    ++frames;
                } else {
                    Thread.sleep(1L);
                }
            } catch (InterruptedException var26) {
            }
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var24) {
        }

        if (!this.interruptFlag) {
            Main.tetrisPanel.saveGame();
            Main.audioPlayer.stopMusic();
            this.gameOverRepaint(this.elementsStayOnField);
            this.goLeaderBoardPanel();
            this.clearDatFile();
        }

    }

    private void update() {
        if (!this.gameOver && !this.clearAnimation) {
            if (this.keyHandler.isLeft()) {
                Moving.pressLeftKey(this.currentTetromino, this.fieldMatrix);
                this.keyHandler.setLeft(false);
                this.repaint();
            } else if (this.keyHandler.isRight()) {
                Moving.pressRightKey(this.currentTetromino, this.fieldMatrix);
                this.keyHandler.setRight(false);
                this.repaint();
            } else if (!this.keyHandler.isDown()) {
                if (this.keyHandler.isHardDrop()) {
                    this.extraScore += Moving.pressHardDropKey(this.fieldMatrix, this.currentTetromino);
                    this.lastMove();
                    this.keyHandler.setHardDrop(false);
                    this.stepY = 0.0D;
                    this.counterOldForFalling = System.nanoTime();
                    this.repaint();
                } else if (this.keyHandler.isCcw_rotation()) {
                    Rotation.pressCCWKey(this.fieldMatrix, this.currentTetromino);
                    this.keyHandler.setCcw_rotation(false);
                    this.repaint();
                } else if (this.keyHandler.isCw_rotation()) {
                    Rotation.pressCWKey(this.fieldMatrix, this.currentTetromino);
                    this.keyHandler.setCw_rotation(false);
                    this.repaint();
                } else if (this.keyHandler.isExit()) {
                    this.mySuspend();
                    Main.audioPlayer.playClick();
                    this.gameOver = true;
                    this.myInterrupt();
                    Main.tetrisPanel.saveGame();
                    Main.audioPlayer.stopMusic();
                    Main.tetrisFrame.remove(Main.tetrisPanel);
                    Main.tetrisFrame.add(Main.menuPanel);
                    Main.tetrisFrame.revalidate();
                    Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
                    Main.tetrisFrame.repaint();
                    Main.menuPanel.selectCurrentButton();
                    Main.menuPanel.requestFocusInWindow();
                    this.keyHandler.setExit(false);
                } else if (this.keyHandler.isPause()) {
                    Pause.pressPauseKey();
                    this.keyHandler.setPause(false);
                    this.repaint();
                }
            } else {
                ++this.currentTetromino.stepY;

                int i;
                for(i = 0; i < 4; ++i) {
                    ++this.currentTetromino.coordinates[i].y;
                }

                if (Moving.isTetrominoConnected(this.currentTetromino.coordinates, this.fieldMatrix)) {
                    --this.currentTetromino.stepY;

                    for(i = 0; i < 4; ++i) {
                        --this.currentTetromino.coordinates[i].y;
                    }

                    this.lastMove();
                    this.counterOldForFalling = System.nanoTime();
                } else {
                    --this.currentTetromino.stepY;

                    for(i = 0; i < 4; ++i) {
                        --this.currentTetromino.coordinates[i].y;
                    }

                    this.extraScore += Moving.pressDownKey(this.currentTetromino, this.fieldMatrix);
                    System.out.println("DOWN");
                    Main.audioPlayer.playMove();
                    this.stepY = 0.0D;
                    this.counterOldForFalling = System.nanoTime();
                }

                this.keyHandler.setDown(false);
                this.repaint();
            }

            if (this.keyHandler.isDown_released()) {
                this.extraScore = 0;
            }
        }

    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {
        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for(int i = amount - 1; i >= 0; --i) {
            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / (long)amount / 1000L);
            } catch (InterruptedException var5) {
            }

            if (elementsStayOnField.size() > 0) {
                elementsStayOnField.remove(i);
            }

            this.repaint();
        }

    }

    private void clearAnimation() {
        if (this.indexesOfDeletingLines.size() > 0) {
            this.clearAnimation = true;
            if (this.helperForDeleting < 5) {
                if (this.helperForDeleting == 0) {
                    this.playClearLinesAudio();
                }

                ++this.helperForDeleting;
                if (this.indexesOfDeletingLines.size() == 4) {
                    if (this.backgroundColor == this.transparentColor2) {
                        this.backgroundColor = this.transparentColor;
                    } else {
                        this.backgroundColor = this.transparentColor2;
                    }
                }
            } else {
                this.clearAnimation = false;
                this.helperForDeleting = 0;
                if (this.indexesOfDeletingLines.size() == 4) {
                    Main.tetrisPanel.tetrisPlayFieldPanel.setForeground(this.transparentColor);
                    this.backgroundColor = this.transparentColor;
                }

                Iterator var1 = this.indexesOfDeletingLines.iterator();

                while(var1.hasNext()) {
                    int el = (Integer)var1.next();
                    this.deleteLine(el);
                }

                this.indexesOfDeletingLines.clear();
                this.counterOldForFalling = System.nanoTime();
            }

            this.repaint();
        }

    }

    private void playClearLinesAudio() {
        if (this.indexesOfDeletingLines.size() == 4) {
            Main.audioPlayer.playTetris();
        } else {
            Main.audioPlayer.playClearLine();
        }

    }

    private void goLeaderBoardPanel() {
        Main.tetrisFrame.remove(Main.tetrisPanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
        this.scoreDialog = new ScoreDialog(Main.tetrisFrame, true, this.score);
        Main.leaderBoardPanel.newPotentialLeader = this.scoreDialog.playerNameField.getText();
        if (this.scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader)) {
            Main.leaderBoardPanel.newPotentialLeader = "player";
        }

        Main.leaderBoardPanel.saveLeaderBoardAfterGameOver(false);
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);
        Main.audioPlayer.playClick();
    }

    private void clearDatFile() {
        try {
            PrintWriter writer = new PrintWriter((new File(System.getProperty("user.dir"), "resume.dat")).getAbsolutePath());
            writer.print("");
            writer.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void startNewGame() {
        this.deserializeOptionsForNewGame();
        Main.tetrisPanel.setVisible(false);
        this.keyHandler.resetValues();
        this.resetPlayValues();
        this.setFieldMatrix();
        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(this.currentTetromino.tetrominoType);
        this.getRandom();
        this.setFirstCurrentTetrominoStepsAndColor();
        this.setTetrisLabels();
        this.startNewTread();
        Main.tetrisPanel.setVisible(true);
        this.requestFocusInWindow();
    }

    private void setTetrisLabels() {
        this.setScore();
        this.setLevel();
        this.setLines();
    }

    private void startNewTread() {
        this.thread = new Thread(this, "repainting thread");
        this.thread.start();
    }

    private void resetPlayValues() {
        Main.tetrisPanel.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0.0D;
        this.elementsStayOnField = new ArrayList();
        this.amountOfDeletingLinesBetweenTetrominoes = 0;
        this.amountOfDeletingLinesBetweenLevels = 0;
        this.score = 0L;
        this.extraScore = 0;
        this.suspendFlag = false;
        this.interruptFlag = false;
        this.usedTetrominoes = new byte[7];
        this.fieldMatrix = new byte[22][12];
        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        int i;
        for(i = 0; i < 4; ++i) {
            coordinates[i] = new ByteCoordinates();
        }

        if (this.randomType == 2) {
            for(i = 0; i < 7; ++i) {
                this.usedTetrominoes[i] = -1;
            }
        }

        this.updateNextTetromino();
        this.currentTetromino = new Tetromino(coordinates, Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino, (byte)0, (byte)0, (byte)0);
    }

    public void resumeGame() {
        this.deserializeOptionsToResumeGame();
        this.deserializeGame();
        this.setTetrisLabels();
        this.interruptFlag = false;
        this.startNewTread();
        this.myResume();
        this.requestFocusInWindow();
    }

    private void deserializeGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "resume.dat")).getAbsolutePath()));
            this.gameSaver = (GameSaver)ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException var2) {
            var2.printStackTrace();
        }

        assert this.gameSaver != null;

        this.amountUsedTetrominoes = this.gameSaver.getAmountUsedTetrominoes();
        this.usedTetrominoes = this.gameSaver.getUsedTetrominoes();
        this.randomType = this.gameSaver.getRandomType();
        this.fieldMatrix = this.gameSaver.getFieldMatrix();
        Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = this.gameSaver.getNextTetromino();
        Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
        this.currentTetromino = this.gameSaver.getCurrentTetromino();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_I = this.gameSaver.getAmount_I();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_O = this.gameSaver.getAmount_O();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_L = this.gameSaver.getAmount_L();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_J = this.gameSaver.getAmount_J();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_S = this.gameSaver.getAmount_S();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_Z = this.gameSaver.getAmount_Z();
        Main.tetrisPanel.tetrisStatisticsPanel.amount_T = this.gameSaver.getAmount_T();
        Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = this.gameSaver.getNextTetromino();
        Main.tetrisPanel.tetrisPlayFieldPanel.stepYBeforePause = this.gameSaver.getStepYBeforePause();
        Main.audioPlayer.musicFramePosition = this.gameSaver.getMusicFramePosition();
        this.elementsStayOnField = this.gameSaver.getElementsStayOnField();
        this.amountOfDeletingLinesBetweenTetrominoes = this.gameSaver.getAmountOfDeletingLinesBetweenTetrominoes();
        this.amountOfDeletingLinesBetweenLevels = this.gameSaver.getAmountOfDeletingLinesBetweenLevels();
        this.score = this.gameSaver.getScore();
        this.level = this.gameSaver.getLevel();
        this.extraScore = this.gameSaver.getExtraScore();
    }

    private void deserializeOptionsToResumeGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            Throwable var2 = null;

            try {
                this.optionsSaver = (OptionsSaver)ois.readObject();
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (ois != null) {
                    if (var2 != null) {
                        try {
                            ois.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        ois.close();
                    }
                }

            }
        } catch (ClassNotFoundException | IOException var14) {
            var14.printStackTrace();
        }

        assert this.optionsSaver != null;

        this.getSettingsNotAffectingTheGame(this.optionsSaver);
    }

    private void getSettingsNotAffectingTheGame(OptionsSaver optionsSaver) {
        this.keyHandler.cwKey = optionsSaver.getCwKey();
        this.keyHandler.ccwKey = optionsSaver.getCcwKey();
        this.keyHandler.rightKey = optionsSaver.getRightKey();
        this.keyHandler.leftKey = optionsSaver.getLeftKey();
        this.keyHandler.downKey = optionsSaver.getDownKey();
        this.keyHandler.hardDropKey = optionsSaver.getHardDropKey();
        this.keyHandler.pauseKey = optionsSaver.getPauseKey();
        this.keyHandler.exitMenuKey = optionsSaver.getExitMenuKey();
        Main.tetrisPanel.backgroundType = optionsSaver.getBackgroundType();
        this.clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        this.paintShadow = optionsSaver.getShadow();
        this.grid = optionsSaver.getGrid();
        this.music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double)optionsSaver.getMusicVolume() / 100.0D;
        Main.audioPlayer.soundsVolume = (double)optionsSaver.getSoundsVolume() / 100.0D;
    }

    private void deserializeOptionsForNewGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            this.optionsGetter = (OptionsSaver)ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException var2) {
            var2.printStackTrace();
        }

        assert this.optionsGetter != null;

        this.level = this.optionsGetter.getStartLevel();
        this.randomType = this.optionsGetter.getRandomType();
        this.getSettingsNotAffectingTheGame(this.optionsGetter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.radius = (double)this.getHeight() / 20.0D;
        if (this.grid) {
            Painting.drawLines(g2d, this.getWidth(), this.getHeight(), this.radius);
        }

        if (this.clearAnimation) {
            if (this.clearLinesAnimationType == 1) {
                Painting.showRandomColorClearLinesAnimation(g2d, this.elementsStayOnField, this.indexesOfDeletingLines, this.radius, this.typeOfSquare);
            } else if (this.clearLinesAnimationType == 0) {
                Painting.showDisappearClearLinesAnimation(g2d, this.helperForDeleting, this.elementsStayOnField, this.indexesOfDeletingLines, this.radius, this.typeOfSquare);
            }
        } else {
            Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
            if (!this.gameOver) {
                Painting.paintCurrentTetrominoForRepainting(this.currentTetromino, this.stepX, this.stepY, g2d, this.radius, this.typeOfSquare);
                if (this.paintShadow) {
                    Painting.paintCurrentTetrominoShadow(this.fieldMatrix, this.currentTetromino, g2d, this.radius, this.typeOfSquare);
                }
            }
        }

    }

    private void checkGameOver() {
        for(int i = 0; i < 10; ++i) {
            if (this.fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                this.gameOver = true;
                break;
            }
        }

    }

    private void checkLine() {
        for(int i = 0; i < 21; ++i) {
            int counter = 0;

            for(int j = 1; j < 11; ++j) {
                if (this.fieldMatrix[i][j] == 1) {
                    ++counter;
                }
            }

            if (counter == 10 && !this.indexesOfDeletingLines.contains(i)) {
                this.indexesOfDeletingLines.add(i);
            }
        }

    }

    private void setLines() {
        Main.tetrisPanel.tetrisLinesAmountLabel.setText("Lines: " + this.amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (this.amountOfDeletingLinesBetweenLevels == (this.level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            ++this.level;
            this.setLevel();
        }

    }

    private void setLevel() {
        Main.tetrisPanel.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + this.level);
    }

    private void checkScore() {
        if (this.amountOfDeletingLinesBetweenTetrominoes == 1) {
            this.score += 40L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 2) {
            this.score += 100L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 3) {
            this.score += 300L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 4) {
            this.score += 1200L * (long)(this.level + 1);
        }

        this.amountOfDeletingLinesBetweenTetrominoes = 0;
        this.setScore();
    }

    private void setScore() {
        Main.tetrisPanel.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>" + this.score);
    }

    private void deleteLine(int deletingLine) {
        ++this.amountOfDeletingLinesBetweenTetrominoes;
        ++this.amountOfDeletingLinesBetweenLevels;

        for(int i = deletingLine; i > 0; --i) {
            System.arraycopy(this.fieldMatrix[i - 1], 1, this.fieldMatrix[i], 1, 10);
        }

        this.elementsStayOnField.removeIf((elx) -> {
            return elx.coordinates.y == deletingLine - 1;
        });
        Iterator var4 = this.elementsStayOnField.iterator();

        while(var4.hasNext()) {
            SquareOfTetromino el = (SquareOfTetromino)var4.next();
            if (el.coordinates.y < deletingLine - 1) {
                ++el.coordinates.y;
            }
        }

        this.setLines();
        this.checkLevel();
    }

    private boolean checkIsElementAlmostFell() {
        for(int i = 0; i < 4; ++i) {
            ++this.currentTetromino.coordinates[i].y;
        }

        boolean isFell = Moving.isTetrominoConnected(this.currentTetromino.coordinates, this.fieldMatrix);

        for(int i = 0; i < 4; ++i) {
            --this.currentTetromino.coordinates[i].y;
        }

        return isFell;
    }

    private void updateNextTetromino() {
        this.getRandom();
        Main.tetrisPanel.tetrisNextTetrominoPanel.repaint();
    }

    private void getRandom() {
        if (this.randomType == 1) {
            Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = Randomizer.oldStyleRandomTetromino();
        } else {
            Object[] randomObject = Randomizer.newStyleRandomTetromino(this.usedTetrominoes, this.amountUsedTetrominoes);
            this.usedTetrominoes = (byte[])((byte[])randomObject[0]);
            this.amountUsedTetrominoes = (Byte)randomObject[1];
            Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino = (Byte)randomObject[2];
        }

    }

    private void updateCurrentTetromino() {
        this.currentTetromino.tetrominoType = Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino;
        this.currentTetromino.rotationType = 0;
        this.setFirstCurrentTetrominoStepsAndColor();
    }

    private void setFirstCurrentTetrominoStepsAndColor() {
        boolean stepYBack = false;

        for(int i = 1; i < 11; ++i) {
            if (this.fieldMatrix[2][i] == 1 && this.currentTetromino.tetrominoType != 0) {
                stepYBack = true;
                break;
            }
        }

        if (stepYBack) {
            this.currentTetromino.stepY = -1;
        } else {
            this.currentTetromino.stepY = 0;
        }

        if (this.currentTetromino.tetrominoType == 3) {
            this.currentTetromino.stepX = 4;
        } else {
            this.currentTetromino.stepX = 3;
        }

        this.currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(this.currentTetromino);
        this.currentTetromino = Rotation.doRotation(this.currentTetromino);
    }

    public synchronized void myInterrupt() {
        this.interruptFlag = true;
        this.thread.interrupt();
        System.out.println("tread interrupted!");
    }

    public void mySuspend() {
        this.stepYBeforePause = this.currentTetromino.stepY;
        this.suspendFlag = true;
        System.out.println("suspend");
    }

    public synchronized void myResume() {
        this.currentTetromino.stepY = this.stepYBeforePause;
        this.suspendFlag = false;
        this.notify();
        System.out.println("resume");
    }

    private void setFieldMatrix() {
        for(int i = 0; i < 22; ++i) {
            for(int j = 0; j < 12; ++j) {
                if (i == 21) {
                    this.fieldMatrix[i][j] = 1;
                } else if (j != 0 && j != 11) {
                    this.fieldMatrix[i][j] = 0;
                } else {
                    this.fieldMatrix[i][j] = 1;
                }
            }
        }

    }

    public Dimension getPreferredSize() {
        this.d = super.getPreferredSize();
        this.c = this.getParent();
        if (this.c != null) {
            this.d = this.c.getSize();
            this.w = this.d.getWidth();
            this.h = this.d.getHeight();
            this.s = Math.min(this.w, this.h);
            return new Dimension((int)Math.round(this.s * 0.4D / 10.0D) * 10, (int)Math.round(this.s * 0.8D / 20.0D) * 20);
        } else {
            return new Dimension(10, 20);
        }
    }
}
