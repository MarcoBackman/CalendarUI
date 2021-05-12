package classfile.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarGenerator {

    /*
     *  - DAY INDEX VALUE -
	 *  DAY_OF_WEEK = 1 : Sunday
	 *  DAY_OF_WEEK = 2 : Monday
	 *  DAY_OF_WEEK = 3 : Tuesday
	 *  DAY_OF_WEEK = 4 : Wednesday
	 *  DAY_OF_WEEK = 5 : Thursday
	 *  DAY_OF_WEEK = 6 : Friday
	 *  DAY_OF_WEEK = 7 : Saturday
	 */

	Calendar calendar;

    //will be needed afterward
	Date startWeekday;
	Date endWeekday;
	Date startWeekend;
	Date endWeekend;

	public CalendarGenerator() {

	}

    //returns a day index value of the 1st date - @see DAY INDEX VALUE.
	public int getStartDay (DateSet dateSet) {
        Calendar instance = Calendar.getInstance();
        instance.set(dateSet.year, dateSet.month - 1, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

    //get a day index value of the specified date - @see DAY INDEX VALUE.
	public int getDay(DateSet dateSet) {
        Calendar instance = Calendar.getInstance();
        instance.set(dateSet.year, dateSet.month - 1, dateSet.date);
		return instance.get(Calendar.DAY_OF_WEEK);
	}

	//amount of dates in specified month and year - e.g. Feburary will return 28
	public int getMaxDates(DateSet dateSet) {
		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.set(dateSet.year, dateSet.month - 1, 1);
		return calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public Date dateInfo(DateSet dateSet) {
		calendar = Calendar.getInstance();
		//January : 0 ~ December : 11
		calendar.set(dateSet.year, dateSet.month - 1, 1);
		return calendar.getTime();
	}

	public Date dateInfo(DateSet dateSet) {
		calendar = Calendar.getInstance();
		//January : 0 ~ December : 11
		calendar.set(dateSet.year, dateSet.month - 1, dateSet.date);
		return calendar.getTime();
	}

    //Rename HashDoublyLinkedList
	public HashDoublyLinkedList generateCalendar(DateSet dateSet) {
        return null;
	}
}
