package game.panels.tetris.playfield.controller;

import game.helperclasses.ByteCoordinates;
import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;
import game.panels.tetris.TetrisNextTetrominoPanel;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

public class Painting {

    public static final Color Z_COLOR = new Color(255, 50, 19);
    public static final Color S_COLOR = new Color(114, 203, 59);
    public static final Color L_COLOR = new Color(255, 151, 28);
    public static final Color O_COLOR = new Color(255, 255, 0);
    public static final Color J_COLOR = new Color(3, 65, 174);
    public static final Color T_COLOR = new Color(139, 0, 139);
    public static final Color I_COLOR = new Color(0, 206, 209);

    public static Color getColor(byte color) {
        if (color == TetrisNextTetrominoPanel.I)
            return I_COLOR;
        else if (color == TetrisNextTetrominoPanel.J)
            return J_COLOR;
        else if (color == TetrisNextTetrominoPanel.L)
            return L_COLOR;
        else if (color == TetrisNextTetrominoPanel.O)
            return O_COLOR;
        else if (color == TetrisNextTetrominoPanel.S)
            return S_COLOR;
        else if (color == TetrisNextTetrominoPanel.T)
            return T_COLOR;
        else if (color == TetrisNextTetrominoPanel.Z)
            return Z_COLOR;

        else return Color.DARK_GRAY;
    }

    public static void paintCurrentTetromino(Tetromino currentTetromino, Graphics2D g2d, double radius) {
        for (int i = 0; i < 4; i++)
            paintSquare(g2d, currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y, currentTetromino.tetrominoType, radius);
    }

    /*public static void paintCurrentTetrominoForRepainting(Tetromino currentTetromino, double step, Graphics2D g2d, double radius) {
        for (int i = 0; i < 4; i++)
            paintSquareForRepainting(g2d, currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y + step,getColor(currentTetromino.tetrominoType), radius);
    }*/

    public static void paintCurrentTetrominoShadow(byte[][] fieldMatrix, Tetromino currentTetromino, Graphics2D g2d, double radius) {
        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        for (int i = 0; i < 4; i++)
            coordinates[i] = new ByteCoordinates(currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y);

        while (!Moving.isTetrominoConnected(coordinates, fieldMatrix)) {
            for (int i = 0; i < 4; i++)
                coordinates[i].y += 1;
        }

        for (int i = 0; i < 4; i++)
            coordinates[i].y -= 1;

        Color tetrominoColor = getColor(currentTetromino.tetrominoType);

        Color shadowColor = new Color(tetrominoColor.getRed(),
                tetrominoColor.getGreen(),
                tetrominoColor.getBlue(), 50);

        for (int i = 0; i < 4; i++)
            paintSquare(g2d, coordinates[i].x,
                    coordinates[i].y, shadowColor, radius);
    }

