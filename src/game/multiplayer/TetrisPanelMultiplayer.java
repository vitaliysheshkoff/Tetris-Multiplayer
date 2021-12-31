package game.multiplayer;

import game.helperclasses.MyButton;
import game.multiplayer.playfield.TetrisPlayFieldPanelMultiplayer;
import game.multiplayer.playfield.TetrisPlayFieldPanelMultiplayerOpponent;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.panels.tetris.TetrisPanel;
import game.panels.tetris.TetrisStatisticsPanel;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static game.panels.tetris.TetrisPanel.*;

public class TetrisPanelMultiplayer extends JPanel {

    private MyButton mainMenuButton;
    public JLabel tetrisGameLevelLabel;
    public JLabel tetrisGameLevelLabelOpponent;
    public JLabel tetrisLinesAmountLabel;
    public JLabel tetrisLinesAmountLabelOpponent;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanelOpponent;
    public TetrisPlayFieldPanelMultiplayer tetrisPlayFieldPanelMultiplayer;
    public TetrisPlayFieldPanelMultiplayerOpponent tetrisPlayFieldPanelMultiplayerOpponent;
    public JLabel tetrisPlayerNameLabel;
    private JLabel tetrisVSLabel;
    public JLabel tetrisPlayerNameLabelOpponent;
    public volatile JLabel tetrisScoresLabel;
    public JLabel tetrisScoresLabelOpponent;
    public TetrisStatisticsPanel tetrisStatisticsPanel;

    public byte backgroundType = BACKGROUND3;

    public TetrisPanelMultiplayer() {

        initComponents();

        setBackground(Color.BLACK);

        tetrisLinesAmountLabel.setFont(Main.FONT);
        tetrisGameLevelLabel.setFont(Main.FONT);
        tetrisScoresLabel.setFont(Main.FONT);
        tetrisPlayerNameLabel.setFont(Main.FONT);
        tetrisVSLabel.setFont(Main.FONT);
        tetrisLinesAmountLabelOpponent.setFont(Main.FONT);
        tetrisGameLevelLabelOpponent.setFont(Main.FONT);
        tetrisScoresLabelOpponent.setFont(Main.FONT);
        tetrisPlayerNameLabelOpponent.setFont(Main.FONT);

        /*try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }*/

        /*try {

            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
            backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_2_PATH)));
            backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_3_PATH)));
            backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_4_PATH)));
            backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_5_PATH)));

        } catch (IOException exception) {
            exception.printStackTrace();
        }*/

    }

