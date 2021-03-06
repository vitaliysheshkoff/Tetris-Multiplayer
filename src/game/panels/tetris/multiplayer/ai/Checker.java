package game.panels.tetris.multiplayer.ai;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Moving;
import game.panels.tetris.controller.Rotation;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel;
import java.util.ArrayList;

class Checker {


    // for new style random
   private static final double A = -0.510066;
   private static final double B = 0.760666;
    private static final double C = -0.35663;
    private static final double D = -0.184483;

    // for plain random
    /*private static final double A = -90;
    private static final double B = 30;
    private static final double C = 170;
    private static final double D = -8222;*/


    public static Info getMove(Tetromino currentTetromino, byte[][] fieldMatrix) {

        if(currentTetromino.rotationType != TetrisPlayFieldPanel.DEFAULT)
            return null;

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
            ByteCoordinates[] byteCoordinates = new ByteCoordinates[4];
            for (int j = 0; j < 4; j++) {
                byteCoordinates[j] = new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y);
            }
            tmp_tetromino = new Tetromino(byteCoordinates, currentTetromino.tetrominoType,
                    TetrisPlayFieldPanel.DEFAULT, currentTetromino.stepY, currentTetromino.stepX);

            // set rotation
            if (i == 0) {
                tmp_tetromino.rotationType = TetrisPlayFieldPanel.DEFAULT;
                //Rotation.doRotation(tmp_tetromino);
            } else if (i == 1) {
                if (tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.O)
                    break;

                tmp_tetromino.rotationType = TetrisPlayFieldPanel.CW;
                Rotation.doRotation(tmp_tetromino);
            } else if (i == 2) {
                if (tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.I ||
                        tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.S ||
                        tmp_tetromino.tetrominoType == TetrisNextTetrominoPanel.Z) {
                    break;
                }

                tmp_tetromino.rotationType = TetrisPlayFieldPanel.DCW;
                Rotation.doRotation(tmp_tetromino);
            } else {
                tmp_tetromino.rotationType = TetrisPlayFieldPanel.CCW;
                Rotation.doRotation(tmp_tetromino);
            }

