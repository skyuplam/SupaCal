package csit5100;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonthTest {
	private Month month = null;
	private SimpleDateFormat formater = new SimpleDateFormat("MMMM d - yyyy",
			Locale.ENGLISH);
	private SimpleDateFormat formater2 = new SimpleDateFormat(
			"MMMM d - yyyy hh:mm", Locale.ENGLISH);
	private String January = "January 1 - 2010";
	private String January2 = "January 2 - 2010";
	private Date JanuaryDate = null;
	private Date JanuaryDate2 = null;
	private Calendar theMonth = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		month = new Month();
		theMonth = new GregorianCalendar();
		JanuaryDate = formater.parse(January);
		JanuaryDate2 = formater.parse(January2);
	}

	@After
	public void tearDown() throws Exception {
		month = null;
		theMonth = null;
		JanuaryDate = null;
		JanuaryDate2 = null;
	}

	@Test
	public void testMonth() {
		assertTrue("testMonth", month != null);
	}

	@Test
	public void testMonthIntInt() {
		Calendar theMonth = new GregorianCalendar(11, 11, 1);
		month = new Month(11, 11);
		assertEquals("test month init", 11, month.getMonthInt());
	}

	@Test
	public void testGetDay() {
		// assertEquals("testGetDay not found",null,month.getDay(JanuaryDate));
		Date today = new Date();
		Day day = new Day(today);
		assertEquals("testGetDay found", null, month.getDay(JanuaryDate));
	}

	@Test
	public void testGetFirstDay() {
		theMonth.set(Calendar.DAY_OF_MONTH, 1);
		assertEquals("testGetFirstDay found",
				theMonth.get(Calendar.DAY_OF_WEEK), month.getFirstDay());
	}

	@Test
	public void testGetMonthInt() {
		;
		assertEquals("testGetMonthInt found", theMonth.get(Calendar.MONTH),
				month.getMonthInt());
	}

	@Test
	public void testGetDays() {
		;
		assertEquals("testGetDays found", 31, month.getDays());
	}

	@Test
	public void testGetYear() {
		assertEquals("testGetDays found", theMonth.get(Calendar.YEAR),
				month.getYear());
	}

	@Test
	public void testToString() {
		month.toString();
		assertEquals("testToString found", month.MonthAsString() + " - "
				+ theMonth.get(Calendar.YEAR), month.toString());
	}

	@Test
	public void testMonthAsString() {
		month.MonthAsString();
		assertEquals("testMonthAsString found", theMonth.getDisplayName(
				Calendar.MONTH, Calendar.LONG, Locale.US),
				month.MonthAsString());
	}

	@Test
	public void testSetDate() {
		month.setDate(11, 6, 11);
		assertEquals("testGetDays found", 6, month.getMonthInt());
	}

}
