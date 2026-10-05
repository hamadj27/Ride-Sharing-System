

public class DateTime implements IDateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
    	if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Invalid hour: " + hour);
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

 
    public int getYear() {
        return year;
    }

  
    public int getMonth() {
        return month;
    }

 
    public int getDay() {
        return day;
    }

  
    public int getHour() {
        return hour;
    }


    public int getMinute() {
        return minute;
    }

   
    public String format() {
        return String.format("%02d/%02d/%04d %02d:%02d", month, day, year, hour, minute);
    }

  
    public int compareTo(IDateTime other) {
        if (this.year != other.getYear()) {
            return this.year - other.getYear();
        }
        if (this.month != other.getMonth()) {
            return this.month - other.getMonth();
        }
        if (this.day != other.getDay()) {
            return this.day - other.getDay();
        }
        if (this.hour != other.getHour()) {
            return this.hour - other.getHour();
        }
        return this.minute - other.getMinute();
    }
}