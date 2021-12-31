package game.multiplayer.playfield;

import game.audio.AudioPlayer;
import game.dialogs.ScoreDialog;
import game.helperclasses.ByteCoordinates;
import game.helperclasses.SquareOfTetromino;
import game.helperclasses.Tetromino;
import game.multiplayer.playfield.client.Client;
import game.multiplayer.playfield.manager.SendingObject;
import game.multiplayer.playfield.server.Server;
import game.panels.tetris.TetrisNextTetrominoPanel;
import game.panels.tetris.playfield.controller.*;
import game.serial.OptionsSaver;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class TetrisPlayFieldPanelMultiplayer extends JPanel implements Runnable, KeyListener {

    public static final int[] MILLI_SPEED = {798, 715, 632, 549, 465, 382, 299, 216, 133, 99, 83, 66, 49, 33, 16};
    public static final int[] NANO_SPEED = {684832, 488496, 292159, 95822, 899486, 703149, 506812, 310475, 114139, 835604, 196337, 557069, 917802, 278535, 639268};
    public static final byte DISAPPEAR_CLEAR_LINES_ANIMATION = 0, RANDOM_COLOR_CLEAR_LINES_ANIMATION = 1;
    public static final byte OLD_STYLE_RANDOM = 1, NEW_STYLE_RANDOM = 2;
    public static final byte DEFAULT = 0;

    public volatile boolean blockMainMenuButton = false;

    static Color transparentColor = new Color(0,0,0,100);

    static Color transparentColor2 = new Color(0,0,0,2);

    public boolean suspendFlag;
    public boolean interruptFlag;
    public volatile boolean gameOver = true;
    public boolean grid;
    public boolean paintShadow;
    public boolean clearAnimation;

    public volatile byte[][] fieldMatrix;
    public byte[] usedTetrominoes;
    public int randomType;
    public int amountOfDeletingLinesBetweenTetrominoes, amountOfDeletingLinesBetweenLevels, level;
    public volatile long score;
    public byte extraScore;
    public byte stepYBeforePause;
    public byte clearLinesAnimationType;
    public byte helperForDeleting;
    public byte music;
    public int cwKey = KeyEvent.VK_D, ccwKey = KeyEvent.VK_A, rightKey = KeyEvent.VK_RIGHT, leftKey = KeyEvent.VK_LEFT, downKey = KeyEvent.VK_DOWN,
            hardDropKey = KeyEvent.VK_SPACE, pauseKey = KeyEvent.VK_ENTER, exitMenuKey = KeyEvent.VK_ESCAPE;

    public volatile Tetromino currentTetromino;
    public volatile ArrayList<SquareOfTetromino> elementsStayOnField;
    public ArrayList<Integer> indexesOfDeletingLines;
    public Thread thread;

    public Server server;
    public Client client1;
    public SendingObject sendingObject;
    public SendingObject receivingObject;

    public  boolean thisAppServer = true;

    byte[] tetrominoesStackByte = null;
    Stack tetrominoesStack;

    volatile byte nextTetromino;

    Thread threadManager;

    volatile long opponentScore;

    volatile boolean waiting = true;
    volatile boolean elementFell = false;



    public TetrisPlayFieldPanelMultiplayer() {
        setOpaque(false);
        setForeground(transparentColor);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        indexesOfDeletingLines = new ArrayList<>();
        addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {

        if (!gameOver && !clearAnimation) {

            if (e.getKeyCode() == ccwKey) {

                Rotation.pressCCWKey(fieldMatrix, currentTetromino);
                repaint();

            } else if (e.getKeyCode() == cwKey) {

                Rotation.pressCWKey(fieldMatrix, currentTetromino);
                repaint();

            } else if (e.getKeyCode() == leftKey) {

                Moving.pressLeftKey(currentTetromino, fieldMatrix);
                repaint();
            } else if (e.getKeyCode() == rightKey) {

                Moving.pressRightKey(currentTetromino, fieldMatrix);
                repaint();

            } else if (e.getKeyCode() == downKey) {

                currentTetromino.stepY += 1;
                for (int i = 0; i < 4; i++)
                    currentTetromino.coordinates[i].y += 1;

                if (Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix)) {

                    currentTetromino.stepY -= 1;
                    for (int i = 0; i < 4; i++)
                        currentTetromino.coordinates[i].y -= 1;

                    repaint();
                    lastMove();
                    wakeUpThreadFromSleeping();
                } else {

                    currentTetromino.stepY -= 1;
                    for (int i = 0; i < 4; i++)
                        currentTetromino.coordinates[i].y -= 1;

                    extraScore += Moving.pressDownKey(currentTetromino, fieldMatrix);
                    repaint();
                }


            } else if (e.getKeyCode() == hardDropKey) {

                extraScore += Moving.pressHardDropKey(fieldMatrix, currentTetromino);

                repaint();
                lastMove();
                wakeUpThreadFromSleeping();

            }
        }
        /*else*/
        if (e.getKeyCode() == exitMenuKey) {
            //  Main.tetrisPanel.mainMenuLabelMousePressed();

            if(!blockMainMenuButton)
            goMenuPanel();
        }
    }

    private  void lastMove() {

        Main.audioPlayer.playHardDrop();

        for (int j = 0; j < 4; j++) {
            elementsStayOnField.add(new SquareOfTetromino(new ByteCoordinates(currentTetromino.coordinates[j].x, currentTetromino.coordinates[j].y), currentTetromino.tetrominoType));

            if (currentTetromino.coordinates[j].y > -2)
                fieldMatrix[currentTetromino.coordinates[j].y + 1][currentTetromino.coordinates[j].x + 1] = 1;
        }

        checkGameOver();

        updateCurrentTetromino();
      //  updateNextTetromino();

        if (extraScore > 0)
            score += extraScore;
        extraScore = 0;

        Main.tetrisPanel.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);


        //Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);
    }


    public void goMenuPanel() {

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();

        Main.audioPlayer.playClick();

        gameOver = true;

       sendingObject = new SendingObject(gameOver, null /*fieldMatrix*/, score, currentTetromino, /*elementsStayOnField*/ null, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);/////////////////

        if (thisAppServer) {

           /* try {
                server.send(sendingObject);///////////////
            } catch (IOException e) {
                System.out.println("connection lost");
            }*/


            if(threadManager.isAlive())
                threadManager.interrupt();


            /*try {
                server.serverSocket.setSoTimeout(1);
            } catch (SocketException e) {
                e.printStackTrace();
            }*/


            if (server != null) {

                server.serverSocket.close();
            }

          //  System.out.println( server.serverSocket.isClosed());

           /* Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){
                server.serverSocket.close();
                System.out.println("The server is shut down!");
            }});*/

        } else {

            /*try {
                client.send(sendingObject);///////////////
            } catch (IOException e) {
                System.out.println("connection lost");
            }*/

            if (client1 != null)
                client1.clientSocket.close();
        }

        blockMainMenuButton = false;

        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.waitingOpponent = false;

        if (Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.thread != null)
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.myInterrupt();


        Main.audioPlayer.stopMusic();

        Main.tetrisFrame.remove(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.add(Main.menuPanel);

        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
       // Main.tetrisFrame.pack();
      //  Main.tetrisFrame.setLocationRelativeTo(null);
        Main.menuPanel.requestFocusInWindow();
        Main.menuPanel.selectCurrentButton();


    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            extraScore = 0;
        }
    }

    @Override
    public void run() {

        Main.audioPlayer.playMusic(music);

        System.out.println("thread run");

        gameOver = false;

        while (!gameOver) {
            try {
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }

                checkGameOver();
                checkLine();
                clearAnimationInThread();
                checkScore();

                /*if(level > 8) {
                    repaintingMoving = true;
                    for (int i = 1; i < 5; i++) {
                        Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                        step = (1. / 4) * i;
                        repaint();
                    }
                    repaintingMoving = false;
                    step = 0;
                }*/

                //  Thread.sleep(MILLI_SPEED[getSpeedIndex()], NANO_SPEED[getSpeedIndex()]);

               /* Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                repaintingMoving = true;
                step = 0.25;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 0.5;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 0.75;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 1;
                repaint();
                repaintingMoving = false;
                step = 0;*/

                Thread.sleep(MILLI_SPEED[getSpeedIndex()], NANO_SPEED[getSpeedIndex()]);
                if (checkIsElementAlmostFell()) {
                   synchronized (this) {
                       lastMove();
                   }
                    repaint();
                    continue;
                }

               /* Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                repaintingMoving = true;
                step = 0.25;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 0.5;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 0.75;
                repaint();
                Thread.sleep(MILLI_SPEED[getSpeedIndex()] / 4, NANO_SPEED[getSpeedIndex()] / 4);
                step = 1;
                repaint();
                repaintingMoving = false;
                step = 0;*/

                synchronized (this) {
                    Moving.move(currentTetromino, (byte) 1);
                }

                repaint();


            } catch (InterruptedException e) {
                if (interruptFlag)
                    return;
                System.out.println("Thread resume after sleeping!");
            }
        }


        // gameover:
        blockMainMenuButton = true;
        Main.audioPlayer.stopMusic();


        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           // e.printStackTrace();
        }*/

        //goMenuPanel();
        // Painting.gameOverRepaint();

        Main.audioPlayer.stopMusic();
        gameOverRepaint(elementsStayOnField);
        goLeaderBoardPanel();
        //   clearDatFile();
    }

    private void clearAnimationInThread() throws InterruptedException {

        if (indexesOfDeletingLines.size() > 0) {
            clearAnimation = true;

            if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                helperForDeleting = 0;

            playClearLinesAudio();

            for (int i = 0; i < 5; i++) {

                if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                    helperForDeleting++;

                if (indexesOfDeletingLines.size() == 4) {

                    if (color == transparentColor2) {
                        color = transparentColor;
                    }
                    else {
                        color = transparentColor2;
                    }

                }

                repaint();
                Thread.sleep(/*40*/ 55);
            }
            clearAnimation = false;
            repaint();

            if (indexesOfDeletingLines.size() == 4) {
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayer.setForeground(transparentColor);
                color = transparentColor;
            }

            for (int el : indexesOfDeletingLines)
                deleteLine(el);

            indexesOfDeletingLines.clear();
        }
    }

    private void playClearLinesAudio() {

        if (indexesOfDeletingLines.size() == 4)
            Main.audioPlayer.playTetris();
        else
            Main.audioPlayer.playClearLine();
    }

    private int getSpeedIndex() {
        if (level == 0)
            return 0;
        else if (level == 1)
            return 1;
        else if (level == 2)
            return 2;
        else if (level == 3)
            return 3;
        else if (level == 4)
            return 4;
        else if (level == 5)
            return 5;
        else if (level == 6)
            return 6;
        else if (level == 7)
            return 7;
        else if (level == 8)
            return 8;
        else if (level == 9)
            return 9;
        else if (level >= 10 && level <= 12)
            return 10;
        else if (level >= 13 && level <= 15)
            return 11;
        else if (level >= 16 && level <= 18)
            return 12;
        else if (level >= 19 && level <= 28)
            return 13;
        else // (level >= 29)
            return 14;
    }


    public void startNewGame() {


        resetPlayValues();

        getSettingsNotAffectingTheGame();

        setFieldMatrix();
        setTetrisLabels();

        threadManager = new Thread(this::manager);
        threadManager.start();

        requestFocusInWindow();

    }

    private void getSettingsNotAffectingTheGame() {

        OptionsSaver optionsSaver = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir"), Main.OPTIONS_FILE_NAME).getAbsolutePath()))) {
            optionsSaver = (OptionsSaver) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert optionsSaver != null;

        cwKey = optionsSaver.getCwKey();
        ccwKey = optionsSaver.getCcwKey();
        rightKey = optionsSaver.getRightKey();
        leftKey = optionsSaver.getLeftKey();
        downKey = optionsSaver.getDownKey();
        hardDropKey = optionsSaver.getHardDropKey();
        pauseKey = optionsSaver.getPauseKey();
        exitMenuKey = optionsSaver.getExitMenuKey();
        Main.tetrisPanelMultiplayer.backgroundType = optionsSaver.getBackgroundType();
        clearLinesAnimationType = optionsSaver.getLineClearAnimation();
        paintShadow = optionsSaver.getShadow();
        grid = optionsSaver.getGrid();
        music = optionsSaver.getMusic();
        Main.audioPlayer.musicVolume = (double) optionsSaver.getMusicVolume() / 100;
        Main.audioPlayer.soundsVolume = (double) optionsSaver.getSoundsVolume() / 100;
    }

    public static ArrayList<String> getNetworkIPs() {

        final byte[] ip;
        try {
            ip = InetAddress.getLocalHost().getAddress();
        } catch (Exception e) {
            return null;     // exit method, otherwise "ip might not have been initialized"
        }

        ArrayList<String> listOfReachableIps = new ArrayList<>();

        for (int i = 1; i <= 254; i++) {
            ip[3] = (byte) i;
            InetAddress address;
            try {
                address = InetAddress.getByAddress(ip);
                String output = address.toString().substring(1);
                listOfReachableIps.add(output);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return listOfReachableIps;
    }
    public static final AtomicBoolean isConnected = new AtomicBoolean(false);
    private void manager() {

        int amountOfLostConnection = 0;/////////////////////////

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
            if(Main.multiplayerPanel2.isLocalGame) {
                ArrayList<String> listOfReachableIps = getNetworkIPs();

                if (listOfReachableIps == null)
                    System.exit(1);

                //  System.out.println("trying to connect...");

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
                    if(el.equals(localAddress))
                        continue;
                    Thread thread = new Thread(() -> {
                        try {
                            client[0] = new Client(tetrominoesStackByte,PORT, el);
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
                    /*System.exit(2);*/
                    goMenuPanel();

                    return;
                }

                isConnected.set(false);
                blockMainMenuButton = false;

                client1 = connectedClient[0];
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(connectedClient[0].opponentName);
            }
            else{
                String[] opponentAddress = Main.multiplayerPanel2.globalAddressTextField.getText().split(":");
                // String port =  /*Main.multiplayerPanel2.joinPort;*/
               //   String address = Main.multiplayerPanel2.joinAddress;


            try {
                blockMainMenuButton = false;
                client1 = new Client(tetrominoesStackByte, Integer.parseInt(opponentAddress[1]), opponentAddress[0]);
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


                sendingObject = new SendingObject(gameOver, fieldMatrixByte /*fieldMatrix*/, score, currentTetromino, /*elementsStayOnField*/ squareOfTetrominoes, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);/////////////////

                try {

                    server.send(sendingObject);///////////////
                } catch (IOException e) {
                    System.out.println("connection lost");
                    break;
                }


                try {
                    server.serverSocket.setSoTimeout(250);
                    receivingObject = server.receive();

                } catch (IOException e) {
                    //e.printStackTrace();
                    /*if(!server.serverSocket.isConnected()){
                        System.out.println("connection lost");
                        break;
                    }*/

                    amountOfLostConnection++;

                    if (amountOfLostConnection == 1) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(server.opponentName);
                    }

                    if (amountOfLostConnection == 2) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.YELLOW);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*"<html>"+ server.opponentName + "<br/>" +*/ "[bad connection]");
                    }

                    if (amountOfLostConnection == 3) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.ORANGE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*"<html>"+ server.opponentName + "<br/>" + */"[bad connection]");
                    }

                    if (amountOfLostConnection == 4) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.RED);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*"<html>"+ server.opponentName + "<br/>" + */"[bad connection]");
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
                    client1.clientSocket.setSoTimeout(250);
                    receivingObject = client1.receive();


                } catch (IOException e) {

                    /*if(!client.clientSocket.isConnected()){
                        System.out.println("connection lost");
                        break;
                    }*/
                    amountOfLostConnection++;

                    if (amountOfLostConnection == 1) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(client1.opponentName);
                    }

                    if (amountOfLostConnection == 2) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.YELLOW);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*client1.opponentName + */" [bad connection]");
                    }

                    if (amountOfLostConnection == 3) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.ORANGE);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*client1.opponentName + */" [bad connection]");
                    }

                    if (amountOfLostConnection == 4) {

                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.RED);
                        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(/*client1.opponentName + */" [bad connection]");
                    }

                    if (amountOfLostConnection > 10) {
                        System.out.println("connection lost");
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                        Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                        break;
                    }

                    System.out.println("continue from client");
                    continue;
                    //  e.printStackTrace();
                }


                int size = elementsStayOnField.size();
                SquareOfTetromino[] squareOfTetrominos = new SquareOfTetromino[size];

                for (int i = 0; i < size; i++)
                    squareOfTetrominos[i] = elementsStayOnField.get(i);

                int n;
                int m;
                try {
                    n = receivingObject.fieldMatrix.length;
                    m = receivingObject.fieldMatrix[0].length;
                }catch (Exception e){
                    continue;
                }


                byte[][] fieldMatrixByte = new byte[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++)
                        fieldMatrixByte[i][j] = fieldMatrix[i][j];
                }

                sendingObject = new SendingObject(gameOver, fieldMatrixByte/*fieldMatrix*/, score, currentTetromino, squareOfTetrominos/*elementsStayOnField*/, (byte) level, amountOfDeletingLinesBetweenLevels, nextTetromino);/////////////////

                try {
                    client1.send(sendingObject);///////////////
                } catch (IOException e) {
                    // e.printStackTrace();
                    System.out.println("connection lost");
                    break;
                }


            }

            amountOfLostConnection = 0;
            Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setForeground(Color.WHITE);
            if(thisAppServer)
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(server.opponentName);
            else
                Main.tetrisPanelMultiplayer.tetrisPlayerNameLabelOpponent.setText(client1.opponentName);



            if (receivingObject.gameOver) {
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOverPainting = true;
                Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();
                break;
            }


            int size = receivingObject.elementsStayOnField.length;

            ArrayList<SquareOfTetromino> elementsStayOnFieldOpponent = new ArrayList<>(Arrays.asList(receivingObject.elementsStayOnField).subList(0, size));


            //  Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix = receivingObject.fieldMatrix;

            byte n = (byte) receivingObject.fieldMatrix.length;
            byte m = (byte) receivingObject.fieldMatrix[0].length;


            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix = new byte[n][m];

            for (int i = 0; i < n; i++) {
                /*Byte.toUnsignedInt(*/
                /*)*/
                System.arraycopy(receivingObject.fieldMatrix[i], 0, Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.fieldMatrix[i], 0, m);
            }

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.gameOver = receivingObject.gameOver;

            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.score = receivingObject.score;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.currentTetromino = receivingObject.currentTetromino;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.elementsStayOnField = /*receivingObject.elementsStayOnField*/elementsStayOnFieldOpponent;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.level = receivingObject.level;

           // Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.paintingFromThread = true;
            Main.tetrisPanelMultiplayer.tetrisPlayFieldPanelMultiplayerOpponent.repaint();

            Main.tetrisPanelMultiplayer.tetrisGameLevelLabelOpponent.setText("<html><body style='text-align: center'>Level:<br>" + receivingObject.level);
            Main.tetrisPanelMultiplayer.tetrisLinesAmountLabelOpponent.setText("lines: " + receivingObject.amountOfDeletingLines);


            opponentScore = receivingObject.score;

            /*if (receivingObject.score > score) {
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(Color.GREEN);
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText("<html>Score: " + receivingObject.score + "<br/>(+" + (receivingObject.score - score) + ")</html>");
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(Color.RED);
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText("<html>Score: " + score + "<br/>(" + (score - receivingObject.score) + ")</html>");
            } else if (receivingObject.score < score) {
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(Color.RED);
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText("<html>Score: " + receivingObject.score + "<br/>(" + (receivingObject.score - score) + ")</html>");
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(Color.GREEN);
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText("<html>Score: " + score + "<br/>(+" + (score - receivingObject.score) + ")</html>");
            } else {
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(Color.WHITE);
                Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText("Score: " + receivingObject.score);
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(Color.WHITE);
                Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText(*//*"<html>*//*"Score: " + score*//* + "<br/>" + scoreInfo + "</html>"*//*);
            }*/

            setScore();


            // next panel:
            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = receivingObject.nextTetromino;
            Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.repaint();


            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
              //  e.printStackTrace();
            }

        }
    }

    private void setTetrominoesStack() {
        tetrominoesStackByte = new byte[500];
        tetrominoesStack = new Stack();

        for (int i = 0; i < 500; i++) {
            tetrominoesStackByte[i] = Randomizer.oldStyleRandomTetromino();
            tetrominoesStack.push(tetrominoesStackByte[i]);
        }
    }

    private void getTetrominoesStack() {
        tetrominoesStack = new Stack();
        tetrominoesStackByte = server.getTetrominoesStack();


        for (int i = 0; i < 500; i++) {
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

        randomType = OLD_STYLE_RANDOM;
        clearLinesAnimationType = DISAPPEAR_CLEAR_LINES_ANIMATION;

        music = AudioPlayer.OFF;

        level = 0;
        score = 0;
        extraScore = 0;
        opponentScore = 0;
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
        for (int i = 0; i < 4; i++)
            coordinates[i] = new ByteCoordinates();

        if (randomType == NEW_STYLE_RANDOM) {
            for (int i = 0; i < 7; i++)
                usedTetrominoes[i] = -1;
        }

        currentTetromino = new Tetromino(coordinates, (byte) -2, DEFAULT, (byte) 0, (byte) 0);

        updateScore(Color.WHITE, Color.WHITE, "<html><body style='text-align: center'>Level:<br>" + "0",
                "<html><body style='text-align: center'>Level:<br>" + "0");

        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = -1;
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanelOpponent.nextTetromino = -1;

        // Main.audioPlayer.musicVolume = (double) 10 / 100;
        //  Main.audioPlayer.soundsVolume = (double) 10 / 100;

        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.resetTetrominoesAmount();
        Main.audioPlayer.musicFramePosition = 0;

        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("player name");

    }

    double radius = 1;
    Color color = new Color(0,0,0,100);
    public void paintComponent(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        if (waiting)
            return;

        if(getWidth() < getHeight())
         radius = getWidth() / 10.;
        else
            radius = getHeight() / 20.0;

        if (grid)
            Painting.drawLines(g2d, getWidth(), getHeight(), radius);

        //clear game animations:
        if (clearAnimation) {

            // first type of clear lines animation:
            if (clearLinesAnimationType == RANDOM_COLOR_CLEAR_LINES_ANIMATION)
                Painting.showRandomColorClearLinesAnimation(g2d, elementsStayOnField, indexesOfDeletingLines, radius);

                // second type of clear lines animation:
            else if (clearLinesAnimationType == DISAPPEAR_CLEAR_LINES_ANIMATION)
                Painting.showDisappearClearLinesAnimation(g2d, helperForDeleting, elementsStayOnField, indexesOfDeletingLines,radius);

        } else {

            Painting.paintLyingElements(g2d, elementsStayOnField,radius);

            if (!gameOver) {

              //  if (elementFell/*checkIsElementFell()*/) {

                   // lastMove();
                Painting.paintLyingElements(g2d, elementsStayOnField,radius);
                    //wakeUpThreadFromSleeping();

               // } else {
                Painting.paintCurrentTetromino(currentTetromino, g2d,radius);

                    if (paintShadow)
                        Painting.paintCurrentTetrominoShadow(fieldMatrix, currentTetromino, g2d,radius);
            //    }
            }
        }
    }

    public  void wakeUpThreadFromSleeping() {
        thread.interrupt();
    }

    private void checkGameOver() {

        /*if (checkOneLayingElementsUpperThenField()) {
            System.out.println("game over!");
            gameOver = true;
        }*/

        for(int i = 0; i < 10; i++){
            if (fieldMatrix[0][i + 1] == 1) {
                System.out.println("game over!");
                gameOver = true;
                break;
            }
        }
    }

    /*private boolean checkOneLayingElementsUpperThenField() {

        for (SquareOfTetromino el : elementsStayOnField) {
            if (el.coordinates.y == -1)
                return true;
        }

        return false;
    }*/

    private void checkLine() {

        int counter;
        int deletingLine;

        for (int i = 0; i < 21; i++) {
            counter = 0;
            for (int j = 1; j < 11; j++) {
                if (fieldMatrix[i][j] == 1) {
                    counter++;
                }
            }
            if (counter == 10) {
                deletingLine = i;
                indexesOfDeletingLines.add(deletingLine);
            }
        }
    }

    private void setLines() {
        Main.tetrisPanelMultiplayer.tetrisLinesAmountLabel.setText("Lines: " + amountOfDeletingLinesBetweenLevels);
    }

    private void checkLevel() {
        if (amountOfDeletingLinesBetweenLevels == (level + 1) * 10) {
            Main.audioPlayer.playNextLevel();
            level++;
            setLevel();
        }
    }

    private void setLevel() {
        Main.tetrisPanelMultiplayer.tetrisGameLevelLabel.setText("<html><body style='text-align: center'>Level:<br>" + level);
    }

    private void checkScore() {
        if (amountOfDeletingLinesBetweenTetrominoes == 1)
            score += 40L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 2)
            score += 100L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 3)
            score += 300L * (level + 1);
        else if (amountOfDeletingLinesBetweenTetrominoes == 4)
            score += 1200L * (level + 1);

        amountOfDeletingLinesBetweenTetrominoes = 0;
        setScore();
    }

    private /*synchronized*/ void setScore() {

        if (score > opponentScore) {

            String playerString =  "<html><body style='text-align: center'>Score:<br>" + score + "<br/>(+" + (score - opponentScore) + ")</html>";
            String opponentString =  "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>(-" + (score - opponentScore) + ")</html>";

            updateScore(Color.GREEN.darker(),Color.RED.darker(), playerString, opponentString);

        } else if (score < opponentScore) {

            String playerString =  "<html><body style='text-align: center'>Score:<br>" + score + "<br/>(-" + (opponentScore - score) + ")</html>" ;
            String opponentString =  "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>(+" + (opponentScore - score) + ")</html>";

            updateScore(Color.RED.darker(),Color.GREEN.darker(), playerString, opponentString);

        } else {

            String playerString =  "<html><body style='text-align: center'>Score:<br>" + score + "<br/>" + "(0)";
            String opponentString =  "<html><body style='text-align: center'>Score:<br>" + opponentScore + "<br/>" + "(0)";

            updateScore(Color.WHITE,Color.WHITE, playerString, opponentString);

        }
    }

    private void updateScore(Color playerColor, Color OpponentColor, String playerString, String opponentString) {

        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setForeground(playerColor);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setForeground(OpponentColor);

        Main.tetrisPanelMultiplayer.tetrisScoresLabel.setText(playerString);
        Main.tetrisPanelMultiplayer.tetrisScoresLabelOpponent.setText(opponentString);
    }

    private void deleteLine(int deletingLine) {

        amountOfDeletingLinesBetweenTetrominoes++;
        amountOfDeletingLinesBetweenLevels++;

        for (int i = deletingLine; i > 0; i--)
            System.arraycopy(fieldMatrix[i - 1], 1, fieldMatrix[i], 1, 10);

        elementsStayOnField.removeIf(el -> el.coordinates.y == deletingLine - 1);
        for (SquareOfTetromino el : elementsStayOnField) {
            if (el.coordinates.y < deletingLine - 1) {
                el.coordinates.y += 1;
            }
        }

        setLines();
        checkLevel();
    }

    private boolean checkIsElementAlmostFell() {

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y += 1;

        boolean isFell = Moving.isTetrominoConnected(currentTetromino.coordinates, fieldMatrix);

        for (int i = 0; i < 4; i++)
            currentTetromino.coordinates[i].y -= 1;

        return isFell;

    }

    private void updateCurrentTetromino() {

        currentTetromino.tetrominoType = (byte) tetrominoesStack.pop();
        nextTetromino = (byte) tetrominoesStack.peek();

        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.nextTetromino = (byte) tetrominoesStack.peek();
        Main.tetrisPanelMultiplayer.tetrisNextTetrominoPanel.repaint();

        Main.tetrisPanelMultiplayer.tetrisStatisticsPanel.updateTetrominoesAmount(currentTetromino.tetrominoType);

        System.out.println(tetrominoesStack.size());

        currentTetromino.rotationType = DEFAULT;
        setFirstCurrentTetrominoStepsAndColor();
    }

    private void setFirstCurrentTetrominoStepsAndColor() {

        boolean stepYBack = false;
        for (int i = 1; i < 11; i++) {
            if (fieldMatrix[2][i] == 1 && currentTetromino.tetrominoType != TetrisNextTetrominoPanel.I) {
                stepYBack = true;
                break;
            }
        }

        if (stepYBack)
            currentTetromino.stepY = -1;
        else
            currentTetromino.stepY = 0;

        if (currentTetromino.tetrominoType == TetrisNextTetrominoPanel.O)
            currentTetromino.stepX = 4;
        else
            currentTetromino.stepX = 3;

        currentTetromino.coordinates = Rotation.setCurrentTetrominoCoordinates(currentTetromino);
        currentTetromino = Rotation.doRotation(currentTetromino);

        /*if(Moving.isTetrominoConnected(currentTetromino.coordinates,fieldMatrix))
            gameOver = true;*/
    }

    public synchronized void myInterrupt() {
        interruptFlag = true;
        thread.interrupt();
        System.out.println("tread interrupted!");
    }

    public synchronized void mySuspend() {
        stepYBeforePause = currentTetromino.stepY;
        suspendFlag = true;
        System.out.println("suspend");
    }

    public synchronized void myResume() {
        currentTetromino.stepY = stepYBeforePause;
        suspendFlag = false;
        notify();
        System.out.println("resume");
    }


    private void setFieldMatrix() {

  /*[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]*/

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 21)
                    fieldMatrix[i][j] = 1;
                else if (j == 0 || j == 11)
                    fieldMatrix[i][j] = 1;
                else
                    fieldMatrix[i][j] = 0;
            }
        }
    }

    private void goLeaderBoardPanel() {

        Main.tetrisFrame.remove(Main.tetrisPanelMultiplayer);
        Main.tetrisFrame.add(Main.leaderBoardPanel);
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        Main.leaderBoardPanel.requestFocusInWindow();
        Main.leaderBoardPanel.selectCurrentButton();

        ScoreDialog scoreDialog = new ScoreDialog(Main.tetrisFrame, true);

        Main.leaderBoardPanel.newPotentialLeader = scoreDialog.playerNameField.getText();
        if (scoreDialog.isBlankString(Main.leaderBoardPanel.newPotentialLeader))
            Main.leaderBoardPanel.newPotentialLeader = "player";

        Main.leaderBoardPanel.newPotentialLeader = Main.leaderBoardPanel.newPotentialLeader + "[M]";

        Main.leaderBoardPanel.saveLeaderBoardAfterGameOver(true);
        System.out.println(Main.leaderBoardPanel.newPotentialLeader);

        Main.audioPlayer.playClick();
    }

    public void gameOverRepaint(ArrayList<SquareOfTetromino> elementsStayOnField) {

        // Main.tetrisPanel.tetrisScoresLabel.setText("game over");
        Main.audioPlayer.playGameOver();
        int amount = elementsStayOnField.size();

        for (int i = amount - 1; i >= 0; i--) {

            try {
                Thread.sleep(Main.audioPlayer.GAME_OVER_SOUND_LENGTH / amount / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elementsStayOnField.size() > 0)
                elementsStayOnField.remove(i);

            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(100, 100);
        }

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);

        //  System.out.println("prefered size" + s / 2 + " " + s);
        return new Dimension((int) (s * 0.41), (int) (s * 0.82));
    }
}