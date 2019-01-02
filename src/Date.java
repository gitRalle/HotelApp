import java.io.Serializable;

public class Date implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {return year;}
    public int getMonth() {return month;}
    public int getDay() {return day;}
    public int getHour() {return hour;}
    public int getMinute() {return minute;}

    public String toString() {
        return String.format("%d/%02d/%02d", year, month, day);
    }

}
