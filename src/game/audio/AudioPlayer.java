package game.audio;

import game.audio.cue.AudioCue;
import game.audio.cue.AudioMixer;
import game.start.Main;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Objects;

public class AudioPlayer {

    public static final byte MUSIC1 = 0;
    public static final byte MUSIC2 = 1;
    public static final byte MUSIC3 = 2;
    public static final byte OFF = 3;

    private static final String SOUNDS_FOLDER = "/resources/tetrissounds/";
    private static final String CLEAR_LINE_PATH = SOUNDS_FOLDER + "line.wav";
    private static final String GAME_OVER_PATH = SOUNDS_FOLDER + "gameover.wav";
    private static final String HARD_DROP_PATH = SOUNDS_FOLDER + "harddrop.wav";
    private static final String ROTATE_PATH = SOUNDS_FOLDER + "rotate.wav";
    private static final String MOVE_PATH = SOUNDS_FOLDER + "move.wav";
    private static final String TETRIS_PATH = SOUNDS_FOLDER + "tetris.wav";
    private static final String CLICK_PATH = SOUNDS_FOLDER + "click.wav";
    private static final String NEXT_LEVEL_PATH = SOUNDS_FOLDER + "level.wav";
    private static final String PAUSE_PATH = SOUNDS_FOLDER + "pause.wav";

    private static final String MUSIC_1_PATH = SOUNDS_FOLDER + "music1.wav";
    private static final String MUSIC_2_PATH = SOUNDS_FOLDER + "music2.wav";
    private static final String MUSIC_3_PATH = SOUNDS_FOLDER + "music3.wav";

    private static final String MUSIC_1_CUT_PATH = SOUNDS_FOLDER + "music1cut.wav";
    private static final String MUSIC_2_CUT_PATH = SOUNDS_FOLDER + "music2cut.wav";
    private static final String MUSIC_3_CUT_PATH = SOUNDS_FOLDER + "music3cut.wav";

    public double soundsVolume = 0;
    public double musicVolume = 0;
    public boolean newMusic = false;

    public Long GAME_OVER_SOUND_LENGTH;

    public AudioCue clearLineSound,
            gameOverSound,
            hardDropSound,
            moveSound,
            rotateSound,
            tetrisSound,
            clickSound,
            nextLevelSound,
            musicSound1,
            musicSound2,
            musicSound3,
            pauseSound;

    public int music1Handler;
    public int music2Handler;
    public int music3Handler;
    public int clearLineSoundHandler;
    public int gameOverSoundHandler;
    public int hardDropSoundHandler;
    public int moveSoundHandler;
    public int rotateSoundHandler;
    public int tetrisSoundHandler;
    public int clickSoundHandler;
    public int nextLevelSoundHandler;
    public int pauseSoundHandler;

    public double musicFramePosition;

    public AudioCue cutMusicSound1,
            cutMusicSound2,
            cutMusicSound3;

    public int cutMusic1Handler;
    public int cutMusic2Handler;
    public int cutMusic3Handler;

    AudioMixer audioMixer;

