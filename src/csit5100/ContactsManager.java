package csit5100;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author B.A.D. Company Manages the contact list for the CSCCalendar.
 *         Basically a much fancier and more integration friendly version of the
 *         electronic rolodex project from freshman year. Works within a JPanel
 *         so it's easily slipped inside of any parent JFrames or other JPanels.
 */
public class ContactsManager extends JPanel {
	// generated serialVersionUID
	private static final long serialVersionUID = 7378850002306699372L;

	/************************** modified by CSIT 5100 TA ******************************/
	// In order to facilitate the unit testing of application, we make GUI
	// components publicly accessible
	// Note in real world, this might not be a good OO practice. Programmers may
	// use reflection to achieve the same goal.

	// all the GUI stuff
	// containers
	public Container left; // left side container
	public Container leftBtn; // container for buttons on the left side
	public Container addContainer; // container for adding data
	public Container saveCancel; // container solely for the save and cancel
									// buttons
	// buttons
	public JButton add; // add button
	public JButton edit; // edit button
	public JButton remove; // remove button
	public JButton save; // save button
	public JButton cancel; // generic cancel button
	// display items
	public JList list; // display for list of entries
	public JScrollPane display; // scroll pane to display the text
	// input data and details items
	public JTextField fName; // first name text field
	public JTextField lName; // last name text field
	public JTextArea phoneNums; // phone number text area
	public JTextArea addresses; // e-mail addresses
	public JScrollPane phoneNumsPane; // pane for addresses
	public JScrollPane addressesPane; // pane for phone numbers

	// class level contactsList variable
	public ContactList contacts;

	/************************** modified by CSIT 5100 TA ******************************/

	public ContactsManager() {
		contacts = SupaCal.contacts;

		// setting up our containers
		this.setLayout(new GridLayout(1, 2)); // our jpanel
		left = new Container(); // container for the left side, as it changes
								// frequently
		left.setLayout(new BorderLayout());
		leftBtn = new Container(); // container for our main (add, edit, remove)
									// buttons
		leftBtn.setLayout(new GridLayout(0, 1));

		// setting up the buttons
		add = new JButton("Add New Contact");
		add.addActionListener(new add());
		edit = new JButton("Edit Selected Contact");
		edit.addActionListener(new edit());
		remove = new JButton("Delete Selected Contact");
		remove.addActionListener(new remove());
		save = new JButton("Submit");
		save.addActionListener(new save());
		cancel = new JButton("Cancel");
		cancel.addActionListener(new cancel());

		// setting up our areas for data input
		fName = new JTextField();
		lName = new JTextField();
		addresses = new JTextArea();
		addressesPane = new JScrollPane(addresses);
		addresses.setLineWrap(true);
		phoneNums = new JTextArea();
		phoneNums.setLineWrap(true);
		phoneNumsPane = new JScrollPane(phoneNums);
		addContainer = new Container();

		// preventing data from being entered without hitting the add or edit
		// buttons
		fName.setEditable(false);
		lName.setEditable(false);
		phoneNums.setEditable(false);
		addresses.setEditable(false);

		// temporarily creating these containers to make the layout look
		// prettier
		// they will not be referenced ever again
		Container topContainer = new Container();
		topContainer.setLayout(new GridLayout(0, 1));
		Container midContainer = new Container();
		midContainer.setLayout(new BorderLayout());
		Container lowContainer = new Container();
		lowContainer.setLayout(new BorderLayout());

		// adding everything to the correct containers for user input and
		// details
		addContainer.setLayout(new GridLayout(0, 1));
		topContainer.add(new JLabel("First Name"));
		topContainer.add(fName);
		topContainer.add(new JLabel("Last Name"));
		topContainer.add(lName);
		midContainer.add(new JLabel("Phone Numbers"), BorderLayout.NORTH);
		midContainer.add(phoneNumsPane, BorderLayout.CENTER);
		lowContainer.add(new JLabel("Addresses (e-mail or physical)"),
				BorderLayout.NORTH);
		lowContainer.add(addressesPane, BorderLayout.CENTER);
		addContainer.add(topContainer);
		addContainer.add(midContainer);
		addContainer.add(lowContainer);

		// small container for save/cancel
		saveCancel = new Container();
		saveCancel.setLayout(new GridLayout(1, 2));
		saveCancel.add(save);
		saveCancel.add(cancel);

		// setting up our areas for data output
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new selectionChanged());
		display = new JScrollPane(list);

		// setup the Jlist for the first time
		setupList();

		// adding everything to the main containers
		leftBtn.add(add);
		leftBtn.add(edit);
		leftBtn.add(remove);
		left.add(addContainer, BorderLayout.CENTER);
		left.add(leftBtn, BorderLayout.SOUTH);

