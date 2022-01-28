package game.panels.tetris.infopanels;

import game.panels.tetris.controller.Painting;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import static game.panels.tetris.controller.Painting.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisNextTetrominoPanel extends JPanel {
    public JLabel tetrisNextElementLabel;
    public static int START_PAINTING_X;
    public static int START_PAINTING_Y;
    public byte nextTetromino = -1;
    public static final byte I = 0;
    public static final byte J = 1;
    public static final byte L = 2;
    public static final byte O = 3;
    public static final byte S = 4;
    public static final byte T = 5;
    public static final byte Z = 6;
    static Color transparentColor = new Color(0, 0, 0, 100);
    public double fps = 0.0D;
    double w;
    double h;
    double s;
    Dimension d;
    Container c;

    public TetrisNextTetrominoPanel() {
        this.setOpaque(false);
        this.tetrisNextElementLabel = new JLabel("Next", 0);
        this.tetrisNextElementLabel.setFont(Main.FONT);
        this.setForeground(Color.WHITE);
        this.tetrisNextElementLabel.setBounds(0, 10, 190, 20);
        this.tetrisNextElementLabel.setForeground(Color.white);
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        this.add(this.tetrisNextElementLabel);
        this.setForeground(transparentColor);
    }

    public void paintComponent(Graphics g) {
        g.setColor(transparentColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.tetrisNextElementLabel.setBounds(0, 0, this.getWidth(), this.getHeight() / 4);
        double radius = (double)this.getHeight() / 6.0D;
        START_PAINTING_X = this.getWidth() / 2;
        START_PAINTING_Y = this.getHeight() / 2;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.nextTetromino == 3) {
            paintO(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 0) {
            paintIHorizontal(g2d, START_PAINTING_X, START_PAINTING_Y + (byte)((int)(radius / 2.0D)), radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 1) {
            paintJ(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 2) {
            paintL(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 4) {
            paintS(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 5) {
            paintT(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (this.nextTetromino == 6) {
            paintZ(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        if (this.fps != 0.0D) {
            this.paintFPS(g2d, "FPS: " + (new DecimalFormat("#0.00")).format(this.fps), new Rectangle(0, 3 * this.getHeight() / 4, this.getWidth(), this.getHeight() / 4), Main.FONT);
        }

    }

    private void paintFPS(Graphics g, String text, Rectangle rect, Font font) {
        font = Main.FONT.deriveFont((float)rect.height / 6.0F);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        g.setColor(new Color(255, 255, 255, 50));
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void paintO(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius), (byte)(startY - radius), Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius), startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, startX, (byte)(startY - radius), Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, startX, startY, Painting.O_COLOR, (double)radius, type);
    }

    public static void paintI(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY - 2 * radius), Color.CYAN, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY - radius), Color.CYAN, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Color.CYAN, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY + radius), Color.CYAN, (double)radius, type);
    }

    public static void paintIHorizontal(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - 2 * radius), (byte)(startY - radius), Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius), (byte)(startY - radius), Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, startX, (byte)(startY - radius), Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius), (byte)(startY - radius), Painting.I_COLOR, (double)radius, type);
    }

    public static void paintJ(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), (byte)(startY - radius), Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), startY, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintL(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), (byte)(startY - radius), Painting.L_COLOR, (double)radius, type);
    }

    public static void paintS(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY - radius), Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), (byte)(startY - radius), Painting.S_COLOR, (double)radius, type);
    }

    public static void paintT(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY - radius), Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), startY, Painting.T_COLOR, (double)radius, type);
    }

    public static void paintZ(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte)(startX - radius - radius / 2), (byte)(startY - radius), Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), (byte)(startY - radius), Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX - radius / 2), startY, Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(graphics2D, (byte)(startX + radius / 2), startY, Painting.Z_COLOR, (double)radius, type);
    }

    public static void paintO(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius), (int)((double)startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius), startY, Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (int)((double)startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, startY, Painting.O_COLOR, radius, type);
    }

    public static void paintIHorizontal(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - 2.0D * radius), (int)((double)startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius), (int)((double)startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (int)((double)startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius), (int)((double)startY - radius), Painting.I_COLOR, radius, type);
    }

    public static void paintJ(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), (int)((double)startY - radius), Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), startY, Painting.J_COLOR, radius, type);
    }

    public static void paintL(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), (int)((double)startY - radius), Painting.L_COLOR, radius, type);
    }

    public static void paintS(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), (int)((double)startY - radius), Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), (int)((double)startY - radius), Painting.S_COLOR, radius, type);
    }

    public static void paintT(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), (int)((double)startY - radius), Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), startY, Painting.T_COLOR, radius, type);
    }

    public static void paintZ(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int)((double)startX - radius - radius / 2.0D), (int)((double)startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), (int)((double)startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX - radius / 2.0D), startY, Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int)((double)startX + radius / 2.0D), startY, Painting.Z_COLOR, radius, type);
    }

    public Dimension getPreferredSize() {
        this.d = super.getPreferredSize();
        this.c = this.getParent();
        if (this.c != null) {
            this.d = this.c.getSize();
            this.w = this.d.getWidth();
            this.h = this.d.getHeight();
            this.s = Math.min(this.w, this.h);
            return new Dimension((int)(Math.round(this.s * 0.25D / 6.0D) + 1L) * 6, (int)(Math.round(this.s * 0.25D / 6.0D) + 1L) * 6);
        } else {
            return new Dimension(10, 20);
        }
    }
}
