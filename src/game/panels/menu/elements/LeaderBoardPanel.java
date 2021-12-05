package game.panels.menu.elements;
import game.dialogs.ResetLeaderboardDialog;
import game.helperclasses.PaintStaticLetters;
import game.serial.LeaderBoardSaver;
import game.start.Main;
import game.helperclasses.MyDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class LeaderBoardPanel extends JPanel implements KeyListener {

    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color BRONZE = new Color(205, 127, 50);

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";
    private static final String UNSELECTED_RESET_PATH = BUTTON_IMAGES_FOLDER + "resetBlackRoundedImage.png";
    private static final String SELECTED_RESET_PATH = BUTTON_IMAGES_FOLDER + "resetRedRoundedImage.png";

    private JLabel[][] dynamicLabels;
    private JLabel[] staticLabels; //0.place, 1.nickname,2.score,3.date
    private JLabel resetLabel;
    private JLabel mainMenuLabel;
    public LeaderBoardSaver[] leaderBoardSaver;
    public String newPotentialLeader = "player";
    private static final int MAIN_MENU = 0, RESET = 1;
    private int buttonController = MAIN_MENU;
    private boolean currentButtonSelected = true;


    public LeaderBoardPanel() {
        initComponents();
        addKeyListener(this);
    }

    private void initComponents() {

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        getLeaderBoard();
        initDynamicLabels();
        initStaticLabels();

        mainMenuLabel = new JLabel();
        resetLabel = new JLabel();

        mainMenuLabel.setBackground(Color.BLACK);
        mainMenuLabel.setForeground(Color.WHITE);
        mainMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
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

        resetLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_RESET_PATH))));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(mainMenuLabel)
                                                .addGap(491, 491, 491)
                                                .addComponent(resetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addComponent(/*firstPlace*/ dynamicLabels[0][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(dynamicLabels[8][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[7][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[6][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[5][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[4][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[3][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[2][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[1][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(staticLabels[0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[9][0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[9][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[9][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[8][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[8][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[5][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[5][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[4][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[4][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[3][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[3][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(dynamicLabels[2][1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(dynamicLabels[2][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(dynamicLabels[0][1], javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                                        .addComponent(dynamicLabels[1][1], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(dynamicLabels[1][2], javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                                        .addComponent(dynamicLabels[0][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(dynamicLabels[6][1], javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                                        .addComponent(dynamicLabels[7][1], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(0, 0, 0)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(dynamicLabels[6][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(dynamicLabels[7][2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(staticLabels[1], javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(staticLabels[2], javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(dynamicLabels[0][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[2][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[1][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[3][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[4][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[5][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[6][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[7][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[8][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dynamicLabels[9][3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(staticLabels[3], javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticLabels[0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticLabels[1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticLabels[2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticLabels[3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[0][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[0][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[0][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[0][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[1][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[1][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[1][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[1][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[2][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[2][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[2][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[2][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[3][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[3][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[3][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[3][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[4][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[4][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[4][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[4][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[5][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[5][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[5][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[5][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[6][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[6][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[6][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[6][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[7][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[7][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[7][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[7][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[8][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[8][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[8][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[8][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dynamicLabels[9][0], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[9][1], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[9][2], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dynamicLabels[9][3], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(mainMenuLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(resetLabel)))
                                .addContainerGap(28, Short.MAX_VALUE))
        );
    }

    public void setLeaderBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0)
                    dynamicLabels[i][j].setText("" + (i + 1));
                else if (j == 1)
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getNickname());
                else if (j == 2)
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)");
                else
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getDate().getDay() + "/" + leaderBoardSaver[i].getDate().getMonth() + "/" + leaderBoardSaver[i].getDate().getYear());
            }
        }
    }

    private void getLeaderBoard() {

        resetLeaderBoardArray();

        File scoreFile = new File(System.getProperty("user.dir"), Main.SCORE_FILE_NAME);

        try {
            if (scoreFile.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(scoreFile.getAbsolutePath()));
                LeaderBoardSaver[] readScore = (LeaderBoardSaver[]) ois.readObject();
                ois.close();
                System.arraycopy(readScore, 0, leaderBoardSaver, 0, 11);
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void resetLeaderBoardArray() {
        leaderBoardSaver = new LeaderBoardSaver[11];
        for (int i = 0; i < 11; i++)
            leaderBoardSaver[i] = new LeaderBoardSaver();
    }

    public void saveLeaderBoardAfterGameOver() {

        LeaderBoardSaver newScore = new LeaderBoardSaver(newPotentialLeader, Main.tetrisPanel.tetrisPlayFieldPanel.score,
                new MyDate(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()), Main.tetrisPanel.tetrisPlayFieldPanel.level);

        leaderBoardSaver[10] = newScore;
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


    private void initStaticLabels() {
        staticLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            staticLabels[i] = new JLabel();
            staticLabels[i].setBackground(Color.BLACK);
            staticLabels[i].setForeground(Color.WHITE);
            staticLabels[i].setFont(Main.FONT);
            staticLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            if (i == 0)
                staticLabels[i].setText("#");
            else if (i == 1)
                staticLabels[i].setText("nickname");
            else if (i == 2)
                staticLabels[i].setText("score");
            else {
                staticLabels[i].setText("date");
                staticLabels[i].setMaximumSize(new Dimension(130, 50));
                staticLabels[i].setMinimumSize(new Dimension(130, 50));
                staticLabels[i].setPreferredSize(new Dimension(130, 50));
            }
        }
    }

    private void initDynamicLabels() {
        dynamicLabels = new JLabel[10][4];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                dynamicLabels[i][j] = new JLabel();
                dynamicLabels[i][j].setBackground(Color.BLACK);
                dynamicLabels[i][j].setFont(Main.FONT);
                dynamicLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                if (i < 3) {
                    if (j == 0)
                        dynamicLabels[i][j].setForeground(Color.BLACK);
                    else {
                        if (i == 0)
                            dynamicLabels[i][j].setForeground(GOLD);
                        else if (i == 1)
                            dynamicLabels[i][j].setForeground(SILVER);
                        else
                            dynamicLabels[i][j].setForeground(BRONZE);
                    }
                } else
                    dynamicLabels[i][j].setForeground(Color.WHITE);
                //place
                if (j == 0)
                    dynamicLabels[i][j].setText("" + (i + 1));
                    //name
                else if (j == 1)
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getNickname());
                    //score
                else if (j == 2)
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getScore() + "(" + leaderBoardSaver[i].getLevel() + "lvl)");
                    //date
                else {
                    dynamicLabels[i][j].setText(leaderBoardSaver[i].getDate().getDay() + "/" + leaderBoardSaver[i].getDate().getMonth() + "/" + leaderBoardSaver[i].getDate().getYear());
                    dynamicLabels[i][j].setMaximumSize(new Dimension(130, 50));
                    dynamicLabels[i][j].setMinimumSize(new Dimension(130, 50));
                    dynamicLabels[i][j].setPreferredSize(new Dimension(130, 50));
                }
            }
        }
    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMousePressed() {
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
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
        resetLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_RESET_PATH))));
    }

    private void resetLabelMouseExited() {
        currentButtonSelected = false;
        resetLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_RESET_PATH))));
    }

    private void resetLabelMousePressed() {

        Main.audioPlayer.playClick();
        new ResetLeaderboardDialog(Main.tetrisFrame, true);
        resetLabelMouseEntered();
        Main.audioPlayer.playClick();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3.0f));

        separateTop3(g2d);
        paintLeaderBoardTitle(g2d);
        paintLeaderboardCells(g2d);

    }

    private void paintLeaderboardCells(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);

        //horizontal lines:
        g2d.drawLine(52, 250, 872, 250);
        g2d.drawLine(52, 300, 872, 300);
        g2d.drawLine(52, 350, 872, 350);
        g2d.drawLine(52, 400, 872, 400);
        g2d.drawLine(52, 450, 872, 450);
        g2d.drawLine(52, 500, 872, 500);
        g2d.drawLine(52, 550, 872, 550);
        g2d.drawLine(52, 600, 872, 600);
        g2d.drawLine(52, 650, 872, 650);
        g2d.drawLine(52, 700, 872, 700);
        g2d.drawLine(52, 750, 872, 750);
        g2d.drawLine(52, 800, 872, 800);

        //vertical lines:
        g2d.drawLine(52, 250, 52, 800);
        g2d.drawLine(102, 250, 102, 800);
        g2d.drawLine(402, 250, 402, 800);
        g2d.drawLine(702, 250, 702, 800);
        g2d.drawLine(872, 250, 872, 800);
    }

    private void separateTop3(Graphics2D g2d) {
        //gold
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillRect(52, 300, 50, 50);
        //silver
        g2d.setColor(new Color(192, 192, 192));
        g2d.fillRect(52, 350, 50, 50);
        //bronze
        g2d.setColor(new Color(205, 127, 50));
        g2d.fillRect(52, 400, 50, 50);
    }

    private void paintLeaderBoardTitle(Graphics2D g2d) {
        int startX = 50, startY = 80, radius = 20, space = 3;
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
}
