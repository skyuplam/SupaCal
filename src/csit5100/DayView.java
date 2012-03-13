package csit5100;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * @author B.A.D. Company. The dayView class is an embeded JPanel class. It will
 *         alow users to add, remove, and edit appointments that they have made.
 */
public class DayView extends JPanel {
	// generated serialVersionUID // private JComboBox repeatEvent;
	private static final long serialVersionUID = -9097975072248899192L;

	/************************** modified by CSIT 5100 TA ******************************/
	// In order to facilitate the unit testing of application, we make GUI
	// components publicly accessible
	// Note in real world, this might not be a good OO practice. Programmers may
	// use reflection to achieve the same goal.

	// header for the date
	public JLabel dateHeader;

	// getting the appointments organized
	public JScrollPane aptPane;
	public JList aptList;

	// buttons for adding and removing appointments
	public JButton newApt;
	public JButton delApt;
	public JButton add;
	public JButton cancel;
	public JButton editApt;

	// everything that needs to be shared for the add/edit/details views
	public JComboBox startHour;
	public JComboBox startMinute;
	public JComboBox endHour;
	public JComboBox endMinute;
	public JTextField title;
	public JTextField location;
	public JTextArea notes;
	public Container addContainer;
	public Container addButtons;
	public Container timeContainer;
	public Container contactsContainer;
	public Container left;
	public Container right;
	public Container lower;

	// list and pane for contacts lists
	public ContactList contacts;
	public JList contactsList;
	public JScrollPane contactsListPane;

	// to track the day we're on
	public Day theDay;

	/************************** modified by CSIT 5100 TA ******************************/

	/**
	 * Constructor method for the day view.
	 * 
	 * @param theDay
	 *            The day object this dayView will be representing.
	 */
	public DayView(Day theDay) {
		this.theDay = theDay;
		contacts = SupaCal.contacts;

		// what day is it again?
		dateHeader = new JLabel(theDay.toString(), JLabel.CENTER);

		// setting up the right side JButtons
		newApt = new JButton("New Appointment");
		delApt = new JButton("Delete Appointment");
		editApt = new JButton("Edit Appointment");
		newApt.addActionListener(new newApt());
		editApt.addActionListener(new editApt());
		delApt.addActionListener(new delApt());

		// containers...
		right = new Container();
		right.setLayout(new BorderLayout());
		left = new Container();
		left.setLayout(new BorderLayout());
		lower = new Container();
		lower.setLayout(new GridLayout(0, 1));
		lower.add(newApt);
		lower.add(editApt);
		lower.add(delApt);

		// setting up appointment List
		aptList = new JList();
		aptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		aptList.addListSelectionListener(new selectionChanged());
		aptPane = new JScrollPane(aptList);
		contactsList = new JList();
		contactsList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contactsListPane = new JScrollPane(contactsList);

		// setting up our appointments
		setAptList();
		// setting up all of our stuff for the add appointment window
		setupAptView();
		// making sure all states are reset before layout
		resetEverything();

		// adding everything to the JPanel and right container
		this.setLayout(new GridLayout());
		right.add(lower, BorderLayout.SOUTH);
		right.add(aptPane, BorderLayout.CENTER);
		right.add(dateHeader, BorderLayout.NORTH);
		this.add(left);
		this.add(right);
		this.setVisible(true);
	} // end dayView constructor

	/**
	 * Just resets the list of appointments. Displays string values only to
	 * attempt to keep everything user friendly.
	 */
	public void setAptList() {
		String[] aptData;
		aptData = theDay.aptsToStrings();
		aptList.setListData(aptData);
	} // end setAptList

	/**
	 * Resets the list of contacts.
	 */
	public void setContactList() {
		String[] contactData;
		contactData = contacts.toStrings();
		contactsList.setListData(contactData);
	} // end setContactList

