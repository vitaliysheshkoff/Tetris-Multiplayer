package game.panels.tetris.multiplayer.web.telegram;

import game.start.Main;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.Objects;

public class DataBaseClient {

    // Indexes of id list the same as indexes in combobox! @all_id == 0, @XXX == 1, ...
    public static ArrayList<String> idList;
    public static boolean isConnected;


    public DataBaseClient() {

        idList = new ArrayList<>();
        idList.add("all");

        try {

            //connection to server
            Socket socket = IO.socket(/*"http://localhost:8080"*/"https://salty-fjord-01783.herokuapp.com/");

            // send database request
            socket.on(Socket.EVENT_CONNECT, objects -> socket.emit("db"));

            // get database
            socket.on("db", objects -> {

                // 0 - users list
                // 1 - chats list

                if(!isConnected) {
                    getUsersFromJSON(objects[0]);
                    getChatsFromJSON(objects[1]);
                    isConnected = true;
                    System.out.println("successfully!");
                    socket.close();
                    System.out.println("socket close!");
                }

            });

            socket.connect();

            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    if (socket.connected()) {
                        isConnected = false;
                        System.out.println("socket close!");
                        socket.close();
                    }
                } catch (InterruptedException e) {
                    isConnected = false;
                    socket.close();
                }

            }).start();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void getUsersFromJSON(Object object) {
        String user_db_id;
        String user_name;
        String user_nickname;

        JSONArray jsonarray = null;

        try {
            jsonarray = new JSONArray(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String userNick;

        for (int i = 0; i < Objects.requireNonNull(jsonarray).length(); i++) {
            try {

                JSONObject jsonobject = jsonarray.getJSONObject(i);

                user_db_id = jsonobject.getString("user_db_id");
                user_name = jsonobject.getString("user_name");
                user_nickname = jsonobject.getString("user_nickname");

                /*if(user_nickname.equals("null") || user_nickname.equals("None") || user_nickname.isEmpty())
                    userNick = user_name;
                else userNick = user_name + "(" + user_nickname + ")";*/

                userNick = (!user_nickname.isEmpty()) ? (user_name + "(" + user_nickname + ")") : user_name;

                idList.add(user_db_id);
                Main.multiplayerPanel2.telegramUsers.addItem("@" + userNick);


                System.out.println(user_db_id);
                System.out.println(user_name);
                System.out.println(user_nickname);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
    }

    private void getChatsFromJSON(Object object) {
        String chat_db_id;
        String chat_full_name;

        JSONArray jsonarray = null;

        try {
            jsonarray = new JSONArray(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < Objects.requireNonNull(jsonarray).length(); i++) {
            try {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                chat_db_id = jsonobject.getString("chat_db_id");
                chat_full_name = jsonobject.getString("chat_full_name");

                idList.add(chat_db_id);
                Main.multiplayerPanel2.telegramUsers.addItem("[GROUP] @" + chat_full_name);

                System.out.println(chat_db_id);
                System.out.println(chat_full_name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
