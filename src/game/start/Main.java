package game.start;

import com.formdev.flatlaf.FlatDarkLaf;
import game.audio.AudioPlayer;
import game.frame.TetrisFrame;
import game.panels.tetris.multiplayer.battlepanel.TetrisPanelMultiplayer;
import game.panels.menu.LeaderBoardPanel;
import game.panels.menu.MenuPanel;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.menu.OptionsPanel;
import game.panels.tetris.singleplayer.mainpanel.TetrisPanel;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class Main {

    public static double monitorWidth;
    public static double monitorHeight;

    public static double applicationWidth = 0;
    public static double applicationHeight = 0;

    public static Font FONT;

    public static final Color GREEN_COLOR = new Color(114, 203, 59);
    public static final Color PINK_COLOR = new Color(139, 0, 139);
    public static final Color BLUE_COLOR = new Color(0, 206, 209);
    public static final Color YELLOW_COLOR = new Color(255, 255, 0);
    public static final Color RED_COLOR = new Color(255, 50, 19);
    public static final Color ORANGE_COLOR = new Color(255, 151, 28);
    public static final Color DARK_BLUE_COLOR = new Color(3, 65, 174);

    public static final String APPLICATION_SIZE_FILE_NAME = "screen_size.dat";
    public static final String RESUME_FILE_NAME = "resume.dat";
    public static final String SCORE_FILE_NAME = "score.dat";
    public static final String OPTIONS_FILE_NAME = "options.dat";

    public static AudioPlayer audioPlayer;
    public static MenuPanel menuPanel;
    public static OptionsPanel optionPanel;
    public static TetrisPanel tetrisPanel;
    public static TetrisFrame tetrisFrame;
    public static LeaderBoardPanel leaderBoardPanel;
    public static TetrisPanelMultiplayer tetrisPanelMultiplayer;
    public static Multiplayer multiplayerPanel2;

    private static final String FONT_PATH = "/resources/fonts/minecraft-title-cyrillic-regular3.ttf";

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            System.setProperty("sun.java2d.uiScale", "1.0");

            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            // get screen dimension
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            monitorWidth = screenSize.getWidth();
            monitorHeight = screenSize.getHeight();

            try {
                //Custom font
                FONT = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getResourceAsStream(FONT_PATH)))/*.deriveFont((float) (width/100))*/;
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(FONT);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            // get application width and height from file
            if (!getApplicationSizeFileName()) {
                applicationWidth = monitorWidth / 2;
                applicationHeight = monitorHeight * 3 / 4;
            }

            // if no file do this:
            if (applicationWidth < applicationHeight)
                Main.FONT = Main.FONT.deriveFont((float) (applicationWidth / 50f));
            else
                Main.FONT = Main.FONT.deriveFont((float) (applicationHeight / 50f));

            // main panels
            audioPlayer = new AudioPlayer();
            tetrisPanel = new TetrisPanel();
            menuPanel = new MenuPanel();
            optionPanel = new OptionsPanel();
            tetrisPanelMultiplayer = new TetrisPanelMultiplayer();
            tetrisFrame = new TetrisFrame();
            leaderBoardPanel = new LeaderBoardPanel();
            multiplayerPanel2 = new Multiplayer();
        });
    }

    private static boolean getApplicationSizeFileName() {
        Dimension dimension;
        try {
            File applicationSizeFile = new File(System.getProperty("user.dir"), APPLICATION_SIZE_FILE_NAME);
            if (applicationSizeFile.length() > 0) {

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), APPLICATION_SIZE_FILE_NAME).getAbsolutePath()));

                dimension = (Dimension) ois.readObject();

                applicationWidth = dimension.getWidth();
                applicationHeight = dimension.getHeight();

                ois.close();

                return true;
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static void saveApplicationSize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), Main.APPLICATION_SIZE_FILE_NAME).getAbsolutePath()))) {
            oos.writeObject(new Dimension(Main.tetrisFrame.getWidth(), Main.tetrisFrame.getHeight()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
