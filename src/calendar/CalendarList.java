package classfile.calendar;

import java.lang.Exception;

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
         Integer key = keyGenerator(dateSet);

         if (hasCalendar(dateSet)) {
             //TODO: Think about the event after detecting a duplication.
         } else {
             //if list has registered adjacent months
             //but no linkage exist in calendarTable, connect link.
             if (hasNextCalendar(dateSet)
                && !targetCalendar.hasNextCalendarLinked()) {
                 SingleCalendar nextCalendar
                    = getNextCalendar(dateSet);
                 targetCalendar.setNextCalendar(nextCalendar);
             }

             if (hasPreviousCalendar(dateSet)
                && !targetCalendar.hasPreviousCalendarLinked()) {
                 SingleCalendar previousCalendar
                   = getPreviousCalendar(dateSet);
                 targetCalendar.setPreviousCalendar(previousCalendar);
             }
             calendarTable.put(key, targetCalendar);
         }
     }

     //unused - consider removing
     public SingleCalendar getCalendarByDate(DateSet dateSet) {
         Integer key = keyGenerator(dateSet);
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     //unused - consider removing
     public SingleCalendar getCalendarByKey(Integer key) throws Exception {
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     private Integer keyGenerator(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getDate();
         Integer key = year * KEY_MUILTIPLIER + month;
         return key;
     }

     private boolean hasNextCalendar(DateSet dateSet) {
         DateSet tempDateSet = nextMonthSet(dateSet);
         if (hasCalendar(tempDateSet)) {
             return true;
         } else {
             return false;
         }
     }

     private boolean hasPreviousCalendar(DateSet dateSet) {
         DateSet tempDateSet = previousMonthSet(dateSet);
         if (hasCalendar(tempDateSet)) {
             return true;
         } else {
             return false;
         }
     }

     private boolean hasCalendar(DateSet dateSet) {
         int key = keyGenerator(dateSet);
         return calendarTable.containsKey(key);
     }

     //Null check before getting
     private SingleCalendar getNextCalendar(DateSet dateSet) {
         DateSet tempSet = nextMonthSet(dateSet);
         int key = keyGenerator(tempSet);
         return calendarTable.get(key);
     }

     //Null check before getting
     private SingleCalendar getPreviousCalendar(DateSet dateSet) {
         DateSet tempSet = previousMonthSet(dateSet);
         int key = keyGenerator(tempSet);
         return calendarTable.get(key);
     }

     private DateSet nextMonthSet(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         if (month == 0) { //if month is January
             --year;
             month = 11; //set to December
         } else {
             --month;
         }
         DateSet tempDateSet = new DateSet(year, month, 1);
         return tempDateSet;
     }

     private DateSet previousMonthSet(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         if (month == 0) { //if month is January
             --year;
             month = 11; //set to December
         } else {
             --month;
         }
         DateSet tempDateSet = new DateSet(year, month, 1);
         return tempDateSet;
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
