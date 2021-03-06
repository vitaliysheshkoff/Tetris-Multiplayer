package game.panels.tetris.multiplayer.ai;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.controller.*;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.util.*;

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

    double radius;
    public byte typeOfSquare = 0;

    ArrayList<Checker.Info> steps;
    Checker.Info pauseSteps = null;

    private Stack<Byte> tetrominoesStack;
    private Stack<Byte> extraTetrominoesStack;

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

            if (currentTetromino.coordinates[j].y > -2) //////////////////////
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
        }

        checkGameOver();

        updateCurrentTetromino();
        // updateNextTetromino();

        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;

    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " start");
        gameOver = false;


        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }

                update();
                ai();

            } catch (InterruptedException ignored) {
                checkLine();
                try {
                    clearAnimation(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                if(pauseIndex >0 && pauseSize > 0){
                    pauseSteps = new Checker.Info(step.getRotation_type(),
                            step.getResult_matrix(),
                            new ArrayList<>(step.getMoves().subList(pauseIndex, pauseSize)));
                }
            }
        }

        // gameover:
        if (!interruptFlag) {
            repaint();
           // gameOverRepaint(elementsStayOnField);
        }
    }

    int pauseIndex = -1;
    int pauseSize = -1;
    Checker.Info step;
    private void ai() throws InterruptedException {

        if(pauseSteps != null) {
            step = pauseSteps;
        }
        else {
                updateCurrentTetromino();
            step = Checker.getMove(currentTetromino, fieldMatrix);
        }

        if (step != null) {

            if (step.getRotation_type() == CW) {
                currentTetromino.rotationType = CW;
            } else if (step.getRotation_type() == CCW) {
                currentTetromino.rotationType = CCW;
            } else if (step.getRotation_type() == DCW) {
                currentTetromino.rotationType = DCW;
            }

            int size = step.getMoves().size();
            pauseSize = size;
            int move;

            for (int i = 0; i < size; i++) {
                move = step.getMoves().get(i);
                if (move == Moving.LEFT) {
                    Moving.pressLeftKey(currentTetromino, fieldMatrix, false);
                    repaint();
                } else if (move == Moving.RIGHT) {
                    Moving.pressRightKey(currentTetromino, fieldMatrix, false);
                    repaint();
                } else if (move == Moving.DOWN) {
                    extraScore += Moving.pressDownKey(currentTetromino, fieldMatrix);
                    repaint();
                }

                pauseIndex =  i;
                /*if(suspendFlag) {
                    pauseSteps = new Checker.Info(step.getRotation_type(),
                            step.getResult_matrix(),
                            new ArrayList<>(step.getMoves().subList(i, size)));

                    return;
                }*/
                Thread.sleep(20);
            }
            pauseIndex = -1;
            pauseSize = -1;

            pauseSteps = null;
            lastMove();
            checkLine();
            clearAnimation(true);
            repaint();

        } else {
            gameOver = true;
            System.out.println("=========================game over===============================");
        }
    }

    private void update() {
        if (Main.multiplayerPanel2.battlePanel.playfield.gameOver) {
            mySuspend();
            myInterrupt();
        }

    }

    /*public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {

        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; i--) {

            try {
                Thread.sleep(25);
            } catch (InterruptedException ignored) {
            }

            if (elementsStayOnField.size() > 0)
                elementsStayOnField.remove(i);

            repaint();
        }
    }*/

    private void clearAnimation(boolean withAnimation) throws InterruptedException {

        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;

            if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                helperForDeleting = 0;

            for (int i = 0; i < 5; i++) {

                if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                    helperForDeleting++;

                if (indexesOfDeletingLines.size() == 4) {

                    if (backgroundColor == transparentColor2) {
                        backgroundColor = transparentColor;
                    } else {
                        backgroundColor = transparentColor2;
                    }

                }

                repaint();

            if(withAnimation)
                Thread.sleep(55);
            }
            backgroundColor = transparentColor;
            clearAnimation = false;


            for (int el : indexesOfDeletingLines)
                deleteLine(el);

            checkScore();

            indexesOfDeletingLines.clear();
        }
    }

    public void startNewGame() {

        resetPlayValues();
        setFieldMatrix();
        extraTetrominoesStack = new Stack<>();

        typeOfSquare = Main.multiplayerPanel2.battlePanel.playfield.typeOfSquare;
        clearLinesAnimationType = Main.multiplayerPanel2.battlePanel.playfield.clearLinesAnimationType;
        paintShadow = Main.multiplayerPanel2.battlePanel.playfield.paintShadow;
        grid = Main.multiplayerPanel2.battlePanel.playfield.grid;

        // getRandom();
        updateCurrentTetromino();
        setFirstCurrentTetrominoStepsAndColor();
        setTetrisLabels();
        startNewTread();
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

        grid = false;
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

        randomType = NEW_STYLE_RANDOM;

        if (randomType == NEW_STYLE_RANDOM) {

            for (int i = 0; i < 7; i++)
                usedTetrominoes[i] = -1;
        }

        //   currentTetromino = new Tetromino(coordinates, (byte)0, DEFAULT, (byte) 0, (byte) 0);

        //updateNextTetromino();

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

        if(gameOver){
            Painting.paintLyingElementsForGameOver(g2d, elementsStayOnField, radius, typeOfSquare);
            drawCenteredString(g2d,"game over", new Rectangle(0,0,getWidth(), getHeight()), Main.FONT, 1f);
            return;
        }

        if(suspendFlag) {
            Painting.paintLyingElementsForGameOver(g2d, elementsStayOnField, radius, typeOfSquare);
            drawCenteredString(g2d,  "[Pause]", new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT, 1f);
            return;
        }

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
                Painting.paintCurrentTetrominoForRepainting(currentTetromino, 0, 0, g2d, radius, typeOfSquare);

                if (paintShadow)
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius, typeOfSquare);
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
        Main.multiplayerPanel2.battlePanel.tetrisLinesAmountLabelOpponent.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            if (level <= 20)
                level++;

            setLevel();
        }
    }

    private void setLevel() {
        Main.multiplayerPanel2.battlePanel.tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>" + level);
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
        // the score is set in the "playingField"
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

    /*private void updateNextTetromino() {
        getRandom();
        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.repaint();
    }*/

    private void updateCurrentTetromino() {
        if (tetrominoesStack.size() > 0) {
            currentTetromino.tetrominoType = tetrominoesStack.pop();
            extraTetrominoesStack.push(currentTetromino.tetrominoType);
        }
        if (tetrominoesStack.size() == 0) {
            tetrominoesStack.addAll(extraTetrominoesStack);
            extraTetrominoesStack = new Stack<>();
        }

        System.out.println("size of original stack: " + tetrominoesStack.size() + "------------------");
        System.out.println("size of extra stack: " + extraTetrominoesStack.size() + "------------------");

        //  nextTetromino = tetrominoesStack.peek();
        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.nextTetromino = tetrominoesStack.peek();
        Main.multiplayerPanel2.battlePanel.tetrisNextTetrominoPanelOpponent.repaint();
        System.out.println(tetrominoesStack.size());
        currentTetromino.rotationType = 0;
        setFirstCurrentTetrominoStepsAndColor();
    }

    public void setTetrominoesStackFromCopy(Stack<Byte> stack) {
        tetrominoesStack = new Stack<>();

        for (Byte aByte : stack) {
            tetrominoesStack.push(aByte);
        }
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

        w = d.getWidth();
        h = d.getHeight();
        s = (Math.min(w, h));

        return new Dimension((int) Math.round(s * 0.41 / 10) * 10, (int) Math.round(s * 0.82 / 20) * 20);
    }
}
