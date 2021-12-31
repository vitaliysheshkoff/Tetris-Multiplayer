package game.panels.tetris.infopanels;

import game.helperclasses.coordinates.IntCoordinates;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisStatisticsPanel extends JPanel {

    public JLabel tetrisStatisticsLabel;
    public static IntCoordinates startPrintingByteCoordinates;
    public int amount_I = 0, amount_J = 0, amount_L = 0, amount_O = 0, amount_S = 0, amount_T = 0, amount_Z = 0;
    static Color transparentColor = new Color(0, 0, 0, 100);

    public TetrisStatisticsPanel() {

        setOpaque(false);
        tetrisStatisticsLabel = new JLabel("Statistics", SwingConstants.CENTER);
        tetrisStatisticsLabel.setFont(Main.FONT);
        setForeground(Color.WHITE);
        tetrisStatisticsLabel.setBounds(10, 10, 258, 20);
        tetrisStatisticsLabel.setForeground(Color.white);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        setForeground(transparentColor);
        add(tetrisStatisticsLabel);
    }

    public void resetTetrominoesAmount() {
        amount_I = 0;
        amount_J = 0;
        amount_L = 0;
        amount_O = 0;
        amount_S = 0;
        amount_T = 0;
        amount_Z = 0;
    }

    public void updateTetrominoesAmount(byte tetrominoType) {
        if (/*Main.tetrisPanel.tetrisPlayFieldPanel.currentTetromino.*/tetrominoType == TetrisNextTetrominoPanel.O)
            /* Main.tetrisPanel.tetrisStatisticsPanel.*/ amount_O++;
        else if (tetrominoType == TetrisNextTetrominoPanel.I)
            amount_I++;
        else if (tetrominoType == TetrisNextTetrominoPanel.J)
            amount_J++;
        else if (tetrominoType == TetrisNextTetrominoPanel.L)
            amount_L++;
        else if (tetrominoType == TetrisNextTetrominoPanel.S)
            amount_S++;
        else if (tetrominoType == TetrisNextTetrominoPanel.T)
            amount_T++;
        else if (tetrominoType == TetrisNextTetrominoPanel.Z)
            amount_Z++;
        repaint();
    }

    public void paintComponent(Graphics g) {

        g.setColor(transparentColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        tetrisStatisticsLabel.setBounds(0, 0, getWidth(), getHeight() / 15);
        double radius = getHeight() / 20.;
        startPrintingByteCoordinates = new IntCoordinates(getWidth() / 3, getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        paintTetrominoes(g2d, radius);
        printTetrominoesAmount(g2d, radius);

    }

    private void paintTetrominoes(Graphics2D g2d, double radius) {
        TetrisNextTetrominoPanel.paintT(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y / 8, radius);
        TetrisNextTetrominoPanel.paintJ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 2 / 8 + (byte) radius / 4/*+ 9*/, radius);
        TetrisNextTetrominoPanel.paintZ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 3 / 8 + (byte) radius / 4 * 2, radius);
        TetrisNextTetrominoPanel.paintO(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 4 / 8 + (byte) radius / 4 * 3, radius);
        TetrisNextTetrominoPanel.paintS(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 5 / 8 + (byte) radius / 4 * 4, radius);
        TetrisNextTetrominoPanel.paintL(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 6 / 8 + (byte) radius / 4 * 5, radius);
        TetrisNextTetrominoPanel.paintIHorizontal(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 7 / 8 + (byte) radius / 4 * 6, radius);
    }

    private void printTetrominoesAmount(Graphics2D g2d, double radius) {

        g2d.setFont(this.tetrisStatisticsLabel.getFont());
        g2d.setColor(Color.WHITE);

        g2d.drawString(String.valueOf(amount_T), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y / 8);
        g2d.drawString(String.valueOf(amount_J), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 2 / 8 + (byte) radius / 4);
        g2d.drawString(String.valueOf(amount_Z), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 3 / 8 + (byte) radius / 4 * 2);
        g2d.drawString(String.valueOf(amount_O), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 4 / 8 + (byte) radius / 4 * 3);
        g2d.drawString(String.valueOf(amount_S), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 5 / 8 + (byte) radius / 4 * 4);
        g2d.drawString(String.valueOf(amount_L), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 6 / 8 + (byte) radius / 4 * 5);
        g2d.drawString(String.valueOf(amount_I), startPrintingByteCoordinates.x * 2 + (byte) radius / 2, startPrintingByteCoordinates.y * 7 / 8 + (byte) radius / 4 * 5);
    }

    int w;
    int h;
    int s;

    Dimension d;
    Container c;

    @Override
    public Dimension getPreferredSize() {
        d = super.getPreferredSize();
        c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 20);
        }

        w = (int) d.getWidth();
        h = (int) d.getHeight();
        s = (Math.min(w, h));

        return new Dimension(s / 4, (int) (s / 1.6));
    }
}