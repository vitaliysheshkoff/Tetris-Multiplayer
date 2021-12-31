package game.serial;

import game.audio.AudioPlayer;
import game.panels.tetris.singleplayer.mainpanel.TetrisPanel;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import static game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel.DISAPPEAR_CLEAR_LINES_ANIMATION;
import static game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel.OLD_STYLE_RANDOM;

public class OptionsSaver implements Serializable {

    private boolean shadow;
    private boolean grid;

    private byte lineClearAnimation;
    private byte startLevel;
    private byte randomType;
    private byte music;
    private byte backgroundType;

    private int cwKey;
    private int ccwKey;
    private int rightKey;
    private int leftKey;
    private int downKey;
    private int hardDropKey;
    private int pauseKey;
    private int exitMenuKey;
    private int musicVolume;
    private int soundsVolume;


    public OptionsSaver() {

        this.startLevel = 0;
        this.cwKey = KeyEvent.VK_D;
        this.ccwKey = KeyEvent.VK_A;
        this.rightKey = KeyEvent.VK_RIGHT;
        this.leftKey = KeyEvent.VK_LEFT;
        this.downKey = KeyEvent.VK_DOWN;
        this.hardDropKey = KeyEvent.VK_SPACE;
        this.pauseKey = KeyEvent.VK_ENTER;
        this.exitMenuKey = KeyEvent.VK_ESCAPE;
        this.randomType = OLD_STYLE_RANDOM;
        this.music = AudioPlayer.OFF;
        this.musicVolume = 10;
        this.soundsVolume = 20;
        this.backgroundType = TetrisPanel.BACKGROUND;
        this.lineClearAnimation = DISAPPEAR_CLEAR_LINES_ANIMATION;
        this.shadow = true;
        this.grid = false;
    }

    public boolean getGrid() {
        return grid;
    }

    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public boolean getShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public byte getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(byte startLevel) {
        this.startLevel = startLevel;
    }

    public int getCwKey() {
        return cwKey;
    }

    public void setCwKey(int cwKey) {
        this.cwKey = cwKey;
    }

    public int getCcwKey() {
        return ccwKey;
    }

    public void setCcwKey(int ccwKey) {
        this.ccwKey = ccwKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getHardDropKey() {
        return hardDropKey;
    }

    public void setHardDropKey(int hardDropKey) {
        this.hardDropKey = hardDropKey;
    }

    public int getPauseKey() {
        return pauseKey;
    }

    public void setPauseKey(int pauseKey) {
        this.pauseKey = pauseKey;
    }

    public int getExitMenuKey() {
        return exitMenuKey;
    }

    public void setExitMenuKey(int exitMenuKey) {
        this.exitMenuKey = exitMenuKey;
    }

    public byte getRandomType() {
        return randomType;
    }

    public void setRandomType(byte randomType) {
        this.randomType = randomType;
    }

    public byte getMusic() {
        return music;
    }

    public void setMusic(byte music) {
        this.music = music;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getSoundsVolume() {
        return soundsVolume;
    }

    public void setSoundsVolume(int soundsVolume) {
        this.soundsVolume = soundsVolume;
    }

    public byte getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(byte backgroundType) {
        this.backgroundType = backgroundType;
    }

    public byte getLineClearAnimation() {
        return lineClearAnimation;
    }

    public void setLineClearAnimation(byte lineClearAnimation) {
        this.lineClearAnimation = lineClearAnimation;
    }
}
