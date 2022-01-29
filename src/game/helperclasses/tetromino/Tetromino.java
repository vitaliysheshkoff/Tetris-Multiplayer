package game.helperclasses.tetromino;

import game.helperclasses.coordinates.ByteCoordinates;
import java.io.Serializable;

public class Tetromino implements Serializable {

    public ByteCoordinates[] coordinates;
    public byte tetrominoType;
    public byte rotationType;
    public byte stepY;
    public byte stepX;

    public Tetromino(ByteCoordinates[] coordinates, byte TetrominoType, byte rotationType, byte stepY, byte stepX) {
        this.coordinates = coordinates;
        this.tetrominoType = TetrominoType;
        this.rotationType = rotationType;
        this.stepY = stepY;
        this.stepX = stepX;
    }

}
