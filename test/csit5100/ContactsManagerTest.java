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
		SupaCal.loadMonth();
		SupaCal.loadContacts();
		gui = new SupaCal();
		gui.setVisible(false);
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
	}

	// @Test
	// public void testSetupList() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testResetLeftSide() {
	// fail("Not yet implemented");
	// }

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

		assertEquals("Normal Save", beforeContactsCnt + 1, cm.contacts.size());

		cm.add.doClick();
		cm.fName.setText("");
		cm.save.doClick();
		assertEquals("Empty first name Save", beforeContactsCnt + 1,
				cm.contacts.size());

		cm.add.doClick();
		cm.fName.setText("test");
		cm.lName.setText("");
		cm.save.doClick();
		assertEquals("Empty last name Save", beforeContactsCnt + 1,
				cm.contacts.size());
	}

	@Test
	public void testRemove() {
		ContactsManager cm = SupaCal.contactsMgr;
		int beforeContactsCnt = cm.contacts.size();
		cm.add.doClick();
		cm.fName.setText("first Name");
		cm.lName.setText("last name");
		cm.addresses.setText("test address");
		cm.phoneNums.setText("test phone");
		cm.save.doClick();

		cm.remove.doClick();

		assertEquals("No Entry Selected", beforeContactsCnt + 1,
				cm.contacts.size());

		cm.list.setSelectedIndex(0);

		cm.remove.doClick();
		assertEquals("Remove the first item", beforeContactsCnt,
				cm.contacts.size());
	}

	@Test
	public void testEdit() {

		String fName = null;
		String lName = null;
		String phoneNums = null;
		String addresses = null;
		Contact selectedContact = null;

		ContactsManager cm = SupaCal.contactsMgr;
		int beforeContactsCnt = cm.contacts.size();
		cm.add.doClick();
		cm.fName.setText("first Name");
		cm.lName.setText("last name");
		cm.addresses.setText("test address");
		cm.phoneNums.setText("test phone");
		cm.save.doClick();
		cm.edit.doClick();
		cm.list.setSelectedIndex(0);
		if (cm.contacts.size() > 0) {
			selectedContact = cm.contacts.get(0);
			cm.edit.doClick();
			fName = selectedContact.getFirstName();
			lName = selectedContact.getLastName();
			addresses = selectedContact.getAddressesAsString();
			phoneNums = selectedContact.getPhonesAsString();
			assertEquals("Check Selected edit item", "first Name" + "last name"
					+ "test phone\n" + "test address\n", fName + lName
					+ phoneNums + addresses);

		} else {
			fail("The size contact list should not be 0");
		}

		cm.fName.setText("1 Name");
		cm.lName.setText("l name");
		cm.phoneNums.setText("t phone");
		cm.addresses.setText("t address");

		cm.save.doClick();

		if (cm.contacts.size() > 0) {
			selectedContact = cm.contacts.get(0);
			fName = selectedContact.getFirstName();
			lName = selectedContact.getLastName();
			phoneNums = selectedContact.getPhonesAsString();
			addresses = selectedContact.getAddressesAsString();
			assertEquals("Check edited item", "1 Name" + "l name" + "t phone\n"
					+ "t address\n", fName + lName + phoneNums + addresses);
		} else {
			fail("The size contact list should not be 0");
		}
	}

	@Test
	public void testCancelBtn() {
		ContactsManager cm = SupaCal.contactsMgr;
		cm.cancel.doClick();
	}

}
