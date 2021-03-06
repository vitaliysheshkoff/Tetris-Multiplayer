package game.panels.tetris.multiplayer.ai;

import game.helperclasses.buttons.MyButton;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.infopanels.TetrisStatisticsPanel;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static game.panels.tetris.singleplayer.mainpanel.TetrisPanel.*;


public class BattlePanel extends JPanel {

    public JLabel tetrisGameLevelLabel;
    public JLabel tetrisGameLevelLabelOpponent;
    public JLabel tetrisLinesAmountLabel;
    public JLabel tetrisLinesAmountLabelOpponent;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanelOpponent;
    public PlayField playfield;
    public AIPlayField aiPlayField;
    public JLabel tetrisPlayerNameLabel;
    public JLabel tetrisPlayerNameLabelOpponent;
    public volatile JLabel tetrisScoresLabel;
    public JLabel tetrisScoresLabelOpponent;
    public TetrisStatisticsPanel tetrisStatisticsPanel;
    public JLabel tetrisVSLabel;
    public MyButton mainMenuButton;

    public byte backgroundType = BACKGROUND2;

    public BattlePanel() {
        initComponents();
        setBackground(Color.BLACK);
    }

    private void initComponents() {

        BackgroundPanel backgroundPanel = new BackgroundPanel();
       mainMenuButton = new MyButton("main menu");

        tetrisVSLabel = new JLabel();
        tetrisScoresLabel = new JLabel();
        tetrisGameLevelLabel = new JLabel();
        tetrisScoresLabelOpponent = new JLabel();
        tetrisGameLevelLabelOpponent = new JLabel();
        tetrisLinesAmountLabelOpponent = new JLabel();
        tetrisPlayerNameLabel = new JLabel();
        tetrisPlayerNameLabelOpponent = new JLabel();
        tetrisLinesAmountLabel = new JLabel();

        aiPlayField = new AIPlayField();
        tetrisNextTetrominoPanelOpponent = new TetrisNextTetrominoPanel();
        tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        tetrisStatisticsPanel = new TetrisStatisticsPanel();
        playfield = new PlayField();

        tetrisVSLabel.setForeground(Color.WHITE);
        tetrisGameLevelLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabelOpponent.setForeground(Color.WHITE);
        tetrisGameLevelLabelOpponent.setForeground(Color.WHITE);
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

        javax.swing.GroupLayout tetrisPanelLayout = new javax.swing.GroupLayout(playfield);
        playfield.setLayout(tetrisPanelLayout);
        tetrisPanelLayout.setHorizontalGroup(
                tetrisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        tetrisPanelLayout.setVerticalGroup(
                tetrisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

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
        tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>" + "0" + "<br/>(0)</html>");
        tetrisScoresLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tetrisGameLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + "0");
        tetrisGameLevelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mainMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout tetrisPanelOpponentLayout = new javax.swing.GroupLayout(aiPlayField);
        aiPlayField.setLayout(tetrisPanelOpponentLayout);
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
        tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>" + "0" + "<br/>(0)</html>");
        tetrisScoresLabelOpponent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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
        tetrisPlayerNameLabelOpponent.setText("bot");
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

        tetrisLinesAmountLabel.setFont(Main.FONT);
        tetrisGameLevelLabel.setFont(Main.FONT);
        tetrisScoresLabel.setFont(Main.FONT);
        tetrisPlayerNameLabel.setFont(Main.FONT);
        tetrisVSLabel.setFont(Main.FONT);
        tetrisLinesAmountLabelOpponent.setFont(Main.FONT);
        tetrisGameLevelLabelOpponent.setFont(Main.FONT);
        tetrisScoresLabelOpponent.setFont(Main.FONT);
        tetrisPlayerNameLabelOpponent.setFont(Main.FONT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);
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
                                        .addComponent(playfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisGameLevelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisScoresLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(tetrisVSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tetrisLinesAmountLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(aiPlayField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(aiPlayField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(playfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tetrisPlayerNameLabel)
                                        .addComponent(tetrisPlayerNameLabelOpponent)
                                        .addComponent(tetrisVSLabel))
                                .addContainerGap(44, Short.MAX_VALUE))
        );

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");

        add(backgroundPanel);
    }

    private void mainMenuLabelMouseEntered() {
    }

    private void mainMenuLabelMouseExited() {
    }

    private void mainMenuLabelMousePressed() {
        if (!Main.multiplayerPanel2.battlePanel.playfield.gameOver) {
            Main.multiplayerPanel2.battlePanel.playfield.mySuspend();
            Main.audioPlayer.playClick();
            Main.multiplayerPanel2.battlePanel.playfield.gameOver = true;
            Main.multiplayerPanel2.battlePanel.playfield.myInterrupt();
            Main.multiplayerPanel2.battlePanel.aiPlayField.mySuspend();
            Main.multiplayerPanel2.battlePanel.aiPlayField.myInterrupt();
            Main.audioPlayer.stopMusic();
            Main.tetrisFrame.remove(Main.multiplayerPanel2.battlePanel);
            Main.tetrisFrame.add(Main.menuPanel);
            Main.tetrisFrame.revalidate();
            Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
            Main.tetrisFrame.repaint();
            Main.menuPanel.selectCurrentButton();
            Main.menuPanel.requestFocusInWindow();
        }
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
                bufferedImage = Main.tetrisPanel.backgroundImage;
            } else if (backgroundType == BACKGROUND2) {
                bufferedImage = Main.tetrisPanel.backgroundImage2;
            } else if (backgroundType == BACKGROUND3) {
                bufferedImage = Main.tetrisPanel.backgroundImage3;
            } else if (backgroundType == BACKGROUND4) {
                bufferedImage = Main.tetrisPanel.backgroundImage4;
            } else if (backgroundType == BACKGROUND5) {
                bufferedImage = Main.tetrisPanel.backgroundImage5;
            }

            for (int i = 0; i < Main.monitorHeight / Objects.requireNonNull(bufferedImage).getHeight() + 1; i++) {
                for (int j = 0; j < Main.monitorWidth / bufferedImage.getWidth() + 1; j++) {

                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
        }

    }
}
