package game.panels.menu.elements;
import game.dialogs.ResetLeaderboardDialog;
import game.helperclasses.CustomButton2;
import game.helperclasses.PaintStaticLetters;
import game.serial.LeaderBoardSaver;
import game.start.Main;
import game.helperclasses.MyDate;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static game.panels.tetris.TetrisPanel.*;

public class LeaderBoardPanel extends JPanel implements KeyListener {

    private JTable leaderboardTable;

    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color BRONZE = new Color(205, 127, 50);

    private CustomButton2 resetLabel;
    private CustomButton2 mainMenuLabel;
    public LeaderBoardSaver[] leaderBoardSaver;
    public String newPotentialLeader = "player";
    private static final int MAIN_MENU = 0, RESET = 1;
    private int buttonController = MAIN_MENU;
    private boolean currentButtonSelected = true;

    public LeaderBoardPanel() {
        initComponents();
        getLeaderBoard();
        initDynamicLabels();
        addKeyListener(this);
    }

    private void initComponents() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        mainMenuLabel = new CustomButton2();
        resetLabel = new CustomButton2();

        mainMenuLabel.setText("main menu");
        resetLabel.setText("reset");

        mainMenuLabel.setColor1(new Color(0,0,0,100));
        mainMenuLabel.setColor2(new Color(0,0,0,100));

        resetLabel.setColor1(new Color(0,0,0,100));
        resetLabel.setColor2(new Color(0,0,0,100));

        mainMenuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
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

