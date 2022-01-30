package game.serial;

import java.io.Serializable;

public class OptionsSaver implements Serializable {

    private boolean shadow = true;
    private boolean grid = false;

    private byte lineClearAnimation = 0;
    private byte startLevel = 1;
    private byte randomType = 1;
    private byte music = 3;
    private byte backgroundType = 0;
    private byte typeOfSquare = 0;

    private int cwKey = 68;
    private int ccwKey = 65;
    private int rightKey = 39;
    private int leftKey = 37;
    private int downKey = 40;
    private int hardDropKey = 32;
    private int pauseKey = 10;
    private int exitMenuKey = 27;
    private int musicVolume = 0;
    private int soundsVolume = 0;

    public OptionsSaver() {
    }

    public byte getTypeOfSquare() {
        return this.typeOfSquare;
    }

    public void setTypeOfSquare(byte typeOfSquare) {
        this.typeOfSquare = typeOfSquare;
    }

    public boolean getGrid() {
        return this.grid;
    }

    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public boolean getShadow() {
        return this.shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public byte getStartLevel() {
        return this.startLevel;
    }

    public void setStartLevel(byte startLevel) {
        this.startLevel = startLevel;
    }

    public int getCwKey() {
        return this.cwKey;
    }

    public void setCwKey(int cwKey) {
        this.cwKey = cwKey;
    }

    public int getCcwKey() {
        return this.ccwKey;
    }

    public void setCcwKey(int ccwKey) {
        this.ccwKey = ccwKey;
    }

    public int getRightKey() {
        return this.rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public int getLeftKey() {
        return this.leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getDownKey() {
        return this.downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getHardDropKey() {
        return this.hardDropKey;
    }

    public void setHardDropKey(int hardDropKey) {
        this.hardDropKey = hardDropKey;
    }

    public int getPauseKey() {
        return this.pauseKey;
    }

    public void setPauseKey(int pauseKey) {
        this.pauseKey = pauseKey;
    }

    public int getExitMenuKey() {
        return this.exitMenuKey;
    }

    public void setExitMenuKey(int exitMenuKey) {
        this.exitMenuKey = exitMenuKey;
    }

    public byte getRandomType() {
        return this.randomType;
    }

    public void setRandomType(byte randomType) {
        this.randomType = randomType;
    }

    public byte getMusic() {
        return this.music;
    }

    public void setMusic(byte music) {
        this.music = music;
    }

    public int getMusicVolume() {
        return this.musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getSoundsVolume() {
        return this.soundsVolume;
    }

    public void setSoundsVolume(int soundsVolume) {
        this.soundsVolume = soundsVolume;
    }

    public byte getBackgroundType() {
        return this.backgroundType;
    }

    public void setBackgroundType(byte backgroundType) {
        this.backgroundType = backgroundType;
    }

    public byte getLineClearAnimation() {
        return this.lineClearAnimation;
    }

    public void setLineClearAnimation(byte lineClearAnimation) {
        this.lineClearAnimation = lineClearAnimation;
    }
}