	/**
	 * Contains everything for setting up the add appointment/details view on
	 * the left. The constructor method was feeling very crowded so this was
	 * moved here.
	 */
	public void setupAptView() {
		// getting our hours for the combo box
		String hourList[] = new String[24];
		String minuteList[] = new String[60];
		for (int i = 0; i < 10; i++)
			hourList[i] = "0" + i + ":";
		for (int i = 10; i < 24; i++)
			hourList[i] = i + ":";
		for (int i = 0; i < 10; i++)
			minuteList[i] = "0" + i + " ";
		for (int i = 10; i < 60; i++)
			minuteList[i] = i + " ";

		add = new JButton("Update");
		cancel = new JButton("Cancel");
		add.addActionListener(new createNewApt());
		cancel.addActionListener(new cancel());
		add.setEnabled(false);
		cancel.setEnabled(false);

		// containers
		addContainer = new Container();
		addButtons = new Container();
		timeContainer = new Container();
		addContainer.setLayout(new GridLayout(0, 1));
		timeContainer.setLayout(new GridLayout(0, 3));
		addButtons.setLayout(new GridLayout(1, 2));
		contactsContainer = new Container();
		contactsContainer.setLayout(new BorderLayout());

		startHour = new JComboBox(hourList);
		startMinute = new JComboBox(minuteList);
		endHour = new JComboBox(hourList);
		endMinute = new JComboBox(minuteList);
		title = new JTextField("", 30);
		location = new JTextField("", 30);
		notes = new JTextArea();
		// notes.setColumns(4);
		notes.setLineWrap(true);

		// organizing everything into their containers
		// time container
		timeContainer.add(new JLabel("Title:", JLabel.LEFT));
		timeContainer.add(title);
		timeContainer.add(new JLabel(""));

		timeContainer.add(new JLabel("Location:", JLabel.LEFT));
		timeContainer.add(location);
		timeContainer.add(new JLabel(""));

		timeContainer.add(new JLabel("Start Time:", JLabel.LEFT));
		timeContainer.add(startHour);
		timeContainer.add(startMinute);

		timeContainer.add(new JLabel("End Time:", JLabel.LEFT));
		timeContainer.add(endHour);
		timeContainer.add(endMinute);

		timeContainer.add(new JLabel("Notes"));

		// contacts container
		contactsContainer.add(new JLabel("Contacts Attending"),
				BorderLayout.NORTH);
		contactsContainer.add(contactsListPane);

		addButtons.add(add);
		addButtons.add(cancel);

		addContainer.add(timeContainer);
		addContainer.add(notes);
		addContainer.add(contactsContainer);

		// putting everything in the Container
		left.add(addContainer, BorderLayout.CENTER);
		left.add(addButtons, BorderLayout.SOUTH);
		left.validate();
		left.repaint();
		// end addContainers section
	} // end setupAddAptView

	/**
	 * Resets all user input fields and buttons to the correct startup state.
	 */
	public void resetEverything() {
		startHour.setEnabled(false);
		startMinute.setEnabled(false);
		endHour.setEnabled(false);
		endMinute.setEnabled(false);
		// repeatEvent.setEnabled(false);
		title.setEditable(false);
		location.setEditable(false);
		notes.setEditable(false);
		cancel.setEnabled(false);
		add.setEnabled(false);
		aptList.setEnabled(true);

		delApt.setEnabled(true);
		editApt.setEnabled(true);
		newApt.setEnabled(true);
	} // end resetEverything

