package csit5100;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.Locale;

/**
 * @author B.A.D. Company This class is designed to represent a single calendar
 *         month. It may be used to represent as much time as you would like,
 *         however if it is used too extensively load times and memory usage may
 *         increase significantly. It is recomended to treat this as a single
 *         month of time.
 */
public class Month extends Vector<Day> implements Serializable {
	// generated serialVersionUID
	private static final long serialVersionUID = -8845110198140895682L;
	private Calendar theMonth;

	/**
	 * Default constructor method. Creates a new gregorian calendar.
	 */
	public Month() {
		theMonth = new GregorianCalendar();
	} // end constructor

	/**
	 * @param year
	 *            Current Year as integer.
	 * @param month
	 *            Current Month as integer (0 being January, 11 being December)
	 *            Creates a new gregorian calendar based on the year and month
	 *            being passed in. Sets the date to the first day of the month.
	 */
	public Month(int year, int month) {// pass in month and year as int
		theMonth = new GregorianCalendar(year, month, 1);// 1 just makes it
															// first day of
															// month
	}// constructor

	/**
	 * @param dateOfDay
	 *            The date value for midnight of the day that we are looking
	 *            for.
	 * @return Returns the day that we are looking for, or null if it is not
	 *         found.
	 */
	public Day getDay(Date dateOfDay) {
		for (int i = 0; i < size(); i++)
			if (dateOfDay.compareTo(get(i).getTheDate()) == 0)
				return get(i);
		return null;
	} // end getDay

	/**
	 * Gets the first day (as in Sunday, Monday, etc) of the month and returns
	 * it. This is mainly used to determine the day of the week the month is
	 * starting on so we can design our monthView accordingly.
	 * 
	 * @return Day of the week for the first day in the month, as an integer.
	 */
	public int getFirstDay() {// just gets first day of the month and returns it
		theMonth.set(Calendar.DAY_OF_MONTH, 1);
		return theMonth.get(Calendar.DAY_OF_WEEK);
	}// method getDay

	/**
	 * Returns the month's value as an integer. 0 being January, 11 being
	 * December.
	 * 
	 * @return Returns an integer representing the month's value.
	 */
	public int getMonthInt() {
		return theMonth.get(Calendar.MONTH);
	} // end getMonthInt()

	/**
	 * Returns the number of days in this month.
	 * 
	 * @return Number of days for the month
	 */
	public int getDays() {// get the number of days in a month
		return theMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
	}// getDays

	/**
	 * Returns the current year.
	 * 
	 * @return Current year as integer.
	 */
	public int getYear() {// get the current year
		return theMonth.get(Calendar.YEAR);
	}// getYear

	/**
	 * Returns a String representation of the month. Month - Year.
	 * 
	 * @return String representation of month and year.
	 */

	public String toString() {
		String str = MonthAsString();
		return str + " - " + theMonth.get(Calendar.YEAR);
	} // end toString

	/**
	 * @return Returns string representing the month's value. Returns the
	 *         month's value as a string. May, March, etc
	 */
	public String MonthAsString() {// returns month as a String
		return theMonth
				.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	}// getMonth

	/**
	 * Sets the current Date of the calendar to the given values.
	 * 
	 * @param year
	 *            Year to be set to.
	 * @param month
	 *            Month to be set to.
	 * @param day
	 *            Day to be set to.
	 */
	public void setDate(int year, int month, int day) {
		theMonth = new GregorianCalendar(year, month, day);
	} // end setDate
}// class Month