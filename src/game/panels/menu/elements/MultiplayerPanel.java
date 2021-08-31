package game.panels.menu.elements;

import game.helperclasses.JTextFieldLimit;
import game.helperclasses.PaintStaticLetters;
import game.start.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class MultiplayerPanel  extends javax.swing.JPanel implements KeyListener{

    private javax.swing.JTextField addressTextField;
    private javax.swing.JLabel createButton;
    private javax.swing.JLabel joinButton;
    private javax.swing.JLabel mainMenuButton;
    private javax.swing.JTextField nicknameTextField;
    private javax.swing.JTextField portTextField;

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";
    private static final String UNSELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createBlackRoundedImage.png";
    private static final String SELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createWhiteRoundedImage.png";

    private static final String UNSELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinBlackRoundedImage.png";
    private static final String SELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinWhiteRoundedImage.png";

    public String address = "";
    public String port = "";
    public String nickname = "";

    public MultiplayerPanel() {
        initComponents();
        addKeyListener(this);
    }

    private void initComponents() {


        nicknameTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        portTextField = new javax.swing.JTextField();

        nicknameTextField.setDocument(new JTextFieldLimit(20));
        addressTextField.setDocument(new JTextFieldLimit(20));
        portTextField.setDocument(new JTextFieldLimit(6));


        javax.swing.JLabel portLabel = new javax.swing.JLabel();
        javax.swing.JLabel addressLabel = new javax.swing.JLabel();
        javax.swing.JLabel joinGameLabel = new javax.swing.JLabel();
        javax.swing.JLabel createGameLabel = new javax.swing.JLabel();
        javax.swing.JLabel nicknameLabel = new javax.swing.JLabel();
        createButton = new javax.swing.JLabel();
        joinButton = new javax.swing.JLabel();
        mainMenuButton = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        nicknameTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nicknameTextField.setText("nickname");
        nicknameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    nicknameTextFieldActionPerformed();
            }
        });

        addressTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        addressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        addressTextField.setText("address");
        addressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                addressTextFieldActionPerformed();
            }
        });

        portTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        portTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        portLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        portLabel.setForeground(new java.awt.Color(255, 255, 255));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        portLabel.setText("Port");

        addressLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        addressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addressLabel.setText("Address");

        joinGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 36)); // NOI18N
        joinGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        joinGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joinGameLabel.setText("Join game");

        createGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 36)); // NOI18N
        createGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        createGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createGameLabel.setText("Create game");

        nicknameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20)); // NOI18N
        nicknameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nicknameLabel.setText("Nickname");

        createButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH)))); // NOI18N
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

        joinButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH)))); // NOI18N
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

        mainMenuButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH)))); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(nicknameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                        .addComponent(nicknameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(360, 360, 360))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(joinButton)
                                                .addGap(184, 184, 184))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(createGameLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(mainMenuButton)
                                                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(joinGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(363, 363, 363)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(joinGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(11, 11, 11)
                                                .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(nicknameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(157, 157, 157)
                                                .addComponent(createButton)
                                                .addGap(44, 44, 44)))
                                .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(joinButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(mainMenuButton)
                                .addGap(29, 29, 29))
        );
    }// </editor-fold>                        

    private void nicknameTextFieldActionPerformed() {

        if (nicknameTextField.getText().equals("nickname"))
            nicknameTextField.selectAll();
        //  nicknameTextField.setText("");
    }

    private void addressTextFieldActionPerformed() {

        if (addressTextField.getText().equals("address"))
            addressTextField.selectAll();
        //addressTextField.setText("");
    }

    private void portTextFieldActionPerformed() {

        if (portTextField.getText().equals("port"))
            portTextField.selectAll();
        //  portTextField.setText("");
    }

    private void mainMenuButtonMouseEntered() {
        mainMenuButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH)))); // NOI18N
    }

    private void mainMenuButtonMouseExited() {
        mainMenuButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH)))); // NOI18N
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
        joinButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_JOIN_PATH)))); // NOI18N
    }

    private void joinButtonMouseExited() {
        joinButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH)))); // NOI18N
    }

    private void joinButtonMousePressed() {
        goTetrisMultiplayerPanel(false);
    }

    private void createButtonMousePressed() {
        goTetrisMultiplayerPanel(true);
    }

    private void createButtonMouseExited() {
        createButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH)))); // NOI18N
    }

    private void createButtonMouseEntered() {
        createButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_CREATE_PATH)))); // NOI18N
    }

    private void goTetrisMultiplayerPanel(boolean thisAppServer) {

        Main.audioPlayer.playClick();

        address = addressTextField.getText();
        port = portTextField.getText();
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
