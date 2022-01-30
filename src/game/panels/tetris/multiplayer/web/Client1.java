package game.panels.tetris.multiplayer.web;

import java.net.URISyntaxException;
import com.google.gson.Gson;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
import io.socket.client.IO;
import io.socket.client.Socket;

public class Client1 {

    private final int roomNumber;

    private SendingObject receivingObject;

    private final Socket socket;

    private final Gson gson;

    private boolean connectedToTheGame;

    private byte[] tetrominoesStackByte = null;

    private String opponentNickname;

    public Client1(int roomNumber, String nickname) throws URISyntaxException {

        socket = IO.socket(/*"http://localhost:8080"*/"https://salty-fjord-01783.herokuapp.com/");

        gson = new Gson();

        this.roomNumber = roomNumber;
        connectedToTheGame = false;


        //connection
        socket.on(Socket.EVENT_CONNECT, objects -> socket.emit("create", roomNumber));

        //get creation answer
        socket.on("success creation", objects -> System.out.println("room " + "[" + objects[0] + "]" + " created successfully"));

        // get nickname
        socket.on("nickname", objects -> {
            opponentNickname = objects[0].toString();

            // send my nickname
            socket.emit("nickname", roomNumber,
                    nickname);
        });

        // get tetrominoes stack
        socket.on("stack", objects -> {
            tetrominoesStackByte = gson.fromJson(objects[0].toString(), byte[].class);
            connectedToTheGame = true;
        });

        //get data
        socket.on("data", objects -> receivingObject = gson.fromJson(objects[0] + "", SendingObject.class));

        // disconnection
        socket.on(Socket.EVENT_DISCONNECT, objects -> {
            System.out.println("disconnect!");
            socket.disconnect();

            connectedToTheGame = false;
        });

        socket.connect();
    }

    public boolean isConnectedToTheServer() {
        return socket.connected();
    }

    public byte[] getTetrominoesStackByte() {
        return tetrominoesStackByte;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void sendObject(SendingObject sendingObject){
        socket.emit("data", roomNumber,
                gson.toJson(sendingObject));
    }

    public SendingObject receiveObject(){
        return receivingObject;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isConnectedToTheGame() {
        return connectedToTheGame;
    }

    public String getOpponentNickname() {
        return opponentNickname;
    }

}
