package classfile.calendar;

import java.util.Calendar;
import java.util.Date;

import java.lang.StringBuilder;

/*
 *  - DAY INDEX VALUE -
 *  All numerical index values are subtracted by 1 from the original value
 *  DAY_OF_WEEK = 0 : Sunday
 *  DAY_OF_WEEK = 1 : Monday
 *  DAY_OF_WEEK = 2 : Tuesday
 *  DAY_OF_WEEK = 3 : Wednesday
 *  DAY_OF_WEEK = 4 : Thursday
 *  DAY_OF_WEEK = 5 : Friday
 *  DAY_OF_WEEK = 6 : Saturday
 *
 *  - Month INDEX VALUE -
 *  January : 0 ~ December : 11

 * Do not get confused with DateBlock.java
 */

public class DateSet implements Cloneable {
    private int year;
    private int month;
    private int date;
    private int dayIndex;
    private String day;

    public DateSet(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.day = setDay();
        //@Debugging
        //assertTimeInfo();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //Assign day string value based on given date info
    private String setDay() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.date);
        this.dayIndex = instance.get(Calendar.DAY_OF_WEEK) - 1;
        return CalendarCharacter.DAYS_IN_ENGLISH_LETTER[dayIndex];
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDate() {
        return this.date;
    }

    public int getDayIndex() {
        return this.dayIndex;
    }

    public String getDay(){
        return this.day;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-DateSet created- \n");
        sb.append("Year: ");
        sb.append(this.year);
        sb.append(" Month index: ");
        sb.append(this.month);
        sb.append(" Month: ");
        sb.append(getMonth());
        sb.append(" Date: ");
        sb.append(getDate());
        sb.append(" : ");
        sb.append(CalendarCharacter.MONTHS_IN_ENGLISH_SHORT[getMonth()]);
        sb.append(". Date: ");
        sb.append(this.date);
        sb.append(" Day: ");
        sb.append(this.day);
        sb.append("\n ------------------------------------- \n");
        return sb.toString();
    }

    private void assertTimeInfo() {
        try {
            assert false;
        } catch (AssertionError e) {
            System.out.println(toString());
        }
    }
}
