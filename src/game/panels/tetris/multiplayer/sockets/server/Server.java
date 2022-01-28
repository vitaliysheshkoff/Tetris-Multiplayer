package game.panels.tetris.multiplayer.sockets.server;

import game.panels.tetris.multiplayer.sockets.manager.DataManager;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
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
        if (Main.multiplayerPanel2.typeOfGame == 0) {
            this.localConnection();
        } else if (Main.multiplayerPanel2.typeOfGame == 1) {
            this.netHolePunching();
        } else {
            this.hamachiConnection();
        }

    }

    private void localConnection() throws IOException {
        int port = '\uffff';
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.serverSocket = new DatagramSocket((SocketAddress)null);
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(Inet4Address.getLocalHost().getHostAddress(), port));
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        System.out.println("waiting connection...");
        System.out.println("waiting nickname ");
        this.serverSocket.receive(this.receivingPacket);
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        System.out.println(this.opponentName);
        System.out.println("get nickname ");
        String CLIENT_ADDRESS = this.receivingPacket.getAddress().getHostName();
        int CLIENT_PORT = this.receivingPacket.getPort();
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.serverSocket.send(this.sendingPacket);
        this.serverSocket.receive(this.receivingPacket);
        this.tetrominoesStackByte = this.receivingPacket.getData();
        if (this.tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(this.tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + this.receivingPacket.getSocketAddress() + "] connected");
        } else {
            this.serverSocket.close();
            System.exit(11);
        }

    }

    private void netHolePunching() throws IOException {
        String[] opponentAddress = Main.multiplayerPanel2.globalCreateAddressTextField.getText().split(":");
        String CLIENT_ADDRESS = opponentAddress[0];
        int CLIENT_PORT = Integer.parseInt(opponentAddress[1]);
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.serverSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        this.serverSocket.setReuseAddress(true);
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        this.sendingData = "".getBytes();
        this.sendingPacket.setData(this.sendingData);
        this.serverSocket.send(this.sendingPacket);
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        System.out.println("waiting connection...");
        String[] tokens = Main.multiplayerPanel2.ipLabel.getText().split(":");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("<html>" + tokens[0] + "<br/>" + tokens[1] + "</html>");
        this.serverSocket.receive(this.receivingPacket);
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.serverSocket.send(this.sendingPacket);
        this.serverSocket.receive(this.receivingPacket);
        this.tetrominoesStackByte = this.receivingPacket.getData();
        if (this.tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(this.tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + this.receivingPacket.getSocketAddress() + "] connected");
        } else {
            this.serverSocket.close();
            System.exit(11);
        }

    }

    private void hamachiConnection() throws IOException {
        int port = '쀀';
        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];
        this.serverSocket = new DatagramSocket((SocketAddress)null);
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(port));
        this.receivingPacket = new DatagramPacket(this.receivingData, this.receivingData.length);
        System.out.println("waiting connection...");
        System.out.println("waiting nickname ");
        this.serverSocket.receive(this.receivingPacket);
        this.opponentName = (new String(this.receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        System.out.println(this.opponentName);
        System.out.println("get nickname ");
        String CLIENT_ADDRESS = this.receivingPacket.getAddress().getHostName();
        int CLIENT_PORT = this.receivingPacket.getPort();
        this.sendingPacket = new DatagramPacket(this.sendingData, this.sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        this.sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        this.sendingPacket.setData(this.sendingData);
        this.serverSocket.send(this.sendingPacket);
        this.serverSocket.receive(this.receivingPacket);
        this.tetrominoesStackByte = this.receivingPacket.getData();
        if (this.tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(this.tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + this.receivingPacket.getSocketAddress() + "] connected");
        } else {
            this.serverSocket.close();
            System.exit(11);
        }

    }

    public byte[] getTetrominoesStack() {
        return this.tetrominoesStackByte;
    }

    public void send(SendingObject sendingObject) throws IOException {
        this.sendingData = DataManager.convertToBytes(sendingObject);
        System.out.println(this.sendingData.length + "  Sending data length");
        this.sendingPacket.setData(this.sendingData);
        this.serverSocket.send(this.sendingPacket);
    }

    public SendingObject receive() throws IOException {
        this.serverSocket.receive(this.receivingPacket);
        return DataManager.getObjectFromBytes(this.receivingPacket.getData());
    }
}
