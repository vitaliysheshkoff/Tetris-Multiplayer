package game.multiplayer.playfield;

import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;
import game.panels.tetris.playfield.controller.Moving;
import game.panels.tetris.playfield.controller.Painting;
import game.panels.tetris.playfield.controller.Rotation;
import game.start.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisPlayFieldPanelMultiplayerOpponent extends JPanel /*implements Runnable*/ {


    public static final int DISAPPEAR_CLEAR_LINES_ANIMATION = 0, RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final int OLD_STYLE_RANDOM = 1, NEW_STYLE_RANDOM = 2;
    public static final int PLAY_FIELD_BORDER = 5;
    public static final int RADIUS_OF_SQUARE = 40;
    public static final int DEFAULT = 0, CW = 1, DCW = 2, CCW = 3, DCCW = 4;
    public static final int LEFT = 1, RIGHT = 2;


    public boolean gameOver;
    public boolean grid = false;
    public boolean paintShadow = true;
    public boolean clearAnimation;

    public byte[][] fieldMatrix;
    public int[] usedTetrominoes;
    public int amountUsedTetrominoes;
    public int randomType;
    public int amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level;
    public long score;
    public int extraScore;
    public int stepYBeforePause;
    public int clearLinesAnimationType;
    public int helperForDeleting;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;

    volatile boolean waitingOpponent = false;
    volatile String waitingString;
    Thread waitingThread;
    public boolean gameOverPainting = false;

    public volatile boolean paintingFromThread = false;

    public TetrisPlayFieldPanelMultiplayerOpponent() {
      //  setVisible(true);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        indexesOfDeletingLines = new ArrayList<>();

        /*waitingThread = new Thread(this::waiting);*/
    }


    public synchronized void waiting() {

        System.out.println("start waiting for an opponent");


        while(waitingOpponent) {

            for (int i = 0; i < 4; i++) {

                if (i == 0)
                    waitingString = "waiting for an opponent";

                else if (i == 1)
                    waitingString = "waiting for an opponent.";

                else if (i == 2)
                    waitingString = "waiting for an opponent..";

                else
                    waitingString = "waiting for an opponent...";

                if(!waitingOpponent)
                    break;

                repaint();
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(!waitingOpponent)
                break;
        }

        System.out.println("stop waiting for an opponent");

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        if(waitingOpponent){
            drawCenteredString(g2d,waitingString, new Rectangle(0,0,getWidth(),getHeight()), Main.FONT);
        }



        if(gameOverPainting){

            Painting.paintLyingElements(g2d, elementsStayOnField);


            g2d.setColor(Color.RED);
            drawCenteredString(g2d,"game over", new Rectangle(0,0,getWidth(),getHeight()), new Font("Consolas", Font.PLAIN, 25));
            gameOverPainting = false;
        }

        if (paintingFromThread) {

        if (grid)
            Painting.drawLines(g2d);

        //clear game animations:
       /*  if (clearAnimation) {

            // first type of clear lines animation:
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
                PaintingForMultiplayerOpponent.showRandomColorClearLinesAnimation(g2d);

                // second type of clear lines animation:
            else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                PaintingForMultiplayerOpponent.showDisappearClearLinesAnimation(g2d);

        }*/ /*else {*/

            Painting.paintLyingElements(g2d, elementsStayOnField);

            if (!gameOver) {

              /*currentTetromino.coordinates =  Rotation.setCurrentTetrominoCoordinates(currentTetromino);

                if (currentTetromino.rotationType != DEFAULT)
                   currentTetromino =  Rotation.doRotation(currentTetromino);*/

                 // checkGameOver();

                    if (checkIsElementFell()) {
                   //  falling();
                     Painting.paintLyingElements(g2d,elementsStayOnField);

                    } else  {
                Painting.paintCurrentTetromino(currentTetromino, g2d);

                if (paintShadow)
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d);
                 }
            }
            paintingFromThread = false;
        }
    }


    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

  //  }

   /* private void falling() {
       // System.out.println("matrix before:\n");
        //showFieldMatrix();
        for (int j = 0; j < 4; j++) {
            if (*//*(*//*currentTetromino.coordinates[j].y*//* - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE + 1*//* < 22) {/////////////////////////////////////
                elementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, (byte)(currentTetromino.coordinates[j].y - 1)*//*RADIUS_OF_SQUARE*//*), currentTetromino.tetrominoType));
                fieldMatrix[*//*(*//*currentTetromino.coordinates[j].y *//*- PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE - 1 + 1*//*][*//*(*//*currentTetromino.coordinates[j].x*//* - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE*//* + 1] = 1;
            }
        }
        //System.out.println("matrix after:\n");
      //  showFieldMatrix();
        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;
      //  updateCurrentTetromino();
      //  updateNextTetromino();
       // Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmountForMultiplayer();
       // wakeUpThreadFromSleeping();
    }*/




   /* private void checkGameOver() {

        for (ByteCoordinates el : currentTetromino.coordinates) {
            if (*//*(*//*el.y*//* - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE*//* == 0) {
                if (fieldMatrix[1][*//*(*//*el.x *//*- PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE*//* + 1] == 1) {
                    System.out.println("game over!");
                    gameOver = true;
                    break;
                }
            }
        }
    }*/










    private boolean checkIsElementFell() {
        if (gameOver)
            return false;
        for (int i = 0; i < 4; i++) {
            if (Moving.isTetrominoConnected(currentTetromino.coordinates,fieldMatrix/*Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.currentTetromino.coordinates*/))
                return true;
        }
        return false;
    }

    /*public boolean isTetrominoConnected(Coordinates[] coordinates) {
        for (int i = 0; i < 4; i++) {
            if ((coordinates[i].y - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE + 1 > 21)/////////////////////////////////////
                return true;//////////////////////////////////////////////////////////////////////////
            if ((coordinates[i].x - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE < 0 || (coordinates[i].x - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE > 11) // for I Tetromino
                return true;
            else if (fieldMatrix[(coordinates[i].y - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE + 1][(coordinates[i].x - PLAY_FIELD_BORDER) / RADIUS_OF_SQUARE + 1] == 1) {
                return true;
            }
        }
        return false;
    }*/



}