	/**
	 * Deletes the selected item from the list of appointments and resets the
	 * JList.
	 */
	class delApt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// checks to make sure an appointment is selected
			// if one is, it removes it
			if (aptList.getSelectedIndex() == -1)
				JOptionPane
						.showMessageDialog(getParent(), "No entry selected.");
			else {
				theDay.remove(aptList.getSelectedIndex());
				setAptList();
			}// else
		}// actionPerformed
	} // end delApt class

	/**
	 * Cancels adding a new appointment by resetting all buttons and data
	 * fields.
	 */
	class cancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetEverything();
		}// actionPerformed
	} // end cancel class

	/**
	 * Handles changing the displayed data whenever the user's selection
	 * changes.
	 */
	class selectionChanged implements ListSelectionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int loc = aptList.getSelectedIndex();
			if (loc != -1) {
				// pulling all of our data out of the selected appointment and
				// putting it into the correct locations
				Appointment temp = theDay.get(loc);
				startHour.setSelectedIndex(temp.getStartTime().getHours());
				startMinute.setSelectedIndex(temp.getStartTime().getMinutes());
				endHour.setSelectedIndex(temp.getEndTime().getHours());
				endMinute.setSelectedIndex(temp.getEndTime().getMinutes());
				title.setText(temp.getTitle());
				notes.setText(temp.getNotes());
				location.setText(temp.getLocation());
				contactsList.setListData(temp.getAttendeesNames());
				notes.repaint();
			}// if
		} // end selectionChanged
	} // end selectionChanged class

	/**
	 * Creates the new appointment. All error checking for the appointment
	 * creation is done here.
	 */
	class createNewApt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (startHour.getSelectedIndex() > endHour.getSelectedIndex()
					|| (startHour.getSelectedIndex() == endHour
							.getSelectedIndex() && startMinute
							.getSelectedIndex() >= endMinute.getSelectedIndex()))
				JOptionPane.showMessageDialog(getParent(),
						"Your end time must be after your start time.");
			else {
				Calendar tempCal = new GregorianCalendar();
				Date startTime, endTime;
				ContactList attendees = new ContactList();
				int[] locations = contactsList.getSelectedIndices();

				// getting the start time
				tempCal.setTime(theDay.getTheDate());
				tempCal.set(Calendar.HOUR, startHour.getSelectedIndex());
				tempCal.set(Calendar.MINUTE, startMinute.getSelectedIndex());
				startTime = tempCal.getTime();
				// getting our end time in date format
				// creating a new calendar is required to fix a small glitch
				// with having
				// a start time of noon.
				tempCal = new GregorianCalendar();
				tempCal.setTime(theDay.getTheDate());
				tempCal.set(Calendar.HOUR, endHour.getSelectedIndex());
				tempCal.set(Calendar.MINUTE, endMinute.getSelectedIndex());
				endTime = tempCal.getTime();
				// getting our attendees into a new contactList
				for (int i = 0; i < locations.length; i++)
					attendees.add(contacts.get(locations[i]));
				Appointment tempApt = new Appointment(title.getText(),
						startTime, endTime, location.getText(),
						notes.getText(), attendees);

				theDay.add(tempApt);
				// resetting buttons and the Appointment List
				resetEverything();
				setAptList();
			} // else
		}// actionPerformed
	} // end createNewApt

	/**
	 * Changes our layout for the left side to handle data for a new
	 * appointment, while blocking the user from using buttons on the right side
	 * until they add or cancel the appointment.
	 */
	class newApt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// making sure no selections are changed and no buttons are pressed
			setContactList();
			aptList.setEnabled(false);
			newApt.setEnabled(false);
			delApt.setEnabled(false);
			editApt.setEnabled(false);

			// enabling user input
			startHour.setEnabled(true);
			startMinute.setEnabled(true);
			endHour.setEnabled(true);
			endMinute.setEnabled(true);
			title.setEditable(true);
			location.setEditable(true);
			notes.setEditable(true);
			cancel.setEnabled(true);

			// resetting all values to blank or first index
			notes.setText("");
			title.setText("");
			location.setText("");
			add.setEnabled(true);
			cancel.setEnabled(true);
			startHour.setSelectedIndex(0);
			endHour.setSelectedIndex(0);
			startMinute.setSelectedIndex(0);
			endMinute.setSelectedIndex(0);
		}// actionPerformed
	} // end delApt class

	/**
	 * Allows the user to edit the selected appointment. Forces the user to
	 * click save so the appointment isn't lost (unless they click the X
	 * button).
	 */
	class editApt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int loc = aptList.getSelectedIndex();
			if (loc == -1)
				JOptionPane
						.showMessageDialog(getParent(), "No entry selected.");
			else {
				theDay.remove(loc);
				// making sure no selections are changed and no buttons are
				// pressed before
				// the edit is complete
				aptList.setEnabled(false);
				newApt.setEnabled(false);
				delApt.setEnabled(false);
				editApt.setEnabled(false);

				// enabling user input
				startHour.setEnabled(true);
				startMinute.setEnabled(true);
				endHour.setEnabled(true);
				endMinute.setEnabled(true);
				title.setEditable(true);
				location.setEditable(true);
				notes.setEditable(true);
				cancel.setEnabled(false);

				// showing all contacts in contacts list
				setContactList();
				add.setEnabled(true);
			}// else
		}// actionPerformed
	} // end editApt class
}// dayView