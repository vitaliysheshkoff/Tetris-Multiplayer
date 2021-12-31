package game.panels.menu.elements;

import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.util.UtilityException;
import game.helperclasses.CustomButton2;
import game.helperclasses.JTextFieldLimit;
import game.helperclasses.PaintStaticLetters;
import game.multiplayer.stun.StunTest;
import game.start.Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static game.panels.tetris.TetrisPanel.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Multiplayer2 extends javax.swing.JPanel implements KeyListener {

    private static final String IPV4_PATTERN_SHORTEST =
            "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
    private static final String PORT_PATTERN =
            "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";

    public javax.swing.JTextField globalAddressTextField;
    public javax.swing.JTextField globalCreateAddressTextField;
    private javax.swing.JTextField globalPortTextField;
    private javax.swing.JTextField localAddressTextField;
    private javax.swing.JTextField localPortTextField;
    private javax.swing.JTextField nicknameTextField;
    private javax.swing.JLabel switchLabel;
    private javax.swing.JTabbedPane tabbedPanel;

    public javax.swing.JLabel ipLabel;
    public String joinAddress = "";
    public String joinPort = "";
    public String nickname = "";

    public boolean isLocalGame;

    public Multiplayer2() {
        initComponents();
        addKeyListener(this);
    }

    private void initComponents() {
        JLabel localJoinGameLabel = new JLabel();
        JLabel localCreateGameLabel = new JLabel();
        JLabel globalJoinGameLabel = new JLabel();
        JLabel globalCreateGameLabel = new JLabel();
        JLabel globalCreateAddressLabel = new JLabel();
        JLabel globalCreatePortLabel = new JLabel();
        JLabel globalPortLabel = new JLabel();
        JLabel globalAddressLabel = new JLabel();
        JLabel nicknameLabel = new JLabel();

        CustomButton2 localCreateButton = new CustomButton2();
        CustomButton2 localJoinButton = new CustomButton2();
        CustomButton2 mainMenuButtonInternet = new CustomButton2();
        CustomButton2 mainMenuButtonLocal = new CustomButton2();
        CustomButton2 globalCreateButton = new CustomButton2();
        CustomButton2 globalJoinButton = new CustomButton2();

        JPanel internetPanel = new JPanel();

        switchLabel = new javax.swing.JLabel();
        ipLabel = new javax.swing.JLabel();
        tabbedPanel = new javax.swing.JTabbedPane();

        nicknameTextField = new javax.swing.JTextField();
        localAddressTextField = new javax.swing.JTextField();
        localPortTextField = new javax.swing.JTextField();
        globalAddressTextField = new javax.swing.JTextField();
        globalCreateAddressTextField = new javax.swing.JTextField();
        globalPortTextField = new javax.swing.JTextField();

        nicknameTextField.setDocument(new JTextFieldLimit(15));
        globalCreateAddressTextField.setDocument(new JTextFieldLimit(21));
        globalAddressTextField.setDocument(new JTextFieldLimit(21));

        setBackground(new java.awt.Color(0, 0, 0));

        mainMenuButtonInternet.setText("main menu");
        mainMenuButtonLocal.setText("main menu");
        localCreateButton.setText("create");
        localJoinButton.setText("join");
        globalCreateButton.setText("create");
        globalJoinButton.setText("join");

        Color buttonColor = new Color(0, 0, 0, 100);

        mainMenuButtonInternet.setColor1(buttonColor);
        mainMenuButtonInternet.setColor2(buttonColor);

        mainMenuButtonLocal.setColor1(buttonColor);
        mainMenuButtonLocal.setColor2(buttonColor);

        localCreateButton.setColor1(buttonColor);
        localCreateButton.setColor2(buttonColor);

        localJoinButton.setColor1(buttonColor);
        localJoinButton.setColor2(buttonColor);

        globalCreateButton.setColor1(buttonColor);
        globalCreateButton.setColor2(buttonColor);

        globalJoinButton.setColor1(buttonColor);
        globalJoinButton.setColor2(buttonColor);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        JPanel localPanel = new JPanel();
        JPanel LabelPanel = new TitlePanel();

        Color textfieldColor = globalCreateAddressTextField.getForeground();

        nicknameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (nicknameTextField.getForeground() == textfieldColor)
                    nicknameTextField.setText("");

                nicknameTextField.setForeground(Color.WHITE);
            }
        });

        globalCreateAddressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (globalCreateAddressTextField.getForeground() == textfieldColor)
                    globalCreateAddressTextField.setText("");

                globalCreateAddressTextField.setForeground(Color.WHITE);
            }
        });

        globalAddressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (globalAddressTextField.getForeground() == textfieldColor)
                    globalAddressTextField.setText("");

                globalAddressTextField.setForeground(Color.WHITE);
            }
        });

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout LabelPanelLayout = new javax.swing.GroupLayout(LabelPanel);
        LabelPanel.setLayout(LabelPanelLayout);
        LabelPanelLayout.setHorizontalGroup(
                LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        LabelPanelLayout.setVerticalGroup(
                LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 78, Short.MAX_VALUE)
        );

        tabbedPanel.setToolTipText("");

        localPanel.setBackground(new java.awt.Color(102, 0, 102));

        localCreateGameLabel.setBackground(new java.awt.Color(255, 255, 153));
        localCreateGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        localCreateGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        localCreateGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localCreateGameLabel.setText("Create game");

        localCreateButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localCreateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                localCreateButtonMousePressed();
            }
        });

        localJoinGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        localJoinGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        localJoinGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localJoinGameLabel.setText("Join game");

        localJoinButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        localJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                localJoinButtonMousePressed();
            }
        });

        mainMenuButtonLocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainMenuButtonMousePressed();
            }
        });

        javax.swing.GroupLayout localPanelLayout = new javax.swing.GroupLayout(localPanel);
        localPanel.setLayout(localPanelLayout);
        localPanelLayout.setHorizontalGroup(
                localPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(localPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(localPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(localCreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(localCreateGameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(localPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(localJoinGameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(localJoinButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(localPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainMenuButtonLocal)
                                .addContainerGap(758, Short.MAX_VALUE))
        );
        localPanelLayout.setVerticalGroup(
                localPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(localPanelLayout.createSequentialGroup()
                                .addGroup(localPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, localPanelLayout.createSequentialGroup()
                                                .addContainerGap(202, Short.MAX_VALUE)
                                                .addComponent(localJoinGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(localJoinButton))
                                        .addGroup(localPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(localCreateGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(localCreateButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                                .addComponent(mainMenuButtonLocal)
                                .addContainerGap())
        );

        tabbedPanel.addTab("local", localPanel);

        internetPanel.setOpaque(false);

        globalCreateGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalCreateGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreateGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreateGameLabel.setText("Create game");

        globalCreateAddressLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalCreateAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreateAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreateAddressLabel.setText("Opponent address");

        globalCreateAddressTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalCreateAddressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalCreateAddressTextField.setText("address");
        globalCreateAddressTextField.setToolTipText("ip:port");

        globalCreatePortLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalCreatePortLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalCreatePortLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreatePortLabel.setText("Port");

        globalPortLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalPortLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalPortLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalPortLabel.setText("Port");

        globalAddressTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalAddressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        globalAddressTextField.setText("address");
        globalAddressTextField.setToolTipText("ip:port");

        globalAddressLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalAddressLabel.setText("Opponent Address");

        globalJoinGameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        globalJoinGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        globalJoinGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalJoinGameLabel.setText("Join game");

        globalJoinButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                globalJoinButtonMousePressed();
            }
        });

        globalCreateButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        globalCreateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                globalCreateButtonMousePressed();
            }
        });

        mainMenuButtonInternet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainMenuButtonMousePressed();
            }
        });

        javax.swing.GroupLayout internetPanelLayout = new javax.swing.GroupLayout(internetPanel);
        internetPanel.setLayout(internetPanelLayout);
        internetPanelLayout.setHorizontalGroup(
                internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(internetPanelLayout.createSequentialGroup()
                                .addContainerGap(142, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(globalCreateGameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(globalCreateAddressLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(globalCreateAddressTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(globalCreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(globalAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addComponent(globalAddressTextField)
                                        .addComponent(globalJoinButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(globalJoinGameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(148, Short.MAX_VALUE))
                        .addGroup(internetPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainMenuButtonInternet)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        internetPanelLayout.setVerticalGroup(
                internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(internetPanelLayout.createSequentialGroup()
                                .addContainerGap(132, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(globalCreateGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(globalJoinGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(globalCreateAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(globalAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(globalCreateAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(globalAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(internetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(globalCreateButton)
                                        .addComponent(globalJoinButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                .addComponent(mainMenuButtonInternet)
                                .addContainerGap())
        );

        tabbedPanel.addTab("inernet", internetPanel);
        localPanel.setOpaque(false);

        tabbedPanel.addChangeListener(e -> {
            switchLabelMousePressed();
            System.out.println("change");
        });

        ipLabel.setBackground(new java.awt.Color(0, 0, 0));
        ipLabel.setToolTipText("click to copy");
        ipLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        ipLabel.setForeground(new java.awt.Color(255, 255, 255));
        ipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ipLabel.setText("thisMachineAddress");

        ipLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (tabbedPanel.getSelectedIndex() == 1)
                    ipLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ipLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (ipLabel.getText().equals("copied"))
                    return;

                if (tabbedPanel.getSelectedIndex() == 1) {
                    String ip = ipLabel.getText();
                    new Thread(() -> {
                        StringSelection stringSelection = new StringSelection(ip);
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);

                        ipLabel.setText("copied");

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        ipLabel.setText(ip);
                    }).start();
                }
            }
        });

        nicknameLabel.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        nicknameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nicknameLabel.setText("Nickname");

        nicknameTextField.setFont(new java.awt.Font("Consolas", Font.PLAIN, 10));
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nicknameTextField.setText("player");

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
                upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(upperPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nicknameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                        .addComponent(nicknameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        upperPanelLayout.setVerticalGroup(
                upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(upperPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nicknameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabbedPanel))
        );

        add(backgroundPanel);
    }

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
        // 0 - local panel
        // 1 - internet panel
        if (tabbedPanel.getSelectedIndex() == 1) {

            ipLabel.setToolTipText("click to copy");
            // get ip address
            if (internetConnectionTester()) {
                try {
                    MappedAddress mappedAddress = StunTest.getDynamicIp();
                    ipLabel.setText(mappedAddress.getAddress() + ":" + mappedAddress.getPort());
                } catch (UtilityException | IOException e) {
                    e.printStackTrace();
                }

                tabbedPanel.setSelectedIndex(1);
            } else {
                ipLabel.setToolTipText("");
                try {
                    ServerSocket s = new ServerSocket(0);
                    ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switchLabel.setText(" no internet connection");
            }

        } else if (tabbedPanel.getSelectedIndex() == 0) {
            try {
                ServerSocket s = new ServerSocket(0);
                ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void globalCreateButtonMousePressed() {
        String[] opponentAddress = globalCreateAddressTextField.getText().split(":");

        Pattern ipPattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);

        Pattern portPattern = Pattern.compile(PORT_PATTERN);
        Matcher portMatcher;

        //if not ":"
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception e) {
            globalCreateAddressTextField.setForeground(Color.RED);
            return;
        }

        // if address has mistakes
        if (!portMatcher.find() || !ipMatcher.find()) {
            globalCreateAddressTextField.setForeground(Color.RED);
            return;
        }

        // if the same ip
        if (globalCreateAddressTextField.getText().equals(ipLabel.getText())) {
            globalCreateAddressTextField.setForeground(Color.RED);
            return;
        }

        goTetrisMultiplayerPanel(true, false);
    }

    private void globalJoinButtonMousePressed() {
        String[] opponentAddress = globalAddressTextField.getText().split(":");

        Pattern ipPattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);

        Pattern portPattern = Pattern.compile(PORT_PATTERN);
        Matcher portMatcher;

        //if not ":"
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception e) {
            globalAddressTextField.setForeground(Color.RED);
            return;
        }

        // if address has mistakes
        if (!portMatcher.find() && !ipMatcher.find()) {
            globalAddressTextField.setForeground(Color.RED);
            return;
        }

        // if the same ip
        if (globalAddressTextField.getText().equals(ipLabel.getText())) {
            globalAddressTextField.setForeground(Color.RED);
            return;
        }

        goTetrisMultiplayerPanel(false, false);
    }

    private void localCreateButtonMousePressed() {
        goTetrisMultiplayerPanel(true, true);
    }

    private void localJoinButtonMousePressed() {
        goTetrisMultiplayerPanel(false, true);
    }

    private void mainMenuButtonMousePressed() {
        Main.audioPlayer.playClick();
        Main.tetrisFrame.remove(Main.multiplayerPanel2);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
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
            }
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

    static class TitlePanel extends JPanel {

        public TitlePanel() {
            setOpaque(false);
        }

        int w;
        int h;
        int s;

        Dimension d;
        Container c;

        @Override
        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
            } else {
                return new Dimension(10, 20);
            }

            w = (int) d.getWidth();
            h = (int) d.getHeight();
            s = (Math.min(w, h));

            return new Dimension(s, (s) / 6);
        }

        private void paintMultiplayerTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 43;

            //M
            PaintStaticLetters.paintLetterM(g2d, startX, startY, radius);
            //U
            PaintStaticLetters.paintLetterU(g2d, startX + 5 * square_radius + space, startY, square_radius);
            //L
            PaintStaticLetters.paintLetterL(g2d, startX + 9 * square_radius + 2 * space, startY, square_radius);
            //T
            PaintStaticLetters.paintLetterT(g2d, startX + 12 * square_radius + 3 * space, startY, square_radius);
            //I
            PaintStaticLetters.paintLetterI(g2d, startX + 17 * square_radius + 4 * space, startY, square_radius);
            //P
            PaintStaticLetters.paintLetterP(g2d, startX + 20 * square_radius + 5 * space, startY, square_radius);
            //L
            PaintStaticLetters.paintLetterL(g2d, startX + 24 * square_radius + 6 * space, startY, square_radius);
            //A
            PaintStaticLetters.paintLetterA(g2d, startX + 27 * square_radius + 7 * space, startY, square_radius);
            //Y
            PaintStaticLetters.paintLetterY(g2d, startX + 30 * square_radius + 8 * space, startY, square_radius);
            //E
            PaintStaticLetters.paintLetterE(g2d, startX + 35 * square_radius + 9 * space, startY, square_radius);
            //R
            PaintStaticLetters.paintLetterR(g2d, startX + 39 * square_radius + 10 * space, startY, square_radius);
        }

        int radius;
        int startX;

        @Override
        protected void paintComponent(Graphics g) {

            radius = Math.min(getWidth() / 44, getHeight() / 6);

            startX = (getWidth() - radius * 44) / 2;

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

            paintMultiplayerTitle(g2d, startX, radius, radius);
        }
    }

    public static class BackgroundPanel extends JPanel {

        BufferedImage bufferedImage = null;
        BufferedImage backgroundImage, backgroundImage2, backgroundImage3, backgroundImage4, backgroundImage5;

        BackgroundPanel() {

            try {
                backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH)));
                backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_2_PATH)));
                backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_3_PATH)));
                backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_4_PATH)));
                backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_5_PATH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            if (Main.tetrisPanel.backgroundType == BACKGROUND) {
                bufferedImage = backgroundImage;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND2) {
                bufferedImage = backgroundImage2;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND3) {
                bufferedImage = backgroundImage3;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND4) {
                bufferedImage = backgroundImage4;

            } else if (Main.tetrisPanel.backgroundType == BACKGROUND5) {
                bufferedImage = backgroundImage5;

            }

            for (int i = 0; i < Main.monitorHeight / bufferedImage.getHeight() + 1; i++) {
                for (int j = 0; j < Main.monitorWidth / bufferedImage.getWidth() + 1; j++) {

                    g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
            g2d.setFont(Main.FONT);
        }
    }
}
