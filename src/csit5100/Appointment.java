package csit5100;

/**
 * @author B.A.D. Company
 * This class represents an appointment.
 * It is serializable so it can be saved. 
 * Relies heavily on the Date class.
 */

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
	// setting a generated serialVersionUID
	private static final long serialVersionUID = 1841793468748301933L;
	private String title; // title of the appointment
	private String location; // location of the appointment
	private String notes; // general notes on the appointment
	private Date startTime; // start time of the appointment
	private Date endTime; // end time of the appointment
	private ContactList attendees; // people attending appointment
	public static int NEVER = 0;
	public static int DAILY = 1;
	public static int WEEKLY = 2;
	public static int MONTHLY = 3;

	/**
	 * @param title
	 *            Title of the appointment.
	 * @param startTime
	 *            Start time of the appointment.
	 * @param endTime
	 *            End time of the appointment.
	 * @param location
	 *            Location fo the appointment.
	 * @param notes
	 *            General notes on the appointment.
	 * @param attendees
	 *            List of contacts attending the appointment. Loaded constructor
	 *            method. Creates a new appointment with the provided
	 *            information.
	 */
	public Appointment(String title, Date startTime, Date endTime,
			String location, String notes, ContactList attendees) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.notes = notes;
		this.attendees = attendees;
	} // end constructor

	/**
	 * @param title
	 *            Title of the appointment.
	 * @param startTime
	 *            Start time of the appointment.
	 * @param endTime
	 *            End time of the appointment.
	 * @param location
	 *            Location of the appointment.
	 * @param notes
	 *            Notes for the appointment. Creates an appointment with the
	 *            specified data. No attendees list is supplied, so it simply
	 *            creates an empty contacts list.
	 */
	public Appointment(String title, Date startTime, Date endTime,
			String location, String notes) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.notes = notes;
		this.attendees = new ContactList();
	} // end constructor

	// getter and setter methods ahead
	// most of these methods may not be used, but are here out of habit
	/**
	 * Returns the title of the appointment as a string.
	 * 
	 * @return Title of the appointment
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the appointment to the one provided.
	 * 
	 * @param title
	 *            Title of the appointment.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the start time of the appointment as a Date value.
	 * 
	 * @return Start time of the appointment.
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time of the appointment to the specified value.
	 * 
	 * @param startTime
	 *            Start time of the appointment.
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Returns the end time of the appointment as a Date value.
	 * 
	 * @return End time of the appointment.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time of the appointment to the Date value supplied.
	 * 
	 * @param endTime
	 *            End time of the appointment.
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the location of the appointment as a String.
	 * 
	 * @return Location of the appointment.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the appointment as a String.
	 * 
	 * @param location
	 *            Location of the appointment.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Returns the notes of the appointment as a String.
	 * 
	 * @return Notes of the appointment.
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes of the appointment to the String provided.
	 * 
	 * @param notes
	 *            Notes of the appointment.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Adds the specified contact as an attendee to this event.
	 * 
	 * @param attendee
	 *            Attendee to add to the event.
	 */
	public void addAttendees(Contact attendee) {
		attendees.add(attendee);
	}

	/**
	 * Removes the specified contact from the Attendee list.
	 * 
	 * @param attendee
	 *            Contact to remove.
	 */
	public void removeAttendees(Contact attendee) {
		attendees.remove(attendee);
	} // end getters and setters

	/**
	 * Returns the names of attendees that are attending the event as an array
	 * of strings.
	 * 
	 * @return Array of Strings of names attending the appointment.
	 */
	public String[] getAttendeesNames() {
		if ((attendees != null) && (attendees.size() > 0))
			return attendees.toStrings();
		else
			return new String[] { "" };
	} // end getAttendeesNames

	/**
	 * Returns a string representation of the appointment.
	 * 
	 * @return A string containing the start time, end time, title, and notes of
	 *         the selected appointment. Solely for listing purposes.
	 */
	@SuppressWarnings("deprecation")
	public String toString() {
		String startHour, startMin, endHour, endMin;
		startHour = startTime.getHours() + "";
		endHour = endTime.getHours() + "";
		if (startTime.getMinutes() < 10)
			startMin = "0" + startTime.getMinutes();
		else
			startMin = startTime.getMinutes() + "";
		if (endTime.getMinutes() < 10)
			endMin = "0" + endTime.getMinutes();
		else
			endMin = "" + endTime.getMinutes();

		return "(" + startHour + ":" + startMin + " - " + endHour + ":"
				+ endMin + ")  " + title + ": " + notes;
	} // end toString

}// class Appointment