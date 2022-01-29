package game.helperclasses.coordinates;

import java.io.Serializable;

public class ByteCoordinates implements Serializable {

    public byte x;
    public byte y;

    public ByteCoordinates(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public ByteCoordinates() {
        this.x = 0;
        this.y = 0;
    }

}