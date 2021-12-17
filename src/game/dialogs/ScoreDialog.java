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

public class ScoreDialog extends JDialog implements KeyListener {

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_OK_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "okBlackRoundedImage.png";
    private static final String SELECTED_OK_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "okWhiteRoundedImage.png";

    private JLabel okLabel;
    public JTextField playerNameField;

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
        
        JLabel staticYourScoreLabel = new JLabel();
        JLabel yourScoreLabel = new JLabel();
        JLabel staticTopScore = new JLabel();
        JLabel topScore = new JLabel();
        
        playerNameField = new JTextField();
        okLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        playerNameField.setFont(Main.FONT); 
        playerNameField.setHorizontalAlignment(JTextField.CENTER);
        playerNameField.setBackground(Color.WHITE);
        playerNameField.setDocument(new JTextFieldLimit(20));
        playerNameField.setText("player");
        playerNameField.selectAll();

        okLabel.setHorizontalAlignment(SwingConstants.CENTER);
        okLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OK_BUTTON_PATH)))); 
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
        staticYourScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticYourScoreLabel.setText("your score:");

        yourScoreLabel.setFont(Main.FONT); 
        yourScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        yourScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        yourScoreLabel.setText("" + Main.tetrisPanel.tetrisPlayFieldPanel.score);

        staticTopScore.setFont(Main.FONT); 
        staticTopScore.setForeground(LeaderBoardPanel.GOLD);
        staticTopScore.setText("top score:");

        topScore.setFont(Main.FONT); 
        topScore.setForeground(new java.awt.Color(255, 255, 255));
        topScore.setHorizontalAlignment(SwingConstants.RIGHT);

        topScore.setForeground(LeaderBoardPanel.GOLD);
        if (Long.parseLong(yourScoreLabel.getText()) > Main.leaderBoardPanel.leaderBoardSaver[0].getScore()) {
            setTitle("new best score");
            topScore.setText(yourScoreLabel.getText());
        } else {
            topScore.setText("" + Main.leaderBoardPanel.leaderBoardSaver[0].getScore());
            setTitle("game over");
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(playerNameField, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(48, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(staticYourScoreLabel)
                                        .addComponent(staticTopScore, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(topScore, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yourScoreLabel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(okLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(yourScoreLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticYourScoreLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticTopScore, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(topScore, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(playerNameField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(okLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void okLabelMouseEntered() {
        okLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_OK_BUTTON_PATH))));
    }

    private void okLabelMouseExited() {
        okLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_OK_BUTTON_PATH))));
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
