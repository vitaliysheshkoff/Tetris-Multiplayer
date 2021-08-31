package game.serial;

import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;

import java.io.Serializable;
import java.util.ArrayList;

public class GameSaver implements Serializable {

    public GameSaver() {
    }
    private byte randomType;
    private int amount_I, amount_J, amount_L, amount_O, amount_S, amount_T, amount_Z;
    private byte amountUsedTetrominoes;
    private byte[] usedTetrominoes;
    private byte[][] fieldMatrix;
    private byte nextTetromino;
    private byte amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level, extraScore;
    private long score;
    private Tetromino currentTetromino;
    private ArrayList<SquareOfTetromino> elementsStayOnField;
    private double musicFramePosition;
    private byte stepYBeforePause;

    public byte getStepYBeforePause() {
        return stepYBeforePause;
    }

    public void setStepYBeforePause(byte stepYBeforePause) {
        this.stepYBeforePause = stepYBeforePause;
    }

    public byte getRandomType() {
        return randomType;
    }

    public void setRandomType(byte randomType) {
        this.randomType = randomType;
    }

    public int getAmount_I() {
        return amount_I;
    }

    public void setAmount_I(int amount_I) {
        this.amount_I = amount_I;
    }

    public int getAmount_J() {
        return amount_J;
    }

    public void setAmount_J(int amount_J) {
        this.amount_J = amount_J;
    }

    public int getAmount_L() {
        return amount_L;
    }

    public void setAmount_L(int amount_L) {
        this.amount_L = amount_L;
    }

    public int getAmount_O() {
        return amount_O;
    }

    public void setAmount_O(int amount_O) {
        this.amount_O = amount_O;
    }

    public int getAmount_S() {
        return amount_S;
    }

    public void setAmount_S(int amount_S) {
        this.amount_S = amount_S;
    }

    public int getAmount_T() {
        return amount_T;
    }

    public void setAmount_T(int amount_T) {
        this.amount_T = amount_T;
    }

    public int getAmount_Z() {
        return amount_Z;
    }

    public void setAmount_Z(int amount_Z) {
        this.amount_Z = amount_Z;
    }

    public byte getAmountUsedTetrominoes() {
        return amountUsedTetrominoes;
    }

    public void setAmountUsedTetrominoes(byte amountUsedTetrominoes) {
        this.amountUsedTetrominoes = amountUsedTetrominoes;
    }

    public byte[] getUsedTetrominoes() {
        return usedTetrominoes;
    }

    public void setUsedTetrominoes(byte[] usedTetrominoes) {
        this.usedTetrominoes = usedTetrominoes;
    }

    public byte[][] getFieldMatrix() {
        return fieldMatrix;
    }

    public void setFieldMatrix(byte[][] fieldMatrix) {
        this.fieldMatrix = fieldMatrix;
    }

    public byte getNextTetromino() {
        return nextTetromino;
    }

    public void setNextTetromino(byte nextTetromino) {
        this.nextTetromino = nextTetromino;
    }

    public byte getAmountOfDeletingLinesBetweenTetrominoes() {
        return amountOfDeletingLinesBetweenTetrominoes;
    }

    public void setAmountOfDeletingLinesBetweenTetrominoes(byte amountOfDeletingLinesBetweenTetrominoes) {
        this.amountOfDeletingLinesBetweenTetrominoes = amountOfDeletingLinesBetweenTetrominoes;
    }

    public byte getAmountOfDeletingLinesBetweenLevels() {
        return amountOfDeletingLinesBetweenLevels;
    }

    public void setAmountOfDeletingLinesBetweenLevels(byte amountOfDeletingLinesBetweenLevels) {
        this.amountOfDeletingLinesBetweenLevels = amountOfDeletingLinesBetweenLevels;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public byte getExtraScore() {
        return extraScore;
    }

    public void setExtraScore(byte extraScore) {
        this.extraScore = extraScore;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public void setCurrentTetromino(Tetromino currentTetromino) {
        this.currentTetromino = currentTetromino;
    }

    public ArrayList<SquareOfTetromino> getElementsStayOnField() {
        return elementsStayOnField;
    }

    public void setElementsStayOnField(ArrayList<SquareOfTetromino> elementsStayOnField) {
        this.elementsStayOnField = elementsStayOnField;
    }

    public double getMusicFramePosition() {
        return musicFramePosition;
    }

    public void setMusicFramePosition(double musicFramePosition) {
        this.musicFramePosition = musicFramePosition;
    }
}
