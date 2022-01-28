//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        this.setOpaque(false);
        this.tetrisStatisticsLabel = new JLabel("Statistics", 0);
        this.tetrisStatisticsLabel.setFont(Main.FONT);
        this.setForeground(Color.WHITE);
        this.tetrisStatisticsLabel.setBounds(10, 10, 258, 20);
        this.tetrisStatisticsLabel.setForeground(Color.white);
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        this.setForeground(transparentColor);
        this.add(this.tetrisStatisticsLabel);
    }

    public void resetTetrominoesAmount() {
        this.amount_I = 0;
        this.amount_J = 0;
        this.amount_L = 0;
        this.amount_O = 0;
        this.amount_S = 0;
        this.amount_T = 0;
        this.amount_Z = 0;
    }

    public void updateTetrominoesAmount(byte tetrominoType) {
        if (tetrominoType == 3) {
            ++this.amount_O;
        } else if (tetrominoType == 0) {
            ++this.amount_I;
        } else if (tetrominoType == 1) {
            ++this.amount_J;
        } else if (tetrominoType == 2) {
            ++this.amount_L;
        } else if (tetrominoType == 4) {
            ++this.amount_S;
        } else if (tetrominoType == 5) {
            ++this.amount_T;
        } else if (tetrominoType == 6) {
            ++this.amount_Z;
        }

        this.repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(transparentColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.tetrisStatisticsLabel.setBounds(0, 0, this.getWidth(), this.getHeight() / 15);
        double radius = (double)this.getHeight() / 20.0D;
        startPrintingByteCoordinates = new IntCoordinates(this.getWidth() / 3, this.getHeight());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.paintTetrominoes(g2d, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        this.printTetrominoesAmount(g2d, radius);
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
        g2d.setFont(this.tetrisStatisticsLabel.getFont());
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(this.amount_T), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y / 8);
        g2d.drawString(String.valueOf(this.amount_J), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 2 / 8 + (byte)((int)radius) / 4);
        g2d.drawString(String.valueOf(this.amount_Z), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 3 / 8 + (byte)((int)radius) / 4 * 2);
        g2d.drawString(String.valueOf(this.amount_O), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 4 / 8 + (byte)((int)radius) / 4 * 3);
        g2d.drawString(String.valueOf(this.amount_S), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 5 / 8 + (byte)((int)radius) / 4 * 4);
        g2d.drawString(String.valueOf(this.amount_L), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 6 / 8 + (byte)((int)radius) / 4 * 5);
        g2d.drawString(String.valueOf(this.amount_I), startPrintingByteCoordinates.x * 2 + (byte)((int)radius) / 2, startPrintingByteCoordinates.y * 7 / 8 + (byte)((int)radius) / 4 * 5);
    }

    public Dimension getPreferredSize() {
        this.d = super.getPreferredSize();
        this.c = this.getParent();
        if (this.c != null) {
            this.d = this.c.getSize();
            this.w = (double)((int)this.d.getWidth());
            this.h = (double)((int)this.d.getHeight());
            this.s = Math.min(this.w, this.h);
            return new Dimension((int)(Math.round(this.s / 4.0D / 20.0D) + 1L) * 20, (int)(Math.round(this.s / 1.6D / 20.0D) + 1L) * 20);
        } else {
            return new Dimension(10, 20);
        }
    }
}
