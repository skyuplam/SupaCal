package csit5100;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author B.A.D. Company A class with the sole responsibility of keeping our
 *         contacts in order and organized. Vector based and serializable.
 */
public class ContactList extends Vector<Contact> implements Serializable {
	// generated serialVersionUID
	private static final long serialVersionUID = 8796567783547447927L;

	/**
	 * Default constructor. Makes an empty vector, nothing else. Typically this
	 * data will be loaded from a saved file anyway.
	 */
	public ContactList() {

	} // doesn't really do anything... heh

	/**
	 * Adds the person to the contact list in the appropriate alphabetic
	 * location based on LastName, FirstName. Returns true in order to be
	 * compliant with vector parent class.
	 * 
	 * @param person
	 *            Contact to add to the contact list.
	 * @return Returns true.
	 */
	public boolean add(Contact person) {
		// if the list doesn't have any elements in it, just add it to the front
		// otherwise, go through the list and find where it should go
		if (size() == 0)
			add(0, person);
		else {
			for (int i = 0; i < size(); i++)
				if (person.getName().compareToIgnoreCase(get(i).getName()) <= 0) {
					add(i, person);
					break;
				} else if (i == size() - 1) {
					add(i + 1, person);
					break;
				}
		} // end else

		return true;
	} // end add

	/**
	 * Returns an array of strings with LastName, FirstName for display
	 * purposes.
	 * 
	 * @return Array of strings with LastName, FirstName of contact.
	 */
	public String[] toStrings() {
		String[] str = new String[size()];
		for (int i = 0; i < str.length; i++)
			str[i] = get(i).getName();
		return str;
	} // end toStrings
}