    private void initComponents() {

        BackgroundPanel jPanel1 = new BackgroundPanel();
        mainMenuButton = new MyButton("main menu");

        tetrisScoresLabel = new JLabel();
        tetrisGameLevelLabel = new JLabel();
        tetrisScoresLabelOpponent = new JLabel();
        tetrisGameLevelLabelOpponent = new JLabel();
        tetrisLinesAmountLabelOpponent = new JLabel();
        tetrisPlayerNameLabel = new JLabel();
        tetrisPlayerNameLabelOpponent = new JLabel();
        tetrisVSLabel = new JLabel();
        tetrisLinesAmountLabel = new JLabel();

        tetrisPlayFieldPanelMultiplayerOpponent = new TetrisPlayFieldPanelMultiplayerOpponent();
        tetrisNextTetrominoPanelOpponent = new TetrisNextTetrominoPanel();
        tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        tetrisStatisticsPanel = new TetrisStatisticsPanel();
        tetrisPlayFieldPanelMultiplayer = new TetrisPlayFieldPanelMultiplayer();

        tetrisGameLevelLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabelOpponent.setForeground(Color.WHITE);
        tetrisGameLevelLabelOpponent.setForeground(Color.WHITE);
        tetrisVSLabel.setForeground(Color.WHITE);
        tetrisPlayerNameLabel.setForeground(Color.WHITE);
        tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));


        javax.swing.GroupLayout nextPanelLayout = new javax.swing.GroupLayout(tetrisNextTetrominoPanelOpponent);
        tetrisNextTetrominoPanelOpponent.setLayout(nextPanelLayout);
        nextPanelLayout.setHorizontalGroup(
                nextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        nextPanelLayout.setVerticalGroup(
                nextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );

        tetrisPlayFieldPanelMultiplayer.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout tetrisPanelLayout = new javax.swing.GroupLayout(tetrisPlayFieldPanelMultiplayer);
        tetrisPlayFieldPanelMultiplayer.setLayout(tetrisPanelLayout);
        tetrisPanelLayout.setHorizontalGroup(
                tetrisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        tetrisPanelLayout.setVerticalGroup(
                tetrisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        tetrisStatisticsPanel.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout statisticPanelLayout = new javax.swing.GroupLayout(tetrisStatisticsPanel);
        tetrisStatisticsPanel.setLayout(statisticPanelLayout);
        statisticPanelLayout.setHorizontalGroup(
                statisticPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        statisticPanelLayout.setVerticalGroup(
                statisticPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );

        tetrisLinesAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisLinesAmountLabel.setText("lines amount");
        tetrisLinesAmountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisScoresLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>" + "0");
        tetrisScoresLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisGameLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabel.setText( "<html><body style='text-align: center'>Level:<br>"+ "0");
        tetrisGameLevelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mainMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisPlayFieldPanelMultiplayerOpponent.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout tetrisPanelOpponentLayout = new javax.swing.GroupLayout(tetrisPlayFieldPanelMultiplayerOpponent);
        tetrisPlayFieldPanelMultiplayerOpponent.setLayout(tetrisPanelOpponentLayout);
        tetrisPanelOpponentLayout.setHorizontalGroup(
                tetrisPanelOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        tetrisPanelOpponentLayout.setVerticalGroup(
                tetrisPanelOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        tetrisLinesAmountLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisLinesAmountLabelOpponent.setText("lines: 0");
        tetrisLinesAmountLabelOpponent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisScoresLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>" + "0");
        tetrisScoresLabelOpponent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisNextTetrominoPanel.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout nextPanel1Layout = new javax.swing.GroupLayout(tetrisNextTetrominoPanel);
        tetrisNextTetrominoPanel.setLayout(nextPanel1Layout);
        nextPanel1Layout.setHorizontalGroup(
                nextPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        nextPanel1Layout.setVerticalGroup(
                nextPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );

        tetrisGameLevelLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>" + "0");
        tetrisGameLevelLabelOpponent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisPlayerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisPlayerNameLabel.setText("player name");
        tetrisPlayerNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisPlayerNameLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisPlayerNameLabelOpponent.setText("opponent name");
        tetrisPlayerNameLabelOpponent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisVSLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisVSLabel.setText("VS");
        tetrisVSLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainMenuLabelMousePressed();
            }
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mainMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisPlayerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisLinesAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayFieldPanelMultiplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisGameLevelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisScoresLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(tetrisVSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisLinesAmountLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayerNameLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisScoresLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisNextTetrominoPanelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisGameLevelLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tetrisLinesAmountLabel)
                                        .addComponent(tetrisLinesAmountLabelOpponent))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tetrisScoresLabel)
                                                .addGap(36, 36, 36)
                                                .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tetrisGameLevelLabel))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(mainMenuButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                                .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tetrisScoresLabelOpponent)
                                                .addGap(36, 36, 36)
                                                .addComponent(tetrisNextTetrominoPanelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(tetrisGameLevelLabelOpponent))
                                        .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayFieldPanelMultiplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tetrisPlayerNameLabel)
                                        .addComponent(tetrisPlayerNameLabelOpponent)
                                        .addComponent(tetrisVSLabel))
                                .addContainerGap(44, Short.MAX_VALUE))
        );

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");

        add(jPanel1);
    }

    private void mainMenuLabelMouseEntered() {
       // mainMenuLabel.selectButton();
    }

    private void mainMenuLabelMouseExited() {
      //  mainMenuLabel.unselectButton();
    }

    private void mainMenuLabelMousePressed() {
        mainMenuLabelMouseExited();
        if (!Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.blockMainMenuButton)
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.goMenuPanel();
    }


    class BackgroundPanel extends JPanel {

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            //paint background
            BufferedImage bufferedImage = null;
            if (backgroundType == BACKGROUND) {
                bufferedImage = TetrisPanel.backgroundImage;
            } else if (backgroundType == BACKGROUND2) {
                bufferedImage = backgroundImage2;
            } else if (backgroundType == BACKGROUND3) {
                bufferedImage = backgroundImage3;
            } else if (backgroundType == BACKGROUND4) {
                bufferedImage = backgroundImage4;
            } else if (backgroundType == BACKGROUND5) {
                bufferedImage = backgroundImage5;
            }

            for (int i = 0; i < Main.monitorHeight / bufferedImage.getHeight() + 1; i++) {
                for (int j = 0; j < Main.monitorWidth / bufferedImage.getWidth() + 1; j++) {

                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
        }
    }
}
