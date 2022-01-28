//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package game.start;

import com.formdev.flatlaf.FlatDarkLaf;
import game.audio.AudioPlayer;
import game.frame.TetrisFrame;
import game.panels.menu.LeaderBoardPanel;
import game.panels.menu.MenuPanel;
import game.panels.menu.OptionsPanel;
import game.panels.tetris.multiplayer.battlepanel.TetrisPanelMultiplayer;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.tetris.singleplayer.mainpanel.TetrisPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import javax.swing.UIManager;

public class Main {
    public static double monitorWidth;
    public static double monitorHeight;
    public static double applicationWidth = 0.0D;
    public static double applicationHeight = 0.0D;
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

    public Main() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            System.setProperty("sun.java2d.uiScale", "1.0");

            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception var4) {
                System.err.println("Failed to initialize LaF");
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            monitorWidth = screenSize.getWidth();
            monitorHeight = screenSize.getHeight();

            try {
                FONT = Font.createFont(0, Objects.requireNonNull(Main.class.getResourceAsStream("/resources/fonts/minecraft-title-cyrillic-regular3.ttf")));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(FONT);
            } catch (FontFormatException | IOException var3) {
                var3.printStackTrace();
            }

            if (!getApplicationSizeFileName()) {
                applicationWidth = monitorWidth / 2.0D;
                applicationHeight = monitorHeight * 3.0D / 4.0D;
            }

            if (applicationWidth < applicationHeight) {
                FONT = FONT.deriveFont((float)(applicationWidth / 50.0D));
            } else {
                FONT = FONT.deriveFont((float)(applicationHeight / 50.0D));
            }

            audioPlayer = new AudioPlayer();
            tetrisPanel = new TetrisPanel();
            menuPanel = new MenuPanel();
            optionPanel = new OptionsPanel();
            tetrisPanelMultiplayer = new TetrisPanelMultiplayer();
            tetrisFrame = new TetrisFrame();
            leaderBoardPanel = new LeaderBoardPanel();
            multiplayerPanel2 = new Multiplayer();
            tetrisFrame.revalidateAll(tetrisFrame);
            if (args != null && args.length == 1) {
                args[0] = args[0].replaceAll("tetris://", "_").replace("/", "_");
                args[0] = args[0].replaceFirst("_", "");
                System.out.println(args[0]);
                String[] ARGS = args[0].split("_");
                System.out.println(ARGS[0] + "\n" + ARGS[1] + "\n" + ARGS[2] + "\n" + ARGS[3] + "\n");
                if (ARGS[0].equals("1")) {
                    menuPanel.goMultiplayer();
                    multiplayerPanel2.globalCreateAddressTextField.setText(ARGS[2].replace("-", ".") + ":" + ARGS[3]);
                    multiplayerPanel2.joinRoomTextField.setText(ARGS[3]);
                    multiplayerPanel2.goTetrisMultiplayerPanel(false, (byte)3);
                    tetrisFrame.setExtendedState(tetrisFrame.getExtendedState() | 6);
                }
            }

        });
    }

    private static boolean getApplicationSizeFileName() {
        try {
            File applicationSizeFile = new File(System.getProperty("user.dir"), "screen_size.dat");
            if (applicationSizeFile.length() > 0L) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "screen_size.dat")).getAbsolutePath()));
                Dimension dimension = (Dimension)ois.readObject();
                applicationWidth = dimension.getWidth();
                applicationHeight = dimension.getHeight();
                ois.close();
                return true;
            }
        } catch (ClassNotFoundException | IOException var3) {
            var3.printStackTrace();
        }

        return false;
    }

    public static void saveApplicationSize() {
        try {

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "screen_size.dat")).getAbsolutePath()))) {
                oos.writeObject(new Dimension(tetrisFrame.getWidth(), tetrisFrame.getHeight()));
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

    }
}
