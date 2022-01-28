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

    private MyButton okLabel;
    public JTextField playerNameField;
    private long score;

    public ScoreDialog(Frame parent, boolean modal, long score) {
        super(parent, modal);
        this.score = score;
        this.initComponents();
        this.setLocationRelativeTo(Main.tetrisFrame);
        this.okLabelMouseEntered();
        this.playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.playerNameField.addKeyListener(this);
        this.setVisible(true);
    }

    private void initComponents() {
        JLabel staticYourScoreLabel = new JLabel();
        JLabel yourScoreLabel = new JLabel();
        JLabel staticTopScore = new JLabel();
        JLabel topScore = new JLabel();
        this.playerNameField = new JTextField();
        this.okLabel = new MyButton("ok");
        this.okLabel.setText("ok");
        this.okLabel.setFont(Main.FONT);
        this.okLabel.setBackground(new Color(0, 0, 0));
        this.setDefaultCloseOperation(2);
        this.setAutoRequestFocus(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 100));
        this.setResizable(false);
        this.playerNameField.setFont(Main.FONT);
        this.playerNameField.setHorizontalAlignment(0);
        this.playerNameField.setBackground(Color.WHITE);
        this.playerNameField.setDocument(new JTextFieldLimit(20));
        this.playerNameField.setText("player");
        this.playerNameField.selectAll();
        this.okLabel.setHorizontalAlignment(0);
       // this.okLabel.addMouseListener(new 1(this));
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
        staticYourScoreLabel.setFont(Main.FONT);
        staticYourScoreLabel.setForeground(new Color(255, 255, 255));
        staticYourScoreLabel.setText("your score:");
        yourScoreLabel.setFont(Main.FONT);
        yourScoreLabel.setForeground(new Color(255, 255, 255));
        yourScoreLabel.setHorizontalAlignment(4);
        yourScoreLabel.setText("" + this.score);
        staticTopScore.setFont(Main.FONT);
        staticTopScore.setForeground(LeaderBoardPanel.GOLD);
        staticTopScore.setText("top score:");
        topScore.setFont(Main.FONT);
        topScore.setForeground(new Color(255, 255, 255));
        topScore.setHorizontalAlignment(4);
        topScore.setForeground(LeaderBoardPanel.GOLD);
        if (Long.parseLong(yourScoreLabel.getText()) > Main.leaderBoardPanel.leaderBoardSaver[0].getScore()) {
            topScore.setText(yourScoreLabel.getText());
        } else {
            topScore.setText("" + Main.leaderBoardPanel.leaderBoardSaver[0].getScore());
        }

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(79, 79, 79).addComponent(this.playerNameField, -2, 225, -2).addGap(0, 0, 32767)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(48, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(staticYourScoreLabel).addComponent(staticTopScore, -2, 225, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(topScore, -2, -1, -2).addComponent(yourScoreLabel, -2, -1, -2)).addGap(59, 59, 59)).addGroup(layout.createSequentialGroup().addGap(157, 157, 157).addComponent(this.okLabel).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(yourScoreLabel, -2, 32, -2).addComponent(staticYourScoreLabel, -2, 35, -2)).addGap(7, 7, 7).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(staticTopScore, -2, 32, -2).addComponent(topScore, -2, 32, -2)).addGap(18, 18, 18).addComponent(this.playerNameField, -2, 46, -2).addGap(18, 18, 18).addComponent(this.okLabel).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void okLabelMouseEntered() {
    }

    private void okLabelMouseExited() {
    }

    private void okLabelMousePressed() {
        this.dispose();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.okLabelMousePressed();
        }

        this.playerNameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public void keyReleased(KeyEvent e) {
        this.playerNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
