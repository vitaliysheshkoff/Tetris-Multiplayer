
package game.panels.menu;

import game.dialogs.ResetLeaderboardDialog;
import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.helperclasses.buttons.MyButton;
import game.helperclasses.date.MyDate;
import game.serial.LeaderBoardSaver;
import game.start.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class LeaderBoardPanel extends JPanel implements KeyListener {
    private JTable leaderboardTable;
    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color BRONZE = new Color(205, 127, 50);
    private MyButton resetButton;
    private MyButton mainMenuButton;
    public LeaderBoardSaver[] leaderBoardSaver;
    public String newPotentialLeader = "player";
    //  private static final int MAIN_MENU = 0;
    // private static final int RESET = 1;
    private int buttonController = 0;
    private boolean currentButtonSelected = true;

    public LeaderBoardPanel() {
        this.initComponents();
        this.getLeaderBoard();
        this.initDynamicLabels();
        this.addKeyListener(this);
    }

    private void initComponents() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.mainMenuButton = new MyButton("main menu");
        this.resetButton = new MyButton("reset");

        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    mainMenuLabelMousePressed();
                }
            }
        });
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    resetLabelMousePressed();
                }
            }
        });
        game.panels.menu.LeaderBoardPanel.BackgroundPanel backgroundPanel = new BackgroundPanel();
        game.panels.menu.LeaderBoardPanel.TitlePanel titlePanel = new TitlePanel();
        JPanel jPanel1 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        leaderboardTable = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (getModel().getValueAt(row, 0).equals(1))
                    comp.setForeground(GOLD);
                else if (getModel().getValueAt(row, 0).equals(2))
                    comp.setForeground(SILVER);
                else if (getModel().getValueAt(row, 0).equals(3))
                    comp.setForeground(BRONZE);
                else
                    comp.setForeground(Color.WHITE);
                return comp;
            }
        };
        this.setLayout(new GridLayout());
        titlePanel.setPreferredSize(new Dimension(0, 120));
        GroupLayout titlePanelLayout = new GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(titlePanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 748, 32767));
        titlePanelLayout.setVerticalGroup(titlePanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 120, 32767));
        jPanel1.setOpaque(false);

        leaderboardTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "PLACE", "NICKNAME", "SCORE", "DATE"
                }
        ) {
            final Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            final boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(new Color(0, 0, 0, 100));
        cellRenderer.setForeground(Color.WHITE);
        this.leaderboardTable.setOpaque(false);
        ((DefaultTableCellRenderer) this.leaderboardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        this.leaderboardTable.setRowHeight(40);
        this.leaderboardTable.setShowHorizontalLines(true);
        this.leaderboardTable.setShowVerticalLines(true);

        for (int i = 0; i < this.leaderboardTable.getColumnCount(); ++i) {
            this.leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        this.leaderboardTable.setFocusable(false);
        this.leaderboardTable.setFillsViewportHeight(true);
        this.leaderboardTable.setMaximumSize(new Dimension(2147483647, 2147483647));
        jScrollPane1.setViewportView(this.leaderboardTable);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(40, 32767).addComponent(jScrollPane1, -1, 668, 32767).addContainerGap(40, 32767)).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.mainMenuButton).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.resetButton).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(jScrollPane1, -1, 385, 32767).addPreferredGap(ComponentPlacement.RELATED, 20, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.resetButton, Alignment.TRAILING).addComponent(this.mainMenuButton, Alignment.TRAILING)).addContainerGap()));
        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(titlePanel, -1, 748, 32767).addComponent(jPanel1, -1, -1, 32767));
        backgroundPanelLayout.setVerticalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(backgroundPanelLayout.createSequentialGroup().addGap(41, 41, 41).addComponent(titlePanel, -2, -1, -2).addGap(82, 82, 82).addComponent(jPanel1, -1, -1, 32767)));
        this.add(backgroundPanel);
    }

    public void setLeaderBoard() {
        DefaultTableModel model = (DefaultTableModel) this.leaderboardTable.getModel();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j == 0) {
                    model.setValueAt(i + 1, i, j);
                } else if (j == 1) {
                    model.setValueAt(this.leaderBoardSaver[i].getNickname(), i, j);
                } else if (j == 2) {
                    model.setValueAt(this.leaderBoardSaver[i].getScore() + "(" + this.leaderBoardSaver[i].getLevel() + "lvl)", i, j);
                } else {
                    model.setValueAt(this.leaderBoardSaver[i].getDate(), i, j);
                }
            }
        }

    }

    private void getLeaderBoard() {
        this.resetLeaderBoardArray();
        File scoreFile = new File(System.getProperty("user.dir"), "score.dat");

        try {
            if (scoreFile.length() > 0L) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "score.dat")).getAbsolutePath()));
                LeaderBoardSaver[] readScore = (LeaderBoardSaver[]) ois.readObject();
                ois.close();
                System.arraycopy(readScore, 0, this.leaderBoardSaver, 0, 16);
            }
        } catch (ClassNotFoundException | IOException var4) {
            var4.printStackTrace();
        }

    }

    public void resetLeaderBoardArray() {
        this.leaderBoardSaver = new LeaderBoardSaver[16];

        for (int i = 0; i < 16; ++i) {
            this.leaderBoardSaver[i] = new LeaderBoardSaver();
        }

    }

    public void saveLeaderBoardAfterGameOver(boolean multiplayerGame) {
        LeaderBoardSaver newScore;
        if (!multiplayerGame) {
            newScore = new LeaderBoardSaver(this.newPotentialLeader, Main.tetrisPanel.tetrisPlayFieldPanel.score, new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()), Main.tetrisPanel.tetrisPlayFieldPanel.level);
        } else {
            newScore = new LeaderBoardSaver(this.newPotentialLeader, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.score, new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()), Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.level);
        }

        this.leaderBoardSaver[15] = newScore;
        Arrays.sort(this.leaderBoardSaver);
        Collections.reverse(Arrays.asList(this.leaderBoardSaver));
        this.saveLeaderBoard();
    }

    public void saveLeaderBoard() {
        try {

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "score.dat")).getAbsolutePath()))) {
                oos.writeObject(this.leaderBoardSaver);
            }
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        this.setLeaderBoard();
    }

    private void initDynamicLabels() {
        DefaultTableModel model = (DefaultTableModel) this.leaderboardTable.getModel();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j == 0) {
                    model.setValueAt(i + 1, i, j);
                } else if (j == 1) {
                    model.setValueAt(this.leaderBoardSaver[i].getNickname(), i, j);
                } else if (j == 2) {
                    model.setValueAt(this.leaderBoardSaver[i].getScore() + "(" + this.leaderBoardSaver[i].getLevel() + "lvl)", i, j);
                } else {
                    model.setValueAt(this.leaderBoardSaver[i].getDate(), i, j);
                }
            }
        }

    }

    private void mainMenuLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 0;
        this.mainMenuButton.selectButton();
    }

    private void mainMenuLabelMouseExited() {
        this.currentButtonSelected = false;
        this.mainMenuButton.unselectButton();
    }

    private void mainMenuLabelMousePressed() {
        Main.audioPlayer.playClick();
        Main.tetrisFrame.remove(Main.leaderBoardPanel);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
    }

    private void resetLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 1;
        this.resetButton.selectButton();
    }

    private void resetLabelMouseExited() {
        this.currentButtonSelected = false;
        this.resetButton.unselectButton();
    }

    private void resetLabelMousePressed() {
        Main.audioPlayer.playClick();
        new ResetLeaderboardDialog(Main.tetrisFrame, true);
        this.resetLabelMouseEntered();
        Main.audioPlayer.playClick();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            this.pressRightKey();
        } else if (e.getKeyCode() == 37) {
            this.pressLeftKey();
        } else if (e.getKeyCode() == 10) {
            this.pressEnterKey();
        } else if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.exitMenuKey) {
            this.mainMenuLabelMousePressed();
        }

    }

    private void pressEnterKey() {
        if (this.currentButtonSelected) {
            if (this.buttonController == 0) {
                this.mainMenuLabelMousePressed();
            } else {
                this.resetLabelMousePressed();
            }
        }

    }

    private void pressLeftKey() {
        if (this.buttonController == 1 || !this.currentButtonSelected) {
            System.out.println("Left");
            Main.audioPlayer.playClick();
            this.unselectCurrentButton();
            this.buttonController = 0;
            this.selectCurrentButton();
        }

    }

    private void pressRightKey() {
        if (this.buttonController == 0 || !this.currentButtonSelected) {
            System.out.println("Right");
            Main.audioPlayer.playClick();
            this.unselectCurrentButton();
            this.buttonController = 1;
            this.selectCurrentButton();
        }

    }

    public void selectCurrentButton() {
        if (this.buttonController == 0) {
            this.mainMenuLabelMouseEntered();
        } else {
            this.resetLabelMouseEntered();
        }

    }

    private void unselectCurrentButton() {
        if (this.buttonController == 0) {
            this.mainMenuLabelMouseExited();
        } else {
            this.resetLabelMouseExited();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    static class BackgroundPanel extends JPanel {
        BufferedImage bufferedImage = null;

        public BackgroundPanel() {
        }

        protected void paintComponent(Graphics g) {
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
                    g.drawImage(this.bufferedImage, j * this.bufferedImage.getWidth(), i * this.bufferedImage.getHeight(), this);
                }
            }

        }
    }

    static class TitlePanel extends JPanel {
        int w;
        int h;
        int s;
        Dimension d;
        Container c;
        int radius;
        int startX;

        public TitlePanel() {
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
                return new Dimension(this.s, this.s / 6);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintLeaderBoardTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 40;
            PaintStaticLetters.paintLetterL(g2d, startX, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 3 * this.radius + space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterA(g2d, startX + 7 * this.radius + 2 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterD(g2d, startX + 10 * this.radius + 3 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 14 * this.radius + 4 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 18 * this.radius + 5 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterB(g2d, startX + 22 * this.radius + 6 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterO(g2d, startX + 26 * this.radius + 7 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterA(g2d, startX + 29 * this.radius + 8 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 32 * this.radius + 9 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterD(g2d, startX + 36 * this.radius + 10 * space, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        protected void paintComponent(Graphics g) {
            this.radius = Math.min(this.getWidth() / 41, this.getHeight() / 6);
            this.startX = (this.getWidth() - this.radius * 41) / 2;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.paintLeaderBoardTitle(g2d, this.startX, this.radius, this.radius);
        }
    }
}