    public AudioPlayer() {

        try {

            audioMixer = new AudioMixer();
            audioMixer.start();

            musicSound1 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_1_PATH)), 4);
            musicSound2 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_2_PATH)), 4);
            musicSound3 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_3_PATH)), 4);

            cutMusicSound1 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_1_CUT_PATH)), 4);
            cutMusicSound2 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_2_CUT_PATH)), 4);
            cutMusicSound3 = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MUSIC_3_CUT_PATH)), 4);

            clearLineSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(CLEAR_LINE_PATH)), 4);
            gameOverSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(GAME_OVER_PATH)), 4);
            hardDropSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(HARD_DROP_PATH)), 4);
            tetrisSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(TETRIS_PATH)), 4);
            clickSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(/*CLICK_PATH*/ ROTATE_PATH)), 1);
            nextLevelSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(NEXT_LEVEL_PATH)), 4);

            pauseSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(PAUSE_PATH)), 4);
            rotateSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(ROTATE_PATH)), 4);
            moveSound = AudioCue.makeStereoCue(Objects.requireNonNull(this.getClass().getResource(MOVE_PATH)), 4);

            clickSound.open(audioMixer);
            pauseSound.open(audioMixer);
            musicSound3.open(audioMixer);
            musicSound2.open(audioMixer);
            musicSound1.open(audioMixer);
            cutMusicSound1.open(audioMixer);
            cutMusicSound2.open(audioMixer);
            cutMusicSound3.open(audioMixer);

            moveSound.open();
            tetrisSound.open();
            rotateSound.open();
            hardDropSound.open();
            gameOverSound.open();
            clearLineSound.open();
            nextLevelSound.open();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        GAME_OVER_SOUND_LENGTH = gameOverSound.getMicrosecondLength();
    }


    public void playClearLine() {

        if (clearLineSound.getIsPlaying(clearLineSoundHandler))
            stopPlayingSound(clearLineSound, clearLineSoundHandler);

        clearLineSoundHandler = clearLineSound.play(soundsVolume);
    }

    public void playGameOver() {

       if (gameOverSound.getIsPlaying(gameOverSoundHandler))
            stopPlayingSound(gameOverSound, hardDropSoundHandler);

        hardDropSoundHandler = gameOverSound.play(soundsVolume);
    }

    public void playHardDrop() {

        if (hardDropSound.getIsPlaying(hardDropSoundHandler))
            stopPlayingSound(hardDropSound, hardDropSoundHandler);

        hardDropSoundHandler = hardDropSound.play(soundsVolume);
    }

    public void playMove() {

        if (moveSound.getIsPlaying(moveSoundHandler))
            stopPlayingSound(moveSound, moveSoundHandler);

        moveSoundHandler = moveSound.play(soundsVolume);
    }

    public void playRotate() {

        if (rotateSound.getIsPlaying(rotateSoundHandler))
            stopPlayingSound(rotateSound, rotateSoundHandler);

        rotateSoundHandler = rotateSound.play(soundsVolume);
    }

    public void playTetris() {

        if (tetrisSound.getIsPlaying(tetrisSoundHandler))
            stopPlayingSound(tetrisSound, tetrisSoundHandler);

        tetrisSoundHandler = tetrisSound.play(soundsVolume);
    }

    public void playClick() {

        if (clickSound.getIsPlaying(clickSoundHandler))
            stopPlayingSound(clickSound, clickSoundHandler);

        clickSoundHandler = clickSound.play(soundsVolume);
        clickSound.setSpeed(clickSoundHandler, 6);

    }

    public void playNextLevel() {

        if (nextLevelSound.getIsPlaying(nextLevelSoundHandler))
            stopPlayingSound(nextLevelSound, nextLevelSoundHandler);

        nextLevelSoundHandler = nextLevelSound.play(soundsVolume);
    }

    public void playPause() {

        if (pauseSound.getIsPlaying(pauseSoundHandler))
            stopPlayingSound(pauseSound, pauseSoundHandler);

        pauseSoundHandler = pauseSound.play(soundsVolume);
    }

    public void playMusic(byte music) {

        if (music == MUSIC1) {

            music1Handler = musicSound1.play(musicVolume);

            if (!newMusic) {
                musicSound1.stop(music1Handler);
                musicSound1.setFramePosition(music1Handler, musicFramePosition);
                musicSound1.start(music1Handler);
            } else
                newMusic = false;

            musicSound1.setLooping(music1Handler, -1);

        } else if (music == MUSIC2) {

            music2Handler = musicSound2.play(musicVolume);

            if (!newMusic) {
                musicSound2.stop(music2Handler);
                musicSound2.setFramePosition(music2Handler, musicFramePosition);
                musicSound2.start(music2Handler);
            } else
                newMusic = false;

            musicSound2.setLooping(music2Handler, -1);

        } else if (music == MUSIC3) {

            music3Handler = musicSound3.play(musicVolume);

            if (!newMusic) {
                musicSound3.stop(music3Handler);
                musicSound3.setFramePosition(music3Handler, musicFramePosition);
                musicSound3.start(music3Handler);
            } else
                newMusic = false;

            musicSound3.setLooping(music3Handler, -1);
        }
    }

    public void playCutMusic1() {
        if (!cutMusicSound1.getIsPlaying(cutMusic1Handler)) {

            if (cutMusicSound2.getIsPlaying(cutMusic2Handler))
                stopPlayingSound(cutMusicSound2, cutMusic2Handler);
            else if (cutMusicSound3.getIsPlaying(cutMusic3Handler))
                stopPlayingSound(cutMusicSound3, cutMusic3Handler);

            cutMusic1Handler = cutMusicSound1.play(musicVolume);
        }
    }

    private void stopPlayingSound(AudioCue sound, int soundHandler) {
        sound.stop(soundHandler);
        sound.releaseInstance(soundHandler);
    }

    public void playCutMusic2() {
        if (!cutMusicSound2.getIsPlaying(cutMusic2Handler)) {

            if (cutMusicSound1.getIsPlaying(cutMusic1Handler))
                stopPlayingSound(cutMusicSound1, cutMusic1Handler);
            else if (cutMusicSound3.getIsPlaying(cutMusic3Handler))
                stopPlayingSound(cutMusicSound3, cutMusic3Handler);

            cutMusic2Handler = cutMusicSound2.play(musicVolume);
        }
    }

    public void playCutMusic3() {
        if (!cutMusicSound3.getIsPlaying(cutMusic3Handler)) {

            if (cutMusicSound1.getIsPlaying(cutMusic1Handler))
                stopPlayingSound(cutMusicSound1, cutMusic1Handler);
            else if (cutMusicSound2.getIsPlaying(cutMusic2Handler))
                stopPlayingSound(cutMusicSound2, cutMusic2Handler);

            cutMusic3Handler = cutMusicSound3.play(musicVolume);
        }
    }

    public void offCutMusic() {

        if (cutMusicSound1.getIsPlaying(cutMusic1Handler))
            stopPlayingSound(cutMusicSound1, cutMusic1Handler);
        else if (cutMusicSound2.getIsPlaying(cutMusic2Handler))
            stopPlayingSound(cutMusicSound2, cutMusic2Handler);
        else if (cutMusicSound3.getIsPlaying(cutMusic3Handler))
            stopPlayingSound(cutMusicSound3, cutMusic3Handler);
    }

    public void updateCutMusicVolume() {

        if (cutMusicSound1.getIsPlaying(cutMusic1Handler))
            cutMusicSound1.setVolume(cutMusic1Handler, musicVolume);
        else if (cutMusicSound2.getIsPlaying(cutMusic2Handler))
            cutMusicSound2.setVolume(cutMusic2Handler, musicVolume);
        else if (cutMusicSound3.getIsPlaying(cutMusic3Handler))
            cutMusicSound3.setVolume(cutMusic3Handler, musicVolume);

    }

    public void stopMusic() {
        if (musicSound1.getIsPlaying(music1Handler)) {
            musicSound1.stop(music1Handler);
            musicFramePosition = musicSound1.getFramePosition(music1Handler);
            musicSound1.releaseInstance(music1Handler);
        } else if (musicSound2.getIsPlaying(music2Handler)) {
            musicSound2.stop(music2Handler);
            musicFramePosition = musicSound2.getFramePosition(music2Handler);
            musicSound2.releaseInstance(music2Handler);
        } else if (musicSound3.getIsPlaying(music3Handler)) {
            musicSound3.stop(music3Handler);
            musicFramePosition = musicSound3.getFramePosition(music3Handler);
            musicSound3.releaseInstance(music3Handler);
        }
    }


    public void pauseMusic() {

        if (musicSound1.getIsPlaying(music1Handler))
            musicSound1.stop(music1Handler);
        else if (musicSound2.getIsPlaying(music2Handler))
            musicSound2.stop(music2Handler);
        else if (musicSound3.getIsPlaying(music3Handler))
            musicSound3.stop(music3Handler);

    }

    public void resumeMusic(byte music) {

        if (/*Main.tetrisPanel.tetrisPlayFieldPanel.*/music == MUSIC1)
            musicSound1.start(music1Handler);
        else if (/*Main.tetrisPanel.tetrisPlayFieldPanel.*/music == MUSIC2)
            musicSound2.start(music2Handler);
        else if (/*Main.tetrisPanel.tetrisPlayFieldPanel.*/music == MUSIC3)
            musicSound3.start(music3Handler);

    }

}
