package game.panels.tetris.multiplayer.playfield;

import game.dialogs.ScoreDialog;
import game.helperclasses.coordinates.ByteCoordinates;
import game.helperclasses.tetromino.SquareOfTetromino;
import game.helperclasses.tetromino.Tetromino;
import game.panels.tetris.controller.*;
import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.tetris.multiplayer.sockets.client.Client;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
import game.panels.tetris.multiplayer.sockets.server.Server;
import game.panels.tetris.multiplayer.web.Client1;
import game.panels.tetris.multiplayer.web.Client2;
import game.panels.tetris.multiplayer.web.telegram.DataBaseClient;
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
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

public class TetrisPlayFieldPanelMultiplayer extends JPanel implements Runnable/*, KeyListener*/ {

    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0;
    public static final byte RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;

    public static final AtomicBoolean isConnected = new AtomicBoolean(false);

    private static final Color transparentColor = new Color(0, 0, 0, 100);
    private static final Color transparentColor2 = new Color(0, 0, 0, 2);

    private Color backgroundColor = new Color(0, 0, 0, 100);

    public boolean suspendFlag;
    public boolean interruptFlag;
    public boolean blockMainMenuButton = false;
    public boolean gameOver = true;
    public boolean grid;
    public boolean paintShadow;
    public boolean clearAnimation;
    public boolean thisAppServer = true;
    public boolean telegram;
    private volatile boolean waiting = true;

    public volatile byte[][] fieldMatrix;
    public byte[] usedTetrominoes;
    private byte[] tetrominoesStackByte = null;

    public long score;
    private long opponentScore;

    public byte extraScore;
    public byte clearLinesAnimationType;
    public byte helperForDeleting;
    public byte music;
    private byte typeOfSquare = 0;
    private volatile byte nextTetromino;

    public int randomType;
    public int amountOfDeletingLinesBetweenTetrominoes;
    public int amountOfDeletingLinesBetweenLevels;
    public int level;
    public int cwKey = 68;
    public int ccwKey = 65;
    public int rightKey = 39;
    public int leftKey = 37;
    public int downKey = 40;
    public int hardDropKey = 32;
    public int pauseKey = 10;
    public int exitMenuKey = 27;

    public Tetromino currentTetromino;
    public ArrayList<SquareOfTetromino> elementsStayOnField;

    public ArrayList<Integer> indexesOfDeletingLines;

    public Server server;
    public Client clientA;
    private Client1 client1 = null;
    private Client2 client2 = null;

    public SendingObject sendingObject;
    public SendingObject receivingObject;

    public Thread thread;
    private Thread threadManager;
    private Thread webThreadManager;

    private Stack<Byte> tetrominoesStack;
    private Stack<Byte> extraTetrominoesStack;
    private long counterOldForFalling;

    private final KeyHandler keyHandler;
    public String telegramUserNicknameOpponent = "player";
    public String telegramUserNickname = "player";

