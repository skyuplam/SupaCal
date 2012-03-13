package csit5100;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	// public void testContact() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testGetFirstName() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("First name should be firstName0", "firstName0",
				contact.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		// fail("Not yet implemented");
		Contact contact = new Contact("firstName0", "lastName0", null, null);
		contact.setFirstName("another name");
		assertEquals("First name should be another name", "another name",
				contact.getFirstName());
	}

	@Test
	public void testGetLastName() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("Last name should be lastName0", "lastName0",
				contact.getLastName());
	}

	@Test
	public void testSetLastName() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.setLastName("Last name");
		assertEquals("Last name should be Last name", "Last name",
				contact.getLastName());
	}

	@Test
	public void testAddAddress() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		LinkedList<String> phoneNumbers = new LinkedList<String>();

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.addAddress("address1");
		assertEquals("Address should be address1", "address1",
				contact.getAddress(0));
	}

	@Test
	public void testGetAddress() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		LinkedList<String> phoneNumbers = new LinkedList<String>();

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.addAddress("address1");
		assertEquals("Address should be", null, contact.getAddress(-1));
		assertEquals("Address should be address1", null, contact.getAddress(2));
		assertEquals("Address should be address1", "address1",
				contact.getAddress(0));
	}

	@Test
	public void testAddNumber() {
		LinkedList<String> addresses = new LinkedList<String>();
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.addNumber("1234");
		assertEquals("Phone number should be", "1234\n",
				contact.getPhonesAsString());
	}

	@Test
	public void testRemoveNumber() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.addNumber("9999");
		contact.removeNumber("9999");
		assertEquals("Phone number should be", "phone 1\nphone 2\n",
				contact.getPhonesAsString());
	}

	@Test
	public void testGetNumber() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");
		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("Number should be", "phone 1", contact.getNumber(0));
	}

	@Test
	public void testRemoveAddress() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		contact.addAddress("address1");
		contact.removeAddress("address1");
		assertEquals("Address should be", "address 1\naddress 2\n",
				contact.getAddressesAsString());
	}

	@Test
	public void testGetAddressesAsString() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("Test getAddressesAsString", "address 1\naddress 2\n",
				contact.getAddressesAsString());
	}

	@Test
	public void testGetPhonesAsString() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("Phone number should be", "phone 1\n",
				contact.getPhonesAsString());
	}

	@Test
	public void testToString() {
		// fail("Not yet implemented");
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("The contact should be",
				"lastName0, firstName0\n[address 1]\n[phone 1]",
				contact.toString());
	}

	@Test
	public void testGetName() {
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");

		Contact contact = new Contact("firstName0", "lastName0", addresses,
				phoneNumbers);
		assertEquals("Test getName", "lastName0, firstName0", contact.getName());
	}

}
