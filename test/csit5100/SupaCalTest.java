package csit5100;

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
		cal = new SupaCal();

	}

	@After
	public void tearDown() throws Exception {
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

}
