package game.panels.tetris.infopanels;

import game.panels.tetris.controller.Painting;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TetrisNextTetrominoPanel extends JPanel {

    public static final byte I = 0;
    public static final byte J = 1;
    public static final byte L = 2;
    public static final byte O = 3;
    public static final byte S = 4;
    public static final byte T = 5;
    public static final byte Z = 6;
    
    public JLabel tetrisNextElementLabel;
    
    public static int START_PAINTING_X;
    public static int START_PAINTING_Y;
    
    public byte nextTetromino = -1;

    static Color transparentColor = new Color(0, 0, 0, 100);
    
    public double fps = 0.0D;
    
    double w;
    double h;
    double s;
    
    Dimension d;
    Container c;

    public TetrisNextTetrominoPanel() {
       setOpaque(false);
       tetrisNextElementLabel = new JLabel("Next", SwingConstants.CENTER);
       tetrisNextElementLabel.setFont(Main.FONT);
       setForeground(Color.WHITE);
       tetrisNextElementLabel.setBounds(0, 10, 190, 20);
       tetrisNextElementLabel.setForeground(Color.white);
       setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
       add(tetrisNextElementLabel);
       setForeground(transparentColor);
    }

    public void paintComponent(Graphics g) {
        g.setColor(transparentColor);
        g.fillRect(0, 0,getWidth(),getHeight());
       tetrisNextElementLabel.setBounds(0, 0,getWidth(),getHeight() / 4);
        double radius = (double)getHeight() / 6.0D;
        START_PAINTING_X =getWidth() / 2;
        START_PAINTING_Y =getHeight() / 2;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (nextTetromino == 3) {
            paintO(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 0) {
            paintIHorizontal(g2d, START_PAINTING_X, START_PAINTING_Y + (byte) ((int) (radius / 2.0D)), radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 1) {
            paintJ(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 2) {
            paintL(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 4) {
            paintS(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 5) {
            paintT(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        } else if (nextTetromino == 6) {
            paintZ(g2d, START_PAINTING_X, START_PAINTING_Y, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        if (fps != 0.0D) {
           paintFPS(g2d, "FPS: " + (new DecimalFormat("#0.00")).format(fps), new Rectangle(0, 3 *getHeight() / 4,getWidth(),getHeight() / 4));
        }

    }

    private void paintFPS(Graphics g, String text, Rectangle rect) {
        Font font = Main.FONT.deriveFont((float) rect.height / 6.0F);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        g.setColor(new Color(255, 255, 255, 50));
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void paintO(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius), (byte) (startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius), startY, Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (byte) (startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, startY, Painting.O_COLOR, radius, type);
    }

    public static void paintI(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - 2 * radius), Color.CYAN, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Color.CYAN, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Color.CYAN, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY + radius), Color.CYAN, radius, type);
    }

    public static void paintIHorizontal(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - 2 * radius), (byte) (startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius), (byte) (startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (byte) (startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius), (byte) (startY - radius), Painting.I_COLOR, radius, type);
    }

    public static void paintJ(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), (byte) (startY - radius), Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), startY, Painting.J_COLOR, radius, type);
    }

    public static void paintL(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), (byte) (startY - radius), Painting.L_COLOR, radius, type);
    }

    public static void paintS(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), (byte) (startY - radius), Painting.S_COLOR, radius, type);
    }

    public static void paintT(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), startY, Painting.T_COLOR, radius, type);
    }

    public static void paintZ(Graphics2D graphics2D, byte startX, byte startY, byte radius, byte type) {
        Painting.paintSquare(graphics2D, (byte) (startX - radius - radius / 2), (byte) (startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (byte) (startX + radius / 2), startY, Painting.Z_COLOR, radius, type);
    }

    public static void paintO(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius), (int) ((double) startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius), startY, Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (int) ((double) startY - radius), Painting.O_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, startY, Painting.O_COLOR, radius, type);
    }

    public static void paintIHorizontal(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - 2.0D * radius), (int) ((double) startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius), (int) ((double) startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, startX, (int) ((double) startY - radius), Painting.I_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius), (int) ((double) startY - radius), Painting.I_COLOR, radius, type);
    }

    public static void paintJ(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), (int) ((double) startY - radius), Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), startY, Painting.J_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), startY, Painting.J_COLOR, radius, type);
    }

    public static void paintL(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), startY, Painting.L_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), (int) ((double) startY - radius), Painting.L_COLOR, radius, type);
    }

    public static void paintS(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), startY, Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), (int) ((double) startY - radius), Painting.S_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), (int) ((double) startY - radius), Painting.S_COLOR, radius, type);
    }

    public static void paintT(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), startY, Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), (int) ((double) startY - radius), Painting.T_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), startY, Painting.T_COLOR, radius, type);
    }

    public static void paintZ(Graphics2D graphics2D, int startX, int startY, double radius, byte type) {
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius - radius / 2.0D), (int) ((double) startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), (int) ((double) startY - radius), Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX - radius / 2.0D), startY, Painting.Z_COLOR, radius, type);
        Painting.paintSquare(graphics2D, (int) ((double) startX + radius / 2.0D), startY, Painting.Z_COLOR, radius, type);
    }

    public Dimension getPreferredSize() {
       d = super.getPreferredSize();
       c =getParent();
        if (c != null) {
           d =c.getSize();
           w =d.getWidth();
           h =d.getHeight();
           s = Math.min(w,h);
            return new Dimension((int) (Math.round(s * 0.25D / 6.0D) + 1L) * 6, (int) (Math.round(s * 0.25D / 6.0D) + 1L) * 6);
        } else {
            return new Dimension(10, 20);
        }
    }
}
