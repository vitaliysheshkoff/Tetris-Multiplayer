package game.panels.tetris.controller;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

public class Painting {

    public static final byte PLAIN = 0;
    public static final byte LITTLE = 1;
    public static final byte ROUND = 2;
    public static final byte ROUND_WITH_GRADIENT = 3;
    public static final byte LEGO = 4;

    public static final Color Z_COLOR = new Color(255, 50, 19);
    public static final Color S_COLOR = new Color(114, 203, 59);
    public static final Color L_COLOR = new Color(255, 151, 28);
    public static final Color O_COLOR = new Color(255, 255, 0);
    public static final Color J_COLOR = new Color(3, 65, 174);
    public static final Color T_COLOR = new Color(139, 0, 139);
    public static final Color I_COLOR = new Color(0, 206, 209);

    public Painting() {
    }

    public static Color getColor(byte color) {
        if (color == 0) {
            return new Color(0, 206, 209);
        } else if (color == 1) {
            return new Color(3, 65, 174);
        } else if (color == 2) {
            return new Color(255, 151, 28);
        } else if (color == 3) {
            return new Color(255, 255, 0);
        } else if (color == 4) {
            return new Color(114, 203, 59);
        } else if (color == 5) {
            return new Color(139, 0, 139);
        } else {
            return color == 6 ? new Color(255, 50, 19) : Color.DARK_GRAY;
        }
    }

    public static void paintCurrentTetromino(Tetromino currentTetromino, Graphics2D g2d, double radius, byte type) {
        for (int i = 0; i < 4; ++i) {
            paintSquare(g2d, currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y, currentTetromino.tetrominoType, radius, type);
        }

    }

    public static void paintCurrentTetrominoForRepainting(Tetromino currentTetromino, double stepX, double stepY, Graphics2D g2d, double radius, byte type) {
        for (int i = 0; i < 4; ++i) {
            paintSquareForRepainting(g2d, (double) currentTetromino.coordinates[i].x + stepX, (double) currentTetromino.coordinates[i].y + stepY, getColor(currentTetromino.tetrominoType), radius, type);
        }

    }

    public static void paintCurrentTetrominoShadow(byte[][] fieldMatrix, Tetromino currentTetromino, Graphics2D g2d, double radius, byte type) {
        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        int i;
        for (i = 0; i < 4; ++i) {
            coordinates[i] = new ByteCoordinates(currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y);
        }

        while (!Moving.isTetrominoConnected(coordinates, fieldMatrix)) {
            for (i = 0; i < 4; ++i) {
                ++coordinates[i].y;
            }
        }

        for (i = 0; i < 4; ++i) {
            --coordinates[i].y;
        }

        Color tetrominoColor = getColor(currentTetromino.tetrominoType);
        Color shadowColor = new Color(tetrominoColor.getRed(), tetrominoColor.getGreen(), tetrominoColor.getBlue(), 50);

        for (int j = 0; j < 4; ++j) {
            paintSquare(g2d, coordinates[j].x, coordinates[j].y, shadowColor, radius, type);
        }

    }

