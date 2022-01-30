package game.panels.tetris.multiplayer.sockets.client;

import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.tetris.multiplayer.sockets.manager.DataManager;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
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
        if (Main.multiplayerPanel2.typeOfGame == Multiplayer.LOCAL) {
            localConnection(tetrominoesStackByte, serverAddress);
        } else if (Main.multiplayerPanel2.typeOfGame == Multiplayer.NET_HOLE_PUNCHING) {
            netHolePunching(tetrominoesStackByte, port, serverAddress);
        } else {
            hamachiConnection(tetrominoesStackByte, serverAddress);
        }
    }

    private void localConnection(byte[] tetrominoesStackByte, String serverAddress) throws IOException {
        int port = /*'\uffff'*/ 65535;

        receivingData = new byte[4096];
        sendingData = new byte[4096];

        clientSocket = new DatagramSocket(0);

        receivingPacket = new DatagramPacket(receivingData, receivingData.length);
        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(serverAddress), port);

        System.out.println("trying to connect to " + serverAddress + "...");

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

        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);
    }

    private void netHolePunching(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {
        receivingData = new byte[4096];
        sendingData = new byte[4096];

        clientSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        receivingPacket = new DatagramPacket(receivingData, receivingData.length);
        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(serverAddress), port);

        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        clientSocket.receive(receivingPacket);

        System.out.println("init message from server[" + receivingPacket.getAddress().getHostName()
                + ":" + receivingPacket.getPort() + "]");

        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);
    }

    private void hamachiConnection(byte[] tetrominoesStackByte, String serverAddress) throws IOException {
        int port = /*'쀀'*/ 49152;

        receivingData = new byte[4096];
        sendingData = new byte[4096];

        clientSocket = new DatagramSocket();
        receivingPacket = new DatagramPacket(receivingData, receivingData.length);
        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(serverAddress), port);

        System.out.println("trying to connect to " + serverAddress + "...");

        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        clientSocket.receive(receivingPacket);

        System.out.println("init message from server[" + receivingPacket.getAddress() + ":" + receivingPacket.getPort() + "]");

        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
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
