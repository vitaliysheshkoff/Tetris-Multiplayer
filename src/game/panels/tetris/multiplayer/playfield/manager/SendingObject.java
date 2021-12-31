package game.panels.tetris.multiplayer.playfield.manager;

import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import java.io.Serializable;

public class SendingObject implements Serializable {

    public boolean gameOver;
    public int amountOfDeletingLines;
    public byte level;
    public long score;
    public byte[][] fieldMatrix;
    public Tetromino currentTetromino;
    public SquareOfTetromino[] elementsStayOnField;
    public byte nextTetromino;


    public SendingObject(boolean gameOver, byte[][] fieldMatrix, long score, Tetromino currentTetromino, SquareOfTetromino[] elementsStayOnField, byte level, int amountOfDeletingLines, byte nextTetromino) {
        this.gameOver = gameOver;
        this.fieldMatrix = fieldMatrix;
        this.score = score;
        this.currentTetromino = currentTetromino;
        this.elementsStayOnField = elementsStayOnField;
        this.level = level;
        this.amountOfDeletingLines = amountOfDeletingLines;
        this.nextTetromino = nextTetromino;
    }
}
