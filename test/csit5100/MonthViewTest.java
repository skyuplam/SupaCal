package csit5100;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonthViewTest {
	private SupaCal gui;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		SupaCal.loadMonth();
		SupaCal.loadContacts();
		gui = new SupaCal();
		gui.setVisible(false);
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
	}

	@Test
	public void testNextmonthBtn() {
		MonthView mv = SupaCal.monthView;
		Calendar cal = Calendar.getInstance();
		// click the next month button
		mv.nextMonth.doClick();
		int monthAfterClick = mv.currentMonth.getMonthInt();
		Assert.assertEquals("testNextmonthBtn", cal.get(Calendar.MONTH) + 1,
				monthAfterClick);
	}

	@Test
	public void testPreMonthBtn() {
		MonthView mv = SupaCal.monthView;
		Calendar cal = Calendar.getInstance();
		// click the previous month button
		mv.prevMonth.doClick();
		int monthAfterClick = mv.currentMonth.getMonthInt();
		Assert.assertEquals("testNextmonthBtn", cal.get(Calendar.MONTH) - 1,
				monthAfterClick);
	}

	@Test
	public void testDayClickedBtn() {
		MonthView mv = SupaCal.monthView;
		Calendar cal = Calendar.getInstance();
		mv.days[0].doClick();
	}

	@Test
	public void testResetViewBtn() {
		MonthView mv = SupaCal.monthView;
		Calendar cal = Calendar.getInstance();
		mv.backToCal.doClick();
	}

	@Test
	public void testLoadMonth() {
		MonthView mv = SupaCal.monthView;
		Calendar cal = Calendar.getInstance();
		mv.loadMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
		int curMonth = mv.currentMonth.getMonthInt();
		Assert.assertEquals("Load 2012 March", cal.get(Calendar.MONTH),
				curMonth);
	}
}
