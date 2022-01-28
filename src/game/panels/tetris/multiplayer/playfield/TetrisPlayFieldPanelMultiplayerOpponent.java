//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
    public byte typeOfSquare = 0;
    Thread waitingThread;
    double radius = 1.0D;
    Color color = new Color(0, 0, 0, 100);
    Dimension d;
    Container c;

    public TetrisPlayFieldPanelMultiplayerOpponent() {
        this.setOpaque(false);
        this.setForeground(transparentColor);
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        this.indexesOfDeletingLines = new ArrayList();
    }

    public synchronized void waiting() {
        System.out.println("start waiting for an opponent");

        while(this.waitingOpponent) {
            for(int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.waitingString = "waiting for an opponent";
                } else if (i == 1) {
                    this.waitingString = "waiting for an opponent.";
                } else if (i == 2) {
                    this.waitingString = "waiting for an opponent..";
                } else {
                    this.waitingString = "waiting for an opponent...";
                }

                if (!this.waitingOpponent) {
                    break;
                }

                this.repaint();

                try {
                    Thread.sleep(600L);
                } catch (InterruptedException var3) {
                    var3.printStackTrace();
                }
            }

            if (!this.waitingOpponent) {
                break;
            }
        }

        System.out.println("stop waiting for an opponent");
    }

    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.waitingOpponent) {
            this.drawCenteredString(g2d, this.waitingString, new Rectangle(0, 0, this.getWidth(), this.getHeight()), Main.FONT);
        } else {
            if (this.getWidth() < this.getHeight()) {
                this.radius = (double)this.getWidth() / 10.0D;
            } else {
                this.radius = (double)this.getHeight() / 20.0D;
            }

            if (this.gameOverPainting) {
                Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
                this.drawCenteredString(g2d, "game over", new Rectangle(0, 0, this.getWidth(), this.getHeight()), Main.FONT);
                this.gameOverPainting = false;
            } else {
                if (this.grid) {
                    Painting.drawLines(g2d, this.getWidth(), this.getHeight(), this.radius);
                }

                Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
                if (!this.gameOver) {
                    Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
                    if (this.currentTetromino != null) {
                        Painting.paintCurrentTetromino(this.currentTetromino, g2d, this.radius, this.typeOfSquare);
                        if (this.paintShadow) {
                            Painting.paintCurrentTetrominoShadow(this.fieldMatrix, this.currentTetromino, g2d, this.radius, this.typeOfSquare);
                        }
                    }
                }

            }
        }
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        font = Main.FONT.deriveFont((float)font.getSize() / 1.5F);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public Dimension getPreferredSize() {
        this.d = super.getPreferredSize();
        this.c = this.getParent();
        if (this.c != null) {
            this.d = this.c.getSize();
            int w = (int)this.d.getWidth();
            int h = (int)this.d.getHeight();
            int s = Math.min(w, h);
            return new Dimension((int)Math.round((double)s * 0.41D / 10.0D) * 10, (int)Math.round((double)s * 0.82D / 20.0D) * 20);
        } else {
            return new Dimension(100, 100);
        }
    }
}
