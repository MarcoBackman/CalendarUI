package calendar;

import constant.DayEnum;
import constant.MonthEnum;

import java.util.Calendar;

import java.lang.StringBuilder;

public class DateSet implements Cloneable {
    private final int year;
    private final int month;
    private final int date;
    private final int dayIndex;
    private final String dayChar;

    public DateSet(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;

        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.date);

        this.dayIndex = instance.get(Calendar.DAY_OF_WEEK) - 1;
        this.dayChar = DayEnum.getSingleLetterByNumCode(dayIndex);
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

    public String getDayChar(){
        return this.dayChar;
    }

    public int getDayIndex() {
        return this.dayIndex;
    }

    public String toString() {
        return String.format("Date set created. Year: %d, Month: %d, Date: %d, Code: %s, Day: %s",
                year, month, date, MonthEnum.getShortMonthStrByNumberCode(getMonth()), dayChar);
    }

    @Override
    public DateSet clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (DateSet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
