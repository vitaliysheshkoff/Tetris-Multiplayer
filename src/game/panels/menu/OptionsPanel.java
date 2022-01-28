//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package game.panels.menu;

import game.helperclasses.backgroundpainting.PaintStaticLetters;
import game.helperclasses.buttons.MyButton;
/*import game.panels.menu.OptionsPanel.1;
import game.panels.menu.OptionsPanel.10;
import game.panels.menu.OptionsPanel.11;
import game.panels.menu.OptionsPanel.12;
import game.panels.menu.OptionsPanel.13;
import game.panels.menu.OptionsPanel.14;
import game.panels.menu.OptionsPanel.15;
import game.panels.menu.OptionsPanel.16;
import game.panels.menu.OptionsPanel.17;
import game.panels.menu.OptionsPanel.18;
import game.panels.menu.OptionsPanel.19;
import game.panels.menu.OptionsPanel.2;
import game.panels.menu.OptionsPanel.20;
import game.panels.menu.OptionsPanel.21;
import game.panels.menu.OptionsPanel.22;
import game.panels.menu.OptionsPanel.23;
import game.panels.menu.OptionsPanel.24;
import game.panels.menu.OptionsPanel.25;
import game.panels.menu.OptionsPanel.26;
import game.panels.menu.OptionsPanel.27;
import game.panels.menu.OptionsPanel.28;
import game.panels.menu.OptionsPanel.3;
import game.panels.menu.OptionsPanel.4;
import game.panels.menu.OptionsPanel.5;
import game.panels.menu.OptionsPanel.6;
import game.panels.menu.OptionsPanel.7;
import game.panels.menu.OptionsPanel.8;
import game.panels.menu.OptionsPanel.9;*/
import game.serial.OptionsSaver;
import game.start.Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsPanel extends JPanel implements ChangeListener, KeyListener {
    public JScrollPane scrollPane;
    public JPanel lowerPanel;
    private JComboBox backgroundBox;
    private JComboBox typeOfSquareBox;
    private static final int MAIN_MENU = 0;
    private static final int RESET = 1;
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
    private int buttonController = 0;
    private boolean currentButtonSelected = true;
    private int[] useKeys;
    private OptionsSaver optionsSaver;

    public OptionsPanel() {
        this.initComponents();
        this.setOptions();
        this.addKeyListener(this);
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, 3));
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
        this.sevenBagRandomLabel = new JLabel();
        this.plainRandomLabel = new JLabel();
        this.disappearingAnimationLabel = new JLabel();
        this.randomColorsAnimationLabel = new JLabel();
        this.leftEventLabel = new JLabel();
        this.rightEventLabel = new JLabel();
        this.CWRotationEventLabel = new JLabel();
        this.CCWRotationEventLabel = new JLabel();
        this.downEventLabel = new JLabel();
        this.hardDropEventLabel = new JLabel();
        this.pauseEventLabel = new JLabel();
        this.exitToMenuEventLabel = new JLabel();
        this.music1Label = new JLabel();
        this.music2Label = new JLabel();
        this.music3Label = new JLabel();
        this.offLabel = new JLabel();
        this.startLevelLabel = new JLabel();
        this.mainMenuButton = new MyButton("main menu");
        this.resetToDefaultButton = new MyButton("reset");
        this.startLevelSlider = new JSlider();
        this.musicVolumeSlider = new JSlider();
        this.soundsVolumeSlider = new JSlider();
        this.startLevelSlider.setForeground(Main.DARK_BLUE_COLOR);
        this.startLevelSlider.setBackground(Color.BLACK);
        this.soundsVolumeSlider.setForeground(Main.DARK_BLUE_COLOR);
        this.soundsVolumeSlider.setBackground(Color.BLACK);
        this.musicVolumeSlider.setForeground(Main.DARK_BLUE_COLOR);
        this.musicVolumeSlider.setBackground(Color.BLACK);
        this.startLevelSlider.setMaximum(21);
        this.startLevelSlider.setMinimum(1);
        this.startLevelSlider.addChangeListener(this);
        this.soundsVolumeSlider.addChangeListener(this);
        this.musicVolumeSlider.addChangeListener(this);
        this.useKeys = new int[8];
        this.gridCheckBox = new JCheckBox();
        this.shadowCheckBox = new JCheckBox();
        this.optionsSaver = new OptionsSaver();
        this.getOptions();
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
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
        staticDownLabel.setHorizontalAlignment(0);
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
        staticHardDropLabel.setHorizontalAlignment(0);
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
        this.sevenBagRandomLabel.setBackground(new Color(0, 0, 0, 100));
        this.sevenBagRandomLabel.setOpaque(true);
        this.sevenBagRandomLabel.setFont(Main.FONT);
        this.sevenBagRandomLabel.setForeground(Color.WHITE);
        this.sevenBagRandomLabel.setText("7-bag random");
       // this.sevenBagRandomLabel.addMouseListener(new 1(this));
        sevenBagRandomLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    Main.audioPlayer.playClick();
                    static7BagRandomMousePressed();
                }
            }
        });
        this.plainRandomLabel.setBackground(new Color(0, 0, 0, 100));
        this.plainRandomLabel.setOpaque(true);
        this.plainRandomLabel.setFont(Main.FONT);
        this.plainRandomLabel.setForeground(Color.WHITE);
        this.plainRandomLabel.setText("plain random");
      //  this.plainRandomLabel.addMouseListener(new 2(this));
        plainRandomLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
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
        this.disappearingAnimationLabel.setBackground(new Color(0, 0, 0, 100));
        this.disappearingAnimationLabel.setOpaque(true);
        this.disappearingAnimationLabel.setFont(Main.FONT);
        this.disappearingAnimationLabel.setForeground(Color.WHITE);
        this.disappearingAnimationLabel.setText("disappearing");
       // this.disappearingAnimationLabel.addMouseListener(new 3(this));
        disappearingAnimationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    Main.audioPlayer.playClick();
                    staticDisappearingAnimationLabelMousePressed();
                }
            }
        });
        this.randomColorsAnimationLabel.setBackground(new Color(0, 0, 0, 100));
        this.randomColorsAnimationLabel.setOpaque(true);
        this.randomColorsAnimationLabel.setFont(Main.FONT);
        this.randomColorsAnimationLabel.setForeground(Color.WHITE);
        this.randomColorsAnimationLabel.setText("random colors");
     //   this.randomColorsAnimationLabel.addMouseListener(new 4(this));
        randomColorsAnimationLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
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
        this.leftEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.leftEventLabel.setOpaque(true);
        this.leftEventLabel.setFont(Main.FONT);
        this.leftEventLabel.setForeground(Color.WHITE);
        this.leftEventLabel.setHorizontalAlignment(0);
       // this.leftEventLabel.addMouseListener(new 5(this));
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
     //   this.leftEventLabel.addKeyListener(new 6(this));
        this.rightEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.rightEventLabel.setOpaque(true);
        this.rightEventLabel.setFont(Main.FONT);
        this.rightEventLabel.setForeground(Color.WHITE);
        this.rightEventLabel.setHorizontalAlignment(0);
       // this.rightEventLabel.addMouseListener(new 7(this));
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
       // this.rightEventLabel.addKeyListener(new 8(this));
        this.CWRotationEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.CWRotationEventLabel.setOpaque(true);
        this.CWRotationEventLabel.setFont(Main.FONT);
        this.CWRotationEventLabel.setForeground(Color.WHITE);
        this.CWRotationEventLabel.setHorizontalAlignment(0);
       // this.CWRotationEventLabel.addMouseListener(new 9(this));
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
      //  this.CWRotationEventLabel.addKeyListener(new 10(this));
        this.CCWRotationEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.CCWRotationEventLabel.setOpaque(true);
        this.CCWRotationEventLabel.setFont(Main.FONT);
        this.CCWRotationEventLabel.setForeground(Color.WHITE);
        this.CCWRotationEventLabel.setHorizontalAlignment(0);
      //  this.CCWRotationEventLabel.addMouseListener(new 11(this));
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
      //  this.CCWRotationEventLabel.addKeyListener(new 12(this));
        this.downEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.downEventLabel.setOpaque(true);
        this.downEventLabel.setFont(Main.FONT);
        this.downEventLabel.setForeground(Color.WHITE);
        this.downEventLabel.setHorizontalAlignment(0);
        //this.downEventLabel.addMouseListener(new 13(this));
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
       // this.downEventLabel.addKeyListener(new 14(this));
        this.hardDropEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.hardDropEventLabel.setOpaque(true);
        this.hardDropEventLabel.setFont(Main.FONT);
        this.hardDropEventLabel.setForeground(Color.WHITE);
        this.hardDropEventLabel.setHorizontalAlignment(0);
      //  this.hardDropEventLabel.addMouseListener(new 15(this));
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

        //  this.hardDropEventLabel.addKeyListener(new 16(this));
        this.pauseEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.pauseEventLabel.setOpaque(true);
        this.pauseEventLabel.setFont(Main.FONT);
        this.pauseEventLabel.setForeground(Color.WHITE);
        this.pauseEventLabel.setHorizontalAlignment(0);
       // this.pauseEventLabel.addMouseListener(new 17(this));
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
      //  this.pauseEventLabel.addKeyListener(new 18(this));
        this.exitToMenuEventLabel.setBackground(new Color(0, 0, 0, 100));
        this.exitToMenuEventLabel.setOpaque(true);
        this.exitToMenuEventLabel.setFont(Main.FONT);
        this.exitToMenuEventLabel.setForeground(Color.WHITE);
        this.exitToMenuEventLabel.setHorizontalAlignment(0);
      //  this.exitToMenuEventLabel.addMouseListener(new 19(this));
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
      //  this.exitToMenuEventLabel.addKeyListener(new 20(this));
        this.music1Label.setBackground(new Color(0, 0, 0, 100));
        this.music1Label.setOpaque(true);
        this.music1Label.setFont(Main.FONT);
        this.music1Label.setForeground(Color.WHITE);
        this.music1Label.setText("music1");
        //this.music1Label.addMouseListener(new 21(this));
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
        this.music2Label.setBackground(new Color(0, 0, 0, 100));
        this.music2Label.setOpaque(true);
        this.music2Label.setFont(Main.FONT);
        this.music2Label.setForeground(Color.WHITE);
        this.music2Label.setText("music2");
       // this.music2Label.addMouseListener(new 22(this));
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
        this.music3Label.setBackground(new Color(0, 0, 0, 100));
        this.music3Label.setOpaque(true);
        this.music3Label.setFont(Main.FONT);
        this.music3Label.setForeground(Color.WHITE);
        this.music3Label.setText("music3");
       // this.music3Label.addMouseListener(new 23(this));
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
        this.offLabel.setBackground(new Color(0, 0, 0, 100));
        this.offLabel.setOpaque(true);
        this.offLabel.setFont(Main.FONT);
        this.offLabel.setForeground(Color.WHITE);
        this.offLabel.setText("OFF");
      //  this.offLabel.addMouseListener(new 24(this));
        offLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Main.audioPlayer.playClick();
                    Main.audioPlayer.offCutMusic();
                    offLabelMousePressed();
                }
            }
        });
        this.startLevelLabel.setBackground(new Color(0, 0, 0, 100));
        this.startLevelLabel.setOpaque(true);
        this.startLevelLabel.setFont(Main.FONT);
        this.startLevelLabel.setForeground(Color.WHITE);
        this.startLevelLabel.setHorizontalAlignment(0);
     //   this.mainMenuButton.addMouseListener(new 25(this));
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
     //   this.resetToDefaultButton.addMouseListener(new 26(this));
        this.shadowCheckBox.setBackground(new Color(0, 0, 0, 100));
        this.shadowCheckBox.setFont(Main.FONT);
        this.shadowCheckBox.setForeground(Color.WHITE);
        this.shadowCheckBox.setText("shadow");
        this.shadowCheckBox.setOpaque(false);
       // this.shadowCheckBox.addMouseListener(new 27(this));
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
        this.gridCheckBox.setBackground(new Color(0, 0, 0, 100));
        this.gridCheckBox.setFont(Main.FONT);
        this.gridCheckBox.setForeground(Color.WHITE);
        this.gridCheckBox.setText("grid");
        this.gridCheckBox.setOpaque(false);
      //  this.gridCheckBox.addMouseListener(new 28(this));
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
        this.lowerPanel = new JPanel();
        this.typeOfSquareBox = new JComboBox();
        this.backgroundBox = new JComboBox();
        game.panels.menu.OptionsPanel.TitlePanel titlePanel = new game.panels.menu.OptionsPanel.TitlePanel();
        game.panels.menu.OptionsPanel.BackgroundPanel backgroundPanel = new game.panels.menu.OptionsPanel.BackgroundPanel();
        this.lowerPanel.setOpaque(false);
        this.backgroundBox.setBackground(new Color(0, 0, 0, 100));
        this.backgroundBox.setForeground(Color.WHITE);
        this.backgroundBox.setFocusable(false);
        this.typeOfSquareBox.setBackground(new Color(0, 0, 0, 100));
        this.typeOfSquareBox.setForeground(Color.WHITE);
        this.typeOfSquareBox.setFocusable(false);
        this.typeOfSquareBox.addItem("Plain");
        this.typeOfSquareBox.addItem("little square");
        this.typeOfSquareBox.addItem("Round");
        this.typeOfSquareBox.addItem("Gradient");
        this.typeOfSquareBox.addItem("Lego");

        for(int i = 1; i <= 5; ++i) {
            this.backgroundBox.addItem("background " + i);
        }

        this.typeOfSquareBox.addActionListener((e) -> {
            if (this.typeOfSquareBox.getSelectedIndex() == 0) {
                this.typeOfSquare1Pressed();
                this.repaint();
            } else if (this.typeOfSquareBox.getSelectedIndex() == 1) {
                this.typeOfSquare2Pressed();
                this.repaint();
            } else if (this.typeOfSquareBox.getSelectedIndex() == 2) {
                this.typeOfSquare3Pressed();
                this.repaint();
            } else if (this.typeOfSquareBox.getSelectedIndex() == 3) {
                this.typeOfSquare4Pressed();
                this.repaint();
            } else {
                this.typeOfSquare5Pressed();
                this.repaint();
            }

        });
        this.backgroundBox.addActionListener((e) -> {
            if (this.backgroundBox.getSelectedIndex() == 0) {
                this.backgroundImage1MousePressed();
                this.repaint();
            } else if (this.backgroundBox.getSelectedIndex() == 1) {
                this.backgroundImage2MousePressed();
                this.repaint();
            } else if (this.backgroundBox.getSelectedIndex() == 2) {
                this.backgroundImage3MousePressed();
                this.repaint();
            } else if (this.backgroundBox.getSelectedIndex() == 3) {
                this.backgroundImage4MousePressed();
                this.repaint();
            } else {
                this.backgroundImage5MousePressed();
                this.repaint();
            }

        });
        GroupLayout jPanel1Layout = new GroupLayout(this.lowerPanel);
        this.lowerPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(staticMusicVolumeLabel).addComponent(staticSoundsVolumeLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.soundsVolumeSlider, -1, -1, 32767).addComponent(this.musicVolumeSlider, -1, -1, 32767))).addComponent(staticLineClearAnimationLabel).addComponent(staticRandomizerLabel).addComponent(staticMusicAndSoundsLabel).addGroup(jPanel1Layout.createSequentialGroup().addComponent(staticMusicLabel, -2, -2, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.music1Label).addGap(18, 18, 18).addComponent(this.music2Label).addGap(18, 18, 18).addComponent(this.music3Label).addGap(18, 18, 18).addComponent(this.offLabel))).addContainerGap(-1, 32767)).addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.randomColorsAnimationLabel).addGap(50, 50, 50).addComponent(this.disappearingAnimationLabel)).addComponent(staticAppearanceLabel).addGroup(jPanel1Layout.createSequentialGroup().addComponent(staticBackgroundImageLabel).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.backgroundBox, 0, -1, 32767))).addGap(0, 50, 100).addComponent(this.typeOfSquareBox, 0, -1, 32767).addContainerGap(292, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(staticKeyboardLabel, -1, -1, -1).addComponent(staticRightLabel).addComponent(staticDownLabel).addComponent(staticHardDropLabel, -1, -1, 32767).addComponent(staticLeftLabel)).addPreferredGap(ComponentPlacement.RELATED, 27, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.rightEventLabel, Alignment.LEADING, -1, 148, 32767).addComponent(this.downEventLabel, Alignment.LEADING, -1, -1, 32767).addComponent(this.hardDropEventLabel, Alignment.LEADING, -1, -1, 32767).addComponent(this.leftEventLabel, Alignment.LEADING, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED, 28, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(staticCWRotationLabel).addComponent(staticCCWRotationLabel).addComponent(staticExitToMenu).addComponent(staticPauseLabel).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.shadowCheckBox).addComponent(staticShadowLevelLabel)))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addComponent(staticStartLevelLabel)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.startLevelSlider, -2, 200, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.startLevelLabel, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.plainRandomLabel).addGap(50, 50, 50).addComponent(this.sevenBagRandomLabel))).addGap(0, 0, 32767))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.gridCheckBox).addComponent(staticGridLevelLabel).addComponent(this.CCWRotationEventLabel, -1, 148, 32767).addComponent(this.exitToMenuEventLabel, -1, -1, 32767).addComponent(this.pauseEventLabel, -1, -1, 32767).addComponent(this.CWRotationEventLabel, -1, -1, 32767)).addContainerGap(-1, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.mainMenuButton, -2, 234, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.resetToDefaultButton, -2, 221, -2).addContainerGap()))));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticStartLevelLabel).addComponent(staticShadowLevelLabel)).addComponent(staticGridLevelLabel, Alignment.TRAILING)).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.shadowCheckBox).addComponent(this.gridCheckBox))).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.startLevelLabel, -2, 0, 32767).addComponent(this.startLevelSlider, -1, -1, 32767)))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(staticKeyboardLabel, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.leftEventLabel, -2, 34, -2).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticLeftLabel).addComponent(staticCWRotationLabel).addComponent(this.CWRotationEventLabel, -2, 34, -2))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticRightLabel, -2, -1, -2).addComponent(this.rightEventLabel, -2, 34, -2).addComponent(staticCCWRotationLabel).addComponent(this.CCWRotationEventLabel, -2, 34, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticDownLabel).addComponent(this.downEventLabel, -2, 34, -2).addComponent(staticExitToMenu).addComponent(this.exitToMenuEventLabel, -2, 34, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticHardDropLabel).addComponent(this.hardDropEventLabel, -2, 34, -2).addComponent(staticPauseLabel).addComponent(this.pauseEventLabel, -2, 34, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(staticRandomizerLabel).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.plainRandomLabel).addComponent(this.sevenBagRandomLabel)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(staticMusicAndSoundsLabel).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticMusicLabel).addComponent(this.music1Label).addComponent(this.music3Label).addComponent(this.offLabel).addComponent(this.music2Label)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(staticMusicVolumeLabel, Alignment.TRAILING, -2, 34, -2).addComponent(this.musicVolumeSlider, Alignment.TRAILING, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.soundsVolumeSlider, Alignment.TRAILING, -2, -1, -2).addComponent(staticSoundsVolumeLabel, Alignment.TRAILING, -2, 34, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(staticAppearanceLabel).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(staticBackgroundImageLabel).addComponent(this.typeOfSquareBox, -2, 26, -2).addComponent(this.backgroundBox, -2, 26, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(staticLineClearAnimationLabel).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.randomColorsAnimationLabel).addComponent(this.disappearingAnimationLabel)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.mainMenuButton, -2, 44, -2).addComponent(this.resetToDefaultButton)).addContainerGap()));
        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(titlePanel, -1, -1, 32767).addComponent(this.lowerPanel, -1, -1, 32767));
        backgroundPanelLayout.setVerticalGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(backgroundPanelLayout.createSequentialGroup().addGap(41, 41, 41).addComponent(titlePanel, -2, -1, -2).addGap(41, 41, 41).addComponent(this.lowerPanel, -1, 740, 32767)));
        this.add(backgroundPanel);
    }

    private void typeOfSquare5Pressed() {
        this.typeOfSquareBox.setSelectedIndex(4);
        Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare = 4;
        this.optionsSaver.setTypeOfSquare((byte)4);
    }

    private void typeOfSquare4Pressed() {
        this.typeOfSquareBox.setSelectedIndex(3);
        Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare = 3;
        this.optionsSaver.setTypeOfSquare((byte)3);
    }

    private void typeOfSquare3Pressed() {
        this.typeOfSquareBox.setSelectedIndex(2);
        Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare = 2;
        this.optionsSaver.setTypeOfSquare((byte)2);
    }

    private void typeOfSquare2Pressed() {
        this.typeOfSquareBox.setSelectedIndex(1);
        Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare = 1;
        this.optionsSaver.setTypeOfSquare((byte)1);
    }

    private void typeOfSquare1Pressed() {
        this.typeOfSquareBox.setSelectedIndex(0);
        Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare = 0;
        this.optionsSaver.setTypeOfSquare((byte)0);
    }

    private void static7BagRandomMouseEntered() {
    }

    private void static7BagRandomMouseExited() {
    }

    private void static7BagRandomMousePressed() {
        this.sevenBagRandomLabel.setBorder(BorderFactory.createLineBorder(Main.PINK_COLOR, 3));
        this.plainRandomLabel.setBorder((Border)null);
        this.optionsSaver.setRandomType((byte)2);
    }

    private void staticPlainRandomMousePressed() {
        this.sevenBagRandomLabel.setBorder((Border)null);
        this.plainRandomLabel.setBorder(BorderFactory.createLineBorder(Main.PINK_COLOR, 3));
        this.optionsSaver.setRandomType((byte)1);
    }

    private void leftEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.leftEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setLeftKey(evt.getKeyCode());
            this.useKeys[0] = evt.getKeyCode();
        }

    }

    private void leftEventLabelMousePressed() {
        this.leftEventLabel.setFocusable(true);
        this.leftEventLabel.requestFocusInWindow();
        this.leftEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.leftEventLabel.setText("[choose key]");
    }

    private void leftEventLabelMouseReleased() {
    }

    private void leftEventLabelKeyReleased() {
        this.leftEventLabel.setBorder((Border)null);
        this.leftEventLabel.setFocusable(false);
    }

    private void rightEventLabelMousePressed() {
        this.rightEventLabel.setFocusable(true);
        this.rightEventLabel.requestFocusInWindow();
        this.rightEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.rightEventLabel.setText("[choose key]");
    }

    private void rightEventLabelMouseReleased() {
    }

    private void rightEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.rightEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setRightKey(evt.getKeyCode());
            this.useKeys[1] = evt.getKeyCode();
        }

    }

    private void rightEventLabelKeyReleased() {
        this.rightEventLabel.setBorder((Border)null);
        this.rightEventLabel.setFocusable(false);
    }

    private void CWRotationEventLabelMousePressed() {
        this.CWRotationEventLabel.setFocusable(true);
        this.CWRotationEventLabel.requestFocusInWindow();
        this.CWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.CWRotationEventLabel.setText("[choose key]");
    }

    private void CWRotationEventLabelMouseReleased() {
    }

    private void CWRotationEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setCwKey(evt.getKeyCode());
            this.useKeys[2] = evt.getKeyCode();
        }

    }

    private void CWRotationEventLabelKeyReleased() {
        this.CWRotationEventLabel.setBorder((Border)null);
        this.CWRotationEventLabel.setFocusable(false);
    }

    private void CCWRotationEventLabelMousePressed() {
        this.CCWRotationEventLabel.setFocusable(true);
        this.CCWRotationEventLabel.requestFocusInWindow();
        this.CCWRotationEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.CCWRotationEventLabel.setText("[choose key]");
    }

    private void CCWRotationEventLabelMouseReleased() {
    }

    private void CCWRotationEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setCcwKey(evt.getKeyCode());
            this.useKeys[3] = evt.getKeyCode();
        }

    }

    private void CCWRotationEventLabelKeyReleased() {
        this.CCWRotationEventLabel.setBorder((Border)null);
        this.CCWRotationEventLabel.setFocusable(false);
    }

    private void downEventLabelMousePressed() {
        this.downEventLabel.setFocusable(true);
        this.downEventLabel.requestFocusInWindow();
        this.downEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.downEventLabel.setText("[choose key]");
    }

    private void downEventLabelMouseReleased() {
    }

    private void downEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.downEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setDownKey(evt.getKeyCode());
            this.useKeys[4] = evt.getKeyCode();
        }

    }

    private void downEventLabelKeyReleased() {
        this.downEventLabel.setBorder((Border)null);
        this.downEventLabel.setFocusable(false);
    }

    private void hardDropEventLabelMousePressed() {
        this.hardDropEventLabel.setFocusable(true);
        this.hardDropEventLabel.requestFocusInWindow();
        this.hardDropEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.hardDropEventLabel.setText("[choose key]");
    }

    private void hardDropEventLabelMouseReleased() {
    }

    private void hardDropEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.hardDropEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setHardDropKey(evt.getKeyCode());
            this.useKeys[5] = evt.getKeyCode();
        }

    }

    private void hardDropEventLabelKeyReleased() {
        this.hardDropEventLabel.setBorder((Border)null);
        this.hardDropEventLabel.setFocusable(false);
    }

    private void pauseEventLabelMousePressed() {
        this.pauseEventLabel.setFocusable(true);
        this.pauseEventLabel.requestFocusInWindow();
        this.pauseEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.pauseEventLabel.setText("[choose key]");
    }

    private void pauseEventLabelMouseReleased() {
    }

    private void pauseEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.pauseEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setPauseKey(evt.getKeyCode());
            this.useKeys[6] = evt.getKeyCode();
        }

    }

    private void pauseEventLabelKeyReleased() {
        this.pauseEventLabel.setBorder((Border)null);
        this.pauseEventLabel.setFocusable(false);
    }

    private void exitToMenuEventLabelMousePressed() {
        this.exitToMenuEventLabel.setFocusable(true);
        this.exitToMenuEventLabel.requestFocusInWindow();
        this.exitToMenuEventLabel.setBorder(BorderFactory.createLineBorder(Main.GREEN_COLOR, 3));
        this.exitToMenuEventLabel.setText("[choose key]");
    }

    private void exitToMenuEventLabelMouseReleased() {
    }

    private void exitToMenuEventLabelKeyPressed(KeyEvent evt) {
        if (IntStream.of(this.useKeys).noneMatch((x) -> {
            return x == evt.getKeyCode();
        })) {
            this.exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(evt.getKeyCode()) + "]");
            this.optionsSaver.setExitMenuKey(evt.getKeyCode());
            this.useKeys[7] = evt.getKeyCode();
            Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.exitMenuKey = evt.getKeyCode();
        }

    }

    private void exitToMenuEventLabelKeyReleased() {
        this.exitToMenuEventLabel.setBorder((Border)null);
        this.exitToMenuEventLabel.setFocusable(false);
    }

    private void backgroundImage1MousePressed() {
        this.backgroundBox.setSelectedIndex(0);
        Main.tetrisPanel.backgroundType = 0;
        this.optionsSaver.setBackgroundType((byte)0);
    }

    private void backgroundImage2MousePressed() {
        this.backgroundBox.setSelectedIndex(1);
        Main.tetrisPanel.backgroundType = 1;
        this.optionsSaver.setBackgroundType((byte)1);
    }

    private void backgroundImage3MousePressed() {
        this.backgroundBox.setSelectedIndex(2);
        Main.tetrisPanel.backgroundType = 2;
        this.optionsSaver.setBackgroundType((byte)2);
    }

    private void backgroundImage4MousePressed() {
        this.backgroundBox.setSelectedIndex(3);
        Main.tetrisPanel.backgroundType = 3;
        this.optionsSaver.setBackgroundType((byte)3);
    }

    private void backgroundImage5MousePressed() {
        this.backgroundBox.setSelectedIndex(4);
        Main.tetrisPanel.backgroundType = 4;
        this.optionsSaver.setBackgroundType((byte)4);
    }

    private void staticDisappearingAnimationLabelMousePressed() {
        this.randomColorsAnimationLabel.setBorder((Border)null);
        this.disappearingAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        this.optionsSaver.setLineClearAnimation((byte)0);
    }

    private void staticRandomColorAnimationLabelMousePressed() {
        this.disappearingAnimationLabel.setBorder((Border)null);
        this.randomColorsAnimationLabel.setBorder(BorderFactory.createLineBorder(Main.ORANGE_COLOR, 3));
        this.optionsSaver.setLineClearAnimation((byte)1);
    }

    private void music1LabelMousePressed() {
        this.music1Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        this.music2Label.setBorder((Border)null);
        this.music3Label.setBorder((Border)null);
        this.offLabel.setBorder((Border)null);
        this.musicVolumeSlider.setVisible(true);
        this.optionsSaver.setMusic((byte)0);
    }

    private void music2LabelMousePressed() {
        this.music2Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        this.music1Label.setBorder((Border)null);
        this.music3Label.setBorder((Border)null);
        this.offLabel.setBorder((Border)null);
        this.musicVolumeSlider.setVisible(true);
        this.optionsSaver.setMusic((byte)1);
    }

    private void music3LabelMousePressed() {
        this.music3Label.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        this.music2Label.setBorder((Border)null);
        this.music1Label.setBorder((Border)null);
        this.offLabel.setBorder((Border)null);
        this.musicVolumeSlider.setVisible(true);
        this.optionsSaver.setMusic((byte)2);
    }

    private void offLabelMousePressed() {
        this.offLabel.setBorder(BorderFactory.createLineBorder(Main.BLUE_COLOR, 3));
        this.music3Label.setBorder((Border)null);
        this.music2Label.setBorder((Border)null);
        this.music1Label.setBorder((Border)null);
        this.musicVolumeSlider.setVisible(false);
        this.musicVolumeSlider.setValue(15);
        this.optionsSaver.setMusic((byte)3);
    }

    private void mainMenuLabelMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 0;
        this.mainMenuButton.selectButton();
    }

    private void mainMenuLabelMouseExited() {
        this.currentButtonSelected = false;
        this.mainMenuButton.unselectButton();
    }

    private void mainMenuLabelMousePressed() {
        Main.audioPlayer.offCutMusic();
        this.setOptions();
        this.saveOptions();
        Main.tetrisFrame.remove(Main.optionPanel.scrollPane);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.menuPanel.requestFocusInWindow();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
    }

    private void resetToDefaultMouseEntered() {
        this.unselectCurrentButton();
        this.currentButtonSelected = true;
        this.buttonController = 1;
        this.resetToDefaultButton.selectButton();
    }

    private void resetToDefaultMouseExited() {
        this.currentButtonSelected = false;
        this.resetToDefaultButton.unselectButton();
    }

    private void resetToDefaultLabelMousePressed() {
        Main.audioPlayer.offCutMusic();
        this.resetToDefaultMouseExited();
        this.setDefaultOptions();
        this.repaint();
    }

    private void shadowCheckBoxMousePressed() {
        this.optionsSaver.setShadow(!this.shadowCheckBox.isSelected());
    }

    private void gridCheckBoxMousePressed() {
        this.optionsSaver.setGrid(!this.gridCheckBox.isSelected());
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(this.startLevelSlider)) {
            this.startLevelLabel.setText("" + this.startLevelSlider.getValue());
            this.optionsSaver.setStartLevel((byte)this.startLevelSlider.getValue());
        } else if (e.getSource().equals(this.musicVolumeSlider)) {
            if (this.musicVolumeSlider.getValue() == 0) {
                this.offLabelMousePressed();
                Main.audioPlayer.offCutMusic();
            } else {
                Main.audioPlayer.musicVolume = (double)this.musicVolumeSlider.getValue() / 100.0D;
                Main.audioPlayer.updateCutMusicVolume();
                this.optionsSaver.setMusicVolume(this.musicVolumeSlider.getValue());
            }
        } else if (e.getSource().equals(this.soundsVolumeSlider)) {
            Main.audioPlayer.soundsVolume = (double)this.soundsVolumeSlider.getValue() / 100.0D;
            this.optionsSaver.setSoundsVolume(this.soundsVolumeSlider.getValue());
        }

        this.requestFocusInWindow();
    }

    public void setOptions() {
        this.leftEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getLeftKey()) + "]");
        this.useKeys[0] = this.optionsSaver.getLeftKey();
        this.leftEventLabel.setBorder((Border)null);
        this.rightEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getRightKey()) + "]");
        this.useKeys[1] = this.optionsSaver.getRightKey();
        this.rightEventLabel.setBorder((Border)null);
        this.CWRotationEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getCwKey()) + "]");
        this.useKeys[2] = this.optionsSaver.getCwKey();
        this.CWRotationEventLabel.setBorder((Border)null);
        this.CCWRotationEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getCcwKey()) + "]");
        this.useKeys[3] = this.optionsSaver.getCcwKey();
        this.CCWRotationEventLabel.setBorder((Border)null);
        this.downEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getDownKey()) + "]");
        this.useKeys[4] = this.optionsSaver.getDownKey();
        this.downEventLabel.setBorder((Border)null);
        this.hardDropEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getHardDropKey()) + "]");
        this.useKeys[5] = this.optionsSaver.getHardDropKey();
        this.hardDropEventLabel.setBorder((Border)null);
        this.pauseEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getPauseKey()) + "]");
        this.useKeys[6] = this.optionsSaver.getPauseKey();
        this.pauseEventLabel.setBorder((Border)null);
        this.exitToMenuEventLabel.setText("[" + KeyEvent.getKeyText(this.optionsSaver.getExitMenuKey()) + "]");
        this.useKeys[7] = this.optionsSaver.getExitMenuKey();
        this.exitToMenuEventLabel.setBorder((Border)null);
        this.startLevelSlider.setValue(this.optionsSaver.getStartLevel());
        this.startLevelLabel.setText("" + this.startLevelSlider.getValue());
        this.musicVolumeSlider.setValue(this.optionsSaver.getMusicVolume());
        this.soundsVolumeSlider.setValue(this.optionsSaver.getSoundsVolume());
        if (this.optionsSaver.getTypeOfSquare() == 0) {
            this.typeOfSquare1Pressed();
        } else if (this.optionsSaver.getTypeOfSquare() == 1) {
            this.typeOfSquare2Pressed();
        } else if (this.optionsSaver.getTypeOfSquare() == 2) {
            this.typeOfSquare3Pressed();
        } else if (this.optionsSaver.getTypeOfSquare() == 3) {
            this.typeOfSquare4Pressed();
        } else if (this.optionsSaver.getTypeOfSquare() == 4) {
            this.typeOfSquare5Pressed();
        }

        if (this.optionsSaver.getBackgroundType() == 0) {
            this.backgroundImage1MousePressed();
        } else if (this.optionsSaver.getBackgroundType() == 1) {
            this.backgroundImage2MousePressed();
        } else if (this.optionsSaver.getBackgroundType() == 2) {
            this.backgroundImage3MousePressed();
        } else if (this.optionsSaver.getBackgroundType() == 3) {
            this.backgroundImage4MousePressed();
        } else if (this.optionsSaver.getBackgroundType() == 4) {
            this.backgroundImage5MousePressed();
        }

        if (this.optionsSaver.getRandomType() == 1) {
            this.staticPlainRandomMousePressed();
        } else if (this.optionsSaver.getRandomType() == 2) {
            this.static7BagRandomMousePressed();
        }

        if (this.optionsSaver.getLineClearAnimation() == 0) {
            this.staticDisappearingAnimationLabelMousePressed();
        } else if (this.optionsSaver.getLineClearAnimation() == 1) {
            this.staticRandomColorAnimationLabelMousePressed();
        }

        if (this.optionsSaver.getMusic() == 0) {
            this.music1LabelMousePressed();
        } else if (this.optionsSaver.getMusic() == 1) {
            this.music2LabelMousePressed();
        } else if (this.optionsSaver.getMusic() == 2) {
            this.music3LabelMousePressed();
        } else if (this.optionsSaver.getMusic() == 3) {
            this.offLabelMousePressed();
        }

        this.shadowCheckBox.setSelected(this.optionsSaver.getShadow());
        this.gridCheckBox.setSelected(this.optionsSaver.getGrid());
    }

    public void getOptions() {
        this.deserializeOptions();
    }

    public void saveOptions() {
        this.serialize();
    }

    public void setDefaultOptions() {
        this.resetOptions();
        this.setOptions();
    }

    public void resetOptions() {
        this.optionsSaver = new OptionsSaver();
    }

    private void deserializeOptions() {
        try {
            File file = new File(System.getProperty("user.dir"), "options.dat");
            if (file.length() == 0L) {
                this.optionsSaver = new OptionsSaver();
                this.saveOptions();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
                this.optionsSaver = (OptionsSaver)ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException | IOException var3) {
            var3.printStackTrace();
        }

        assert this.optionsSaver != null;

    }

    private void serialize() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            oos.writeObject(this.optionsSaver);
            oos.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            this.pressRightKey();
        } else if (e.getKeyCode() == 37) {
            this.pressLeftKey();
        } else if (e.getKeyCode() == 10) {
            this.pressEnterKey();
        } else if (e.getKeyCode() == Main.tetrisPanel.tetrisPlayFieldPanel.keyHandler.exitMenuKey) {
            this.pressExitKey();
        }

    }

    private void pressExitKey() {
        Main.audioPlayer.playClick();
        this.mainMenuLabelMousePressed();
    }

    private void pressEnterKey() {
        if (this.currentButtonSelected) {
            Main.audioPlayer.playClick();
            if (this.buttonController == 0) {
                this.mainMenuLabelMousePressed();
            } else {
                this.resetToDefaultLabelMousePressed();
            }
        }

    }

    private void pressLeftKey() {
        if (this.buttonController == 1 || !this.currentButtonSelected) {
            Main.audioPlayer.playClick();
            System.out.println("Left");
            this.unselectCurrentButton();
            this.buttonController = 0;
            this.selectCurrentButton();
        }

    }

    private void pressRightKey() {
        if (this.buttonController == 0 || !this.currentButtonSelected) {
            Main.audioPlayer.playClick();
            System.out.println("Right");
            this.unselectCurrentButton();
            this.buttonController = 1;
            this.selectCurrentButton();
        }

    }

    public void selectCurrentButton() {
        if (this.buttonController == 0) {
            this.mainMenuLabelMouseEntered();
        } else {
            this.resetToDefaultMouseEntered();
        }

    }

    private void unselectCurrentButton() {
        if (this.buttonController == 0) {
            this.mainMenuLabelMouseExited();
        } else {
            this.resetToDefaultMouseExited();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    class BackgroundPanel extends JPanel {
        BufferedImage bufferedImage = null;

        public BackgroundPanel() {
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
                this.bufferedImage = Main.tetrisPanel.backgroundImage;
            } else if (Main.tetrisPanel.backgroundType == 1) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage2;
            } else if (Main.tetrisPanel.backgroundType == 2) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage3;
            } else if (Main.tetrisPanel.backgroundType == 3) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage4;
            } else if (Main.tetrisPanel.backgroundType == 4) {
                this.bufferedImage = Main.tetrisPanel.backgroundImage5;
            }

            for(int i = 0; (double)i < Main.monitorHeight / (double)this.bufferedImage.getHeight() + 1.0D; ++i) {
                for(int j = 0; (double)j < Main.monitorWidth / (double)this.bufferedImage.getWidth() + 1.0D; ++j) {
                    g.drawImage(this.bufferedImage, j * this.bufferedImage.getWidth(), i * this.bufferedImage.getHeight(), this);
                }
            }

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
            this.c = Main.optionPanel.scrollPane.getParent();
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

        private void paintOptionTitle(Graphics2D g2d, int startX, int startY, int square_radius, byte type) {
            int space = square_radius / 28;
            PaintStaticLetters.paintLetterO(g2d, startX, startY, this.radius, type);
            PaintStaticLetters.paintLetterP(g2d, startX + 3 * this.radius + space, startY, this.radius, type);
            PaintStaticLetters.paintLetterT(g2d, startX + 7 * this.radius + 2 * space, startY, this.radius, type);
            PaintStaticLetters.paintLetterI(g2d, startX + 12 * this.radius + 3 * space, startY, this.radius, type);
            PaintStaticLetters.paintLetterO(g2d, startX + 15 * this.radius + 4 * space, startY, this.radius, type);
            PaintStaticLetters.paintLetterN(g2d, startX + 18 * this.radius + 5 * space, startY, this.radius, type);
            PaintStaticLetters.paintLetterS(g2d, startX + 23 * this.radius + 6 * space, startY, this.radius, type);
        }

        protected void paintComponent(Graphics g) {
            this.radius = Math.min(this.getWidth() / 29, this.getHeight() / 6);
            this.startX = (this.getWidth() - this.radius * 29) / 2;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.paintOptionTitle(g2d, this.startX, this.radius, this.radius, Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare);
        }
    }
}
