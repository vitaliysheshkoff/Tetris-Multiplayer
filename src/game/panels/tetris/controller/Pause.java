package game.panels.tetris.controller;
import game.start.Main;

public class Pause {

    public static void pressPauseKey() {
        Main.audioPlayer.playPause();
        Main.audioPlayer.pauseMusic();
        Main.tetrisPanel.tetrisPlayFieldPanel.wakeUpThreadFromSleeping();
        Main.tetrisPanel.tetrisPlayFieldPanel.mySuspend();
        hideComponentsForPause();
        Main.tetrisPanel.requestFocusInWindow();
        Main.tetrisPanel.paintPause = true;
        Main.tetrisPanel.repaint();

    }

    private static void hideComponentsForPause() {
        Main.tetrisPanel.tetrisPlayFieldPanel.setVisible(false);
        Main.tetrisPanel.tetrisLinesAmountLabel.setVisible(false);
        Main.tetrisPanel.tetrisGameLevelLabel.setVisible(false);
        Main.tetrisPanel.tetrisStatisticsPanel.setVisible(false);
        Main.tetrisPanel.tetrisNextTetrominoPanel.setVisible(false);
        Main.tetrisPanel.tetrisScoresLabel.setVisible(false);
        Main.tetrisPanel.mainMenuButton.setVisible(false);
    }
}
