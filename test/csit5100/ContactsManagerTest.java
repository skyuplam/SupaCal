package csit5100;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactsManagerTest {
	private SupaCal gui;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gui = new SupaCal();
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
	}

	@Test
	public void testSetupList() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetLeftSide() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		ContactsManager cm = SupaCal.contactsMgr;
		int beforeContactsCnt = cm.contacts.size();
		cm.add.doClick();
		cm.fName.setText("first Name");
		cm.lName.setText("last name");
		cm.addresses.setText("test address");
		cm.phoneNums.setText("test phone");
		cm.save.doClick();

		assertEquals("", beforeContactsCnt + 1, cm.contacts.size());
	}

}
