package game.panels.menu;

import game.dialogs.NewGameDialog;
import game.dialogs.YesNoDialog;
import game.helperclasses.MyButton;
import game.helperclasses.PaintStaticLetters;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.start.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import static game.panels.tetris.TetrisPanel.*;

public class MenuPanel extends JPanel implements KeyListener {

    private MyButton leaderboardButton;
    private MyButton newGameButton;
    private MyButton optionsButton;
    private MyButton quitGameButton;
    private MyButton resumeGameButton;
    private MyButton multiplayerGameButton;

    private static final int RESUME_GAME = 0, NEW_GAME = 1, BATTLE_GAME = 2, LEADERBOARD = 3, OPTIONS = 4, QUIT_GAME = 5;
    private int buttonController = RESUME_GAME;
    private boolean currentButtonSelected = true;

    public MenuPanel() {
        initComponents();
        addKeyListener(this);
        setFocusable(true);
    }

    private void initComponents() {

        resumeGameButton = new MyButton("resume game");
        newGameButton = new MyButton("new game");
        leaderboardButton = new MyButton("leaderboard");
        optionsButton = new MyButton("options");
        quitGameButton = new MyButton("quit game");
        multiplayerGameButton = new MyButton("multiplayer");

        TetrisLabelPanel tetrisLabelPanel = new TetrisLabelPanel();
        setBackground(Color.BLACK);

        resumeGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        leaderboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        optionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        quitGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quiteGameLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                quiteGameLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    quitGameLabelMousePressed();
                }
            }
        });

        multiplayerGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        BackgroundPanel jPanel1 = new BackgroundPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel();

        resumeGameButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resumeGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        newGameButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        multiplayerGameButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        multiplayerGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        leaderboardButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leaderboardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        optionsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        quitGameButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
                buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(optionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(quitGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(multiplayerGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(leaderboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(resumeGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
                buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buttonsPanelLayout.createSequentialGroup()
                                .addComponent(resumeGameButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newGameButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(multiplayerGameButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(leaderboardButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(optionsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quitGameButton)
                                .addContainerGap())
        );

        javax.swing.GroupLayout tetrisLabelPanelLayout = new javax.swing.GroupLayout(tetrisLabelPanel);
        tetrisLabelPanel.setLayout(tetrisLabelPanelLayout);
        tetrisLabelPanelLayout.setHorizontalGroup(
                tetrisLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 254, Short.MAX_VALUE)
        );
        tetrisLabelPanelLayout.setVerticalGroup(
                tetrisLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(69, Short.MAX_VALUE)
                                .addComponent(tetrisLabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tetrisLabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        add(jPanel1);
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
            goTetrisPanel();
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

    private void quitGameLabelMousePressed() {
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
        resumeGameButton.selectButton();
    }

    private void newGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NEW_GAME;
        newGameButton.selectButton();
    }

    private void leaderboardLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = LEADERBOARD;
        leaderboardButton.selectButton();
    }

    private void optionsLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = OPTIONS;
        optionsButton.selectButton();
    }

    private void quiteGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = QUIT_GAME;
        quitGameButton.selectButton();
    }

    private void battleGameLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = BATTLE_GAME;
        multiplayerGameButton.selectButton();
    }

    private void resumeGameLabelMouseExited() {
        currentButtonSelected = false;
        resumeGameButton.unselectButton();
    }

    private void newGameLabelMouseExited() {
        currentButtonSelected = false;
        newGameButton.unselectButton();
    }

    private void leaderboardLabelMouseExited() {
        currentButtonSelected = false;
        leaderboardButton.unselectButton();
    }

    private void optionsLabelMouseExited() {
        currentButtonSelected = false;
        optionsButton.unselectButton();
    }

    private void quiteGameLabelMouseExited() {
        currentButtonSelected = false;
        quitGameButton.unselectButton();
    }

    private void battleGameLabelMouseExited() {
        currentButtonSelected = false;
        multiplayerGameButton.unselectButton();
    }

    private boolean noResumeFile() {
        File file = new File(System.getProperty("user.dir"), "resume.dat");
        return file.length() == 0;
    }

    private void showQuiteDialog() {
        new YesNoDialog(Main.tetrisFrame,"",
                "<html><body style='text-align: center'>You really want to <br/> leave the game?</html>\"", true);

    }

    public void goTetrisPanel() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.tetrisPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
    }

    private void goOptionPanel() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.optionPanel.scrollPane = new JScrollPane(Main.optionPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Main.optionPanel.scrollPane.setBorder(null);
        Main.tetrisFrame.getContentPane().add(Main.optionPanel.scrollPane);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.optionPanel.requestFocusInWindow();
        Main.optionPanel.selectCurrentButton();



    }

    private void goLeaderboard() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
    }

    private void goTetrisMultiplayerPanel() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.multiplayerPanel2);
        Main.multiplayerPanel2.switchLabelMousePressed();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.multiplayerPanel2.requestFocusInWindow();
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
                quitGameLabelMousePressed();
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

    static class BackgroundPanel extends JPanel {

        int radius;
        int width;
        int height;

        BufferedImage bufferedImage = null;

        public BackgroundPanel() {

           // setBackground(new Color(68, 148, 74));
        }

        @Override
        protected void paintComponent(Graphics g) {

            width = getWidth();
            height = getHeight();

            if (width < height)
                radius = width / 37;
            else
                radius = height / 37;

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
         //   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            if (Main.tetrisPanel.backgroundType == BACKGROUND) {
                bufferedImage = backgroundImage;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND2) {
                bufferedImage = backgroundImage2;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND3) {
                bufferedImage = backgroundImage3;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND4) {
                bufferedImage = backgroundImage4;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND5) {
                bufferedImage = backgroundImage5;

            }

            for (int i = 0; i < Main.monitorHeight / bufferedImage.getHeight() + 1; i++) {
                for (int j = 0; j < Main.monitorWidth / bufferedImage.getWidth() + 1; j++) {

                    g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }

            paintBackgroundTetrominoes(g2d, width, height, radius);

            g2d.setColor(Color.YELLOW);
            g2d.setFont(Main.FONT);
        }

        private void paintBackgroundTetrominoes(Graphics2D g2d, int width, int height, int radius) {
            TetrisNextTetrominoPanel.paintJ(g2d, radius * 8, (int) (height / 2.5), radius);
            TetrisNextTetrominoPanel.paintO(g2d, width * 9 / 10, radius, radius);
            TetrisNextTetrominoPanel.paintZ(g2d, width * 8 / 10, height * 8 / 10, radius);
            TetrisNextTetrominoPanel.paintS(g2d, radius * 5, height - radius, radius);
            TetrisNextTetrominoPanel.paintIHorizontal(g2d, width / 20, height / 10, radius);
            TetrisNextTetrominoPanel.paintL(g2d, width - radius * 4, height / 2, radius);
            TetrisNextTetrominoPanel.paintT(g2d, radius * 6, (int) (height * 2.5 / 4), radius);
        }
    }

    static class ButtonsPanel extends JPanel {

        public ButtonsPanel() {
            setOpaque(false);
        }

        int w;
        int h;
        int s;

        Dimension d;
        Container c;

        @Override
        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
            } else {
                return new Dimension(10, 20);
            }

            w = (int) d.getWidth();
            h = (int) d.getHeight();
            s = (Math.min(w, h));

            return new Dimension(s / 16, (s) / 3);
        }
    }

    static class TetrisLabelPanel extends JPanel {

        public TetrisLabelPanel() {
            setOpaque(false);
        }

        int w;
        int h;
        int s;

        Dimension d;
        Container c;

        @Override
        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
            } else {
                return new Dimension(10, 20);
            }

            w = (int) d.getWidth();
            h = (int) d.getHeight();
            s = (Math.min(w, h));

            return new Dimension((int) (s / 1.2), s / 4);
        }

        private void paintTetrisTitle(Graphics2D g2d, int startX, int startY, int square_radius) {

            int space = square_radius / 4;

            //T
            PaintStaticLetters.paintLetterT(g2d, startX, startY, radius);
            //E
            PaintStaticLetters.paintLetterE(g2d, startX + 5 * square_radius + space, startY, square_radius);
            //T
            PaintStaticLetters.paintLetterT(g2d, startX + 9 * square_radius + 2 * space, startY, square_radius);
            //R
            PaintStaticLetters.paintLetterR(g2d, startX + 14 * square_radius + 3 * space, startY, square_radius);
            //I
            PaintStaticLetters.paintLetterI(g2d, startX + 18 * square_radius + 4 * space, startY, square_radius);
            //S
            PaintStaticLetters.paintLetterS(g2d, startX + 21 * square_radius + 5 * space, startY, square_radius);
        }

        int radius;

        @Override
        protected void paintComponent(Graphics g) {

            radius = getWidth() / 30;

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            paintTetrisTitle(g2d, radius, radius, radius);
        }
    }
}