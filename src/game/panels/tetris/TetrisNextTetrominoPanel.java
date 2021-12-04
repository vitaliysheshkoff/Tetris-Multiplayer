package game.panels.tetris;

import game.panels.tetris.playfield.controller.Painting;
import game.start.Main;
import javax.swing.*;
import java.awt.*;

import static game.panels.tetris.playfield.controller.Painting.*;
import static game.start.Main.RADIUS_OF_SQUARE;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisNextTetrominoPanel extends JPanel {

    public JLabel tetrisNextElementLabel;
    public static int START_PAINTING_X;
    public static int START_PAINTING_Y;
    public byte nextTetromino = -1;

    public static final byte I = 0, J = 1, L = 2, O = 3, S = 4, T = 5, Z = 6;
    static Color transparentColor = new Color(0,0,0,100);

    public TetrisNextTetrominoPanel() {
        setOpaque(false);
        tetrisNextElementLabel = new JLabel("Next", SwingConstants.CENTER);
        tetrisNextElementLabel.setFont(Main.FONT);
        setForeground(Color.WHITE);
        tetrisNextElementLabel.setBounds(0, 10, 190, 20);
        tetrisNextElementLabel.setForeground(Color.white);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        add(tetrisNextElementLabel);
        setForeground(transparentColor);
    }

    public void paintComponent(Graphics g) {

        g.setColor(transparentColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        double radius;

        tetrisNextElementLabel.setBounds(0,0,getWidth(), getHeight()/4);
        radius = getHeight() / 6.;

        START_PAINTING_X = getWidth() / 2;
        START_PAINTING_Y = getHeight() / 2;

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        if (nextTetromino == O)
            paintO(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
        else if (nextTetromino == I)
            paintIHorizontal(g2d, START_PAINTING_X, START_PAINTING_Y + (byte)(radius/2), radius);
        else if (nextTetromino == J)
            paintJ(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
        else if (nextTetromino == L)
            paintL(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
        else if (nextTetromino == S)
            paintS(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
        else if (nextTetromino == T)
            paintT(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
        else if (nextTetromino == Z)
            paintZ(g2d, START_PAINTING_X, START_PAINTING_Y, radius);
    }

    public static void paintO(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius), (byte) (startY - radius), O_COLOR, radius);
        paintSquare(graphics2D,  (byte) (startX - radius), startY, O_COLOR, radius);
        paintSquare(graphics2D, startX,  (byte) (startY - radius), O_COLOR, radius);
        paintSquare(graphics2D, startX, startY, O_COLOR, radius);
    }

    public static void paintI(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - 2 * radius), Color.CYAN, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Color.CYAN, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Color.CYAN, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY + radius), Color.CYAN, radius);

    }

    public static void paintIHorizontal(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - 2 * radius), (byte) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius), (byte) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D, startX, (byte) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius), (byte) (startY - radius), I_COLOR, radius);

    }

    public static void paintJ(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), (byte) (startY - radius), J_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, J_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, J_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), startY, J_COLOR, radius);

    }

    public static void paintL(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), (byte) (startY - radius), L_COLOR, radius);

    }

    public static void paintS(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, S_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, S_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), S_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), (byte) (startY - radius), S_COLOR, radius);

    }

    public static void paintT(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), startY, T_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, T_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), T_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), startY, T_COLOR, radius);

    }

    public static void paintZ(Graphics2D graphics2D, byte startX, byte startY, byte radius) {

        paintSquare(graphics2D, (byte) (startX - radius - radius / 2), (byte) (startY - radius), Z_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), (byte) (startY - radius), Z_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX - radius / 2), startY, Z_COLOR, radius);
        paintSquare(graphics2D, (byte) (startX + radius / 2), startY, Z_COLOR, radius);

    }





    public static void paintO(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - radius), (int)(startY - radius), O_COLOR, radius);
        paintSquare(graphics2D,  (int) (startX - radius), startY, O_COLOR, radius);
        paintSquare(graphics2D, (int) (startX),  (int) (startY - radius), O_COLOR, radius);
        paintSquare(graphics2D, startX, startY, O_COLOR, radius);
    }


    public static void paintIHorizontal(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - 2 * radius), (int) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius), (int) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D, startX, (int) (startY - radius), I_COLOR, radius);
        paintSquare(graphics2D,(int) ( startX + radius),(int) (startY - radius), I_COLOR, radius);

    }

    public static void paintJ(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - radius - radius / 2),(int) (startY - radius), J_COLOR, radius);
        paintSquare(graphics2D,(int) ( startX - radius - radius / 2), startY, J_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), startY, J_COLOR, radius);
        paintSquare(graphics2D, (int) (startX + radius / 2), startY, J_COLOR, radius);

    }

    public static void paintL(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - radius - radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D,(int) ( startX - radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D, (int) (startX + radius / 2), startY, L_COLOR, radius);
        paintSquare(graphics2D, (int) (startX + radius / 2),(int) (startY - radius), L_COLOR, radius);

    }

    public static void paintS(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - radius - radius / 2), startY, S_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), startY, S_COLOR, radius);
        paintSquare(graphics2D,(int) ( startX - radius / 2), (int) (startY - radius), S_COLOR, radius);
        paintSquare(graphics2D, (int) (startX + radius / 2), (int) (startY - radius), S_COLOR, radius);

    }

    public static void paintT(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int) (startX - radius - radius / 2), startY, T_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), startY, T_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), (int) (startY - radius), T_COLOR, radius);
        paintSquare(graphics2D, (int) (startX + radius / 2), startY, T_COLOR, radius);

    }

    public static void paintZ(Graphics2D graphics2D, int startX, int startY, double radius) {

        paintSquare(graphics2D, (int)(startX - radius - radius / 2), (int) (startY - radius), Z_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), (int)(startY - radius), Z_COLOR, radius);
        paintSquare(graphics2D, (int) (startX - radius / 2), startY, Z_COLOR, radius);
        paintSquare(graphics2D, (int)(startX + radius / 2), startY, Z_COLOR, radius);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 20);
        }

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);

       // System.out.println("prefered size" + s + " " + s);
        return new Dimension((int) s / 4, (int) (s / 4));
    }

}
