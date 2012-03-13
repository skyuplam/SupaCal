package csit5100;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

//javacoffeebreak.com to use vector
//rgagnon need serializable to write/read saved object

/**
 * Day class manages all appointments for a given date. Uses Vector to do this.
 * Implements serializable so data may be saved.
 */
public class Day extends Vector<Appointment> implements Serializable {
	// generated serialVersionUID
	private static final long serialVersionUID = -3774819467156718041L;

	// our only class level variable
	// this is set to midnight of the date
	private Date theDate;

	/**
	 * Sets the day to the specified date. The date MUST be midnight of the
	 * selected day in order for the other classes to recognize it properly.
	 * 
	 * @param theDate
	 *            Midnight of the selected date.
	 */
	public Day(Date theDate) {
		this.theDate = theDate;
	} // end constructor

	/**
	 * Overrides the vector add method. This method adds the appointment to the
	 * correct spot, ordered by start time of the appointment. Since we are
	 * using a Vector, we simply run through the list in a linear insertion sort
	 * fashion. This method overrides the default vector.add method.
	 */
	public boolean add(Appointment apt) {
		// if the list doesn't have any elements in it, just add it to the front
		// otherwise, go through the list and find where it should go
		if (size() == 0)
			add(0, apt);
		else {
			for (int i = 0; i < size(); i++)
				if (apt.getStartTime().compareTo(get(i).getStartTime()) <= 0) {
					add(i, apt);
					break;
				}// if
				else if (i == size() - 1) {
					add(i + 1, apt);
					break;
				}// else if
		} // end else

		return true;
	} // end add

	/**
	 * Removes the selected appointment from the list.
	 * 
	 * @param entry
	 */
	public void removeAppointment(Appointment entry) {
		remove(entry);
	}// method removeAppointment

	public int getTotalAppointments() {
		return this.size();
	}// getTotalAppointments

	/**
	 * @return int Returns an integer representation of the current day in the
	 *         week. This is mainly used to organize the monthView of the
	 *         calendar.
	 */
	public int getDayOfWeek() {
		Calendar temp = Calendar.getInstance();
		temp.setTime(theDate);
		return temp.get(Calendar.DAY_OF_WEEK);
	} // end getDayOfWeek();

	/**
	 * Returns the value of the day in Date format, which is midnight for the
	 * current day.
	 * 
	 * @return Midnight of the current day.
	 */
	public Date getTheDate() {
		return theDate;
	}// method getTheDate

	/**
	 * Returns a String representation of the object. For debugging and display
	 * purposes.
	 */
	@SuppressWarnings("deprecation")
	public String toString() {
		int temp = theDate.getMonth();
		String str = "";
		switch (temp) {
		case Calendar.JANUARY:
			str = "January";
			break;
		case Calendar.FEBRUARY:
			str = "February";
			break;
		case Calendar.MARCH:
			str = "March";
			break;
		case Calendar.APRIL:
			str = "April";
			break;
		case Calendar.MAY:
			str = "May";
			break;
		case Calendar.JUNE:
			str = "June";
			break;
		case Calendar.JULY:
			str = "July";
			break;
		case Calendar.AUGUST:
			str = "August";
			break;
		case Calendar.SEPTEMBER:
			str = "September";
			break;
		case Calendar.OCTOBER:
			str = "October";
			break;
		case Calendar.NOVEMBER:
			str = "November";
			break;
		case Calendar.DECEMBER:
			str = "December";
			break;
		}// switch
		return str + " " + theDate.getDate() + " - "
				+ (theDate.getYear() + 1900);
	} // end toString

	/**
	 * Converts all appointments for the day to an array of strings. Strictly
	 * for display purposes within the GUI.
	 * 
	 * @return Array of Strings containing all appointments.
	 */
	public String[] aptsToStrings() {
		String[] str = new String[size()];

		for (int i = 0; i < str.length; i++)
			str[i] = get(i).toString();

		return str;
	} // end toStrings
}// class Day