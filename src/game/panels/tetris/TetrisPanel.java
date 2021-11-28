package game.panels.tetris;

import game.audio.AudioPlayer;
import game.dialogs.QuitGameDialog;
import game.helperclasses.CustomButton;
import game.panels.tetris.playfield.TetrisPlayFieldPanel;
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

    public  boolean paintPause;
    public BufferedImage backgroundImage, backgroundImage2, backgroundImage3, backgroundImage4, backgroundImage5, pauseImage;
    public JLabel tetrisMainMenuLabel;

  //  public CustomButton mainMenuButton;

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

    public static final String BACKGROUND_IMAGES_FOLDER = "/res/backgroundImages/";
    public static final String BACKGROUND_IMAGE_PATH = BACKGROUND_IMAGES_FOLDER + "tetrisBackgroundImage.jpg";
    public static final String BACKGROUND_IMAGE_2_PATH = BACKGROUND_IMAGES_FOLDER + "tetrisBackgroundImage2.jpg";
    public static final String BACKGROUND_IMAGE_3_PATH = BACKGROUND_IMAGES_FOLDER + "tetrisBackgroundImage3.jpg";
    public static final String BACKGROUND_IMAGE_4_PATH = BACKGROUND_IMAGES_FOLDER + "tetrisBackgroundImage4.jpg";
    public static final String BACKGROUND_IMAGE_5_PATH = BACKGROUND_IMAGES_FOLDER + "tetrisBackgroundImage5.jpg";

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";

    private static final String PAUSE_PATH = "/res/images/pauseImage.png";

    public TetrisPanel() {

        tetrisLinesAmountLabel = new JLabel("tetrisLinesAmountLabel");
        tetrisScoresLabel = new JLabel("tetrisScoresLabel");
        tetrisGameLevelLabel = new JLabel("tetrisGameLevelLabel");

        tetrisLinesAmountLabel.setFont(Main.FONT);
        tetrisGameLevelLabel.setFont(Main.FONT);
        tetrisScoresLabel.setFont(Main.FONT);


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
      //  setPreferredSize(new Dimension(950, 900));
        initComponents();

        setBackground(Color.BLACK);
        addKeyListener(this);

        /*mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuLabelMousePressed();
            }
        });*/

        tetrisMainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
        tetrisMainMenuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                mainMenuLabelMouseEntered();
            }

            public void mouseExited(MouseEvent evt) {
                mainMenuLabelMouseExited();
            }

            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    mainMenuLabelMousePressed();
                }
            }
        });
    }



    private void initComponents() {


        BackgroundPanel jPanel1 = new BackgroundPanel();

         tetrisMainMenuLabel = new javax.swing.JLabel();
        Color buttonColor = new Color(15,15,15);
       // mainMenuButton = new CustomButton(buttonColor, buttonColor);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        tetrisNextTetrominoPanel.setBackground(new java.awt.Color(0, 0, 0));

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

        tetrisPlayFieldPanel.setBackground(new java.awt.Color(0, 0, 0));

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

        tetrisStatisticsPanel.setBackground(new java.awt.Color(0, 0, 0));

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

        tetrisMainMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //mainMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

      //  tetrisMainMenuLabel.setText("main menu");
       // mainMenuButton.setFont(Main.FONT);
       // mainMenuButton.setText("main menu");
       tetrisMainMenuLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tetrisMainMenuLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisMainMenuLabel/*mainMenuButton*/, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                .addComponent(tetrisMainMenuLabel/*mainMenuButton*/)
                                                .addGap(57, 57, 57)
                                                .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");

        add(jPanel1);

    }

    public void mainMenuLabelMouseEntered() {
        tetrisMainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH)))); // NOI18N
    }

    private void mainMenuLabelMouseExited() {
        tetrisMainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    public void mainMenuLabelMousePressed() {

         tetrisMainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
        if (!tetrisPlayFieldPanel.gameOver) {
            tetrisPlayFieldPanel.mySuspend();
            Main.audioPlayer.playClick();
            new QuitGameDialog(Main.tetrisFrame, true);
            tetrisMainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
            Main.audioPlayer.playClick();
            if (Main.tetrisPanel.tetrisPlayFieldPanel.thread.isAlive())
                tetrisPlayFieldPanel.myResume();
        }
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
        tetrisMainMenuLabel.setVisible(true);
       // mainMenuButton.setVisible(true);
    }

    public void saveGame() {
        serialize();
    }

    private void serialize() {
        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), "resume.dat").getAbsolutePath()));
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

    class BackgroundPanel extends JPanel{
        public void paintComponent(Graphics g) {


           super.paintComponent(g);

            if (paintPause) {
                Graphics2D g2d = (Graphics2D) g;
                int x = (this.getWidth() - pauseImage.getWidth(null)) / 2;
                int y = (this.getHeight() - pauseImage.getHeight(null)) / 2;
                g2d.drawImage(pauseImage, x, y, null);
            } else { //paint background
                BufferedImage bufferedImage = null;
                if (backgroundType == BACKGROUND) {
                    bufferedImage = backgroundImage;
                    setGameLabelsColor(Color.WHITE);
                }
                else if (backgroundType == BACKGROUND2) {
                    bufferedImage = backgroundImage2;
                    setGameLabelsColor(Color.BLACK);
                }
                else if (backgroundType == BACKGROUND3) {
                    bufferedImage = backgroundImage3;
                    setGameLabelsColor(Color.BLACK);
                }
                else if (backgroundType == BACKGROUND4) {
                    bufferedImage = backgroundImage4;
                    setGameLabelsColor(Color.BLACK);
                }
                else if (backgroundType == BACKGROUND5) {
                    bufferedImage = backgroundImage5;
                    setGameLabelsColor(Color.WHITE);
                }
                for (int i = 0; i < Main.height / 150 +1; i++) {
                    for (int j = 0; j < Main.width /150 +1; j++) {

                        g.drawImage(bufferedImage, j * 150, i * 150, this);
                    }
                }
            }
        }

        private void setGameLabelsColor(Color color) {
            tetrisGameLevelLabel.setForeground(color);
            tetrisScoresLabel.setForeground(color);
            tetrisLinesAmountLabel.setForeground(color);
        }
    }
}