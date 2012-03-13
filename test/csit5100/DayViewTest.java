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

	@Test
	public void testNewApt() {
		dayView.newApt.doClick();
		dayView.startHour.setSelectedIndex(0);
		dayView.startMinute.setSelectedIndex(0);
		dayView.endHour.setSelectedIndex(0);
		dayView.endMinute.setSelectedIndex(0);
		dayView.title.setText("Title");
		dayView.location.setText("Location");
		dayView.notes.setText("Notes");
		dayView.add.doClick();

		dayView.newApt.doClick();
		dayView.startHour.setSelectedIndex(0);
		dayView.startMinute.setSelectedIndex(0);
		dayView.endHour.setSelectedIndex(1);
		dayView.endMinute.setSelectedIndex(1);
		dayView.title.setText("Title");
		dayView.location.setText("Location");
		dayView.notes.setText("Notes");
		dayView.add.doClick();
	}

	@Test
	public void testDelApt() {
		dayView.newApt.doClick();
		dayView.startHour.setSelectedIndex(0);
		dayView.startMinute.setSelectedIndex(0);
		dayView.endHour.setSelectedIndex(1);
		dayView.endMinute.setSelectedIndex(1);
		dayView.title.setText("Title");
		dayView.location.setText("Location");
		dayView.notes.setText("Notes");
		dayView.add.doClick();
		dayView.aptList.setSelectedIndex(0);
		dayView.delApt.doClick();
	}

	@Test
	public void testEditApt() {
		dayView.aptList.setSelectedIndex(0);
		dayView.editApt.doClick();
		dayView.cancel.doClick();
	}
}
