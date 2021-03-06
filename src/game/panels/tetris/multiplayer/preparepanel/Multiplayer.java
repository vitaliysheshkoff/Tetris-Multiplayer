package game.panels.tetris.multiplayer.preparepanel;

import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.util.UtilityException;
import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.helperclasses.buttons.MyButton;
import game.helperclasses.textfieldlimit.JTextFieldLimit;
import game.panels.tetris.multiplayer.ai.BattlePanel;
import game.panels.tetris.multiplayer.stun.StunTest;
import game.panels.tetris.multiplayer.web.telegram.DataBaseClient;
import game.serial.LeaderBoardSaver;
import game.start.Main;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
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
    public static final byte TELEGRAM = 4;
    public static final byte BOT = 5;

    public byte typeOfGame = 0;

    public JTextField globalAddressTextField;
    public JTextField globalCreateAddressTextField;
    public JTextField nicknameTextField;
    public JTextField vpnAddressTextField;
    public JTextField joinRoomTextField;

    public JTabbedPane tabbedPanel;

    public JLabel ipLabel;

    public String nickname = "";

    public BattlePanel battlePanel;
    public JComboBox<String> telegramUsers;
    private JTextArea commandTextArea;

    public Multiplayer() {
        setBackground(new Color(0, 0, 0));
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
        JLabel vpnCreateGameLabel = new JLabel();
        JLabel nicknameLabel = new JLabel();
        JLabel vpnAddressLabel = new JLabel();
        JLabel vpnJoinGameLabel = new JLabel();

        MyButton localCreateButton = new MyButton("create");
        MyButton localJoinButton = new MyButton("join");
        MyButton globalCreateButton = new MyButton("create");
        MyButton globalJoinButton = new MyButton("join");
        MyButton vpnCreateButton = new MyButton("create");
        MyButton vpnJoinButton = new MyButton("join");
        MyButton mainMenuButtonInternet = new MyButton("main menu");
        MyButton mainMenuButtonLocal = new MyButton("main menu");
        MyButton mainMenuButtonvpn = new MyButton("main menu");
        MyButton mainMenuButtonTelegram = new MyButton("main menu");
        MyButton mainMenuButtonBot = new MyButton("main menu");
        MyButton mainMenuButtonWeb = new MyButton("main menu");


        tabbedPanel = new JTabbedPane();
        tabbedPanel.setToolTipText("");

        JPanel internetPanel = new JPanel();
        internetPanel.setOpaque(false);

        JPanel vpnPanel = new JPanel();
        vpnPanel.setOpaque(false);
        vpnPanel.setBackground(new Color(0, 153, 153));

        ipLabel = new JLabel();

        nicknameTextField = new JTextField();
        globalAddressTextField = new JTextField();
        globalCreateAddressTextField = new JTextField();
        vpnAddressTextField = new JTextField();
        joinRoomTextField = new JTextField();
        joinRoomTextField.setHorizontalAlignment(JTextField.CENTER);
        joinRoomTextField.setPreferredSize(new Dimension(90,40));

        nicknameTextField.setDocument(new JTextFieldLimit(15));
        globalCreateAddressTextField.setDocument(new JTextFieldLimit(21));
        globalAddressTextField.setDocument(new JTextFieldLimit(21));
        vpnAddressTextField.setDocument(new JTextFieldLimit(21));
        joinRoomTextField.setDocument(new JTextFieldLimit(3));

        vpnAddressTextField.setToolTipText("IP_4");
        vpnAddressTextField.setToolTipText("<html><img src=\"" + Main.class.getResource("/resources/backgroundImages/img.png") + "\">");
        vpnAddressTextField.setToolTipText("IP_4");

        BackgroundPanel backgroundPanel = new BackgroundPanel();

        JPanel localPanel = new JPanel();
        localPanel.setOpaque(false);

        JPanel labelPanel = new TitlePanel();

        Color textfieldColor = globalCreateAddressTextField.getForeground();

        nicknameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (nicknameTextField.getForeground() == textfieldColor) {
                    nicknameTextField.setText("");
                }

                nicknameTextField.setForeground(Color.WHITE);
            }
        });

        globalCreateAddressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (globalCreateAddressTextField.getForeground() == textfieldColor) {
                    globalCreateAddressTextField.setText("");
                }

                globalCreateAddressTextField.setForeground(Color.WHITE);
            }
        });

        globalAddressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (globalAddressTextField.getForeground() == textfieldColor) {
                    globalAddressTextField.setText("");
                }

                globalAddressTextField.setForeground(Color.WHITE);
            }
        });

        vpnAddressTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (vpnAddressTextField.getForeground() == textfieldColor) {
                    vpnAddressTextField.setText("");
                }

                vpnAddressTextField.setForeground(Color.WHITE);
            }
        });

        joinRoomTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (joinRoomTextField.getForeground() == textfieldColor) {
                    joinRoomTextField.setText("");
                }

                joinRoomTextField.setForeground(Color.WHITE);
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        GroupLayout LabelPanelLayout = new GroupLayout(labelPanel);
        labelPanel.setLayout(LabelPanelLayout);

        LabelPanelLayout.setHorizontalGroup(LabelPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, 32767));
        LabelPanelLayout.setVerticalGroup(LabelPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 78, 32767));


        localCreateGameLabel.setFont(Main.FONT);
        localCreateGameLabel.setForeground(new Color(255, 255, 255));
        localCreateGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        localCreateGameLabel.setText("Create game");

        localCreateButton.setHorizontalAlignment(SwingConstants.CENTER);
        localCreateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    localCreateButtonMousePressed();
            }
        });

        localJoinGameLabel.setFont(Main.FONT);
        localJoinGameLabel.setForeground(new Color(255, 255, 255));
        localJoinGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        localJoinGameLabel.setText("Join game");

        localJoinButton.setHorizontalAlignment(SwingConstants.CENTER);
        localJoinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    localJoinButtonMousePressed();
            }
        });

        mainMenuButtonLocal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        GroupLayout localPanelLayout = new GroupLayout(localPanel);
        localPanel.setLayout(localPanelLayout);

        localPanelLayout.setHorizontalGroup(localPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(localPanelLayout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(localCreateButton, -1, 150, 32767)
                                .addComponent(localCreateGameLabel, -1, -1, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                        .addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(localJoinGameLabel, -1, -1, 32767)
                                .addComponent(localJoinButton, -1, 150, 32767))
                        .addContainerGap(-1, 32767))
                .addGroup(localPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainMenuButtonLocal)
                        .addContainerGap(758, 32767)));

        localPanelLayout.setVerticalGroup(localPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(localPanelLayout.createSequentialGroup()
                        .addGroup(localPanelLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(Alignment.TRAILING, localPanelLayout.createSequentialGroup()
                                        .addContainerGap(202, 32767)
                                        .addComponent(localJoinGameLabel, -2, 60, -2)
                                        .addGap(18, 18, 18)
                                        .addComponent(localJoinButton))
                                .addGroup(localPanelLayout.createSequentialGroup()
                                        .addContainerGap(-1, 32767)
                                        .addComponent(localCreateGameLabel, -2, 60, -2)
                                        .addGap(18, 18, 18)
                                        .addComponent(localCreateButton)))
                        .addPreferredGap(ComponentPlacement.RELATED, 177, 32767)
                        .addComponent(mainMenuButtonLocal).addContainerGap()));

        tabbedPanel.addTab("local", localPanel);

        globalCreateGameLabel.setFont(Main.FONT);
        globalCreateGameLabel.setForeground(new Color(255, 255, 255));
        globalCreateGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalCreateGameLabel.setText("Create game");

        globalCreateAddressLabel.setFont(Main.FONT);
        globalCreateAddressLabel.setForeground(new Color(255, 255, 255));
        globalCreateAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalCreateAddressLabel.setText("Opponent address");

        globalCreateAddressTextField.setFont(Main.FONT);
        globalCreateAddressTextField.setHorizontalAlignment(SwingConstants.CENTER);
        globalCreateAddressTextField.setText("address");
        globalCreateAddressTextField.setToolTipText("ip:port");

        globalCreatePortLabel.setFont(Main.FONT);
        globalCreatePortLabel.setForeground(new Color(255, 255, 255));
        globalCreatePortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalCreatePortLabel.setText("Port");

        globalPortLabel.setFont(Main.FONT);
        globalPortLabel.setForeground(new Color(255, 255, 255));
        globalPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalPortLabel.setText("Port");

        globalAddressTextField.setFont(Main.FONT);
        globalAddressTextField.setHorizontalAlignment(SwingConstants.CENTER);
        globalAddressTextField.setText("address");
        globalAddressTextField.setToolTipText("ip:port");

        globalAddressLabel.setFont(Main.FONT);
        globalAddressLabel.setForeground(new Color(255, 255, 255));
        globalAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalAddressLabel.setText("Opponent Address");

        globalJoinGameLabel.setFont(Main.FONT);
        globalJoinGameLabel.setForeground(new Color(255, 255, 255));
        globalJoinGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        globalJoinGameLabel.setText("Join game");

        globalJoinButton.setHorizontalAlignment(SwingConstants.CENTER);
        globalJoinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    globalJoinButtonMousePressed();
            }
        });

        globalCreateButton.setHorizontalAlignment(SwingConstants.CENTER);
        globalCreateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    globalCreateButtonMousePressed();
            }
        });

        mainMenuButtonInternet.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        mainMenuButtonTelegram.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        mainMenuButtonBot.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        mainMenuButtonWeb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        GroupLayout internetPanelLayout = new GroupLayout(internetPanel);
        internetPanel.setLayout(internetPanelLayout);

        internetPanelLayout.setHorizontalGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(internetPanelLayout.createSequentialGroup()
                        .addContainerGap(142, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(globalCreateGameLabel, -1, -1, 32767)
                                .addComponent(globalCreateAddressLabel, Alignment.TRAILING, -1, -1, 32767)
                                .addComponent(globalCreateAddressTextField, Alignment.TRAILING)
                                .addComponent(globalCreateButton, -1, 250, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 254, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(globalAddressLabel, -1, 250, 32767)
                                .addComponent(globalAddressTextField)
                                .addComponent(globalJoinButton, -1, -1, 32767)
                                .addComponent(globalJoinGameLabel, -1, -1, 32767))
                        .addContainerGap(148, 32767))
                .addGroup(internetPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainMenuButtonInternet)
                        .addContainerGap(-1, 32767)));

        internetPanelLayout.setVerticalGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(internetPanelLayout.createSequentialGroup()
                        .addContainerGap(132, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(globalCreateGameLabel, -2, 43, -2)
                                .addComponent(globalJoinGameLabel, -2, 51, -2))
                        .addPreferredGap(ComponentPlacement.UNRELATED, -1, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(globalCreateAddressLabel, -2, 41, -2)
                                .addComponent(globalAddressLabel, -2, 41, -2))
                        .addPreferredGap(ComponentPlacement.RELATED, 44, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(globalCreateAddressTextField, -2, 35, -2)
                                .addComponent(globalAddressTextField, -2, 35, -2))
                        .addPreferredGap(ComponentPlacement.RELATED, 45, 32767)
                        .addGroup(internetPanelLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(globalCreateButton)
                                .addComponent(globalJoinButton))
                        .addPreferredGap(ComponentPlacement.RELATED, 124, 32767)
                        .addComponent(mainMenuButtonInternet).addContainerGap()));

        tabbedPanel.addTab("inernet", internetPanel);

        vpnCreateGameLabel.setFont(Main.FONT);
        vpnCreateGameLabel.setForeground(new Color(255, 255, 255));
        vpnCreateGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vpnCreateGameLabel.setText("Create game");

        vpnCreateButton.setHorizontalAlignment(SwingConstants.CENTER);
        vpnCreateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    vpnCreateButtonMousePressed();
            }
        });

        mainMenuButtonvpn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mainMenuButtonMousePressed();
            }
        });

        vpnJoinButton.setHorizontalAlignment(SwingConstants.CENTER);
        vpnJoinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    vpnJoinButtonMousePressed();
            }
        });

        vpnAddressTextField.setFont(Main.FONT);
        vpnAddressTextField.setHorizontalAlignment(SwingConstants.CENTER);
        vpnAddressTextField.setText("address");

        vpnAddressLabel.setFont(Main.FONT);
        vpnAddressLabel.setForeground(new Color(255, 255, 255));
        vpnAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vpnAddressLabel.setText("Opponent Address");

        vpnJoinGameLabel.setFont(Main.FONT);
        vpnJoinGameLabel.setForeground(new Color(255, 255, 255));
        vpnJoinGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vpnJoinGameLabel.setText("Join game");

        GroupLayout vpnPanelLayout = new GroupLayout(vpnPanel);
        vpnPanel.setLayout(vpnPanelLayout);

        vpnPanelLayout.setHorizontalGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(vpnPanelLayout.createSequentialGroup()
                        .addContainerGap(142, 32767)
                        .addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(vpnCreateGameLabel, -1, -1, 32767)
                                .addComponent(vpnCreateButton, -1, 250, 32767))
                        .addPreferredGap(ComponentPlacement.RELATED, 254, 32767)
                        .addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(vpnAddressLabel, -1, 250, 32767)
                                .addComponent(vpnAddressTextField)
                                .addComponent(vpnJoinButton, -1, -1, 32767)
                                .addComponent(vpnJoinGameLabel, -1, -1, 32767))
                        .addContainerGap(148, 32767))
                .addGroup(vpnPanelLayout.createSequentialGroup()
                        .addContainerGap().addComponent(mainMenuButtonvpn)
                        .addContainerGap(-1, 32767)));

        vpnPanelLayout.setVerticalGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(vpnPanelLayout.createSequentialGroup()
                        .addContainerGap(157, 32767)
                        .addGroup(vpnPanelLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(vpnCreateGameLabel, -2, 43, -2)
                                .addComponent(vpnJoinGameLabel, -2, 51, -2))
                        .addPreferredGap(ComponentPlacement.UNRELATED, 15, 32767)
                        .addComponent(vpnAddressLabel, -2, 41, -2)
                        .addGroup(vpnPanelLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(vpnPanelLayout.createSequentialGroup()
                                        .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                                        .addComponent(vpnAddressTextField, -2, 35, -2)
                                        .addPreferredGap(ComponentPlacement.RELATED, 42, 32767))
                                .addGroup(vpnPanelLayout.createSequentialGroup()
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(vpnCreateButton)
                                        .addPreferredGap(ComponentPlacement.RELATED, 72, 32767)))
                        .addComponent(vpnJoinButton)
                        .addPreferredGap(ComponentPlacement.RELATED, 117, 32767)
                        .addComponent(mainMenuButtonvpn).addContainerGap()));

        tabbedPanel.addTab("vpn", vpnPanel);

        JPanel webPanel = new JPanel(/*new GridLayout(3, 1)*/new BorderLayout());
        webPanel.setOpaque(false);

        MyButton createRoomButton = new MyButton("create room");
        MyButton joinRoomButton = new MyButton("join room");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        panel.add(createRoomButton);
        panel.add(joinRoomTextField);
        panel.add(joinRoomButton);

        JPanel down =  new JPanel(new BorderLayout());
        down.add(mainMenuButtonWeb);
        down.setOpaque(false);

        webPanel.add(panel);
        webPanel.add(down, BorderLayout.PAGE_END);

        tabbedPanel.addTab("web", webPanel);

        joinRoomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (ipLabel.getText().equals("server is ready")) {

                    int room;
                    try {
                        room = Integer.parseInt(joinRoomTextField.getText());
                    } catch (Exception var4) {
                        System.out.println("room is incorrect!");
                        joinRoomTextField.setForeground(Color.RED);
                        return;
                    }

                    if (room < 1 || room > 999) {
                        System.out.println("room is incorrect!");
                        joinRoomTextField.setForeground(Color.RED);
                        return;
                    }

                    joinRoomButtonMousePressed();
                }
            }

            private void joinRoomButtonMousePressed() {
                goTetrisMultiplayerPanel(false, WEB);
            }

        });

        createRoomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (ipLabel.getText().equals("server is ready")) {
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegram = false;
                    createRoomButtonMousePressed();
                }
            }

            private void createRoomButtonMousePressed() {
                goTetrisMultiplayerPanel(true, WEB);
            }
        });

        JPanel telegramPanel = new JPanel(new BorderLayout()/*new GridLayout(1, 2) */);
        telegramPanel.setOpaque(false);

        MyButton telegramRequestButton = new MyButton("telegram request");

       telegramUsers = new JComboBox<>();
       telegramUsers.setName("users");

       telegramUsers.setPrototypeDisplayValue("maximum width of combobox...");
        telegramUsers.addItem("@all");

        JPanel requestPanel = new JPanel(/*new FlowLayout(FlowLayout.CENTER)*/);
        requestPanel.setOpaque(false);

        requestPanel.add(telegramRequestButton);
        requestPanel.add(telegramUsers);

        JPanel downPanel =  new JPanel(new BorderLayout());
        downPanel.add(mainMenuButtonTelegram);
        downPanel.setOpaque(false);

        telegramPanel.add(requestPanel, BorderLayout.PAGE_START);
        telegramPanel.add(downPanel, BorderLayout.PAGE_END);