    public static void showDisappearClearLinesAnimation(Graphics2D g2d, byte helperForDeleting, ArrayList<SquareOfTetromino> elementsStayOnField, ArrayList<Integer> indexesOfDeletingLines, double radius) {
        System.out.println("show disappear-clear-animation");
        ArrayList<SquareOfTetromino> copyOfElementsStayOnField = new ArrayList<>();
        for (SquareOfTetromino oneSquare : elementsStayOnField) {
            copyOfElementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(oneSquare.coordinates.x, oneSquare.coordinates.y), oneSquare.color));
        }
        if (helperForDeleting == 1) {
            for (int deletingIndex : indexesOfDeletingLines)
                copyOfElementsStayOnField.removeIf((el -> el.coordinates.y == deletingIndex - 1 && (el.coordinates.x == 4 || el.coordinates.x == 5)));
        } else if (helperForDeleting == 2) {
            for (int deletingIndex : indexesOfDeletingLines)
                copyOfElementsStayOnField.removeIf((el -> el.coordinates.y == deletingIndex - 1 && (el.coordinates.x == 4 || el.coordinates.x == 5 || el.coordinates.x == 3 || el.coordinates.x == 6)));
        } else if (helperForDeleting == 3) {
            for (int deletingIndex : indexesOfDeletingLines)
                copyOfElementsStayOnField.removeIf((el -> el.coordinates.y == deletingIndex - 1
                        && (el.coordinates.x == 4 || el.coordinates.x == 5 || el.coordinates.x == 3 || el.coordinates.x == 6
                        || el.coordinates.x == 2 || el.coordinates.x == 7)));
        } else if (helperForDeleting == 4) {
            for (int deletingIndex : indexesOfDeletingLines)
                copyOfElementsStayOnField.removeIf((el -> el.coordinates.y == deletingIndex - 1
                        && (el.coordinates.x == 4 || el.coordinates.x == 5 || el.coordinates.x == 3 || el.coordinates.x == 6
                        || el.coordinates.x == 2 || el.coordinates.x == 7 || el.coordinates.x == 1 || el.coordinates.x == 8)));
        } else if (helperForDeleting == 5) {
            for (int deletingIndex : indexesOfDeletingLines)
                copyOfElementsStayOnField.removeIf((el -> el.coordinates.y == deletingIndex - 1
                        && (el.coordinates.x == 4 || el.coordinates.x == 5 || el.coordinates.x == 3 || el.coordinates.x == 6
                        || el.coordinates.x == 2 || el.coordinates.x == 7 || el.coordinates.x == 1 || el.coordinates.x == 8
                        || el.coordinates.x == 0 || el.coordinates.x == 9)));
        }

        Color elementColor;
        for (SquareOfTetromino el : copyOfElementsStayOnField) {
            elementColor = getColor(el.color);
            paintSquare(g2d, el.coordinates.x, el.coordinates.y, elementColor, radius);
        }
    }

    public static void showRandomColorClearLinesAnimation(Graphics2D g2d, ArrayList<SquareOfTetromino> elementsStayOnField, ArrayList<Integer> indexesOfDeletingLines, double radius) {
        System.out.println("show random-color-clear-animation");
        Random randomGenerator = new Random();
        paintLyingElements(g2d, elementsStayOnField, radius);
        for (SquareOfTetromino el : elementsStayOnField) {
            for (int deletingIndex : indexesOfDeletingLines) {
                if (el.coordinates.y == deletingIndex - 1) {
                    int red = randomGenerator.nextInt(256);
                    int green = randomGenerator.nextInt(256);
                    int blue = randomGenerator.nextInt(256);
                    paintSquare(g2d, el.coordinates.x, el.coordinates.y, new Color(red, green, blue), radius);
                }
            }
        }
    }

    public static void paintLyingElements(Graphics2D g2d, ArrayList<SquareOfTetromino> elementsStayOnField, double radius) {
        if (elementsStayOnField == null)
            return;

        // dont modify this
        for(int i = 0; i < elementsStayOnField.size(); i++){
            paintSquare(g2d, elementsStayOnField.get(i).coordinates.x,
                    elementsStayOnField.get(i).coordinates.y,
                    elementsStayOnField.get(i).color, radius);
        }

    }

    public static void drawLines(Graphics2D g2d, int width, int height, double radius) {
        g2d.setColor(new Color(255, 255, 255, 50));

        //vertical lines
        for (int i = 1; i < 10; i++) {
            Shape l = new Line2D.Double(i * radius, 0, i * radius, height);
            g2d.draw(l);
        }

        //horizontal lines
        for (int i = 1; i < 20; i++) {
            Shape l = new Line2D.Double(0, radius * i, width, radius * i);
            g2d.draw(l);
        }
    }

    public static void paintSquare(Graphics2D graphics2D, byte x, byte y, byte color, double radius) {
        paintSquare(graphics2D, x, y, getColor(color), radius);
    }

    /*public static void paintSquareForRepainting(Graphics2D graphics2D, double x, double y, Color color, double radius) {
        RoundRectangle2D r_rect1 = new RoundRectangle2D.Double(x * radius, y * radius, radius, radius, radius / 3., radius / 3.);
        RoundRectangle2D r_rect2 = new RoundRectangle2D.Double(x * radius + radius / 12., y * radius + radius / 12., radius - radius / 6., radius - radius / 6., radius / 3., radius / 3.);
        GradientPaint gradientPaint = new GradientPaint((float) (x * radius + radius / 8.), (float) (y * radius + radius / 8.),
                color.darker().darker(), (float) (x * radius + radius / 8. + radius - radius / 4.), (float) (y * radius + radius / 8. + +radius - radius / 4.), color.darker());
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(r_rect1);

        GradientPaint gradientPaint2 = new GradientPaint((float) (x * radius + radius / 12.), (float) (y * radius + radius / 12.),
                color.brighter(), (float) (x * radius + radius / 12. + radius - radius / 6.), (float) (y * radius + radius / 12. + +radius - radius / 6.), color.darker());
        graphics2D.setPaint(gradientPaint2);
        graphics2D.fill(r_rect2);
    }*/

    public static void paintSquare(Graphics2D graphics2D, int x, int y, Color color, double radius) {
        graphics2D.setColor(color.darker());
        RoundRectangle2D r_rect1 = new RoundRectangle2D.Double(x, y, radius, radius, radius / 3., radius / 3.);
        RoundRectangle2D r_rect2 = new RoundRectangle2D.Double(x + radius / 12., y + radius / 12., radius - radius / 6., radius - radius / 6., radius / 3., radius / 3.);
        GradientPaint gradientPaint = new GradientPaint((float) (x + radius / 8.), (float) (y + radius / 8.),
                color.darker().darker(), (float) (x + radius / 8. + radius - radius / 4.), (float) (y + radius / 8. + +radius - radius / 4.), color.darker().darker());
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(r_rect1);

        GradientPaint gradientPaint2 = new GradientPaint((float) (x + radius / 12.), (float) (y + radius / 12.),
                color.brighter(), (float) (x + radius / 12. + radius - radius / 6.), (float) (y + radius / 12. + +radius - radius / 6.), color.darker());
        graphics2D.setPaint(gradientPaint2);
        graphics2D.fill(r_rect2);
    }

    public static void paintSquare(Graphics2D graphics2D, byte x, byte y, Color color, double radius) {
        RoundRectangle2D r_rect1 = new RoundRectangle2D.Double(x * radius, y * radius, radius, radius, radius / 3., radius / 3.);
        RoundRectangle2D r_rect2 = new RoundRectangle2D.Double(x * radius + radius / 12., y * radius + radius / 12., radius - radius / 6., radius - radius / 6., radius / 3., radius / 3.);
        GradientPaint gradientPaint = new GradientPaint((float) (x * radius + radius / 8.), (float) (y * radius + radius / 8.),
                color.darker().darker(), (float) (x * radius + radius / 8. + radius - radius / 4.), (float) (y * radius + radius / 8. + +radius - radius / 4.), color.darker());
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(r_rect1);

        GradientPaint gradientPaint2 = new GradientPaint((float) (x * radius + radius / 12.), (float) (y * radius + radius / 12.),
                color.brighter(), (float) (x * radius + radius / 12. + radius - radius / 6.), (float) (y * radius + radius / 12. + +radius - radius / 6.), color.darker());
        graphics2D.setPaint(gradientPaint2);
        graphics2D.fill(r_rect2);
    }
}
