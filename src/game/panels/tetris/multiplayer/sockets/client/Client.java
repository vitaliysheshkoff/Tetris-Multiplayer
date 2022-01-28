package game.panels.tetris.multiplayer.sockets.client;

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
        if (Main.multiplayerPanel2.typeOfGame == 0) {
            this.localConnection(tetrominoesStackByte, serverAddress);
        } else if (Main.multiplayerPanel2.typeOfGame == 1) {
            this.netHolePunching(tetrominoesStackByte, port, serverAddress);
        } else {
            this.hamachiConnection(tetrominoesStackByte, serverAddress);
        }

    }

    private void localConnection(byte[] tetrominoesStackByte, String serverAddress) throws IOException {
        int port = '\uffff';
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.clientSocket = new DatagramSocket(0);
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(serverAddress), port);
        System.out.println("trying to connect to " + serverAddress + "...");
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);

        try {
            this.clientSocket.setSoTimeout(2000);
        } catch (SocketException var6) {
            var6.printStackTrace();
        }

        try {
            this.clientSocket.receive(this.receivingPacket);
        } catch (IOException var5) {
            this.clientSocket.close();
            return;
        }

        this.connected = true;
        System.out.println("init message from server[" + this.receivingPacket.getAddress() + ":" + this.receivingPacket.getPort() + "]");
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        this.sendingData = tetrominoesStackByte;
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
    }

    private void netHolePunching(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.clientSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(serverAddress), port);
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
        this.clientSocket.receive(this.receivingPacket);
        System.out.println("init message from server[" + this.receivingPacket.getAddress().getHostName() + ":" + this.receivingPacket.getPort() + "]");
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        this.sendingData = tetrominoesStackByte;
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
    }

    private void hamachiConnection(byte[] tetrominoesStackByte, String serverAddress) throws IOException {
        int port = '쀀';
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.clientSocket = new DatagramSocket();
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(serverAddress), port);
        System.out.println("trying to connect to " + serverAddress + "...");
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
        this.clientSocket.receive(this.receivingPacket);
        System.out.println("init message from server[" + this.receivingPacket.getAddress() + ":" + this.receivingPacket.getPort() + "]");
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        this.sendingData = tetrominoesStackByte;
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
    }

    public void send(SendingObject sendingObject) throws IOException {
        this.sendingData = DataManager.convertToBytes(sendingObject);
        this.sendingPacket.setData(this.sendingData);
        this.clientSocket.send(this.sendingPacket);
        System.out.println(this.sendingData.length + "  Sending data length");
    }

    public SendingObject receive() throws IOException {
        this.clientSocket.receive(this.receivingPacket);
        return DataManager.getObjectFromBytes(this.receivingPacket.getData());
    }
}
