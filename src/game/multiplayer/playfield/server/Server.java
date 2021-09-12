package game.multiplayer.playfield.server;

import game.multiplayer.playfield.client.Client;
import game.multiplayer.playfield.manager.DataManager;
import game.multiplayer.playfield.manager.SendingObject;
import game.multiplayer.stun.StunTest;
import game.start.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;

public class Server {

    private static String CLIENT_ADDRESS = "";
    private static String SERVER_ADDRESS = "";
    //  private static final int PORT = 57880;

    private static int SERVER_PORT;
    private static int CLIENT_PORT;

    byte[] receivingData;
    byte[] sendingData;

    public DatagramSocket serverSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    private byte[] tetrominoesStackByte;

    public Server() throws IOException {




        CLIENT_ADDRESS = Main.multiplayerPanel.createAddress; /*tokens[0];*/
        CLIENT_PORT = Integer.parseInt(Main.multiplayerPanel.createPort);/* Integer.parseInt(tokens[1]);*/

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.serverSocket = new DatagramSocket(/*0*/StunTest.INTERNAL_PORT);
        this.serverSocket.setReuseAddress(true);

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);

        //send init message
        sendingData = "".getBytes();
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");


        String[] tokens = Main.multiplayerPanel.ipLabel.getText().split(",");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("address: " + tokens[0]/*Inet4Address.getLocalHost().getHostAddress()*/ + " port: " + tokens[1] /*String.valueOf(serverSocket.getLocalPort())*/);


        // get init message

        serverSocket.receive(receivingPacket);
        tetrominoesStackByte = receivingPacket.getData();

        if (!(tetrominoesStackByte[499] >= 0 && Byte.toUnsignedInt(tetrominoesStackByte[499]) < 7)) {
            serverSocket.close();
            System.exit(11);
        } else
            System.out.println("client[" + receivingPacket.getSocketAddress() + "] connected");






        //  CLIENT_ADDRESS = receivingPacket.getAddress().getHostAddress();

        //  CLIENT_ADDRESS =receivingPacket.getAddress().getCanonicalHostName();

        /*CLIENT_ADDRESS = receivingPacket.getAddress().getHostName();

        System.out.println(CLIENT_ADDRESS);


        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), receivingPacket.getPort());


        sendingData = "connected".getBytes();
        sendingPacket.setData(sendingData);
        serverSocket.send(sendingPacket);

        System.out.println("send accept message");*/

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