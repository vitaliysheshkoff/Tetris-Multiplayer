package game.panels.menu.elements;

import game.audio.AudioPlayer;
import game.helperclasses.PaintStaticLetters;
import game.panels.tetris.TetrisPanel;
import game.panels.tetris.playfield.controller.Painting;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import static game.panels.tetris.playfield.TetrisPlayFieldPanel.*;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsPanel extends javax.swing.JPanel implements ChangeListener, KeyListener {

    private static final int MAIN_MENU = 0, RESET = 1;
    private int buttonController = MAIN_MENU;
    private boolean currentButtonSelected = true;

    OptionsSaver optionsSaver;
    private JLabel[] backgroundImageLabels;
    private javax.swing.JLabel CCWRotationEventLabel;
    private javax.swing.JLabel CWRotationEventLabel;
    private javax.swing.JLabel downEventLabel;
    private javax.swing.JLabel pauseEventLabel;
    private javax.swing.JLabel rightEventLabel;
    private javax.swing.JLabel exitToMenuEventLabel;
    private javax.swing.JLabel hardDropEventLabel;
    public javax.swing.JSlider startLevelSlider;
    private javax.swing.JSlider soundsVolumeSlider;
    private javax.swing.JSlider musicVolumeSlider;
    private javax.swing.JLabel leftEventLabel;
    private javax.swing.JLabel music1Label;
    private javax.swing.JLabel music2Label;
    private javax.swing.JLabel music3Label;
    private javax.swing.JLabel offLabel;
    private javax.swing.JLabel sevenBagRandomLabel;
    private javax.swing.JLabel plainRandomLabel;
    private javax.swing.JLabel randomColorsAnimationLabel;
    private javax.swing.JLabel disappearingAnimationLabel;
    private JLabel musicVolumeLabel;
    private JLabel soundsVolumeLabel;
    private JLabel startLevelLabel;
    private JLabel mainMenuLabel;
    private JLabel resetToDefaultLabel;
    private JCheckBox shadowCheckBox;
    private JCheckBox gridCheckBox;

    private int[] useKeys;

    public OptionsPanel() {
        initComponents();
        setOptions();
        addKeyListener(this);
    }

    private void initComponents() {

        useKeys = new int[8];

        gridCheckBox = new JCheckBox();
        JLabel staticGridLevelLabel = new JLabel();
        JLabel staticShadowLevelLabel = new JLabel();
        shadowCheckBox = new javax.swing.JCheckBox();
        optionsSaver = new OptionsSaver();
        getOptions();
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
        sevenBagRandomLabel = new javax.swing.JLabel();
        plainRandomLabel = new javax.swing.JLabel();
        JLabel staticMusicLabel = new JLabel();
        JLabel staticMusicVolumeLabel = new JLabel();
        JLabel staticSoundsVolumeLabel = new JLabel();
        JLabel staticBackgroundImageLabel = new JLabel();
        disappearingAnimationLabel = new javax.swing.JLabel();
        randomColorsAnimationLabel = new javax.swing.JLabel();
        JLabel staticLineClearAnimationLabel = new JLabel();
        leftEventLabel = new javax.swing.JLabel();
        rightEventLabel = new javax.swing.JLabel();
        CWRotationEventLabel = new javax.swing.JLabel();
        CCWRotationEventLabel = new javax.swing.JLabel();
        downEventLabel = new javax.swing.JLabel();
        hardDropEventLabel = new javax.swing.JLabel();
        pauseEventLabel = new javax.swing.JLabel();
        exitToMenuEventLabel = new javax.swing.JLabel();

        music1Label = new javax.swing.JLabel();
        music2Label = new javax.swing.JLabel();
        music3Label = new javax.swing.JLabel();
        JLabel staticStartLevelLabel = new JLabel();
        startLevelSlider = new javax.swing.JSlider();
        musicVolumeSlider = new javax.swing.JSlider();
        soundsVolumeSlider = new javax.swing.JSlider();
        offLabel = new javax.swing.JLabel();

        startLevelSlider.setMaximum(29);
        startLevelSlider.addChangeListener(this);
        soundsVolumeSlider.addChangeListener(this);
        musicVolumeSlider.addChangeListener(this);


        musicVolumeLabel = new javax.swing.JLabel();
        soundsVolumeLabel = new javax.swing.JLabel();
        startLevelLabel = new javax.swing.JLabel();
        mainMenuLabel = new javax.swing.JLabel();
        resetToDefaultLabel = new javax.swing.JLabel();


        backgroundImageLabels = new JLabel[5];
        backgroundImageLabels[0] = new JLabel();
        backgroundImageLabels[1] = new JLabel();
        backgroundImageLabels[2] = new JLabel();
        backgroundImageLabels[3] = new JLabel();
        backgroundImageLabels[4] = new JLabel();


        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(920, 915));

        staticKeyboardLabel.setBackground(new java.awt.Color(0, 0, 0));
        staticKeyboardLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticKeyboardLabel.setForeground(new java.awt.Color(114, 203, 59));
        staticKeyboardLabel.setText("KEYBOARD");

        staticRandomizerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticRandomizerLabel.setForeground(new java.awt.Color(139, 0, 139));
        staticRandomizerLabel.setText("RANDOMIZER");

        staticMusicAndSoundsLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticMusicAndSoundsLabel.setForeground(new java.awt.Color(0, 206, 209));
        staticMusicAndSoundsLabel.setText("MUSIC AND SOUNDS");

        staticAppearanceLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticAppearanceLabel.setForeground(new java.awt.Color(255, 255, 0));
        staticAppearanceLabel.setText("APPEARANCE");

        staticPauseLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticPauseLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticPauseLabel.setText("pause");

        staticLeftLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticLeftLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticLeftLabel.setText("left");

        staticRightLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticRightLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticRightLabel.setText("right");

        staticDownLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticDownLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticDownLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staticDownLabel.setText("down");

        staticCWRotationLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticCWRotationLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticCWRotationLabel.setText("CW-rotation");

        staticHardDropLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticHardDropLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticHardDropLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staticHardDropLabel.setText("hard drop");

        staticCCWRotationLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticCCWRotationLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticCCWRotationLabel.setText("CCW-rotation");

        staticExitToMenu.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticExitToMenu.setForeground(new java.awt.Color(255, 255, 255));
        staticExitToMenu.setText("exit to menu");
        staticExitToMenu.setToolTipText("");

        sevenBagRandomLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        sevenBagRandomLabel.setForeground(new java.awt.Color(255, 255, 255));
        sevenBagRandomLabel.setText("7-bag random");
        sevenBagRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        sevenBagRandomLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                static7BagRandomMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                static7BagRandomMouseExited();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    static7BagRandomMousePressed();
                }
            }
        });

        plainRandomLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        plainRandomLabel.setForeground(new java.awt.Color(255, 255, 255));
        plainRandomLabel.setText("plain random");
        plainRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        plainRandomLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticPlainRandomMousePressed();
                }
            }
        });

        staticMusicLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticMusicLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticMusicLabel.setText("music");

        staticMusicVolumeLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticMusicVolumeLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticMusicVolumeLabel.setText("music volume");

        staticSoundsVolumeLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticSoundsVolumeLabel.setForeground(new java.awt.Color(255, 255, 255));
        staticSoundsVolumeLabel.setText("sounds volume");

        staticBackgroundImageLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 18));
        staticBackgroundImageLabel.setForeground(new java.awt.Color(255, 50, 19));
        staticBackgroundImageLabel.setText("BACKGROUND IMAGE");

        disappearingAnimationLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        disappearingAnimationLabel.setForeground(new java.awt.Color(255, 255, 255));
        disappearingAnimationLabel.setText("disappearing");
        disappearingAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        disappearingAnimationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticDisappearingAnimationLabelMousePressed();
                }
            }
        });

        randomColorsAnimationLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        randomColorsAnimationLabel.setForeground(new java.awt.Color(255, 255, 255));
        randomColorsAnimationLabel.setText("random colors");
        randomColorsAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        randomColorsAnimationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    staticRandomColorAnimationLabelMousePressed();
                }
            }
        });

        staticLineClearAnimationLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 18));
        staticLineClearAnimationLabel.setForeground(new java.awt.Color(255, 151, 28));
        staticLineClearAnimationLabel.setText("LINE CLEAR ANIMATION");

        leftEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        leftEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        leftEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[0] = -1;
                    leftEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                leftEventLabelMouseReleased();
            }
        });
        leftEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                leftEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                leftEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        rightEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        rightEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        rightEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[1] = -1;
                    rightEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rightEventLabelMouseReleased();
            }
        });
        rightEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                rightEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                rightEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        CWRotationEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        CWRotationEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        CWRotationEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CWRotationEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[2] = -1;
                    CWRotationEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CWRotationEventLabelMouseReleased();
            }
        });
        CWRotationEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                CWRotationEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                CWRotationEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        CCWRotationEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        CCWRotationEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        CCWRotationEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CCWRotationEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[3] = -1;
                    CCWRotationEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CCWRotationEventLabelMouseReleased();
            }
        });
        CCWRotationEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();

                CCWRotationEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                CCWRotationEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        downEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        downEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        downEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[4] = -1;
                    downEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                downEventLabelMouseReleased();
            }
        });
        downEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                downEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                downEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        hardDropEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        hardDropEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        hardDropEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hardDropEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[5] = -1;
                    hardDropEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hardDropEventLabelMouseReleased();
            }
        });
        hardDropEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                hardDropEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                hardDropEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        pauseEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        pauseEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        pauseEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pauseEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[6] = -1;
                    pauseEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pauseEventLabelMouseReleased();
            }
        });
        pauseEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                pauseEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                pauseEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });

        exitToMenuEventLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        exitToMenuEventLabel.setForeground(new java.awt.Color(255, 255, 255));
        exitToMenuEventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitToMenuEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    useKeys[7] = -1;
                    exitToMenuEventLabelMousePressed();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitToMenuEventLabelMouseReleased();
            }
        });
        exitToMenuEventLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Main.audioPlayer.playClick();
                exitToMenuEventLabelKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                exitToMenuEventLabelKeyReleased();
                requestFocusInWindow();
            }
        });


        backgroundImageLabels[0].setForeground(new java.awt.Color(255, 255, 255));
        backgroundImageLabels[0].setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/backgroundImages/backgroundImage1.jpg"))));
        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage1MousePressed();
                }
            }
        });

        backgroundImageLabels[2].setForeground(new java.awt.Color(255, 255, 255));
        backgroundImageLabels[2].setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/backgroundImages/backgroundImage3.jpg"))));
        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage3MousePressed();
                }
            }
        });

        backgroundImageLabels[1].setForeground(new java.awt.Color(255, 255, 255));
        backgroundImageLabels[1].setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/backgroundImages/backgroundImage2.jpg"))));
        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage2MousePressed();
                }
            }
        });

        backgroundImageLabels[3].setForeground(new java.awt.Color(255, 255, 255));
        backgroundImageLabels[3].setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/backgroundImages/backgroundImage4.jpg"))));
        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage4MousePressed();
                }
            }
        });

        backgroundImageLabels[4].setForeground(new java.awt.Color(255, 255, 255));
        backgroundImageLabels[4].setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/backgroundImages/backgroundImage5.jpg"))));
        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[4].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    backgroundImage5MousePressed();
                }
            }
        });

        music1Label.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        music1Label.setForeground(new java.awt.Color(255, 255, 255));
        music1Label.setText("music1");
        music1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic1();
                    Main.audioPlayer.newMusic = true;
                    music1LabelMousePressed();
                }
            }
        });

        music2Label.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        music2Label.setForeground(new java.awt.Color(255, 255, 255));
        music2Label.setText("music2");
        music2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic2();
                    Main.audioPlayer.newMusic = true;
                    music2LabelMousePressed();
                }
            }
        });

        music3Label.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        music3Label.setForeground(new java.awt.Color(255, 255, 255));
        music3Label.setText("music3");
        music3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.playCutMusic3();
                    Main.audioPlayer.newMusic = true;
                    music3LabelMousePressed();
                }
            }
        });

        staticStartLevelLabel.setBackground(new java.awt.Color(0, 0, 0));
        staticStartLevelLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticStartLevelLabel.setForeground(new java.awt.Color(3, 65, 174));
        staticStartLevelLabel.setText("START LEVEL");

        offLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        offLabel.setForeground(new java.awt.Color(255, 255, 255));
        offLabel.setText("OFF");
        offLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        offLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.offCutMusic();
                    offLabelMousePressed();
                }
            }
        });

        musicVolumeLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 16));
        musicVolumeLabel.setForeground(new java.awt.Color(255, 255, 255));
        musicVolumeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        soundsVolumeLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 16));
        soundsVolumeLabel.setForeground(new java.awt.Color(255, 255, 255));
        soundsVolumeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        startLevelLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 16));
        startLevelLabel.setForeground(new java.awt.Color(255, 255, 255));
        startLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/mainMenuBlackRoundedImage.png"))));
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

        resetToDefaultLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/resetToDefaultRedUnselectedRoundedImage.png"))));
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
        shadowCheckBox.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        shadowCheckBox.setForeground(new java.awt.Color(255, 255, 255));
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

        staticShadowLevelLabel.setBackground(new java.awt.Color(0, 0, 0));
        staticShadowLevelLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticShadowLevelLabel.setForeground(new java.awt.Color(3, 65, 174));
        staticShadowLevelLabel.setText("SHADOW");

        staticGridLevelLabel.setBackground(new java.awt.Color(0, 0, 0));
        staticGridLevelLabel.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        staticGridLevelLabel.setForeground(new java.awt.Color(3, 65, 174));
        staticGridLevelLabel.setText("GRID");

        gridCheckBox.setBackground(new java.awt.Color(0, 0, 0));
        gridCheckBox.setFont(new java.awt.Font("Comic Sans MS", Font.PLAIN, 24));
        gridCheckBox.setForeground(new java.awt.Color(255, 255, 255));
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


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(staticAppearanceLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticBackgroundImageLabel)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(staticLineClearAnimationLabel)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(33, 33, 33)
                                                                                                .addComponent(randomColorsAnimationLabel)))
                                                                                .addGap(50, 50, 50)
                                                                                .addComponent(disappearingAnimationLabel)))))
                                                .addContainerGap(456, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(backgroundImageLabels[0], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[1], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[2], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[3], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(backgroundImageLabels[4], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticMusicLabel)
                                                                        .addComponent(staticSoundsVolumeLabel)
                                                                        .addComponent(staticMusicVolumeLabel))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(soundsVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(music1Label)
                                                                                .addGap(25, 25, 25)
                                                                                .addComponent(music2Label)
                                                                                .addGap(25, 25, 25)
                                                                                .addComponent(music3Label)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(offLabel)
                                                                                .addGap(0, 7, Short.MAX_VALUE))
                                                                        .addComponent(musicVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(3, 3, 3)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(musicVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(soundsVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 230, Short.MAX_VALUE)))
                                                .addGap(48, 48, 48))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(plainRandomLabel)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(sevenBagRandomLabel))
                                                        .addComponent(staticRandomizerLabel)
                                                        .addComponent(staticMusicAndSoundsLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(20, 20, 20)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(staticRightLabel)
                                                                                                        .addComponent(staticLeftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(staticDownLabel))
                                                                                        .addComponent(staticHardDropLabel)))
                                                                        .addComponent(staticKeyboardLabel))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(downEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rightEventLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(leftEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                        .addGap(6, 6, 6)
                                                                                        .addComponent(hardDropEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(321, 321, 321)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(staticCCWRotationLabel)
                                                                                        .addComponent(staticExitToMenu)
                                                                                        .addComponent(staticPauseLabel)
                                                                                        .addComponent(staticCWRotationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(6, 6, 6)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(CCWRotationEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(CWRotationEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(exitToMenuEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(pauseEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(startLevelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(startLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(staticStartLevelLabel))
                                                                .addGap(50, 50, 50)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(staticShadowLevelLabel)
                                                                        .addComponent(shadowCheckBox))
                                                                .addGap(100, 100, 100)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(gridCheckBox)
                                                                        .addComponent(staticGridLevelLabel))))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(mainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetToDefaultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(staticStartLevelLabel)
                                        .addComponent(staticShadowLevelLabel)
                                        .addComponent(staticGridLevelLabel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(startLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                .addComponent(shadowCheckBox)
                                                                .addComponent(gridCheckBox))
                                                        .addComponent(startLevelSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(staticKeyboardLabel)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticLeftLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(leftEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(staticCWRotationLabel)
                                                                .addComponent(CWRotationEventLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rightEventLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticRightLabel)
                                                .addComponent(staticCCWRotationLabel)
                                                .addComponent(CCWRotationEventLabel)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(downEventLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticDownLabel)
                                                .addComponent(staticExitToMenu)
                                                .addComponent(exitToMenuEventLabel)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hardDropEventLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(staticHardDropLabel)
                                                .addComponent(pauseEventLabel)
                                                .addComponent(staticPauseLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staticRandomizerLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(plainRandomLabel)
                                        .addComponent(sevenBagRandomLabel))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(staticMusicAndSoundsLabel)
                                                .addGap(0, 0, 0)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(staticMusicLabel)
                                                        .addComponent(music1Label)
                                                        .addComponent(music2Label)
                                                        .addComponent(music3Label)
                                                        .addComponent(offLabel))
                                                .addGap(0, 0, 0)
                                                .addComponent(staticMusicVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticSoundsVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(staticAppearanceLabel)
                                                .addGap(0, 0, 0)
                                                .addComponent(staticBackgroundImageLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(musicVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(musicVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(soundsVolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(soundsVolumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(72, 72, 72)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(backgroundImageLabels[1], javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[2], javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[3], javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[4], javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundImageLabels[0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staticLineClearAnimationLabel)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(disappearingAnimationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(randomColorsAnimationLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(mainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resetToDefaultLabel))
                                .addContainerGap())
        );
    }

    private void static7BagRandomMouseEntered() {
    }

    private void static7BagRandomMouseExited() {
    }

    private void static7BagRandomMousePressed() {

        sevenBagRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.T_COLOR, 3));
        plainRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        optionsSaver.setRandomType(NEW_STYLE_RANDOM);
    }

    private void staticPlainRandomMousePressed() {

        sevenBagRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        plainRandomLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.T_COLOR, 3));
        optionsSaver.setRandomType(OLD_STYLE_RANDOM);
    }

    private void leftEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            leftEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setLeftKey(evt.getKeyCode());
            useKeys[0] = evt.getKeyCode();
        }

    }

    private void leftEventLabelMousePressed() {

        leftEventLabel.setFocusable(true);
        leftEventLabel.requestFocusInWindow();
        leftEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        leftEventLabel.setText("[choose key]");
    }

    private void leftEventLabelMouseReleased() {

    }

    private void leftEventLabelKeyReleased() {

        leftEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        leftEventLabel.setFocusable(false);
    }

    private void rightEventLabelMousePressed() {

        rightEventLabel.setFocusable(true);
        rightEventLabel.requestFocusInWindow();
        rightEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        rightEventLabel.setText("[choose key]");
    }

    private void rightEventLabelMouseReleased() {
    }

    private void rightEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            rightEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setRightKey(evt.getKeyCode());
            useKeys[1] = evt.getKeyCode();
        }
    }

    private void rightEventLabelKeyReleased() {
        rightEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        rightEventLabel.setFocusable(false);
    }

    private void CWRotationEventLabelMousePressed() {

        CWRotationEventLabel.setFocusable(true);
        CWRotationEventLabel.requestFocusInWindow();
        CWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        CWRotationEventLabel.setText("[choose key]");
    }

    private void CWRotationEventLabelMouseReleased() {
    }

    private void CWRotationEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setCwKey(evt.getKeyCode());
            useKeys[2] = evt.getKeyCode();
        }
    }

    private void CWRotationEventLabelKeyReleased() {
        CWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CWRotationEventLabel.setFocusable(false);
    }

    private void CCWRotationEventLabelMousePressed() {

        CCWRotationEventLabel.setFocusable(true);
        CCWRotationEventLabel.requestFocusInWindow();
        CCWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        CCWRotationEventLabel.setText("[choose key]");
    }

    private void CCWRotationEventLabelMouseReleased() {
    }

    private void CCWRotationEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setCcwKey(evt.getKeyCode());
            useKeys[3] = evt.getKeyCode();
        }
    }

    private void CCWRotationEventLabelKeyReleased() {
        CCWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CCWRotationEventLabel.setFocusable(false);
    }

    private void downEventLabelMousePressed() {

        downEventLabel.setFocusable(true);
        downEventLabel.requestFocusInWindow();
        downEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        downEventLabel.setText("[choose key]");
    }

    private void downEventLabelMouseReleased() {
    }

    private void downEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            downEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setDownKey(evt.getKeyCode());
            useKeys[4] = evt.getKeyCode();
        }
    }

    private void downEventLabelKeyReleased() {
        downEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        downEventLabel.setFocusable(false);
    }

    private void hardDropEventLabelMousePressed() {

        hardDropEventLabel.setFocusable(true);
        hardDropEventLabel.requestFocusInWindow();
        hardDropEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        hardDropEventLabel.setText("[choose key]");
    }

    private void hardDropEventLabelMouseReleased() {
    }

    private void hardDropEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            hardDropEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setHardDropKey(evt.getKeyCode());
            useKeys[5] = evt.getKeyCode();
        }
    }

    private void hardDropEventLabelKeyReleased() {
        hardDropEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        hardDropEventLabel.setFocusable(false);
    }

    private void pauseEventLabelMousePressed() {

        pauseEventLabel.setFocusable(true);
        pauseEventLabel.requestFocusInWindow();
        pauseEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        pauseEventLabel.setText("[choose key]");
    }

    private void pauseEventLabelMouseReleased() {
    }

    private void pauseEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            pauseEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setPauseKey(evt.getKeyCode());
            useKeys[6] = evt.getKeyCode();
        }
    }

    private void pauseEventLabelKeyReleased() {
        pauseEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pauseEventLabel.setFocusable(false);
    }

    private void exitToMenuEventLabelMousePressed() {

        exitToMenuEventLabel.setFocusable(true);
        exitToMenuEventLabel.requestFocusInWindow();
        exitToMenuEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.S_COLOR, 3));
        exitToMenuEventLabel.setText("[choose key]");
    }

    private void exitToMenuEventLabelMouseReleased() {
    }

    private void exitToMenuEventLabelKeyPressed(java.awt.event.KeyEvent evt) {

        if (IntStream.of(useKeys).noneMatch(x -> x == evt.getKeyCode())) {
            exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            optionsSaver.setExitMenuKey(evt.getKeyCode());
            useKeys[7] = evt.getKeyCode();
            Main.tetrisPanel.tetrisPlayFieldPanel.exitMenuKey = evt.getKeyCode();
        }
    }

    private void exitToMenuEventLabelKeyReleased() {
        exitToMenuEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        exitToMenuEventLabel.setFocusable(false);
    }

    private void backgroundImage1MousePressed() {

        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(Painting.Z_COLOR, 3));
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND);

        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    }

    private void backgroundImage2MousePressed() {

        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(Painting.Z_COLOR, 3));
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND2);

        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    }

    private void backgroundImage3MousePressed() {

        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(Painting.Z_COLOR, 3));
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND3);

        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    }

    private void backgroundImage4MousePressed() {

        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(Painting.Z_COLOR, 3));
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND4);

        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    }

    private void backgroundImage5MousePressed() {

        backgroundImageLabels[4].setBorder(javax.swing.BorderFactory.createLineBorder(Painting.Z_COLOR, 3));
        optionsSaver.setBackgroundType(TetrisPanel.BACKGROUND5);

        backgroundImageLabels[1].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[2].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[3].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        backgroundImageLabels[0].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    }

    private void staticDisappearingAnimationLabelMousePressed() {

        randomColorsAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        disappearingAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.L_COLOR, 3));
        optionsSaver.setLineClearAnimation(DISAPPEAR_CLEAR_LINES_ANIMATION);
    }

    private void staticRandomColorAnimationLabelMousePressed() {

        disappearingAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        randomColorsAnimationLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.L_COLOR, 3));
        optionsSaver.setLineClearAnimation(RANDOM_COLOR_CLEAR_LINES_ANIMATION);
    }

    private void music1LabelMousePressed() {

        music1Label.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.I_COLOR, 3));
        music2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        offLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC1);
    }

    private void music2LabelMousePressed() {

        music2Label.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.I_COLOR, 3));
        music1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        offLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC2);

    }

    private void music3LabelMousePressed() {
        music3Label.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.I_COLOR, 3));
        music2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        offLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        musicVolumeSlider.setVisible(true);
        optionsSaver.setMusic(AudioPlayer.MUSIC3);

    }

    private void offLabelMousePressed() {

        offLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Painting.I_COLOR, 3));
        music3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        music1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        musicVolumeSlider.setVisible(false);
        musicVolumeSlider.setValue(15);
        optionsSaver.setMusic(AudioPlayer.OFF);
    }

    private void mainMenuLabelMouseEntered() {
        unselectCurrentButton();
        currentButtonSelected = true;
        buttonController = MAIN_MENU;
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/mainMenuWhiteRoundedImage.png"))));
    }

    private void mainMenuLabelMouseExited() {
        currentButtonSelected = false;
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/mainMenuBlackRoundedImage.png"))));
    }

    private void mainMenuLabelMousePressed() {

        Main.audioPlayer.offCutMusic();
        mainMenuLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/mainMenuBlackRoundedImage.png"))));
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
        resetToDefaultLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/resetToDefaultRedSelectedRoundedImage.png"))));
    }

    private void resetToDefaultMouseExited() {
        currentButtonSelected = false;
        resetToDefaultLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/res/buttonImages/resetToDefaultRedUnselectedRoundedImage.png"))));
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
        leftEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        rightEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getRightKey()) + "]");
        useKeys[1] = optionsSaver.getRightKey();
        rightEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCwKey()) + "]");
        useKeys[2] = optionsSaver.getCwKey();
        CWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getCcwKey()) + "]");
        useKeys[3] = optionsSaver.getCcwKey();
        CCWRotationEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        downEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getDownKey()) + "]");
        useKeys[4] = optionsSaver.getDownKey();
        downEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        hardDropEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getHardDropKey()) + "]");
        useKeys[5] = optionsSaver.getHardDropKey();
        hardDropEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pauseEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getPauseKey()) + "]");
        useKeys[6] = optionsSaver.getPauseKey();
        pauseEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(optionsSaver.getExitMenuKey()) + "]");
        useKeys[7] = optionsSaver.getExitMenuKey();
        exitToMenuEventLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));


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
            File file = new File(System.getProperty("user.dir"), "options.dat");
            if (file.length() == 0) {
                optionsSaver = new OptionsSaver();
                saveOptions();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), "options.dat").getAbsolutePath()));
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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), "options.dat").getAbsolutePath()));
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

