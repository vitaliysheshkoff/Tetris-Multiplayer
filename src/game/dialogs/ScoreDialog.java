package game.dialogs;

import game.helperclasses.textfieldlimit.JTextFieldLimit;
import game.helperclasses.buttons.MyButton;
import game.panels.menu.LeaderBoardPanel;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class ScoreDialog extends JDialog implements KeyListener {

    public JTextField playerNameField;
    private final long score;

    public ScoreDialog(Frame parent, boolean modal, long score) {
        super(parent, modal);
        this.score = score;
        initComponents();
        getContentPane().setBackground(new Color(0, 0, 0, 100));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);
        setLocationRelativeTo(Main.tetrisFrame);
        setVisible(true);
    }

    private void initComponents() {
        JLabel staticYourScoreLabel = new JLabel();
        JLabel yourScoreLabel = new JLabel();
        JLabel staticTopScore = new JLabel();
        JLabel topScore = new JLabel();

        MyButton okLabel = new MyButton("ok");
        okLabel.setText("ok");
        okLabel.setFont(Main.FONT);
        okLabel.setBackground(new Color(0, 0, 0));

        playerNameField = new JTextField();
        playerNameField.setFont(Main.FONT);
        playerNameField.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameField.setBackground(Color.WHITE);
        playerNameField.setDocument(new JTextFieldLimit(20));
        playerNameField.setText("player");
        playerNameField.selectAll();
        playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerNameField.addKeyListener(this);

        okLabel.setHorizontalAlignment(SwingConstants.CENTER);
        okLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    okLabelMousePressed();
                }
            }
        });

        staticYourScoreLabel.setFont(Main.FONT);
        staticYourScoreLabel.setForeground(new Color(255, 255, 255));
        staticYourScoreLabel.setText("your score:");

        yourScoreLabel.setFont(Main.FONT);
        yourScoreLabel.setForeground(new Color(255, 255, 255));
        yourScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        yourScoreLabel.setText("" + score);

        staticTopScore.setFont(Main.FONT);
        staticTopScore.setForeground(LeaderBoardPanel.GOLD);
        staticTopScore.setText("top score:");

        topScore.setFont(Main.FONT);
        topScore.setForeground(new Color(255, 255, 255));
        topScore.setHorizontalAlignment(SwingConstants.RIGHT);
        topScore.setForeground(LeaderBoardPanel.GOLD);

        if (Long.parseLong(yourScoreLabel.getText()) > Main.leaderBoardPanel.leaderBoardSaver[0].getScore()) {
            topScore.setText(yourScoreLabel.getText());
        } else {
            topScore.setText("" + Main.leaderBoardPanel.leaderBoardSaver[0].getScore());
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(playerNameField, -2, 225, -2)
                        .addGap(0, 0, 32767))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(48, 32767)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(staticYourScoreLabel).addComponent(staticTopScore, -2, 225, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(topScore, -2, -1, -2)
                                .addComponent(yourScoreLabel, -2, -1, -2))
                        .addGap(59, 59, 59)).addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(okLabel).addContainerGap(-1, 32767)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(yourScoreLabel, -2, 32, -2)
                                .addComponent(staticYourScoreLabel, -2, 35, -2))
                        .addGap(7, 7, 7).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(staticTopScore, -2, 32, -2)
                                .addComponent(topScore, -2, 32, -2))
                        .addGap(18, 18, 18).addComponent(playerNameField, -2, 46, -2)
                        .addGap(18, 18, 18).addComponent(okLabel).addContainerGap(-1, 32767)));
        pack();
    }

    private void okLabelMousePressed() {
        dispose();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            okLabelMousePressed();
        }

        playerNameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public void keyReleased(KeyEvent e) {
        playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
