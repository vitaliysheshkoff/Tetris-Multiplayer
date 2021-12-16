package game.panels.menu.elements;

import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.util.UtilityException;
import game.helperclasses.JTextFieldLimit;
import game.helperclasses.PaintStaticLetters;
import game.multiplayer.stun.StunTest;
import game.start.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.*;
import java.util.Objects;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Multiplayer2 extends javax.swing.JPanel implements KeyListener {

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";
    private static final String UNSELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createBlackRoundedImage.png";
    private static final String SELECTED_CREATE_PATH = BUTTON_IMAGES_FOLDER + "createWhiteRoundedImage.png";
    private static final String UNSELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinBlackRoundedImage.png";
    private static final String SELECTED_JOIN_PATH = BUTTON_IMAGES_FOLDER + "joinWhiteRoundedImage.png";
    private static final String LOCAL_CONNECTION_JOIN_PATH = BUTTON_IMAGES_FOLDER + "localConnection.png";
    private static final String GLOBAL_CONNECTION_JOIN_PATH = BUTTON_IMAGES_FOLDER + "globalConnection.png";

    // Variables declaration - do not modify
    private javax.swing.JLabel globalAddressLabel;
    private javax.swing.JTextField globalAddressTextField;
    private javax.swing.JLabel globalCreateAddressLabel;
    private javax.swing.JTextField globalCreateAddressTextField;
    private javax.swing.JLabel globalCreateButton;
    private javax.swing.JLabel globalCreateGameLabel;
    private javax.swing.JLabel globalCreatePortLabel;
    private javax.swing.JTextField globalCreatePortTextField;
    private javax.swing.JLabel globalJoinButton;
    private javax.swing.JLabel globalJoinGameLabel;
    private javax.swing.JLabel globalPortLabel;
    private javax.swing.JTextField globalPortTextField;
    private javax.swing.JPanel internetPanel;

    private javax.swing.JLabel localAddressLabel;
    private javax.swing.JTextField localAddressTextField;
    private javax.swing.JLabel localCreateButton;
    private javax.swing.JLabel localCreateGameLabel;
    private javax.swing.JLabel localJoinButton;
    private javax.swing.JLabel localJoinGameLabel;
    private javax.swing.JPanel localPanel;
    private javax.swing.JLabel localPortLabel;
    private javax.swing.JTextField localPortTextField;
    private javax.swing.JLabel mainMenuButton;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JTextField nicknameTextField;
    private javax.swing.JLabel switchLabel;
    private javax.swing.JTabbedPane tabbedPanel;
    // End of variables declaration


    public javax.swing.JLabel ipLabel;

    public String joinAddress = "";
    public String joinPort = "";
    public String createAddress = "";
    public String createPort = "";
    public String nickname = "";

    public boolean isLocalGame;

    /**
     * Creates new form Multiplayer2
     */
    public Multiplayer2() {
        initComponents();
        addKeyListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        tabbedPanel = new javax.swing.JTabbedPane();
        localPanel = new javax.swing.JPanel();
        localJoinGameLabel = new javax.swing.JLabel();
        localCreateGameLabel = new javax.swing.JLabel();
        localCreateButton = new javax.swing.JLabel();
        localJoinButton = new javax.swing.JLabel();
        localAddressTextField = new javax.swing.JTextField();
        localPortTextField = new javax.swing.JTextField();
        localPortLabel = new javax.swing.JLabel();
        localAddressLabel = new javax.swing.JLabel();
        internetPanel = new javax.swing.JPanel();
        globalJoinGameLabel = new javax.swing.JLabel();
        globalCreateGameLabel = new javax.swing.JLabel();
        globalCreateButton = new javax.swing.JLabel();
        globalJoinButton = new javax.swing.JLabel();
        globalCreateAddressLabel = new javax.swing.JLabel();
        globalAddressTextField = new javax.swing.JTextField();
        globalCreateAddressTextField = new javax.swing.JTextField();
        globalPortTextField = new javax.swing.JTextField();
        globalCreatePortLabel = new javax.swing.JLabel();
        globalPortLabel = new javax.swing.JLabel();
        globalCreatePortTextField = new javax.swing.JTextField();
        globalAddressLabel = new javax.swing.JLabel();
        switchLabel = new javax.swing.JLabel();
        nicknameLabel = new javax.swing.JLabel();
        nicknameTextField = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        mainMenuButton = new javax.swing.JLabel();

        nicknameTextField.setDocument(new JTextFieldLimit(15));


        setBackground(new java.awt.Color(0, 0, 0));
        /*setMaximumSize(new java.awt.Dimension(920, 900));
        setMinimumSize(new java.awt.Dimension(920, 900));
        setPreferredSize(new java.awt.Dimension(920, 900));*/
        setLayout(null);

        tabbedPanel.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        localPanel.setBackground(new java.awt.Color(0, 0, 0));
        localPanel.setLayout(null);

        localJoinGameLabel.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        localJoinGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        localJoinGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localJoinGameLabel.setText("Join game");
        localPanel.add(localJoinGameLabel);
        localJoinGameLabel.setBounds(595, 91, 200, 51);

        localCreateGameLabel.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        localCreateGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        localCreateGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localCreateGameLabel.setText("Create game");
        localPanel.add(localCreateGameLabel);
        localCreateGameLabel.setBounds(116, 91, 220, 51);

        localCreateButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH)))); // NOI18N
        localCreateButton.setText("jLabel1");
        localCreateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                localCreateButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                localCreateButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    localCreateButtonMousePressed(evt);
            }
        });
        localPanel.add(localCreateButton);
        localCreateButton.setBounds(166, 229, 122, 40);

        localJoinButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH)))); // NOI18N
        localJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                localJoinButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                localJoinButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    localJoinButtonMousePressed(evt);
            }
        });
        localPanel.add(localJoinButton);
        localJoinButton.setBounds(649, 423, 96, 40);

        localAddressTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        localAddressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        localAddressTextField.setText("address");
        localPanel.add(localAddressTextField);
        localAddressTextField.setBounds(595, 212, 200, 35);

        localPortTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        localPortTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        localPortTextField.setText("port");
        localPortTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localPortTextFieldActionPerformed(evt);
            }
        });
        localPanel.add(localPortTextField);
        localPortTextField.setBounds(595, 330, 200, 35);

        localPortLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        localPortLabel.setForeground(new java.awt.Color(255, 255, 255));
        localPortLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localPortLabel.setText("Port");
        localPanel.add(localPortLabel);
        localPortLabel.setBounds(595, 283, 200, 41);

        localAddressLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        localAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        localAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localAddressLabel.setText("Opponent Address");
        localPanel.add(localAddressLabel);
        localAddressLabel.setBounds(595, 160, 200, 41);

        tabbedPanel.addTab("local", localPanel);

        internetPanel.setBackground(new java.awt.Color(0, 0, 0));
        internetPanel.setForeground(new java.awt.Color(255, 255, 255));
        internetPanel.setPreferredSize(new java.awt.Dimension(getWidth(), getHeight()));
        internetPanel.setLayout(null);

        globalJoinGameLabel.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        globalJoinGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalJoinGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalJoinGameLabel.setText("Join game");
        internetPanel.add(globalJoinGameLabel);
        globalJoinGameLabel.setBounds(595, 91, 200, 51);

        globalCreateGameLabel.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        globalCreateGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreateGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreateGameLabel.setText("Create game");
        internetPanel.add(globalCreateGameLabel);
        globalCreateGameLabel.setBounds(116, 91, 220, 51);

        globalCreateButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH)))); // NOI18N
        globalCreateButton.setText("jLabel1");
        globalCreateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                globalCreateButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                globalCreateButtonMouseExited();
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    globalCreateButtonMousePressed(evt);
            }
        });
        internetPanel.add(globalCreateButton);
        globalCreateButton.setBounds(116, 423, 122, 40);

        globalJoinButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH)))); // NOI18N
        globalJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                globalJoinButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                globalJoinButtonMouseExited();
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    globalJoinButtonMousePressed(evt);
            }
        });
        internetPanel.add(globalJoinButton);
        globalJoinButton.setBounds(649, 423, 96, 40);

        globalCreateAddressLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalCreateAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreateAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreateAddressLabel.setText("Opponent address");
        internetPanel.add(globalCreateAddressLabel);
        globalCreateAddressLabel.setBounds(116, 160, 220, 41);

        globalAddressTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalAddressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalAddressTextField.setText("address");
        internetPanel.add(globalAddressTextField);
        globalAddressTextField.setBounds(595, 212, 200, 35);

        globalCreateAddressTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalCreateAddressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalCreateAddressTextField.setText("address");
        internetPanel.add(globalCreateAddressTextField);
        globalCreateAddressTextField.setBounds(126, 212, 200, 35);

        globalPortTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalPortTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalPortTextField.setText("port");
        globalPortTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalPortTextFieldActionPerformed(evt);
            }
        });
        internetPanel.add(globalPortTextField);
        globalPortTextField.setBounds(595, 330, 200, 35);

        globalCreatePortLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalCreatePortLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreatePortLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreatePortLabel.setText("Port");
        internetPanel.add(globalCreatePortLabel);
        globalCreatePortLabel.setBounds(126, 283, 200, 41);

        globalPortLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalPortLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalPortLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalPortLabel.setText("Port");
        internetPanel.add(globalPortLabel);
        globalPortLabel.setBounds(595, 283, 200, 41);

        globalCreatePortTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalCreatePortTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalCreatePortTextField.setText("port");
        globalCreatePortTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalCreatePortTextFieldActionPerformed(evt);
            }
        });
        internetPanel.add(globalCreatePortTextField);
        globalCreatePortTextField.setBounds(126, 330, 200, 35);

        globalAddressLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        globalAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalAddressLabel.setText("Opponent Address");
        internetPanel.add(globalAddressLabel);
        globalAddressLabel.setBounds(595, 160, 200, 41);

        tabbedPanel.addTab("internet", internetPanel);

        add(tabbedPanel);
        tabbedPanel.setBounds(0, 310, 985, 510);

        switchLabel.setBackground(new java.awt.Color(0, 0, 0));
        switchLabel.setForeground(new java.awt.Color(255, 255, 255));
        switchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        switchLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(LOCAL_CONNECTION_JOIN_PATH))));
        switchLabel.setText("local");
        switchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    switchLabelMousePressed();
            }
        });
        add(switchLabel);
        switchLabel.setBounds(10, 240, 110, 60);

        nicknameLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        nicknameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nicknameLabel.setText("Nickname");
        add(nicknameLabel);
        nicknameLabel.setBounds(370, 210, 200, 41);

        nicknameTextField.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nicknameTextField.setText("nickname");
        nicknameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicknameTextFieldActionPerformed(evt);
            }
        });
        add(nicknameTextField);
        nicknameTextField.setBounds(370, 260, 200, 35);

        ipLabel.setBackground(new java.awt.Color(0, 0, 0));
        ipLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        ipLabel.setForeground(new java.awt.Color(255, 255, 255));
        ipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //  ipLabel.setText("thisMachineAddress");
        add(ipLabel);
        ipLabel.setBounds(310, 10, 800, 35);

        mainMenuButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH)))); // NOI18N
        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });
        add(mainMenuButton);
        mainMenuButton.setBounds(20, 840, 232, 44);
    }// </editor-fold>

    private boolean internetConnectionTester() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(100);
            connection.connect();
        } catch (IOException e) {
            System.out.println("no internet connection");
            return false;
        }
        return true;
    }

    public void switchLabelMousePressed() {
        // TODO add your handling code here:

        // 0 - local panel
        // 1 - internet panel

        if (tabbedPanel.getSelectedIndex() == 0) {

            // get ip address

            if(internetConnectionTester()) {
                try {
                    MappedAddress mappedAddress = StunTest.getDynamicIp();
                    Main.multiplayerPanel2.ipLabel.setText(mappedAddress.getAddress() + ":" + mappedAddress.getPort());
                } catch (UtilityException | IOException e) {
                    e.printStackTrace();
                }

                tabbedPanel.setSelectedIndex(1);
                switchLabel.setText("internet");
                switchLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(GLOBAL_CONNECTION_JOIN_PATH)))); // NOI18N
            }
            else {
                try {
                    ServerSocket s = new ServerSocket(0);
                    Main.multiplayerPanel2.ipLabel.setText(String.valueOf(Inet4Address.getLocalHost().getHostAddress()) + ":" + s.getLocalPort());
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switchLabel.setText(" no internet connection");
            }

        } else if (tabbedPanel.getSelectedIndex() == 1) {

            // get local ip address
            //  try {
            try {
                ServerSocket s = new ServerSocket(0);
                Main.multiplayerPanel2.ipLabel.setText(String.valueOf(Inet4Address.getLocalHost().getHostAddress()) + ":" + s.getLocalPort());
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            ;
           /* } catch (UnknownHostException e) {
                e.printStackTrace();
            }*/

            tabbedPanel.setSelectedIndex(0);
            switchLabel.setText("local");
            switchLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(LOCAL_CONNECTION_JOIN_PATH)))); // NOI18N
        }

    }

    private void globalCreateButtonMouseEntered(java.awt.event.MouseEvent evt) {
        globalCreateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_CREATE_PATH))));
    }

    private void globalCreateButtonMouseExited() {
        globalCreateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH))));
    }

    private void globalCreateButtonMousePressed(java.awt.event.MouseEvent evt) {
        goTetrisMultiplayerPanel(true, false);
    }

    private void globalJoinButtonMouseEntered(java.awt.event.MouseEvent evt) {
        globalJoinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_JOIN_PATH))));
    }

    private void globalJoinButtonMouseExited() {
        globalJoinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH))));
    }

    private void globalJoinButtonMousePressed(java.awt.event.MouseEvent evt) {
        goTetrisMultiplayerPanel(false, false);
    }

    private void globalPortTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void globalCreatePortTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void localCreateButtonMouseEntered(java.awt.event.MouseEvent evt) {

        localCreateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_CREATE_PATH))));
    }

    private void localCreateButtonMouseExited(java.awt.event.MouseEvent evt) {
        localCreateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_CREATE_PATH))));
    }

    private void localCreateButtonMousePressed(java.awt.event.MouseEvent evt) {
        goTetrisMultiplayerPanel(true, true);
    }

    private void localJoinButtonMouseEntered(java.awt.event.MouseEvent evt) {
        localJoinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_JOIN_PATH))));
    }

    private void localJoinButtonMouseExited(java.awt.event.MouseEvent evt) {
        localJoinButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_JOIN_PATH))));
    }

    private void localJoinButtonMousePressed(java.awt.event.MouseEvent evt) {
        goTetrisMultiplayerPanel(false, true);
    }

    private void localPortTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void nicknameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void mainMenuButtonMouseEntered(java.awt.event.MouseEvent evt) {
        mainMenuButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuButtonMouseExited(java.awt.event.MouseEvent evt) {
        mainMenuButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuButtonMousePressed() {
        Main.audioPlayer.playClick();
        Main.tetrisFrame.remove(Main.multiplayerPanel2);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
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

    private void goTetrisMultiplayerPanel(boolean thisAppServer, boolean thisLocalGame) {

        Main.audioPlayer.playClick();

        if (!thisAppServer) {

            if (thisLocalGame) {
                joinAddress = localAddressTextField.getText();
                joinPort = localPortTextField.getText();
            } else {
                joinAddress = globalAddressTextField.getText();
                joinPort = globalPortTextField.getText();

                globalCreateButtonMouseExited();
                globalJoinButtonMouseExited();
            }

        } else {
            createAddress = globalCreateAddressTextField.getText();
            createPort = globalCreatePortTextField.getText();

            globalJoinButtonMouseExited();
        }

        isLocalGame = thisLocalGame;

        nickname = nicknameTextField.getText();

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.thisAppServer = thisAppServer;

        Main.tetrisFrame.remove(Main.multiplayerPanel2);
        Main.tetrisFrame.add(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.repaint();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
     //   Main.tetrisFrame.setMinimumSize(new Dimension((int) (Main.width / 1.5), (int) (Main.height * 0.6)));
       // Main.tetrisFrame.setExtendedState(Main.tetrisFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
      //  Main.tetrisFrame.setLocationRelativeTo(null);

       Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.startNewGame();


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
