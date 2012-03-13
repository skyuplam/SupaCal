package csit5100;

/**
 * @author B.A.D. Company
 * Contains all the data for a contact. First name, last name, phone numbers,
 * and addresses. Is serializable so data can be saved.
 */
import java.io.Serializable;
import java.util.LinkedList;

public class Contact implements Serializable {
	// generated serialVersionUID
	private static final long serialVersionUID = -7995868872281745655L;
	private String firstName, lastName; // fist and last name as strings
	// addresses (physical and non physical) for the contact
	private LinkedList<String> addressList = new LinkedList<String>();
	// phone numbers for the contact
	private LinkedList<String> phoneList = new LinkedList<String>();

	/**
	 * @param firstName
	 *            First name of the contact.
	 * @param lastName
	 *            Last name of the contact.
	 * @param address
	 *            LinkedList of addresses as Strings.
	 * @param phoneNumbers
	 *            LinkedList of phone numbers as Strings Creates a new contact
	 *            with the following first name and last name. Adds address and
	 *            phone numbers to a list Returns address and phone numbers
	 *            based on given index
	 */
	public Contact(String firstName, String lastName,
			LinkedList<String> address, LinkedList<String> phoneNumbers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressList = address;
		this.phoneList = phoneNumbers;
	}// constructor

	// Getter and Setter methods ahead.
	/**
	 * Returns the first name of the contact as a String.
	 * 
	 * @return First name of the contact.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the contact to the String provided.
	 * 
	 * @param firstName
	 *            First name of the contact.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the contact as a String.
	 * 
	 * @return Last name of the contact.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the contact to the String provided.
	 * 
	 * @param lastName
	 *            Last name of the contact.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Adds an address to the list of addresses for the contact.
	 * 
	 * @param address
	 *            Address to add.
	 */
	public void addAddress(String address) {
		addressList.add(address);
	}// method addAddress

	/**
	 * Gets an address from the specified index of the LinkedList. If the index
	 * is invalid, returns null.
	 * 
	 * @param index
	 * @return Address at the specified index, or null if the index isn't valid.
	 */
	public String getAddress(int index) {
		if ((index >= addressList.size()) || index < 0)
			return null;
		return (String) addressList.get(index);
	}// method getAddress

	/**
	 * Adds a phone number to the list of phone numbers.
	 * 
	 * @param number
	 *            String representing the phone number.
	 */
	public void addNumber(String number) {
		phoneList.add(number);
	}// method addNumber

	/**
	 * Removes the specified number from the list of phone numbers. Does nothing
	 * if the specified number isn't found.
	 * 
	 * @param number
	 *            Number to remove.
	 */
	public void removeNumber(String number) {
		phoneList.remove(number);
	}// method removeNumber

	/**
	 * Gets the phone number at the specified index and returns it. Returns null
	 * if the index isn't valid.
	 * 
	 * @param index
	 *            Index of number to be retrieved.
	 * @return The string representing the phone number, or null if the index
	 *         isn't valid.
	 */
	public String getNumber(int index) {
		if ((index >= addressList.size()) || index < 0)
			return null;
		return (String) phoneList.get(index);
	}// method getNumber

	/**
	 * Removes the specified address from the list. If the address isn't found,
	 * then nothing happens.
	 * 
	 * @param address
	 *            Address to remove.
	 */
	public void removeAddress(String address) {
		addressList.remove(address);
	}// method removeAddress
		// end getter and setter methods

	/**
	 * Returns the addresses as a string with line breaks. This is mainly used
	 * for displaying the data in a textArea.
	 * 
	 * @return String containing all addresses.
	 */
	public String getAddressesAsString() {
		String str = "";
		for (int i = 0; i < addressList.size(); i++)
			str += addressList.get(i) + "\n";
		return str;
	} // end getAddressesAsString

	/**
	 * Returns the phone numbers as a string with line breaks. This is mainly
	 * used for displaying the data in a textArea.
	 * 
	 * @return String containing all phone numbers.
	 */
	public String getPhonesAsString() {
		String str = "";
		for (int i = 0; i < phoneList.size(); i++)
			str += phoneList.get(i) + "\n";
		return str;
	} // end getPhonesAsString

	/**
	 * Returns a string representation of the object. Used for debugging.
	 * 
	 * @return String representation of the object.
	 */
	public String toString() {
		return this.lastName + ", " + this.firstName + "\n"
				+ addressList.toString() + "\n" + phoneList.toString();
	} // end toString

	/**
	 * Returns the name in LastName, FirstName format.
	 * 
	 * @return Name of contact.
	 */
	public String getName() {
		return this.lastName + ", " + this.firstName;
	} // end getName
}