package game.dialogs;

import game.helperclasses.JTextFieldLimit;
import game.panels.menu.elements.LeaderBoardPanel;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ScoreDialog extends javax.swing.JDialog implements KeyListener {

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_OK_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "okBlackRoundedImage.png";
    private static final String SELECTED_OK_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "okWhiteRoundedImage.png";

    private javax.swing.JLabel okLabel;
    public javax.swing.JTextField playerNameField;

    public ScoreDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(Main.tetrisFrame);
        okLabelMouseEntered();
        playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerNameField.addKeyListener(this);
        setVisible(true);
    }

    private void initComponents() {

        playerNameField = new javax.swing.JTextField();
        okLabel = new javax.swing.JLabel();
        JLabel staticYourScoreLabel = new JLabel();
        JLabel yourScoreLabel = new JLabel();
        JLabel staticTopScore = new JLabel();
        JLabel topScore = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        playerNameField.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24)); // NOI18N
        playerNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playerNameField.setBackground(Color.WHITE);
        playerNameField.setDocument(new JTextFieldLimit(20));
        playerNameField.setText("player");
        playerNameField.selectAll();

        okLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        okLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OK_BUTTON_PATH)))); // NOI18N
        okLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                okLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    okLabelMousePressed();
                }
            }
        });

        staticYourScoreLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 20)); // NOI18N
        staticYourScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticYourScoreLabel.setText("your score:");

        yourScoreLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 20)); // NOI18N
        yourScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        yourScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yourScoreLabel.setText("" + Main.tetrisPanel.tetrisPlayFieldPanel.score);

        staticTopScore.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 20)); // NOI18N
        staticTopScore.setForeground(LeaderBoardPanel.GOLD);
        staticTopScore.setText("top score:");

        topScore.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 20)); // NOI18N
        topScore.setForeground(new java.awt.Color(255, 255, 255));
        topScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        topScore.setForeground(LeaderBoardPanel.GOLD);
        if (Long.parseLong(yourScoreLabel.getText()) > Main.leaderBoardPanel.leaderBoardSaver[0].getScore()) {
            setTitle("new best score");
            topScore.setText(yourScoreLabel.getText());
        } else {
            topScore.setText("" + Main.leaderBoardPanel.leaderBoardSaver[0].getScore());
            setTitle("game over");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(playerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(48, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(staticYourScoreLabel)
                                        .addComponent(staticTopScore, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(topScore, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yourScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(okLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(yourScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticYourScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticTopScore, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(topScore, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(playerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(okLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void okLabelMouseEntered() {
        okLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_OK_BUTTON_PATH))));
    }

    private void okLabelMouseExited() {
        okLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OK_BUTTON_PATH))));
    }

    private void okLabelMousePressed() {
        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            okLabelMousePressed();

        playerNameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
