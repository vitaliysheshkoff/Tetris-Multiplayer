package game.multiplayer.playfield.client;

import game.multiplayer.playfield.manager.DataManager;
import game.multiplayer.playfield.manager.SendingObject;
import game.multiplayer.stun.StunTest;
import game.start.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    private static String SERVER_ADDRESS =""/*"192.168.100.5"*//*"192.168.43.66"*//* "localhost"*//*"192.168.43.218"*/;
    private static  int PORT  /*57880*/;

    public byte[] receivingData;
    public byte[] sendingData;

    public DatagramSocket clientSocket;

    public DatagramPacket receivingPacket;
    public DatagramPacket sendingPacket;

    public String opponentName = "";

    public Client(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.clientSocket = new DatagramSocket(StunTest.INTERNAL_PORT);
        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);


        SERVER_ADDRESS = serverAddress;
        PORT = port;

        this.sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(SERVER_ADDRESS), PORT);

        // send nickname
        sendingData = Main.multiplayerPanel.nickname.getBytes();
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        //receive opponent name
        clientSocket.receive(receivingPacket);
        System.out.println("init message from server[" + receivingPacket.getAddress().getHostName() + ":" + receivingPacket.getPort() + "]");
        opponentName = new String(receivingPacket.getData()).trim();

        // send tetrominoes stack
        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

    }


    public synchronized void send(SendingObject sendingObject) throws IOException {
        sendingData = DataManager.convertToBytes(sendingObject);
        System.out.println(sendingData.length + "  Sending data length");
        sendingPacket.setData(sendingData);

        clientSocket.send(sendingPacket);

    }

    public synchronized SendingObject receive() throws IOException {

        clientSocket.receive(receivingPacket);

        return DataManager.getObjectFromBytes(receivingPacket.getData());
    }
}