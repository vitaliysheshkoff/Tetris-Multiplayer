package game.panels.tetris.playfield.controller;

import game.helperclasses.ByteCoordinates;
import game.helperclasses.Tetromino;
import game.start.Main;

public class Moving {

    public static final byte LEFT = 1, RIGHT = 2;

    public static void pressRightKey(Tetromino currentTetromino, byte[][] fieldMatrix) {

        if (abilityToMove(fieldMatrix, currentTetromino, RIGHT)) {
            currentTetromino.stepX += 1;
            System.out.println("RIGHT");
            Main.audioPlayer.playMove();

            currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);

            Rotation.doRotation(currentTetromino);
        }
    }

    public static void pressLeftKey(Tetromino currentTetromino, byte[][] fieldMatrix) {
        if (abilityToMove(fieldMatrix, currentTetromino, LEFT)) {
            currentTetromino.stepX -= 1;
            System.out.println("LEFT");
            Main.audioPlayer.playMove();

            currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);

            Rotation.doRotation(currentTetromino);
        }
    }

    public static byte pressDownKey(Tetromino currentTetromino) {
        Main.audioPlayer.playMove();

        System.out.println("DOWN");
        move(currentTetromino, (byte) 1);

        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);

        Rotation.doRotation(currentTetromino);

        return 1;

    }

    public static byte pressHardDropKey(byte[][] fieldMatrix, Tetromino currentTetromino) {

        ByteCoordinates[] coordinates = new ByteCoordinates[4];
        for (int i = 0; i < 4; i++) {
            coordinates[i] = new ByteCoordinates(currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y);
        }

        byte counter = 0;
        while (!isTetrominoConnected(coordinates, fieldMatrix)) {
            for (int i = 0; i < 4; i++)
                coordinates[i].y += 1;
            counter++;
        }

        counter--;

        System.out.println("SPACE_DOWN");

        currentTetromino.stepY += counter;

        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);

        Rotation.doRotation(currentTetromino);

        return counter;
    }

    public static void move(Tetromino currentTetromino, byte radiusOfSquare) {
        currentTetromino.stepY += radiusOfSquare;
        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);
        Rotation.doRotation(currentTetromino);
    }


    private static boolean abilityToMove(byte[][] fieldMatrix, Tetromino currentTetromino, byte typeOfMovement) {
        if (typeOfMovement == LEFT) {
            for (int i = 0; i < 4; i++) {
                if (fieldMatrix[currentTetromino.coordinates[i].y + 1][currentTetromino.coordinates[i].x] == 1) {
                    System.out.println("block left movement!");
                    return false;
                }
            }
        }
        if (typeOfMovement == RIGHT) {
            for (int i = 0; i < 4; i++) {
                if (fieldMatrix[currentTetromino.coordinates[i].y + 1][currentTetromino.coordinates[i].x + 2] == 1) {
                    System.out.println("block right movement!");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isTetrominoConnected(ByteCoordinates[] coordinates, byte[][] fieldMatrix) {
        for (int i = 0; i < 4; i++) {
            if (coordinates[i].y + 1 > 21)
                return true;
            if (coordinates[i].x < 0 || coordinates[i].x > 11) // for I Tetromino
                return true;
            else if (fieldMatrix[coordinates[i].y + 1][coordinates[i].x + 1] == 1) {
                return true;
            }
        }
        return false;
    }

}