		this.add(left);
		this.add(display);
	} // end constructor

	/**
	 * Resets the list when the list data set is changed. This is done in this
	 * manner to prevent the contactList from becoming "out of sync" with the
	 * JList.
	 */
	public void setupList() {
		String[] tempStrings;
		tempStrings = contacts.toStrings();
		list.setListData(tempStrings);
	} // end setupList

	/**
	 * Resets the left side to it's default objects. Also re-enables the JList.
	 */
	public void resetLeftSide() {
		fName.setEditable(false);
		lName.setEditable(false);
		phoneNums.setEditable(false);
		addresses.setEditable(false);
		cancel.setEnabled(true);
		list.setEnabled(true);
		left.remove(saveCancel);
		left.add(leftBtn, BorderLayout.SOUTH);
		left.validate();
		left.repaint();
	} // end resetLeftSide

	/**
	 * add ActionListener. Handles user input for adding a new contact.
	 */
	class add implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// clearing all text fields and setting them as editable
			fName.setEditable(true);
			fName.setText("");
			lName.setText("");
			lName.setEditable(true);
			addresses.setText("");
			addresses.setEditable(true);
			phoneNums.setText("");
			phoneNums.setEditable(true);

			// swapping the buttons in the left half of our JPanel
			left.remove(leftBtn);
			// left.remove(details);
			left.add(saveCancel, BorderLayout.SOUTH);
			// left.add(addContainer, BorderLayout.CENTER);
			left.validate();
			left.repaint();
		}
	} // end add class

	/**
	 * Handles changing the displayed details whenever the user's selection
	 * changes.
	 */
	class selectionChanged implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int location = list.getSelectedIndex();
			if (location != -1) {
				Contact selectedContact = contacts.get(list.getSelectedIndex());
				fName.setText(selectedContact.getFirstName());
				lName.setText(selectedContact.getLastName());
				phoneNums.setText(selectedContact.getPhonesAsString());
				addresses.setText(selectedContact.getAddressesAsString());
			}
		}
	} // end selectionChanged class

	/**
	 * If the user cancels adding a new contact this simply runs
	 * resetLeftSide().
	 */
	class cancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetLeftSide();
		}
	} // end cancel class

	/**
	 * Manages user input from editing an existing contact.
	 */
	class edit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int location = list.getSelectedIndex();

			if (location == -1)
				JOptionPane
						.showMessageDialog(getParent(), "No Entry Selected.");
			else {
				// to change the data we need to allow the user to edit it
				cancel.setEnabled(false);
				list.setEnabled(false);
				fName.setEditable(true);
				lName.setEditable(true);
				phoneNums.setEditable(true);
				addresses.setEditable(true);
				contacts.remove(location);
				// swapping the items in the left half of our JPanel
				left.remove(leftBtn);
				// left.remove(details);
				left.add(saveCancel, BorderLayout.SOUTH);
				left.validate();
				left.repaint();
			}
		}
	} // end edit class

	/**
	 * Removes the selected contact from the contactList, if there is one. If
	 * there isn't one, it informs the user of their mistake.
	 */
	class remove implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int location = list.getSelectedIndex();

			if (location == -1)
				JOptionPane
						.showMessageDialog(getParent(), "No Entry Selected.");
			else {
				contacts.remove(location);
				setupList();
			}
		}
	} // end remove class

	/**
	 * Manages the save button. Creates a new contact from the entered
	 * information, saves the new contact, and then runs resetLeftSide() and
	 * setupList();
	 */
	class save implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (fName.getText().compareTo("") == 0)
				JOptionPane.showMessageDialog(getParent(),
						"Nothing entered for First Name.");
			else if (lName.getText().compareTo("") == 0)
				JOptionPane.showMessageDialog(getParent(),
						"Nothing entered for Last Name.");
			else {
				Contact tempContact;
				// converting our data to a usable format for the class and then
				// saving it
				String[] tempAddArray = addresses.getText().split("\n");
				String[] tempPhoneArray = phoneNums.getText().split("\n");
				LinkedList<String> tempAddList = new LinkedList<String>();
				LinkedList<String> tempPhoneList = new LinkedList<String>();
				for (int i = 0; i < tempAddArray.length; i++)
					tempAddList.add(tempAddArray[i]);
				for (int i = 0; i < tempPhoneArray.length; i++)
					tempPhoneList.add(tempPhoneArray[i]);
				tempContact = new Contact(fName.getText(), lName.getText(),
						tempAddList, tempPhoneList);
				contacts.add(tempContact);

				setupList();
				resetLeftSide();
			}
		}
	} // end save class
}
