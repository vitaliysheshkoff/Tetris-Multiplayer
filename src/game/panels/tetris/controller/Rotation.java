package game.panels.tetris.controller;

import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.start.Main;

import static game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel.*;

public class Rotation {

    public static void pressCCWKey(byte[][] fieldMatrix, Tetromino currentTetromino) {

        if (currentTetromino.rotationType == DEFAULT) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CCW)) {
                System.out.println("Rotate CCW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == CCW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, DCCW)) {
                System.out.println("Rotate CCW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == DCCW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CW)) {
                System.out.println("Rotate CCW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == DCW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CW)) {
                System.out.println("Rotate CCW");
                Main.audioPlayer.playRotate();
            }
        } else { //(currentTetromino.rotationType == CW)
            if (abilityToRotate(fieldMatrix, currentTetromino, DEFAULT)) {
                System.out.println("Rotate CCW");
                Main.audioPlayer.playRotate();
            }
        }
    }

    public static void pressCWKey(byte[][] fieldMatrix, Tetromino currentTetromino) {

        if (currentTetromino.rotationType == DEFAULT) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CW)) {
                System.out.println("Rotate CW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == CW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, DCW)) {
                System.out.println("Rotate CW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == DCW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CCW)) {
                System.out.println("Rotate CW");
                Main.audioPlayer.playRotate();
            }
        } else if (currentTetromino.rotationType == DCCW) {
            if (abilityToRotate(fieldMatrix, currentTetromino, CCW)) {
                System.out.println("Rotate CW");
                Main.audioPlayer.playRotate();
            }
        } else { //(currentTetromino.rotationType == CCW)
            if (abilityToRotate(fieldMatrix, currentTetromino, DEFAULT)) {
                System.out.println("Rotate CW");
                Main.audioPlayer.playRotate();
            }
        }
    }

    private static boolean abilityToRotate(byte[][] fieldMatrix, Tetromino currentTetromino, byte typeOfRotation) {

        ByteCoordinates[] oldCoordinates = new ByteCoordinates[4];
        for (int i = 0; i < 4; i++)
            oldCoordinates[i] = new ByteCoordinates(currentTetromino.coordinates[i].x, currentTetromino.coordinates[i].y);

        byte oldTypeOfRotation = currentTetromino.rotationType;
        currentTetromino.rotationType = typeOfRotation;

        currentTetromino.coordinates = setCurrentTetrominoCoordinates(currentTetromino);
        currentTetromino = doRotation(currentTetromino);

        for (int i = 0; i < 4; i++) {
            if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {
                currentTetromino.rotationType = oldTypeOfRotation;
                for (int j = 0; j < 4; j++) {
                    currentTetromino.coordinates[j].y = oldCoordinates[j].y;
                    currentTetromino.coordinates[j].x = oldCoordinates[j].x;
                }
                System.out.println("block rotation!");
                return false;
            }
        }
        return true;
    }

    public static ByteCoordinates[] setCurrentTetrominoCoordinates(Tetromino currentTetromino) {

        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.O) {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1),
                    currentTetromino.stepX, (byte) (currentTetromino.stepX + 1), currentTetromino.stepY, currentTetromino.stepY,
                    (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1));
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.I) {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1),
                    (byte) (currentTetromino.stepX + 2), (byte) (currentTetromino.stepX + 3), currentTetromino.stepY,
                    currentTetromino.stepY, currentTetromino.stepY, currentTetromino.stepY);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.J) {
            return setTetrominoCoordinates(currentTetromino.stepX, currentTetromino.stepX, (byte) (currentTetromino.stepX + 1),
                    (byte) (currentTetromino.stepX + 2), currentTetromino.stepY, (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1),
                    (byte) (currentTetromino.stepY + 1));
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.L) {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepX + 2),
                    (byte) (currentTetromino.stepX + 2), (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1), currentTetromino.stepY);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.S) {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepX + 1),
                    (byte) (currentTetromino.stepX + 2), (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1), currentTetromino.stepY, currentTetromino.stepY);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.T) {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepX + 2),
                    (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1), currentTetromino.stepY);
        } else /*if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.Z)*/ {
            return setTetrominoCoordinates(currentTetromino.stepX, (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepX + 1), (byte) (currentTetromino.stepX + 2),
                    currentTetromino.stepY, currentTetromino.stepY, (byte) (currentTetromino.stepY + 1), (byte) (currentTetromino.stepY + 1));
        }
    }

    private static ByteCoordinates[] setTetrominoCoordinates(byte x1, byte x2, byte x3, byte x4, byte y1, byte y2, byte y3, byte y4) {

        return new ByteCoordinates[]{new ByteCoordinates(x1, y1), new ByteCoordinates(x2, y2), new ByteCoordinates(x3, y3), new ByteCoordinates(x4, y4)};

    }

    public static Tetromino doRotation(Tetromino currentTetromino) {
        if (currentTetromino.rotationType == CW)
            return rotateClockWise(currentTetromino);
        else if (currentTetromino.rotationType == CCW)
            return rotateCounterClockWise(currentTetromino);
        else if (currentTetromino.rotationType == DCW)
            return rotateDoubleClockWise(currentTetromino);
        else if (currentTetromino.rotationType == DCCW)
            return rotateDoubleCounterClockWise(currentTetromino);

        return currentTetromino;
    }

    private static Tetromino rotateTCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateJCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 2;
        currentTetromino.coordinates[1].x += 1;
        currentTetromino.coordinates[1].y -= 1;
        currentTetromino.coordinates[3].x -= 1;
        currentTetromino.coordinates[3].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateLCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y -= 1;
        currentTetromino.coordinates[2].x -= 1;
        currentTetromino.coordinates[2].y += 1;
        currentTetromino.coordinates[3].y += 2;

        return currentTetromino;
    }

    private static Tetromino rotateZCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 2;
        currentTetromino.coordinates[1].x += 1;
        currentTetromino.coordinates[1].y += 1;
        currentTetromino.coordinates[3].x -= 1;
        currentTetromino.coordinates[3].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateSCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y -= 1;
        currentTetromino.coordinates[2].x += 1;
        currentTetromino.coordinates[2].y += 1;
        currentTetromino.coordinates[3].y += 2;

        return currentTetromino;
    }

    private static Tetromino rotateICW(Tetromino currentTetromino) {
        /**/
        currentTetromino.coordinates[1].x += 1;
        currentTetromino.coordinates[1].y += 1;
        currentTetromino.coordinates[0].x += 2;
        currentTetromino.coordinates[0].y += 2;
        currentTetromino.coordinates[3].x -= 1;
        currentTetromino.coordinates[3].y -= 1;

        return currentTetromino;
    }

    private static Tetromino rotateTDCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[3].y += 2;

        return currentTetromino;
    }

    private static Tetromino rotateJDCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 2;
        currentTetromino.coordinates[0].y += 2;

        return currentTetromino;
    }

    private static Tetromino rotateLDCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[3].x -= 2;
        currentTetromino.coordinates[3].y += 2;

        return currentTetromino;
    }

    private static Tetromino rotateIDCW(Tetromino currentTetromino) {
        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateZDCW(Tetromino currentTetromino) {
        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateSDCW(Tetromino currentTetromino) {
        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateTCCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[2].x -= 1;
        currentTetromino.coordinates[2].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateJCCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].y += 2;
        currentTetromino.coordinates[1].x += 1;
        currentTetromino.coordinates[1].y -= 1;
        currentTetromino.coordinates[3].x -= 1;
        currentTetromino.coordinates[3].y += 1;

        return currentTetromino;
    }

    private static Tetromino rotateLCCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y -= 1;
        currentTetromino.coordinates[2].x -= 1;
        currentTetromino.coordinates[2].y += 1;
        currentTetromino.coordinates[3].x -= 2;

        return currentTetromino;
    }

    private static Tetromino rotateZCCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].y += 2;
        currentTetromino.coordinates[1].x -= 1;
        currentTetromino.coordinates[1].y += 1;
        currentTetromino.coordinates[3].x -= 1;
        currentTetromino.coordinates[3].y -= 1;

        return currentTetromino;
    }

    private static Tetromino rotateSCCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y += 1;
        currentTetromino.coordinates[2].x -= 1;
        currentTetromino.coordinates[2].y += 1;
        currentTetromino.coordinates[3].x -= 2;

        return currentTetromino;
    }

    private static Tetromino rotateICCW(Tetromino currentTetromino) {
        currentTetromino.coordinates[2].x -= 1;
        currentTetromino.coordinates[2].y += 1;
        currentTetromino.coordinates[3].x -= 2;
        currentTetromino.coordinates[3].y += 2;
        currentTetromino.coordinates[0].x += 1;
        currentTetromino.coordinates[0].y -= 1;

        return currentTetromino;
    }

    private static Tetromino rotateClockWise(Tetromino currentTetromino) {
        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.T) {
            return rotateTCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.J) {
            return rotateJCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.L) {
            return rotateLCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.Z) {
            return rotateZCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.S) {
            return rotateSCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.I) {
            return rotateICW(currentTetromino);
        }
        return currentTetromino;
    }

    private static Tetromino rotateDoubleClockWise(Tetromino currentTetromino) {
        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.T) {
            return rotateTDCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.J) {
            return rotateJDCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.L) {
            return rotateLDCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.Z) {
            return rotateZDCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.S) {
            return rotateSDCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.I) {
            return rotateIDCW(currentTetromino);
        }
        return currentTetromino;
    }

    private static Tetromino rotateCounterClockWise(Tetromino currentTetromino) {
        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.T) {
            return rotateTCCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.J) {
            return rotateJCCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.L) {
            return rotateLCCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.Z) {
            return rotateZCCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.S) {
            return rotateSCCW(currentTetromino);
        } else if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.I) {
            return rotateICCW(currentTetromino);
        }
        return currentTetromino;
    }

    private static Tetromino rotateDoubleCounterClockWise(Tetromino currentTetromino) {
        return rotateDoubleClockWise(currentTetromino);
    }
}
