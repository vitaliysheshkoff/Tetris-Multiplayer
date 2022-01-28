//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package game.panels.tetris.multiplayer.preparepanel;

import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.util.UtilityException;
import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.helperclasses.buttons.MyButton;
import game.helperclasses.textfieldlimit.JTextFieldLimit;
/*import game.panels.tetris.multiplayer.preparepanel.Multiplayer.1;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.10;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.11;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.12;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.13;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.14;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.15;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.16;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.17;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.18;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.2;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.3;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.4;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.5;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.6;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.7;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.8;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer.9;*/
import game.panels.tetris.multiplayer.stun.StunTest;
import game.start.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Multiplayer extends JPanel implements KeyListener {
    private static final String IPV4_PATTERN_SHORTEST = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
    private static final String PORT_PATTERN = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
    private static final String HAMACHI_EXAMPLE_PATH = "D:\\IdeaProjects\\ResizeTetris-Multiplayer\\Tetris-Multiplayer\\src\\resources\\backgroundImages\\hamachiExample.png";
    public static final byte LOCAL = 0;
    public static final byte NET_HOLE_PUNCHING = 1;
    public static final byte HAMACHI = 2;
    public static final byte WEB = 3;
    public byte typeOfGame = 0;
    public JTextField globalAddressTextField;
    public JTextField globalCreateAddressTextField;
    private JTextField localAddressTextField;
    public JTextField nicknameTextField;
    public JTextField vpnAddressTextField;
    public JTextField joinRoomTextField;
    public JTabbedPane tabbedPanel;
    public JLabel ipLabel;
    public String nickname = "";

    public Multiplayer() {
        this.initComponents();
        this.addKeyListener(this);
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
        JLabel vpnCreateGameLabel = new JLabel();
        JLabel nicknameLabel = new JLabel();
        JLabel vpnAddressLabel = new JLabel();
        JLabel vpnJoinGameLabel = new JLabel();
        MyButton localCreateButton = new MyButton("create");
        MyButton localJoinButton = new MyButton("join");
        MyButton mainMenuButtonInternet = new MyButton("main menu");
        MyButton mainMenuButtonLocal = new MyButton("main menu");
        MyButton globalCreateButton = new MyButton("create");
        MyButton globalJoinButton = new MyButton("join");
        MyButton vpnCreateButton = new MyButton("create");
        MyButton mainMenuButtonvpn = new MyButton("main menu");
        MyButton vpnJoinButton = new MyButton("join");
        JPanel internetPanel = new JPanel();
        JPanel vpnPanel = new JPanel();
        this.ipLabel = new JLabel();
        this.tabbedPanel = new JTabbedPane();
        this.nicknameTextField = new JTextField();
        this.localAddressTextField = new JTextField();
        this.globalAddressTextField = new JTextField();
        this.globalCreateAddressTextField = new JTextField();
        this.vpnAddressTextField = new JTextField();
        this.joinRoomTextField = new JTextField();
        this.nicknameTextField.setDocument(new JTextFieldLimit(15));
        this.globalCreateAddressTextField.setDocument(new JTextFieldLimit(21));
        this.globalAddressTextField.setDocument(new JTextFieldLimit(21));
        this.vpnAddressTextField.setDocument(new JTextFieldLimit(21));
        this.joinRoomTextField.setDocument(new JTextFieldLimit(3));
        this.vpnAddressTextField.setToolTipText("IP_4");
        this.vpnAddressTextField.setToolTipText("<html><img src=\"" + Main.class.getResource("/resources/backgroundImages/img.png") + "\">");
        this.vpnAddressTextField.setToolTipText("IP_4");
        this.setBackground(new Color(0, 0, 0));
        game.panels.tetris.multiplayer.preparepanel.Multiplayer.BackgroundPanel backgroundPanel = new game.panels.tetris.multiplayer.preparepanel.Multiplayer.BackgroundPanel();
        JPanel localPanel = new JPanel();
        JPanel labelPanel = new game.panels.tetris.multiplayer.preparepanel.Multiplayer.TitlePanel();
        Color textfieldColor = this.globalCreateAddressTextField.getForeground();
       // this.nicknameTextField.addMouseListener(new 1(this, textfieldColor));
       // this.globalCreateAddressTextField.addMouseListener(new 2(this, textfieldColor));
       // this.globalAddressTextField.addMouseListener(new 3(this, textfieldColor));
       // this.vpnAddressTextField.addMouseListener(new 4(this, textfieldColor));
       // this.joinRoomTextField.addMouseListener(new 5(this, textfieldColor));
        this.setLayout(new BoxLayout(this, 2));
        GroupLayout LabelPanelLayout = new GroupLayout(labelPanel);
        labelPanel.setLayout(LabelPanelLayout);
        LabelPanelLayout.setHorizontalGroup(LabelPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 0, 32767));
        LabelPanelLayout.setVerticalGroup(LabelPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 78, 32767));
        this.tabbedPanel.setToolTipText("");
        localCreateGameLabel.setFont(new Font("Consolas", 0, 10));
        localCreateGameLabel.setForeground(new Color(255, 255, 255));
        localCreateGameLabel.setHorizontalAlignment(0);
        localCreateGameLabel.setText("Create game");
        localCreateButton.setHorizontalAlignment(0);
      //  localCreateButton.addMouseListener(new 6(this));
        localJoinGameLabel.setFont(new Font("Consolas", 0, 10));
        localJoinGameLabel.setForeground(new Color(255, 255, 255));
        localJoinGameLabel.setHorizontalAlignment(0);
        localJoinGameLabel.setText("Join game");
        localJoinButton.setHorizontalAlignment(0);
     //   localJoinButton.addMouseListener(new 7(this));
      //  mainMenuButtonLocal.addMouseListener(new 8(this));
        GroupLayout localPanelLayout = new GroupLayout(localPanel);
        localPanel.setLayout(localPanelLayout);
        localPanelLayout.setHorizontalGroup(localPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(localPanelLayout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(localCreateButton, -1, 150, 32767).addComponent(localCreateGameLabel, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(localJoinGameLabel, -1, -1, 32767).addComponent(localJoinButton, -1, 150, 32767)).addContainerGap(-1, 32767)).addGroup(localPanelLayout.createSequentialGroup().addContainerGap().addComponent(mainMenuButtonLocal).addContainerGap(758, 32767)));
        localPanelLayout.setVerticalGroup(localPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(localPanelLayout.createSequentialGroup().addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, localPanelLayout.createSequentialGroup().addContainerGap(202, 32767).addComponent(localJoinGameLabel, -2, 60, -2).addGap(18, 18, 18).addComponent(localJoinButton)).addGroup(localPanelLayout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(localCreateGameLabel, -2, 60, -2).addGap(18, 18, 18).addComponent(localCreateButton))).addPreferredGap(ComponentPlacement.RELATED, 177, 32767).addComponent(mainMenuButtonLocal).addContainerGap()));
        this.tabbedPanel.addTab("local", localPanel);
        internetPanel.setOpaque(false);
        globalCreateGameLabel.setFont(new Font("Consolas", 0, 10));
        globalCreateGameLabel.setForeground(new Color(255, 255, 255));
        globalCreateGameLabel.setHorizontalAlignment(0);
        globalCreateGameLabel.setText("Create game");
        globalCreateAddressLabel.setFont(new Font("Consolas", 0, 10));
        globalCreateAddressLabel.setForeground(new Color(255, 255, 255));
        globalCreateAddressLabel.setHorizontalAlignment(0);
        globalCreateAddressLabel.setText("Opponent address");
        this.globalCreateAddressTextField.setFont(new Font("Consolas", 0, 10));
        this.globalCreateAddressTextField.setHorizontalAlignment(0);
        this.globalCreateAddressTextField.setText("address");
        this.globalCreateAddressTextField.setToolTipText("ip:port");
        globalCreatePortLabel.setFont(new Font("Consolas", 0, 10));
        globalCreatePortLabel.setForeground(new Color(255, 255, 255));
        globalCreatePortLabel.setHorizontalAlignment(0);
        globalCreatePortLabel.setText("Port");
        globalPortLabel.setFont(new Font("Consolas", 0, 10));
        globalPortLabel.setForeground(new Color(255, 255, 255));
        globalPortLabel.setHorizontalAlignment(0);
        globalPortLabel.setText("Port");
        this.globalAddressTextField.setFont(new Font("Consolas", 0, 10));
        this.globalAddressTextField.setHorizontalAlignment(0);
        this.globalAddressTextField.setText("address");
        this.globalAddressTextField.setToolTipText("ip:port");
        globalAddressLabel.setFont(new Font("Consolas", 0, 10));
        globalAddressLabel.setForeground(new Color(255, 255, 255));
        globalAddressLabel.setHorizontalAlignment(0);
        globalAddressLabel.setText("Opponent Address");
        globalJoinGameLabel.setFont(new Font("Consolas", 0, 10));
        globalJoinGameLabel.setForeground(new Color(255, 255, 255));
        globalJoinGameLabel.setHorizontalAlignment(0);
        globalJoinGameLabel.setText("Join game");
        globalJoinButton.setHorizontalAlignment(0);
      //  globalJoinButton.addMouseListener(new 9(this));
        globalCreateButton.setHorizontalAlignment(0);
      //  globalCreateButton.addMouseListener(new 10(this));
      //  mainMenuButtonInternet.addMouseListener(new 11(this));
        GroupLayout internetPanelLayout = new GroupLayout(internetPanel);
        internetPanel.setLayout(internetPanelLayout);
        internetPanelLayout.setHorizontalGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(internetPanelLayout.createSequentialGroup().addContainerGap(142, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(globalCreateGameLabel, -1, -1, 32767).addComponent(globalCreateAddressLabel, Alignment.TRAILING, -1, -1, 32767).addComponent(this.globalCreateAddressTextField, Alignment.TRAILING).addComponent(globalCreateButton, -1, 250, 32767)).addPreferredGap(ComponentPlacement.RELATED, 254, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(globalAddressLabel, -1, 250, 32767).addComponent(this.globalAddressTextField).addComponent(globalJoinButton, -1, -1, 32767).addComponent(globalJoinGameLabel, -1, -1, 32767)).addContainerGap(148, 32767)).addGroup(internetPanelLayout.createSequentialGroup().addContainerGap().addComponent(mainMenuButtonInternet).addContainerGap(-1, 32767)));
        internetPanelLayout.setVerticalGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(internetPanelLayout.createSequentialGroup().addContainerGap(132, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE).addComponent(globalCreateGameLabel, -2, 43, -2).addComponent(globalJoinGameLabel, -2, 51, -2)).addPreferredGap(ComponentPlacement.UNRELATED, -1, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE).addComponent(globalCreateAddressLabel, -2, 41, -2).addComponent(globalAddressLabel, -2, 41, -2)).addPreferredGap(ComponentPlacement.RELATED, 44, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.globalCreateAddressTextField, -2, 35, -2).addComponent(this.globalAddressTextField, -2, 35, -2)).addPreferredGap(ComponentPlacement.RELATED, 45, 32767).addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(globalCreateButton).addComponent(globalJoinButton)).addPreferredGap(ComponentPlacement.RELATED, 124, 32767).addComponent(mainMenuButtonInternet).addContainerGap()));
        this.tabbedPanel.addTab("inernet", internetPanel);
        localPanel.setOpaque(false);
        vpnPanel.setOpaque(false);
        vpnPanel.setBackground(new Color(0, 153, 153));
        vpnCreateGameLabel.setFont(new Font("Consolas", 0, 10));
        vpnCreateGameLabel.setForeground(new Color(255, 255, 255));
        vpnCreateGameLabel.setHorizontalAlignment(0);
        vpnCreateGameLabel.setText("Create game");
        vpnCreateButton.setHorizontalAlignment(0);
        //vpnCreateButton.addMouseListener(new 12(this));
       // mainMenuButtonvpn.addMouseListener(new 13(this));
        vpnJoinButton.setHorizontalAlignment(0);
      //  vpnJoinButton.addMouseListener(new 14(this));
        this.vpnAddressTextField.setFont(new Font("Consolas", 0, 10));
        this.vpnAddressTextField.setHorizontalAlignment(0);
        this.vpnAddressTextField.setText("address");
        vpnAddressLabel.setFont(new Font("Consolas", 0, 10));
        vpnAddressLabel.setForeground(new Color(255, 255, 255));
        vpnAddressLabel.setHorizontalAlignment(0);
        vpnAddressLabel.setText("Opponent Address");
        vpnJoinGameLabel.setFont(new Font("Consolas", 0, 10));
        vpnJoinGameLabel.setForeground(new Color(255, 255, 255));
        vpnJoinGameLabel.setHorizontalAlignment(0);
        vpnJoinGameLabel.setText("Join game");
        GroupLayout vpnPanelLayout = new GroupLayout(vpnPanel);
        vpnPanel.setLayout(vpnPanelLayout);
        vpnPanelLayout.setHorizontalGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(vpnPanelLayout.createSequentialGroup().addContainerGap(142, 32767).addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(vpnCreateGameLabel, -1, -1, 32767).addComponent(vpnCreateButton, -1, 250, 32767)).addPreferredGap(ComponentPlacement.RELATED, 254, 32767).addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING, false).addComponent(vpnAddressLabel, -1, 250, 32767).addComponent(this.vpnAddressTextField).addComponent(vpnJoinButton, -1, -1, 32767).addComponent(vpnJoinGameLabel, -1, -1, 32767)).addContainerGap(148, 32767)).addGroup(vpnPanelLayout.createSequentialGroup().addContainerGap().addComponent(mainMenuButtonvpn).addContainerGap(-1, 32767)));
        vpnPanelLayout.setVerticalGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(vpnPanelLayout.createSequentialGroup().addContainerGap(157, 32767).addGroup(vpnPanelLayout.createParallelGroup(Alignment.BASELINE).addComponent(vpnCreateGameLabel, -2, 43, -2).addComponent(vpnJoinGameLabel, -2, 51, -2)).addPreferredGap(ComponentPlacement.UNRELATED, 15, 32767).addComponent(vpnAddressLabel, -2, 41, -2).addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(vpnPanelLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.vpnAddressTextField, -2, 35, -2).addPreferredGap(ComponentPlacement.RELATED, 42, 32767)).addGroup(vpnPanelLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(vpnCreateButton).addPreferredGap(ComponentPlacement.RELATED, 72, 32767))).addComponent(vpnJoinButton).addPreferredGap(ComponentPlacement.RELATED, 117, 32767).addComponent(mainMenuButtonvpn).addContainerGap()));
        this.tabbedPanel.addTab("vpn", vpnPanel);
        JPanel webPanel = new JPanel();
        webPanel.setOpaque(false);
        MyButton createRoomButton = new MyButton("create room");
        webPanel.add(createRoomButton);
        MyButton joinRoomButton = new MyButton("join room");
        webPanel.add(this.joinRoomTextField);
        webPanel.add(joinRoomButton);
        this.tabbedPanel.addTab("web", webPanel);
       // joinRoomButton.addMouseListener(new 15(this));
       // createRoomButton.addMouseListener(new 16(this));
        JPanel telegramPanel = new JPanel();
        telegramPanel.setOpaque(false);
        MyButton telegramRequestButton = new MyButton("telegram request");
        telegramPanel.add(telegramRequestButton);
        this.tabbedPanel.addTab("Telegram", telegramPanel);
       // telegramRequestButton.addMouseListener(new 17(this));
        this.tabbedPanel.addChangeListener((e) -> {
            this.switchLabelMousePressed();
            System.out.println("change");
        });
        this.ipLabel.setBackground(new Color(0, 0, 0));
        this.ipLabel.setToolTipText("click to copy");
        this.ipLabel.setFont(new Font("Consolas", 0, 10));
        this.ipLabel.setForeground(new Color(255, 255, 255));
        this.ipLabel.setHorizontalAlignment(0);
        this.ipLabel.setText("thisMachineAddress");
       // this.ipLabel.addMouseListener(new 18(this));
        nicknameLabel.setFont(new Font("Consolas", 0, 10));
        nicknameLabel.setForeground(new Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(0);
        nicknameLabel.setText("Nickname");
        this.nicknameTextField.setFont(new Font("Consolas", 0, 10));
        this.nicknameTextField.setHorizontalAlignment(0);
        this.nicknameTextField.setText("player");
        GroupLayout upperPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(upperPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(labelPanel, Alignment.TRAILING, -1, -1, 32767).addComponent(this.tabbedPanel, Alignment.TRAILING).addGroup(upperPanelLayout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(upperPanelLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.ipLabel, -2, 500, -2).addComponent(this.nicknameTextField, -1, 297, 32767).addComponent(nicknameLabel, -1, -1, 32767)).addContainerGap(-1, 32767)));
        upperPanelLayout.setVerticalGroup(upperPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(upperPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.ipLabel, -2, 25, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(labelPanel, -2, -1, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(nicknameLabel, -2, 31, -2).addGap(4, 4, 4).addComponent(this.nicknameTextField, -2, 40, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.tabbedPanel)));
        this.add(backgroundPanel);
    }

    private void vpnJoinButtonMousePressed(MouseEvent evt) {
        String opponentAddress = this.vpnAddressTextField.getText();
        Pattern ipPattern = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");
        Matcher ipMatcher = ipPattern.matcher(opponentAddress);
        if (!ipMatcher.find()) {
            this.vpnAddressTextField.setForeground(Color.RED);
        } else {
            this.goTetrisMultiplayerPanel(false, (byte)2);
        }
    }

    private void mainMenuButtonvpnMousePressed(MouseEvent evt) {
        this.mainMenuButtonMousePressed();
    }

    private void vpnCreateButtonMousePressed(MouseEvent evt) {
        this.goTetrisMultiplayerPanel(true, (byte)2);
    }

    private boolean internetConnectionTester() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(100);
            connection.connect();
            return true;
        } catch (IOException var3) {
            System.out.println("no internet connection");
            return false;
        }
    }

    public void switchLabelMousePressed() {
        ServerSocket s;
        if (this.tabbedPanel.getSelectedIndex() == 1) {
            this.ipLabel.setToolTipText("click to copy");
            if (this.internetConnectionTester()) {
                try {
                    MappedAddress mappedAddress = StunTest.getDynamicIp();
                    this.ipLabel.setForeground(Color.WHITE);
                    this.ipLabel.setText(mappedAddress.getAddress() + ":" + mappedAddress.getPort());
                } catch (IOException | UtilityException var4) {
                    var4.printStackTrace();
                }

                this.tabbedPanel.setSelectedIndex(1);
            } else {
                this.ipLabel.setToolTipText("");

                try {
                    s = new ServerSocket(0);
                    this.ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                    s.close();
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                this.ipLabel.setForeground(Color.RED);
                this.ipLabel.setText("no internet connection");
            }
        } else if (this.tabbedPanel.getSelectedIndex() == 0) {
            try {
                s = new ServerSocket(0);
                this.ipLabel.setForeground(Color.WHITE);
                this.ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                s.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        } else if (this.tabbedPanel.getSelectedIndex() == 2) {
            this.ipLabel.setText("");
        } else if (this.tabbedPanel.getSelectedIndex() == 3) {
            this.ipLabel.setForeground(Color.WHITE);
            this.ipLabel.setText("checking the server...");
            (new Thread(() -> {
                try {
                    URL url = new URL("https://salty-fjord-01783.herokuapp.com/");
                    URLConnection connection = url.openConnection();
                    connection.setConnectTimeout(400);
                    connection.connect();
                    this.ipLabel.setForeground(Color.WHITE);
                    this.ipLabel.setText("server is ready");
                } catch (IOException var3) {
                    this.ipLabel.setForeground(Color.RED);
                    this.ipLabel.setText("no connection to server");
                }

            })).start();
        }

    }

    public void globalCreateButtonMousePressed() {
        String[] opponentAddress = this.globalCreateAddressTextField.getText().split(":");
        Pattern ipPattern = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);
        Pattern portPattern = Pattern.compile("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");

        Matcher portMatcher;
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception var7) {
            this.globalCreateAddressTextField.setForeground(Color.RED);
            return;
        }

        if (portMatcher.find() && ipMatcher.find()) {
            if (this.globalCreateAddressTextField.getText().equals(this.ipLabel.getText())) {
                this.globalCreateAddressTextField.setForeground(Color.RED);
            } else {
                this.goTetrisMultiplayerPanel(true, (byte)1);
            }
        } else {
            this.globalCreateAddressTextField.setForeground(Color.RED);
        }
    }

    public void globalJoinButtonMousePressed() {
        String[] opponentAddress = this.globalAddressTextField.getText().split(":");
        Pattern ipPattern = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);
        Pattern portPattern = Pattern.compile("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");

        Matcher portMatcher;
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception var7) {
            this.globalAddressTextField.setForeground(Color.RED);
            return;
        }

        if (!portMatcher.find() && !ipMatcher.find()) {
            this.globalAddressTextField.setForeground(Color.RED);
        } else if (this.globalAddressTextField.getText().equals(this.ipLabel.getText())) {
            this.globalAddressTextField.setForeground(Color.RED);
        } else {
            this.goTetrisMultiplayerPanel(false, (byte)1);
        }
    }

    private void localCreateButtonMousePressed() {
        this.goTetrisMultiplayerPanel(true, (byte)0);
    }

    private void localJoinButtonMousePressed() {
        this.goTetrisMultiplayerPanel(false, (byte)0);
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

    public void goTetrisMultiplayerPanel(boolean thisAppServer, byte typeOfGame) {
        Main.audioPlayer.playClick();
        this.typeOfGame = typeOfGame;
        this.nickname = this.nicknameTextField.getText();
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.thisAppServer = thisAppServer;
        Main.tetrisFrame.remove(Main.multiplayerPanel2);
        Main.tetrisFrame.add(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.repaint();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.startNewGame();
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.typeOfSquare = Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.exitMenuKey) {
            this.mainMenuButtonMousePressed();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public class BackgroundPanel extends JPanel {
        BufferedImage bufferedImage = null;
        BufferedImage backgroundImage;
        BufferedImage backgroundImage2;
        BufferedImage backgroundImage3;
        BufferedImage backgroundImage4;
        BufferedImage backgroundImage5;

        BackgroundPanel() {
            try {
                this.backgroundImage = ImageIO.read((URL) Objects.requireNonNull(this.getClass().getResource("/resources/backgroundImages/congruent_outline.png")));
                this.backgroundImage2 = ImageIO.read((URL)Objects.requireNonNull(this.getClass().getResource("/resources/backgroundImages/dark-triangles.png")));
                this.backgroundImage3 = ImageIO.read((URL)Objects.requireNonNull(this.getClass().getResource("/resources/backgroundImages/watercolor-3264479_640.jpg")));
                this.backgroundImage4 = ImageIO.read((URL)Objects.requireNonNull(this.getClass().getResource("/resources/backgroundImages/pattern-1004855_640.jpg")));
                this.backgroundImage5 = ImageIO.read((URL)Objects.requireNonNull(this.getClass().getResource("/resources/backgroundImages/backdrop-3346304_640.png")));
            } catch (IOException var2) {
                var2.printStackTrace();
            }

        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            if (Main.tetrisPanel.backgroundType == 0) {
                this.bufferedImage = this.backgroundImage;
            } else if (Main.tetrisPanel.backgroundType == 1) {
                this.bufferedImage = this.backgroundImage2;
            } else if (Main.tetrisPanel.backgroundType == 2) {
                this.bufferedImage = this.backgroundImage3;
            } else if (Main.tetrisPanel.backgroundType == 3) {
                this.bufferedImage = this.backgroundImage4;
            } else if (Main.tetrisPanel.backgroundType == 4) {
                this.bufferedImage = this.backgroundImage5;
            }

            for(int i = 0; (double)i < Main.monitorHeight / (double)this.bufferedImage.getHeight() + 1.0D; ++i) {
                for(int j = 0; (double)j < Main.monitorWidth / (double)this.bufferedImage.getWidth() + 1.0D; ++j) {
                    g2d.drawImage(this.bufferedImage, j * this.bufferedImage.getWidth(), i * this.bufferedImage.getHeight(), this);
                }
            }

            g2d.setFont(Main.FONT);
        }
    }

    class TitlePanel extends JPanel {
        int w;
        int h;
        int s;
        Dimension d;
        Container c;
        int radius;
        int startX;

        public TitlePanel() {
            this.setOpaque(false);
        }

        public Dimension getPreferredSize() {
            this.d = super.getPreferredSize();
            this.c = this.getParent();
            if (this.c != null) {
                this.d = this.c.getSize();
                this.w = (int)this.d.getWidth();
                this.h = (int)this.d.getHeight();
                this.s = Math.min(this.w, this.h);
                return new Dimension(this.s, this.s / 6);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintMultiplayerTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 43;
            PaintStaticLetters.paintLetterM(g2d, startX, startY, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterU(g2d, startX + 5 * square_radius + space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterL(g2d, startX + 9 * square_radius + 2 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterT(g2d, startX + 12 * square_radius + 3 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterI(g2d, startX + 17 * square_radius + 4 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterP(g2d, startX + 20 * square_radius + 5 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterL(g2d, startX + 24 * square_radius + 6 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterA(g2d, startX + 27 * square_radius + 7 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterY(g2d, startX + 30 * square_radius + 8 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterE(g2d, startX + 35 * square_radius + 9 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
            PaintStaticLetters.paintLetterR(g2d, startX + 39 * square_radius + 10 * space, startY, square_radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }

        protected void paintComponent(Graphics g) {
            this.radius = Math.min(this.getWidth() / 44, this.getHeight() / 6);
            this.startX = (this.getWidth() - this.radius * 44) / 2;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.paintMultiplayerTitle(g2d, this.startX, this.radius, this.radius);
        }
    }

}
