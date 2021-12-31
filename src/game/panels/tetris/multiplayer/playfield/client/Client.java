package game.panels.tetris.multiplayer.playfield.client;

import game.panels.tetris.multiplayer.playfield.manager.DataManager;
import game.panels.tetris.multiplayer.playfield.manager.SendingObject;
import game.panels.tetris.multiplayer.stun.StunTest;
import game.start.Main;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Client {

    public volatile boolean connected = false;
    public byte[] receivingData;
    public byte[] sendingData;

    public DatagramSocket clientSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    public String opponentName;

    public Client(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {

        if (Main.multiplayerPanel2.isLocalGame) {
            localConnection(tetrominoesStackByte, port, serverAddress);
        } else
            inetConnection(tetrominoesStackByte, port, serverAddress);
    }

    private void localConnection(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.clientSocket = new DatagramSocket(0);
        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        this.sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(serverAddress), port);

        System.out.println("trying to connect to " + serverAddress + "...");
        // send nickname
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        try {
            clientSocket.setSoTimeout(2000);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            clientSocket.receive(receivingPacket);
        } catch (IOException e) {
            clientSocket.close();
            return;
        }

        connected = true;
        System.out.println("init message from server[" + receivingPacket.getAddress() + ":" + receivingPacket.getPort() + "]");
        opponentName = new String(receivingPacket.getData(), StandardCharsets.UTF_8).trim();

        // send tetrominoes stack
        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);
    }

    private void inetConnection(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.clientSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        this.sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(serverAddress), port);

        // send nickname
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        //receive opponent name
        clientSocket.receive(receivingPacket);
        System.out.println("init message from server[" + receivingPacket.getAddress().getHostName() + ":" + receivingPacket.getPort() + "]");
        opponentName = new String(receivingPacket.getData(), StandardCharsets.UTF_8).trim();

        // send tetrominoes stack
        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);
    }


    public void send(SendingObject sendingObject) throws IOException {

        sendingData = DataManager.convertToBytes(sendingObject);
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        System.out.println(sendingData.length + "  Sending data length");
    }

    public SendingObject receive() throws IOException {

        clientSocket.receive(receivingPacket);
        return DataManager.getObjectFromBytes(receivingPacket.getData());

    }
}