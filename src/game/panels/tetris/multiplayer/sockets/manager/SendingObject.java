package game.panels.tetris.multiplayer.sockets.manager;

import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import java.io.Serializable;
import java.util.Arrays;

public class SendingObject implements Serializable {

    public boolean gameOver;

    public int amountOfDeletingLines;

    public long score;

    public byte level;
    public byte nextTetromino;
    public byte[][] fieldMatrixByte;

    public Tetromino currentTetromino;
    public SquareOfTetromino[] squareOfTetrominoes;


    public SendingObject(boolean gameOver, byte[][] fieldMatrixByte, long score, Tetromino currentTetromino, SquareOfTetromino[] squareOfTetrominoes, byte level, int amountOfDeletingLines, byte nextTetromino) {
        this.gameOver = gameOver;
        this.fieldMatrixByte = fieldMatrixByte;
        this.score = score;
        this.currentTetromino = currentTetromino;
        this.squareOfTetrominoes = squareOfTetrominoes;
        this.level = level;
        this.amountOfDeletingLines = amountOfDeletingLines;
        this.nextTetromino = nextTetromino;
    }

    public String toString() {
        return "SendingObject{gameOver=" + this.gameOver + ", amountOfDeletingLines=" + this.amountOfDeletingLines + ", level=" + this.level + ", score=" + this.score + ", fieldMatrix=" + Arrays.toString(this.fieldMatrixByte) + ", currentTetromino=" + this.currentTetromino + ", elementsStayOnField=" + Arrays.toString(this.squareOfTetrominoes) + ", nextTetromino=" + this.nextTetromino + '}';
    }
}