//////////////////////////

        JLabel shareScoreLabel = new JLabel();
        shareScoreLabel.setText("<html><body style='text-align: center'>send this message to Bot<br>to publish your best score<br>(click to copy)");

        JPanel scorePanel = new JPanel();
        scorePanel.add(shareScoreLabel);

        commandTextArea = new JTextArea();
        commandTextArea.setEditable(false);
        commandTextArea.setOpaque(false);
        commandTextArea.setForeground(new Color(6,69,173));
        commandTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (!commandTextArea.getForeground().equals(Color.RED)) {
                    (new Thread(() -> {
                        String command = commandTextArea.getText();
                        StringSelection stringSelection = new StringSelection(command.replaceAll("\n", ""));
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);

                        commandTextArea.setVisible(false);

                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException var5) {
                            var5.printStackTrace();
                        }
                        commandTextArea.setVisible(true);
                        commandTextArea.setText(command);
                    })).start();
                }
            }
        });


        /*JPanel telegramLinkPanel = new JPanel();
        telegramLinkPanel.setOpaque(false);

        JLabel telegramLinkLabel = new JLabel();
      //  telegramLinkLabel.setForeground(new Color(6,69,173));
        telegramLinkLabel.setText("telegram link");
        telegramLinkLabel.setBackground(new Color(0,0,0,100));
        telegramLinkLabel.setToolTipText("uri.toString()");

        telegramLinkPanel.add(telegramLinkLabel);

        telegramLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse( new URI("http://java.sun.com"));
                    } catch (IOException | URISyntaxException exception) { }
                } else {  }
            }
        });*/

        JPanel commandPanel = new JPanel();
        commandPanel.setOpaque(false);
        commandPanel.add(commandTextArea);

        JPanel shareScorePanel = new JPanel(new BorderLayout());
        shareScorePanel.add(shareScoreLabel, BorderLayout.PAGE_START);
        shareScorePanel.add(commandPanel, BorderLayout.WEST);
      //  shareScorePanel.add(telegramLinkPanel );
        shareScorePanel.setOpaque(false);

        telegramPanel.add(shareScorePanel);

        tabbedPanel.addTab("Telegram", telegramPanel);

        telegramRequestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && ipLabel.getText().equals("server is ready") && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegram = true;
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegramUserNickname =
                            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.
                                    getEditTelegramNick((String) Objects.requireNonNull(Main.multiplayerPanel2.telegramUsers.getSelectedItem()));
                    goTetrisMultiplayerPanel(true, WEB);
                }
            }
        });


        //////////////////////////////
        // Bot

        JPanel aiPanel = new JPanel(new GridLayout(1, 2));
        aiPanel.setOpaque(false);

        MyButton startAIButton = new MyButton("play with AI");

        /*JComboBox<String> aiLevel = new JComboBox<>();

        aiLevel.addItem("easy");
        aiLevel.addItem("medium");
        aiLevel.addItem("hard");
        aiLevel.addItem("go home");*/


        aiPanel.add(mainMenuButtonBot);
        aiPanel.add(startAIButton);
        //aiPanel.add(aiLevel);

        tabbedPanel.addTab("bot",aiPanel);

        startAIButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    typeOfGame = BOT;
                    battlePanel = new BattlePanel();
                    Main.tetrisFrame.remove(Main.multiplayerPanel2);
                    Main.tetrisFrame.add(battlePanel);

                    Main.tetrisFrame.revalidate();
                    Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
                    Main.tetrisFrame.repaint();

                    battlePanel.tetrisPlayerNameLabel.setText(nicknameTextField.getText());
                    battlePanel.tetrisPlayerNameLabel.setForeground(Color.WHITE);

                    battlePanel.playfield.setTetrominoesStack();
                    battlePanel.aiPlayField.setTetrominoesStackFromCopy(battlePanel.playfield.tetrominoesStack);

                    battlePanel.playfield.startNewGame();
                    battlePanel.aiPlayField.startNewGame();
                }
            }
        });
        ////////////////////

        tabbedPanel.addChangeListener((e) -> {
            switchLabelMousePressed();
            System.out.println("change");
        });

        ipLabel.setBackground(new Color(0, 0, 0));
        ipLabel.setToolTipText("click to copy");
        ipLabel.setFont(Main.FONT);
        ipLabel.setForeground(new Color(255, 255, 255));
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ipLabel.setText("thisMachineAddress");
        ipLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (tabbedPanel.getSelectedIndex() == 1) {
                    ipLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
                }
            }

            public void mouseExited(MouseEvent e) {
                ipLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                if (!ipLabel.getText().equals("copied")) {
                    if (tabbedPanel.getSelectedIndex() == 1 && !ipLabel.getText().equals("no internet connection")) {
                        String ip = ipLabel.getText();
                        (new Thread(() -> {
                            StringSelection stringSelection = new StringSelection(ip);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                            ipLabel.setText("copied");

                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException var5) {
                                var5.printStackTrace();
                            }

                            ipLabel.setText(ip);
                        })).start();
                    }

                }
            }
        });

        nicknameLabel.setFont(Main.FONT);
        nicknameLabel.setForeground(new Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nicknameLabel.setText("Nickname");

        nicknameTextField.setFont(Main.FONT);
        nicknameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        nicknameTextField.setText("player");

        GroupLayout upperPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(upperPanelLayout);

        upperPanelLayout.setHorizontalGroup(upperPanelLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(labelPanel, Alignment.TRAILING, -1, -1, 32767)
                .addComponent(tabbedPanel, Alignment.TRAILING)
                .addGroup(upperPanelLayout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addGroup(upperPanelLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(ipLabel, -2, 500, -2)
                                .addComponent(nicknameTextField, -1, 297, 32767)
                                .addComponent(nicknameLabel, -1, -1, 32767)).addContainerGap(-1, 32767)));

        upperPanelLayout.setVerticalGroup(upperPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(upperPanelLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addComponent(ipLabel, -2, 25, -2)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(labelPanel, -2, -1, -2)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(nicknameLabel, -2, 31, -2)
                        .addGap(4, 4, 4)
                        .addComponent(nicknameTextField, -2, 40, -2)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(tabbedPanel)));

        add(backgroundPanel);
    }

    private String getLinesTokens(String command, int maxLineLength) {
        String commandLine = "";
        if(command.length() / maxLineLength > 0) {
            for (int i = 0; i < command.length(); i++) {
                commandLine += command.charAt(i);
                if ((i + 1) % maxLineLength == 0)
                    commandLine += "\n";
            }
        }
        else commandLine = command;

        return commandLine;
    }

    private String getEncodedString(String score) {
        return Base64.getEncoder().encodeToString(score.getBytes(StandardCharsets.UTF_8));
    }

    private String getBestScoreFromFile() {
        File scoreFile = new File(System.getProperty("user.dir"), "score.dat");
        String score = "";
        try {
            if (scoreFile.length() > 0L) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "score.dat")).getAbsolutePath()));
                LeaderBoardSaver[] readScore = (LeaderBoardSaver[]) ois.readObject();
                ois.close();
                score = readScore[0].getScore() + " " + readScore[0].getLevel() + " " + readScore[0].getDate();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    private void vpnJoinButtonMousePressed() {
        String opponentAddress = vpnAddressTextField.getText();
        Pattern ipPattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
        Matcher ipMatcher = ipPattern.matcher(opponentAddress);
        if (!ipMatcher.find()) {
            vpnAddressTextField.setForeground(Color.RED);
        } else {
            goTetrisMultiplayerPanel(false, HAMACHI);
        }
    }

    private void vpnCreateButtonMousePressed() {
        goTetrisMultiplayerPanel(true, HAMACHI);
    }

    private boolean internetConnectionTester() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(100);
            connection.connect();
            return true;
        } catch (IOException e) {
            System.out.println("no internet connection");
            return false;
        }
    }

    Thread dbThread = new Thread();
    public void switchLabelMousePressed() {
        ServerSocket s;
        if (dbThread != null) {
            if (dbThread.isAlive())
                dbThread.interrupt();
        }
        telegramUsers.setEnabled(false);

        if (tabbedPanel.getSelectedIndex() == NET_HOLE_PUNCHING) {
            ipLabel.setToolTipText("click to copy");
            if (internetConnectionTester()) {
                try {
                    MappedAddress mappedAddress = StunTest.getDynamicIp();
                    ipLabel.setForeground(Color.WHITE);
                    ipLabel.setText(mappedAddress.getAddress() + ":" + mappedAddress.getPort());
                } catch (IOException | UtilityException e) {
                    e.printStackTrace();
                }

                tabbedPanel.setSelectedIndex(1);
            } else {
                ipLabel.setToolTipText("");

                try {
                    s = new ServerSocket(0);
                    ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ipLabel.setForeground(Color.RED);
                ipLabel.setText("no internet connection");
            }
        } else if (tabbedPanel.getSelectedIndex() == LOCAL) {
            try {
                s = new ServerSocket(0);
                ipLabel.setForeground(Color.WHITE);
                ipLabel.setText(Inet4Address.getLocalHost().getHostAddress() + ":" + s.getLocalPort());
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tabbedPanel.getSelectedIndex() == HAMACHI) {
            ipLabel.setText("");
        } else if (tabbedPanel.getSelectedIndex() == WEB) {

            ipLabel.setForeground(Color.WHITE);
            ipLabel.setText("checking the server...");

            // checking is server reachable
            new Thread(() -> {
                try {
                    URL url = new URL("https://salty-fjord-01783.herokuapp.com/");
                    URLConnection connection = url.openConnection();
                    connection.setConnectTimeout(400);
                    connection.connect();

                    if (tabbedPanel.getSelectedIndex() == WEB) {
                        ipLabel.setForeground(Color.WHITE);
                        ipLabel.setText("server is ready");
                    }
                } catch (IOException e) {
                    if (tabbedPanel.getSelectedIndex() == WEB) {
                        ipLabel.setForeground(Color.RED);
                        ipLabel.setText("no connection to server");
                    }
                }
            }).start();
        } else if (tabbedPanel.getSelectedIndex() == TELEGRAM) {

            ipLabel.setForeground(Color.WHITE);
            ipLabel.setText("checking the database...");
            telegramUsers.setEnabled(false);
            telegramUsers.removeAllItems();
            telegramUsers.addItem("@all");

            // get users and chats database
            dbThread = new Thread(() -> {

                DataBaseClient.isConnected = false;
                new DataBaseClient();

                for (int i = 0; i < 500; i++) {
                    if (DataBaseClient.isConnected)
                        break;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if (tabbedPanel.getSelectedIndex() == TELEGRAM) {
                    if (!DataBaseClient.isConnected) {
                        telegramUsers.setEnabled(false);
                        ipLabel.setForeground(Color.RED);
                        ipLabel.setText("no connection to database");
                    } else {
                        telegramUsers.setEnabled(true);
                        ipLabel.setForeground(Color.WHITE);
                        ipLabel.setText("server is ready");
                    }
                }
            });
            dbThread.start();

            // get best score from file
            String score = getBestScoreFromFile();

            System.out.println(score);

            if(!score.equals("0 0 0/0/0") && !score.isEmpty()) {
                // encode score
                String encoded = getEncodedString(score);

                // add '\n' every 25 characters
                String commandLine = getLinesTokens("/add_score " + encoded, 25);

                commandTextArea.setText(commandLine);
                commandTextArea.setForeground(new Color(6,69,173));
            }
            else {
                commandTextArea.setText("no score in leaderboard");
                commandTextArea.setForeground(Color.RED);
            }

        }else {
            ipLabel.setForeground(Color.WHITE);
            ipLabel.setText("");
        }
    }

    public void globalCreateButtonMousePressed() {
        String[] opponentAddress = globalCreateAddressTextField.getText().split(":");
        Pattern ipPattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);
        Pattern portPattern = Pattern.compile(PORT_PATTERN);

        Matcher portMatcher;
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception var7) {
            globalCreateAddressTextField.setForeground(Color.RED);
            return;
        }

        if (portMatcher.find() && ipMatcher.find()) {
            if (globalCreateAddressTextField.getText().equals(ipLabel.getText())) {
                globalCreateAddressTextField.setForeground(Color.RED);
            } else {
                goTetrisMultiplayerPanel(true, NET_HOLE_PUNCHING);
            }
        } else {
            globalCreateAddressTextField.setForeground(Color.RED);
        }
    }

    public void globalJoinButtonMousePressed() {
        String[] opponentAddress = globalAddressTextField.getText().split(":");
        Pattern ipPattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
        Matcher ipMatcher = ipPattern.matcher(opponentAddress[0]);
        Pattern portPattern = Pattern.compile(PORT_PATTERN);

        Matcher portMatcher;
        try {
            portMatcher = portPattern.matcher(opponentAddress[1]);
        } catch (Exception var7) {
            globalAddressTextField.setForeground(Color.RED);
            return;
        }

        if (!portMatcher.find() && !ipMatcher.find()) {
            globalAddressTextField.setForeground(Color.RED);
        } else if (globalAddressTextField.getText().equals(ipLabel.getText())) {
            globalAddressTextField.setForeground(Color.RED);
        } else {
            goTetrisMultiplayerPanel(false, NET_HOLE_PUNCHING);
        }
    }

    private void localCreateButtonMousePressed() {
        goTetrisMultiplayerPanel(true, LOCAL);
    }

    private void localJoinButtonMousePressed() {
        goTetrisMultiplayerPanel(false, LOCAL);
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

        nickname = nicknameTextField.getText();

        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setName("");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setName("");

        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setFont(Main.FONT);
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setFont(Main.FONT);

        if(typeOfGame == WEB) {
            if (Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.telegram) {
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setName("telegram nickname");
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setName("telegram opponent nickname");

                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setFont(new Font("Verdana ", Font.PLAIN, 11));
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setFont(new Font("Verdana ", Font.PLAIN, 11));
            }
        }

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
            mainMenuButtonMousePressed();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public static class BackgroundPanel extends JPanel {
        BufferedImage bufferedImage = null;
        BufferedImage backgroundImage;
        BufferedImage backgroundImage2;
        BufferedImage backgroundImage3;
        BufferedImage backgroundImage4;
        BufferedImage backgroundImage5;

        BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/congruent_outline.png")));
                backgroundImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/dark-triangles.png")));
                backgroundImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/1.png")));
                backgroundImage4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/1_2.png")));
                backgroundImage5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/backgroundImages/cut_zip_floral-pattern-6696185_640.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

            if (Main.tetrisPanel.backgroundType == 0) {
                bufferedImage = backgroundImage;
            } else if (Main.tetrisPanel.backgroundType == 1) {
                bufferedImage = backgroundImage2;
            } else if (Main.tetrisPanel.backgroundType == 2) {
                bufferedImage = backgroundImage3;
            } else if (Main.tetrisPanel.backgroundType == 3) {
                bufferedImage = backgroundImage4;
            } else if (Main.tetrisPanel.backgroundType == 4) {
                bufferedImage = backgroundImage5;
            }

            for (int i = 0; (double) i < Main.monitorHeight / (double) bufferedImage.getHeight() + 1.0D; ++i) {
                for (int j = 0; (double) j < Main.monitorWidth / (double) bufferedImage.getWidth() + 1.0D; ++j) {
                    g2d.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }

            g2d.setFont(Main.FONT);
        }
    }

    static class TitlePanel extends JPanel {
        int w;
        int h;
        int s;
        int radius;
        int startX;

        Dimension d;
        Container c;

        public TitlePanel() {
            setOpaque(false);
        }

        public Dimension getPreferredSize() {
            d = super.getPreferredSize();
            c = getParent();
            if (c != null) {
                d = c.getSize();
                w = (int) d.getWidth();
                h = (int) d.getHeight();
                s = Math.min(w, h);
                return new Dimension(s, s / 6);
            } else {
                return new Dimension(10, 20);
            }
        }

        private void paintMultiplayerTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 43;
            PaintStaticLetters.paintLetterM(g2d, startX, startY, radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
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
            radius = Math.min(getWidth() / 44, getHeight() / 6);
            startX = (getWidth() - radius * 44) / 2;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintMultiplayerTitle(g2d, startX, radius, radius);
        }
    }
}
