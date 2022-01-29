package game.panels.tetris.infopanels;

import game.helperclasses.coordinates.IntCoordinates;
import game.start.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;

public class TetrisStatisticsPanel extends JPanel {
    public JLabel tetrisStatisticsLabel;
    public static IntCoordinates startPrintingByteCoordinates;
    public int amount_I = 0;
    public int amount_J = 0;
    public int amount_L = 0;
    public int amount_O = 0;
    public int amount_S = 0;
    public int amount_T = 0;
    public int amount_Z = 0;
    static Color transparentColor = new Color(0, 0, 0, 100);
    double w;
    double h;
    double s;
    Dimension d;
    Container c;

    public TetrisStatisticsPanel() {
        setOpaque(false);
        tetrisStatisticsLabel = new JLabel("Statistics", SwingConstants.CENTER);
        tetrisStatisticsLabel.setFont(Main.FONT);
        setForeground(Color.WHITE);
        tetrisStatisticsLabel.setBounds(10, 10, 258, 20);
        tetrisStatisticsLabel.setForeground(Color.white);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
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
        if (tetrominoType == 3) {
            ++amount_O;
        } else if (tetrominoType == 0) {
            ++amount_I;
        } else if (tetrominoType == 1) {
            ++amount_J;
        } else if (tetrominoType == 2) {
            ++amount_L;
        } else if (tetrominoType == 4) {
            ++amount_S;
        } else if (tetrominoType == 5) {
            ++amount_T;
        } else if (tetrominoType == 6) {
            ++amount_Z;
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(transparentColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        tetrisStatisticsLabel.setBounds(0, 0, getWidth(), getHeight() / 15);
        double radius = (double)getHeight() / 20.0D;
        startPrintingByteCoordinates = new IntCoordinates(getWidth() / 3, getHeight());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintTetrominoes(g2d, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        printTetrominoesAmount(g2d, radius);
    }

    private void paintTetrominoes(Graphics2D g2d, double radius, byte type) {
        TetrisNextTetrominoPanel.paintT(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y / 8, radius, type);
        TetrisNextTetrominoPanel.paintJ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 2 / 8 + (byte)((int)radius) / 4, radius, type);
        TetrisNextTetrominoPanel.paintZ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 3 / 8 + (byte)((int)radius) / 4 * 2, radius, type);
        TetrisNextTetrominoPanel.paintO(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 4 / 8 + (byte)((int)radius) / 4 * 3, radius, type);
        TetrisNextTetrominoPanel.paintS(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 5 / 8 + (byte)((int)radius) / 4 * 4, radius, type);
        TetrisNextTetrominoPanel.paintL(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 6 / 8 + (byte)((int)radius) / 4 * 5, radius, type);
        TetrisNextTetrominoPanel.paintIHorizontal(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 7 / 8 + (byte)((int)radius) / 4 * 6, radius, type);
    }

    private void printTetrominoesAmount(Graphics2D g2d, double radius) {
        g2d.setFont(tetrisStatisticsLabel.getFont());
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(amount_T), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y / 8);
        g2d.drawString(String.valueOf(amount_J), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 2 / 8 + (byte)((int)radius) / 4);
        g2d.drawString(String.valueOf(amount_Z), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 3 / 8 + (byte)((int)radius) / 4 * 2);
        g2d.drawString(String.valueOf(amount_O), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 4 / 8 + (byte)((int)radius) / 4 * 3);
        g2d.drawString(String.valueOf(amount_S), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 5 / 8 + (byte)((int)radius) / 4 * 4);
        g2d.drawString(String.valueOf(amount_L), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 6 / 8 + (byte)((int)radius) / 4 * 5);
        g2d.drawString(String.valueOf(amount_I), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 7 / 8 + (byte)((int)radius) / 4 * 5);
    }

    public Dimension getPreferredSize() {
        d = super.getPreferredSize();
        c = getParent();
        if (c != null) {
            d = c.getSize();
            w = (int)d.getWidth();
            h = (int)d.getHeight();
            s = Math.min(w, h);
            return new Dimension((int)(Math.round(s / 4.0D / 20.0D) + 1L) * 20, (int)(Math.round(s / 1.6D / 20.0D) + 1L) * 20);
        } else {
            return new Dimension(10, 20);
        }
    }
}
