package game.start;

import game.audio.AudioPlayer;
import game.frames.TetrisFrame;
import game.multiplayer.TetrisPanelMultiplayer;
import game.panels.menu.elements.LeaderBoardPanel;
import game.panels.menu.MenuPanel;
import game.panels.menu.elements.MultiplayerPanel;
import game.panels.menu.elements.OptionsPanel;
import game.panels.tetris.TetrisPanel;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static Font FONT = new Font("Consolas", Font.PLAIN, 20);
    public static final byte RADIUS_OF_SQUARE = 40;
    public static final byte PLAY_FIELD_BORDER = 5;

    public static AudioPlayer audioPlayer;
    public static MenuPanel menuPanel;
    public static OptionsPanel optionPanel;
    public static TetrisPanel tetrisPanel;
    public static TetrisFrame tetrisFrame;
    public static LeaderBoardPanel leaderBoardPanel;

    public static TetrisPanelMultiplayer tetrisPanelMultiplayer;

    public static MultiplayerPanel multiplayerPanel;


    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        audioPlayer = new AudioPlayer();
        tetrisPanel = new TetrisPanel();
        menuPanel = new MenuPanel();
        optionPanel = new OptionsPanel();

        tetrisPanelMultiplayer = new TetrisPanelMultiplayer();//////////////

        tetrisFrame = new TetrisFrame();
        leaderBoardPanel = new LeaderBoardPanel();

        multiplayerPanel = new MultiplayerPanel();
    }
}
