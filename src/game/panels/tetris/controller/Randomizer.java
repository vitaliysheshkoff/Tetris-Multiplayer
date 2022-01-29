package game.panels.tetris.controller;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static byte oldStyleRandomTetromino() {
        return (byte) ThreadLocalRandom.current().nextInt(0, 7);
    }

    public static Object[] newStyleRandomTetromino(byte[] usedTetrominoes, byte amountUsedTetrominoes) {
        System.out.println(Arrays.toString(usedTetrominoes));

        byte nextTetromino;

        if (amountUsedTetrominoes == 7)
            amountUsedTetrominoes = resetUsedTetrominoes(usedTetrominoes);

        do
            nextTetromino = (byte) ThreadLocalRandom.current().nextInt(0, 7);
        while (checkIsArrayContainsTetromino(usedTetrominoes, nextTetromino));

        usedTetrominoes[nextTetromino] = nextTetromino;
        amountUsedTetrominoes++;

        return new Object[]{usedTetrominoes, amountUsedTetrominoes, nextTetromino};
    }

    private static byte resetUsedTetrominoes(byte[] usedTetrominoes) {

        for (int i = 0; i < 7; i++)
            usedTetrominoes[i] = -1;

        return 0;
    }


    private static boolean checkIsArrayContainsTetromino(byte[] usedTetrominoes, byte tetromino) {
        for (int i = 0; i < 7; i++) {
            if (usedTetrominoes[i] == tetromino)
                return true;
        }
        return false;
    }
}
