package game.multiplayer.playfield.server;

import game.multiplayer.playfield.manager.DataManager;
import game.multiplayer.playfield.manager.SendingObject;
import game.multiplayer.stun.StunTest;
import game.start.Main;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    byte[] receivingData;
    byte[] sendingData;

    public DatagramSocket serverSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    private final byte[] tetrominoesStackByte;

    public String opponentName;

    public Server() throws IOException {

        String CLIENT_ADDRESS = Main.multiplayerPanel.createAddress;
        int CLIENT_PORT = Integer.parseInt(Main.multiplayerPanel.createPort);

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


        String[] tokens = Main.multiplayerPanel.ipLabel.getText().split(",");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("address: " + tokens[0] + " port: " + tokens[1] );

        // receive nickname
        serverSocket.receive(receivingPacket);
        opponentName = new String(receivingPacket.getData()).trim();

        // send nickname
        sendingData = Main.multiplayerPanel.nickname.getBytes();
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

    public synchronized void send(SendingObject sendingObject) throws IOException {
        sendingData = DataManager.convertToBytes(sendingObject);
        System.out.println(sendingData.length + "  Sending data length");
        sendingPacket.setData(sendingData);

        serverSocket.send(sendingPacket);

    }


    public synchronized SendingObject receive() throws IOException {

        serverSocket.receive(receivingPacket);

        return DataManager.getObjectFromBytes(receivingPacket.getData());
    }
}