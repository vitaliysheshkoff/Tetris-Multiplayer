package game.serial;
import game.helperclasses.MyDate;

import java.io.Serializable;
import java.util.Comparator;

public class LeaderBoardSaver implements Comparable<LeaderBoardSaver>, Serializable {

    private String nickname = "player";
    private long score;
    private MyDate date;
    private int level;

    public LeaderBoardSaver(String nickname, long score, MyDate date, int level) {
        this.nickname = nickname;
        this.score = score;
        this.date = date;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LeaderBoardSaver() {
        this.date = new MyDate(0, 0, 0);
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public int compareTo(LeaderBoardSaver o) {
        return Comparator.comparing(LeaderBoardSaver::getScore)
                .thenComparing(LeaderBoardSaver::getDate)
                .thenComparing(LeaderBoardSaver::getNickname)
                .compare(this, o);
    }
}
