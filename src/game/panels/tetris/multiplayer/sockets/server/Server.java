package game.panels.tetris.multiplayer.sockets.server;

import game.panels.tetris.multiplayer.preparepanel.Multiplayer;
import game.panels.tetris.multiplayer.sockets.manager.DataManager;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
import game.panels.tetris.multiplayer.stun.StunTest;
import game.start.Main;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server {

    private byte[] receivingData;
    private byte[] sendingData;
    private byte[] tetrominoesStackByte;

    public DatagramSocket serverSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    public String opponentName;

    public Server() throws IOException {
        if (Main.multiplayerPanel2.typeOfGame == Multiplayer.LOCAL) {
            localConnection();
        } else if (Main.multiplayerPanel2.typeOfGame == Multiplayer.NET_HOLE_PUNCHING) {
            netHolePunching();
        } else {
            hamachiConnection();
        }
    }

    private void localConnection() throws IOException {
        int port = 65535;

        receivingData = new byte[4096];
        sendingData = new byte[4096];

        serverSocket = new DatagramSocket(null);
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(Inet4Address.getLocalHost().getHostAddress(), port));

        receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");

        serverSocket.receive(receivingPacket);
        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();

        System.out.println(opponentName);

        String CLIENT_ADDRESS = receivingPacket.getAddress().getHostName();

        int CLIENT_PORT = receivingPacket.getPort();

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();

        if (tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");
        } else {
            serverSocket.close();
            System.exit(11);
        }
    }

    private void netHolePunching() throws IOException {
        String[] opponentAddress = Main.multiplayerPanel2.globalCreateAddressTextField.getText().split(":");

        String CLIENT_ADDRESS = opponentAddress[0];

        int CLIENT_PORT = Integer.parseInt(opponentAddress[1]);

        receivingData = new byte[4096];
        sendingData = new byte[4096];

        serverSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        serverSocket.setReuseAddress(true);

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        sendingData = "".getBytes();
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        receivingPacket = new DatagramPacket(receivingData, receivingData.length);
        System.out.println("waiting connection...");

        String[] tokens = Main.multiplayerPanel2.ipLabel.getText().split(":");

        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("<html>" + tokens[0] + "<br/>" + tokens[1] + "</html>");

        serverSocket.receive(receivingPacket);
        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();
        if (tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");
        } else {
            serverSocket.close();
            System.exit(11);
        }
    }

    private void hamachiConnection() throws IOException {
        int port = 49152;

        receivingData = new byte[4096];
        sendingData = new byte[4096];

        serverSocket = new DatagramSocket(null);
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(port));

        receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");

        serverSocket.receive(receivingPacket);
        opponentName = (new String(receivingPacket.getData(), StandardCharsets.UTF_8)).trim();

        System.out.println(opponentName);

        String CLIENT_ADDRESS = receivingPacket.getAddress().getHostName();
        int CLIENT_PORT = receivingPacket.getPort();

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);
        sendingData = Main.multiplayerPanel2.nickname.getBytes(StandardCharsets.UTF_8);
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();

        if (tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7) {
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");
        } else {
            serverSocket.close();
            System.exit(11);
        }
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
