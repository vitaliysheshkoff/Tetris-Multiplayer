package game.panels.menu.elements;

import game.helperclasses.JTextFieldLimit;
import game.helperclasses.PaintStaticLetters;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class MultiplayerPanel  extends JPanel implements KeyListener{

    private JTextField addressTextField;
    private JLabel createButton;
    private JLabel joinButton;
    private JLabel mainMenuButton;
    private JTextField nicknameTextField;
    private JTextField portTextField;

    private JLabel createAddressLabel;
    private JTextField createAddressTextField;
    private JLabel createGameLabel;
    private JLabel createPortLabel;
    private JTextField createPortTextField;
    public JLabel ipLabel;

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";
    private static final String UNSELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createBlackRoundedImage.png";
    private static final String SELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createWhiteRoundedImage.png";
    private static final String UNSELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinBlackRoundedImage.png";
    private static final String SELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinWhiteRoundedImage.png";

    public String joinAddress = "";
    public String joinPort = "";
    public String createAddress = "";
    public String createPort = "";
    public String nickname = "";

    public MultiplayerPanel() {
        initComponents();
        addKeyListener(this);
    }

    private void initComponents() {

        nicknameTextField = new JTextField();
        addressTextField = new JTextField();
        portTextField = new JTextField();

        createAddressLabel = new JLabel();
        createAddressTextField = new JTextField();
        createPortLabel = new JLabel();
        createPortTextField = new JTextField();
        ipLabel = new JLabel();

        nicknameTextField.setDocument(new JTextFieldLimit(20));
        addressTextField.setDocument(new JTextFieldLimit(20));
        portTextField.setDocument(new JTextFieldLimit(6));


        JLabel portLabel = new JLabel();
        JLabel addressLabel = new JLabel();
        JLabel joinGameLabel = new JLabel();
        JLabel createGameLabel = new JLabel();
        JLabel nicknameLabel = new JLabel();
        createButton = new JLabel();
        joinButton = new JLabel();
        mainMenuButton = new JLabel();

        setBackground(new Color(0, 0, 0));

        nicknameTextField.setFont(new Font("Consolas", Font.PLAIN, 20));
        nicknameTextField.setHorizontalAlignment(JTextField.CENTER);
        nicknameTextField.setText("nickname");
        nicknameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    nicknameTextFieldActionPerformed();
            }
        });

        addressTextField.setFont(new Font("Consolas", Font.PLAIN, 20));
        addressTextField.setHorizontalAlignment(JTextField.CENTER);
        addressTextField.setText("address");
        addressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addressTextFieldActionPerformed();
            }
        });

        portTextField.setFont(new Font("Consolas", Font.PLAIN, 20));
        portTextField.setHorizontalAlignment(JTextField.CENTER);
        portTextField.setText("port");
        portTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    portTextFieldActionPerformed();
            }
        });

        nicknameTextField.selectAll();
        addressTextField.selectAll();
        portTextField.selectAll();

        portLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        portLabel.setForeground(new Color(255, 255, 255));
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setText("Port");

        addressLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        addressLabel.setForeground(new Color(255, 255, 255));
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addressLabel.setText("Address");

        joinGameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));
        joinGameLabel.setForeground(new Color(255, 255, 255));
        joinGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        joinGameLabel.setText("Join game");

        createGameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));
        createGameLabel.setForeground(new Color(255, 255, 255));
        createGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createGameLabel.setText("Create game");

        nicknameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        nicknameLabel.setForeground(new Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nicknameLabel.setText("Nickname");

        createButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH))));
        createButton.setText("jLabel1");
        createButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createButtonMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                createButtonMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1)
                    createButtonMousePressed();
            }
        });

        joinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH))));
        joinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                joinButtonMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                joinButtonMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1)
                    joinButtonMousePressed();
            }
        });

        mainMenuButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuButtonMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuButtonMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        createAddressLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        createAddressLabel.setForeground(new Color(255, 255, 255));
        createAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAddressLabel.setText("Opponent address");

        createAddressTextField.setFont(new Font("Consolas", Font.PLAIN, 20));
        createAddressTextField.setHorizontalAlignment(JTextField.CENTER);
        createAddressTextField.setText("address");

        createPortLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        createPortLabel.setForeground(new Color(255, 255, 255));
        createPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createPortLabel.setText("Port");

        createPortTextField.setFont(new Font("Consolas", Font.PLAIN, 20));
        createPortTextField.setHorizontalAlignment(JTextField.CENTER);
        createPortTextField.setText("port");

        ipLabel.setBackground(new Color(0, 0, 0));
        ipLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        ipLabel.setForeground(new Color(255, 255, 255));
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ipLabel.setText("thisMachineAddress");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(createGameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createAddressLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(createPortLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createAddressTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createPortTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(joinGameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addGap(134, 134, 134))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nicknameTextField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(nicknameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(360, 360, 360))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(311, 311, 311)
                                .addComponent(ipLabel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(mainMenuButton)
                                        .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(joinButton)
                                .addGap(184, 184, 184))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ipLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(317, 317, 317)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(joinGameLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createGameLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createAddressLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                .addGap(11, 11, 11)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addressTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createAddressTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addGap(36, 36, 36))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(nicknameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nicknameTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(241, 241, 241)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createPortLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createPortTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(joinButton)
                                        .addComponent(createButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(mainMenuButton)
                                .addGap(29, 29, 29))
        );
    }

    private void nicknameTextFieldActionPerformed() {

        if (nicknameTextField.getText().equals("nickname"))
            nicknameTextField.selectAll();
    }

    private void addressTextFieldActionPerformed() {

        if (addressTextField.getText().equals("address"))
            addressTextField.selectAll();
    }

    private void portTextFieldActionPerformed() {

        if (portTextField.getText().equals("port"))
            portTextField.selectAll();
    }

    private void mainMenuButtonMouseEntered() {
        mainMenuButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuButtonMouseExited() {
        mainMenuButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuButtonMousePressed() {
        Main.audioPlayer.playClick();
        Main.tetrisFrame.remove(Main.multiplayerPanel);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
    }

    private void joinButtonMouseEntered() {
        joinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_JOIN_PATH))));
    }

    private void joinButtonMouseExited() {
        joinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH))));
    }

    private void joinButtonMousePressed() {
        goTetrisMultiplayerPanel(false);
    }

    private void createButtonMousePressed() {
        goTetrisMultiplayerPanel(true);
    }

    private void createButtonMouseExited() {
        createButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH))));
    }

    private void createButtonMouseEntered() {
        createButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_CREATE_PATH))));
    }

    private void goTetrisMultiplayerPanel(boolean thisAppServer) {

        Main.audioPlayer.playClick();

        if(!thisAppServer) {
            joinAddress = addressTextField.getText();
            joinPort = portTextField.getText();
        }
        else {
            createAddress = createAddressTextField.getText();
            createPort = createPortTextField.getText();
        }

        nickname = nicknameTextField.getText();

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.thisAppServer = thisAppServer;

        Main.tetrisFrame.remove(Main.multiplayerPanel);
        Main.tetrisFrame.add(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
        Main.tetrisFrame.pack();
        Main.tetrisFrame.setLocationRelativeTo(null);
        
        createButtonMouseExited();
        joinButtonMouseExited();

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.startNewGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.drawLine(getWidth() / 2, 340, getWidth() / 2, 750);

        paintMultiplayerTitle(g2d);
        
    }

    private void paintMultiplayerTitle(Graphics2D g2d) {
        int startX = 15, startY = 80, radius = 20, space = 3;

        //M
        PaintStaticLetters.paintLetterM(g2d, startX, startY, radius);
        //U
        PaintStaticLetters.paintLetterU(g2d, startX + 5 * radius + space, startY, radius);
        //L
        PaintStaticLetters.paintLetterL(g2d, startX + 9 * radius + 2 * space, startY, radius);
        //T
        PaintStaticLetters.paintLetterT(g2d, startX + 12 * radius + 3 * space, startY, radius);
        //I
        PaintStaticLetters.paintLetterI(g2d, startX + 17 * radius + 4 * space, startY, radius);
        //P
        PaintStaticLetters.paintLetterP(g2d, startX + 20 * radius + 5 * space, startY, radius);
        //L
        PaintStaticLetters.paintLetterL(g2d, startX + 24 * radius + 6 * space, startY, radius);
        //A
        PaintStaticLetters.paintLetterA(g2d, startX + 27 * radius + 7 * space, startY, radius);
        //Y
        PaintStaticLetters.paintLetterY(g2d, startX + 30 * radius + 8 * space, startY, radius);
        //E
        PaintStaticLetters.paintLetterE(g2d, startX + 35 * radius + 9 * space, startY, radius);
        //R
        PaintStaticLetters.paintLetterR(g2d, startX + 39 * radius + 10 * space, startY, radius);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.exitMenuKey) {
            mainMenuButtonMousePressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
