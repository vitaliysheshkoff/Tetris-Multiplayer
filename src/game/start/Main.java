package game.start;

import com.formdev.flatlaf.FlatDarkLaf;
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
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static double width;
    public static double height;
    public static Font FONT;

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

    public static AudioPlayer audioPlayer;
    public static MenuPanel menuPanel;
    public static OptionsPanel optionPanel;
    public static TetrisPanel tetrisPanel;
    public static TetrisFrame tetrisFrame;
    public static LeaderBoardPanel leaderBoardPanel;
    public static TetrisPanelMultiplayer tetrisPanelMultiplayer;
    public static MultiplayerPanel multiplayerPanel;
    public static Multiplayer2 multiplayerPanel2;

    private static final String FONT_PATH = "/res/fonts/minecraft-title-cyrillic-regular3.ttf";


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel( new FlatDarkLaf() );
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }

            // get screen dimension
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            width = screenSize.getWidth();
            height = screenSize.getHeight();

            try {
                //create the font to use. Specify the size!
                FONT = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getResourceAsStream(FONT_PATH)))/*.deriveFont((float) (width/100))*/;
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(FONT);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            // get width and height from file
            // {
            // }

            // if no file do this:
            if (width / 2 < height * 3/4)
                Main.FONT = Main.FONT.deriveFont((float) (width / 100f));
            else
                Main.FONT = Main.FONT.deriveFont((float) (height * 3 /200f));

            audioPlayer = new AudioPlayer();
            tetrisPanel = new TetrisPanel();
            menuPanel = new MenuPanel();
            optionPanel = new OptionsPanel();
            tetrisPanelMultiplayer = new TetrisPanelMultiplayer();
            tetrisFrame = new TetrisFrame();
            leaderBoardPanel = new LeaderBoardPanel();
            multiplayerPanel2 = new Multiplayer2();
        });
    }
}
