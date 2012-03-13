package csit5100;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author B.A.D. Company Manages the layout of the month view. Is a JPanel for
 *         easy integration into a JFrame and for flexibility. This will also
 *         serve as the home of the day view when a user click's on a day.
 */
public class MonthView extends JPanel {

	// generated serialVersionUID
	private static final long serialVersionUID = 6840560819065153830L;

	/************************** modified by CSIT 5100 TA ******************************/
	// In order to facilitate the unit testing of application, we make GUI
	// components publicly accessible
	// Note in real world, this might not be a good OO practice. Programmers may
	// use reflection to achieve the same goal.

	// containers
	public Container daysContainer;
	public Container mainView;

	// buttons for next month, previous month
	public JButton nextMonth;
	public JButton prevMonth;

	// buttons for days, 1-31
	public JButton[] days;

	// returns the user to the calendar view (for use when in day view)
	public JButton backToCal;

	// label for the month
	public JLabel header;

	// data for months
	public Month currentMonth;
	public int monthValue;
	public int yearValue;

	/************************** modified by CSIT 5100 TA ******************************/

	// list of contacts to hold onto for the day view

	/**
	 * Constructor for the monthView class.
	 */
	public MonthView() {
		// getting our buttons made
		nextMonth = new JButton(" > ");
		prevMonth = new JButton(" < ");
		backToCal = new JButton("Back to Calendar View");
		nextMonth.addActionListener(new nextMonth());
		prevMonth.addActionListener(new prevMonth());
		backToCal.addActionListener(new resetView());

		// sets up the month and sets the selected date to the current date
		currentMonth = SupaCal.theMonth;
		Calendar tempCal = new GregorianCalendar();
		currentMonth.setDate(tempCal.get(Calendar.YEAR),
				tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DATE));
		monthValue = currentMonth.getMonthInt();
		yearValue = currentMonth.getYear();
		// setting up our days container
		daysContainer = new Container();
		daysContainer.setLayout(new GridLayout(0, 7, 1, 1));
		this.setLayout(new BorderLayout());
		header = new JLabel("", JLabel.CENTER);
		setupView();

		mainView = new Container();
		mainView.setLayout(new BorderLayout());
		mainView.add(prevMonth, BorderLayout.WEST);
		mainView.add(nextMonth, BorderLayout.EAST);
		mainView.add(daysContainer, BorderLayout.CENTER);

