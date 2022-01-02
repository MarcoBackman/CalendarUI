package classfile.calendar;

/*
 *  - DAY INDEX VALUE -
 *  All incoming numerical index values must be subtracted by 1
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

public class CalendarCharacter {
    public static final String[] DAYS_IN_ENGLISH_LETTER
        = {"S", "M", "T", "W", "H", "F", "S"};
    public static final String[] DAYS_IN_ENGLISH_SHORT
        = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    public static final String[] DAYS_IN_ENGLISH_LONG
        = {"SUNDAY",
          "MONDAY",
          "TUESDAY",
          "WEDNESDAY",
          "THURSDAY",
          "FRIDAY",
          "SATURDAY"
    };

    public static final String[] MONTHS_IN_ENGLISH_SHORT
        = {"JAN",
           "FEB",
           "MAR",
           "APR",
           "MAY",
           "JUN",
           "JUL",
           "AUG",
           "SEP",
           "OCT",
           "NOV",
           "DEC"
          };

    public static final String[] MONTHS_IN_ENGLISH_LONG

        = {"JANUARY",
           "FEBURARY",
           "MARCH",
           "APRIL",
           "MAY",
           "JUNE",
           "JULY",
           "AUGUST",
           "SEPTEMBER",
           "OCTOBER",
           "NOVEMBER",
           "DECEMBER"
          };
}
