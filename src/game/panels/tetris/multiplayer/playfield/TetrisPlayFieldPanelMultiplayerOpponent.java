package game.panels.tetris.multiplayer.playfield;

import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Painting;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisPlayFieldPanelMultiplayerOpponent extends JPanel {

    public boolean gameOver;
    public boolean grid = false;
    public boolean paintShadow = true;

    public byte[][] fieldMatrix;
    public int level;
    public long score;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;

    volatile boolean waitingOpponent = false;
    volatile String waitingString;
    public boolean gameOverPainting = false;
    static Color transparentColor = new Color(0, 0, 0, 100);

    Thread waitingThread;

    public TetrisPlayFieldPanelMultiplayerOpponent() {
        setOpaque(false);
        setForeground(transparentColor);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        indexesOfDeletingLines = new ArrayList<>();
    }

    public synchronized void waiting() {

        System.out.println("start waiting for an opponent");

        while (waitingOpponent) {

            for (int i = 0; i < 4; i++) {

                if (i == 0)
                    waitingString = "waiting for an opponent";

                else if (i == 1)
                    waitingString = "waiting for an opponent.";

                else if (i == 2)
                    waitingString = "waiting for an opponent..";

                else
                    waitingString = "waiting for an opponent...";

                if (!waitingOpponent)
                    break;

                repaint();
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!waitingOpponent)
                break;
        }

        System.out.println("stop waiting for an opponent");

    }

    double radius = 1;
    Color color = new Color(0, 0, 0, 100);

    public void paintComponent(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        if (waitingOpponent) {
            drawCenteredString(g2d, waitingString, new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT);
            return;
        }

        if (getWidth() < getHeight())
            radius = getWidth() / 10.;
        else
            radius = getHeight() / 20.0;


        if (gameOverPainting) {

            Painting.paintLyingElements(g2d, elementsStayOnField, radius);

            drawCenteredString(g2d, "game over", new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT);
            gameOverPainting = false;
            return;
        }

        if (grid)
            Painting.drawLines(g2d, getWidth(), getHeight(), radius);

        Painting.paintLyingElements(g2d, elementsStayOnField, radius);

        if (!gameOver) {

            Painting.paintLyingElements(g2d, elementsStayOnField, radius);

            if (currentTetromino != null) {
                Painting.paintCurrentTetromino(currentTetromino, g2d, radius);

                if (paintShadow)
                    Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius);
            }
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

    Dimension d;
    Container c;

    @Override
    public Dimension getPreferredSize() {
        d = super.getPreferredSize();
        c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(100, 100);
        }

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (Math.min(w, h));

        return new Dimension((int) (s * 0.41), (int) (s * 0.82));
    }
}
