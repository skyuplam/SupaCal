package csit5100;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupaCalTest {
	private SupaCal cal = null;

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
		cal = new SupaCal();
		cal.setVisible(false);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testContactManagerView(){
		cal.viewCalendar.doClick();
	}
	
	@Test
	public void testManagerContactsView(){
		cal.manageContacts.doClick();
	}

	@Test
	public void testDeleteEmptyDays() {
		SupaCal.deleteEmptyDays();
	}

	@Test
	public void testLoadMonth() {
		SupaCal.loadMonth();
	}

	@Test
	public void testSaveMonth() {
		SupaCal.saveMonth();
	}

	@Test
	public void testLoadContacts() {
		SupaCal.loadContacts();
	}

	@Test
	public void testSaveContacts() {
		SupaCal.saveContacts();
	}
	
	@Test
	public void testCloseWindow(){
		cal.setDefaultCloseOperation(cal.EXIT_ON_CLOSE);
		WindowEvent closingEvent = new WindowEvent(cal, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
	}

}
