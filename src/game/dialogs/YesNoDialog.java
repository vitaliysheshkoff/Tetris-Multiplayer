package game.dialogs;

import game.frames.TetrisFrame;
import game.helperclasses.CustomButton2;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class YesNoDialog extends JDialog implements KeyListener {

    protected CustomButton2 noLabel;
    protected javax.swing.JLabel quitGameLabel;
    protected CustomButton2 yesLabel;
    protected static int YES = 0, NO = 1;
    protected int buttonController = YES;
    protected boolean currentButtonSelected = true;


    public YesNoDialog(TetrisFrame parent, String title, String dialogText, boolean modal) {

        super(parent, title, modal);

        initComponents(dialogText);
        requestFocusInWindow();
        setLocationRelativeTo(parent);
        addKeyListener(this);
        setVisible(true);
        setFocusable(true);


    }

    private void initComponents(String dialogText) {

        quitGameLabel = new javax.swing.JLabel("");
        yesLabel = new CustomButton2();
        yesLabel.setColor1(Color.GREEN);
        yesLabel.setColor2(Color.GREEN.darker());
        yesLabel.setFont(Main.FONT);
        yesLabel.setText("yes");
        noLabel = new CustomButton2();
        noLabel.setColor1(Color.RED);
        noLabel.setColor2(Color.RED.darker());
        noLabel.setFont(Main.FONT);
        noLabel.setText("no");

        noLabel.setFocusable(false);
        yesLabel.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);

        quitGameLabel.setBackground(new java.awt.Color(0, 0, 0));
        quitGameLabel.setFont(Main.FONT);
        quitGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        quitGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitGameLabel.setText(dialogText);

        yesLabel.setBackground(new java.awt.Color(0, 0, 0));
        yesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    yesLabelMousePressed();
                }
            }
        });

        noLabel.setBackground(new java.awt.Color(0, 0, 0));
        noLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noLabelMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                noLabelMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    noLabelMousePressed();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(yesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(quitGameLabel)
                                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(quitGameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(yesLabel)
                                        .addComponent(noLabel))
                                .addGap(30, 30, 30))
        );
        pack();
    }

    protected void yesLabelMousePressed() {
        Main.audioPlayer.playClick();
        System.out.println("Quit game!");
        System.exit(0);
    }

    protected void yesLabelMouseExited() {
        currentButtonSelected = false;
        yesLabel.unselectButton();
    }

    protected void yesLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = YES;
        yesLabel.selectButton();
    }

    protected void noLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NO;
        noLabel.selectButton();
    }

    protected void noLabelMouseExited() {
        currentButtonSelected = false;
        noLabel.unselectButton();
    }

    protected void noLabelMousePressed() {
        dispose();
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
    }

    protected void pressEnterKey() {
        if (currentButtonSelected) {
            if (buttonController == YES)
                yesLabelMousePressed();
            else {
                Main.menuPanel.selectCurrentButton();
                noLabelMousePressed();
            }
        }
    }

    protected void pressLeftKey() {

        if (buttonController == NO || !currentButtonSelected) {
            System.out.println("Left");
            Main.audioPlayer.playClick();

            unselectCurrentButton();
            buttonController = YES;
            selectCurrentButton();
        }
    }

    protected void pressRightKey() {
        if (buttonController == YES || !currentButtonSelected) {
            System.out.println("Right");
            Main.audioPlayer.playClick();

            unselectCurrentButton();
            buttonController = NO;
            selectCurrentButton();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void selectCurrentButton() {
        if (buttonController == YES)
            yesLabelMouseEntered();
        else
            noLabelMouseEntered();
    }

    protected void unselectCurrentButton() {
        if (buttonController == YES)
            yesLabelMouseExited();
        else
            noLabelMouseExited();
    }
}