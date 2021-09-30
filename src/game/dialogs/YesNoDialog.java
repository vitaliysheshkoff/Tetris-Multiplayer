package game.dialogs;

import game.frames.TetrisFrame;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class YesNoDialog extends JDialog implements KeyListener {

    protected static int YES = 0, NO = 1;

    protected static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    protected static final String UNSELECTED_YES_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "yesGreenUnselectedRoundedImage (2).png";
    protected static final String SELECTED_YES_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "yesGreenSelectedRoundedImage (2).png";
    protected static final String UNSELECTED_NO_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "noRedUnselectedRoundedImage (2).png";
    protected static final String SELECTED_NO_BUTTON_PATH = BUTTON_IMAGES_FOLDER + "noRedSelectedRoundedImage (2).png";

    protected int buttonController = YES;
    protected boolean currentButtonSelected = true;

    protected JLabel noLabel;
    protected JLabel quitGameLabel;
    protected JLabel yesLabel;

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

        quitGameLabel = new JLabel("");
        yesLabel = new JLabel();
        noLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);

        quitGameLabel.setBackground(Color.BLACK);
        quitGameLabel.setFont(Main.COSMIC_SAN_MS_FONT_20);
        quitGameLabel.setForeground(Color.WHITE);
        quitGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quitGameLabel.setText(dialogText);

        yesLabel.setBackground(Color.BLACK);
        yesLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_YES_BUTTON_PATH))));
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

        noLabel.setBackground(Color.BLACK);
        noLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NO_BUTTON_PATH))));
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(yesLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(quitGameLabel)
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(quitGameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
        yesLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_YES_BUTTON_PATH))));
    }

    protected void yesLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = YES;
        yesLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_YES_BUTTON_PATH))));
    }

    protected void noLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = NO;
        noLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_NO_BUTTON_PATH))));

    }

    protected void noLabelMouseExited() {
        currentButtonSelected = false;
        noLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_NO_BUTTON_PATH))));
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