package game.panels.menu;

import game.audio.AudioPlayer;
import game.helperclasses.buttons.MyButton;
import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.panels.tetris.singleplayer.mainpanel.TetrisPanel;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.stream.IntStream;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static game.panels.tetris.singleplayer.mainpanel.TetrisPanel.*;
import static game.panels.tetris.singleplayer.mainpanel.TetrisPanel.backgroundImage5;
import static game.panels.tetris.singleplayer.playfield.TetrisPlayFieldPanel.*;

public class OptionsPanel extends JPanel implements ChangeListener, KeyListener {

    public JScrollPane scrollPane;
    public JPanel lowerPanel;
    private JComboBox backgroundBox;

    private static final int MAIN_MENU = 0, RESET = 1;
    private JLabel CCWRotationEventLabel;
    private JLabel CWRotationEventLabel;
    private JLabel downEventLabel;
    private JLabel pauseEventLabel;
    private JLabel rightEventLabel;
    private JLabel exitToMenuEventLabel;
    private JLabel hardDropEventLabel;
    private JLabel leftEventLabel;
    private JLabel music1Label;
    private JLabel music2Label;
    private JLabel music3Label;
    private JLabel offLabel;
    private JLabel sevenBagRandomLabel;
    private JLabel plainRandomLabel;
    private JLabel randomColorsAnimationLabel;
    private JLabel disappearingAnimationLabel;
    private JLabel startLevelLabel;

    private MyButton mainMenuButton;
    private MyButton resetToDefaultButton;

    private JCheckBox shadowCheckBox;
    private JCheckBox gridCheckBox;

    public JSlider startLevelSlider;
    private JSlider soundsVolumeSlider;
    private JSlider musicVolumeSlider;

    private int buttonController = MAIN_MENU;

    private boolean currentButtonSelected = true;

    private int[] useKeys;

    private OptionsSaver optionsSaver;

    public OptionsPanel() {
        initComponents();
        setOptions();
        addKeyListener(this);
    }

    private void initComponents() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel staticGridLevelLabel = new JLabel();
        JLabel staticShadowLevelLabel = new JLabel();
        JLabel staticKeyboardLabel = new JLabel();
        JLabel staticRandomizerLabel = new JLabel();
        JLabel staticMusicAndSoundsLabel = new JLabel();
        JLabel staticAppearanceLabel = new JLabel();
        JLabel staticPauseLabel = new JLabel();
        JLabel staticLeftLabel = new JLabel();
        JLabel staticRightLabel = new JLabel();
        JLabel staticDownLabel = new JLabel();
        JLabel staticCWRotationLabel = new JLabel();
        JLabel staticHardDropLabel = new JLabel();
        JLabel staticCCWRotationLabel = new JLabel();
        JLabel staticExitToMenu = new JLabel();
        JLabel staticMusicLabel = new JLabel();
        JLabel staticMusicVolumeLabel = new JLabel();
        JLabel staticSoundsVolumeLabel = new JLabel();
        JLabel staticBackgroundImageLabel = new JLabel();
        JLabel staticLineClearAnimationLabel = new JLabel();
        JLabel staticStartLevelLabel = new JLabel();

        sevenBagRandomLabel = new JLabel();
        plainRandomLabel = new JLabel();
        disappearingAnimationLabel = new JLabel();
        randomColorsAnimationLabel = new JLabel();
        leftEventLabel = new JLabel();
        rightEventLabel = new JLabel();
        CWRotationEventLabel = new JLabel();
        CCWRotationEventLabel = new JLabel();
        downEventLabel = new JLabel();
        hardDropEventLabel = new JLabel();
        pauseEventLabel = new JLabel();
        exitToMenuEventLabel = new JLabel();
        music1Label = new JLabel();
        music2Label = new JLabel();
        music3Label = new JLabel();
        offLabel = new JLabel();
        startLevelLabel = new JLabel();

        mainMenuButton = new MyButton("main menu");
        resetToDefaultButton = new MyButton("reset");

        startLevelSlider = new JSlider();
        musicVolumeSlider = new JSlider();
        soundsVolumeSlider = new JSlider();

        startLevelSlider.setForeground(Main.DARK_BLUE_COLOR);
        startLevelSlider.setBackground(Color.BLACK);

        soundsVolumeSlider.setForeground(Main.DARK_BLUE_COLOR);
        soundsVolumeSlider.setBackground(Color.BLACK);

        musicVolumeSlider.setForeground(Main.DARK_BLUE_COLOR);
        musicVolumeSlider.setBackground(Color.BLACK);

        startLevelSlider.setMaximum(29);
        startLevelSlider.addChangeListener(this);
        soundsVolumeSlider.addChangeListener(this);
        musicVolumeSlider.addChangeListener(this);

        useKeys = new int[8];

        gridCheckBox = new JCheckBox();
        shadowCheckBox = new JCheckBox();

        optionsSaver = new OptionsSaver();

