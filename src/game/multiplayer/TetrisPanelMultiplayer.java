package game.multiplayer;

import game.multiplayer.playfield.TetrisPlayFieldPanelMultiplayer;
import game.multiplayer.playfield.TetrisPlayFieldPanelMultiplayerOpponent;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.panels.tetris.TetrisStatisticsPanel;
import game.start.Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static game.panels.tetris.TetrisPanel.*;

public class TetrisPanelMultiplayer extends JPanel {

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";

    private JLabel mainMenuLabel;
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

    public byte backgroundType = BACKGROUND;

    private BufferedImage backgroundImage;
    private BufferedImage backgroundImage2;
    private BufferedImage backgroundImage3;
    private BufferedImage backgroundImage4;
    private BufferedImage backgroundImage5;

    public TetrisPanelMultiplayer() {

        initComponents();

        setBackground(Color.BLACK);
        tetrisLinesAmountLabel.setFont(Main.CONSOLAS_FONT_20);
        tetrisGameLevelLabel.setFont(Main.CONSOLAS_FONT_20);
        tetrisScoresLabel.setFont(Main.CONSOLAS_FONT_20);
        tetrisPlayerNameLabel.setFont(Main.CONSOLAS_FONT_20);
        tetrisVSLabel.setFont(Main.CONSOLAS_FONT_20);

        tetrisLinesAmountLabelOpponent.setFont(Main.CONSOLAS_FONT_20);
        tetrisGameLevelLabelOpponent.setFont(Main.CONSOLAS_FONT_20);
        tetrisScoresLabelOpponent.setFont(Main.CONSOLAS_FONT_20);
        tetrisPlayerNameLabelOpponent.setFont(Main.CONSOLAS_FONT_20);

        tetrisStatisticsPanel.setForeground(Color.WHITE);
        tetrisNextTetrominoPanel.setForeground(Color.WHITE);
        tetrisNextTetrominoPanelOpponent.setForeground(Color.WHITE);

        tetrisStatisticsPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        tetrisNextTetrominoPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        tetrisNextTetrominoPanelOpponent.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {

            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
            backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_2_PATH)));
            backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_3_PATH)));
            backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_4_PATH)));
            backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_5_PATH)));

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void initComponents() {

        mainMenuLabel = new JLabel();
        tetrisLinesAmountLabel = new JLabel();
        tetrisScoresLabel = new JLabel();
        tetrisGameLevelLabel = new JLabel();
        tetrisPlayFieldPanelMultiplayerOpponent = new TetrisPlayFieldPanelMultiplayerOpponent();
        tetrisNextTetrominoPanelOpponent = new TetrisNextTetrominoPanel();
        tetrisScoresLabelOpponent = new JLabel();
        tetrisGameLevelLabelOpponent = new JLabel();
        tetrisLinesAmountLabelOpponent = new JLabel();
        tetrisPlayerNameLabel = new JLabel();
        tetrisPlayerNameLabelOpponent = new JLabel();
        tetrisVSLabel = new JLabel();

        tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        tetrisStatisticsPanel = new TetrisStatisticsPanel();

        tetrisPlayFieldPanelMultiplayer = new TetrisPlayFieldPanelMultiplayer();

        setBackground(new java.awt.Color(51, 153, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1570, 900));

        tetrisPlayFieldPanelMultiplayer.setBackground(new java.awt.Color(0, 0, 0));
        tetrisPlayFieldPanelMultiplayer.setForeground(new java.awt.Color(255, 255, 255));
        tetrisPlayFieldPanelMultiplayer.setMaximumSize(new java.awt.Dimension(410, 810));
        tetrisPlayFieldPanelMultiplayer.setMinimumSize(new java.awt.Dimension(410, 810));
        tetrisPlayFieldPanelMultiplayer.setPreferredSize(new java.awt.Dimension(410, 810));

        javax.swing.GroupLayout tetrisPlayFieldPanelMultiplayerLayout = new javax.swing.GroupLayout(tetrisPlayFieldPanelMultiplayer);
        tetrisPlayFieldPanelMultiplayer.setLayout(tetrisPlayFieldPanelMultiplayerLayout);
        tetrisPlayFieldPanelMultiplayerLayout.setHorizontalGroup(
                tetrisPlayFieldPanelMultiplayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        tetrisPlayFieldPanelMultiplayerLayout.setVerticalGroup(
                tetrisPlayFieldPanelMultiplayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        tetrisNextTetrominoPanel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisNextTetrominoPanel.setPreferredSize(new java.awt.Dimension(210, 210));

        javax.swing.GroupLayout tetrisNextTetrominoPanelLayout = new javax.swing.GroupLayout(tetrisNextTetrominoPanel);
        tetrisNextTetrominoPanel.setLayout(tetrisNextTetrominoPanelLayout);
        tetrisNextTetrominoPanelLayout.setHorizontalGroup(
                tetrisNextTetrominoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 210, Short.MAX_VALUE)
        );
        tetrisNextTetrominoPanelLayout.setVerticalGroup(
                tetrisNextTetrominoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 210, Short.MAX_VALUE)
        );

        tetrisStatisticsPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tetrisStatisticsPanelLayout = new javax.swing.GroupLayout(tetrisStatisticsPanel);
        tetrisStatisticsPanel.setLayout(tetrisStatisticsPanelLayout);
        tetrisStatisticsPanelLayout.setHorizontalGroup(
                tetrisStatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 258, Short.MAX_VALUE)
        );
        tetrisStatisticsPanelLayout.setVerticalGroup(
                tetrisStatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 526, Short.MAX_VALUE)
        );

        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
        mainMenuLabel.setText("jLabel1");
        mainMenuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
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

        tetrisLinesAmountLabel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisLinesAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        tetrisLinesAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisLinesAmountLabel.setText("tetrisLinesAmountLabel");
        tetrisLinesAmountLabel.setMaximumSize(new java.awt.Dimension(410, 40));
        tetrisLinesAmountLabel.setMinimumSize(new java.awt.Dimension(410, 40));
        tetrisLinesAmountLabel.setPreferredSize(new java.awt.Dimension(410, 40));

        tetrisScoresLabel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisScoresLabel.setForeground(new java.awt.Color(255, 255, 255));
        tetrisScoresLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisScoresLabel.setText("tetrisScoresLabel");
        tetrisScoresLabel.setPreferredSize(new java.awt.Dimension(200, 200));

        tetrisGameLevelLabel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisGameLevelLabel.setForeground(new java.awt.Color(255, 255, 255));
        tetrisGameLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabel.setText("tetrisGameLevelLabel");

        tetrisPlayFieldPanelMultiplayerOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisPlayFieldPanelMultiplayerOpponent.setForeground(new java.awt.Color(255, 255, 255));
        tetrisPlayFieldPanelMultiplayerOpponent.setMaximumSize(new java.awt.Dimension(410, 810));
        tetrisPlayFieldPanelMultiplayerOpponent.setMinimumSize(new java.awt.Dimension(410, 810));
        tetrisPlayFieldPanelMultiplayerOpponent.setPreferredSize(new java.awt.Dimension(410, 810));

        javax.swing.GroupLayout tetrisPlayFieldPanelMultiplayerOpponentLayout = new javax.swing.GroupLayout(tetrisPlayFieldPanelMultiplayerOpponent);
        tetrisPlayFieldPanelMultiplayerOpponent.setLayout(tetrisPlayFieldPanelMultiplayerOpponentLayout);
        tetrisPlayFieldPanelMultiplayerOpponentLayout.setHorizontalGroup(
                tetrisPlayFieldPanelMultiplayerOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        tetrisPlayFieldPanelMultiplayerOpponentLayout.setVerticalGroup(
                tetrisPlayFieldPanelMultiplayerOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 810, Short.MAX_VALUE)
        );

        tetrisNextTetrominoPanelOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisNextTetrominoPanelOpponent.setPreferredSize(new java.awt.Dimension(210, 210));

        javax.swing.GroupLayout tetrisNextTetrominoPanelOpponentLayout = new javax.swing.GroupLayout(tetrisNextTetrominoPanelOpponent);
        tetrisNextTetrominoPanelOpponent.setLayout(tetrisNextTetrominoPanelOpponentLayout);
        tetrisNextTetrominoPanelOpponentLayout.setHorizontalGroup(
                tetrisNextTetrominoPanelOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 210, Short.MAX_VALUE)
        );
        tetrisNextTetrominoPanelOpponentLayout.setVerticalGroup(
                tetrisNextTetrominoPanelOpponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 210, Short.MAX_VALUE)
        );

        tetrisScoresLabelOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisScoresLabelOpponent.setForeground(new java.awt.Color(255, 255, 255));
        tetrisScoresLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisScoresLabelOpponent.setText("tetrisScoresLabel");
        tetrisScoresLabelOpponent.setPreferredSize(new java.awt.Dimension(200, 200));

        tetrisGameLevelLabelOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisGameLevelLabelOpponent.setForeground(new java.awt.Color(255, 255, 255));
        tetrisGameLevelLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisGameLevelLabelOpponent.setText("tetrisGameLevelLabel");

        tetrisLinesAmountLabelOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisLinesAmountLabelOpponent.setForeground(new java.awt.Color(255, 255, 255));
        tetrisLinesAmountLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisLinesAmountLabelOpponent.setText("tetrisLinesAmountLabel");
        tetrisLinesAmountLabelOpponent.setMaximumSize(new java.awt.Dimension(410, 40));
        tetrisLinesAmountLabelOpponent.setMinimumSize(new java.awt.Dimension(400, 40));
        tetrisLinesAmountLabelOpponent.setPreferredSize(new java.awt.Dimension(410, 40));

        tetrisPlayerNameLabel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisPlayerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        tetrisPlayerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisPlayerNameLabel.setText("tetrisPlayerNameLabel");
        tetrisPlayerNameLabel.setMaximumSize(new java.awt.Dimension(400, 47));
        tetrisPlayerNameLabel.setMinimumSize(new java.awt.Dimension(400, 47));
        tetrisPlayerNameLabel.setPreferredSize(new java.awt.Dimension(400, 47));

        tetrisPlayerNameLabelOpponent.setBackground(new java.awt.Color(0, 0, 0));
        tetrisPlayerNameLabelOpponent.setForeground(new java.awt.Color(255, 255, 255));
        tetrisPlayerNameLabelOpponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisPlayerNameLabelOpponent.setText("tetrisPlayerNameLabelOpponent");
        tetrisPlayerNameLabelOpponent.setMaximumSize(new java.awt.Dimension(400, 47));
        tetrisPlayerNameLabelOpponent.setMinimumSize(new java.awt.Dimension(400, 47));
        tetrisPlayerNameLabelOpponent.setPreferredSize(new java.awt.Dimension(400, 47));

        tetrisVSLabel.setBackground(new java.awt.Color(0, 0, 0));
        tetrisVSLabel.setForeground(new java.awt.Color(255, 255, 255));
        tetrisVSLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tetrisVSLabel.setText("vs");
        tetrisVSLabel.setMaximumSize(new java.awt.Dimension(216, 47));
        tetrisVSLabel.setMinimumSize(new java.awt.Dimension(216, 47));
        tetrisVSLabel.setPreferredSize(new java.awt.Dimension(216, 47));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(mainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tetrisPlayerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisLinesAmountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayFieldPanelMultiplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(tetrisVSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tetrisPlayerNameLabelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tetrisScoresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tetrisGameLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tetrisLinesAmountLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tetrisNextTetrominoPanelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tetrisScoresLabelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tetrisGameLevelLabelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tetrisLinesAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tetrisLinesAmountLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(tetrisScoresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(tetrisNextTetrominoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(40, 40, 40)
                                                                .addComponent(tetrisGameLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(tetrisPlayFieldPanelMultiplayerOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(tetrisScoresLabelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(tetrisNextTetrominoPanelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(40, 40, 40)
                                                                .addComponent(tetrisGameLevelLabelOpponent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(tetrisPlayFieldPanelMultiplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(mainMenuLabel)
                                                .addGap(138, 138, 138)
                                                .addComponent(tetrisStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tetrisPlayerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisPlayerNameLabelOpponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tetrisVSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }

    private void mainMenuLabelMouseEntered() {
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMouseExited() {
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMousePressed() {
        mainMenuLabelMouseExited();
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.goMenuPanel();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage bufferedImage = null;
        if (backgroundType == BACKGROUND) {
            bufferedImage = backgroundImage;
            setGameLabelsColor(Color.WHITE);
        } else if (backgroundType == BACKGROUND2) {
            bufferedImage = backgroundImage2;
            setGameLabelsColor(Color.BLACK);
        } else if (backgroundType == BACKGROUND3) {
            bufferedImage = backgroundImage3;
            setGameLabelsColor(Color.BLACK);
        } else if (backgroundType == BACKGROUND4) {
            bufferedImage = backgroundImage4;
            setGameLabelsColor(Color.BLACK);
        } else if (backgroundType == BACKGROUND5) {
            bufferedImage = backgroundImage5;
            setGameLabelsColor(Color.WHITE);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                g.drawImage(bufferedImage, j * 150, i * 150, this);
            }
        }

    }

    private void setGameLabelsColor(Color color) {

        tetrisGameLevelLabel.setForeground(color);
        tetrisLinesAmountLabel.setForeground(color);
        tetrisLinesAmountLabelOpponent.setForeground(color);
        tetrisGameLevelLabelOpponent.setForeground(color);

    }

}
