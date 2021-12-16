package game.multiplayer.playfield.server;

import game.multiplayer.playfield.manager.DataManager;
import game.multiplayer.playfield.manager.SendingObject;
import game.multiplayer.stun.StunTest;
import game.start.Main;
import java.io.IOException;
import java.net.*;

public class Server {

    byte[] receivingData;
    byte[] sendingData;

    public DatagramSocket serverSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    private byte[] tetrominoesStackByte;

    public String opponentName;

    public Server() throws IOException {


        if(Main.multiplayerPanel2.isLocalGame){
            localConnection();
        }
        else
            inetConnection();
    }

    private void localConnection() throws IOException{
        int port = 65535;
        String CLIENT_ADDRESS;
        int CLIENT_PORT;

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.serverSocket = new DatagramSocket(port);

        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

        System.out.println("waiting connection...");

        System.out.println("waiting nickname ");
        // receive nickname
        serverSocket.receive(receivingPacket);
        opponentName = new String(receivingPacket.getData()).trim();
        System.out.println(opponentName);

        System.out.println("get nickname ");

        // send nickname

        CLIENT_ADDRESS = receivingPacket.getAddress().getHostName();
        CLIENT_PORT = receivingPacket.getPort();

        sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(CLIENT_ADDRESS), CLIENT_PORT);

        sendingData = Main.multiplayerPanel2.nickname.getBytes();
        sendingPacket.setData(sendingData);
        // for(int i = 0; i < 10; i++)
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

    private void inetConnection() throws IOException{
        // String CLIENT_ADDRESS = Main.multiplayerPanel.createAddress;
        String CLIENT_ADDRESS = Main.multiplayerPanel2.createAddress;
        //int CLIENT_PORT = Integer.parseInt(Main.multiplayerPanel.createPort);
        int CLIENT_PORT = Integer.parseInt(Main.multiplayerPanel2.createPort);

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


        //  String[] tokens = Main.multiplayerPanel.ipLabel.getText().split(",");
        String[] tokens = Main.multiplayerPanel2.ipLabel.getText().split(":");
        Main.tetrisPanelMultiplayer.tetrisPlayerNameLabel.setText("address: " + tokens[0] + " port: " + tokens[1] );

        // receive nickname
        serverSocket.receive(receivingPacket);
        opponentName = new String(receivingPacket.getData()).trim();

        // send nickname
        sendingData = Main.multiplayerPanel2.nickname.getBytes();
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