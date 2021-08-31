package game.multiplayer.playfield.client;

import game.multiplayer.playfield.manager.DataManager;
import game.multiplayer.playfield.manager.SendingObject;
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

    public Client(byte[] tetrominoesStackByte, int port, String serverAddress) throws IOException {

        this.receivingData = new byte[4096];
        this.sendingData = new byte[4096];

        this.clientSocket = new DatagramSocket();
        this.receivingPacket = new DatagramPacket(receivingData, receivingData.length);

      //  SERVER_ADDRESS = InetAddress.getLocalHost().getCanonicalHostName();

        SERVER_ADDRESS = serverAddress;
        PORT = port;

        this.sendingPacket = new DatagramPacket(sendingData, sendingData.length, InetAddress.getByName(SERVER_ADDRESS), PORT);

        sendingData = tetrominoesStackByte;
        sendingPacket.setData(sendingData);
        clientSocket.send(sendingPacket);

        System.out.println("sent init message");


        clientSocket.setSoTimeout(50000);
        clientSocket.receive(receivingPacket);

        if (new String(receivingPacket.getData()).equals("connected")) {
            clientSocket.close();
            System.out.println(new String(receivingPacket.getData()).equals("connected"));
            System.exit(11);
        } else
            System.out.println("connection accept");
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