//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

public class MenuPanel extends JPanel implements KeyListener {
    private MyButton leaderboardButton;
    private MyButton newGameButton;
    private MyButton optionsButton;
    private MyButton quitGameButton;
    private MyButton resumeGameButton;
    private MyButton multiplayerGameButton;
    private static final int RESUME_GAME = 0;
    private static final int NEW_GAME = 1;
    private static final int BATTLE_GAME = 2;
    private static final int LEADERBOARD = 3;
    private static final int OPTIONS = 4;
    private static final int QUIT_GAME = 5;
    private int buttonController = 0;
    private boolean currentButtonSelected = true;

    public MenuPanel() {
        this.initComponents();
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    private void initComponents() {
        this.resumeGameButton = new MyButton("resume game");
        this.newGameButton = new MyButton("new game");
        this.leaderboardButton = new MyButton("leaderboard");
        this.optionsButton = new MyButton("options");
        this.quitGameButton = new MyButton("quit game");
        this.multiplayerGameButton = new MyButton("multiplayer");
        game.panels.menu.MenuPanel.TetrisLabelPanel tetrisLabelPanel = new game.panels.menu.MenuPanel.TetrisLabelPanel();
        this.setBackground(Color.BLACK);
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
        this.setLayout(new BoxLayout(this, 2));
        game.panels.menu.MenuPanel.BackgroundPanel backgroundPanel = new game.panels.menu.MenuPanel.BackgroundPanel();
        game.panels.menu.MenuPanel.ButtonsPanel buttonsPanel = new game.panels.menu.MenuPanel.ButtonsPanel();
        this.resumeGameButton.setHorizontalAlignment(0);
        this.resumeGameButton.setHorizontalTextPosition(0);
        this.newGameButton.setHorizontalAlignment(0);
        this.newGameButton.setHorizontalTextPosition(0);
        this.multiplayerGameButton.setHorizontalAlignment(0);
        this.multiplayerGameButton.setHorizontalTextPosition(0);
        this.leaderboardButton.setHorizontalAlignment(0);
        this.leaderboardButton.setHorizontalTextPosition(0);
        this.optionsButton.setHorizontalAlignment(0);
        this.optionsButton.setHorizontalTextPosition(0);
        this.quitGameButton.setHorizontalAlignment(0);
        this.quitGameButton.setHorizontalTextPosition(0);
        GroupLayout buttonsPanelLayout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(buttonsPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(buttonsPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.optionsButton, -1, -2, 32767).addComponent(this.quitGameButton, -1, -2, 32767).addComponent(this.multiplayerGameButton, -1, -2, 32767).addComponent(this.leaderboardButton, -1, -2, 32767).addComponent(this.resumeGameButton, -1, -2, 32767).addComponent(this.newGameButton, -1, -2, 32767)).addContainerGap(-1, 32767)));
        buttonsPanelLayout.setVerticalGroup(buttonsPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(buttonsPanelLayout.createSequentialGroup().addComponent(this.resumeGameButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.newGameButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.multiplayerGameButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.leaderboardButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.optionsButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.quitGameButton).addContainerGap()));
        GroupLayout tetrisLabelPanelLayout = new GroupLayout(tetrisLabelPanel);
        tetrisLabelPanel.setLayout(tetrisLabelPanelLayout);
        tetrisLabelPanelLayout.setHorizontalGroup(tetrisLabelPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 254, 32767));
        tetrisLabelPanelLayout.setVerticalGroup(tetrisLabelPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 100, 32767));
        GroupLayout jPanel1Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(buttonsPanel, -1, -1, 32767).addContainerGap(-1, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(69, 32767).addComponent(tetrisLabelPanel, -2, -1, -2).addContainerGap(69, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(tetrisLabelPanel, -2, -1, -2).addGap(18, 18, 18).addComponent(buttonsPanel, -1, -1, -2).addContainerGap(25, 32767)));
        this.add(backgroundPanel);
    }

    private void resumeGameLabelMousePressed() {
        if (!this.noResumeFile()) {
            Main.audioPlayer.playClick();
            this.goTetrisPanel();
            Main.tetrisPanel.tetrisPlayFieldPanel.resumeGame();
        }
    }

    private void newGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        if (!this.noResumeFile()) {
            new NewGameDialog(Main.tetrisFrame, true);
            Main.audioPlayer.playClick();
        } else {
            this.goTetrisPanel();
            Main.tetrisPanel.tetrisPlayFieldPanel.startNewGame();
        }

    }

    private void leaderboardLabelMousePressed() {
        Main.audioPlayer.playClick();
        this.goLeaderboard();
    }

    private void optionsLabelMousePressed() {
        Main.audioPlayer.playClick();
        this.goOptionPanel();
    }

    private void quitGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        this.showQuiteDialog();
        Main.audioPlayer.playClick();
    }

    private void battleGameLabelMousePressed() {
        Main.audioPlayer.playClick();
        this.goMultiplayer();
    }

    private void resumeGameLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 0;
        this.resumeGameButton.selectButton();
    }

    private void newGameLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 1;
        this.newGameButton.selectButton();
    }

    private void leaderboardLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 3;
        this.leaderboardButton.selectButton();
    }

    private void optionsLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 4;
        this.optionsButton.selectButton();
    }

    private void quiteGameLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 5;
        this.quitGameButton.selectButton();
    }

    private void battleGameLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 2;
        this.multiplayerGameButton.selectButton();
    }

    private void resumeGameLabelMouseExited() {
        this.currentButtonSelected = false;
        this.resumeGameButton.unselectButton();
    }

    private void newGameLabelMouseExited() {
        this.currentButtonSelected = false;
        this.newGameButton.unselectButton();
    }

    private void leaderboardLabelMouseExited() {
        this.currentButtonSelected = false;
        this.leaderboardButton.unselectButton();
    }

    private void optionsLabelMouseExited() {
        this.currentButtonSelected = false;
        this.optionsButton.unselectButton();
    }

    private void quiteGameLabelMouseExited() {
        this.currentButtonSelected = false;
        this.quitGameButton.unselectButton();
    }

    private void battleGameLabelMouseExited() {
        this.currentButtonSelected = false;
        this.multiplayerGameButton.unselectButton();
    }

    private boolean noResumeFile() {
        File file = new File(System.getProperty("user.dir"), "resume.dat");
        return file.length() == 0L;
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
        Main.optionPanel.scrollPane = new JScrollPane(Main.optionPanel, 20, 30);
        Main.optionPanel.scrollPane.setBorder((Border) null);
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
        if (e.getKeyCode() == 40) {
            this.pressDown();
        } else if (e.getKeyCode() == 38) {
            this.pressUp();
        } else if (e.getKeyCode() == 10) {
            this.pressEnter();
        }

    }

    private void pressDown() {
        System.out.println("DOWN");
        Main.audioPlayer.playClick();
        this.unselectCurrentButton();
        if (this.buttonController != 5) {
            ++this.buttonController;
        } else {
            this.buttonController = 0;
        }

        this.selectCurrentButton();
    }

    private void pressUp() {
        System.out.println("UP");
        Main.audioPlayer.playClick();
        this.unselectCurrentButton();
        if (this.buttonController != 0) {
            --this.buttonController;
        } else {
            this.buttonController = 5;
        }

        this.selectCurrentButton();
    }

    private void pressEnter() {
        if (this.currentButtonSelected) {
            if (this.buttonController == 0) {
                this.resumeGameLabelMousePressed();
            } else if (this.buttonController == 1) {
                this.newGameLabelMousePressed();
            } else if (this.buttonController == 2) {
                this.battleGameLabelMousePressed();
            } else if (this.buttonController == 3) {
                this.leaderboardLabelMousePressed();
            } else if (this.buttonController == 4) {
                this.optionsLabelMousePressed();
            } else {
                this.quitGameLabelMousePressed();
            }
        }

    }

    public void selectCurrentButton() {
        if (this.buttonController == 0) {
            this.resumeGameLabelMouseEntered();
        } else if (this.buttonController == 1) {
            this.newGameLabelMouseEntered();
        } else if (this.buttonController == 2) {
            this.battleGameLabelMouseEntered();
        } else if (this.buttonController == 3) {
            this.leaderboardLabelMouseEntered();
        } else if (this.buttonController == 4) {
            this.optionsLabelMouseEntered();
        } else {
            this.quiteGameLabelMouseEntered();
        }

    }

    private void unselectCurrentButton() {
        if (this.buttonController == 0) {
            this.resumeGameLabelMouseExited();
        } else if (this.buttonController == 1) {
            this.newGameLabelMouseExited();
        } else if (this.buttonController == 2) {
            this.battleGameLabelMouseExited();
        } else if (this.buttonController == 3) {
            this.leaderboardLabelMouseExited();
        } else if (this.buttonController == 4) {
            this.optionsLabelMouseExited();
        } else if (this.buttonController == 5) {
            this.quiteGameLabelMouseExited();
        } else {
            this.battleGameLabelMouseExited();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    class TetrisLabelPanel extends JPanel {
        int w;
        int h;
        int s;
        Dimension d;
        Container c;
        int radius;

        public TetrisLabelPanel() {
            this.setOpaque(false);
        }

        public Dimension getPreferredSize() {
            this.d = super.getPreferredSize();
            this.c = this.getParent();
            if (this.c != null) {
                this.d = this.c.getSize();
                this.w = (int) this.d.getWidth();
                this.h = (int) this.d.getHeight();
                this.s = Math.min(this.w, this.h);
                return new Dimension((int) ((double) this.s / 1.2D), this.s / 4);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintTetrisTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 4;
            PaintStaticLetters.paintLetterT(g2d, startX, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 5 * square_radius + space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterT(g2d, startX + 9 * square_radius + 2 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 14 * square_radius + 3 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterI(g2d, startX + 18 * square_radius + 4 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterS(g2d, startX + 21 * square_radius + 5 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        protected void paintComponent(Graphics g) {
            this.radius = this.getWidth() / 30;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.paintTetrisTitle(g2d, this.radius, this.radius, this.radius);
        }
    }

    class BackgroundPanel extends JPanel {
        int radius;
        int width;
        int height;
        BufferedImage bufferedImage = null;

        public BackgroundPanel() {
        }

        protected void paintComponent(Graphics g) {
            this.width = this.getWidth();
            this.height = this.getHeight();
            if (this.width < this.height) {
                this.radius = this.width / 37;
            } else {
                this.radius = this.height / 37;
            }

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
            if (Main.tetrisPanel.backgroundType == 0) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage;
            } else if (Main.tetrisPanel.backgroundType == 1) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage2;
            } else if (Main.tetrisPanel.backgroundType == 2) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage3;
            } else if (Main.tetrisPanel.backgroundType == 3) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage4;
            } else if (Main.tetrisPanel.backgroundType == 4) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage5;
            }

            for (int i = 0; (double) i < Main.monitorHeight / (double) this.bufferedImage.getHeight() + 1.0D; ++i) {
                for (int j = 0; (double) j < Main.monitorWidth / (double) this.bufferedImage.getWidth() + 1.0D; ++j) {
                    g2d.drawImage(this.bufferedImage, j * this.bufferedImage.getWidth(), i * this.bufferedImage.getHeight(), this);
                }
            }

            this.paintBackgroundTetrominoes(g2d, this.width, this.height, this.radius);
            g2d.setColor(Color.YELLOW);
            g2d.setFont(Main.FONT);
        }

        private void paintBackgroundTetrominoes(Graphics2D g2d, int width, int height, int radius) {
            TetrisNextTetrominoPanel.paintJ(g2d, radius * 8, (int) ((double) height / 2.5D), (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintO(g2d, width * 9 / 10, radius, (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintZ(g2d, width * 8 / 10, height * 8 / 10, (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintS(g2d, radius * 5, height - radius, (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintIHorizontal(g2d, width / 20, height / 10, (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintL(g2d, width - radius * 4, height / 2, (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            TetrisNextTetrominoPanel.paintT(g2d, radius * 6, (int) ((double) height * 2.5D / 4.0D), (double) radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }
    }


    class ButtonsPanel extends JPanel {
        int w;
        int h;
        int s;
        Dimension d;
        Container c;

        public ButtonsPanel() {
            this.setOpaque(false);
        }

        public Dimension getPreferredSize() {
            this.d = super.getPreferredSize();
            this.c = this.getParent();
            if (this.c != null) {
                this.d = this.c.getSize();
                this.w = (int) this.d.getWidth();
                this.h = (int) this.d.getHeight();
                this.s = Math.min(this.w, this.h);
                return new Dimension(this.s / 16, this.s / 3);
            } else {
                return new Dimension(10, 20);
            }
        }
    }


}
