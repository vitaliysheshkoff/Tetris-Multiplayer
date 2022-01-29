package game.dialogs;

import game.frame.TetrisFrame;
import game.helperclasses.buttons.MyButton;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class YesNoDialog extends JDialog implements KeyListener {

    protected MyButton noButton;
    protected javax.swing.JLabel quitGameLabel;
    protected MyButton yesButton;
    protected static int YES = 0, NO = 1;
    protected int buttonController = YES;
    protected boolean currentButtonSelected = true;

    public YesNoDialog(TetrisFrame parent, String title, String dialogText, boolean modal) {

        super(parent, title, modal);

        addKeyListener(this);

        initComponents(dialogText);

        getContentPane().setBackground(new java.awt.Color(0, 0, 0, 100));

        setLocationRelativeTo(parent);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
    }

    private void initComponents(String dialogText) {

        noButton = new MyButton("no");
        noButton.setBackground(Color.RED);
        noButton.setFocusable(false);

        yesButton = new MyButton("yes");
        yesButton.setBackground(Color.GREEN);
        yesButton.setFocusable(false);

        quitGameLabel = new javax.swing.JLabel("");
        quitGameLabel.setBackground(new java.awt.Color(0, 0, 0));
        quitGameLabel.setFont(Main.FONT);
        quitGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        quitGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitGameLabel.setText(dialogText);

        yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
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

        noButton.addMouseListener(new java.awt.event.MouseAdapter() {
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
                                .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(yesButton)
                                        .addComponent(noButton))
                                .addGap(30, 30, 30))
        );
        pack();
    }

    protected void yesLabelMousePressed() {
        Main.audioPlayer.playClick();
        System.out.println("Quit game!");
        Main.saveApplicationSize();
        System.exit(0);
    }

    protected void yesLabelMouseExited() {
        currentButtonSelected = false;
        yesButton.unselectButton();
    }

    protected void yesLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = YES;
        yesButton.selectButton();
    }

    protected void noLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NO;
        noButton.selectButton();
    }

    protected void noLabelMouseExited() {
        currentButtonSelected = false;
        noButton.unselectButton();
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