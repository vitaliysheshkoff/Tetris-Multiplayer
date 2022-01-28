//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanel;
    public TetrisNextTetrominoPanel tetrisNextTetrominoPanelOpponent;
    public TetrisPlayFieldPanelMultiplayer tetrisPlayFieldPanelMultiplayer;
    public TetrisPlayFieldPanelMultiplayerOpponent tetrisPlayFieldPanelMultiplayerOpponent;
    public JLabel tetrisPlayerNameLabel;
    public JLabel tetrisPlayerNameLabelOpponent;
    public volatile JLabel tetrisScoresLabel;
    public JLabel tetrisScoresLabelOpponent;
    public TetrisStatisticsPanel tetrisStatisticsPanel;
    public JLabel tetrisVSLabel;
    public byte backgroundType = 2;

    public TetrisPanelMultiplayer() {
        this.initComponents();
        this.setBackground(Color.BLACK);
    }

    private void initComponents() {
        game.panels.tetris.multiplayer.battlepanel.TetrisPanelMultiplayer.BackgroundPanel backgroundPanel = new game.panels.tetris.multiplayer.battlepanel.TetrisPanelMultiplayer.BackgroundPanel();
        MyButton mainMenuButton = new MyButton("main menu");
        this.tetrisVSLabel = new JLabel();
        this.tetrisScoresLabel = new JLabel();
        this.tetrisGameLevelLabel = new JLabel();
        this.tetrisScoresLabelOpponent = new JLabel();
        this.tetrisGameLevelLabelOpponent = new JLabel();
        this.tetrisLinesAmountLabelOpponent = new JLabel();
        this.tetrisPlayerNameLabel = new JLabel();
        this.tetrisPlayerNameLabelOpponent = new JLabel();
        this.tetrisLinesAmountLabel = new JLabel();
        this.tetrisPlayFieldPanelMultiplayerOpponent = new TetrisPlayFieldPanelMultiplayerOpponent();
        this.tetrisNextTetrominoPanelOpponent = new TetrisNextTetrominoPanel();
        this.tetrisNextTetrominoPanel = new TetrisNextTetrominoPanel();
        this.tetrisStatisticsPanel = new TetrisStatisticsPanel();
        this.tetrisPlayFieldPanelMultiplayer = new TetrisPlayFieldPanelMultiplayer();
        this.tetrisVSLabel.setForeground(Color.WHITE);
        this.tetrisGameLevelLabel.setForeground(Color.WHITE);
        this.tetrisLinesAmountLabel.setForeground(Color.WHITE);
        this.tetrisLinesAmountLabelOpponent.setForeground(Color.WHITE);
        this.tetrisGameLevelLabelOpponent.setForeground(Color.WHITE);
        this.tetrisPlayerNameLabel.setForeground(Color.WHITE);
        this.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        GroupLayout nextPanelLayout = new GroupLayout(this.tetrisNextTetrominoPanelOpponent);
        this.tetrisNextTetrominoPanelOpponent.setLayout(nextPanelLayout);
        nextPanelLayout.setHorizontalGroup(nextPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        nextPanelLayout.setVerticalGroup(nextPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        GroupLayout tetrisPanelLayout = new GroupLayout(this.tetrisPlayFieldPanelMultiplayer);
        this.tetrisPlayFieldPanelMultiplayer.setLayout(tetrisPanelLayout);
        tetrisPanelLayout.setHorizontalGroup(tetrisPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        tetrisPanelLayout.setVerticalGroup(tetrisPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 0, 32767));
        GroupLayout statisticPanelLayout = new GroupLayout(this.tetrisStatisticsPanel);
        this.tetrisStatisticsPanel.setLayout(statisticPanelLayout);
        statisticPanelLayout.setHorizontalGroup(statisticPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        statisticPanelLayout.setVerticalGroup(statisticPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        this.tetrisLinesAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisLinesAmountLabel.setText("lines amount");
        this.tetrisLinesAmountLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisScoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisScoresLabel.setText("<html><body style='text-align: center'>Score:<br>0");
        this.tetrisScoresLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisGameLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>0");
        this.tetrisGameLevelLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        GroupLayout tetrisPanelOpponentLayout = new GroupLayout(this.tetrisPlayFieldPanelMultiplayerOpponent);
        this.tetrisPlayFieldPanelMultiplayerOpponent.setLayout(tetrisPanelOpponentLayout);
        tetrisPanelOpponentLayout.setHorizontalGroup(tetrisPanelOpponentLayout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        tetrisPanelOpponentLayout.setVerticalGroup(tetrisPanelOpponentLayout.createParallelGroup(Alignment.LEADING).addGap(0, 0, 32767));
        this.tetrisLinesAmountLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisLinesAmountLabelOpponent.setText("lines: 0");
        this.tetrisLinesAmountLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisScoresLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisScoresLabelOpponent.setText("<html><body style='text-align: center'>Score:<br>0");
        this.tetrisScoresLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        GroupLayout nextPanel1Layout = new GroupLayout(this.tetrisNextTetrominoPanel);
        this.tetrisNextTetrominoPanel.setLayout(nextPanel1Layout);
        nextPanel1Layout.setHorizontalGroup(nextPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 0, 32767));
        nextPanel1Layout.setVerticalGroup(nextPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 50, 32767));
        this.tetrisGameLevelLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>0");
        this.tetrisGameLevelLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisPlayerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisPlayerNameLabel.setText("player name");
        this.tetrisPlayerNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisPlayerNameLabelOpponent.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisPlayerNameLabelOpponent.setText("opponent name");
        this.tetrisPlayerNameLabelOpponent.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tetrisVSLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tetrisVSLabel.setText("VS");
        this.tetrisVSLabel.setHorizontalTextPosition(SwingConstants.CENTER);
       // mainMenuButton.addMouseListener(new 1(this));
        mainMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1)
                    mainMenuLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               mainMenuLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainMenuLabelMouseExited();
            }
        });
        this.tetrisLinesAmountLabel.setFont(Main.FONT);
        this.tetrisGameLevelLabel.setFont(Main.FONT);
        this.tetrisScoresLabel.setFont(Main.FONT);
        this.tetrisPlayerNameLabel.setFont(Main.FONT);
        this.tetrisVSLabel.setFont(Main.FONT);
        this.tetrisLinesAmountLabelOpponent.setFont(Main.FONT);
        this.tetrisGameLevelLabelOpponent.setFont(Main.FONT);
        this.tetrisScoresLabelOpponent.setFont(Main.FONT);
        this.tetrisPlayerNameLabelOpponent.setFont(Main.FONT);
        GroupLayout jPanel1Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tetrisStatisticsPanel, -1, -1, 32767).addComponent(mainMenuButton, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, 18, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tetrisPlayerNameLabel, -1, -1, 32767).addComponent(this.tetrisLinesAmountLabel, -1, -1, 32767).addComponent(this.tetrisPlayFieldPanelMultiplayer, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, 30, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tetrisNextTetrominoPanel, Alignment.TRAILING, -1, -1, 32767).addComponent(this.tetrisGameLevelLabel, Alignment.TRAILING, -1, -1, 32767).addComponent(this.tetrisScoresLabel, Alignment.TRAILING, -1, 100, 32767).addComponent(this.tetrisVSLabel, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, 35, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tetrisLinesAmountLabelOpponent, -1, -1, 32767).addComponent(this.tetrisPlayFieldPanelMultiplayerOpponent, -1, -1, 32767).addComponent(this.tetrisPlayerNameLabelOpponent, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, 30, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tetrisScoresLabelOpponent, -1, -1, 32767).addComponent(this.tetrisNextTetrominoPanelOpponent, -1, -1, 32767).addComponent(this.tetrisGameLevelLabelOpponent, -1, -1, 32767)).addContainerGap(97, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(12, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tetrisLinesAmountLabel).addComponent(this.tetrisLinesAmountLabelOpponent)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.tetrisScoresLabel).addGap(36, 36, 36).addComponent(this.tetrisNextTetrominoPanel, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.tetrisGameLevelLabel)).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(mainMenuButton).addPreferredGap(ComponentPlacement.RELATED, 57, 32767).addComponent(this.tetrisStatisticsPanel, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.tetrisScoresLabelOpponent).addGap(36, 36, 36).addComponent(this.tetrisNextTetrominoPanelOpponent, -2, -1, -2).addGap(6, 6, 6).addComponent(this.tetrisGameLevelLabelOpponent)).addComponent(this.tetrisPlayFieldPanelMultiplayerOpponent, -1, -1, 32767).addComponent(this.tetrisPlayFieldPanelMultiplayer, -1, -1, 32767)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tetrisPlayerNameLabel).addComponent(this.tetrisPlayerNameLabelOpponent).addComponent(this.tetrisVSLabel)).addContainerGap(44, 32767)));
        this.tetrisLinesAmountLabel.getAccessibleContext().setAccessibleName("");
        this.add(backgroundPanel);
    }

    private void mainMenuLabelMouseEntered() {
    }

    private void mainMenuLabelMouseExited() {
    }

    private void mainMenuLabelMousePressed() {
        this.mainMenuLabelMouseExited();
        if (!Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.blockMainMenuButton) {
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.goMenuPanel();
        }

    }

    class BackgroundPanel extends JPanel {
        BackgroundPanel() {
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
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

            for(int i = 0; (double)i < Main.monitorHeight / (double) Objects.requireNonNull(bufferedImage).getHeight() + 1.0D; ++i) {
                for(int j = 0; (double)j < Main.monitorWidth / (double)bufferedImage.getWidth() + 1.0D; ++j) {
                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }

        }
    }

}
