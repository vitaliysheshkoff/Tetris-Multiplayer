package game.panels.tetris.multiplayer.web;

import com.google.gson.Gson;
import game.panels.tetris.multiplayer.sockets.manager.SendingObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;

public class Client2 {

    private SendingObject receivingObject;

    private final int roomNumber;

    private final Gson gson;

    private final Socket socket;

    private boolean connectedToTheGame;
    private boolean disconnectedFromServer;

    private String opponentNickname;

    public Client2(int roomNumber, byte[] tetrominoesStackByte, String nickname) throws URISyntaxException {
        socket = IO.socket(/*"http://localhost:8080"*/"https://salty-fjord-01783.herokuapp.com/");

        this.roomNumber = roomNumber;

        connectedToTheGame = false;
        disconnectedFromServer = false;

        gson = new Gson();

        // connection
        socket.on(Socket.EVENT_CONNECT, objects -> socket.emit("join", roomNumber));

        // get join answer
        socket.on("success join", objects -> {
            System.out.println("successfully join to the room " + "[" + objects[0] + "]");

            //send nickname
            socket.emit("nickname", roomNumber, nickname);

            // send tetrominoes stack
            socket.emit("stack", roomNumber, gson.toJson(tetrominoesStackByte));

        });

        // get nickname
        socket.on("nickname", objects -> {
            opponentNickname = objects[0].toString();
            connectedToTheGame = true;
        });

        // get data
        socket.on("data", objects -> receivingObject = gson.fromJson(objects[0] + "", SendingObject.class));

        // disconnection
        socket.on(Socket.EVENT_DISCONNECT, objects -> {
            System.out.println("disconnect!");
            socket.disconnect();
        //    waiting = false;
            connectedToTheGame = false;
            disconnectedFromServer = true;
        });

        socket.connect();
    }

    public boolean isConnectedToTheServer() {
        return !socket.connected();
    }

    public void sendObject(SendingObject sendingObject) {
        socket.emit("data", roomNumber,
                gson.toJson(sendingObject));
    }

    public SendingObject receiveObject() {
        return receivingObject;
    }

    public int getRoomNumber() {
        return roomNumber;
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
    public boolean isDisconnectedFromServer() {
        return disconnectedFromServer;
    }
}
