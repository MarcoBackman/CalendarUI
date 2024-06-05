package calendar;

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

     public SingleCalendar addNewMonth(DateSet dateSet) {
         SingleCalendar newCalendar;
         CalendarGenerator calendarGenerator
         = new CalendarGenerator();
         Integer key = keyGenerator(dateSet);
         if (hasCalendar(dateSet)) {
             SingleCalendar existingCalendar = getCalendarByDate(dateSet);
             existingCalendar = linkAdjacentCalendar(existingCalendar, dateSet);
             calendarTable.put(key, existingCalendar);
             return existingCalendar;
         } else {
             newCalendar = calendarGenerator.createSingleCalendar(dateSet);
             newCalendar = linkAdjacentCalendar(newCalendar, dateSet);
             calendarTable.put(key, newCalendar);
             return newCalendar;
         }
     }

     //if list has registered adjacent months
     //but no linkage exist in calendarTable, connect link.
     private SingleCalendar linkAdjacentCalendar(SingleCalendar targetCalendar,
                                                 DateSet dateSet) {
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

         return targetCalendar;
     }

     //unused - consider removing
     public SingleCalendar getCalendarByDate(DateSet dateSet) {
         Integer key = keyGenerator(dateSet);
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     //unused - consider removing
     private SingleCalendar getCalendarByKey(Integer key) throws Exception {
         SingleCalendar targetCalendar = calendarTable.get(key);
         return targetCalendar;
     }

     private boolean hasNextCalendar(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         Integer key = year * KEY_MUILTIPLIER + month;
         if (month == 11) { //if month is January
             int tempYear = (key / KEY_MUILTIPLIER) + 1;
             key = tempYear * KEY_MUILTIPLIER; //set to December
         } else {
            ++key;
         }

         return calendarTable.containsKey(key);
     }

     private boolean hasPreviousCalendar(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         Integer key = year * KEY_MUILTIPLIER + month;
         if (month == 11) { //if month is January
             int tempYear = (key / KEY_MUILTIPLIER) - 1;
             key = tempYear * KEY_MUILTIPLIER; //set to December
         } else {
            --key;
         }

         return calendarTable.containsKey(key);
     }

     private SingleCalendar getNextCalendar(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         Integer key = year * KEY_MUILTIPLIER + month;
         if (month == 0) { //if month is January
             int tempYear = (key / KEY_MUILTIPLIER) - 1;
             key = tempYear * KEY_MUILTIPLIER + 11; //set to December
         } else {
            --key;
         }

         return calendarTable.get(key);
     }

     private SingleCalendar getPreviousCalendar(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         Integer key = year * KEY_MUILTIPLIER + month;
         if (month == 0) { //if month is January
             int tempYear = (key / KEY_MUILTIPLIER) - 1;
             key = tempYear * KEY_MUILTIPLIER + 11; //set to December
         } else {
            --key;
         }

         return calendarTable.get(key);
     }

     private boolean hasCalendar(DateSet dateSet) {
         int key = keyGenerator(dateSet);
         return calendarTable.containsKey(key);
     }

     //If the date is 2021 May 5th, Key will produce: 202104 as a key value
     private int keyGenerator(DateSet dateSet) {
         int year = dateSet.getYear();
         int month = dateSet.getMonth();
         int key = year * KEY_MUILTIPLIER + month;
         return key;
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
