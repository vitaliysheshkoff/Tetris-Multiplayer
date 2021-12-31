package game.helperclasses.tetromino;
import game.helperclasses.coordinates.ByteCoordinates;

import java.io.Serializable;

public class SquareOfTetromino implements Serializable {
    public ByteCoordinates coordinates;
    public byte color;

    public SquareOfTetromino(ByteCoordinates coordinates, byte color) {
        this.coordinates = coordinates;
        this.color = color;
    }
}