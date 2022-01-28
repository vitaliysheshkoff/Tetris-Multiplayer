//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package game.helperclasses.backgroundpainting;

import game.panels.tetris.controller.Painting;
import java.awt.Graphics2D;

public class PaintStaticLetters {
    public PaintStaticLetters() {
    }

    public static void paintLetterA(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
    }

    public static void paintLetterB(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
    }

    public static void paintLetterD(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + radius, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + radius, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintLetterE(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
    }

    public static void paintLetterI(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.T_COLOR, (double)radius, type);
    }

    public static void paintLetterL(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
    }

    public static void paintLetterM(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
    }

    public static void paintLetterN(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 4 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY, Painting.I_COLOR, (double)radius, type);
    }

    public static void paintLetterO(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintLetterP(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintLetterR(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.S_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
    }

    public static void paintLetterS(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + radius, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintLetterT(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY, Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY + radius, Painting.Z_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 3 * radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.I_COLOR, (double)radius, type);
    }

    public static void paintLetterU(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 2 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 3 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + 4 * radius, Painting.L_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + radius, Painting.I_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 2 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 3 * radius, Painting.J_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + 4 * radius, Painting.J_COLOR, (double)radius, type);
    }

    public static void paintLetterY(Graphics2D g2d, int startX, int startY, int radius, byte type) {
        Painting.paintSquare(g2d, startX, startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + radius, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 2 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 3 * radius, startY + radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 4 * radius, startY, Painting.O_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 3 * radius, Painting.T_COLOR, (double)radius, type);
        Painting.paintSquare(g2d, startX + 2 * radius, startY + 4 * radius, Painting.O_COLOR, (double)radius, type);
    }
}
