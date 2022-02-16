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

    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color BRONZE = new Color(205, 127, 50);

    private static final int MAIN_MENU = 0;
    private static final int RESET = 1;

    public LeaderBoardSaver[] leaderBoardSaver;
    public String newPotentialLeader = "player";

    private int buttonController = 0;

    private boolean currentButtonSelected = true;

    private JTable leaderboardTable;

    private MyButton resetButton;
    private MyButton mainMenuButton;

    public LeaderBoardPanel() {
        initComponents();
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        getLeaderBoard();
        initDynamicLabels();
        addKeyListener(this);
    }

    private void initComponents() {

        mainMenuButton = new MyButton("main menu");
        resetButton = new MyButton("reset");

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

        BackgroundPanel backgroundPanel = new BackgroundPanel();

        TitlePanel titlePanel = new TitlePanel();

        JPanel panel = new JPanel();
        panel.setOpaque(false);

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

        setLayout(new GridLayout());

        titlePanel.setPreferredSize(new Dimension(0, 120));

        GroupLayout titlePanelLayout = new GroupLayout(titlePanel);

        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(titlePanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 748, 32767));
        titlePanelLayout.setVerticalGroup(titlePanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 120, 32767));

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

        leaderboardTable.setOpaque(false);
        leaderboardTable.setRowHeight(40);
        leaderboardTable.setShowHorizontalLines(true);
        leaderboardTable.setShowVerticalLines(true);

        ((DefaultTableCellRenderer) leaderboardTable.getDefaultRenderer(Object.class)).setOpaque(false);

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        for (int i = 0; i < leaderboardTable.getColumnCount(); ++i)
            leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        leaderboardTable.setFocusable(false);
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setMaximumSize(new Dimension(2147483647, 2147483647));

        jScrollPane1.setViewportView(leaderboardTable);

        GroupLayout jPanel1Layout = new GroupLayout(panel);

        panel.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(40, 32767)
                        .addComponent(jScrollPane1, -1, 668, 32767)
                        .addContainerGap(40, 32767))
                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainMenuButton)
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(resetButton).addContainerGap()));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30)
                        .addComponent(jScrollPane1, -1, 385, 32767)
                        .addPreferredGap(ComponentPlacement.RELATED, 20, 32767)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(resetButton, Alignment.TRAILING)
                                .addComponent(mainMenuButton, Alignment.TRAILING))
                        .addContainerGap()));

        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(titlePanel, -1, 748, 32767).addComponent(panel, -1, -1, 32767));
        backgroundPanelLayout.setVerticalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(backgroundPanelLayout.createSequentialGroup().addGap(41, 41, 41).addComponent(titlePanel, -2, -1, -2).addGap(82, 82, 82).addComponent(panel, -1, -1, 32767)));
        add(backgroundPanel);
    }

    public void setLeaderBoard() {
        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j == 0) {
                    model.setValueAt(i + 1, i, j);
                } else if (j == 1) {
                    model.setValueAt(leaderBoardSaver[i].getNickname(), i, j);
                } else if (j == 2) {
                    model.setValueAt(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)", i, j);
                } else {
                    model.setValueAt(leaderBoardSaver[i].getDate(), i, j);
                }
            }
        }

    }

    private void getLeaderBoard() {
        resetLeaderBoardArray();
        File scoreFile = new File(System.getProperty("user.dir"), "score.dat");

        try {
            if (scoreFile.length() > 0L) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "score.dat")).getAbsolutePath()));
                LeaderBoardSaver[] readScore = (LeaderBoardSaver[]) ois.readObject();
                ois.close();
                System.arraycopy(readScore, 0, leaderBoardSaver, 0, 16);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    public void resetLeaderBoardArray() {
        leaderBoardSaver = new LeaderBoardSaver[16];

        for (int i = 0; i < 16; ++i) {
            leaderBoardSaver[i] = new LeaderBoardSaver();
        }

    }

    public void saveLeaderBoardAfterGameOver(boolean multiplayerGame) {
        LeaderBoardSaver newScore;
        if (!multiplayerGame) {
            newScore = new LeaderBoardSaver(newPotentialLeader, Main.tetrisPanel.tetrisPlayFieldPanel.score, new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()), Main.tetrisPanel.tetrisPlayFieldPanel.level);
        } else {
            newScore = new LeaderBoardSaver(newPotentialLeader, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.score, new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()), Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.level);
        }

        leaderBoardSaver[15] = newScore;
        Arrays.sort(leaderBoardSaver);
        Collections.reverse(Arrays.asList(leaderBoardSaver));
        saveLeaderBoard();
    }

    public void saveLeaderBoardAfterGameOverBot() {
        LeaderBoardSaver newScore;
        newScore = new LeaderBoardSaver(newPotentialLeader, Main.multiplayerPanel2.battlePanel.playfield.score,
                new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(),
                        LocalDateTime.now().getYear()), Main.multiplayerPanel2.battlePanel.playfield.level);


        leaderBoardSaver[15] = newScore;
        Arrays.sort(leaderBoardSaver);
        Collections.reverse(Arrays.asList(leaderBoardSaver));
        saveLeaderBoard();
    }

    public void saveLeaderBoard() {
        try {

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "score.dat")).getAbsolutePath()))) {
                oos.writeObject(leaderBoardSaver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLeaderBoard();
    }

    private void initDynamicLabels() {
        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j == 0) {
                    model.setValueAt(i + 1, i, j);
                } else if (j == 1) {
                    model.setValueAt(leaderBoardSaver[i].getNickname(), i, j);
                } else if (j == 2) {
                    model.setValueAt(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)", i, j);
                } else {
                    model.setValueAt(leaderBoardSaver[i].getDate(), i, j);
                }
            }
        }

    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuButton.selectButton();
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuButton.unselectButton();
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
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = RESET;
        resetButton.selectButton();
    }

    private void resetLabelMouseExited() {
        currentButtonSelected = false;
        resetButton.unselectButton();
    }

    private void resetLabelMousePressed() {
        Main.audioPlayer.playClick();
        new ResetLeaderboardDialog(Main.tetrisFrame, true);
        resetLabelMouseEntered();
        Main.audioPlayer.playClick();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressRightKey();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressLeftKey();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            pressEnterKey();
        } else if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.exitMenuKey) {
            mainMenuLabelMousePressed();
        }
    }

    private void pressEnterKey() {
        if (currentButtonSelected) {
            if (buttonController == MAIN_MENU) {
                mainMenuLabelMousePressed();
            } else {
                resetLabelMousePressed();
            }
        }
    }

    private void pressLeftKey() {
        if (buttonController == RESET || !currentButtonSelected) {
            System.out.println("Left");
            Main.audioPlayer.playClick();
            unselectCurrentButton();
            buttonController = MAIN_MENU;
            selectCurrentButton();
        }

    }

    private void pressRightKey() {
        if (buttonController == MAIN_MENU || !currentButtonSelected) {
            System.out.println("Right");
            Main.audioPlayer.playClick();
            unselectCurrentButton();
            buttonController = RESET;
            selectCurrentButton();
        }

    }

    public void selectCurrentButton() {
        if (buttonController == MAIN_MENU) {
            mainMenuLabelMouseEntered();
        } else {
            resetLabelMouseEntered();
        }
    }

    private void unselectCurrentButton() {
        if (buttonController == MAIN_MENU) {
            mainMenuLabelMouseExited();
        } else {
            resetLabelMouseExited();
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
                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
        }
    }

    static class TitlePanel extends JPanel {
        int w;
        int h;
        int s;

        int radius;
        int startX;

        Dimension d;
        Container c;

        public TitlePanel() {
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
                return new Dimension(s, s / 6);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintLeaderBoardTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 40;

            PaintStaticLetters.paintLetterL(g2d, startX, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 3 * radius + space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterA(g2d, startX + 7 * radius + 2 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterD(g2d, startX + 10 * radius + 3 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 14 * radius + 4 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 18 * radius + 5 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterB(g2d, startX + 22 * radius + 6 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterO(g2d, startX + 26 * radius + 7 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterA(g2d, startX + 29 * radius + 8 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 32 * radius + 9 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterD(g2d, startX + 36 * radius + 10 * space, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            radius = Math.min(getWidth() / 41, getHeight() / 6);
            startX = (getWidth() - radius * 41) / 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintLeaderBoardTitle(g2d, startX, radius, radius);
        }
    }
}