        getOptions();

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        staticKeyboardLabel.setBackground(Color.BLACK);
        staticKeyboardLabel.setOpaque(true);
        staticKeyboardLabel.setFont(Main.FONT);
        staticKeyboardLabel.setForeground(Main.GREEN_COLOR);
        staticKeyboardLabel.setText("KEYBOARD");

        staticRandomizerLabel.setBackground(Color.BLACK);
        staticRandomizerLabel.setOpaque(true);
        staticRandomizerLabel.setFont(Main.FONT);
        staticRandomizerLabel.setForeground(Main.PINK_COLOR);
        staticRandomizerLabel.setText("RANDOMIZER");

        staticMusicAndSoundsLabel.setBackground(Color.BLACK);
        staticMusicAndSoundsLabel.setOpaque(true);
        staticMusicAndSoundsLabel.setFont(Main.FONT);
        staticMusicAndSoundsLabel.setForeground(Main.BLUE_COLOR);
        staticMusicAndSoundsLabel.setText("MUSIC AND SOUNDS");

        staticAppearanceLabel.setBackground(Color.BLACK);
        staticAppearanceLabel.setOpaque(true);
        staticAppearanceLabel.setFont(Main.FONT);
        staticAppearanceLabel.setForeground(Main.YELLOW_COLOR);
        staticAppearanceLabel.setText("APPEARANCE");

        staticPauseLabel.setBackground(Color.BLACK);
        staticPauseLabel.setOpaque(true);
        staticPauseLabel.setFont(Main.FONT);
        staticPauseLabel.setForeground(Main.GREEN_COLOR);
        staticPauseLabel.setText("pause");

        staticLeftLabel.setBackground(Color.BLACK);
        staticLeftLabel.setOpaque(true);
        staticLeftLabel.setFont(Main.FONT);
        staticLeftLabel.setForeground(Main.GREEN_COLOR);
        staticLeftLabel.setText("left");

        staticRightLabel.setBackground(Color.BLACK);
        staticRightLabel.setOpaque(true);
        staticRightLabel.setFont(Main.FONT);
        staticRightLabel.setForeground(Main.GREEN_COLOR);
        staticRightLabel.setText("right");

        staticDownLabel.setBackground(Color.BLACK);
        staticDownLabel.setOpaque(true);
        staticDownLabel.setFont(Main.FONT);
        staticDownLabel.setForeground(Main.GREEN_COLOR);
        staticDownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staticDownLabel.setText("down");

        staticCWRotationLabel.setBackground(Color.BLACK);
        staticCWRotationLabel.setOpaque(true);
        staticCWRotationLabel.setFont(Main.FONT);
        staticCWRotationLabel.setForeground(Main.GREEN_COLOR);
        staticCWRotationLabel.setText("CW-rotation");

        staticHardDropLabel.setBackground(Color.BLACK);
        staticHardDropLabel.setOpaque(true);
        staticHardDropLabel.setFont(Main.FONT);
        staticHardDropLabel.setForeground(Main.GREEN_COLOR);
        staticHardDropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staticHardDropLabel.setText("hard drop");

        staticCCWRotationLabel.setBackground(Color.BLACK);
        staticCCWRotationLabel.setOpaque(true);
        staticCCWRotationLabel.setFont(Main.FONT);
        staticCCWRotationLabel.setForeground(Main.GREEN_COLOR);
        staticCCWRotationLabel.setText("CCW-rotation");

        staticExitToMenu.setBackground(Color.BLACK);
        staticExitToMenu.setOpaque(true);
        staticExitToMenu.setFont(Main.FONT);
        staticExitToMenu.setForeground(Main.GREEN_COLOR);
        staticExitToMenu.setText("exit to menu");
        staticExitToMenu.setToolTipText("");

