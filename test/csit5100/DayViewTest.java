package csit5100;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DayViewTest {
	private SupaCal gui = null;
	private DayView dayView = null;
	private Day day = null;
	private SimpleDateFormat formater = new SimpleDateFormat("MMMM d - yyyy",
			Locale.ENGLISH);
	private String January = "January 1 - 2010";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gui = new SupaCal();

		day = new Day(formater.parse(January));
		dayView = new DayView(day);

		gui.setVisible(false);
	}

	@After
	public void tearDown() throws Exception {
		day = null;
		dayView = null;
		gui = null;
	}

	@Test
	public void testSetAptList() {
		dayView.setAptList();
	}

	@Test
	public void testSetContactList() {
		dayView.setContactList();
	}

	@Test
	public void testSetupAptView() {
		dayView.setupAptView();
	}

	@Test
	public void testResetEverything() {
		dayView.resetEverything();
	}

}
