package game.panels.menu.elements;

import game.audio.AudioPlayer;
import game.helperclasses.PaintStaticLetters;
import game.panels.tetris.TetrisPanel;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static game.panels.tetris.playfield.TetrisPlayFieldPanel.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class OptionsPanel extends JPanel implements ChangeListener, KeyListener {

    private static final int MAIN_MENU = 0, RESET = 1;

    private static final String BUTTON_IMAGES_FOLDER = "/res/buttonImages/";
    private static final String UNSELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuBlackRoundedImage.png";
    private static final String SELECTED_MAIN_MENU_PATH = BUTTON_IMAGES_FOLDER + "mainMenuWhiteRoundedImage.png";
    private static final String UNSELECTED_RESET_TO_DEFAULT_PATH = BUTTON_IMAGES_FOLDER + "resetToDefaultRedUnselectedRoundedImage.png";
    private static final String SELECTED_RESET_TO_DEFAULT_PATH = BUTTON_IMAGES_FOLDER + "resetToDefaultRedSelectedRoundedImage.png";

    private static final String BACKGROUND_IMAGES_FOLDER = "/res/backgroundImages/";
    private static final String BACKGROUND_IMAGE_PATH = BACKGROUND_IMAGES_FOLDER + "backgroundImage1.jpg";
    private static final String BACKGROUND_IMAGE_2_PATH = BACKGROUND_IMAGES_FOLDER + "backgroundImage2.jpg";
    private static final String BACKGROUND_IMAGE_3_PATH = BACKGROUND_IMAGES_FOLDER + "backgroundImage3.jpg";
    private static final String BACKGROUND_IMAGE_4_PATH = BACKGROUND_IMAGES_FOLDER + "backgroundImage4.jpg";
    private static final String BACKGROUND_IMAGE_5_PATH = BACKGROUND_IMAGES_FOLDER + "backgroundImage5.jpg";

    private JLabel[] backgroundImageLabels;
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
    private JLabel musicVolumeLabel;
    private JLabel soundsVolumeLabel;
    private JLabel startLevelLabel;
    private JLabel mainMenuLabel;
    private JLabel resetToDefaultLabel;

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
        musicVolumeLabel = new JLabel();
        soundsVolumeLabel = new JLabel();
        startLevelLabel = new JLabel();
        mainMenuLabel = new JLabel();
        resetToDefaultLabel = new JLabel();

        backgroundImageLabels = new JLabel[5];
        backgroundImageLabels[0] = new JLabel();
        backgroundImageLabels[1] = new JLabel();
        backgroundImageLabels[2] = new JLabel();
        backgroundImageLabels[3] = new JLabel();
        backgroundImageLabels[4] = new JLabel();

        startLevelSlider = new JSlider();
        musicVolumeSlider = new JSlider();
        soundsVolumeSlider = new JSlider();

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
        setPreferredSize(new java.awt.Dimension(920, 915));

        staticKeyboardLabel.setBackground(Color.BLACK);
        staticKeyboardLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticKeyboardLabel.setForeground(Main.GREEN_COLOR);
        staticKeyboardLabel.setText("KEYBOARD");

        staticRandomizerLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticRandomizerLabel.setForeground(Main.PINK_COLOR);
        staticRandomizerLabel.setText("RANDOMIZER");

        staticMusicAndSoundsLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticMusicAndSoundsLabel.setForeground(Main.BLUE_COLOR);
        staticMusicAndSoundsLabel.setText("MUSIC AND SOUNDS");

        staticAppearanceLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticAppearanceLabel.setForeground(Main.YELLOW_COLOR);
        staticAppearanceLabel.setText("APPEARANCE");

        staticPauseLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticPauseLabel.setForeground(Color.WHITE);
        staticPauseLabel.setText("pause");

        staticLeftLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticLeftLabel.setForeground(Color.WHITE);
        staticLeftLabel.setText("left");

        staticRightLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticRightLabel.setForeground(Color.WHITE);
        staticRightLabel.setText("right");

        staticDownLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticDownLabel.setForeground(Color.WHITE);
        staticDownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staticDownLabel.setText("down");

        staticCWRotationLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticCWRotationLabel.setForeground(Color.WHITE);
        staticCWRotationLabel.setText("CW-rotation");

        staticHardDropLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticHardDropLabel.setForeground(Color.WHITE);
        staticHardDropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staticHardDropLabel.setText("hard drop");

        staticCCWRotationLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticCCWRotationLabel.setForeground(Color.WHITE);
        staticCCWRotationLabel.setText("CCW-rotation");

        staticExitToMenu.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticExitToMenu.setForeground(Color.WHITE);
        staticExitToMenu.setText("exit to menu");
        staticExitToMenu.setToolTipText("");

        sevenBagRandomLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        sevenBagRandomLabel.setForeground(Color.WHITE);
        sevenBagRandomLabel.setText("7-bag random");
        sevenBagRandomLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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

        plainRandomLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        plainRandomLabel.setForeground(Color.WHITE);
        plainRandomLabel.setText("plain random");
        plainRandomLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        plainRandomLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticPlainRandomMousePressed();
                }
            }
        });

        staticMusicLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticMusicLabel.setForeground(Color.WHITE);
        staticMusicLabel.setText("music");

        staticMusicVolumeLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticMusicVolumeLabel.setForeground(Color.WHITE);
        staticMusicVolumeLabel.setText("music volume");

        staticSoundsVolumeLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticSoundsVolumeLabel.setForeground(Color.WHITE);
        staticSoundsVolumeLabel.setText("sounds volume");

        staticBackgroundImageLabel.setFont(Main.COSMIC_SAN_MS_FONT_18);
        staticBackgroundImageLabel.setForeground(Main.RED_COLOR);
        staticBackgroundImageLabel.setText("BACKGROUND IMAGE");

        disappearingAnimationLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        disappearingAnimationLabel.setForeground(Color.WHITE);
        disappearingAnimationLabel.setText("disappearing");
        disappearingAnimationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        disappearingAnimationLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticDisappearingAnimationLabelMousePressed();
                }
            }
        });

        randomColorsAnimationLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        randomColorsAnimationLabel.setForeground(Color.WHITE);
        randomColorsAnimationLabel.setText("random colors");
        randomColorsAnimationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        randomColorsAnimationLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticRandomColorAnimationLabelMousePressed();
                }
            }
        });

        staticLineClearAnimationLabel.setFont(Main.COSMIC_SAN_MS_FONT_18);
        staticLineClearAnimationLabel.setForeground(Main.ORANGE_COLOR);
        staticLineClearAnimationLabel.setText("LINE CLEAR ANIMATION");

        leftEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        rightEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        CWRotationEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        CCWRotationEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        downEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        hardDropEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        pauseEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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

        exitToMenuEventLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
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


        backgroundImageLabels[0].setForeground(Color.WHITE);
        backgroundImageLabels[0].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_PATH))));
        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[0].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage1MousePressed();
                }
            }
        });

        backgroundImageLabels[2].setForeground(Color.WHITE);
        backgroundImageLabels[2].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_3_PATH))));
        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[2].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage3MousePressed();
                }
            }
        });

        backgroundImageLabels[1].setForeground(Color.WHITE);
        backgroundImageLabels[1].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_2_PATH))));
        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[1].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage2MousePressed();
                }
            }
        });

        backgroundImageLabels[3].setForeground(Color.WHITE);
        backgroundImageLabels[3].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_4_PATH))));
        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[3].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage4MousePressed();
                }
            }
        });

        backgroundImageLabels[4].setForeground(Color.WHITE);
        backgroundImageLabels[4].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_5_PATH))));
        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[4].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage5MousePressed();
                }
            }
        });

        music1Label.setFont(Main.COSMIC_SAN_MS_FONT_24);
        music1Label.setForeground(Color.WHITE);
        music1Label.setText("music1");
        music1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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

        music2Label.setFont(Main.COSMIC_SAN_MS_FONT_24);
        music2Label.setForeground(Color.WHITE);
        music2Label.setText("music2");
        music2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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

        music3Label.setFont(Main.COSMIC_SAN_MS_FONT_24);
        music3Label.setForeground(Color.WHITE);
        music3Label.setText("music3");
        music3Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        staticStartLevelLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticStartLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticStartLevelLabel.setText("START LEVEL");

        offLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        offLabel.setForeground(Color.WHITE);
        offLabel.setText("OFF");
        offLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        offLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.offCutMusic();
                    offLabelMousePressed();
                }
            }
        });

        musicVolumeLabel.setFont(Main.COSMIC_SAN_MS_FONT_16);
        musicVolumeLabel.setForeground(Color.WHITE);
        musicVolumeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        soundsVolumeLabel.setFont(Main.COSMIC_SAN_MS_FONT_16);
        soundsVolumeLabel.setForeground(Color.WHITE);
        soundsVolumeLabel.setHorizontalAlignment(SwingConstants.CENTER);


        startLevelLabel.setFont(Main.COSMIC_SAN_MS_FONT_16);
        startLevelLabel.setForeground(Color.WHITE);
        startLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainMenuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
        mainMenuLabel.addMouseListener(new MouseAdapter() {
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

        resetToDefaultLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_RESET_TO_DEFAULT_PATH))));
        resetToDefaultLabel.addMouseListener(new MouseAdapter() {
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

        shadowCheckBox.setBackground(new java.awt.Color(0, 0, 0));
        shadowCheckBox.setFont(Main.COSMIC_SAN_MS_FONT_24);
        shadowCheckBox.setForeground(Color.WHITE);
        shadowCheckBox.setText("shadow");
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
        staticShadowLevelLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticShadowLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticShadowLevelLabel.setText("SHADOW");

        staticGridLevelLabel.setBackground(Color.BLACK);
        staticGridLevelLabel.setFont(Main.COSMIC_SAN_MS_FONT_24);
        staticGridLevelLabel.setForeground(Main.DARK_BLUE_COLOR);
        staticGridLevelLabel.setText("GRID");

        gridCheckBox.setBackground(Color.BLACK);
        gridCheckBox.setFont(Main.COSMIC_SAN_MS_FONT_24);
        gridCheckBox.setForeground(Color.WHITE);
        gridCheckBox.setText("grid");
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


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(staticAppearanceLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticBackgroundImageLabel)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(staticLineClearAnimationLabel)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(33, 33, 33)
                                                                                                .addComponent(randomColorsAnimationLabel)))
                                                                                .addGap(50, 50, 50)
                                                                                .addComponent(disappearingAnimationLabel)))))
                                                .addContainerGap(456, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(backgroundImageLabels[0], GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[1], GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[2], GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[3], GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[4], GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticMusicLabel)
                                                                        .addComponent(staticSoundsVolumeLabel)
                                                                        .addComponent(staticMusicVolumeLabel))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(soundsVolumeSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(music1Label)
                                                                                .addGap(25, 25, 25)
                                                                                .addComponent(music2Label)
                                                                                .addGap(25, 25, 25)
                                                                                .addComponent(music3Label)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(offLabel)
                                                                                .addGap(0, 7, Short.MAX_VALUE))
                                                                        .addComponent(musicVolumeSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(3, 3, 3)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(musicVolumeLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(soundsVolumeLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 230, Short.MAX_VALUE)))
                                                .addGap(48, 48, 48))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(plainRandomLabel)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(sevenBagRandomLabel))
                                                        .addComponent(staticRandomizerLabel)
                                                        .addComponent(staticMusicAndSoundsLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(20, 20, 20)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(staticRightLabel)
                                                                                                        .addComponent(staticLeftLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(staticDownLabel))
                                                                                        .addComponent(staticHardDropLabel)))
                                                                        .addComponent(staticKeyboardLabel))
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(downEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rightEventLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(leftEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                        .addGap(6, 6, 6)
                                                                                        .addComponent(hardDropEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(321, 321, 321)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(staticCCWRotationLabel)
                                                                                        .addComponent(staticExitToMenu)
                                                                                        .addComponent(staticPauseLabel)
                                                                                        .addComponent(staticCWRotationLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(6, 6, 6)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(CCWRotationEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(CWRotationEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(exitToMenuEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(pauseEventLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(startLevelSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(startLevelLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(staticStartLevelLabel))
                                                                .addGap(50, 50, 50)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticShadowLevelLabel)
                                                                        .addComponent(shadowCheckBox))
                                                                .addGap(100, 100, 100)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(gridCheckBox)
                                                                        .addComponent(staticGridLevelLabel))))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(mainMenuLabel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetToDefaultLabel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticStartLevelLabel)
                                        .addComponent(staticShadowLevelLabel)
                                        .addComponent(staticGridLevelLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(startLevelLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                .addComponent(shadowCheckBox)
                                                                .addComponent(gridCheckBox))
                                                        .addComponent(startLevelSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(staticKeyboardLabel)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticLeftLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(leftEventLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(staticCWRotationLabel)
                                                                .addComponent(CWRotationEventLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(rightEventLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticRightLabel)
                                                .addComponent(staticCCWRotationLabel)
                                                .addComponent(CCWRotationEventLabel)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(downEventLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticDownLabel)
                                                .addComponent(staticExitToMenu)
                                                .addComponent(exitToMenuEventLabel)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(hardDropEventLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticHardDropLabel)
                                                .addComponent(pauseEventLabel)
                                                .addComponent(staticPauseLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staticRandomizerLabel)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(plainRandomLabel)
                                        .addComponent(sevenBagRandomLabel))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(staticMusicAndSoundsLabel)
                                                .addGap(0, 0, 0)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(staticMusicLabel)
                                                        .addComponent(music1Label)
                                                        .addComponent(music2Label)
                                                        .addComponent(music3Label)
                                                        .addComponent(offLabel))
                                                .addGap(0, 0, 0)
                                                .addComponent(staticMusicVolumeLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticSoundsVolumeLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(staticAppearanceLabel)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticBackgroundImageLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(musicVolumeSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(musicVolumeLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(soundsVolumeSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(soundsVolumeLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(72, 72, 72)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(backgroundImageLabels[1], GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[2], GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[3], GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[4], GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[0], GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staticLineClearAnimationLabel)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(disappearingAnimationLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(randomColorsAnimationLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(mainMenuLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resetToDefaultLabel))
                                .addContainerGap())
        );
    }

    private void static7BagRandomMouseEntered() {
    }

    private void static7BagRandomMouseExited() {
    }

    private void static7BagRandomMousePressed() {

        sevenBagRandomLabel.setBorder(BorderFactory.createLineBorder(Main.PINK_COLOR, 3));
        plainRandomLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        optionsSaver.setRandomType(NEW_STYLE_RANDOM);
    }

    private void staticPlainRandomMousePressed() {

        sevenBagRandomLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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

        leftEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        rightEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        CWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        CCWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        downEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        hardDropEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        pauseEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        exitToMenuEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        exitToMenuEventLabel.setFocusable(false);
    }

    private void backgroundImage1MousePressed() {

        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Main.RED_COLOR, 3));
        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND);

        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

    }

    private void backgroundImage2MousePressed() {

        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Main.RED_COLOR, 3));

        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND2;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND2);

        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    private void backgroundImage3MousePressed() {

        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Main.RED_COLOR, 3));

        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND3;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND3);

        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    private void backgroundImage4MousePressed() {

        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Main.RED_COLOR, 3));

        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND4;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND4);

        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    private void backgroundImage5MousePressed() {

        backgroundImageLabels[4].setBorder(BorderFactory.createLineBorder(Main.RED_COLOR, 3));

        Main.tetrisPanel.backgroundType = TetrisPanel.BACKGROUND5;
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND5);

        backgroundImageLabels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[2].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundImageLabels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    private void staticDisappearingAnimationLabelMousePressed() {

        randomColorsAnimationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        disappearingAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        optionsSaver.setLineClearAnimation(DISAPPEAR_CLEAR_LINES_ANIMATION);
    }

    private void staticRandomColorAnimationLabelMousePressed() {

        disappearingAnimationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        randomColorsAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        optionsSaver.setLineClearAnimation(RANDOM_COLOR_CLEAR_LINES_ANIMATION);
    }

    private void music1LabelMousePressed() {

        music1Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        music3Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        offLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC1);
    }

    private void music2LabelMousePressed() {

        music2Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        music3Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        offLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC2);

    }

    private void music3LabelMousePressed() {
        music3Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        music1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        offLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC3);

    }

    private void offLabelMousePressed() {

        offLabel.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        music3Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        music2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        music1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        musicVolumeSlider.setVisible(false);
        musicVolumeSlider.setValue(15);
        optionsSaver.setMusic(AudioPlayer.OFF);
    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
    }

    private void mainMenuLabelMousePressed() {

        Main.audioPlayer.offCutMusic();
        mainMenuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_MAIN_MENU_PATH))));
        setOptions();
        saveOptions();
        Main.tetrisFrame.remove(Main.optionPanel);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.repaint();

    }

    private void resetToDefaultMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = RESET;
        resetToDefaultLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(SELECTED_RESET_TO_DEFAULT_PATH))));
    }

    private void resetToDefaultMouseExited() {
        currentButtonSelected = false;
        resetToDefaultLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(UNSELECTED_RESET_TO_DEFAULT_PATH))));
    }

    private void resetToDefaultLabelMousePressed() {
        Main.audioPlayer.offCutMusic();
        resetToDefaultMouseExited();
        setDefaultOptions();

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
                musicVolumeLabel.setText("" + musicVolumeSlider.getValue());
                Main.audioPlayer.musicVolume = (double) musicVolumeSlider.getValue() / 100;
                Main.audioPlayer.updateCutMusicVolume();
                optionsSaver.setMusicVolume(musicVolumeSlider.getValue());
            }
        } else if (e.getSource().equals(soundsVolumeSlider)) {
            Main.audioPlayer.soundsVolume = (double) soundsVolumeSlider.getValue() / 100;
            optionsSaver.setSoundsVolume(soundsVolumeSlider.getValue());
            if (soundsVolumeSlider.getValue() == 0)
                soundsVolumeLabel.setText("mute");
            else
                soundsVolumeLabel.setText("" + soundsVolumeSlider.getValue());
        }
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        paintLeaderBoardTitle(g2d);
    }

    private void paintLeaderBoardTitle(Graphics2D g2d) {
        int startX = 180, startY = 5, radius = 20, space = 3;
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

    public void setOptions() {
        leftEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getLeftKey()) + "]");
        useKeys[0] = optionsSaver.getLeftKey();
        leftEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        rightEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getRightKey()) + "]");
        useKeys[1] = optionsSaver.getRightKey();
        rightEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCwKey()) + "]");
        useKeys[2] = optionsSaver.getCwKey();
        CWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCcwKey()) + "]");
        useKeys[3] = optionsSaver.getCcwKey();
        CCWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        downEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getDownKey()) + "]");
        useKeys[4] = optionsSaver.getDownKey();
        downEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        hardDropEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getHardDropKey()) + "]");
        useKeys[5] = optionsSaver.getHardDropKey();
        hardDropEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        pauseEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getPauseKey()) + "]");
        useKeys[6] = optionsSaver.getPauseKey();
        pauseEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getExitMenuKey()) + "]");
        useKeys[7] = optionsSaver.getExitMenuKey();
        exitToMenuEventLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));


        startLevelSlider.setValue(optionsSaver.getStartLevel());
        startLevelLabel.setText("" + startLevelSlider.getValue());

        musicVolumeSlider.setValue(optionsSaver.getMusicVolume());
        musicVolumeLabel.setText("" + musicVolumeSlider.getValue());

        soundsVolumeSlider.setValue(optionsSaver.getSoundsVolume());
        if (soundsVolumeSlider.getValue() == 0)
            soundsVolumeLabel.setText("mute");
        else
            soundsVolumeLabel.setText("" + soundsVolumeSlider.getValue());

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

}

