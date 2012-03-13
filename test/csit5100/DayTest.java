package csit5100;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DayTest {
	private Date today = new Date();
	private SimpleDateFormat formater = new SimpleDateFormat("MMMM d - yyyy",
			Locale.ENGLISH);

	Day day = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		day = new Day(today);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRemoveAppointment() {
		// fail("Not yet implemented");
		Appointment appt = new Appointment("Title", new Date(), new Date(),
				"location", "notes", null);
		Appointment appt2 = new Appointment("Title2", new Date(), new Date(),
				"location", "notes", null);
		day.add(appt);
		day.add(appt);
		day.add(appt2);
		day.add(appt2);
		day.add(appt);
		assertTrue("Test addAppointment", day.add(appt));
		day.removeAppointment(appt);
	}

	@Test
	public void testGetTotalAppointments() {
		assertEquals("Test getTotalAppointments", 0, day.getTotalAppointments());
	}

	@Test
	public void testGetDayOfWeek() {
		Calendar temp = Calendar.getInstance();
		temp.setTime(today);
		assertEquals("testGetDayOfWeek", temp.get(Calendar.DAY_OF_WEEK),
				day.getDayOfWeek());
	}

	@Test
	public void testGetTheDate() {
		assertEquals("testGetTheDate", today, day.getTheDate());
	}

	@Test
	public void testToString() throws ParseException {
		String January = "January 1 - 2010";
		String February = "February 1 - 2010";
		String March = "March 1 - 2010";
		String April = "April 1 - 2010";
		String May = "May 1 - 2010";
		String June = "June 1 - 2010";
		String July = "July 1 - 2010";
		String August = "August 1 - 2010";
		String September = "September 1 - 2010";
		String October = "October 1 - 2010";
		String November = "November 1 - 2010";
		String December = "December 1 - 2010";
		Date JanuaryDate = formater.parse(January);
		Date FebruaryDate = formater.parse(February);
		Date MarchDate = formater.parse(March);
		Date AprilDate = formater.parse(April);
		Date MayDate = formater.parse(May);
		Date JuneDate = formater.parse(June);
		Date JulyDate = formater.parse(July);
		Date AugustDate = formater.parse(August);
		Date SeptemberDate = formater.parse(September);
		Date OctoberDate = formater.parse(October);
		Date NovemberDate = formater.parse(November);
		Date DecemberDate = formater.parse(December);
		Day JanuaryDay = new Day(JanuaryDate);
		Day FebruaryDay = new Day(FebruaryDate);
		Day MarchDay = new Day(MarchDate);
		Day AprilDay = new Day(AprilDate);
		Day MayDay = new Day(MayDate);
		Day JuneDay = new Day(JuneDate);
		Day JulyDay = new Day(JulyDate);
		Day AugustDay = new Day(AugustDate);
		Day SeptemberDay = new Day(SeptemberDate);
		Day OctoberDay = new Day(OctoberDate);
		Day NovemberDay = new Day(NovemberDate);
		Day DecemberDay = new Day(DecemberDate);
		assertEquals("testToString", January, JanuaryDay.toString());
		assertEquals("testToString", February, FebruaryDay.toString());
		assertEquals("testToString", March, MarchDay.toString());
		assertEquals("testToString", April, AprilDay.toString());
		assertEquals("testToString", May, MayDay.toString());
		assertEquals("testToString", June, JuneDay.toString());
		assertEquals("testToString", July, JulyDay.toString());
		assertEquals("testToString", August, AugustDay.toString());
		assertEquals("testToString", September, SeptemberDay.toString());
		assertEquals("testToString", October, OctoberDay.toString());
		assertEquals("testToString", November, NovemberDay.toString());
		assertEquals("testToString", December, DecemberDay.toString());
	}

	@Test
	public void testAptsToStrings() throws ParseException {
		SimpleDateFormat formater2 = new SimpleDateFormat(
				"MMMM d - yyyy hh:mm", Locale.ENGLISH);
		String January = "January 1 - 2010 00:00";
		Date date = formater2.parse(January);
		Appointment appt = new Appointment("Title", date, date,
				"location", "notes", null);
		
		day.add(appt);
		for (int i = 0; i < day.aptsToStrings().length; i++) {
			assertEquals("testGetTheDate", appt.toString(),
					day.aptsToStrings()[i]);
		}
	}

}
