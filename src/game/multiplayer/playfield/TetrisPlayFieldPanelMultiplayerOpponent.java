package game.multiplayer.playfield;

import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;
import game.panels.tetris.playfield.controller.Moving;
import game.panels.tetris.playfield.controller.Painting;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisPlayFieldPanelMultiplayerOpponent extends JPanel  {

    public boolean gameOver;
    public boolean grid = false;
    public boolean paintShadow = true;

    public byte[][] fieldMatrix;
    public int  level;
    public long score;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;

    volatile boolean waitingOpponent = false;
    volatile String waitingString;
    public boolean gameOverPainting = false;
    public volatile boolean paintingFromThread = false;
    static Color transparentColor = new Color(0,0,0,100);
    volatile boolean elementFell = false;

    Thread waitingThread;

    public TetrisPlayFieldPanelMultiplayerOpponent() {
        setOpaque(false);
        //setBackground(Color.BLACK);
        setForeground(transparentColor);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        indexesOfDeletingLines = new ArrayList<>();
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

    double radius = 1;
    Color color = new Color(0,0,0,100);
    public void paintComponent(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        if(waitingOpponent){
            drawCenteredString(g2d,waitingString, new Rectangle(0,0,getWidth(),getHeight()), Main.FONT);
        }


        if(getWidth() < getHeight())
            radius = getWidth() / 10.;
        else
            radius = getHeight() / 20.0;


        if(gameOverPainting){


            Painting.paintLyingElements(g2d, elementsStayOnField, radius);

          /*  g2d.setColor(Color.RED);*/
            drawCenteredString(g2d,"game over", new Rectangle(0,0,getWidth(),getHeight()), Main.FONT);
            gameOverPainting = false;
        }

        if (paintingFromThread) {



        if (grid)
            Painting.drawLines(g2d, getWidth(), getHeight(), radius);

        //clear game animations:
       /*  if (clearAnimation) {

            // first type of clear lines animation:
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
                PaintingForMultiplayerOpponent.showRandomColorClearLinesAnimation(g2d);

                // second type of clear lines animation:
            else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                PaintingForMultiplayerOpponent.showDisappearClearLinesAnimation(g2d);

        }*/ /*else {*/

            Painting.paintLyingElements(g2d, elementsStayOnField, radius);

            if (!gameOver) {

              /*currentTetromino.coordinates =  Rotation.setCurrentTetrominoCoordinates(currentTetromino);

                if (currentTetromino.rotationType != DEFAULT)
                   currentTetromino =  Rotation.doRotation(currentTetromino);*/

                 // checkGameOver();

                 //   if (checkIsElementFell()) {
                   //  falling();
                     Painting.paintLyingElements(g2d,elementsStayOnField, radius);

                  //  } else  {
                Painting.paintCurrentTetromino(currentTetromino, g2d, radius);

                if (paintShadow)
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius);
                 }
         //   }
            paintingFromThread = false;
        }
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {

        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.setFont(font);
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

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(100, 100);
        }

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);

        //  System.out.println("prefered size" + s / 2 + " " + s);
        return new Dimension((int) (s * 0.41), (int) (s * 0.82));
    }
}
