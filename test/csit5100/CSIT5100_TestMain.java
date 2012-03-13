package csit5100;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	AppointmentTest.class, 
	ContactListTest.class,
	ContactTest.class,
	DayTest.class,
	MonthTest.class,
	SupaCalTest.class,
	MonthViewTest.class,
	ContactsManagerTest.class,
	DayViewTest.class
	})

public class CSIT5100_TestMain {
	
}
