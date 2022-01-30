package game.panels.tetris.singleplayer.mainpanel;

import game.helperclasses.buttons.MyButton;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.infopanels.TetrisStatisticsPanel;
import game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel;
import game.serial.GameSaver;
import game.start.Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class TetrisPanel extends JPanel implements KeyListener {
    public boolean paintPause;
    
    public BufferedImage backgroundImage;
    public BufferedImage backgroundImage2;
    public BufferedImage backgroundImage3;
    public BufferedImage backgroundImage4;
    public BufferedImage backgroundImage5;
    public BufferedImage pauseImage;
    
    public MyButton mainMenuButton;
    
    public final TetrisPlayFieldPanel tetrisPlayFieldPanel;
    
    public TetrisStatisticsPanel tetrisStatisticsPanel;
    
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    
    public JLabel tetrisScoresLabel = new JLabel("tetrisScoresLabel");
    public JLabel tetrisGameLevelLabel = new JLabel("tetrisGameLevelLabel");
    public JLabel tetrisLinesAmountLabel = new JLabel("tetrisLinesAmountLabel");
    
    public static final byte BACKGROUND = 0;
    public static final byte BACKGROUND2 = 1;
    public static final byte BACKGROUND3 = 2;
    public static final byte BACKGROUND4 = 3;
    public static final byte BACKGROUND5 = 4;
    
    public byte backgroundType = 0;
    
    public static final String BACKGROUND_IMAGES_FOLDER = "/resources/backgroundImages/";
    public static final String BACKGROUND_IMAGE_PATH = "/resources/backgroundImages/congruent_outline.png";
    public static final String BACKGROUND_IMAGE_2_PATH = "/resources/backgroundImages/dark-triangles.png";
    public static final String BACKGROUND_IMAGE_3_PATH = "/resources/backgroundImages/watercolor-3264479_640.jpg";
    public static final String BACKGROUND_IMAGE_4_PATH = "/resources/backgroundImages/pattern-1004855_640.jpg";
    public static final String BACKGROUND_IMAGE_5_PATH = "/resources/backgroundImages/backdrop-3346304_640.png";
    private static final String PAUSE_PATH = "/resources/images/pauseImage.png";
    
    public BackgroundPanel backgroundPanel = null;

    public TetrisPanel() {
        tetrisLinesAmountLabel.setFont(Main.FONT);
        tetrisGameLevelLabel.setFont(Main.FONT);
        tetrisScoresLabel.setFont(Main.FONT);

        tetrisLinesAmountLabel.setForeground(Color.WHITE);
        tetrisGameLevelLabel.setForeground(Color.WHITE);
        tetrisScoresLabel.setForeground(Color.WHITE);

        tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        tetrisStatisticsPanel = new TetrisStatisticsPanel();
        tetrisPlayFieldPanel = new TetrisPlayFieldPanel();

        paintPause = false;

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/congruent_outline.png")));
            backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/dark-triangles.png")));
            backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/watercolor-3264479_640.jpg")));
            backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/pattern-1004855_640.jpg")));
            backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/backdrop-3346304_640.png")));
            pauseImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/images/pauseImage.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initComponents();
        setBackground(Color.BLACK);
        addKeyListener(this);
    }

    private void initComponents() {
        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBackground(new Color(0, 0, 0));

        mainMenuButton = new MyButton("main menu");
        mainMenuButton.addActionListener((e) -> mainMenuLabelMousePressed());

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        GroupLayout tetrisNextTetrominoPanelLayout = new GroupLayout(tetrisNextTetrominoPanel);
        tetrisNextTetrominoPanel.setLayout(tetrisNextTetrominoPanelLayout);

        tetrisNextTetrominoPanelLayout.setHorizontalGroup(tetrisNextTetrominoPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, 32767));
        tetrisNextTetrominoPanelLayout.setVerticalGroup(tetrisNextTetrominoPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, 32767));

        GroupLayout tetrisPlayFieldPanelLayout = new GroupLayout(tetrisPlayFieldPanel);
        tetrisPlayFieldPanel.setLayout(tetrisPlayFieldPanelLayout);

        tetrisPlayFieldPanelLayout.setHorizontalGroup(tetrisPlayFieldPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, 32767));
        tetrisPlayFieldPanelLayout.setVerticalGroup(tetrisPlayFieldPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 203, 32767));

        GroupLayout tetrisStatisticsPanelLayout = new GroupLayout(tetrisStatisticsPanel);
        tetrisStatisticsPanel.setLayout(tetrisStatisticsPanelLayout);

        tetrisStatisticsPanelLayout.setHorizontalGroup(tetrisStatisticsPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, 32767));
        tetrisStatisticsPanelLayout.setVerticalGroup(tetrisStatisticsPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, 32767));

        tetrisLinesAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisLinesAmountLabel.setText("lines amount");
        tetrisLinesAmountLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        tetrisScoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisScoresLabel.setText("score");
        tetrisScoresLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        tetrisGameLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisGameLevelLabel.setText("level");
        tetrisGameLevelLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout jPanel1Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisStatisticsPanel, -1, -1, 32767)
                                .addComponent(mainMenuButton, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 16, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisLinesAmountLabel, -1, -1, 32767)
                                .addComponent(tetrisPlayFieldPanel, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 35, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisNextTetrominoPanel, -1, -1, 32767)
                                .addComponent(tetrisScoresLabel, -1, 100, 32767)
                                .addComponent(tetrisGameLevelLabel, Alignment.TRAILING, -1, -1, 32767))
                        .addContainerGap(32, 32767)));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addComponent(tetrisLinesAmountLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tetrisScoresLabel)
                                                .addGap(32, 32, 32)
                                                .addComponent(tetrisNextTetrominoPanel, -2, -1, -2)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(tetrisGameLevelLabel))
                                        .addComponent(tetrisPlayFieldPanel, -2, -1, -2))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(mainMenuButton)
                                        .addGap(57, 57, 57)
                                        .addComponent(tetrisStatisticsPanel, -2, -1, -2)))
                        .addContainerGap(53, 32767)));

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");
        add(backgroundPanel);
    }

    public void mainMenuLabelMousePressed() {
        mainMenuButton.selectButton();

        if (!tetrisPlayFieldPanel.gameOver) {
            Main.audioPlayer.playClick();

            tetrisPlayFieldPanel.mySuspend();

            tetrisPlayFieldPanel.gameOver = true;

            tetrisPlayFieldPanel.myInterrupt();

            Main.audioPlayer.stopMusic();

            Main.tetrisPanel.saveGame();

            Main.tetrisFrame.remove(Main.tetrisPanel);
            Main.tetrisFrame.add(Main.menuPanel);
            Main.tetrisFrame.revalidate();
            Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
            Main.tetrisFrame.repaint();

            Main.menuPanel.selectCurrentButton();
            Main.menuPanel.requestFocusInWindow();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.pauseKey) {
            resumeGameAfterPause();
        }

    }

    private void resumeGameAfterPause() {
        Main.audioPlayer.resumeMusic();
        showFrameComponents();
        tetrisPlayFieldPanel.myResume();
        Main.tetrisPanel.paintPause = false;
        tetrisPlayFieldPanel.requestFocusInWindow();
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    private void showFrameComponents() {
        tetrisPlayFieldPanel.setVisible(true);
        tetrisLinesAmountLabel.setVisible(true);
        tetrisGameLevelLabel.setVisible(true);
        tetrisStatisticsPanel.setVisible(true);
        tetrisNextTetrominoPanel.setVisible(true);
        tetrisScoresLabel.setVisible(true);
        mainMenuButton.setVisible(true);
    }

    public void saveGame() {
        serialize();
    }

    private void serialize() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "resume.dat")).getAbsolutePath()));
            GameSaver gameSaver = new GameSaver();
            gameSaver.setRandomType(Main.tetrisPanel.tetrisPlayFieldPanel.randomType);
            gameSaver.setUsedTetrominoes(Main.tetrisPanel.tetrisPlayFieldPanel.usedTetrominoes);
            gameSaver.setAmount_I(Main.tetrisPanel.tetrisStatisticsPanel.amount_I);
            gameSaver.setAmount_J(Main.tetrisPanel.tetrisStatisticsPanel.amount_J);
            gameSaver.setAmount_L(Main.tetrisPanel.tetrisStatisticsPanel.amount_L);
            gameSaver.setAmount_O(Main.tetrisPanel.tetrisStatisticsPanel.amount_O);
            gameSaver.setAmount_S(Main.tetrisPanel.tetrisStatisticsPanel.amount_S);
            gameSaver.setAmount_Z(Main.tetrisPanel.tetrisStatisticsPanel.amount_Z);
            gameSaver.setAmount_T(Main.tetrisPanel.tetrisStatisticsPanel.amount_T);
            gameSaver.setAmountOfDeletingLinesBetweenLevels(Main.tetrisPanel.tetrisPlayFieldPanel.amountOfDeletingLinesBetweenLevels);
            gameSaver.setAmountOfDeletingLinesBetweenTetrominoes(Main.tetrisPanel.tetrisPlayFieldPanel.amountOfDeletingLinesBetweenTetrominoes);
            gameSaver.setAmountUsedTetrominoes(Main.tetrisPanel.tetrisPlayFieldPanel.amountUsedTetrominoes);
            gameSaver.setCurrentTetromino(Main.tetrisPanel.tetrisPlayFieldPanel.currentTetromino);
            gameSaver.setElementsStayOnField(Main.tetrisPanel.tetrisPlayFieldPanel.elementsStayOnField);
            gameSaver.setExtraScore(Main.tetrisPanel.tetrisPlayFieldPanel.extraScore);
            gameSaver.setFieldMatrix(Main.tetrisPanel.tetrisPlayFieldPanel.fieldMatrix);
            gameSaver.setLevel(Main.tetrisPanel.tetrisPlayFieldPanel.level);
            gameSaver.setScore(Main.tetrisPanel.tetrisPlayFieldPanel.score);
            gameSaver.setUsedTetrominoes(Main.tetrisPanel.tetrisPlayFieldPanel.usedTetrominoes);
            gameSaver.setNextTetromino(Main.tetrisPanel.tetrisNextTetrominoPanel.nextTetromino);
            gameSaver.setStepYBeforePause(Main.tetrisPanel.tetrisPlayFieldPanel.stepYBeforePause);
            if (Main.tetrisPanel.tetrisPlayFieldPanel.music == 0) {
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound1.getFramePosition(Main.audioPlayer.music1Handler));
            } else if (Main.tetrisPanel.tetrisPlayFieldPanel.music == 1) {
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound2.getFramePosition(Main.audioPlayer.music2Handler));
            } else if (Main.tetrisPanel.tetrisPlayFieldPanel.music == 2) {
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound3.getFramePosition(Main.audioPlayer.music3Handler));
            } else {
                gameSaver.setMusicFramePosition(0.0D);
            }

            oos.writeObject(gameSaver);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class BackgroundPanel extends JPanel {
        BufferedImage bufferedImage;

        public BackgroundPanel() {
            bufferedImage = null;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int i;
            int j;
            if (paintPause) {
                i = (getWidth() - pauseImage.getWidth(null)) / 2;
                j = (getHeight() - pauseImage.getHeight(null)) / 2;
                g2d.drawImage(pauseImage, i, j, null);
            } else {
                g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                try {
                    if (backgroundType == 0) {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/congruent_outline.png")));
                    } else if (backgroundType == 1) {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/dark-triangles.png")));
                    } else if (backgroundType == 2) {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/watercolor-3264479_640.jpg")));
                    } else if (backgroundType == 3) {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/pattern-1004855_640.jpg")));
                    } else if (backgroundType == 4) {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/backdrop-3346304_640.png")));
                    }
                } catch (IOException var5) {
                    var5.printStackTrace();
                }

                for (i = 0; (double) i < Main.monitorHeight / (double) bufferedImage.getHeight() + 1.0D; ++i) {
                    for (j = 0; (double) j < Main.monitorWidth / (double) bufferedImage.getWidth() + 1.0D; ++j) {
                        g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                    }
                }
            }
        }
    }
}
