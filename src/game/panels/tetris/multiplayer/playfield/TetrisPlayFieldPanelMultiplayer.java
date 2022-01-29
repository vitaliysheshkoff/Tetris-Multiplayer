package game.panels.tetris.multiplayer.playfield;

import game.dialogs.ScoreDialog;
import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.Moving;
import game.panels.tetris.controller.Painting;
import game.panels.tetris.controller.Randomizer;
import game.panels.tetris.controller.Rotation;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.tetris.multiplayer.sockets.client.Client;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
import game.panels.tetris.multiplayer.sockets.server.Server;
import game.panels.tetris.multiplayer.web.Client1;
import game.panels.tetris.multiplayer.web.Client2;
import game.serial.OptionsSaver;
import game.start.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Desktop.Action;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TetrisPlayFieldPanelMultiplayer extends JPanel implements Runnable, KeyListener {
    public static final int[] MILLI_SPEED = new int[]{798, 715, 632, 549, 465, 382, 299, 216, 133, 99, 83, 66, 49, 33, 16};
    public static final int[] NANO_SPEED = new int[]{684832, 488496, 292159, 95822, 899486, 703149, 506812, 310475, 114139, 835604, 196337, 557069, 917802, 278535, 639268};
    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0;
    public static final byte RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1;
    public static final byte NEW_STYLE_RANDOM = 2;
    public static final byte DEFAULT = 0;
    public volatile boolean blockMainMenuButton = false;
    static Color transparentColor = new Color(0, 0, 0, 100);
    static Color transparentColor2 = new Color(0, 0, 0, 2);
    public boolean suspendFlag;
    public boolean interruptFlag;
    public volatile boolean gameOver = true;
    public boolean grid;
    public boolean paintShadow;
    public boolean clearAnimation;
    public volatile byte[][] fieldMatrix;
    public byte[] usedTetrominoes;
    public int randomType;
    public int amountOfDeletingLinesBetweenTetrominoes;
    public int amountOfDeletingLinesBetweenLevels;
    public int level;
    public volatile long score;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte clearLinesAnimationType;
    public byte helperForDeleting;
    public byte music;
    public int cwKey = 68;
    public int ccwKey = 65;
    public int rightKey = 39;
    public int leftKey = 37;
    public int downKey = 40;
    public int hardDropKey = 32;
    public int pauseKey = 10;
    public int exitMenuKey = 27;
    public volatile Tetromino currentTetromino;
    public volatile ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;
    public Thread thread;
    public Server server;
    public Client clientA;
    public SendingObject sendingObject;
    public SendingObject receivingObject;
    public boolean thisAppServer = true;
    byte[] tetrominoesStackByte = null;
    Stack tetrominoesStack;
    volatile byte nextTetromino;
    Thread threadManager;
    Thread webThreadManager;
    volatile long opponentScore;
    volatile boolean waiting = true;
    byte typeOfSquare = 0;
    Client1 client1 = null;
    Client2 client2 = null;


    public boolean telegram;
    public static final AtomicBoolean isConnected = new AtomicBoolean(false);
    double radius = 1.0D;
    Color color = new Color(0, 0, 0, 100);
    Dimension d;
    Container c;

    public TetrisPlayFieldPanelMultiplayer() {
        this.setOpaque(false);
        this.setForeground(transparentColor);
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        this.indexesOfDeletingLines = new ArrayList();
        this.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {
    }

    public synchronized void keyPressed(KeyEvent e) {
        if (!this.gameOver && !this.clearAnimation) {
            if (e.getKeyCode() == this.ccwKey) {
                Rotation.pressCCWKey(this.fieldMatrix, this.currentTetromino);
                this.repaint();
            } else if (e.getKeyCode() == this.cwKey) {
                Rotation.pressCWKey(this.fieldMatrix, this.currentTetromino);
                this.repaint();
            } else if (e.getKeyCode() == this.leftKey) {
                Moving.pressLeftKey(this.currentTetromino, this.fieldMatrix);
                this.repaint();
            } else if (e.getKeyCode() == this.rightKey) {
                Moving.pressRightKey(this.currentTetromino, this.fieldMatrix);
                this.repaint();
            } else if (e.getKeyCode() == this.downKey) {
                ++this.currentTetromino.stepY;

                int i;
                for(i = 0; i < 4; ++i) {
                    ++this.currentTetromino.coordinates[i].y;
                }

                if (Moving.isTetrominoConnected(this.currentTetromino.coordinates, this.fieldMatrix)) {
                    --this.currentTetromino.stepY;

                    for(i = 0; i < 4; ++i) {
                        --this.currentTetromino.coordinates[i].y;
                    }

                    this.repaint();
                    this.lastMove();
                    this.wakeUpThreadFromSleeping();
                } else {
                    --this.currentTetromino.stepY;

                    for(i = 0; i < 4; ++i) {
                        --this.currentTetromino.coordinates[i].y;
                    }

                    this.extraScore += Moving.pressDownKey(this.currentTetromino, this.fieldMatrix);
                    this.repaint();
                }
            } else if (e.getKeyCode() == this.hardDropKey) {
                this.extraScore += Moving.pressHardDropKey(this.fieldMatrix, this.currentTetromino);
                this.repaint();
                this.lastMove();
                this.wakeUpThreadFromSleeping();
            }
        }

        if (e.getKeyCode() == this.exitMenuKey && !this.blockMainMenuButton) {
            this.goMenuPanel();
        }

    }

    private void lastMove() {
        Main.audioPlayer.playHardDrop();

        for(int j = 0; j < 4; ++j) {
            this.elementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(this.currentTetromino.coordinates[j].x, this.currentTetromino.coordinates[j].y), this.currentTetromino.tetrominoType));
            if (this.currentTetromino.coordinates[j].y > -2) {
                this.fieldMatrix[this.currentTetromino.coordinates[j].y + 1][this.currentTetromino.coordinates[j].x + 1] = 1;
            }
        }

        this.checkGameOver();
        this.updateCurrentTetromino();
        if (this.extraScore > 0) {
            this.score += (long)this.extraScore;
        }

        this.extraScore = 0;
    }

    public void goMenuPanel() {
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
        Main.audioPlayer.playClick();
        if (this.client1 != null) {
            this.client1.getSocket().close();
        }

        if (this.client2 != null) {
            this.client2.getSocket().close();
        }

        this.gameOver = true;
        if (Main.multiplayerPanel2.typeOfGame == 3) {
            this.webThreadManager.interrupt();
        } else if (this.thisAppServer) {
            if (this.threadManager.isAlive()) {
                this.threadManager.interrupt();
            }

            if (this.server != null) {
                this.server.serverSocket.close();
            }
        } else if (this.clientA != null) {
            this.clientA.clientSocket.close();
        }

        this.blockMainMenuButton = false;
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = false;
        if (Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.thread != null) {
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.myInterrupt();
        }

        Main.audioPlayer.stopMusic();
        Main.tetrisFrame.remove(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.menuPanel.requestFocusInWindow();
        Main.menuPanel.selectCurrentButton();
    }

    public synchronized void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 40) {
            this.extraScore = 0;
        }

    }

    public void run() {
        this.typeOfSquare = Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare;
        Main.audioPlayer.playMusic(this.music);
        System.out.println("thread run");
        this.gameOver = false;

        while(!this.gameOver) {
            try {
                synchronized(this) {
                    while(this.suspendFlag) {
                        this.wait();
                    }
                }

                this.checkGameOver();
                this.checkLine();
                this.clearAnimationInThread();
                this.checkScore();
                Thread.sleep((long)MILLI_SPEED[this.getSpeedIndex()], NANO_SPEED[this.getSpeedIndex()]);
                if (this.checkIsElementAlmostFell()) {
                    synchronized(this) {
                        this.lastMove();
                    }

                    this.repaint();
                } else {
                    synchronized(this) {
                        Moving.move(this.currentTetromino, (byte)1);
                    }

                    this.repaint();
                }
            } catch (InterruptedException var8) {
                if (this.interruptFlag) {
                    return;
                }

                System.out.println("Thread resume after sleeping!");
            }
        }

        this.blockMainMenuButton = true;
        Main.audioPlayer.stopMusic();
        Main.audioPlayer.stopMusic();
        this.gameOverRepaint(this.elementsStayOnField);
        this.goLeaderBoardPanel();
    }

    private void clearAnimationInThread() throws InterruptedException {
        if (this.indexesOfDeletingLines.size() > 0) {
            this.clearAnimation = true;
            if (this.clearLinesAnimationType == 0) {
                this.helperForDeleting = 0;
            }

            this.playClearLinesAudio();

            for(int i = 0; i < 5; ++i) {
                if (this.clearLinesAnimationType == 0) {
                    ++this.helperForDeleting;
                }

                if (this.indexesOfDeletingLines.size() == 4) {
                    if (this.color == transparentColor2) {
                        this.color = transparentColor;
                    } else {
                        this.color = transparentColor2;
                    }
                }

                this.repaint();
                Thread.sleep(55L);
            }

            this.clearAnimation = false;
            this.repaint();
            if (this.indexesOfDeletingLines.size() == 4) {
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.setForeground(transparentColor);
                this.color = transparentColor;
            }

            Iterator var3 = this.indexesOfDeletingLines.iterator();

            while(var3.hasNext()) {
                int el = (Integer)var3.next();
                this.deleteLine(el);
            }

            this.indexesOfDeletingLines.clear();
        }

    }

    private void playClearLinesAudio() {
        if (this.indexesOfDeletingLines.size() == 4) {
            Main.audioPlayer.playTetris();
        } else {
            Main.audioPlayer.playClearLine();
        }

    }

    private int getSpeedIndex() {
        if (this.level == 0) {
            return 0;
        } else if (this.level == 1) {
            return 1;
        } else if (this.level == 2) {
            return 2;
        } else if (this.level == 3) {
            return 3;
        } else if (this.level == 4) {
            return 4;
        } else if (this.level == 5) {
            return 5;
        } else if (this.level == 6) {
            return 6;
        } else if (this.level == 7) {
            return 7;
        } else if (this.level == 8) {
            return 8;
        } else if (this.level == 9) {
            return 9;
        } else if (this.level >= 10 && this.level <= 12) {
            return 10;
        } else if (this.level >= 13 && this.level <= 15) {
            return 11;
        } else if (this.level >= 16 && this.level <= 18) {
            return 12;
        } else {
            return this.level >= 19 && this.level <= 28 ? 13 : 14;
        }
    }

    public void startNewGame() {
        this.resetPlayValues();
        this.getSettingsNotAffectingTheGame();
        this.setFieldMatrix();
        this.setTetrisLabels();
        if (Main.multiplayerPanel2.typeOfGame == 3) {
            this.webThreadManager = new Thread(this::webManager);
            this.webThreadManager.start();
        } else {
            this.threadManager = new Thread(this::manager);
            this.threadManager.start();
        }

        this.requestFocusInWindow();
    }

    private void getSettingsNotAffectingTheGame() {
        OptionsSaver optionsSaver = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            Throwable var3 = null;

            try {
                optionsSaver = (OptionsSaver)ois.readObject();
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (ois != null) {
                    if (var3 != null) {
                        try {
                            ois.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        ois.close();
                    }
                }

            }
        } catch (ClassNotFoundException | IOException var15) {
            var15.printStackTrace();
        }

        assert optionsSaver != null;

        this.cwKey = optionsSaver.getCwKey();
        this.ccwKey = optionsSaver.getCcwKey();
        this.rightKey = optionsSaver.getRightKey();
        this.leftKey = optionsSaver.getLeftKey();
        this.downKey = optionsSaver.getDownKey();
        this.hardDropKey = optionsSaver.getHardDropKey();
        this.pauseKey = optionsSaver.getPauseKey();
        this.exitMenuKey = optionsSaver.getExitMenuKey();
        Main.tetrisPanelMultiplayer.backgroundType = optionsSaver.getBackgroundType();
        this.clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        this.paintShadow = optionsSaver.getShadow();
        this.grid = optionsSaver.getGrid();
        this.music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double)optionsSaver.getMusicVolume() / 100.0D;
        Main.audioPlayer.soundsVolume = (double)optionsSaver.getSoundsVolume() / 100.0D;
    }

    public static ArrayList<String> getNetworkIPs() {
        byte[] ip;
        try {
            ip = InetAddress.getLocalHost().getAddress();
        } catch (Exception var6) {
            return null;
        }

        ArrayList<String> listOfReachableIps = new ArrayList();

        for(int i = 1; i <= 254; ++i) {
            ip[3] = (byte)i;

            try {
                InetAddress address = InetAddress.getByAddress(ip);
                String output = address.toString().substring(1);
                listOfReachableIps.add(output);
            } catch (UnknownHostException var5) {
                var5.printStackTrace();
            }
        }

        return listOfReachableIps;
    }

    private void webManager() {
        this.blockMainMenuButton = false;
        int n;
        int size;
        if (this.thisAppServer) {
            this.waiting = true;
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("room creation...");
            this.startOpponentWaitingThread();
            int roomNumber = 0;
            size = ThreadLocalRandom.current().nextInt(1, 1000);
            if (this.telegram && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://telegram.me/tetris_game_tetris_bot?start=iwannaplay=1=" + Main.multiplayerPanel2.nicknameTextField.getText() + "=address=" + size));
                } catch (URISyntaxException | IOException var12) {
                    var12.printStackTrace();
                }
            }

            try {
                this.client1 = new Client1(size, Main.multiplayerPanel2.nicknameTextField.getText());
            } catch (URISyntaxException var11) {
                var11.printStackTrace();
            }

            boolean setRoom = true;

            do {
                try {
                    Thread.sleep(60L);
                } catch (InterruptedException var10) {
                    System.out.println("interrupted");
                    return;
                }

                if (setRoom && this.client1.isConnectedToTheServer()) {
                    setRoom = false;
                    Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(this.client1.getRoomNumber() + "");
                }
            } while(!this.client1.isConnectedToTheGame());

            if (!this.client1.isConnectedToTheServer()) {
                return;
            }

            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(this.client1.getOpponentNickname());
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nicknameTextField.getText());
            this.tetrominoesStack = new Stack();
            this.tetrominoesStackByte = this.client1.getTetrominoesStackByte();

            for(n = 0; n < 500; ++n) {
                this.tetrominoesStack.push(this.tetrominoesStackByte[n]);
            }
        } else {
            this.setTetrominoesStack();
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("trying to connect...");

            try {
                this.client2 = new Client2(Integer.parseInt(Main.multiplayerPanel2.joinRoomTextField.getText()), this.tetrominoesStackByte, Main.multiplayerPanel2.nicknameTextField.getText());
            } catch (URISyntaxException var9) {
                var9.printStackTrace();
            }

            do {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException var8) {
                    return;
                }

                if (this.client2.isDisconnectedFromServer()) {
                    Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("connection failed!");
                }
            } while(!this.client2.isConnectedToTheGame());

            if (!this.client2.isConnectedToTheServer()) {
                return;
            }

            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(this.client2.getOpponentNickname());
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nicknameTextField.getText());
        }

        this.waiting = false;
        this.updateCurrentTetromino();
        this.repaint();
        this.startNewTread();
        System.out.println(" web manager start!");
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = false;
        this.gameOver = false;

        while(!this.gameOver) {
            int m;
            byte[][] fieldMatrixByte;
            int i;
            int j;
            SquareOfTetromino[] squareOfTetrominos;
            if (this.thisAppServer) {
                size = this.elementsStayOnField.size();
                squareOfTetrominos = new SquareOfTetromino[size];

                for(n = 0; n < size; ++n) {
                    squareOfTetrominos[n] = (SquareOfTetromino)this.elementsStayOnField.get(n);
                }

                n = this.fieldMatrix.length;
                m = this.fieldMatrix[0].length;
                fieldMatrixByte = new byte[n][m];

                for(i = 0; i < n; ++i) {
                    for(j = 0; j < m; ++j) {
                        fieldMatrixByte[i][j] = this.fieldMatrix[i][j];
                    }
                }

                this.sendingObject = new SendingObject(this.gameOver, fieldMatrixByte, this.score, this.currentTetromino, squareOfTetrominos, (byte)this.level, this.amountOfDeletingLinesBetweenLevels, this.nextTetromino);
                this.client1.sendObject(this.sendingObject);
                this.receivingObject = this.client1.receiveObject();
                if (!this.client1.isConnectedToTheServer()) {
                    System.out.println("connection lost");
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                    break;
                }
            } else {
                this.receivingObject = this.client2.receiveObject();
                if (!this.client2.isConnectedToTheServer()) {
                    System.out.println("connection lost");
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                    break;
                }

                size = this.elementsStayOnField.size();
                squareOfTetrominos = new SquareOfTetromino[size];

                for(n = 0; n < size; ++n) {
                    squareOfTetrominos[n] = (SquareOfTetromino)this.elementsStayOnField.get(n);
                }

                try {
                    n = this.receivingObject.fieldMatrixByte.length;
                    m = this.receivingObject.fieldMatrixByte[0].length;
                } catch (Exception var14) {
                    continue;
                }

                fieldMatrixByte = new byte[n][m];

                for(i = 0; i < n; ++i) {
                    for(j = 0; j < m; ++j) {
                        fieldMatrixByte[i][j] = this.fieldMatrix[i][j];
                    }
                }

                this.sendingObject = new SendingObject(this.gameOver, fieldMatrixByte, this.score, this.currentTetromino, squareOfTetrominos, (byte)this.level, this.amountOfDeletingLinesBetweenLevels, this.nextTetromino);
                this.client2.sendObject(this.sendingObject);
            }

            try {
                if (this.receivingObject.gameOver) {
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                    break;
                }

                size = this.receivingObject.squareOfTetrominoes.length;
                ArrayList<SquareOfTetromino> elementsStayOnFieldOpponent = new ArrayList(Arrays.asList(this.receivingObject.squareOfTetrominoes).subList(0, size));
                byte n1 = (byte)this.receivingObject.fieldMatrixByte.length;
                byte m1 = (byte)this.receivingObject.fieldMatrixByte[0].length;
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix = new byte[n1][m1];
                int i1 = 0;

                while(true) {
                    if (i1 >= n1) {
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOver = this.receivingObject.gameOver;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.score = this.receivingObject.score;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.currentTetromino = this.receivingObject.currentTetromino;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.elementsStayOnField = elementsStayOnFieldOpponent;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.level = this.receivingObject.level;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                        Main.tetrisPanelMultiplayer.tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>" + this.receivingObject.level);
                        Main.tetrisPanelMultiplayer.tetrisLinesAmountLabelOpponent.setText("lines: " + this.receivingObject.amountOfDeletingLines);
                        this.opponentScore = this.receivingObject.score;
                        this.setScore();
                        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = this.receivingObject.nextTetromino;
                        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.repaint();
                        break;
                    }

                    System.arraycopy(this.receivingObject.fieldMatrixByte[i1], 0, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix[i1], 0, m1);
                    ++i1;
                }
            } catch (NullPointerException var15) {
                continue;
            }

            try {
                Thread.sleep(80L);
            } catch (InterruptedException var13) {
                break;
            }
        }

    }

    private void manager() {

        int amountOfLostConnection = 0;

        if (thisAppServer) {

            waiting = true;

            startOpponentWaitingThread();

            try {

                server = new Server();
            } catch (IOException e) {
                e.printStackTrace();

                goMenuPanel();
                return;
            }

            getTetrominoesStack();

            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(server.opponentName);

        } else {

            blockMainMenuButton = true;
            setTetrominoesStack();
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nickname);
            if (Main.multiplayerPanel2.typeOfGame == Multiplayer.LOCAL/*Main.multiplayerPanel2.isLocalGame*/) {
                ArrayList<String> listOfReachableIps = getNetworkIPs();

                if (listOfReachableIps == null)
                    System.exit(1);

                int PORT = 65535;

                final Client[] connectedClient = {null};
                final Client[] client = new Client[1];

                String localAddress = null;

                try {
                    localAddress = InetAddress.getLocalHost().getHostAddress();
                    System.out.println(localAddress);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                for (String el : listOfReachableIps) {
                    if (el.equals(localAddress))
                        continue;
                    Thread thread = new Thread(() -> {
                        try {
                            client[0] = new Client(tetrominoesStackByte, PORT, el);
                            if (client[0].connected) {
                                connectedClient[0] = client[0];
                                isConnected.set(true);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                }

                synchronized (isConnected) {
                    int counter = 0;
                    while (!isConnected.get()) {
                        try {
                            isConnected.wait(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        if (counter == 100)
                            break;
                        if (isConnected.get()) {
                            isConnected.notifyAll();
                        }
                    }
                }

                listOfReachableIps.clear();

                if (connectedClient[0] == null) {
                    System.out.println("connection error!");
                    goMenuPanel();

                    return;
                }

                isConnected.set(false);
                blockMainMenuButton = false;

                clientA = connectedClient[0];
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(connectedClient[0].opponentName);
            } else {
                String[] opponentAddress = Main.multiplayerPanel2.globalAddressTextField.getText().split(":");

                try {
                    blockMainMenuButton = false;
                    clientA = new Client(tetrominoesStackByte, Integer.parseInt(opponentAddress[1]), opponentAddress[0]);
                } catch (IOException | NumberFormatException e) {

                    goMenuPanel();
                    return;
                }
            }

        }

        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nickname);

        waiting = false;
        updateCurrentTetromino();
        repaint();
        startNewTread();

        System.out.println("manager start!");

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = false;

        gameOver = false;

        while (!gameOver) {

            if (thisAppServer) {

                int size = elementsStayOnField.size();
                SquareOfTetromino[] squareOfTetrominoes = new SquareOfTetromino[size];

                for (int i = 0; i < size; i++)
                    squareOfTetrominoes[i] = elementsStayOnField.get(i);

                int n = fieldMatrix.length;
                int m = fieldMatrix[0].length;

                System.out.println(n + "x" + m);

                byte[][] fieldMatrixByte = new byte[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++)
                        fieldMatrixByte[i][j] = fieldMatrix[i][j];
                }

                sendingObject = new SendingObject(gameOver, fieldMatrixByte, score, currentTetromino,
                        squareOfTetrominoes, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);

                try {
                    server.send(sendingObject);
                } catch (IOException e) {
                    System.out.println("connection lost");
                    break;
                }

                try {
                    server.serverSocket.setSoTimeout(250);
                    receivingObject = server.receive();

                } catch (IOException e) {

                    amountOfLostConnection++;

                    if (amountOfLostConnection == 1) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(server.opponentName);
                    }

                    if (amountOfLostConnection == 2) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.YELLOW);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText("[bad connection]");
                    }

                    if (amountOfLostConnection == 3) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.ORANGE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText("[bad connection]");
                    }

                    if (amountOfLostConnection == 4) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.RED);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText("[bad connection]");
                    }

                    if (amountOfLostConnection > 10) {
                        System.out.println("connection lost");
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                        break;
                    }

                    System.out.println("continue from server");
                    continue;
                }

            } else {

                try {
                    clientA.clientSocket.setSoTimeout(250);
                    receivingObject = clientA.receive();

                } catch (IOException e) {

                    amountOfLostConnection++;

                    if (amountOfLostConnection == 1) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(clientA.opponentName);
                    }

                    if (amountOfLostConnection == 2) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.YELLOW);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText("[bad connection]");
                    }

                    if (amountOfLostConnection == 3) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.ORANGE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText("[bad connection]");
                    }

                    if (amountOfLostConnection == 4) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.RED);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(" [bad connection]");
                    }

                    if (amountOfLostConnection > 10) {
                        System.out.println("connection lost");
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                        break;
                    }

                    System.out.println("continue from client");
                    continue;
                }

                int size = elementsStayOnField.size();
                SquareOfTetromino[] squareOfTetrominos = new SquareOfTetromino[size];

                for (int i = 0; i < size; i++)
                    squareOfTetrominos[i] = elementsStayOnField.get(i);

                int n;
                int m;

                try {
                    n = receivingObject.fieldMatrixByte.length;
                    m = receivingObject.fieldMatrixByte[0].length;
                } catch (Exception e) {
                    continue;
                }

                byte[][] fieldMatrixByte = new byte[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++)
                        fieldMatrixByte[i][j] = fieldMatrix[i][j];
                }

                sendingObject = new SendingObject(gameOver, fieldMatrixByte, score, currentTetromino, squareOfTetrominos, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);

                try {
                    clientA.send(sendingObject);
                } catch (IOException e) {
                    System.out.println("connection lost");
                    break;
                }
            }

            amountOfLostConnection = 0;
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
            if (thisAppServer)
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(server.opponentName);
            else
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(clientA.opponentName);

            if (receivingObject.gameOver) {
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                break;
            }

            int size = receivingObject.squareOfTetrominoes.length;

            ArrayList<SquareOfTetromino> elementsStayOnFieldOpponent = new ArrayList<>(Arrays.asList(receivingObject.squareOfTetrominoes).subList(0, size));

            byte n = (byte) receivingObject.fieldMatrixByte.length;
            byte m = (byte) receivingObject.fieldMatrixByte[0].length;

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix = new byte[n][m];

            for (int i = 0; i < n; i++) {
                System.arraycopy(receivingObject.fieldMatrixByte[i], 0, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix[i], 0, m);
            }

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOver = receivingObject.gameOver;

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.score = receivingObject.score;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.currentTetromino = receivingObject.currentTetromino;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.elementsStayOnField = elementsStayOnFieldOpponent;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.level = receivingObject.level;

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();

            Main.tetrisPanelMultiplayer.tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>" + receivingObject.level);
            Main.tetrisPanelMultiplayer.tetrisLinesAmountLabelOpponent.setText("lines: " + receivingObject.amountOfDeletingLines);

            opponentScore = receivingObject.score;
            setScore();

            // next panel:
            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = receivingObject.nextTetromino;
            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.repaint();


            try {
                Thread.sleep(40);
            } catch (InterruptedException ignored) {
            }
        }
    }


    private void setTetrominoesStack() {
        this.tetrominoesStackByte = new byte[500];
        this.tetrominoesStack = new Stack();

        for(int i = 0; i < 500; ++i) {
            this.tetrominoesStackByte[i] = Randomizer.oldStyleRandomTetromino();
            this.tetrominoesStack.push(this.tetrominoesStackByte[i]);
        }

    }

    private void getTetrominoesStack() {
        this.tetrominoesStack = new Stack();
        this.tetrominoesStackByte = this.server.getTetrominoesStack();

        for(int i = 0; i < 500; ++i) {
            this.tetrominoesStack.push(this.tetrominoesStackByte[i]);
        }

    }

    private void startOpponentWaitingThread() {
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = true;
        TetrisPlayFieldPanelMultiplayerOpponent var10000 = Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent;
        TetrisPlayFieldPanelMultiplayerOpponent var10003 = Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent;
        var10003.getClass();
        var10000.waitingThread = new Thread(var10003::waiting);
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingThread.start();
    }

    private void setTetrisLabels() {
        this.setScore();
        this.setLevel();
        this.setLines();
    }

    private void startNewTread() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    private void resetPlayValues() {
        this.randomType = 1;
        this.clearLinesAnimationType = 0;
        this.music = 3;
        this.level = 0;
        this.score = 0L;
        this.extraScore = 0;
        this.opponentScore = 0L;
        this.amountOfDeletingLinesBetweenLevels = 0;
        this.amountOfDeletingLinesBetweenTetrominoes = 0;
        this.grid = false;
        this.gameOver = true;
        this.paintShadow = true;
        this.suspendFlag = false;
        this.interruptFlag = false;
        this.elementsStayOnField = new ArrayList();
        this.usedTetrominoes = new byte[7];
        this.fieldMatrix = new byte[22][12];
        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        int i;
        for(i = 0; i < 4; ++i) {
            coordinates[i] = new ByteCoordinates();
        }

        if (this.randomType == 2) {
            for(i = 0; i < 7; ++i) {
                this.usedTetrominoes[i] = -1;
            }
        }

        this.currentTetromino = new Tetromino(coordinates, (byte)-2, (byte)0, (byte)0, (byte)0);
        this.updateScore(Color.WHITE, Color.WHITE, "<html><body style='text-align: center'>Level:<br>0", "<html><body style='text-align: center'>Level:<br>0");
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = -1;
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = -1;
        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0.0D;
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("player name");
    }

    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!this.waiting) {
            if (this.getWidth() < this.getHeight()) {
                this.radius = (double)this.getWidth() / 10.0D;
            } else {
                this.radius = (double)this.getHeight() / 20.0D;
            }

            if (this.grid) {
                Painting.drawLines(g2d, this.getWidth(), this.getHeight(), this.radius);
            }

            if (this.clearAnimation) {
                if (this.clearLinesAnimationType == 1) {
                    Painting.showRandomColorClearLinesAnimation(g2d, this.elementsStayOnField, this.indexesOfDeletingLines, this.radius, this.typeOfSquare);
                } else if (this.clearLinesAnimationType == 0) {
                    Painting.showDisappearClearLinesAnimation(g2d, this.helperForDeleting, this.elementsStayOnField, this.indexesOfDeletingLines, this.radius, this.typeOfSquare);
                }
            } else {
                Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
                if (!this.gameOver) {
                    Painting.paintLyingElements(g2d, this.elementsStayOnField, this.radius, this.typeOfSquare);
                    Painting.paintCurrentTetromino(this.currentTetromino, g2d, this.radius, this.typeOfSquare);
                    if (this.paintShadow) {
                        Painting.paintCurrentTetrominoShadow(this.fieldMatrix, this.currentTetromino, g2d, this.radius, this.typeOfSquare);
                    }
                }
            }

        }
    }

    public void wakeUpThreadFromSleeping() {
        this.thread.interrupt();
    }

    private void checkGameOver() {
        for(int i = 0; i < 10; ++i) {
            if (this.fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                this.gameOver = true;
                break;
            }
        }

    }

    private void checkLine() {
        for(int i = 0; i < 21; ++i) {
            int counter = 0;

            for(int j = 1; j < 11; ++j) {
                if (this.fieldMatrix[i][j] == 1) {
                    ++counter;
                }
            }

            if (counter == 10) {
                this.indexesOfDeletingLines.add(i);
            }
        }

    }

    private void setLines() {
        Main.tetrisPanelMultiplayer.tetrisLinesAmountLabel.setText("Lines: " + this.amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (this.amountOfDeletingLinesBetweenLevels == (this.level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            ++this.level;
            this.setLevel();
        }

    }

    private void setLevel() {
        Main.tetrisPanelMultiplayer.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + this.level);
    }

    private void checkScore() {
        if (this.amountOfDeletingLinesBetweenTetrominoes == 1) {
            this.score += 40L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 2) {
            this.score += 100L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 3) {
            this.score += 300L * (long)(this.level + 1);
        } else if (this.amountOfDeletingLinesBetweenTetrominoes == 4) {
            this.score += 1200L * (long)(this.level + 1);
        }

        this.amountOfDeletingLinesBetweenTetrominoes = 0;
        this.setScore();
    }

    private void setScore() {
        String playerString;
        String opponentString;
        if (this.score > this.opponentScore) {
            playerString = "<html><body style='text-align: center'>Score:<br>" + this.score + "<br/>(+" + (this.score - this.opponentScore) + ")</html>";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + this.opponentScore + "<br/>(-" + (this.score - this.opponentScore) + ")</html>";
            this.updateScore(Color.GREEN.darker(), Color.RED.darker(), playerString, opponentString);
        } else if (this.score < this.opponentScore) {
            playerString = "<html><body style='text-align: center'>Score:<br>" + this.score + "<br/>(-" + (this.opponentScore - this.score) + ")</html>";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + this.opponentScore + "<br/>(+" + (this.opponentScore - this.score) + ")</html>";
            this.updateScore(Color.RED.darker(), Color.GREEN.darker(), playerString, opponentString);
        } else {
            playerString = "<html><body style='text-align: center'>Score:<br>" + this.score + "<br/>(0)";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + this.opponentScore + "<br/>(0)";
            this.updateScore(Color.WHITE, Color.WHITE, playerString, opponentString);
        }

    }

    private void updateScore(Color playerColor, Color OpponentColor, String playerString, String opponentString) {
        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(playerColor);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(OpponentColor);
        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText(playerString);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText(opponentString);
    }

    private void deleteLine(int deletingLine) {
        ++this.amountOfDeletingLinesBetweenTetrominoes;
        ++this.amountOfDeletingLinesBetweenLevels;

        for(int i = deletingLine; i > 0; --i) {
            System.arraycopy(this.fieldMatrix[i - 1], 1, this.fieldMatrix[i], 1, 10);
        }

        this.elementsStayOnField.removeIf((elx) -> {
            return elx.coordinates.y == deletingLine - 1;
        });
        Iterator var4 = this.elementsStayOnField.iterator();

        while(var4.hasNext()) {
            SquareOfTetromino el = (SquareOfTetromino)var4.next();
            if (el.coordinates.y < deletingLine - 1) {
                ++el.coordinates.y;
            }
        }

        this.setLines();
        this.checkLevel();
    }

    private boolean checkIsElementAlmostFell() {
        for(int i = 0; i < 4; ++i) {
            ++this.currentTetromino.coordinates[i].y;
        }

        boolean isFell = Moving.isTetrominoConnected(this.currentTetromino.coordinates, this.fieldMatrix);

        for(int i = 0; i < 4; ++i) {
            --this.currentTetromino.coordinates[i].y;
        }

        return isFell;
    }

    private void updateCurrentTetromino() {
        this.currentTetromino.tetrominoType = (Byte)this.tetrominoesStack.pop();
        this.nextTetromino = (Byte)this.tetrominoesStack.peek();
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = (Byte)this.tetrominoesStack.peek();
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.repaint();
        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmount(this.currentTetromino.tetrominoType);
        System.out.println(this.tetrominoesStack.size());
        this.currentTetromino.rotationType = 0;
        this.setFirstCurrentTetrominoStepsAndColor();
    }

    private void setFirstCurrentTetrominoStepsAndColor() {
        boolean stepYBack = false;

        for(int i = 1; i < 11; ++i) {
            if (this.fieldMatrix[2][i] == 1 && this.currentTetromino.tetrominoType != 0) {
                stepYBack = true;
                break;
            }
        }

        if (stepYBack) {
            this.currentTetromino.stepY = -1;
        } else {
            this.currentTetromino.stepY = 0;
        }

        if (this.currentTetromino.tetrominoType == 3) {
            this.currentTetromino.stepX = 4;
        } else {
            this.currentTetromino.stepX = 3;
        }

        this.currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(this.currentTetromino);
        this.currentTetromino = Rotation.doRotation(this.currentTetromino);
    }

    public synchronized void myInterrupt() {
        this.interruptFlag = true;
        this.thread.interrupt();
        System.out.println("tread interrupted!");
    }

    public synchronized void mySuspend() {
        this.stepYBeforePause = this.currentTetromino.stepY;
        this.suspendFlag = true;
        System.out.println("suspend");
    }

    public synchronized void myResume() {
        this.currentTetromino.stepY = this.stepYBeforePause;
        this.suspendFlag = false;
        this.notify();
        System.out.println("resume");
    }

    private void setFieldMatrix() {
        for(int i = 0; i < 22; ++i) {
            for(int j = 0; j < 12; ++j) {
                if (i == 21) {
                    this.fieldMatrix[i][j] = 1;
                } else if (j != 0 && j != 11) {
                    this.fieldMatrix[i][j] = 0;
                } else {
                    this.fieldMatrix[i][j] = 1;
                }
            }
        }

    }

    private void goLeaderBoardPanel() {
        if (this.client1 != null) {
            this.client1.getSocket().close();
        }

        if (this.client2 != null) {
            this.client2.getSocket().close();
        }

        Main.tetrisFrame.remove(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
        ScoreDialog scoreDialog = new ScoreDialog(Main.tetrisFrame, true, this.score);
        Main.leaderBoardPanel.newPotentialLeader = scoreDialog.playerNameField.getText();
        if (scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader)) {
            Main.leaderBoardPanel.newPotentialLeader = "player";
        }

        Main.leaderBoardPanel.newPotentialLeader = Main.leaderBoardPanel.newPotentialLeader + "[M]";
        Main.leaderBoardPanel.saveLeaderBoardAfterGameOver(true);
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);
        Main.audioPlayer.playClick();
    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {
        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for(int i = amount - 1; i >= 0; --i) {
            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / (long)amount / 1000L);
            } catch (InterruptedException var5) {
                var5.printStackTrace();
            }

            if (elementsStayOnField.size() > 0) {
                elementsStayOnField.remove(i);
            }

            this.repaint();
        }

    }

    public Dimension getPreferredSize() {
        this.d = super.getPreferredSize();
        this.c = this.getParent();
        if (this.c != null) {
            this.d = this.c.getSize();
            double w = this.d.getWidth();
            double h = this.d.getHeight();
            double s = Math.min(w, h);
            return new Dimension((int)Math.round(s * 0.41D / 10.0D) * 10, (int)Math.round(s * 0.82D / 20.0D) * 20);
        } else {
            return new Dimension(100, 100);
        }
    }
}