		this.setSize(800, 400);
		this.setVisible(true);
		this.add(header, BorderLayout.NORTH);
		this.add(mainView, BorderLayout.CENTER);
	} // end constructor

	/**
	 * Sets up the most comonly used container, the days container. Removes all
	 * buttons in the daysContainer, then rebuilds the month based on the
	 * yearValue and monthValue integers.
	 */
	public void setupView() {
		daysContainer.removeAll();
		daysContainer.validate();

		// setting up our calendar
		int firstDay = currentMonth.getFirstDay();
		int daysToSkip = 0;
		int numOfDays = currentMonth.getDays();

		switch (firstDay) {
		case Calendar.SUNDAY:
			daysToSkip = 0;
			break;
		case Calendar.MONDAY:
			daysToSkip = 1;
			break;
		case Calendar.TUESDAY:
			daysToSkip = 2;
			break;
		case Calendar.WEDNESDAY:
			daysToSkip = 3;
			break;
		case Calendar.THURSDAY:
			daysToSkip = 4;
			break;
		case Calendar.FRIDAY:
			daysToSkip = 5;
			break;
		case Calendar.SATURDAY:
			daysToSkip = 6;
			break;
		} // end switch statement

		// adding our labels for the days
		daysContainer.add(new JLabel("Sunday", JLabel.CENTER));
		daysContainer.add(new JLabel("Monday", JLabel.CENTER));
		daysContainer.add(new JLabel("Tuesday", JLabel.CENTER));
		daysContainer.add(new JLabel("Wednesday", JLabel.CENTER));
		daysContainer.add(new JLabel("Thursday", JLabel.CENTER));
		daysContainer.add(new JLabel("Friday", JLabel.CENTER));
		daysContainer.add(new JLabel("Saturday", JLabel.CENTER));

		// adding empty space
		for (int i = 0; i < daysToSkip; i++)
			daysContainer.add(new JLabel(""));

		// getting all of our buttons made
		// if there is an appointment display an '*' in the button
		days = new JButton[31];

		String str;
		GregorianCalendar tempGC;
		Day tempDay;

		for (int i = 0; i < numOfDays; i++) {

			tempGC = new GregorianCalendar(yearValue, monthValue, (i + 1));
			tempDay = currentMonth.getDay(tempGC.getTime());

			if (tempDay != null && tempDay.size() > 0)
				str = "*";
			else
				str = " ";

			days[i] = new JButton((i + 1) + str);
			days[i].addActionListener(new dayClicked(i));
			daysContainer.add(days[i]);
		}// end for

		header.setText(currentMonth.toString());

		this.validate();
		header.repaint();
		this.repaint();
	} // end setupView

	/**
	 * Checks to see if there is already a day on the calendar with appointments
	 * in it. If there isn't one, it creates a new day. If there is one, it
	 * loads and displays that day in a new window.
	 */
	class dayClicked implements ActionListener {
		private int day;

		public dayClicked(int day) {
			this.day = day + 1;
		}// construct

		public void actionPerformed(ActionEvent e) {
			GregorianCalendar tempGC = new GregorianCalendar(yearValue,
					monthValue, day);
			Date tempDate = tempGC.getTime();
			DayView currentDayView;
			Day clickedDay = currentMonth.getDay(tempDate);
			if (clickedDay == null) {
				GregorianCalendar temp = new GregorianCalendar(yearValue,
						monthValue, day);
				Day newDay = new Day(temp.getTime());
				currentMonth.add(newDay);
				currentDayView = new DayView(newDay);
			}// if
			else
				currentDayView = new DayView(clickedDay);

			mainView.remove(daysContainer);
			mainView.remove(nextMonth);
			mainView.remove(prevMonth);
			mainView.add(backToCal, BorderLayout.SOUTH);
			mainView.add(currentDayView, BorderLayout.CENTER);
			mainView.validate();
			mainView.repaint();
		}// actionPerformed
	} // end dayClicked class

	/**
	 * Goes to the next month. If we're at the end of the year it simply goes to
	 * January of the next year's calendar. I really wanted to put Undecember in
	 * here just for fun... but I held myself back.
	 */
	class nextMonth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (monthValue == Calendar.DECEMBER) {
				monthValue = Calendar.JANUARY;
				yearValue++;
			} else {
				monthValue++;
			}
			saveMonth();
			loadMonth(yearValue, monthValue);
			setupView();
		}// actionPerformed
	} // end nextMonth class

	/**
	 * Goes to the previous month. If we're at the beginning of the year it goes
	 * to December of the previous year.
	 */
	class prevMonth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (monthValue == Calendar.JANUARY) {
				monthValue = Calendar.DECEMBER;
				yearValue--;
			} else
				monthValue--;
			saveMonth();
			loadMonth(yearValue, monthValue);
			setupView();
		}// actionPerformed
	} // end prevMonth Class

	/**
	 * Loads currentMonth If the load fails it outputs the error message to
	 * console for debugging purposes, and starts with a blank month.
	 */
	public void loadMonth(int yearvalue, int monthvalue) {
		File test = new File(yearvalue + " " + monthvalue);
		if (test.exists()) { // http://www.rgagnon.com/javadetails/java-0070.html
			try {
				ObjectInputStream inStream = new ObjectInputStream(
						new FileInputStream(yearvalue + " " + monthvalue));
				currentMonth = (Month) inStream.readObject();
				inStream.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}// if

		else {
			currentMonth = new Month(yearvalue, monthvalue);
		}// else
	} // end loadMonth

	/**
	 * Saves currentMonth If it fails it outputs the error message to console
	 * for debugging purposes, however our data is lost.
	 */
	public void saveMonth() {
		deleteEmptyDays();
		if (!currentMonth.isEmpty()) {
			try {
				ObjectOutputStream outstream = new ObjectOutputStream(
						new FileOutputStream(Integer.toString(currentMonth
								.getYear()) + " " + currentMonth.getMonthInt()));
				outstream.writeObject(currentMonth);
				outstream.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}// if
	} // end saveMonth

	/**
	 * Quickly runs through our calendar and removes any hanging days with 0
	 * appointments. This is to prevent wasted space and will help improve
	 * overall load times of the application will be consistent.
	 */
	public void deleteEmptyDays() {
		for (int i = 0; i < currentMonth.size(); i++)
			if (currentMonth.get(i).isEmpty()) {
				currentMonth.remove(i);
				i--;
			}
	} // end deleteEmptyDays

	/**
	 * Resets the JFrame's layout after the backToCal button is pressed. Just
	 * removes everything in the mainView container, and adds everything that
	 * should be placed back into it, and repaints it.
	 */
	class resetView implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainView.removeAll();
			mainView.add(prevMonth, BorderLayout.WEST);
			mainView.add(nextMonth, BorderLayout.EAST);
			mainView.add(daysContainer, BorderLayout.CENTER);
			mainView.validate();
			mainView.repaint();
			setupView();
		} // actionPerformed
	} // end resetView class
}// monthView