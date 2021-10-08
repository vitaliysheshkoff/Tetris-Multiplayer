package game.start;

import game.audio.AudioPlayer;
import game.frames.TetrisFrame;
import game.multiplayer.TetrisPanelMultiplayer;
import game.panels.menu.elements.LeaderBoardPanel;
import game.panels.menu.MenuPanel;
import game.panels.menu.elements.Multiplayer2;
import game.panels.menu.elements.MultiplayerPanel;
import game.panels.menu.elements.OptionsPanel;
import game.panels.tetris.TetrisPanel;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static Font CONSOLAS_FONT_20 = new Font("Consolas", Font.PLAIN, 20);
    public static Font CONSOLAS_FONT_24 = new Font("Consolas", Font.PLAIN, 24);
    public static Font CONSOLAS_FONT_36 = new Font("Consolas", Font.PLAIN, 36);

    public static Font COSMIC_SAN_MS_FONT_16 = new Font("Comic Sans MS", Font.PLAIN, 16);
    public static Font COSMIC_SAN_MS_FONT_18 = new Font("Comic Sans MS", Font.PLAIN, 18);
    public static Font COSMIC_SAN_MS_FONT_20 = new Font("Comic Sans MS", Font.PLAIN, 20);
    public static Font COSMIC_SAN_MS_FONT_24 = new Font("Comic Sans MS", Font.PLAIN, 24);

    public static Color GREEN_COLOR = new Color(114, 203, 59);
    public static Color PINK_COLOR = new Color(139, 0, 139);
    public static Color BLUE_COLOR = new  Color(0, 206, 209);
    public static Color YELLOW_COLOR = new Color(255, 255, 0);
    public static Color RED_COLOR = new Color(255, 50, 19);
    public static Color ORANGE_COLOR = new Color(255, 151, 28);
    public static Color DARK_BLUE_COLOR = new Color(3, 65, 174);

    public static String RESUME_FILE_NAME = "resume.dat";
    public static String SCORE_FILE_NAME = "score.dat";
    public static String OPTIONS_FILE_NAME = "options.dat";

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
    public static Multiplayer2 multiplayerPanel2;

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
        tetrisPanelMultiplayer = new TetrisPanelMultiplayer();
        tetrisFrame = new TetrisFrame();
        leaderBoardPanel = new LeaderBoardPanel();
        multiplayerPanel = new MultiplayerPanel();

        multiplayerPanel2 = new Multiplayer2();
    }
}
