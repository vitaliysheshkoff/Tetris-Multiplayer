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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import javax.swing.UIManager;

public class Main {
    public static double monitorWidth;
    public static double monitorHeight;
    public static double applicationWidth = 0.0D;
    public static double applicationHeight = 0.0D;

    public static Font FONT;
    public static Font TITLE_FONT;

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
                    TITLE_FONT = Font.createFont(0, Objects.requireNonNull(Main.class.getResourceAsStream("/resources/fonts/minecraft-title-cyrillic-regular3.ttf")));
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(FONT);
                } catch (FontFormatException | IOException e) {
                    e.printStackTrace();
                }

                if (!getApplicationSizeFileName()) {
                    applicationWidth = monitorWidth / 2.0D;
                    applicationHeight = monitorHeight * 3.0D / 4.0D;
                }

                if (applicationWidth < applicationHeight) {
                    FONT = FONT.deriveFont((float) (applicationWidth / 50.0D));
                } else {
                    FONT = FONT.deriveFont((float) (applicationHeight / 50.0D));
                    //  TITLE_FONT = FONT.deriveFont((float)(applicationHeight / 70.0D));
                }

                TITLE_FONT = FONT.deriveFont(14.0F);


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

                    // example "tetris://123/"
                    // example "tetris://569&0JLQuNGC0LDQu9C40LkoVml0YWxpeV9TaGVzaGtvKQ=="
                    // example "tetris://595&0JLQuNGC0LDQu9C40LkoVml0YWxpeV9TaGVzaGtvKQ=="

                    args[0] = args[0].replaceAll("tetris://", "")
                            .replace("/", "")
                            .replace("%20", "+");

                    System.out.println(args[0]);
                    String[] ARGS = args[0].split("&");

                    byte[] decoded = Base64.getDecoder().decode(ARGS[1]);
                    ARGS[1] = new String(decoded, StandardCharsets.UTF_8);

                    if(ARGS[1].endsWith("()")) {
                        ARGS[1] = ARGS[1].substring(0, ARGS[1].length() - 2);
                        ARGS[1] = "@" + ARGS[1];
                    }
                    else {
                        String name = ARGS[1].substring(0, ARGS[1].lastIndexOf("("));
                        String nick = ARGS[1].substring(ARGS[1].lastIndexOf("(") + 1, ARGS[1].lastIndexOf(")"));

                        if (name.length() > 19)
                            name = name.substring(0, 19) + "...";
                        if (nick.length() > 19)
                            nick = nick.substring(0, 19) + "...";

                        ARGS[1] = "<html><body style='text-align: center'>" + "@" + name + "<br>" + nick;
                    }

                    System.out.println(ARGS[0]);
                    System.out.println(ARGS[1]);

                    menuPanel.goMultiplayer();

                    multiplayerPanel2.joinRoomTextField.setText(ARGS[0]);

                    tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegramUserNickname = ARGS[1];

                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegram = true;
                    multiplayerPanel2.goTetrisMultiplayerPanel(false, Multiplayer.WEB);
                    tetrisFrame.setExtendedState(tetrisFrame.getExtendedState() | 6);
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
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void saveApplicationSize() {
        try {

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "screen_size.dat")).getAbsolutePath()))) {
                oos.writeObject(new Dimension(tetrisFrame.getWidth(), tetrisFrame.getHeight()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
