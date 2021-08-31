package game.helperclasses;
import java.io.Serializable;

public class SquareOfTetromino implements Serializable {
    public ByteCoordinates coordinates;
    public byte color;

    public SquareOfTetromino(ByteCoordinates coordinates, byte color) {
        this.coordinates = coordinates;
        this.color = color;
    }
}