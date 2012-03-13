package csit5100;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ContactListTest {

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

	@Test
	public void testAddContact() {
//		fail("Not yet implemented");
		ContactList contactList = new ContactList();
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");
		
		Contact contact = new Contact("firstName", "lastName",
				addresses, phoneNumbers);
		Contact contact2 = new Contact("firstName2", "lastName2",
				addresses, phoneNumbers);
		Contact contact3 = new Contact("firstName2", "lastName21",
				addresses, phoneNumbers);
		assertEquals("Contact cannot be added",true,contactList.add(contact));
		assertEquals("Contact cannot be added",true,contactList.add(contact2));
		assertEquals("Contact cannot be added",true,contactList.add(contact));
		assertEquals("Contact cannot be added",true,contactList.add(contact2));
		assertEquals("Contact cannot be added",true,contactList.add(contact3));
		assertEquals("Only 5 contact added.",5,contactList.size());
	}

	@Test
	public void testToStrings() {
//		fail("Not yet implemented");
		ContactList contactList = new ContactList();
		LinkedList<String> addresses = new LinkedList<String>();
		addresses.add("address 1");
		addresses.add("address 2");
		LinkedList<String> phoneNumbers = new LinkedList<String>();
		phoneNumbers.add("phone 1");
		phoneNumbers.add("phone 2");
		
		Contact contact = new Contact("firstName0", "lastName0",
				addresses, phoneNumbers);
		Contact contact2 = new Contact("firstName1", "lastName1",
				addresses, phoneNumbers);
		contactList.add(contact);
		contactList.add(contact2);
		String[] strs = contactList.toStrings();
		for(int i = 0; i < strs.length; i++)
			assertEquals("The contact should be lastName"+i+", firstName"+i,"lastName"+i+", firstName"+i,strs[i]);
	}

}
