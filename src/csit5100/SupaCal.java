package csit5100;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author B.A.D. Company The main window (JFrame) that everything else in the
 *         GUI will reside in. Handles the loading and saving of data.
 *         Initializes the monthView as the default view. Also loads a
 *         contactManager while starting up, so it is at the ready of the user
 *         without needing to wait.
 */
public class SupaCal extends JFrame {
	private static final long serialVersionUID = 8597814870141766562L;

	/************************** modified by CSIT 5100 TA ******************************/
	// In order to facilitate the unit testing of application, we make GUI
	// components publicly accessible
	// Note in real world, this might not be a good OO practice. Programmers may
	// use reflection to achieve the same goal.

	// class level variables for our JPanels
	public static MonthView monthView;
	public static ContactsManager contactsMgr;

	// buttons
	public static JButton manageContacts;
	public static JButton viewCalendar;

	// class level variables for our data
	// these will be public and static so any of the other classes may reference
	// them
	// without needing them to be passed back and forth
	public static ContactList contacts;
	public static Month theMonth;

	/************************** modified by CSIT 5100 TA ******************************/

	/**
	 * Constructor method. Creates the GUI.
	 */
	public SupaCal() {
		monthView = new MonthView();
		contactsMgr = new ContactsManager();

		// setting up buttons for view calendar and view contacts
		manageContacts = new JButton("Manage Your Contacts");
		manageContacts.addActionListener(new setupContactsManager(this));
		viewCalendar = new JButton("View Your Calendar");
		viewCalendar.addActionListener(new setupMonthView(this));

		this.setSize(800, 500);
		this.setLayout(new BorderLayout());
		this.add(monthView, BorderLayout.CENTER);
		this.add(manageContacts, BorderLayout.SOUTH);
		this.setTitle("CSC Calendar");
		this.addWindowListener(new close());
		this.setVisible(true);
	} // end constructor

	/**
	 * Main method. Simple loads the appointment and contact data, then creates
	 * an instance of the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		loadMonth();
		loadContacts();

		@SuppressWarnings("unused")
		SupaCal gui = new SupaCal();
	} // end main

	/**
	 * Quickly runs through our calendar and removes any hanging days with 0
	 * appointments. This is to prevent wasted space and will help improve
	 * overall load times of the application will be consistent.
	 */
	public static void deleteEmptyDays() {
		for (int i = 0; i < theMonth.size(); i++)
			if (theMonth.get(i).isEmpty()) {
				theMonth.remove(i);
				i--;
			}// if
	} // end deleteEmptyDays

	/**
	 * Loads all data for appointments. If the load fails it outputs the error
	 * message to console for debugging purposes creates a new month if one did
	 * not exist
	 */

	public static void loadMonth() {
		Calendar tempCal = new GregorianCalendar();// jan = 0
		File test = new File(tempCal.get(Calendar.YEAR) + " "
				+ tempCal.get(Calendar.MONTH));
		if (test.exists()) { // http://www.rgagnon.com/javadetails/java-0070.html
			try {
				ObjectInputStream inStream = new ObjectInputStream(
						new FileInputStream(tempCal.get(Calendar.YEAR) + " "
								+ tempCal.get(Calendar.MONTH)));
				theMonth = (Month) inStream.readObject();
				inStream.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}// if
		else {// creates a new month if one doesn't exist
			theMonth = new Month(tempCal.get(Calendar.YEAR),
					tempCal.get(Calendar.MONTH));
		}// else
	} // end loadMonth

	/**
	 * Saves all data for the appointments. If it fails it outputs the error
	 * message to console for debugging purposes, however our data is lost.
	 */
	public static void saveMonth() {
		monthView.saveMonth();
	} // end saveMonth

	/**
	 * Loads all contacts data. If it can't load the data (file missing) it
	 * makes a new contact list.
	 */
	public static void loadContacts() {
		File test = new File("contacts.mon");
		if (test.exists()) {
			try {
				ObjectInputStream inStream = new ObjectInputStream(
						new FileInputStream("contacts.mon"));
				contacts = (ContactList) inStream.readObject();
				inStream.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}// if
		else
			contacts = new ContactList();// creates a new blank contactList
	} // end loadContacts

	/**
	 * Saves the contact list. If it can't save the contacts list we lose
	 * everything... oops.
	 */
	public static void saveContacts() {
		try {
			ObjectOutputStream outstream = new ObjectOutputStream(
					new FileOutputStream("contacts.mon"));
			outstream.writeObject(contacts);
			outstream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} // end saveContacts

	/**
	 * For the X button on the window. So we don't just close the GUI and leave
	 * everything else hanging. First, removes any hanging empty days. Then
	 * saves the contacts and the month. Then exits.
	 */
	class close extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			deleteEmptyDays();
			saveContacts();
			saveMonth();
			System.exit(0);
		}// windowClosing
	} // end close

	/**
	 * Changes the view from the month view to the contacts manager.
	 */
	class setupContactsManager implements ActionListener {
		private JFrame parentFrame;

		public setupContactsManager(JFrame frame) {
			parentFrame = frame;
		}// setContactsManager

		public void actionPerformed(ActionEvent e) {
			parentFrame.remove(monthView);
			parentFrame.remove(manageContacts);

			parentFrame.add(contactsMgr, BorderLayout.CENTER);
			parentFrame.add(viewCalendar, BorderLayout.SOUTH);
			parentFrame.validate();
			parentFrame.repaint();
		}// actionPerformed
	} // end setupContactsManager class

	/**
	 * Changes the view from the contacts manager to the month view.
	 */
	class setupMonthView implements ActionListener {
		private JFrame parentFrame;

		public setupMonthView(JFrame frame) {
			parentFrame = frame;
		}// setupMonthView

		public void actionPerformed(ActionEvent e) {
			parentFrame.remove(contactsMgr);
			parentFrame.remove(viewCalendar);
			parentFrame.validate();
			parentFrame.add(monthView, BorderLayout.CENTER);
			parentFrame.add(manageContacts, BorderLayout.SOUTH);
			parentFrame.repaint();
		}// actionPerformed
	} // end setupMonthView
}// SupaCal