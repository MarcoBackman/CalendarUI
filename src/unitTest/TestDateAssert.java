package classfile.unitTest;

import java.lang.AssertionError;

import java.util.Calendar;
import java.util.Date;

import classfile.data.SharedDateValue;

/*
 *  - DAY INDEX VALUE -
 *  All numerical index values are subtracted by 1
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

public class TestDateAssert {
    int year;
    int month;
    int date;

    public TestDateAssert(int expectedYear,
                          int expectedMonth,
                          int expectedDate) {
         CheckTodayDate(expectedYear,
                        expectedMonth - 1,
                        expectedDate);

    }

    private void CheckTodayDate(int expectedYear,
                                int expectedMonth,
                                int expectedDate) {
        todyYearMatch(expectedYear);
        todyMonthMatch(expectedMonth);
        todyDateMatch(expectedDate);
    }

    private void todyYearMatch(int expectedYear) {
        int year = SharedDateValue.todayYear;
        try {
            assert year == expectedYear;
        } catch (AssertionError e) {
            System.out.println("\tYear mismatch. Expected Year:"
               + expectedYear + ". Actual Year: " + year);
        }
    }

    private void todyMonthMatch(int expectedMonth) throws AssertionError {
        int month = SharedDateValue.todayMonth;
        try {
            assert month == expectedMonth;
        }  catch (AssertionError e) {
            System.out.println("\tMonth mismatch. Expected Month:"
               + expectedMonth + ". Actual Month: " + month);
        }
    }

    private void todyDateMatch(int expectedDate) throws AssertionError {
        int date = SharedDateValue.todayDate;
        try {
            assert date == expectedDate;
        } catch (AssertionError e) {
            System.out.println("\tDate mismatch. Expected Date:"
               + expectedDate + ". Actual Date: " + date);
       }
    }

    public void testTotalDate(int expectedValue) {
        int date = 0;
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(year, month, date);
            date = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
            assert date == expectedValue;
        } catch (AssertionError e) {
            System.out.println("\tTotal Number of Date mismatch. Expected Value:"
               + expectedValue + ". Actual Value: " + date);
       }
    }

    public void testEntireDayMatch() {

    }

    private void testDayMatch(int targetDate, String expectedDay) {

    }

    public printSpecifiedDateInfo(int year, int month, int date) {
        String expectedDate = year + "/" + month + "/" + date;
        Date actualDate = null;
        int maximumDate = 0;
        int startDayIndex = 0;
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(year, month, date);

            actualDate = instance.getTime();
            maximumDate = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
            startDayIndex = instance.get(Calendar.DAY_OF_WEEK) - 1;

            assert false;
        } catch (AssertionError e) {
            String border = "----------------------------------";
            String header1 = "Target Date :"
            String header2 = "Actual Date"
            String header3 = "Total Date :"
            String header4 = "Starting Date :"
            String header5 = "Day :"
            System.out.println(border);
            System.out.printf("%-15s | 15s\n", header1, header2);
            System.out.printf("%-15s | 15s\n", header2, header2);
            System.out.println(border);
       }
    }
}
