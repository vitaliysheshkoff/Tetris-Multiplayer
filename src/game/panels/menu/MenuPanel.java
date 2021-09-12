package game.panels.menu;

import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.util.UtilityException;
import game.dialogs.NewGameDialog;
import game.dialogs.YesNoDialog;
import game.helperclasses.PaintStaticLetters;
import game.multiplayer.stun.StunTest;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Objects;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class MenuPanel extends JPanel implements KeyListener {

    private JLabel leaderboardLabel;
    private JLabel newGameLabel;
    private JLabel optionsLabel;
    private JLabel quiteGameLabel;
    private JLabel resumeGameLabel;
    private JLabel battleGameLabel;

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_RESUME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "resumeGameBlackRoundedImage.png";
    private static final String SELECTED_RESUME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "resumeGameWhiteRoundedImage.png";
    private static final String UNSELECTED_NEW_GAME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "newGameBlackRoundedImage.png";
    private static final String SELECTED_NEW_GAME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "newGameWhiteRoundedImage.png";
    private static final String UNSELECTED_LEADERBOARD_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "leaderboardBlackRoundedImage.png";
    private static final String SELECTED_LEADERBOARD_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "leaderboardWhiteRoundedImage.png";
    private static final String UNSELECTED_OPTION_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "optionsBlackRoundedImage.png";
    private static final String SELECTED_OPTION_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "optionsWhiteRoundedImage.png";
    private static final String SELECTED_QUIT_GAME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "quitGameWhiteRoundedImage.png";
    private static final String UNSELECTED_QUIT_GAME_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "quitGameBlackRoundedImage.png";
    private static final String SELECTED_MULTIPLAYER_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "multiplayerWhiteRoundedImage.png";
    private static final String UNSELECTED_MULTIPLAYER_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "multiplayerBlackRoundedImage.png";

    private static final int RESUME_GAME = 0, NEW_GAME = 1, BATTLE_GAME = 2, LEADERBOARD = 3, OPTIONS = 4, QUIT_GAME = 5;
    private int buttonController = RESUME_GAME;
    private boolean currentButtonSelected = true;

    public MenuPanel() {
        initComponents();
        selectCurrentButton();
        addKeyListener(this);
        setFocusable(true);
    }

    private void initComponents() {

        resumeGameLabel = new JLabel();
        newGameLabel = new JLabel();
        leaderboardLabel = new JLabel();
        optionsLabel = new JLabel();
        quiteGameLabel = new JLabel();
        battleGameLabel = new JLabel();

        setBackground(Color.BLACK);

        resumeGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_RESUME_BUTTON_PATH))));
        resumeGameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resumeGameLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                resumeGameLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    resumeGameLabelMousePressed();
                }
            }
        });

        newGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NEW_GAME_BUTTON_PATH))));
        newGameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newGameLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                newGameLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    newGameLabelMousePressed();
                }
            }
        });

        leaderboardLabel.setBackground(new java.awt.Color(0, 0, 0));
        leaderboardLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_LEADERBOARD_BUTTON_PATH))));
        leaderboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leaderboardLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                leaderboardLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    leaderboardLabelMousePressed();
                }
            }
        });

        optionsLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OPTION_BUTTON_PATH))));
        optionsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionsLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionsLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    optionsLabelMousePressed();
                }
            }
        });

        quiteGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_QUIT_GAME_BUTTON_PATH))));
        quiteGameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quiteGameLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                quiteGameLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    quiteGameLabelMousePressed();
                }
            }
        });

        battleGameLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MULTIPLAYER_BUTTON_PATH)))); // NOI18N
        battleGameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                battleGameLabelMouseEntered();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                battleGameLabelMouseExited();
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                battleGameLabelMousePressed();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(344, 344, 344)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(newGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resumeGameLabel)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(quiteGameLabel)
                                                        .addComponent(optionsLabel)
                                                        .addComponent(leaderboardLabel))
                                                .addComponent(battleGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(343, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(400, Short.MAX_VALUE)
                                .addComponent(resumeGameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(newGameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(battleGameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(leaderboardLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(optionsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quiteGameLabel)
                                .addGap(181, 181, 181))
        );


    }

    private void resumeGameLabelMousePressed() {
        if (noResumeFile()) return;

        Main.audioPlayer.playClick();
        goTetrisPanel();
        Main.tetrisPanel.tetrisPlayFieldPanel.resumeGame();
    }

    private void newGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        if (!noResumeFile()) {
            new NewGameDialog(Main.tetrisFrame, true);
            Main.audioPlayer.playClick();
        } else {
            /* Main.menuPanel.*/goTetrisPanel();
            Main.tetrisPanel.tetrisPlayFieldPanel.startNewGame();
        }

    }

    private void leaderboardLabelMousePressed() {
        Main.audioPlayer.playClick();
        goLeaderboard();
    }

    private void optionsLabelMousePressed() {
        Main.audioPlayer.playClick();
        goOptionPanel();
    }

    private void quiteGameLabelMousePressed() {
        quiteGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_QUIT_GAME_BUTTON_PATH))));
        Main.audioPlayer.playClick();
        showQuiteDialog();
        Main.audioPlayer.playClick();
    }

    private void battleGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        goTetrisMultiplayerPanel();
    }

    private void resumeGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = RESUME_GAME;
        resumeGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_RESUME_BUTTON_PATH))));
    }

    private void newGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NEW_GAME;
        newGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_NEW_GAME_BUTTON_PATH))));
    }

    private void leaderboardLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = LEADERBOARD;
        leaderboardLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_LEADERBOARD_BUTTON_PATH))));
    }

    private void optionsLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = OPTIONS;
        optionsLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_OPTION_BUTTON_PATH))));
    }

    private void quiteGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = QUIT_GAME;
        quiteGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_QUIT_GAME_BUTTON_PATH))));
    }

    private void battleGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = BATTLE_GAME;
        battleGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MULTIPLAYER_BUTTON_PATH))));
    }

    private void resumeGameLabelMouseExited() {
        currentButtonSelected = false;
        resumeGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_RESUME_BUTTON_PATH))));
    }

    private void newGameLabelMouseExited() {
        currentButtonSelected = false;
        newGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NEW_GAME_BUTTON_PATH))));
    }

    private void leaderboardLabelMouseExited() {
        currentButtonSelected = false;
        leaderboardLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_LEADERBOARD_BUTTON_PATH))));
    }

    private void optionsLabelMouseExited() {
        currentButtonSelected = false;
        optionsLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OPTION_BUTTON_PATH))));
    }

    private void quiteGameLabelMouseExited() {
        currentButtonSelected = false;
        quiteGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_QUIT_GAME_BUTTON_PATH))));
    }

    private void battleGameLabelMouseExited() {
        currentButtonSelected = false;
        battleGameLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MULTIPLAYER_BUTTON_PATH))));
    }

    private boolean noResumeFile() {
        File file = new File(System.getProperty("user.dir"), "resume.dat");
        return file.length() == 0;
    }

    private void showQuiteDialog() {
        new YesNoDialog(Main.tetrisFrame, "quit game", "You really want to leave the game?", true);
    }

    public void goTetrisPanel() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.tetrisPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
    }

    private void goOptionPanel() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.optionPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
        Main.optionPanel.requestFocusInWindow();
        Main.optionPanel.selectCurrentButton();
    }

    private void goLeaderboard() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
    }

    private void goTetrisMultiplayerPanel() {


        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.multiplayerPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
        Main.multiplayerPanel.requestFocusInWindow();

        try {
            MappedAddress mappedAddress = StunTest.getDynamicIp();
            Main.multiplayerPanel.ipLabel.setText(mappedAddress.getAddress() + "," + mappedAddress.getPort());
        } catch (UtilityException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        paintTetrisTitle(g2d);
        paintBackgroundTetrominoes(g2d);

    }

    private void paintBackgroundTetrominoes(Graphics2D g2d) {
        TetrisNextTetrominoPanel.paintJ(g2d, 150, 600, 25);
        TetrisNextTetrominoPanel.paintO(g2d, 700, 70, 25);
        TetrisNextTetrominoPanel.paintZ(g2d, 700, 650, 25);
        TetrisNextTetrominoPanel.paintS(g2d, 80, 850, 25);
        TetrisNextTetrominoPanel.paintIHorizontal(g2d, 150, 80, 25);
        TetrisNextTetrominoPanel.paintL(g2d, 800, 350, 25);
        TetrisNextTetrominoPanel.paintT(g2d, 450, 800, 25);
    }

    private void paintTetrisTitle(Graphics2D g2d) {
        int startX = 195, startY = 150, radius = 20, space = 3;
        //T
        PaintStaticLetters.paintLetterT(g2d, startX, startY, radius);
        //E
        PaintStaticLetters.paintLetterE(g2d, startX + 5 * radius + space, startY, radius);
        //T
        PaintStaticLetters.paintLetterT(g2d, startX + 9 * radius + 2 * space, startY, radius);
        //R
        PaintStaticLetters.paintLetterR(g2d, startX + 14 * radius + 3 * space, startY, radius);
        //I
        PaintStaticLetters.paintLetterI(g2d, startX + 18 * radius + 4 * space, startY, radius);
        //S
        PaintStaticLetters.paintLetterS(g2d, startX + 21 * radius + 5 * space, startY, radius);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pressDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            pressUp();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            pressEnter();
        }
    }

    private void pressDown() {
        System.out.println("DOWN");
        Main.audioPlayer.playClick();

        unselectCurrentButton();

        if (buttonController != QUIT_GAME)
            buttonController++;
        else
            buttonController = RESUME_GAME;

        selectCurrentButton();
    }

    private void pressUp() {
        System.out.println("UP");
        Main.audioPlayer.playClick();

        unselectCurrentButton();

        if (buttonController != RESUME_GAME)
            buttonController--;
        else
            buttonController = QUIT_GAME;

        selectCurrentButton();
    }

    private void pressEnter() {
        if (currentButtonSelected) {
            if (buttonController == RESUME_GAME)
                resumeGameLabelMousePressed();
            else if (buttonController == NEW_GAME)
                newGameLabelMousePressed();
            else if (buttonController == BATTLE_GAME)
                battleGameLabelMousePressed();
            else if (buttonController == LEADERBOARD)
                leaderboardLabelMousePressed();
            else if (buttonController == OPTIONS)
                optionsLabelMousePressed();
            else
                quiteGameLabelMousePressed();
        }
    }

    public void selectCurrentButton() {
        if (buttonController == RESUME_GAME)
            resumeGameLabelMouseEntered();
        else if (buttonController == NEW_GAME)
            newGameLabelMouseEntered();
        else if (buttonController == BATTLE_GAME)
            battleGameLabelMouseEntered();
        else if (buttonController == LEADERBOARD)
            leaderboardLabelMouseEntered();
        else if (buttonController == OPTIONS)
            optionsLabelMouseEntered();
        else
            quiteGameLabelMouseEntered();
    }

    private void unselectCurrentButton() {
        if (buttonController == RESUME_GAME)
            resumeGameLabelMouseExited();
        else if (buttonController == NEW_GAME)
            newGameLabelMouseExited();
        else if (buttonController == BATTLE_GAME)
            battleGameLabelMouseExited();
        else if (buttonController == LEADERBOARD)
            leaderboardLabelMouseExited();
        else if (buttonController == OPTIONS)
            optionsLabelMouseExited();
        else if (buttonController == QUIT_GAME)
            quiteGameLabelMouseExited();
        else
            battleGameLabelMouseExited();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}