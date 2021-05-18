package classfile.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarGenerator {

    /*
     *  - DAY INDEX VALUE -
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
	 */

     //Forms YYYYMM when month is added to the key

	public DateBlock startWeekday;
	public DateBlock lastWeekday;
	public DateBlock startWeekend;
	public DateBlock lastWeekend;
    private  boolean isFirstWeekend = false;
    private final int MAX_BLOCK_COUNT = 42;

    //dateSet contains year, month but not date
    public SingleCalendar createSingleCalendar(DateSet dateSet) {
        int actualDate = 1; //defualt starting date is 1.
        int year = dateSet.getYear();
        int month = dateSet.getMonth();
        int maxDates = getMaxDates(dateSet) - 1;
        int startDayIndex = getStartDay(dateSet) - 1;

        SingleCalendar newCalendarTable = new SingleCalendar(dateSet);

        for (int blockIndex = 0; blockIndex < MAX_BLOCK_COUNT; blockIndex++) {
            DateSet newDateSet = new DateSet(year, month, actualDate);
            DateBlock newDateBlock
              = new DateBlock(newCalendarTable, newDateSet);
            int dayIndex = newDateSet.getDayIndex();
            if(blockIndex < startDayIndex || actualDate > maxDates) {
                newCalendarTable.addEmptyBlock();
                continue;
            } else if (blockIndex == startDayIndex) {
                newDateBlock = setFirstDateBlock(newDateBlock, dayIndex);
            } else if (actualDate == maxDates) {
                newDateBlock = setLastDateBlock(newDateBlock, dayIndex);
            } else {
                newDateBlock = setOrdinaryDateBlock(newDateBlock, dayIndex);
            }
            newCalendarTable.addDateBlock(newDateBlock);
            ++actualDate;
        }
        return newCalendarTable;
    }

    private DateBlock setFirstDateBlock(DateBlock dateBlock, int dayIndex) {
        dateBlock.setFirstDate();
        if (isWeekend(dayIndex)) {
            dateBlock.setWeekend();
            if (!isFirstWeekend) {
                dateBlock.setFirstWeekend();
                startWeekend = dateBlock;
                isFirstWeekend = true;
            }
        } else {
            startWeekday = dateBlock;
        }
        return dateBlock;
    }

    private DateBlock setLastDateBlock(DateBlock dateBlock, int dayIndex) {
        dateBlock.setLastDate();
        if (isWeekend(dayIndex)) {
            dateBlock.setWeekend();
            lastWeekend = dateBlock;
        } else {
            lastWeekday = dateBlock;
        }
        return dateBlock;
    }

    private DateBlock setOrdinaryDateBlock(DateBlock dateBlock, int dayIndex) {
        if (isWeekend(dayIndex)) {
            dateBlock.setWeekend();
            if (!isFirstWeekend) {
                dateBlock.setFirstWeekend();
                startWeekend = dateBlock;
                isFirstWeekend = true;
            } else {
                lastWeekend = dateBlock;
            }
        } else {
            lastWeekday = dateBlock;
        }
        return dateBlock;
    }

    private boolean isWeekend(int dayIndex) {
        return (dayIndex == 0 || dayIndex == 6);
    }

    //returns a day index value of the 1st date - @see DAY INDEX VALUE.
	public int getStartDay (DateSet dateSet) {
        Calendar instance = Calendar.getInstance();
        instance.set(dateSet.getYear(),
                     dateSet.getMonthIndex(), 1);
        int startDayIndex = instance.get(Calendar.DAY_OF_WEEK) - 1;
		return startDayIndex;
	}

	//amount of dates in specified month and year - e.g. Feburary will return 28
	public int getMaxDates(DateSet dateSet) {
		Calendar instance = Calendar.getInstance();
		instance.set(dateSet.getYear(),
                             dateSet.getMonthIndex(), 1);
        int maximumdate = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maximumdate;
	}
}
