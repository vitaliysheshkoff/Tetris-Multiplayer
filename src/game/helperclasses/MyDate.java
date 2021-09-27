package game.helperclasses;
import java.io.Serializable;
import java.util.Comparator;

public class MyDate implements Comparable<MyDate>, Serializable {

    int day;
    int month;
    int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(MyDate o) {
        return Comparator.comparing(MyDate::getYear)
                .thenComparing(MyDate::getMonth)
                .thenComparingInt(MyDate::getDay)
                .compare(this, o);
    }

}
