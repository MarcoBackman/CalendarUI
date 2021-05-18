package classfile.calendar;

import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarList {
    //Key multiplier makes key format to 'YYYYMM' that causes no unintended duplication
    private final int KEY_MUILTIPLIER = 100;
    String calendarType;
    public static Hashtable <Integer, SingleCalendar> calendarTable
     = new Hashtable<Integer, SingleCalendar>();

     CalendarList nextCalendarType;
     CalendarList previousCalendarType;

     public void addNewMonth(SingleCalendar targetCalendar, DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getDate();
         Integer key = year * KEY_MUILTIPLIER + month;

         if (!calendarTable.containsKey(key)) {
             calendarTable.put(key, targetCalendar);
         } else {
             //TODO: Think about the event after detecting a duplication.
         }
     }

     public SingleCalendar getCalendarByDate(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getDate();
         Integer key = year * KEY_MUILTIPLIER + month;
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     public SingleCalendar getCalendarByKey(int key) {
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     public void setType(String type) {
         this.calendarType = type;
     }

     public String getType() {
         return this.calendarType;
     }

     public void setPreviousType(CalendarList previous) {
         this.previousCalendarType = previous;
     }

     public void setNextType(CalendarList next) {
         this.nextCalendarType = next;
     }
}