        sevenBagRandomLabel.setBackground(new Color(0, 0, 0, 100));
        sevenBagRandomLabel.setOpaque(true);
        sevenBagRandomLabel.setFont(Main.FONT);
        sevenBagRandomLabel.setForeground(Color.WHITE);
        sevenBagRandomLabel.setText("7-bag random");
        sevenBagRandomLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                static7BagRandomMouseEntered();
            }

            public void mouseExited(MouseEvent evt) {
                static7BagRandomMouseExited();
            }

            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    static7BagRandomMousePressed();
                }
            }
        });

        plainRandomLabel.setBackground(new Color(0, 0, 0, 100));
        plainRandomLabel.setOpaque(true);
        plainRandomLabel.setFont(Main.FONT);
        plainRandomLabel.setForeground(Color.WHITE);
        plainRandomLabel.setText("plain random");
        plainRandomLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticPlainRandomMousePressed();
                }
            }
        });

        staticMusicLabel.setBackground(Color.BLACK);
        staticMusicLabel.setOpaque(true);
        staticMusicLabel.setFont(Main.FONT);
        staticMusicLabel.setForeground(Main.BLUE_COLOR);
        staticMusicLabel.setText("music");

        staticMusicVolumeLabel.setBackground(Color.BLACK);
        staticMusicVolumeLabel.setOpaque(true);
        staticMusicVolumeLabel.setFont(Main.FONT);
        staticMusicVolumeLabel.setForeground(Main.BLUE_COLOR);
        staticMusicVolumeLabel.setText("music volume");

        staticSoundsVolumeLabel.setBackground(Color.BLACK);
        staticSoundsVolumeLabel.setOpaque(true);
        staticSoundsVolumeLabel.setFont(Main.FONT);
        staticSoundsVolumeLabel.setForeground(Main.BLUE_COLOR);
        staticSoundsVolumeLabel.setText("sounds volume");

        staticBackgroundImageLabel.setBackground(Color.BLACK);
        staticBackgroundImageLabel.setOpaque(true);
        staticBackgroundImageLabel.setFont(Main.FONT);
        staticBackgroundImageLabel.setForeground(Main.RED_COLOR);
        staticBackgroundImageLabel.setText("BACKGROUND IMAGE");

        disappearingAnimationLabel.setBackground(new Color(0, 0, 0, 100));
        disappearingAnimationLabel.setOpaque(true);
        disappearingAnimationLabel.setFont(Main.FONT);
        disappearingAnimationLabel.setForeground(Color.WHITE);
        disappearingAnimationLabel.setText("disappearing");
        disappearingAnimationLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticDisappearingAnimationLabelMousePressed();
                }
            }
        });

        randomColorsAnimationLabel.setBackground(new Color(0, 0, 0, 100));
        randomColorsAnimationLabel.setOpaque(true);
        randomColorsAnimationLabel.setFont(Main.FONT);
        randomColorsAnimationLabel.setForeground(Color.WHITE);
        randomColorsAnimationLabel.setText("random colors");
        randomColorsAnimationLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticRandomColorAnimationLabelMousePressed();
                }
            }
        });

        staticLineClearAnimationLabel.setBackground(Color.BLACK);
        staticLineClearAnimationLabel.setOpaque(true);
        staticLineClearAnimationLabel.setFont(Main.FONT);
        staticLineClearAnimationLabel.setForeground(Main.ORANGE_COLOR);
        staticLineClearAnimationLabel.setText("LINE CLEAR ANIMATION");

        leftEventLabel.setBackground(new Color(0, 0, 0, 100));
        leftEventLabel.setOpaque(true);
        leftEventLabel.setFont(Main.FONT);
        leftEventLabel.setForeground(Color.WHITE);
        leftEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[0] = -1;
                    leftEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                leftEventLabelMouseReleased();
            }
        });
        leftEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                leftEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                leftEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        rightEventLabel.setBackground(new Color(0, 0, 0, 100));
        rightEventLabel.setOpaque(true);
        rightEventLabel.setFont(Main.FONT);
        rightEventLabel.setForeground(Color.WHITE);
        rightEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[1] = -1;
                    rightEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                rightEventLabelMouseReleased();
            }
        });
        rightEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                rightEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                rightEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        CWRotationEventLabel.setBackground(new Color(0, 0, 0, 100));
        CWRotationEventLabel.setOpaque(true);
        CWRotationEventLabel.setFont(Main.FONT);
        CWRotationEventLabel.setForeground(Color.WHITE);
        CWRotationEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CWRotationEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[2] = -1;
                    CWRotationEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                CWRotationEventLabelMouseReleased();
            }
        });
        CWRotationEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                CWRotationEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                CWRotationEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        CCWRotationEventLabel.setBackground(new Color(0, 0, 0, 100));
        CCWRotationEventLabel.setOpaque(true);
        CCWRotationEventLabel.setFont(Main.FONT);
        CCWRotationEventLabel.setForeground(Color.WHITE);
        CCWRotationEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CCWRotationEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[3] = -1;
                    CCWRotationEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                CCWRotationEventLabelMouseReleased();
            }
        });

        CCWRotationEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();

                CCWRotationEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                CCWRotationEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        downEventLabel.setBackground(new Color(0, 0, 0, 100));
        downEventLabel.setOpaque(true);
        downEventLabel.setFont(Main.FONT);
        downEventLabel.setForeground(Color.WHITE);
        downEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        downEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[4] = -1;
                    downEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                downEventLabelMouseReleased();
            }
        });

        downEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                downEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                downEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        hardDropEventLabel.setBackground(new Color(0, 0, 0, 100));
        hardDropEventLabel.setOpaque(true);
        hardDropEventLabel.setFont(Main.FONT);
        hardDropEventLabel.setForeground(Color.WHITE);
        hardDropEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hardDropEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[5] = -1;
                    hardDropEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                hardDropEventLabelMouseReleased();
            }
        });
        hardDropEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                hardDropEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                hardDropEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        pauseEventLabel.setBackground(new Color(0, 0, 0, 100));
        pauseEventLabel.setOpaque(true);
        pauseEventLabel.setFont(Main.FONT);
        pauseEventLabel.setForeground(Color.WHITE);
        pauseEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[6] = -1;
                    pauseEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                pauseEventLabelMouseReleased();
            }
        });

        pauseEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                pauseEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                pauseEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        exitToMenuEventLabel.setBackground(new Color(0, 0, 0, 100));
        exitToMenuEventLabel.setOpaque(true);
        exitToMenuEventLabel.setFont(Main.FONT);
        exitToMenuEventLabel.setForeground(Color.WHITE);
        exitToMenuEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exitToMenuEventLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[7] = -1;
                    exitToMenuEventLabelMousePressed();
                }
            }

            public void mouseReleased(MouseEvent evt) {
                exitToMenuEventLabelMouseReleased();
            }
        });

        exitToMenuEventLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                Main.audioPlayer.playClick();
                exitToMenuEventLabelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                exitToMenuEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        music1Label.setBackground(new Color(0, 0, 0, 100));
        music1Label.setOpaque(true);
        music1Label.setFont(Main.FONT);
        music1Label.setForeground(Color.WHITE);
        music1Label.setText("music1");
        music1Label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic1();
                    Main.audioPlayer.newMusic = true;
                    music1LabelMousePressed();
                }
            }
        });

        music2Label.setBackground(new Color(0, 0, 0, 100));
        music2Label.setOpaque(true);
        music2Label.setFont(Main.FONT);
        music2Label.setForeground(Color.WHITE);
        music2Label.setText("music2");
        music2Label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic2();
                    Main.audioPlayer.newMusic = true;
                    music2LabelMousePressed();
                }
            }
        });

        music3Label.setBackground(new Color(0, 0, 0, 100));
        music3Label.setOpaque(true);
        music3Label.setFont(Main.FONT);
        music3Label.setForeground(Color.WHITE);
        music3Label.setText("music3");
        music3Label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic3();
                    Main.audioPlayer.newMusic = true;
                    music3LabelMousePressed();
                }
            }
        });

        staticStartLevelLabel.setBackground(Color.BLACK);
        staticStartLevelLabel.setOpaque(true);
        staticStartLevelLabel.setFont(Main.FONT);
        staticStartLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticStartLevelLabel.setText("START LEVEL");

        offLabel.setBackground(new Color(0, 0, 0, 100));
        offLabel.setOpaque(true);
        offLabel.setFont(Main.FONT);
        offLabel.setForeground(Color.WHITE);
        offLabel.setText("OFF");
        offLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.offCutMusic();
                    offLabelMousePressed();
                }
            }
        });

        startLevelLabel.setBackground(new Color(0, 0, 0, 100));
        startLevelLabel.setOpaque(true);
        startLevelLabel.setFont(Main.FONT);
        startLevelLabel.setForeground(Color.WHITE);
        startLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    mainMenuLabelMousePressed();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mainMenuLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainMenuLabelMouseExited();
            }
        });

        resetToDefaultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    resetToDefaultLabelMousePressed();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resetToDefaultMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetToDefaultMouseExited();
            }
        });

        shadowCheckBox.setBackground(new Color(0, 0, 0, 100));
        shadowCheckBox.setFont(Main.FONT);
        shadowCheckBox.setForeground(Color.WHITE);
        shadowCheckBox.setText("shadow");
        shadowCheckBox.setOpaque(false);
        shadowCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    shadowCheckBoxMousePressed();
                    requestFocusInWindow();
                }
            }
        });

        staticShadowLevelLabel.setBackground(Color.BLACK);
        staticShadowLevelLabel.setOpaque(true);
        staticShadowLevelLabel.setFont(Main.FONT);
        staticShadowLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticShadowLevelLabel.setText("SHADOW");

        staticGridLevelLabel.setBackground(Color.BLACK);
        staticGridLevelLabel.setOpaque(true);
        staticGridLevelLabel.setFont(Main.FONT);
        staticGridLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticGridLevelLabel.setText("GRID");

        gridCheckBox.setBackground(new Color(0, 0, 0, 100));
        gridCheckBox.setFont(Main.FONT);
        gridCheckBox.setForeground(Color.WHITE);
        gridCheckBox.setText("grid");
        gridCheckBox.setOpaque(false);
        gridCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    gridCheckBoxMousePressed();
                    requestFocusInWindow();
                }
            }
        });

        lowerPanel = new JPanel();
        backgroundBox = new JComboBox();
        TitlePanel titlePanel = new TitlePanel();
        BackgroundPanel backgroundPanel = new BackgroundPanel();

        lowerPanel.setOpaque(false);
        backgroundBox.setBackground(new Color(0, 0, 0, 100));
        backgroundBox.setForeground(Color.WHITE);
        backgroundBox.setFocusable(false);

        for (int i = 1; i <= 5; i++)
            backgroundBox.addItem("background " + i);

        backgroundBox.addActionListener(e -> {
            if (backgroundBox.getSelectedIndex() == 0) {
                backgroundImage1MousePressed();
                repaint();

            } else if (backgroundBox.getSelectedIndex() == 1) {
                backgroundImage2MousePressed();
                repaint();

            } else if (backgroundBox.getSelectedIndex() == 2) {
                backgroundImage3MousePressed();
                repaint();

            } else if (backgroundBox.getSelectedIndex() == 3) {
                backgroundImage4MousePressed();
                repaint();

            } else {
                backgroundImage5MousePressed();
                repaint();

            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(lowerPanel);
        lowerPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticMusicVolumeLabel)
                                                                        .addComponent(staticSoundsVolumeLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(soundsVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(musicVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addComponent(staticLineClearAnimationLabel)
                                                        .addComponent(staticRandomizerLabel)
                                                        .addComponent(staticMusicAndSoundsLabel)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(staticMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(music1Label)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(music2Label)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(music3Label)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(offLabel)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(randomColorsAnimationLabel)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(disappearingAnimationLabel))
                                                        .addComponent(staticAppearanceLabel)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(staticBackgroundImageLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(backgroundBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addContainerGap(292, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(staticKeyboardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                                                                        .addComponent(staticRightLabel)
                                                                        .addComponent(staticDownLabel)
                                                                        .addComponent(staticHardDropLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(staticLeftLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(rightEventLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                                        .addComponent(downEventLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(hardDropEventLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(leftEventLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticCWRotationLabel)
                                                                        .addComponent(staticCCWRotationLabel)
                                                                        .addComponent(staticExitToMenu)
                                                                        .addComponent(staticPauseLabel)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(shadowCheckBox)
                                                                                .addComponent(staticShadowLevelLabel))))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(8, 8, 8)
                                                                                .addComponent(staticStartLevelLabel))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(startLevelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(startLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(plainRandomLabel)
                                                                                .addGap(50, 50, 50)
                                                                                .addComponent(sevenBagRandomLabel)))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(gridCheckBox)
                                                        .addComponent(staticGridLevelLabel)
                                                        .addComponent(CCWRotationEventLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                        .addComponent(exitToMenuEventLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(pauseEventLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(CWRotationEventLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(resetToDefaultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticStartLevelLabel)
                                                .addComponent(staticShadowLevelLabel))
                                        .addComponent(staticGridLevelLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(shadowCheckBox)
                                                        .addComponent(gridCheckBox)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(startLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(startLevelSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staticKeyboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(leftEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticLeftLabel)
                                                .addComponent(staticCWRotationLabel)
                                                .addComponent(CWRotationEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rightEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticCCWRotationLabel)
                                        .addComponent(CCWRotationEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticDownLabel)
                                        .addComponent(downEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticExitToMenu)
                                        .addComponent(exitToMenuEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticHardDropLabel)
                                        .addComponent(hardDropEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticPauseLabel)
                                        .addComponent(pauseEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staticRandomizerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(plainRandomLabel)
                                        .addComponent(sevenBagRandomLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staticMusicAndSoundsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticMusicLabel)
                                        .addComponent(music1Label)
                                        .addComponent(music3Label)
                                        .addComponent(offLabel)
                                        .addComponent(music2Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(staticMusicVolumeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(musicVolumeSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(soundsVolumeSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(staticSoundsVolumeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staticAppearanceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticBackgroundImageLabel)
                                        .addComponent(backgroundBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staticLineClearAnimationLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(randomColorsAnimationLabel)
                                        .addComponent(disappearingAnimationLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resetToDefaultButton))
                                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(lowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
        );

        add(backgroundPanel);
    }

    private void static7BagRandomMouseEntered() {
    }

    private void static7BagRandomMouseExited() {
    }

    private void static7BagRandomMousePressed() {
        sevenBagRandomLabel.setBorder(BorderFactory.createLineBorder(Main.PINK_COLOR, 3));
        plainRandomLabel.setBorder(null);
        optionsSaver.setRandomType(NEW_STYLE_RANDOM);
    }

    private void staticPlainRandomMousePressed() {
        sevenBagRandomLabel.setBorder(null);
        plainRandomLabel.setBorder(BorderFactory.createLineBorder(Main.PINK_COLOR, 3));
        optionsSaver.setRandomType(OLD_STYLE_RANDOM);
    }

    private void leftEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            leftEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setLeftKey(evt.getKeyCode());
            useKeys[0] = evt.getKeyCode();
        }
    }

    private void leftEventLabelMousePressed() {
        leftEventLabel.setFocusable(true);
        leftEventLabel.requestFocusInWindow();
        leftEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        leftEventLabel.setText("[choose key]");
    }

    private void leftEventLabelMouseReleased() {
    }

    private void leftEventLabelKeyReleased() {
        leftEventLabel.setBorder(null);
        leftEventLabel.setFocusable(false);
    }

    private void rightEventLabelMousePressed() {
        rightEventLabel.setFocusable(true);
        rightEventLabel.requestFocusInWindow();
        rightEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        rightEventLabel.setText("[choose key]");
    }

    private void rightEventLabelMouseReleased() {
    }

    private void rightEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            rightEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setRightKey(evt.getKeyCode());
            useKeys[1] = evt.getKeyCode();
        }
    }

    private void rightEventLabelKeyReleased() {
        rightEventLabel.setBorder(null);
        rightEventLabel.setFocusable(false);
    }

    private void CWRotationEventLabelMousePressed() {
        CWRotationEventLabel.setFocusable(true);
        CWRotationEventLabel.requestFocusInWindow();
        CWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        CWRotationEventLabel.setText("[choose key]");
    }

    private void CWRotationEventLabelMouseReleased() {
    }

    private void CWRotationEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setCwKey(evt.getKeyCode());
            useKeys[2] = evt.getKeyCode();
        }
    }

    private void CWRotationEventLabelKeyReleased() {
        CWRotationEventLabel.setBorder(null);
        CWRotationEventLabel.setFocusable(false);
    }

    private void CCWRotationEventLabelMousePressed() {
        CCWRotationEventLabel.setFocusable(true);
        CCWRotationEventLabel.requestFocusInWindow();
        CCWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        CCWRotationEventLabel.setText("[choose key]");
    }

    private void CCWRotationEventLabelMouseReleased() {
    }

    private void CCWRotationEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setCcwKey(evt.getKeyCode());
            useKeys[3] = evt.getKeyCode();
        }
    }

    private void CCWRotationEventLabelKeyReleased() {
        CCWRotationEventLabel.setBorder(null);
        CCWRotationEventLabel.setFocusable(false);
    }

    private void downEventLabelMousePressed() {
        downEventLabel.setFocusable(true);
        downEventLabel.requestFocusInWindow();
        downEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        downEventLabel.setText("[choose key]");
    }

    private void downEventLabelMouseReleased() {
    }

    private void downEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            downEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setDownKey(evt.getKeyCode());
            useKeys[4] = evt.getKeyCode();
        }
    }

    private void downEventLabelKeyReleased() {
        downEventLabel.setBorder(null);
        downEventLabel.setFocusable(false);
    }

    private void hardDropEventLabelMousePressed() {
        hardDropEventLabel.setFocusable(true);
        hardDropEventLabel.requestFocusInWindow();
        hardDropEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        hardDropEventLabel.setText("[choose key]");
    }

    private void hardDropEventLabelMouseReleased() {
    }

    private void hardDropEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            hardDropEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setHardDropKey(evt.getKeyCode());
            useKeys[5] = evt.getKeyCode();
        }
    }

    private void hardDropEventLabelKeyReleased() {
        hardDropEventLabel.setBorder(null);
        hardDropEventLabel.setFocusable(false);
    }

    private void pauseEventLabelMousePressed() {
        pauseEventLabel.setFocusable(true);
        pauseEventLabel.requestFocusInWindow();
        pauseEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        pauseEventLabel.setText("[choose key]");
    }

    private void pauseEventLabelMouseReleased() {
    }

    private void pauseEventLabelKeyPressed(KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            pauseEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setPauseKey(evt.getKeyCode());
            useKeys[6] = evt.getKeyCode();
        }
    }

    private void pauseEventLabelKeyReleased() {
        pauseEventLabel.setBorder(null);
        pauseEventLabel.setFocusable(false);
    }

    private void exitToMenuEventLabelMousePressed() {
        exitToMenuEventLabel.setFocusable(true);
        exitToMenuEventLabel.requestFocusInWindow();
        exitToMenuEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        exitToMenuEventLabel.setText("[choose key]");
    }

    private void exitToMenuEventLabelMouseReleased() {
    }

    private void exitToMenuEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setExitMenuKey(evt.getKeyCode());
            useKeys[7] = evt.getKeyCode();
            Main.tetrisPanel.tetrisPlayFieldPanel.exitMenuKey = evt.getKeyCode();
        }
    }

    private void exitToMenuEventLabelKeyReleased() {
        exitToMenuEventLabel.setBorder(null);
        exitToMenuEventLabel.setFocusable(false);
    }

    private void backgroundImage1MousePressed() {
        backgroundBox.setSelectedIndex(0);
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND);
    }

    private void backgroundImage2MousePressed() {
        backgroundBox.setSelectedIndex(1);
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND2;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND2);
    }

    private void backgroundImage3MousePressed() {
        backgroundBox.setSelectedIndex(2);
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND3;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND3);
    }

    private void backgroundImage4MousePressed() {
        backgroundBox.setSelectedIndex(3);
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND4;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND4);
    }

    private void backgroundImage5MousePressed() {
        backgroundBox.setSelectedIndex(4);
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND5;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND5);
    }

    private void staticDisappearingAnimationLabelMousePressed() {
        randomColorsAnimationLabel.setBorder(null);
        disappearingAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        optionsSaver.setLineClearAnimation(DISAPPEAR_CLEAR_LINES_ANIMATION);
    }

    private void staticRandomColorAnimationLabelMousePressed() {
        disappearingAnimationLabel.setBorder(null);
        randomColorsAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        optionsSaver.setLineClearAnimation(RANDOM_COLOR_CLEAR_LINES_ANIMATION);
    }

    private void music1LabelMousePressed() {
        music1Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music2Label.setBorder(null);
        music3Label.setBorder(null);
        offLabel.setBorder(null);
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC1);
    }

    private void music2LabelMousePressed() {
        music2Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music1Label.setBorder(null);
        music3Label.setBorder(null);
        offLabel.setBorder(null);
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC2);
    }

    private void music3LabelMousePressed() {
        music3Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music2Label.setBorder(null);
        music1Label.setBorder(null);
        offLabel.setBorder(null);
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC3);
    }

    private void offLabelMousePressed() {
        offLabel.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music3Label.setBorder(null);
        music2Label.setBorder(null);
        music1Label.setBorder(null);
        musicVolumeSlider.setVisible(false);
        musicVolumeSlider.setValue(15);
        optionsSaver.setMusic(AudioPlayer.OFF);
    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuButton.selectButton();
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuButton.unselectButton();
    }

    private void mainMenuLabelMousePressed() {
        Main.audioPlayer.offCutMusic();
        setOptions();
        saveOptions();
        Main.tetrisFrame.remove(Main.optionPanel.scrollPane);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
    }

    private void resetToDefaultMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = RESET;
        resetToDefaultButton.selectButton();
    }

    private void resetToDefaultMouseExited() {
        currentButtonSelected = false;
        resetToDefaultButton.unselectButton();
    }

    private void resetToDefaultLabelMousePressed() {
        Main.audioPlayer.offCutMusic();
        resetToDefaultMouseExited();
        setDefaultOptions();
        repaint();
    }

    private void shadowCheckBoxMousePressed() {
        optionsSaver.setShadow(!shadowCheckBox.isSelected());
    }

    private void gridCheckBoxMousePressed() {
        optionsSaver.setGrid(!gridCheckBox.isSelected());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(startLevelSlider)) {
            startLevelLabel.setText("" + startLevelSlider.getValue());
            optionsSaver.setStartLevel((byte) startLevelSlider.getValue());
        } else if (e.getSource().equals(musicVolumeSlider)) {
            if (musicVolumeSlider.getValue() == 0) {
                offLabelMousePressed();
                Main.audioPlayer.offCutMusic();
            } else {
                Main.audioPlayer.musicVolume = (double) musicVolumeSlider.getValue() / 100;
                Main.audioPlayer.updateCutMusicVolume();
                optionsSaver.setMusicVolume(musicVolumeSlider.getValue());
            }
        } else if (e.getSource().equals(soundsVolumeSlider)) {
            Main.audioPlayer.soundsVolume = (double) soundsVolumeSlider.getValue() / 100;
            optionsSaver.setSoundsVolume(soundsVolumeSlider.getValue());
        }
        requestFocusInWindow();
    }

    public void setOptions() {
        leftEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getLeftKey()) + "]");
        useKeys[0] = optionsSaver.getLeftKey();
        leftEventLabel.setBorder(null);
        rightEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getRightKey()) + "]");
        useKeys[1] = optionsSaver.getRightKey();
        rightEventLabel.setBorder(null);
        CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCwKey()) + "]");
        useKeys[2] = optionsSaver.getCwKey();
        CWRotationEventLabel.setBorder(null);
        CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCcwKey()) + "]");
        useKeys[3] = optionsSaver.getCcwKey();
        CCWRotationEventLabel.setBorder(null);
        downEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getDownKey()) + "]");
        useKeys[4] = optionsSaver.getDownKey();
        downEventLabel.setBorder(null);
        hardDropEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getHardDropKey()) + "]");
        useKeys[5] = optionsSaver.getHardDropKey();
        hardDropEventLabel.setBorder(null);
        pauseEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getPauseKey()) + "]");
        useKeys[6] = optionsSaver.getPauseKey();
        pauseEventLabel.setBorder(null);
        exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getExitMenuKey()) + "]");
        useKeys[7] = optionsSaver.getExitMenuKey();
        exitToMenuEventLabel.setBorder(null);

        startLevelSlider.setValue(optionsSaver.getStartLevel());
        startLevelLabel.setText("" + startLevelSlider.getValue());

        musicVolumeSlider.setValue(optionsSaver.getMusicVolume());
        soundsVolumeSlider.setValue(optionsSaver.getSoundsVolume());

        if (optionsSaver.getBackgroundType() == TetrisPanel.BACKGROUND)
            backgroundImage1MousePressed();
        else if (optionsSaver.getBackgroundType() == TetrisPanel.BACKGROUND2)
            backgroundImage2MousePressed();
        else if (optionsSaver.getBackgroundType() == TetrisPanel.BACKGROUND3)
            backgroundImage3MousePressed();
        else if (optionsSaver.getBackgroundType() == TetrisPanel.BACKGROUND4)
            backgroundImage4MousePressed();
        else if (optionsSaver.getBackgroundType() == TetrisPanel.BACKGROUND5)
            backgroundImage5MousePressed();

        if (optionsSaver.getRandomType() == OLD_STYLE_RANDOM)
            staticPlainRandomMousePressed();
        else if (optionsSaver.getRandomType() == NEW_STYLE_RANDOM)
            static7BagRandomMousePressed();

        if (optionsSaver.getLineClearAnimation() == DISAPPEAR_CLEAR_LINES_ANIMATION)
            staticDisappearingAnimationLabelMousePressed();
        else if (optionsSaver.getLineClearAnimation() == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
            staticRandomColorAnimationLabelMousePressed();

        if (optionsSaver.getMusic() == AudioPlayer.MUSIC1)
            music1LabelMousePressed();
        else if (optionsSaver.getMusic() == AudioPlayer.MUSIC2)
            music2LabelMousePressed();
        else if (optionsSaver.getMusic() == AudioPlayer.MUSIC3)
            music3LabelMousePressed();
        else if (optionsSaver.getMusic() == AudioPlayer.OFF)
            offLabelMousePressed();

        shadowCheckBox.setSelected(optionsSaver.getShadow());
        gridCheckBox.setSelected(optionsSaver.getGrid());
    }

    public void getOptions() {
        deserializeOptions();
    }

    public void saveOptions() {
        serialize();
    }

    public void setDefaultOptions() {
        resetOptions();
        setOptions();
    }

    public void resetOptions() {
        optionsSaver = new OptionsSaver();
    }

    private void deserializeOptions() {
        try {
            File file = new File(System.getProperty("user.dir"), Main.OPTIONS_FILE_NAME);
            if (file.length() == 0) {
                optionsSaver = new OptionsSaver();
                saveOptions();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), Main.OPTIONS_FILE_NAME).getAbsolutePath()));
                optionsSaver = (OptionsSaver) ois.readObject();
                ois.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert optionsSaver != null;
    }

    private void serialize() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), Main.OPTIONS_FILE_NAME).getAbsolutePath()));
            oos.writeObject(optionsSaver);
            oos.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
        else if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.exitMenuKey) {
            pressExitKey();
        }
    }

    private void pressExitKey() {
        Main.audioPlayer.playClick();
        mainMenuLabelMousePressed();
    }

    private void pressEnterKey() {
        if (currentButtonSelected) {
            Main.audioPlayer.playClick();
            if (buttonController == MAIN_MENU)
                mainMenuLabelMousePressed();
            else {
                resetToDefaultLabelMousePressed();
            }
        }
    }

    private void pressLeftKey() {
        if (buttonController == RESET || !currentButtonSelected) {
            Main.audioPlayer.playClick();
            System.out.println("Left");

            unselectCurrentButton();
            buttonController = MAIN_MENU;
            selectCurrentButton();
        }
    }

    private void pressRightKey() {
        if (buttonController == MAIN_MENU || !currentButtonSelected) {
            Main.audioPlayer.playClick();
            System.out.println("Right");

            unselectCurrentButton();
            buttonController = RESET;
            selectCurrentButton();
        }
    }

    public void selectCurrentButton() {
        if (buttonController == MAIN_MENU)
            mainMenuLabelMouseEntered();
        else
            resetToDefaultMouseEntered();
    }

    private void unselectCurrentButton() {
        if (buttonController == MAIN_MENU)
            mainMenuLabelMouseExited();
        else
            resetToDefaultMouseExited();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    static class BackgroundPanel extends JPanel {

        BufferedImage bufferedImage = null;

        public BackgroundPanel() {
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

                    g.drawImage(bufferedImage, j * bufferedImage.getWidth(), i * bufferedImage.getHeight(), this);
                }
            }
        }
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
            c = Main.optionPanel.scrollPane.getParent();
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

        private void paintOptionTitle(Graphics2D g2d, int startX, int startY, int square_radius) {
            int space = square_radius / 28;
            //O
            PaintStaticLetters.paintLetterO(g2d, startX, startY, radius);
            //P
            PaintStaticLetters.paintLetterP(g2d, startX + 3 * radius + space, startY, radius);
            //T
            PaintStaticLetters.paintLetterT(g2d, startX + 7 * radius + 2 * space, startY, radius);
            //I
            PaintStaticLetters.paintLetterI(g2d, startX + 12 * radius + 3 * space, startY, radius);
            //O
            PaintStaticLetters.paintLetterO(g2d, startX + 15 * radius + 4 * space, startY, radius);
            //N
            PaintStaticLetters.paintLetterN(g2d, startX + 18 * radius + 5 * space, startY, radius);
            //S
            PaintStaticLetters.paintLetterS(g2d, startX + 23 * radius + 6 * space, startY, radius);
        }

        int radius;
        int startX;

        @Override
        protected void paintComponent(Graphics g) {
            radius = Math.min(getWidth() / 29, getHeight() / 6);
            startX = (getWidth() - radius * 29) / 2;

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            paintOptionTitle(g2d, startX, radius, radius);
        }
    }
}