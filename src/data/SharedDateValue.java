package data;

import java.util.Calendar;

import calendar.DateSet;

public class SharedDateValue {
    //"current" prefixed variable: locates user's desired date (visual track)
    private static int currentYear;
    private static int currentMonth;
    private static int currentDate;
    //"today" prefixed variable:
    //   values that only changes by real time on user's window time setting
    private static int todayYear;
    private static int todayMonth;
    private static int todayDate;

    static {
        Calendar instance = Calendar.getInstance();
        SharedDateValue.currentYear = instance.get(Calendar.YEAR);
        SharedDateValue.currentMonth = instance.get(Calendar.MONTH);
        SharedDateValue.currentDate = instance.get(Calendar.DAY_OF_MONTH);

        //Values won't change once values are assigned.
        //On default, today date must be same as current date.
        SharedDateValue.todayYear = instance.get(Calendar.YEAR);
        SharedDateValue.todayMonth = instance.get(Calendar.MONTH);
        SharedDateValue.todayDate = instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTodayYear() {
        return todayYear;
    }

    public static int getTodayMonth() {
        return todayMonth;
    }

    public static int getTodayDate() {
        return todayDate;
    }

    public static DateSet getTodayDateSet() {
        return new DateSet(SharedDateValue.todayYear,
                                           SharedDateValue.todayMonth,
                                           SharedDateValue.todayDate);
    }

    public static DateSet getCurrentDateSet() {
        return new DateSet(SharedDateValue.currentYear,
                                             SharedDateValue.currentMonth,
                                             SharedDateValue.currentDate);
    }

    public static void setCurrentDateSet(DateSet targetDateSet) {
        SharedDateValue.currentYear = targetDateSet.getYear();
        SharedDateValue.currentMonth = targetDateSet.getMonth();
        SharedDateValue.currentDate = targetDateSet.getDate();
    }

    public static void shiftNextYear() {
        ++SharedDateValue.currentYear;
    }

    public static void shiftPreviousYear() {
        --SharedDateValue.currentYear;
    }

    public static void shiftNextMonth() {
        if (currentMonth == 11) { //if December
            ++SharedDateValue.currentYear;
            SharedDateValue.currentMonth = 0; //January
        } else {
            ++SharedDateValue.currentMonth;
        }
        SharedDateValue.currentDate = 1;
    }

    public static void shiftPreviousMonth() {
        if (currentMonth == 0) { //if January
            --SharedDateValue.currentYear;
            SharedDateValue.currentMonth = 11; //December
        } else {
            --SharedDateValue.currentMonth;
        }
        SharedDateValue.currentDate = 1;
    }
}
