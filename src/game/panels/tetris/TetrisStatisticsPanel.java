package game.panels.tetris;

import game.helperclasses.ByteCoordinates;
import game.helperclasses.IntCoordinates;
import game.start.Main;

import javax.swing.*;
import java.awt.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisStatisticsPanel extends JPanel {

    public JLabel tetrisStatisticsLabel;
    public static IntCoordinates startPrintingByteCoordinates;
    public int amount_I = 0, amount_J = 0, amount_L = 0, amount_O = 0, amount_S = 0, amount_T = 0, amount_Z = 0;

    public TetrisStatisticsPanel() {

        setBackground(Color.BLACK);
        tetrisStatisticsLabel = new JLabel("Statistics", SwingConstants.CENTER);
        tetrisStatisticsLabel.setFont(Main.FONT);
        tetrisStatisticsLabel.setForeground(Color.WHITE);
        tetrisStatisticsLabel.setBounds(10, 10, 258, 20);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
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
           /* Main.tetrisPanel.tetrisStatisticsPanel.*/amount_O++;
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
        startPrintingByteCoordinates = new IntCoordinates(getWidth() / 3 , getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        paintTetrominoes(g2d);
        printTetrominoesAmount(g2d);

    }

    private void paintTetrominoes(Graphics2D g2d) {
        TetrisNextTetrominoPanel.paintT(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y / 8, 30);
        TetrisNextTetrominoPanel.paintJ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 2 / 8 + 9, 30);
        TetrisNextTetrominoPanel.paintZ(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 3 / 8 + 18, 30);
        TetrisNextTetrominoPanel.paintO(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 4 / 8 + 27, 30);
        TetrisNextTetrominoPanel.paintS(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 5 / 8 + 36, 30);
        TetrisNextTetrominoPanel.paintL(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 6 / 8 + 45, 30);
        TetrisNextTetrominoPanel.paintIHorizontal(g2d, startPrintingByteCoordinates.x, startPrintingByteCoordinates.y * 7 / 8 + 54, 30);
    }

    private void printTetrominoesAmount(Graphics2D g2d){

        g2d.setFont(Main.FONT);
        g2d.setColor(Color.WHITE);

        g2d.drawString(String.valueOf(amount_T), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y / 8);
        g2d.drawString(String.valueOf(amount_J), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 2 / 8 + 9);
        g2d.drawString(String.valueOf(amount_Z), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 3 / 8 + 18);
        g2d.drawString(String.valueOf(amount_O), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 4 / 8 + 27);
        g2d.drawString(String.valueOf(amount_S), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 5 / 8 + 36);
        g2d.drawString(String.valueOf(amount_L), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 6 / 8 + 45);
        g2d.drawString(String.valueOf(amount_I), startPrintingByteCoordinates.x * 2 + 25, startPrintingByteCoordinates.y * 7 / 8 + 45);
    }

}
