package game.panels.tetris.multiplayer.preparepanel;

import java.io.Serializable;

public class TelegramSaver implements Serializable {
    private String nickname;
    private int port;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String address;

    public TelegramSaver(String nickname, String address, int port) {
        this.nickname = nickname;
        this.address = address;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
