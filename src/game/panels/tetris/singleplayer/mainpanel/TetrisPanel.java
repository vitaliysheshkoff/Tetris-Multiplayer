package game.panels.tetris.singleplayer.mainpanel;

import game.audio.AudioPlayer;
import game.dialogs.QuitGameDialog;
import game.helperclasses.buttons.MyButton;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.infopanels.TetrisStatisticsPanel;
import game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel;
import game.start.Main;
import game.serial.GameSaver;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class TetrisPanel extends JPanel implements KeyListener {

    public boolean paintPause;
    public static BufferedImage backgroundImage, backgroundImage2, backgroundImage3, backgroundImage4, backgroundImage5, pauseImage;

    public MyButton mainMenuButton;

    public final TetrisPlayFieldPanel tetrisPlayFieldPanel;
    public TetrisStatisticsPanel tetrisStatisticsPanel;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    public JLabel tetrisScoresLabel;
    public JLabel tetrisGameLevelLabel;
    public JLabel tetrisLinesAmountLabel;

    public static final byte BACKGROUND = 0;
    public static final byte BACKGROUND2 = 1;
    public static final byte BACKGROUND3 = 2;
    public static final byte BACKGROUND4 = 3;
    public static final byte BACKGROUND5 = 4;

    public byte backgroundType = BACKGROUND;

    public static final String BACKGROUND_IMAGES_FOLDER = "/resources/backgroundImages/";
    public static final String BACKGROUND_IMAGE_PATH = BACKGROUND_IMAGES_FOLDER + "congruent_outline.png";
    public static final String BACKGROUND_IMAGE_2_PATH = BACKGROUND_IMAGES_FOLDER + "dark-triangles.png";
    public static final String BACKGROUND_IMAGE_3_PATH = BACKGROUND_IMAGES_FOLDER + "watercolor-3264479_640.jpg";
    public static final String BACKGROUND_IMAGE_4_PATH = BACKGROUND_IMAGES_FOLDER + "pattern-1004855_640.jpg";
    public static final String BACKGROUND_IMAGE_5_PATH = BACKGROUND_IMAGES_FOLDER + "backdrop-3346304_640.png";

    private static final String PAUSE_PATH = "/resources/images/pauseImage.png";

    public TetrisPanel() {

        tetrisLinesAmountLabel = new JLabel("tetrisLinesAmountLabel");
        tetrisScoresLabel = new JLabel("tetrisScoresLabel");
        tetrisGameLevelLabel = new JLabel("tetrisGameLevelLabel");

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
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
            backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_2_PATH)));
            backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_3_PATH)));
            backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_4_PATH)));
            backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_5_PATH)));

            pauseImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(PAUSE_PATH)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        initComponents();

        setBackground(Color.BLACK);
        addKeyListener(this);

        mainMenuButton.addActionListener(e -> mainMenuLabelMousePressed());
    }


    private void initComponents() {

        BackgroundPanel jPanel1 = new BackgroundPanel();

        mainMenuButton = new MyButton("main menu");

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tetrisNextTetrominoPanelLayout = new javax.swing.GroupLayout(tetrisNextTetrominoPanel);
        tetrisNextTetrominoPanel.setLayout(tetrisNextTetrominoPanelLayout);
        tetrisNextTetrominoPanelLayout.setHorizontalGroup(
                tetrisNextTetrominoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        tetrisNextTetrominoPanelLayout.setVerticalGroup(
                tetrisNextTetrominoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tetrisPlayFieldPanelLayout = new javax.swing.GroupLayout(tetrisPlayFieldPanel);
        tetrisPlayFieldPanel.setLayout(tetrisPlayFieldPanelLayout);
        tetrisPlayFieldPanelLayout.setHorizontalGroup(
                tetrisPlayFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        tetrisPlayFieldPanelLayout.setVerticalGroup(
                tetrisPlayFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 203, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tetrisStatisticsPanelLayout = new javax.swing.GroupLayout(tetrisStatisticsPanel);
        tetrisStatisticsPanel.setLayout(tetrisStatisticsPanelLayout);
        tetrisStatisticsPanelLayout.setHorizontalGroup(
                tetrisStatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        tetrisStatisticsPanelLayout.setVerticalGroup(
                tetrisStatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        tetrisLinesAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisLinesAmountLabel.setText("lines amount");
        tetrisLinesAmountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisScoresLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisScoresLabel.setText("score");
        tetrisScoresLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisGameLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabel.setText("level");
        tetrisGameLevelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mainMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        mainMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mainMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisLinesAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisScoresLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(tetrisGameLevelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tetrisLinesAmountLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(tetrisScoresLabel)
                                                        .addGap(32, 32, 32)
                                                        .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(tetrisGameLevelLabel))
                                                .addComponent(tetrisPlayFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(mainMenuButton)
                                                .addGap(57, 57, 57)
                                                .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");

        add(jPanel1);

    }

    public void mainMenuLabelMousePressed() {

        mainMenuButton.selectButton();
        if (!tetrisPlayFieldPanel.gameOver) {
            tetrisPlayFieldPanel.mySuspend();
            Main.audioPlayer.playClick();
            new QuitGameDialog(Main.tetrisFrame, true);
            Main.audioPlayer.playClick();
            if (Main.tetrisPanel.tetrisPlayFieldPanel.thread.isAlive())
                tetrisPlayFieldPanel.myResume();
        }
        mainMenuButton.unselectButton();
        this.tetrisPlayFieldPanel.requestFocusInWindow();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.pauseKey) {
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

    @Override
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

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), Main.RESUME_FILE_NAME).getAbsolutePath()));
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
            if (Main.tetrisPanel.tetrisPlayFieldPanel.music == AudioPlayer.MUSIC1)
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound1.getFramePosition(Main.audioPlayer.music1Handler));
            else if (Main.tetrisPanel.tetrisPlayFieldPanel.music == AudioPlayer.MUSIC2)
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound2.getFramePosition(Main.audioPlayer.music2Handler));
            else if (Main.tetrisPanel.tetrisPlayFieldPanel.music == AudioPlayer.MUSIC3)
                gameSaver.setMusicFramePosition(Main.audioPlayer.musicSound3.getFramePosition(Main.audioPlayer.music3Handler));
            else
                gameSaver.setMusicFramePosition(0);

            oos.writeObject(gameSaver);
            oos.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    class BackgroundPanel extends JPanel {

        BufferedImage bufferedImage = null;

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (paintPause) {
                int x = (this.getWidth() - pauseImage.getWidth(null)) / 2;
                int y = (this.getHeight() - pauseImage.getHeight(null)) / 2;
                g2d.drawImage(pauseImage, x, y, null);
            } else {

                // need this to improve quality of image
                 g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


                if (backgroundType == BACKGROUND)
                    bufferedImage = backgroundImage;

                else if (backgroundType == BACKGROUND2)
                    bufferedImage = backgroundImage2;

                else if (backgroundType == BACKGROUND3)
                    bufferedImage = backgroundImage3;

                else if (backgroundType == BACKGROUND4)
                    bufferedImage = backgroundImage4;

                else if (backgroundType == BACKGROUND5)
                    bufferedImage = backgroundImage5;

                for (int i = 0; i < Main.monitorHeight / bufferedImage.getHeight() + 1; i++) {
                    for (int j = 0; j < Main.monitorWidth / bufferedImage.getWidth() + 1; j++) {

                        g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                    }
                }
            }
        }

    }
}