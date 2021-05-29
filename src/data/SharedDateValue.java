package classfile.data;

import java.util.Calendar;

import classfile.calendar.DateSet;

public class SharedDateValue {
    //"current" prefixed variable: locates user's desired date (visual track)
    public static int currentYear;
    public static int currentMonth;
    public static int currentDate;
    //"today" prefixed variable:
    //   values that only changes by real time on user's window time setting
    public static int todayYear;
    public static int todayMonth;
    public static int todayDate;

    public static void setDefaultDate() {
        Calendar instance = Calendar.getInstance();
        SharedDateValue.currentYear = instance.get(Calendar.YEAR);
        SharedDateValue.currentMonth = instance.get(Calendar.MONTH);
        SharedDateValue.currentDate = instance.get(Calendar.DAY_OF_MONTH);

        //Values won't change once values are assigned.
        //On default, today date must be same as current date.
        SharedDateValue.todayYear = SharedDateValue.currentYear;
        SharedDateValue.todayMonth = SharedDateValue.currentMonth;
        SharedDateValue.todayDate = SharedDateValue.currentDate;
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
        DateSet todayDateSet = new DateSet(SharedDateValue.todayYear,
                                           SharedDateValue.todayMonth,
                                           SharedDateValue.todayDate);
        return todayDateSet;
    }

    public static DateSet getCurrentDateSet() {
        DateSet currentDateSet = new DateSet(SharedDateValue.currentYear,
                                             SharedDateValue.currentMonth,
                                             SharedDateValue.currentDate);
        return currentDateSet;
    }

    public static void setCurrentDateSet(DateSet targetDateSet) {
        SharedDateValue.currentYear = targetDateSet.getYear();
        SharedDateValue.currentMonth = targetDateSet.getMonth();
        SharedDateValue.currentDate = targetDateSet.getDate();
    }

    public void assertValue(DateSet dateSet) {
        try {
            assert dateSet.getYear() == currentYear;
            assert dateSet.getMonth() == currentMonth;
            assert dateSet.getDate() == currentDate;
        }  catch (AssertionError e) {
            System.out.println("SharedDateValue - Date info mismatch");
        }
    }
}
