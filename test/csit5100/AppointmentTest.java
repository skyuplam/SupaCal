/**
 * 
 */
package csit5100;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author TerrenceLam
 * 
 */
public class AppointmentTest {
	private Appointment appointment = null;
	private Appointment appointment2 = null;
	private ContactList contactList = null;
	private SimpleDateFormat formater = new SimpleDateFormat("MMMM d - yyyy",
			Locale.ENGLISH);
	private SimpleDateFormat formater2 = new SimpleDateFormat(
			"MMMM d - yyyy hh:mm", Locale.ENGLISH);
	private String January = "January 1 - 2010";
	private String January2 = "January 2 - 2010";
	private Date JanuaryDate = null;
	private Date JanuaryDate2 = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		JanuaryDate = formater.parse(January);
		JanuaryDate2 = formater.parse(January2);
		contactList = new ContactList();
		appointment = new Appointment("title", JanuaryDate, JanuaryDate2,
				"hong kong", "notes", contactList);
		appointment2 = new Appointment("title", JanuaryDate, JanuaryDate2,
				"hong kong", "notes");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		JanuaryDate = null;
		JanuaryDate2 = null;
		contactList = null;
		appointment = null;
		appointment2 = null;
	}

	/**
	 * Test method for {@link csit5100.Appointment#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		assertEquals("testGetTitle", "title", appointment.getTitle());
	}

	/**
	 * Test method for {@link csit5100.Appointment#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		appointment.setTitle("title 2");
		assertEquals("testSetTitle", "title 2", appointment.getTitle());
	}

	/**
	 * Test method for {@link csit5100.Appointment#getStartTime()}.
	 */
	@Test
	public void testGetStartTime() {
		assertEquals("testGetStartTime", JanuaryDate,
				appointment.getStartTime());
	}

	/**
	 * Test method for {@link csit5100.Appointment#setStartTime(java.util.Date)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testSetStartTime() throws ParseException {
		String December = "December 1 - 2010";
		Date DecemberDate = formater.parse(December);
		appointment.setStartTime(DecemberDate);
		assertEquals("testSetStartTime", DecemberDate,
				appointment.getStartTime());
	}

	/**
	 * Test method for {@link csit5100.Appointment#getEndTime()}.
	 */
	@Test
	public void testGetEndTime() {
		assertEquals("testGetEndTime", JanuaryDate2, appointment.getEndTime());
	}

	/**
	 * Test method for {@link csit5100.Appointment#setEndTime(java.util.Date)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testSetEndTime() throws ParseException {
		String December = "December 1 - 2010";
		Date DecemberDate = formater.parse(December);
		appointment.setEndTime(DecemberDate);
		assertEquals("testSetEndTime", DecemberDate, appointment.getEndTime());
	}

	/**
	 * Test method for {@link csit5100.Appointment#getLocation()}.
	 */
	@Test
	public void testGetLocation() {
		assertEquals("testGetLocation", "hong kong", appointment.getLocation());
	}

	/**
	 * Test method for
	 * {@link csit5100.Appointment#setLocation(java.lang.String)}.
	 */
	@Test
	public void testSetLocation() {
		appointment.setLocation("kowloon");
		assertEquals("testSetLocation", "kowloon", appointment.getLocation());
	}

	/**
	 * Test method for {@link csit5100.Appointment#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		assertEquals("testGetNotes", "notes", appointment.getNotes());
	}

	/**
	 * Test method for {@link csit5100.Appointment#setNotes(java.lang.String)}.
	 */
	@Test
	public void testSetNotes() {
		appointment.setNotes("notes 2");
		assertEquals("testSetNotes", "notes 2", appointment.getNotes());
	}

	/**
	 * Test method for
	 * {@link csit5100.Appointment#addAttendees(csit5100.Contact)}.
	 */
	@Test
	public void testAddAttendees() {
		for (String s : appointment2.getAttendeesNames())
			assertEquals("test null attendees", "", s);

		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		appointment.addAttendees(contact);
		for (String s : appointment.getAttendeesNames())
			assertEquals("testAddAttendees", "lastName0, firstName0", s);
	}

	/**
	 * Test method for
	 * {@link csit5100.Appointment#removeAttendees(csit5100.Contact)}.
	 */
	@Test
	public void testRemoveAttendees() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		appointment.addAttendees(contact);
		appointment.removeAttendees(contact);
		for (String s : appointment.getAttendeesNames())
			assertEquals("testAddAttendees", "", s);
	}

	/**
	 * Test method for {@link csit5100.Appointment#toString()}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testToString() throws ParseException {
		assertEquals("testAddAttendees", "(0:00 - 0:00)  title: notes",
				appointment2.toString());
		Date date = formater2.parse("January 1 - 2010 10:20");
		appointment.setStartTime(date);
		appointment.setEndTime(date);
		assertEquals("testAddAttendees", "(10:20 - 10:20)  title: notes",
				appointment.toString());
	}

}