            // while we can moving
            while ((!Moving.isTetrominoConnected(tmp_tetromino.coordinates, tmp_matrix))
                    && (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT) ||
                    Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT) ||
                    Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.DOWN))) {


                // print
              /*  boolean skip1 = false;
                for (int k = 0; k < tmp_matrix.length; k++) {

                    System.out.print("[");
                    for (int s = 0; s < tmp_matrix[0].length; s++) {
                        for (int j = 0; j < 4; j++) {
                            if (tmp_tetromino.coordinates[j].y + 1 == k && tmp_tetromino.coordinates[j].x + 1 == s) {
                               System.out.print("3, ");
                                skip1 = true;
                            }
                        }
                        if (!skip1) {
                           System.out.print(tmp_matrix[k][s] + ", ");
                        }
                        skip1 = false;

                    }
                    System.out.println("]");
                }*/

                // update steps
                steps = new ArrayList<>();

                boolean isRightTraversal = false;
                boolean isLeftTraversal = false;

                // first moving to left
                if (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT)) {
                    while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.LEFT)) {
                        Moving.pressLeftKey(tmp_tetromino, tmp_matrix, false);
                        steps.add(Moving.LEFT);
                    }
                    isLeftTraversal = true;
                }
                // if we can't moving to the left moving to right
                else if (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT)) {
                    while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.RIGHT)) {
                        Moving.pressRightKey(tmp_tetromino, tmp_matrix, false);
                        steps.add(Moving.RIGHT);
                    }
                    isRightTraversal = true;
                }

                // at the end, moving down
                    if (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.DOWN)) {
                        while (Moving.abilityToMove(tmp_matrix, tmp_tetromino, Moving.DOWN)) {
                            Moving.pressDownKey(tmp_tetromino, tmp_matrix);
                            steps.add(Moving.DOWN);
                        }
                    }

                // add 1 to leftmost column if was left moving,
                //       to rightmost column if was right moving,
                //       to stepX if column was only down moving

                int x = 0;
                if (isRightTraversal) {
                    // find rightmost square
                    byte max = tmp_tetromino.coordinates[0].x;
                    for (int j = 0; j < 4; j++) {
                        if (tmp_tetromino.coordinates[j].x > max)
                            max = tmp_tetromino.coordinates[j].x;
                    }
                    x = max;
                } else if (isLeftTraversal) {// if left traversal
                    // find leftmost square
                    byte min = tmp_tetromino.coordinates[0].x;
                    for (int j = 0; j < 4; j++) {
                        if (tmp_tetromino.coordinates[j].x < min)
                            min = tmp_tetromino.coordinates[j].x;
                    }
                    x = min;
                }

                if (isLeftTraversal || isRightTraversal) {
                    // add 1 to checked column
                    for (int col = 0; col < tmp_matrix.length; col++)
                        tmp_matrix[col][x + 1] = 1;
                } else {
                    byte x1 = tmp_tetromino.coordinates[0].x;
                    byte x2 = tmp_tetromino.coordinates[1].x;
                    byte x3 = tmp_tetromino.coordinates[2].x;
                    byte x4 = tmp_tetromino.coordinates[3].x;

                    for (int col = 0; col < tmp_matrix.length; col++) {
                        tmp_matrix[col][x1 + 1] = 1;
                        tmp_matrix[col][x2 + 1] = 1;
                        tmp_matrix[col][x3 + 1] = 1;
                        tmp_matrix[col][x4 + 1] = 1;
                    }
                }

                // copy of current matrix
                byte[][] tmp_tmp_matrix = new byte[fieldMatrix.length][];

                for (int j = 0; j < fieldMatrix.length; ++j) {
                    tmp_tmp_matrix[j] = new byte[fieldMatrix[j].length];
                    System.arraycopy(fieldMatrix[j], 0, tmp_tmp_matrix[j], 0, tmp_tmp_matrix[j].length);
                }

                // add new tetromino position to tmp tmp matrix
                for (int j = 0; j < 4; j++)
                    tmp_tmp_matrix[tmp_tetromino.coordinates[j].y + 1][tmp_tetromino.coordinates[j].x + 1] = 1;

                // add to info
                allInfo.add(new Info(tmp_tetromino.rotationType, tmp_tmp_matrix, /*copy of steps*/ new ArrayList<>(steps)));

                // update tmp tetromino coordinates
                ByteCoordinates[] byteCoordinates1 = new ByteCoordinates[4];
                for (int j = 0; j < 4; j++) {
                    byteCoordinates1[j] = new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y);
                }
                tmp_tetromino.coordinates = byteCoordinates1;

                // update tmp tetromino stepX
                tmp_tetromino.stepX = currentTetromino.stepX;
                // update tmp tetromino stepY
                tmp_tetromino.stepY = currentTetromino.stepY;

                // set rotation
                if (i == 0) {
                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.DEFAULT;
                } else if (i == 1) {
                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CW;
                    Rotation.doRotation(tmp_tetromino);
                } else if (i == 2) {
                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.DCW;
                    Rotation.doRotation(tmp_tetromino);
                } else {
                    tmp_tetromino.rotationType = TetrisPlayFieldPanel.CCW;
                    Rotation.doRotation(tmp_tetromino);
                }

                if (allInfo.size() > 50)
                    System.exit(111);
            }
        }

        if(allInfo.size() == 0)
            return null;

        int bestIndex = 0;
        double max = A * getAggregateHeight(allInfo.get(0).getResult_matrix())
                + B * getCompleteLines(allInfo.get(0).getResult_matrix())
                + C * getHoles(allInfo.get(0).getResult_matrix())
                + D * getBumpiness(allInfo.get(0).getResult_matrix());

        double tmp;

        for(int i = 1; i < allInfo.size(); i++){
           tmp = A * getAggregateHeight(allInfo.get(i).getResult_matrix())
                   + B * getCompleteLines(allInfo.get(i).getResult_matrix())
                   + C * getHoles(allInfo.get(i).getResult_matrix())
                   + D * getBumpiness(allInfo.get(i).getResult_matrix());

           if(tmp > max) {
               bestIndex = i;
               max = tmp;
           }
        }

        return allInfo.get(bestIndex);
    }

    public static int getAggregateHeight(byte[][] fieldMatrix){
        int sum = 0;
        int colHeight;
        for(int row = 1; row < fieldMatrix[0].length - 1; row++) {
            colHeight = 0;
            for (int col = 1; col < fieldMatrix.length - 1; col++) {
                if (fieldMatrix[col][row] == 1)
                    break;
                colHeight++;
            }
            colHeight = 20 - colHeight;
          //  System.out.println(colHeight + "_______________________________-");
            sum += colHeight;
        }

        return sum;
    }

    public static int getCompleteLines(byte[][] fieldMatrix){
        int sum = 0;
        int amountInLine;
        for(int col = 1; col < fieldMatrix.length - 1; col++) {
            amountInLine = 0;
            for (int row = 1; row < fieldMatrix[0].length - 1; row++) {
                if (fieldMatrix[col][row] == 0/*1*/)
                    break;
                amountInLine++;
            }
          //  System.out.println(amountInLine + "_______________________________-");
            if (amountInLine == 10)
                sum++;
        }

        return sum;
    }

    public static int getHoles(byte[][] fieldMatrix) {

      //  System.out.println("get holes");
        int amountInOneLine;
        int sum = 0;

        for (int row = 1; row < fieldMatrix[0].length - 1; row++) {
            amountInOneLine = 0;
            for (int col = 1; col < fieldMatrix.length - 1; col++) {
                if (fieldMatrix[col][row] == 1){
                    for(int i = col; i < fieldMatrix.length - 1; i++){
                        if(fieldMatrix[i][row] == 0)
                            amountInOneLine++;
                    }
                    sum+=amountInOneLine;
                    break;
                }
            }
        //    System.out.println(amountInOneLine + "_______________________________");
        }

      //  System.out.println(sum);

        return sum;
    }

    public static int getBumpiness(byte[][] fieldMatrix){

        int[] column = new int[10];
        int colHeight;
        for(int row = 1; row < fieldMatrix[0].length - 1; row++) {
            colHeight = 0;
            for (int col = 1; col < fieldMatrix.length - 1; col++) {
                if (fieldMatrix[col][row] == 1)
                    break;
                colHeight++;
            }
            colHeight = 20 - colHeight;
            column[row - 1] = colHeight;

        }

        int sum = 0;
        for(int i = 0; i < 9; i++){
           // System.out.println("|" + column[i] + " - " + column[i + 1] + "| + ");
            sum += Math.abs((column[i] - column[i + 1]));
        }

       // System.out.println("=" + sum);
        return sum;
    }


    public static int getHeight(byte[][] fieldMatrix) {
        int max = 0;

        int colHeight;
        for (int row = 1; row < fieldMatrix[0].length - 1; row++) {
            colHeight = 0;
            for (int col = 1; col < fieldMatrix.length - 1; col++) {
                if (fieldMatrix[col][row] == 1)
                    break;
                colHeight++;
            }
            colHeight = 20 - colHeight;
            //  System.out.println(colHeight + "_______________________________-");
            if (colHeight > max)
                max = colHeight;
        }

        return max;
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