    public TetrisPlayFieldPanelMultiplayer() {
        setOpaque(false);
        setForeground(transparentColor);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0F)));
        indexesOfDeletingLines = new ArrayList<>();
        keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
    }

    private void lastMove() {
        Main.audioPlayer.playHardDrop();

        for (int j = 0; j < 4; ++j) {
            SquareOfTetromino squareOfTetromino = new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y), currentTetromino.tetrominoType);
            elementsStayOnField.add(squareOfTetromino);
            if (currentTetromino.coordinates[j].y > -2) {
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
            }
        }

        checkGameOver();
        updateCurrentTetromino();
        if (extraScore > 0)
            score += extraScore;

        extraScore = 0;
        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);
    }

    public void goMenuPanel() {

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();

        Main.audioPlayer.playClick();

        sendingObject.gameOver = true;

        if (client1 != null) {
            client1.sendObject(sendingObject);
            client1.getSocket().close();
        }

        if (client2 != null) {
            client2.sendObject(sendingObject);
            client2.getSocket().close();
        }

        gameOver = true;

        if (Main.multiplayerPanel2.typeOfGame == Multiplayer.WEB) {
            webThreadManager.interrupt();
        } else if (thisAppServer) {
            if (threadManager.isAlive()) {
                threadManager.interrupt();
            }

            if (server != null) {
                server.serverSocket.close();
            }
        } else if (clientA != null) {
            clientA.clientSocket.close();
        }

        blockMainMenuButton = false;
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

    public void run() {

        typeOfSquare = Main.tetrisPanel.tetrisPlayFieldPanel.typeOfSquare;

        Main.audioPlayer.playMusic(music);

        System.out.println(Thread.currentThread().getName() + " start");

        gameOver = false;

        double frames = 0.0D;

        long old = System.nanoTime();
        long counterOld = System.nanoTime();
        long counterOldForClearing = System.nanoTime();

        counterOldForFalling = System.nanoTime();

        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }

                long current = System.nanoTime();
                long delta = current - old;
                long counterDelta = current - counterOld;
                long counterTimeOfFalling = current - counterOldForFalling;
                long counterTimeOfClearing = current - counterOldForClearing;

                if (clearAnimation && counterTimeOfClearing >= 55555555L) {
                    clearAnimation();
                    counterOldForClearing = System.nanoTime();
                }

                int speedLevel;
                if (level < 21) {
                    speedLevel = level;
                } else {
                    speedLevel = 20;
                }

                if (counterTimeOfFalling >= (long) (1000000000 / (speedLevel + 1) * 2)) {
                    if (checkIsElementAlmostFell()) {
                        lastMove();
                        repaint();
                    } else if (!clearAnimation) {
                        Moving.pressDownKey(currentTetromino, fieldMatrix);
                        repaint();
                    }

                    counterOldForFalling = System.nanoTime();
                }

                if (counterDelta >= 333333333L) {
                    System.out.println(frames / ((double) counterDelta / 1.0E9D));
                    Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.fps = frames / ((double) counterDelta / 1.0E9D);
                    Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.repaint();
                    frames = 0.0D;
                    counterOld = System.nanoTime();
                }

                if (delta >= 16666666L) {
                    long missedTime = delta - 16666666L;
                    old = System.nanoTime() - missedTime;
                    update();
                    checkGameOver();
                    checkLine();

                    clearAnimation = indexesOfDeletingLines.size() > 0;

                    checkScore();

                    ++frames;
                } else {
                    Thread.sleep(1L);
                }
            } catch (InterruptedException ignored) {
            }
        }

        /*try {
            Thread.sleep(300L);
        } catch (InterruptedException ignored) {
        }*/

        if (!interruptFlag) {
            Main.audioPlayer.stopMusic();
            gameOverRepaint(elementsStayOnField);
            goLeaderBoardPanel();
        }
    }

    private void update() {
        if (!gameOver && !clearAnimation) {
            if (keyHandler.isLeft()) {
                Moving.pressLeftKey(currentTetromino, fieldMatrix,true);
                keyHandler.setLeft(false);
                repaint();
            } else if (keyHandler.isRight()) {
                Moving.pressRightKey(currentTetromino, fieldMatrix,true);
                keyHandler.setRight(false);
                repaint();
            } else if (!keyHandler.isDown()) {
                if (keyHandler.isHardDrop()) {
                    extraScore += Moving.pressHardDropKey(fieldMatrix, currentTetromino);
                    lastMove();
                    keyHandler.setHardDrop(false);
                    counterOldForFalling = System.nanoTime();
                    repaint();
                } else if (keyHandler.isCcw_rotation()) {
                    Rotation.pressCCWKey(fieldMatrix, currentTetromino);
                    keyHandler.setCcw_rotation(false);
                    repaint();
                } else if (keyHandler.isCw_rotation()) {
                    Rotation.pressCWKey(fieldMatrix, currentTetromino);
                    keyHandler.setCw_rotation(false);
                    repaint();
                } else if (keyHandler.isExit()) {
                    if (!blockMainMenuButton) {
                        gameOver = true;
                        keyHandler.setExit(false);
                        goMenuPanel();
                    }
                }
            } else {
                ++currentTetromino.stepY;

                int i;
                for (i = 0; i < 4; ++i) {
                    ++currentTetromino.coordinates[i].y;
                }

                if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {
                    --currentTetromino.stepY;

                    for (i = 0; i < 4; ++i) {
                        --currentTetromino.coordinates[i].y;
                    }

                    lastMove();
                    counterOldForFalling = System.nanoTime();
                } else {
                    --currentTetromino.stepY;

                    for (i = 0; i < 4; ++i) {
                        --currentTetromino.coordinates[i].y;
                    }

                    extraScore += Moving.pressDownKey(currentTetromino, fieldMatrix);
                    System.out.println("DOWN");
                    Main.audioPlayer.playMove();
                    counterOldForFalling = System.nanoTime();
                }

                keyHandler.setDown(false);
                repaint();
            }

            if (keyHandler.isDown_released()) {
                extraScore = 0;
            }
        }
    }

    private void clearAnimation() {
        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;
            if (helperForDeleting < 5) {
                if (helperForDeleting == 0) {
                    playClearLinesAudio();
                }

                ++helperForDeleting;
                if (indexesOfDeletingLines.size() == 4) {
                    if (backgroundColor == transparentColor2) {
                        backgroundColor = transparentColor;
                    } else {
                        backgroundColor = transparentColor2;
                    }
                }
            } else {
                clearAnimation = false;
                helperForDeleting = 0;
                if (indexesOfDeletingLines.size() == 4) {
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.setForeground(transparentColor);
                    backgroundColor = transparentColor;
                }

                for (int el : indexesOfDeletingLines) {
                    deleteLine(el);
                }

                indexesOfDeletingLines.clear();
                counterOldForFalling = System.nanoTime();
            }

            repaint();
        }
    }

    private void playClearLinesAudio() {
        if (indexesOfDeletingLines.size() == 4) {
            Main.audioPlayer.playTetris();
        } else {
            Main.audioPlayer.playClearLine();
        }
    }

    public void startNewGame() {


        /*int width = (int) (getWidth() * 4.7);
        int height = (int) (getHeight() * 1.2);

        if(width >= Main.monitorWidth || height >= Main.monitorHeight)
           Main.tetrisFrame.setExtendedState(Main.tetrisFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        else {
            Main.tetrisFrame.setSize(width, height);
            Main.tetrisFrame.setLocationRelativeTo(null);
        }*/

        resetPlayValues();
        deserializeOptionsForNewGame();
        setFieldMatrix();
        setTetrisLabels();

        keyHandler.resetValues();

        extraTetrominoesStack = new Stack<>();
        if (Main.multiplayerPanel2.typeOfGame == Multiplayer.WEB) {
            webThreadManager = new Thread(this::webManager);
            webThreadManager.start();
        } else {
            threadManager = new Thread(this::manager);
            threadManager.start();
        }

        requestFocusInWindow();
    }

    private void getSettingsNotAffectingTheGame(OptionsSaver optionsSaver) {
        keyHandler.cwKey = optionsSaver.getCwKey();
        keyHandler.ccwKey = optionsSaver.getCcwKey();
        keyHandler.rightKey = optionsSaver.getRightKey();
        keyHandler.leftKey = optionsSaver.getLeftKey();
        keyHandler.downKey = optionsSaver.getDownKey();
        keyHandler.hardDropKey = optionsSaver.getHardDropKey();
        keyHandler.pauseKey = optionsSaver.getPauseKey();
        keyHandler.exitMenuKey = optionsSaver.getExitMenuKey();
        Main.tetrisPanelMultiplayer.backgroundType = optionsSaver.getBackgroundType();
        clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        paintShadow = optionsSaver.getShadow();
        grid = optionsSaver.getGrid();
        music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double) optionsSaver.getMusicVolume() / 100.0D;
        Main.audioPlayer.soundsVolume = (double) optionsSaver.getSoundsVolume() / 100.0D;
    }

    private void deserializeOptionsForNewGame() {
        OptionsSaver optionsGetter = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(System.getProperty("user.dir"), "options.dat")).getAbsolutePath()));
            optionsGetter = (OptionsSaver) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException var2) {
            var2.printStackTrace();
        }

        assert optionsGetter != null;

        level = optionsGetter.getStartLevel();
        randomType = optionsGetter.getRandomType();
        getSettingsNotAffectingTheGame(optionsGetter);
    }



    public static ArrayList<String> getNetworkIPs() {
        byte[] ip;
        try {
            ip = InetAddress.getLocalHost().getAddress();
        } catch (Exception e) {
            return null;
        }

        ArrayList<String> listOfReachableIps = new ArrayList<>();

        for (int i = 1; i <= 254; ++i) {
            ip[3] = (byte) i;

            try {
                InetAddress address = InetAddress.getByAddress(ip);
                String output = address.toString().substring(1);
                listOfReachableIps.add(output);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return listOfReachableIps;
    }

    private void webManager() {
        blockMainMenuButton = false;
        int n;
        int size;
        if (thisAppServer) {
            waiting = true;
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("room creation...");
            startOpponentWaitingThread();
            size = ThreadLocalRandom.current().nextInt(1, 1000);
            if (telegram && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://telegram.me/tetris_game_tetris_bot?start=iwannaplay=" + size
                            + "=" + DataBaseClient.idList.get(Main.multiplayerPanel2.telegramUsers.getSelectedIndex())));
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if(telegram)
                    client1 = new Client1(size, telegramUserNickname);
                else
                client1 = new Client1(size, Main.multiplayerPanel2.nicknameTextField.getText());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            boolean setRoom = true;

            do {
                try {
                    Thread.sleep(60L);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    return;
                }

                if (setRoom && client1.isConnectedToTheServer()) {
                    setRoom = false;
                    Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(client1.getRoomNumber() + "");
                }
            } while (!client1.isConnectedToTheGame());

            if (!client1.isConnectedToTheServer()) {
                return;
            }

            if (telegram) {

                String opponentNick = (String) Main.multiplayerPanel2.telegramUsers.getSelectedItem();

                assert opponentNick != null;

                opponentNick = getEditTelegramNick(opponentNick);

                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(opponentNick);
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(client1.getOpponentNickname());
            } else {
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(client1.getOpponentNickname());
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nicknameTextField.getText());
            }

            tetrominoesStack = new Stack<>();
            tetrominoesStackByte = client1.getTetrominoesStackByte();

            for (n = 0; n < 500; ++n) {
                tetrominoesStack.push(tetrominoesStackByte[n]);
            }
        } else {
            setTetrominoesStack();
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("trying to connect...");

            try {
                if (telegram)
                    client2 = new Client2(Integer.parseInt(Main.multiplayerPanel2.joinRoomTextField.getText()), tetrominoesStackByte, telegramUserNickname);
                else
                    client2 = new Client2(Integer.parseInt(Main.multiplayerPanel2.joinRoomTextField.getText()), tetrominoesStackByte, Main.multiplayerPanel2.nicknameTextField.getText());
            }catch (Exception e) {
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("connection failed!");
                return;
            }

            do {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    return;
                }

                if (client2.isDisconnectedFromServer()) {
                    Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("connection failed!");
                }
            } while (!client2.isConnectedToTheGame());

            if (client2.isConnectedToTheServer()) {
                return;
            }

            if (telegram) {
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(telegramUserNicknameOpponent);
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(client2.getOpponentNickname());
            }
            else {
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(client2.getOpponentNickname());
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText(Main.multiplayerPanel2.nicknameTextField.getText());
            }
        }

        waiting = false;
        updateCurrentTetromino();
        repaint();
        startNewTread();
        System.out.println(" web manager start!");
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = false;
        gameOver = false;

        while (!gameOver) {
            int m;
            byte[][] fieldMatrixByte;
            int i;
            int j;
            SquareOfTetromino[] squareOfTetrominos;

            try {
                Thread.sleep(80L);
            } catch (InterruptedException e) {
                break;
            }

            if (thisAppServer) {
                size = elementsStayOnField.size();
                squareOfTetrominos = new SquareOfTetromino[size];

                for (n = 0; n < size; ++n) {
                    squareOfTetrominos[n] = elementsStayOnField.get(n);
                }

                n = fieldMatrix.length;
                m = fieldMatrix[0].length;
                fieldMatrixByte = new byte[n][m];

                for (i = 0; i < n; ++i) {
                    for (j = 0; j < m; ++j) {
                        fieldMatrixByte[i][j] = fieldMatrix[i][j];
                    }
                }

                sendingObject = new SendingObject(gameOver, fieldMatrixByte, score, currentTetromino, squareOfTetrominos, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);
                client1.sendObject(sendingObject);
                receivingObject = client1.receiveObject();


            } else {
                receivingObject = client2.receiveObject();

                size = elementsStayOnField.size();
                squareOfTetrominos = new SquareOfTetromino[size];

                for (n = 0; n < size; ++n) {
                    squareOfTetrominos[n] = elementsStayOnField.get(n);
                }

                try {
                    n = receivingObject.fieldMatrixByte.length;
                    m = receivingObject.fieldMatrixByte[0].length;
                } catch (Exception e) {
                    continue;
                }

                fieldMatrixByte = new byte[n][m];

                for (i = 0; i < n; ++i) {
                    for (j = 0; j < m; ++j) {
                        fieldMatrixByte[i][j] = fieldMatrix[i][j];
                    }
                }

                sendingObject = new SendingObject(gameOver, fieldMatrixByte, score, currentTetromino, squareOfTetrominos, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);
                client2.sendObject(sendingObject);
            }

            try {

                size = receivingObject.squareOfTetrominoes.length;
                ArrayList<SquareOfTetromino> elementsStayOnFieldOpponent = new ArrayList<>(Arrays.asList(receivingObject.squareOfTetrominoes).subList(0, size));
                byte n1 = (byte) receivingObject.fieldMatrixByte.length;
                byte m1 = (byte) receivingObject.fieldMatrixByte[0].length;
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix = new byte[n1][m1];
                int i1 = 0;

                while (true) {
                    if (i1 >= n1) {
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
                        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = receivingObject.nextTetromino;
                        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.repaint();
                        break;
                    }

                    System.arraycopy(receivingObject.fieldMatrixByte[i1], 0, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix[i1], 0, m1);
                    ++i1;
                }

                if (receivingObject.gameOver) {
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                    Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                    break;
                }

            } catch (NullPointerException ignored) {
            }
        }
    }

    public String getEditTelegramNick(String opponentNick) {
        // opponentNick is a chat
        if (opponentNick.startsWith("[GROUP]")) {

            if (opponentNick.length() > 19)
                opponentNick = opponentNick.substring(0, 19) + "...";

        }

        // opponentNick is an user (if 'all' - skip)
        else if(!opponentNick.equals("@all")){

            // (null) - user doesn't have nickname only name
            if (opponentNick.endsWith("(null)")) {
                opponentNick = opponentNick.substring(0, opponentNick.length() - 6);

                if (opponentNick.length() > 19)
                    opponentNick = opponentNick.substring(0, 19) + "...";

            }
            // user has name and (nickname)
            else {
                String name = opponentNick.substring(0, opponentNick.lastIndexOf("("));
                String nick = opponentNick.substring(opponentNick.lastIndexOf("(") + 1, opponentNick.lastIndexOf(")"));

                if (name.length() > 19)
                    name = name.substring(0, 19) + "...";
                if (nick.length() > 19)
                    nick = nick.substring(0, 19) + "...";

                opponentNick = "<html><body style='text-align: center'>" + name + "<br>" + nick;
            }
        }
        return opponentNick;
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
            if (Main.multiplayerPanel2.typeOfGame == Multiplayer.LOCAL) {
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
                    if(Main.multiplayerPanel2.typeOfGame == Multiplayer.HAMACHI)
                    clientA = new Client(tetrominoesStackByte, 0, Main.multiplayerPanel2.vpnAddressTextField.getText());
                    else
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
                    System.arraycopy(fieldMatrix[i], 0, fieldMatrixByte[i], 0, m);
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
                    System.arraycopy(fieldMatrix[i], 0, fieldMatrixByte[i], 0, m);
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

            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = receivingObject.nextTetromino;
            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.repaint();

            try {
                Thread.sleep(40);
            } catch (InterruptedException ignored) {
            }
        }
    }


    private void setTetrominoesStack() {
        tetrominoesStackByte = new byte[500];
        tetrominoesStack = new Stack<>();

        for (int i = 0; i < 500; ++i) {
            tetrominoesStackByte[i] = Randomizer.oldStyleRandomTetromino();
            tetrominoesStack.push(tetrominoesStackByte[i]);
        }
    }

    private void getTetrominoesStack() {
        tetrominoesStack = new Stack<>();
        tetrominoesStackByte = server.getTetrominoesStack();

        for (int i = 0; i < 500; ++i) {
            tetrominoesStack.push(tetrominoesStackByte[i]);
        }
    }

    private void startOpponentWaitingThread() {
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = true;
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingThread = new Thread(Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent::waiting);
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingThread.start();
    }

    private void setTetrisLabels() {
        setScore();
        setLevel();
        setLines();
    }

    private void startNewTread() {
        thread = new Thread(this);
        thread.start();
    }

    private void resetPlayValues() {
        randomType = 1;
        clearLinesAnimationType = 0;
        music = 3;
        level = 0;
        score = 0L;
        extraScore = 0;
        opponentScore = 0L;
        amountOfDeletingLinesBetweenLevels = 0;
        amountOfDeletingLinesBetweenTetrominoes = 0;

        grid = false;
        gameOver = true;
        paintShadow = true;
        suspendFlag = false;
        interruptFlag = false;

        elementsStayOnField = new ArrayList<>();
        usedTetrominoes = new byte[7];
        fieldMatrix = new byte[22][12];

        ByteCoordinates[] coordinates = new ByteCoordinates[4];

        for (int i = 0; i < 4; ++i)
            coordinates[i] = new ByteCoordinates();

        currentTetromino = new Tetromino(coordinates, (byte) -2, (byte) 0, (byte) 0, (byte) 0);

        updateScore(Color.WHITE, Color.WHITE, "<html><body style='text-align: center'>Level:<br>0",
                "<html><body style='text-align: center'>Level:<br>0");

        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = -1;
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = -1;
        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0.0D;
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("player name");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            paint(g);
        } catch (Exception ignored) {
        }
    }

    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!waiting) {
            double radius;
            if (getWidth() < getHeight()) {
                radius = (double) getWidth() / 10.0D;
            } else {
                radius = (double) getHeight() / 20.0D;
            }

            if (grid) {
                Painting.drawLines(g2d, getWidth(), getHeight(), radius);
            }

            if (clearAnimation) {
                if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION) {
                    Painting.showRandomColorClearLinesAnimation(g2d, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);
                } else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION) {
                    Painting.showDisappearClearLinesAnimation(g2d, helperForDeleting, elementsStayOnField, indexesOfDeletingLines, radius, typeOfSquare);
                }
            } else {
                Painting.paintLyingElements(g2d, elementsStayOnField, radius, typeOfSquare);
                if (!gameOver) {
                    Painting.paintCurrentTetromino(currentTetromino, g2d, radius, typeOfSquare);
                    if (paintShadow) {
                        Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d, radius, typeOfSquare);
                    }
                }
            }
        }
    }

    private void checkGameOver() {
        for (int i = 0; i < 10; ++i) {
            if (fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                gameOver = true;
                break;
            }
        }
    }

    private void checkLine() {
        for (int i = 0; i < 21; ++i) {
            int counter = 0;

            for (int j = 1; j < 11; ++j) {
                if (fieldMatrix[i][j] == 1) {
                    ++counter;
                }
            }

            if (counter == 10 && !indexesOfDeletingLines.contains(i)) {
                indexesOfDeletingLines.add(i);
            }
        }
    }

    private void setLines() {
        Main.tetrisPanelMultiplayer.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            ++level;
            setLevel();
        }
    }

    private void setLevel() {
        Main.tetrisPanelMultiplayer.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + level);
    }

    private void checkScore() {
        if (amountOfDeletingLinesBetweenTetrominoes == 1) {
            score += 40L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 2) {
            score += 100L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 3) {
            score += 300L * (long) (level + 1);
        } else if (amountOfDeletingLinesBetweenTetrominoes == 4) {
            score += 1200L * (long) (level + 1);
        }

        amountOfDeletingLinesBetweenTetrominoes = 0;
        setScore();
    }

    private void setScore() {
        String playerString;
        String opponentString;
        if (score > opponentScore) {
            playerString = "<html><body style='text-align: center'>Score:<br>" + score + "<br/>(+" + (score - opponentScore) + ")</html>";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>(-" + (score - opponentScore) + ")</html>";
            updateScore(Color.GREEN.darker(), Color.RED.darker(), playerString, opponentString);
        } else if (score < opponentScore) {
            playerString = "<html><body style='text-align: center'>Score:<br>" + score + "<br/>(-" + (opponentScore - score) + ")</html>";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>(+" + (opponentScore - score) + ")</html>";
            updateScore(Color.RED.darker(), Color.GREEN.darker(), playerString, opponentString);
        } else {
            playerString = "<html><body style='text-align: center'>Score:<br>" + score + "<br/>(0)";
            opponentString = "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>(0)";
            updateScore(Color.WHITE, Color.WHITE, playerString, opponentString);
        }
    }

    private void updateScore(Color playerColor, Color OpponentColor, String playerString, String opponentString) {
        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(playerColor);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(OpponentColor);
        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText(playerString);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText(opponentString);
    }

    private void deleteLine(int deletingLine) {
        ++amountOfDeletingLinesBetweenTetrominoes;
        ++amountOfDeletingLinesBetweenLevels;

        for (int i = deletingLine; i > 0; --i) {
            System.arraycopy(fieldMatrix[i - 1], 1, fieldMatrix[i], 1, 10);
        }

        elementsStayOnField.removeIf((elx) -> elx.coordinates.y == deletingLine - 1);

        for (SquareOfTetromino el : elementsStayOnField) {
            if (el.coordinates.y < deletingLine - 1) {
                ++el.coordinates.y;
            }
        }

        setLines();
        checkLevel();
    }

    private boolean checkIsElementAlmostFell() {
        for (int i = 0; i < 4; ++i) {
            ++currentTetromino.coordinates[i].y;
        }

        boolean isFell = Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);

        for (int i = 0; i < 4; ++i) {
            --currentTetromino.coordinates[i].y;
        }

        return isFell;
    }

    private void updateCurrentTetromino() {
        if (tetrominoesStack.size() > 0) {
            currentTetromino.tetrominoType = tetrominoesStack.pop();
            extraTetrominoesStack.push(currentTetromino.tetrominoType);
        }
        if(tetrominoesStack.size() == 0) {
            tetrominoesStack.addAll(extraTetrominoesStack);
            /*currentTetromino.tetrominoType = tetrominoesStack.pop();*/
            extraTetrominoesStack = new Stack<>();
        }

        System.out.println("size of original stack: " + tetrominoesStack.size() + "------------------");
        System.out.println("size of extra stack: " + extraTetrominoesStack.size() + "------------------");

        nextTetromino = tetrominoesStack.peek();
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = tetrominoesStack.peek();
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.repaint();
        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);
        System.out.println(tetrominoesStack.size());
        currentTetromino.rotationType = 0;
        setFirstCurrentTetrominoStepsAndColor();
    }

    private void setFirstCurrentTetrominoStepsAndColor() {
        boolean stepYBack = false;

        for (int i = 1; i < 11; ++i) {
            if (fieldMatrix[2][i] == 1 && currentTetromino.tetrominoType != 0) {
                stepYBack = true;
                break;
            }
        }

        if (stepYBack) {
            currentTetromino.stepY = -1;
        } else {
            currentTetromino.stepY = 0;
        }

        if (currentTetromino.tetrominoType == 3) {
            currentTetromino.stepX = 4;
        } else {
            currentTetromino.stepX = 3;
        }

        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);
        currentTetromino = Rotation.doRotation(currentTetromino);
    }

    public synchronized void myInterrupt() {
        interruptFlag = true;
        thread.interrupt();
        System.out.println("tread interrupted!");
    }

    private void setFieldMatrix() {
        for (int i = 0; i < 22; ++i) {
            for (int j = 0; j < 12; ++j) {
                if (i == 21) {
                    fieldMatrix[i][j] = 1;
                } else if (j != 0 && j != 11) {
                    fieldMatrix[i][j] = 0;
                } else {
                    fieldMatrix[i][j] = 1;
                }
            }
        }
    }

    private void goLeaderBoardPanel() {

        sendingObject.gameOver = true;

        if (client1 != null) {
            client1.sendObject(sendingObject);
            client1.getSocket().close();
        }

        if (client2 != null) {
            client2.sendObject(sendingObject);
            client2.getSocket().close();
        }

        Main.tetrisFrame.remove(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();
        ScoreDialog scoreDialog = new ScoreDialog(Main.tetrisFrame, true, score);
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

        blockMainMenuButton = true;
        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; --i) {
            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / (long) amount / 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elementsStayOnField.size() > 0) {
                elementsStayOnField.remove(i);
            }

            repaint();
        }
        blockMainMenuButton = false;
    }

    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
            double w = d.getWidth();
            double h = d.getHeight();
            double s = Math.min(w, h);
            return new Dimension((int) Math.round(s * 0.41D / 10.0D) * 10, (int) Math.round(s * 0.82D / 20.0D) * 20);
        } else {
            return new Dimension(100, 100);
        }
    }
}
