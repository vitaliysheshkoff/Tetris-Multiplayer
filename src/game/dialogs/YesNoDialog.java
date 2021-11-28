package game.dialogs;

import game.frames.TetrisFrame;
import game.helperclasses.CustomButton;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class YesNoDialog extends JDialog implements KeyListener {

    protected javax.swing.JLabel noLabel;
    //protected CustomButton noLabel;
    protected javax.swing.JLabel quitGameLabel;
    protected javax.swing.JLabel yesLabel;
   // protected CustomButton yesLabel;
    protected static int YES = 0, NO = 1;
    protected int buttonController = YES;
    protected boolean currentButtonSelected = true;

    protected static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    protected static final String UNSELECTED_YES_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "yesGreenUnselectedRoundedImage (2).png";
    protected static final String SELECTED_YES_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "yesGreenSelectedRoundedImage (2).png";
    protected static final String UNSELECTED_NO_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "noRedUnselectedRoundedImage (2).png";
    protected static final String SELECTED_NO_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "noRedSelectedRoundedImage (2).png";

    public YesNoDialog(TetrisFrame parent, String title, String dialogText, boolean modal) {
        super(parent, title, modal);
        initComponents(dialogText);
        selectCurrentButton();
        setLocationRelativeTo(parent);
        addKeyListener(this);
        setVisible(true);
        setFocusable(true);
    }

    private void initComponents(String dialogText) {

        quitGameLabel = new javax.swing.JLabel("");
        yesLabel = new javax.swing.JLabel();
      //  yesLabel = new CustomButton(Color.GREEN, Color.GREEN.darker());
        yesLabel.setFont(Main.FONT);
        yesLabel.setText("yes");
        noLabel = new javax.swing.JLabel();
      //  noLabel = new CustomButton(Color.RED, Color.RED.darker());
        noLabel.setFont(Main.FONT);
        noLabel.setText("no");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);

        quitGameLabel.setBackground(new java.awt.Color(0, 0, 0));
        quitGameLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 20));
        quitGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        quitGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitGameLabel.setText(dialogText);

        yesLabel.setBackground(new java.awt.Color(0, 0, 0));
       yesLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_YES_BUTTON_PATH))));
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
        noLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NO_BUTTON_PATH)))); // NOI18N
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
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(yesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(quitGameLabel)
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
    }// </editor-fold>

    protected void yesLabelMousePressed() {
        Main.audioPlayer.playClick();
        System.out.println("Quit game!");
        System.exit(0);
    }

    protected void yesLabelMouseExited() {
        currentButtonSelected = false;
        yesLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_YES_BUTTON_PATH)))); // NOI18N
    }

    protected void yesLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = YES;
        yesLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_YES_BUTTON_PATH)))); // NOI18N
    }

    protected void noLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NO;
        noLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_NO_BUTTON_PATH))));

    }

    protected void noLabelMouseExited() {
        currentButtonSelected = false;
        noLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NO_BUTTON_PATH))));
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