    public static void showDisappearClearLinesAnimation(Graphics2D g2d, byte helperForDeleting, ArrayList<SquareOfTetromino> elementsStayOnField, ArrayList<Integer> indexesOfDeletingLines, double radius, byte type) {
        System.out.println("show disappear-clear-animation");
        ArrayList<SquareOfTetromino> copyOfElementsStayOnField = new ArrayList<>();

        for (SquareOfTetromino oneSquare : elementsStayOnField) {
            copyOfElementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(oneSquare.coordinates.x, oneSquare.coordinates.y), oneSquare.color));
        }

        Iterator iterator;
        int deletingIndex;
        if (helperForDeleting == 1) {
            iterator = indexesOfDeletingLines.iterator();

            while (iterator.hasNext()) {
                deletingIndex = (Integer) iterator.next();
                int finalDeletingIndex = deletingIndex;
                copyOfElementsStayOnField.removeIf((elx) -> elx.coordinates.y == finalDeletingIndex - 1 && (elx.coordinates.x == 4 || elx.coordinates.x == 5));
            }
        } else if (helperForDeleting == 2) {
            iterator = indexesOfDeletingLines.iterator();

            while (iterator.hasNext()) {
                deletingIndex = (Integer) iterator.next();
                int finalDeletingIndex1 = deletingIndex;
                copyOfElementsStayOnField.removeIf((elx) -> elx.coordinates.y == finalDeletingIndex1 - 1 && (elx.coordinates.x == 4 || elx.coordinates.x == 5 || elx.coordinates.x == 3 || elx.coordinates.x == 6));
            }
        } else if (helperForDeleting == 3) {
            iterator = indexesOfDeletingLines.iterator();

            while (iterator.hasNext()) {
                deletingIndex = (Integer) iterator.next();
                int finalDeletingIndex2 = deletingIndex;
                copyOfElementsStayOnField.removeIf((elx) -> elx.coordinates.y == finalDeletingIndex2 - 1 && (elx.coordinates.x == 4 || elx.coordinates.x == 5 || elx.coordinates.x == 3 || elx.coordinates.x == 6 || elx.coordinates.x == 2 || elx.coordinates.x == 7));
            }
        } else if (helperForDeleting == 4) {
            iterator = indexesOfDeletingLines.iterator();

            while (iterator.hasNext()) {
                deletingIndex = (Integer) iterator.next();
                int finalDeletingIndex3 = deletingIndex;
                copyOfElementsStayOnField.removeIf((elx) -> elx.coordinates.y == finalDeletingIndex3 - 1 && (elx.coordinates.x == 4 || elx.coordinates.x == 5 || elx.coordinates.x == 3 || elx.coordinates.x == 6 || elx.coordinates.x == 2 || elx.coordinates.x == 7 || elx.coordinates.x == 1 || elx.coordinates.x == 8));
            }
        } else if (helperForDeleting == 5) {
            iterator = indexesOfDeletingLines.iterator();

            while (iterator.hasNext()) {
                deletingIndex = (Integer) iterator.next();
                int finalDeletingIndex4 = deletingIndex;
                copyOfElementsStayOnField.removeIf((elx) -> elx.coordinates.y == finalDeletingIndex4 - 1 && (elx.coordinates.x == 4 || elx.coordinates.x == 5 || elx.coordinates.x == 3 || elx.coordinates.x == 6 || elx.coordinates.x == 2 || elx.coordinates.x == 7 || elx.coordinates.x == 1 || elx.coordinates.x == 8 || elx.coordinates.x == 0 || elx.coordinates.x == 9));
            }
        }

        for (SquareOfTetromino el : copyOfElementsStayOnField) {
            Color elementColor = getColor(el.color);
            paintSquare(g2d, el.coordinates.x, el.coordinates.y, elementColor, radius, type);
        }

    }

    public static void showRandomColorClearLinesAnimation(Graphics2D g2d, ArrayList<SquareOfTetromino> elementsStayOnField, ArrayList<Integer> indexesOfDeletingLines, double radius, byte type) {
        System.out.println("show random-color-clear-animation");
        Random randomGenerator = new Random();
        paintLyingElements(g2d, elementsStayOnField, radius, type);

        for (SquareOfTetromino squareOfTetromino : elementsStayOnField) {
            for (Integer indexesOfDeletingLine : indexesOfDeletingLines) {
                if (squareOfTetromino.coordinates.y == indexesOfDeletingLine - 1) {
                    int red = randomGenerator.nextInt(256);
                    int green = randomGenerator.nextInt(256);
                    int blue = randomGenerator.nextInt(256);
                    paintSquare(g2d, squareOfTetromino.coordinates.x, squareOfTetromino.coordinates.y, new Color(red, green, blue), radius, type);
                }
            }
        }

    }

    public static void paintLyingElements(Graphics2D g2d, ArrayList<SquareOfTetromino> elementsStayOnField, double radius, byte type) {
        if (elementsStayOnField != null) {

            for(int i = 0; i < elementsStayOnField.size(); i++){
                paintSquare(g2d,elementsStayOnField.get(i).coordinates.x,
                        elementsStayOnField.get(i).coordinates.y,elementsStayOnField.get(i).color,radius,type);
            }
        }
    }

    public static void paintLyingElementsForGameOver(Graphics2D g2d, ArrayList<SquareOfTetromino> elementsStayOnField, double radius, byte type) {
        if (elementsStayOnField != null) {

            for (int i = 0; i < elementsStayOnField.size(); i++) {
                Color color = getColor(elementsStayOnField.get(i).color);
                paintSquare(g2d, elementsStayOnField.get(i).coordinates.x,
                        elementsStayOnField.get(i).coordinates.y, new Color(color.getRed(), color.getGreen(), color.getBlue(), 50), radius, type);
            }
        }
    }

    public static void drawLines(Graphics2D g2d, int width, int height, double radius) {
        g2d.setColor(new Color(255, 255, 255, 50));

        int i;
        Line2D.Double l;
        for (i = 1; i < 10; ++i) {
            l = new Line2D.Double((double) i * radius, 0.0D, (double) i * radius, height);
            g2d.draw(l);
        }

        for (i = 1; i < 20; ++i) {
            l = new Line2D.Double(0.0D, radius * (double) i, width, radius * (double) i);
            g2d.draw(l);
        }

    }

    public static void paintSquare(Graphics2D graphics2D, byte x, byte y, byte color, double radius, byte type) {
        paintSquare(graphics2D, x, y, getColor(color), radius, type);
    }

    public static void paintSquareForRepainting(Graphics2D graphics2D, double x, double y, Color color, double radius, byte type) {
        if (type == PLAIN) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double(x * radius, (double) Math.round(y * radius), radius, radius));
        } else if (type == LITTLE) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double(x * radius + radius / 12.0D, y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D));
        } else if (type == ROUND) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius, y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius + radius / 12.0D, y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        } else if (type == ROUND_WITH_GRADIENT) {
            graphics2D.setColor(color.darker());
            graphics2D.setPaint(new GradientPaint((float) (x * radius + radius / 8.0D), (float) (y * radius + radius / 8.0D), color.darker().darker(), (float) (x * radius + radius / 8.0D + radius - radius / 4.0D), (float) (y * radius + radius / 8.0D + radius - radius / 4.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius, y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.setPaint(new GradientPaint((float) (x * radius + radius / 12.0D), (float) (y * radius + radius / 12.0D), color.brighter(), (float) (x * radius + radius / 12.0D + radius - radius / 6.0D), (float) (y * radius + radius / 12.0D + radius - radius / 6.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius + radius / 12.0D, y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        } else if (type == LEGO) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius, y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x * radius + radius / 8.0D, y * radius + radius / 8.0D, radius - radius / 4.0D, radius - radius / 4.0D, radius, radius));
        }

    }

    public static void paintSquare(Graphics2D graphics2D, int x, int y, Color color, double radius, byte type) {
        if (type == PLAIN) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double(x, y, radius, radius));
        }

        if (type == LITTLE) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double((double) x + radius / 12.0D, (double) y + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D));
        }

        if (type == ROUND) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x, y, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x + radius / 12.0D, (double) y + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        }

        if (type == ROUND_WITH_GRADIENT) {
            graphics2D.setPaint(new GradientPaint((float) ((double) x + radius / 8.0D), (float) ((double) y + radius / 8.0D), color.darker().darker(), (float) ((double) x + radius / 8.0D + radius - radius / 4.0D), (float) ((double) y + radius / 8.0D + radius - radius / 4.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x, y, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setPaint(new GradientPaint((float) ((double) x + radius / 12.0D), (float) ((double) y + radius / 12.0D), color.brighter(), (float) ((double) x + radius / 12.0D + radius - radius / 6.0D), (float) ((double) y + radius / 12.0D + radius - radius / 6.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x + radius / 12.0D, (double) y + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        } else if (type == LEGO) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double(x, y, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x + radius / 8.0D, (double) y + radius / 8.0D, radius - radius / 4.0D, radius - radius / 4.0D, radius, radius));
        }

    }

    public static void paintSquare(Graphics2D graphics2D, byte x, byte y, Color color, double radius, byte type) {
        if (type == PLAIN) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double((double) x * radius, (double) y * radius, radius, radius));
        } else if (type == LITTLE) {
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.Rectangle2D.Double((double) x * radius + radius / 12.0D, (double) y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D));
        } else if (type == ROUND) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius, (double) y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius + radius / 12.0D, (double) y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        } else if (type == ROUND_WITH_GRADIENT) {
            graphics2D.setColor(color.darker());
            graphics2D.setPaint(new GradientPaint((float) ((double) x * radius + radius / 8.0D), (float) ((double) y * radius + radius / 8.0D), color.darker().darker(), (float) ((double) x * radius + radius / 8.0D + radius - radius / 4.0D), (float) ((double) y * radius + radius / 8.0D + radius - radius / 4.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius, (double) y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.setPaint(new GradientPaint((float) ((double) x * radius + radius / 12.0D), (float) ((double) y * radius + radius / 12.0D), color.brighter(), (float) ((double) x * radius + radius / 12.0D + radius - radius / 6.0D), (float) ((double) y * radius + radius / 12.0D + radius - radius / 6.0D), color.darker()));
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius + radius / 12.0D, (double) y * radius + radius / 12.0D, radius - radius / 6.0D, radius - radius / 6.0D, radius / 3.0D, radius / 3.0D));
        } else if (type == LEGO) {
            graphics2D.setColor(color.darker());
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius, (double) y * radius, radius, radius, radius / 3.0D, radius / 3.0D));
            graphics2D.setColor(color);
            graphics2D.setColor(color);
            graphics2D.fill(new java.awt.geom.RoundRectangle2D.Double((double) x * radius + radius / 8.0D, (double) y * radius + radius / 8.0D, radius - radius / 4.0D, radius - radius / 4.0D, radius, radius));
        }

    }
}
