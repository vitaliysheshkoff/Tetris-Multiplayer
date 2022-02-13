package game.panels.tetris.multiplayer.playfield;

import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Painting;
import game.start.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TetrisPlayFieldPanelMultiplayerOpponent extends JPanel {

    private static final Color transparentColor = new Color(0, 0, 0, 100);

    private final Color color = new Color(0, 0, 0, 100);

    public boolean gameOver;
    public boolean grid = false;
    public boolean paintShadow = true;
    public volatile boolean waitingOpponent = false;
    public boolean gameOverPainting = false;

    public byte typeOfSquare = 0;

    public byte[][] fieldMatrix;

    public int level;

    public long score;

    public Tetromino currentTetromino;

    public ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;

    private volatile String waitingString;

    public Thread waitingThread;

    public TetrisPlayFieldPanelMultiplayerOpponent() {
        setOpaque(false);
        setForeground(transparentColor);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        indexesOfDeletingLines = new ArrayList<>();
    }

    public synchronized void waiting() {
        System.out.println("start waiting for an opponent");

        while (waitingOpponent) {
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    waitingString = "waiting for an opponent";
                } else if (i == 1) {
                    waitingString = "waiting for an opponent.";
                } else if (i == 2) {
                    waitingString = "waiting for an opponent..";
                } else {
                    waitingString = "waiting for an opponent...";
                }

                if (!waitingOpponent) {
                    break;
                }

                repaint();

                try {
                    Thread.sleep(600L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!waitingOpponent) {
                break;
            }
        }

        System.out.println("stop waiting for an opponent");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            paint(g);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (waitingOpponent) {
            drawCenteredString(g2d, waitingString, new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT);
        } else {
            double radius;
            if (getWidth() < getHeight()) {
                radius = (double) getWidth() / 10.0D;
            } else {
                radius = (double) getHeight() / 20.0D;
            }

            if (gameOverPainting) {
                Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);
                drawCenteredString(g2d, "game over", new Rectangle(0, 0, getWidth(), getHeight()), Main.FONT);
                gameOverPainting = false;
            } else {
                if (grid) {
                    Painting.drawLines(g2d, getWidth(), getHeight(), radius);
                }

                Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);
                if (!gameOver) {
                    Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);
                    if (currentTetromino != null) {
                        Painting.paintCurrentTetromino(currentTetromino, g2d, radius, typeOfSquare);
                        if (paintShadow) {
                            Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius, typeOfSquare);
                        }
                    }
                }
            }
        }
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        font = Main.FONT.deriveFont((float) font.getSize() / 1.5F);

        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
            int w = (int) d.getWidth();
            int h = (int) d.getHeight();
            int s = Math.min(w, h);
            return new Dimension((int) Math.round((double) s * 0.41D / 10.0D) * 10, (int) Math.round((double) s * 0.82D / 20.0D) * 20);
        } else {
            return new Dimension(100, 100);
        }
    }
}
