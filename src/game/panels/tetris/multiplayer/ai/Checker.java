package game.panels.tetris.multiplayer.ai;

import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Moving;
import game.panels.tetris.controller.Rotation;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel;
import java.util.ArrayList;
import java.util.Arrays;

class Checker {

    public Checker() {

    }

    public static ArrayList<Info> getMoves(Tetromino currentTetromino, byte[][] fieldMatrix) {

        Tetromino tmp_tetromino;
        byte[][] tmp_matrix;

        ArrayList<Info> allInfo = new ArrayList<>();
        ArrayList<Byte> steps;

        for (int i = 0; i < 4; ++i) {

            // copy field matrix to tmp matrix
            tmp_matrix = new byte[fieldMatrix.length][];

            for (int j = 0; j < fieldMatrix.length; ++j) {
                tmp_matrix[j] = new byte[fieldMatrix[j].length];
                System.arraycopy(fieldMatrix[j], 0, tmp_matrix[j], 0, tmp_matrix[j].length);
            }

            // copy current tetromino to tmp tetromino
            tmp_tetromino = new Tetromino(Arrays.copyOf(currentTetromino.coordinates,
                    currentTetromino.coordinates.length), currentTetromino.tetrominoType,
                    TetrisPlayFieldPanel.DEFAULT, currentTetromino.stepY, currentTetromino.stepX);

            // set rotation
             if (i == 1){

                if(tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.O)
                    break;

                Rotation.pressCWKey(tmp_matrix,tmp_tetromino);
            }
            else if (i == 2) {
                if(tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.I ||
                        tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.S ||
                        tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.Z){
                    break;
                }

                Rotation.pressCWKey(tmp_matrix,tmp_tetromino);
                Rotation.pressCWKey(tmp_matrix,tmp_tetromino);
            }

            else if (i == 3) Rotation.pressCCWKey(tmp_matrix,tmp_tetromino);

            // while we can moving
            while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT) ||
                    Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT) ||
                    Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.DOWN)){

                // update steps
                steps = new ArrayList<>();

                // update tmp tetromino coordinates
                tmp_tetromino.coordinates = Arrays.copyOf(currentTetromino.coordinates,
                        currentTetromino.coordinates.length);
                // update tmp tetromino stepX
                tmp_tetromino.stepX = currentTetromino.stepX;
                // update tmp tetromino stepY
                tmp_tetromino.stepY = currentTetromino.stepY;

                tmp_tetromino.rotationType = TetrisPlayFieldPanel.DEFAULT;

                // set rotation
                if (i == 1) {

                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CW;

                    tmp_tetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(tmp_tetromino);
                    currentTetromino = Rotation.doRotation(currentTetromino);
                }
                  //  Rotation.pressCWKey(tmp_matrix, tmp_tetromino);

                else if (i == 2) {
                    /*Rotation.pressCWKey(tmp_matrix,tmp_tetromino);
                    Rotation.pressCWKey(tmp_matrix,tmp_tetromino);*/

                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CW;

                    tmp_tetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(tmp_tetromino);
                    currentTetromino = Rotation.doRotation(currentTetromino);

                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CW;

                    tmp_tetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(tmp_tetromino);
                    currentTetromino = Rotation.doRotation(currentTetromino);
                }


                else if (i == 3){
                  //  Rotation.pressCCWKey(tmp_matrix,tmp_tetromino);
                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CCW;

                    tmp_tetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(tmp_tetromino);
                    currentTetromino = Rotation.doRotation(currentTetromino);
                }


                // update tmp tetromino rotation
               /* if(!Rotation.abilityToRotate(tmp_matrix, tmp_tetromino, tmp_tetromino.rotationType))
                    break;*/

                if(Moving.isTetrominoConnected(tmp_tetromino.coordinates,tmp_matrix))
                    break;

                byte min = -1;
                byte max = -1;

                // first moving to leftmost
                if (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT)) {

                    while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT)) {

                      //  System.out.println("move left");
                        tmp_tetromino.stepX -= 1;

                        for (int k = 0; k < 4; k++)
                            tmp_tetromino.coordinates[k].x -= 1;

                        steps.add(Moving.LEFT);
                    }

                    // find leftmost square
                    min = tmp_tetromino.coordinates[0].x;
                    for (int j = 0; j < 4; j++) {
                        if (tmp_tetromino.coordinates[j].x < min)
                            min = tmp_tetromino.coordinates[j].x;
                    }

                }
                // if we can't moving to the leftmost moving to rightmost
                else if (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT)) {
                    while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT)) {
                       // System.out.println("move right");
                        tmp_tetromino.stepX += 1;

                        for (int k = 0; k < 4; k++)
                            tmp_tetromino.coordinates[k].x += 1;

                        steps.add(Moving.RIGHT);
                    }

                    // find rightmost square
                    max = tmp_tetromino.coordinates[0].x;
                    for (int j = 0; j < 4; j++) {
                        if (tmp_tetromino.coordinates[j].x > max)
                            max = tmp_tetromino.coordinates[j].x;
                    }

                }

                // at the end, moving down
                while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.DOWN)) {
                  //  System.out.println("move down");
                    tmp_tetromino.stepY += 1;

                    for (int k = 0; k < 4; k++)
                        tmp_tetromino.coordinates[k].y += 1;

                    steps.add(Moving.DOWN);
                }

                // add 1 to leftmost column if was left moving,
                //       to rightmost column if was right moving,
                //       to stepX if column was only down moving
                int x;
                if (max != -1) x = max;
                else if (min != -1) x = min;
                else x = tmp_tetromino.stepX;

                for (int col = 0; col < tmp_matrix.length; col++) {
                    tmp_matrix[col][x + 1] = 1;
                }

                // add new position to current matrix
                for (int j = 0; j < 4; j++) {
                    fieldMatrix[tmp_tetromino.coordinates[j].y + 1][tmp_tetromino.coordinates[j].x + 1] = 1;
                }

                // print current matrix with new position
              /*  if(tmp_tetromino.rotationType == TetrisPlayFieldPanel.DEFAULT) System.out.println("default rotation");
                else if(tmp_tetromino.rotationType == TetrisPlayFieldPanel.CW) System.out.println("clock-wise rotation");
                else if(tmp_tetromino.rotationType == TetrisPlayFieldPanel.DCW) System.out.println("double clock-wise rotation");
                else if(tmp_tetromino.rotationType == TetrisPlayFieldPanel.CCW) System.out.println("counter clock-wise rotation");

                for (byte[] matrix : fieldMatrix) System.out.println(Arrays.toString(matrix));*/

                // copy of tmp current matrix with new position of tetromino
                byte[][] tmp_tmp_matrix = new byte[fieldMatrix.length][];

                for (int j = 0; j < fieldMatrix.length; ++j) {
                    tmp_tmp_matrix[j] = new byte[fieldMatrix[j].length];
                    System.arraycopy(fieldMatrix[j], 0, tmp_tmp_matrix[j], 0, tmp_tmp_matrix[j].length);
                }

                // add to info
                allInfo.add(new Info(tmp_tetromino.rotationType, tmp_tmp_matrix, /*copy of steps*/ new ArrayList<>(steps)));

                // delete new position from current matrix
                for (int j = 0; j < 4; j++) {
                    fieldMatrix[tmp_tetromino.coordinates[j].y + 1][tmp_tetromino.coordinates[j].x + 1] = 0;
                }


                // print
                for (byte[] tmpMatrix : tmp_matrix) System.out.println(Arrays.toString(tmpMatrix));

                /*for(byte el: steps){
                    if(el == Moving.LEFT)
                        System.out.println("left,");
                    else if(el == Moving.RIGHT)
                        System.out.println("right,");
                    else
                        System.out.println("down,");
                }*/



            }


        }

        return allInfo;

    }

    static class Info {

        private byte rotation_type;
        private byte[][] result_matrix;
        private ArrayList<Byte> moves;

        public Info(byte rotation_type, byte[][] result_matrix, ArrayList<Byte> moves) {
            this.rotation_type = rotation_type;
            this.result_matrix = result_matrix;
            this.moves = moves;
        }

        public byte getRotation_type() {
            return rotation_type;
        }

        public void setRotation_type(byte rotation_type) {
            this.rotation_type = rotation_type;
        }

        public byte[][] getResult_matrix() {
            return result_matrix;
        }

        public void setResult_matrix(byte[][] result_matrix) {
            this.result_matrix = result_matrix;
        }

        public ArrayList<Byte> getMoves() {
            return moves;
        }

        public void setMoves(ArrayList<Byte> moves) {
            this.moves = moves;
        }
    }
}