        resetLabel.addMouseListener(new java.awt.event.MouseAdapter() {
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
       JPanel jPanel1 = new javax.swing.JPanel();
       JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        leaderboardTable = new javax.swing.JTable(){

           @Override
           public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
              Component comp =  super.prepareRenderer(renderer, row, column);
              if(getModel().getValueAt(row,0).equals(1))
                  comp.setForeground(GOLD);
              else if(getModel().getValueAt(row,0).equals(2))
                  comp.setForeground(SILVER);
              else if(getModel().getValueAt(row,0).equals(3))
                  comp.setForeground(BRONZE);
              else
                  comp.setForeground(Color.WHITE);
              return comp;
           }
       };

        setLayout(new java.awt.GridLayout());

        backgroundPanel.setBackground(new java.awt.Color(0, 0, 0));

        titlePanel.setPreferredSize(new java.awt.Dimension(0, 120));

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 748, Short.MAX_VALUE)
        );
        titlePanelLayout.setVerticalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setOpaque(false);

        leaderboardTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {1, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {2, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {3, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {23, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},
                        {123, "vitalik", "12345678901", new MyDate(10,10,2010)},

                },
                new String [] {
                        "PLACE", "NICKNAME", "SCORE", "DATE"
                }
        ) {
             Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setBackground(new Color(0,0,0,100));
        cellRenderer.setForeground(Color.WHITE);

        leaderboardTable.setOpaque(false);
        ((DefaultTableCellRenderer)leaderboardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        leaderboardTable.setRowHeight(40);
        leaderboardTable.setShowHorizontalLines(true);
        leaderboardTable.setShowVerticalLines(true);

        for(int i = 0; i < leaderboardTable.getColumnCount(); i++){
            leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        leaderboardTable.setFocusable(false);
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        jScrollPane1.setViewportView(leaderboardTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(40, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                                .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainMenuLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetLabel)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resetLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(mainMenuLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(backgroundPanel);
    }

    public void setLeaderBoard() {
        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0)
                    model.setValueAt((i + 1),i,j);

                else if (j == 1)
                    model.setValueAt(leaderBoardSaver[i].getNickname(),i,j);

                else if (j == 2)
                    model.setValueAt(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)",i,j);

                else
                    model.setValueAt(leaderBoardSaver[i].getDate(),i,j);
            }
        }
    }


    private void getLeaderBoard() {
        resetLeaderBoardArray();

        File scoreFile = new File(System.getProperty("user.dir"), Main.SCORE_FILE_NAME);
        try {

            if (scoreFile.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), Main.SCORE_FILE_NAME).getAbsolutePath()));
                LeaderBoardSaver[] readScore = (LeaderBoardSaver[]) ois.readObject();
                ois.close();
                System.arraycopy(readScore, 0, leaderBoardSaver, 0, 16);
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void resetLeaderBoardArray() {
        leaderBoardSaver = new LeaderBoardSaver[16];
        for (int i = 0; i < 16; i++)
            leaderBoardSaver[i] = new LeaderBoardSaver();
    }


    public void saveLeaderBoardAfterGameOver(boolean multiplayerGame) {
        LeaderBoardSaver newScore;
        if (!multiplayerGame) {
            newScore = new LeaderBoardSaver(newPotentialLeader, Main.tetrisPanel.tetrisPlayFieldPanel.score,
                    new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(),
                            LocalDateTime.now().getYear()), Main.tetrisPanel.tetrisPlayFieldPanel.level);
        } else {
            newScore = new LeaderBoardSaver(newPotentialLeader, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.score,
                    new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(),
                            LocalDateTime.now().getYear()), Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.level);
        }
        leaderBoardSaver[15] = newScore;
        Arrays.sort(leaderBoardSaver);
        Collections.reverse(Arrays.asList(leaderBoardSaver));
        saveLeaderBoard();
    }

    public void saveLeaderBoard() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), Main.SCORE_FILE_NAME).getAbsolutePath()))) {
            oos.writeObject(leaderBoardSaver);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        setLeaderBoard();
    }


    private void initDynamicLabels() {
        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0)
                    model.setValueAt((i + 1),i,j);
                    //name
                else if (j == 1)
                    model.setValueAt(leaderBoardSaver[i].getNickname(),i,j);
                    //score
                else if (j == 2)
                    model.setValueAt(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)",i,j);
                    //date
                else
                    model.setValueAt(leaderBoardSaver[i].getDate(),i,j);
            }
        }
    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuLabel.selectButton();
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuLabel.unselectButton();
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
        resetLabel.selectButton();
    }

    private void resetLabelMouseExited() {
        currentButtonSelected = false;
        resetLabel.unselectButton();
    }

    private void resetLabelMousePressed() {
        Main.audioPlayer.playClick();
        new ResetLeaderboardDialog(Main.tetrisFrame, true);
        resetLabelMouseEntered();
        Main.audioPlayer.playClick();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            pressRightKey();
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            pressLeftKey();
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            pressEnterKey();
        else if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.exitMenuKey) {
            mainMenuLabelMousePressed();
        }
    }

    private void pressEnterKey() {
        if (currentButtonSelected) {
            if (buttonController == MAIN_MENU)
                mainMenuLabelMousePressed();
            else {
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
        if (buttonController == MAIN_MENU)
            mainMenuLabelMouseEntered();
        else
            resetLabelMouseEntered();
    }

    private void unselectCurrentButton() {
        if (buttonController == MAIN_MENU)
            mainMenuLabelMouseExited();
        else
            resetLabelMouseExited();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static class BackgroundPanel extends JPanel{

       BufferedImage bufferedImage = null;

        public BackgroundPanel(){
        }

        @Override
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
                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
        }
    }

    static class TitlePanel extends JPanel {

        public TitlePanel() {
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

            return new Dimension(s , (s) / 6);
        }

        private void paintLeaderBoardTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 40;
            //L
            PaintStaticLetters.paintLetterL(g2d, startX, startY, radius);
            //E
            PaintStaticLetters.paintLetterE(g2d, startX + 3 * radius + space, startY, radius);
            //A
            PaintStaticLetters.paintLetterA(g2d, startX + 7 * radius + 2 * space, startY, radius);
            //D
            PaintStaticLetters.paintLetterD(g2d, startX + 10 * radius + 3 * space, startY, radius);
            //E
            PaintStaticLetters.paintLetterE(g2d, startX + 14 * radius + 4 * space, startY, radius);
            //R
            PaintStaticLetters.paintLetterR(g2d, startX + 18 * radius + 5 * space, startY, radius);
            //B
            PaintStaticLetters.paintLetterB(g2d, startX + 22 * radius + 6 * space, startY, radius);
            //O
            PaintStaticLetters.paintLetterO(g2d, startX + 26 * radius + 7 * space, startY, radius);
            //A
            PaintStaticLetters.paintLetterA(g2d, startX + 29 * radius + 8 * space, startY, radius);
            //R
            PaintStaticLetters.paintLetterR(g2d, startX + 32 * radius + 9 * space, startY, radius);
            //D
            PaintStaticLetters.paintLetterD(g2d, startX + 36 * radius + 10 * space, startY, radius);
        }

        int radius;
        int startX;

        @Override
        protected void paintComponent(Graphics g) {
            radius = Math.min(getWidth() / 41, getHeight() / 6);
            startX = (getWidth() - radius * 41) / 2;

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            paintLeaderBoardTitle(g2d, startX, radius, radius);
        }
    }
}
