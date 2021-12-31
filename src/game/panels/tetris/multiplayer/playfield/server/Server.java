package game.panels.tetris.multiplayer.playfield.server;

import game.panels.tetris.multiplayer.playfield.manager.DataManager;
import game.panels.tetris.multiplayer.playfield.manager.SendingObject;
import game.panels.tetris.multiplayer.stun.StunTest;
import game.start.Main;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server {

    byte[] receivingData;
    byte[] sendingData;

    public DatagramSocket serverSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    private byte[] tetrominoesStackByte;

    public String opponentName;

    public Server() throws IOException {

        if (Main.multiplayerPanel2.isLocalGame) {
            localConnection();
        } else
            inetConnection();
    }

    private void localConnection() throws IOException {
        int port = 65535;
        String CLIENT_ADDRESS;
        int CLIENT_PORT;

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        serverSocket = new DatagramSocket(null);
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(Inet4Address.getLocalHost().getHostAddress(), port));

        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");

        System.out.println("waiting nickname ");
        // receive nickname
        serverSocket.receive(receivingPacket);
        opponentName = new String(receivingPacket.getData(), StandardCharsets.UTF_8).trim();
        System.out.println(opponentName);

        System.out.println("get nickname ");

        // send nickname
        CLIENT_ADDRESS = receivingPacket.getAddress().getHostName();
        CLIENT_PORT = receivingPacket.getPort();

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);

        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);

        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        // get tetrominoes stack
        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();

        if (!(tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7)) {
            serverSocket.close();
            System.exit(11);
        } else
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");
    }

    private void inetConnection() throws IOException {
        String[] opponentAddress = Main.multiplayerPanel2.globalCreateAddressTextField.getText().split(":");
        String CLIENT_ADDRESS = opponentAddress[0];
        int CLIENT_PORT;
        CLIENT_PORT = Integer.parseInt(opponentAddress[1]);

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.serverSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        this.serverSocket.setReuseAddress(true);

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);

        //send init message
        sendingData = "".getBytes();
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");

        String[] tokens = Main.multiplayerPanel2.ipLabel.getText().split(":");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("<html>" + tokens[0] + "<br/>" + tokens[1] + "</html>");

        // receive nickname
        serverSocket.receive(receivingPacket);
        opponentName = new String(receivingPacket.getData(), StandardCharsets.UTF_8).trim();

        // send nickname
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        // get tetrominoes stack
        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();

        if (!(tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7)) {
            serverSocket.close();
            System.exit(11);
        } else
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");
    }

    public byte[] getTetrominoesStack() {
        return tetrominoesStackByte;
    }

    public void send(SendingObject sendingObject) throws IOException {
        sendingData = DataManager.convertToBytes(sendingObject);
        System.out.println(sendingData.length + "  Sending data length");
        sendingPacket.setData(sendingData);

        serverSocket.send(sendingPacket);
    }

    public SendingObject receive() throws IOException {
        serverSocket.receive(receivingPacket);
        return DataManager.getObjectFromBytes(receivingPacket.getData());
    }
}