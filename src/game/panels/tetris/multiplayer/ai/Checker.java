/*
package game.panels.tetris.multiplayer.ai;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Moving;
import game.panels.tetris.controller.Rotation;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Checker {

    private static final byte DEFAULT = 0, CW = 1, DCW = 2, CCW = 3, DCCW = 4;

    public static ArrayList<Pair<Byte, ArrayList<Byte>>> getAllSteps(byte[][] matrix, Tetromino currentTetromino) {

        for (byte[] bytes : matrix) {
            for (byte aByte : bytes) {
                System.out.print(aByte + " ");
            }
            System.out.println();
        }
        //key: rotation, value: steps
        ArrayList<Pair<Byte, ArrayList<Byte>>> allSteps = new ArrayList<>();

        ArrayList<Byte> steps;
        Tetromino tmpTetromino;

        byte[][] tmpMatrix = new byte[matrix.length][];
        byte rotationType;

        boolean finish = false;

        for (int i = 0; i < 4; i++) {

            if (i == 0) rotationType = DEFAULT;
            else if (i == 1) rotationType = CW;
            else if (i == 2) rotationType = DCW;
            else rotationType = CCW;

            // reset tmp matrix
            for (int s = 0; s < matrix.length; s++) {
                byte[] aMatrix = matrix[s];
                int aLength = aMatrix.length;
                tmpMatrix[s] = new byte[aLength];
                System.arraycopy(aMatrix, 0, tmpMatrix[s], 0, aLength);
            }

          //  tmpTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(tmpTetromino);

            while (!finish) {

                // reset tmp tetromino
                ByteCoordinates[] byteCoordinates;
                byteCoordinates = Arrays.copyOf(currentTetromino.coordinates, currentTetromino.coordinates.length);
                tmpTetromino = new Tetromino(byteCoordinates, currentTetromino.tetrominoType, rotationType,
                        currentTetromino.stepY, currentTetromino.stepX);

                steps = new ArrayList<>();

                if (Moving.abilityToMove(tmpMatrix, tmpTetromino, Moving.LEFT)) {
                    while (Moving.abilityToMove(tmpMatrix, tmpTetromino, Moving.LEFT)) {
                        Moving.moveLRD(tmpTetromino, (byte) 1, Moving.LEFT);
                        steps.add(Moving.LEFT);
                    }
                } else {
                    while (Moving.abilityToMove(tmpMatrix, tmpTetromino, Moving.RIGHT)) {
                        Moving.moveLRD(tmpTetromino, (byte) 1, Moving.RIGHT);
                        steps.add(Moving.RIGHT);
                    }
                }
                while (Moving.abilityToMove(tmpMatrix, tmpTetromino, Moving.DOWN)) {
                    Moving.moveLRD(tmpTetromino, (byte) 1, Moving.DOWN);
                    steps.add(Moving.DOWN);
                }

                if (steps.isEmpty()) {
                    if(i == 3)
                    finish = true;
                    break;
                }

                // find leftmost square
                int min = tmpTetromino.coordinates[0].x;
                for (int j = 1; j < 4; j++) {
                    if (min > tmpTetromino.coordinates[j].x)
                        min = tmpTetromino.coordinates[j].x;
                }

                // fill matrix by [column == leftmost square]
                for (int k = 0; k < tmpMatrix.length; k++)
                    tmpMatrix[k][min*/
/*tmpTetromino.stepX*//*
 + 1] = 1;

                allSteps.add(new Pair<>(rotationType, steps));

                for (byte[] bytes : tmpMatrix) {
                    for (byte aByte : bytes) {
                        System.out.print(aByte + " ");
                    }
                    System.out.println();
                }
            }
        }


        return allSteps;
    }

*/
/*
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]*//*

}
*/
