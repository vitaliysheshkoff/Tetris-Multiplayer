package game.panels.menu;

import game.dialogs.NewGameDialog;
import game.dialogs.YesNoDialog;
import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.helperclasses.buttons.MyButton;
import game.panels.tetris.infopanels.TetrisNextTetrominoPanel;
import game.start.Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MenuPanel extends JPanel implements KeyListener {

    private static final int RESUME_GAME = 0;
    private static final int NEW_GAME = 1;
    private static final int BATTLE_GAME = 2;
    private static final int LEADERBOARD = 3;
    private static final int OPTIONS = 4;
    private static final int QUIT_GAME = 5;

    private int buttonController = RESUME_GAME;

    private boolean currentButtonSelected = true;

    private MyButton leaderboardButton;
    private MyButton newGameButton;
    private MyButton optionsButton;
    private MyButton quitGameButton;
    private MyButton resumeGameButton;
    private MyButton multiplayerGameButton;

    public MenuPanel() {
        setBackground(Color.BLACK);
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

        resumeGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    resumeGameLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resumeGameLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeGameLabelMouseExited();
            }
        });

        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    newGameLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newGameLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGameLabelMouseExited();
            }
        });

        leaderboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    leaderboardLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leaderboardLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leaderboardLabelMouseExited();
            }
        });

        optionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    optionsLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                optionsLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                optionsLabelMouseExited();
            }
        });

        quitGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    quitGameLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quiteGameLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quiteGameLabelMouseExited();
            }
        });

        multiplayerGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    battleGameLabelMousePressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                battleGameLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                battleGameLabelMouseExited();
            }
        });

        TetrisLabelPanel tetrisLabelPanel = new TetrisLabelPanel();

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel();

        resumeGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        resumeGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        multiplayerGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        multiplayerGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        leaderboardButton.setHorizontalAlignment(SwingConstants.CENTER);
        leaderboardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        optionsButton.setHorizontalAlignment(SwingConstants.CENTER);
        optionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        quitGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitGameButton.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout buttonsPanelLayout = new GroupLayout(buttonsPanel);

        buttonsPanel.setLayout(buttonsPanelLayout);

        buttonsPanelLayout.setHorizontalGroup(buttonsPanelLayout
                .createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addGroup(buttonsPanelLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(optionsButton, -1, -2, 32767)
                                .addComponent(quitGameButton, -1, -2, 32767)
                                .addComponent(multiplayerGameButton, -1, -2, 32767)
                                .addComponent(leaderboardButton, -1, -2, 32767)
                                .addComponent(resumeGameButton, -1, -2, 32767)
                                .addComponent(newGameButton, -1, -2, 32767))
                        .addContainerGap(-1, 32767)));

        buttonsPanelLayout.setVerticalGroup(buttonsPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addComponent(resumeGameButton)
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(newGameButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(multiplayerGameButton)
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(leaderboardButton)
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(optionsButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(quitGameButton).addContainerGap()));

        GroupLayout tetrisLabelPanelLayout = new GroupLayout(tetrisLabelPanel);

        tetrisLabelPanel.setLayout(tetrisLabelPanelLayout);

        tetrisLabelPanelLayout.setHorizontalGroup(tetrisLabelPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 254, 32767));
        tetrisLabelPanelLayout.setVerticalGroup(tetrisLabelPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, 32767));

        GroupLayout jPanel1Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(buttonsPanel, -1, -1, 32767).addContainerGap(-1, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(69, 32767).addComponent(tetrisLabelPanel, -2, -1, -2).addContainerGap(69, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(tetrisLabelPanel, -2, -1, -2).addGap(18, 18, 18).addComponent(buttonsPanel, -1, -1, -2).addContainerGap(25, 32767)));
        add(backgroundPanel);
    }

    private void resumeGameLabelMousePressed() {
        if (noResumeFile()) {
            Main.audioPlayer.playClick();
            goTetrisPanel();
            Main.tetrisPanel.tetrisPlayFieldPanel.resumeGame();
        }
    }

    private void newGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        if (noResumeFile()) {
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
        goMultiplayer();
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
        return file.length() != 0L;
    }

    private void showQuiteDialog() {
        new YesNoDialog(Main.tetrisFrame, "", "<html><body style='text-align: center'>You really want to <br/> leave the game?</html>\"", true);
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
        Main.optionPanel.scrollPane = new JScrollPane(Main.optionPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

    public void goMultiplayer() {
        Main.tetrisFrame.remove(Main.menuPanel);
        Main.tetrisFrame.add(Main.multiplayerPanel2);
        Main.multiplayerPanel2.switchLabelMousePressed();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.multiplayerPanel2.requestFocusInWindow();
    }

    public void keyTyped(KeyEvent e) {
    }

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
        if (buttonController != QUIT_GAME) {
            ++buttonController;
        } else {
            buttonController = RESUME_GAME;
        }

        selectCurrentButton();
    }

    private void pressUp() {
        System.out.println("UP");
        Main.audioPlayer.playClick();
        unselectCurrentButton();
        if (buttonController != RESUME_GAME) {
            --buttonController;
        } else {
            buttonController = QUIT_GAME;
        }

        selectCurrentButton();
    }

    private void pressEnter() {
        if (currentButtonSelected) {
            if (buttonController == RESUME_GAME) {
                resumeGameLabelMousePressed();
            } else if (buttonController == NEW_GAME) {
                newGameLabelMousePressed();
            } else if (buttonController == BATTLE_GAME) {
                battleGameLabelMousePressed();
            } else if (buttonController == LEADERBOARD) {
                leaderboardLabelMousePressed();
            } else if (buttonController == OPTIONS) {
                optionsLabelMousePressed();
            } else {
                quitGameLabelMousePressed();
            }
        }

    }

    public void selectCurrentButton() {
        if (buttonController == RESUME_GAME) {
            resumeGameLabelMouseEntered();
        } else if (buttonController == NEW_GAME) {
            newGameLabelMouseEntered();
        } else if (buttonController == BATTLE_GAME) {
            battleGameLabelMouseEntered();
        } else if (buttonController == LEADERBOARD) {
            leaderboardLabelMouseEntered();
        } else if (buttonController == OPTIONS) {
            optionsLabelMouseEntered();
        } else {
            quiteGameLabelMouseEntered();
        }

    }

    private void unselectCurrentButton() {
        if (buttonController == RESUME_GAME) {
            resumeGameLabelMouseExited();
        } else if (buttonController == NEW_GAME) {
            newGameLabelMouseExited();
        } else if (buttonController == BATTLE_GAME) {
            battleGameLabelMouseExited();
        } else if (buttonController == LEADERBOARD) {
            leaderboardLabelMouseExited();
        } else if (buttonController == OPTIONS) {
            optionsLabelMouseExited();
        } else if (buttonController == QUIT_GAME) {
            quiteGameLabelMouseExited();
        } else {
            battleGameLabelMouseExited();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    static class TetrisLabelPanel extends JPanel {

        int w;
        int h;
        int s;
        int radius;

        Dimension d;
        Container c;

        public TetrisLabelPanel() {
            setOpaque(false);
        }

        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
                w = (int) d.getWidth();
                h = (int) d.getHeight();
                s = Math.min(w, h);
                return new Dimension((int) ((double) s / 1.2D), s / 4);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintTetrisTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 4;
            PaintStaticLetters.paintLetterT(g2d, startX, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 5 * square_radius + space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterT(g2d, startX + 9 * square_radius + 2 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 14 * square_radius + 3 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterI(g2d, startX + 18 * square_radius + 4 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterS(g2d, startX + 21 * square_radius + 5 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            radius = getWidth() / 30;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintTetrisTitle(g2d, radius, radius, radius);
        }
    }

    static class BackgroundPanel extends JPanel {

        int radius;
        int width;
        int height;

        BufferedImage bufferedImage = null;

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            width = getWidth();
            height = getHeight();
            if (width < height) {
                radius = width / 37;
            } else {
                radius = height / 37;
            }

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            if (Main.tetrisPanel.backgroundType == 0) {
                bufferedImage = Main.tetrisPanel.backgroundImage;
            } else if (Main.tetrisPanel.backgroundType == 1) {
                bufferedImage = Main.tetrisPanel.backgroundImage2;
            } else if (Main.tetrisPanel.backgroundType == 2) {
                bufferedImage = Main.tetrisPanel.backgroundImage3;
            } else if (Main.tetrisPanel.backgroundType == 3) {
                bufferedImage = Main.tetrisPanel.backgroundImage4;
            } else if (Main.tetrisPanel.backgroundType == 4) {
                bufferedImage = Main.tetrisPanel.backgroundImage5;
            }

            for (int i = 0; (double) i < Main.monitorHeight / (double) bufferedImage.getHeight() + 1.0D; ++i) {
                for (int j = 0; (double) j < Main.monitorWidth / (double) bufferedImage.getWidth() + 1.0D; ++j) {
                    g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }

            paintBackgroundTetrominoes(g2d, width, height, radius);

            g2d.setColor(Color.YELLOW);
            g2d.setFont(Main.FONT);
        }

        private void paintBackgroundTetrominoes(Graphics2D g2d, int width, int height, int radius) {
            TetrisNextTetrominoPanel.paintJ(g2d, radius * 8, (int) ((double) height / 2.5D), radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintO(g2d, width * 9 / 10, radius, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintZ(g2d, width * 8 / 10, height * 8 / 10, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintS(g2d, radius * 5, height - radius, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintIHorizontal(g2d, width / 20, height / 10, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintL(g2d, width - radius * 4, height / 2, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintT(g2d, radius * 6, (int) ((double) height * 2.5D / 4.0D), radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }
    }


    static class ButtonsPanel extends JPanel {

        int w;
        int h;
        int s;

        Dimension d;
        Container c;

        public ButtonsPanel() {
            setOpaque(false);
        }

        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
                w = (int) d.getWidth();
                h = (int) d.getHeight();
                s = Math.min(w, h);
                return new Dimension(s / 16, s / 3);
            } else {
                return new Dimension(10, 20);
            }
        }
    }

}
