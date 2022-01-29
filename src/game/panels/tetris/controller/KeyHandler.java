package game.panels.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public int cwKey = KeyEvent.VK_D, ccwKey = KeyEvent.VK_A, rightKey = KeyEvent.VK_RIGHT, leftKey = KeyEvent.VK_LEFT, downKey = KeyEvent.VK_DOWN,
            hardDropKey = KeyEvent.VK_SPACE, pauseKey = KeyEvent.VK_ENTER, exitMenuKey = KeyEvent.VK_ESCAPE;

    //moving
    private boolean gameOver;
    private boolean clearAnimation;
    private boolean right;
    private boolean left;
    private boolean down;
    private boolean hardDrop;

    //rotation
    private boolean ccw_rotation;
    private boolean cw_rotation;

    private boolean pause;
    private boolean exit;

    private boolean down_released;

    public KeyHandler() {
        resetValues();
    }

    public void resetValues(){
        this.gameOver = false;
        this.clearAnimation = false;
        this.right = false;
        this.left = false;
        this.down = false;
        this.hardDrop = false;
        this.pause = false;
        this.exit = false;
        this.ccw_rotation = false;
        this. cw_rotation = false;
        this.down_released = false;
    }

    public boolean isDown_released() {
        return down_released;
    }

    public void setDown_released(boolean down_released) {
        this.down_released = down_released;
    }


    public boolean isCcw_rotation() {
        return ccw_rotation;
    }

    public void setCcw_rotation(boolean ccw_rotation) {
        this.ccw_rotation = ccw_rotation;
    }

    public boolean isCw_rotation() {
        return cw_rotation;
    }

    public void setCw_rotation(boolean cw_rotation) {
        this.cw_rotation = cw_rotation;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isClearAnimation() {
        return clearAnimation;
    }

    public void setClearAnimation(boolean clearAnimation) {
        this.clearAnimation = clearAnimation;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isHardDrop() {
        return hardDrop;
    }

    public void setHardDrop(boolean hardDrop) {
        this.hardDrop = hardDrop;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && !clearAnimation) {

            if (e.getKeyCode() == ccwKey) {
                ccw_rotation = true;
            } else if (e.getKeyCode() == cwKey) {
                cw_rotation = true;
            } else if (e.getKeyCode() == leftKey) {
                left = true;
            } else if (e.getKeyCode() == rightKey) {
                right = true;
            } else if (e.getKeyCode() == downKey) {
                down = true;
                down_released = false;
            }else if(e.getKeyCode() == hardDropKey){
                hardDrop = true;
            }else if(e.getKeyCode() == exitMenuKey){
                exit = true;
            }else if(e.getKeyCode() == pauseKey){
                pause = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == downKey) {
            down_released = true;
        }
    }
}
