package game.panels.tetris.multiplayer.battlepanel;

import game.helperclasses.buttons.MyButton;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.panels.tetris.infopanels.TetrisStatisticsPanel;
import game.panels.tetris.multiplayer.playfield.TetrisPlayFieldPanelMultiplayer;
import game.panels.tetris.multiplayer.playfield.TetrisPlayFieldPanelMultiplayerOpponent;
import game.start.Main;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TetrisPanelMultiplayer extends JPanel {

    public JLabel tetrisGameLevelLabel;
    public JLabel tetrisGameLevelLabelOpponent;
    public JLabel tetrisLinesAmountLabel;
    public JLabel tetrisLinesAmountLabelOpponent;
    public JLabel tetrisPlayerNameLabel;
    public JLabel tetrisPlayerNameLabelOpponent;
    public JLabel tetrisVSLabel;
    public JLabel tetrisScoresLabelOpponent;
    public JLabel tetrisScoresLabel;

    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanelOpponent;
    public TetrisPlayFieldPanelMultiplayer tetrisPlayFieldPanelMultiplayer;
    public TetrisPlayFieldPanelMultiplayerOpponent tetrisPlayFieldPanelMultiplayerOpponent;
    public TetrisStatisticsPanel tetrisStatisticsPanel;

    public byte backgroundType = 2;

    public TetrisPanelMultiplayer() {
        initComponents();
        setBackground(Color.BLACK);
    }

    private void initComponents() {

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        MyButton mainMenuButton = new MyButton("main menu");

        tetrisVSLabel = new JLabel();
        tetrisScoresLabel = new JLabel();
        tetrisGameLevelLabel = new JLabel();
        tetrisScoresLabelOpponent = new JLabel();
        tetrisGameLevelLabelOpponent = new JLabel();
        tetrisLinesAmountLabelOpponent = new JLabel();
        tetrisPlayerNameLabel = new JLabel();
        tetrisPlayerNameLabelOpponent = new JLabel();
        tetrisLinesAmountLabel = new JLabel();

        tetrisPlayFieldPanelMultiplayerOpponent = new TetrisPlayFieldPanelMultiplayerOpponent();
        tetrisNextTetrominoPanelOpponent = new TetrisNextTetrominoPanel();
        tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        tetrisStatisticsPanel = new TetrisStatisticsPanel();
        tetrisPlayFieldPanelMultiplayer = new TetrisPlayFieldPanelMultiplayer();

        tetrisVSLabel.setForeground(Color.WHITE);
        tetrisGameLevelLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabel.setForeground(Color.WHITE);
        tetrisLinesAmountLabelOpponent.setForeground(Color.WHITE);
        tetrisGameLevelLabelOpponent.setForeground(Color.WHITE);
        tetrisPlayerNameLabel.setForeground(Color.WHITE);
        tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        GroupLayout nextPanelLayout = new GroupLayout(tetrisNextTetrominoPanelOpponent);
        tetrisNextTetrominoPanelOpponent.setLayout(nextPanelLayout);

        nextPanelLayout.setHorizontalGroup(nextPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));
        nextPanelLayout.setVerticalGroup(nextPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));

        GroupLayout tetrisPanelLayout = new GroupLayout(tetrisPlayFieldPanelMultiplayer);
        tetrisPlayFieldPanelMultiplayer.setLayout(tetrisPanelLayout);

        tetrisPanelLayout.setHorizontalGroup(tetrisPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));
        tetrisPanelLayout.setVerticalGroup(tetrisPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, 32767));

        GroupLayout statisticPanelLayout = new GroupLayout(tetrisStatisticsPanel);
        tetrisStatisticsPanel.setLayout(statisticPanelLayout);

        statisticPanelLayout.setHorizontalGroup(statisticPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));
        statisticPanelLayout.setVerticalGroup(statisticPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));

        tetrisLinesAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisLinesAmountLabel.setText("lines amount");
        tetrisLinesAmountLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisScoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>0");
        tetrisScoresLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisGameLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>0");
        tetrisGameLevelLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout tetrisPanelOpponentLayout = new GroupLayout(tetrisPlayFieldPanelMultiplayerOpponent);
        tetrisPlayFieldPanelMultiplayerOpponent.setLayout(tetrisPanelOpponentLayout);

        tetrisPanelOpponentLayout.setHorizontalGroup(tetrisPanelOpponentLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));
        tetrisPanelOpponentLayout.setVerticalGroup(tetrisPanelOpponentLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, 32767));

        tetrisLinesAmountLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisLinesAmountLabelOpponent.setText("lines: 0");
        tetrisLinesAmountLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisScoresLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>0");
        tetrisScoresLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout nextPanel1Layout = new GroupLayout(tetrisNextTetrominoPanel);
        tetrisNextTetrominoPanel.setLayout(nextPanel1Layout);

        nextPanel1Layout.setHorizontalGroup(nextPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, 32767));
        nextPanel1Layout.setVerticalGroup(nextPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 50, 32767));

        tetrisGameLevelLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>0");
        tetrisGameLevelLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisPlayerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisPlayerNameLabel.setText("player name");
        tetrisPlayerNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisPlayerNameLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisPlayerNameLabelOpponent.setText("opponent name");
        tetrisPlayerNameLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        tetrisVSLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisVSLabel.setText("VS");
        tetrisVSLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        mainMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
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

        GroupLayout jPanel1Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisStatisticsPanel, -1, -1, 32767)
                                .addComponent(mainMenuButton, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 18, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisPlayerNameLabel, -1, -1, 32767)
                                .addComponent(tetrisLinesAmountLabel, -1, -1, 32767)
                                .addComponent(tetrisPlayFieldPanelMultiplayer, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 30, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisNextTetrominoPanel, Alignment.TRAILING, -1, -1, 32767)
                                .addComponent(tetrisGameLevelLabel, Alignment.TRAILING, -1, -1, 32767)
                                .addComponent(tetrisScoresLabel, Alignment.TRAILING, -1, 100, 32767)
                                .addComponent(tetrisVSLabel, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 35, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisLinesAmountLabelOpponent, -1, -1, 32767)
                                .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, -1, -1, 32767)
                                .addComponent(tetrisPlayerNameLabelOpponent, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 30, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tetrisScoresLabelOpponent, -1, -1, 32767)
                                .addComponent(tetrisNextTetrominoPanelOpponent, -1, -1, 32767)
                                .addComponent(tetrisGameLevelLabelOpponent, -1, -1, 32767))
                        .addContainerGap(97, 32767)));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(12, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(tetrisLinesAmountLabel)
                                .addComponent(tetrisLinesAmountLabelOpponent))
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup().addComponent(tetrisScoresLabel)
                                        .addGap(36, 36, 36)
                                        .addComponent(tetrisNextTetrominoPanel, -2, -1, -2)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(tetrisGameLevelLabel))
                                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(mainMenuButton)
                                        .addPreferredGap(ComponentPlacement.RELATED, 57, 32767)
                                        .addComponent(tetrisStatisticsPanel, -2, -1, -2))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tetrisScoresLabelOpponent)
                                        .addGap(36, 36, 36)
                                        .addComponent(tetrisNextTetrominoPanelOpponent, -2, -1, -2)
                                        .addGap(6, 6, 6)
                                        .addComponent(tetrisGameLevelLabelOpponent))
                                .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, -1, -1, 32767)
                                .addComponent(tetrisPlayFieldPanelMultiplayer, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(tetrisPlayerNameLabel)
                                .addComponent(tetrisPlayerNameLabelOpponent)
                                .addComponent(tetrisVSLabel))
                        .addContainerGap(44, 32767)));

        tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");
        add(backgroundPanel);
    }

    private void mainMenuLabelMousePressed() {
        if (!Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.blockMainMenuButton) {
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.goMenuPanel();
        }
    }

    class BackgroundPanel extends JPanel {
        BackgroundPanel() {
        }

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

            BufferedImage bufferedImage = null;
            if (backgroundType == 0) {
                bufferedImage = Main.tetrisPanel.backgroundImage;
            } else if (backgroundType == 1) {
                bufferedImage = Main.tetrisPanel.backgroundImage2;
            } else if (backgroundType == 2) {
                bufferedImage = Main.tetrisPanel.backgroundImage3;
            } else if (backgroundType == 3) {
                bufferedImage = Main.tetrisPanel.backgroundImage4;
            } else if (backgroundType == 4) {
                bufferedImage = Main.tetrisPanel.backgroundImage5;
            }

            for (int i = 0; (double) i < Main.monitorHeight / (double) Objects.requireNonNull(bufferedImage).getHeight() + 1.0D; ++i) {
                for (int j = 0; (double) j < Main.monitorWidth / (double) bufferedImage.getWidth() + 1.0D; ++j) {
                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }

        }
    }
}
