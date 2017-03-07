package scenario;
import staff.*;

public class Scenario
{
	public static void main(String args[])
	{	
		// Creation  Staff
		Staff s = new Staff();

		// Creation Volunteer
		Volunteer v1 = new Volunteer("v1", "address v1", "0000001");
		Volunteer v2 = new Volunteer("v2", "address v2", "0000002");
		Volunteer v3 = new Volunteer("v3", "address v3", "0000003");

		// Creation de Employee
		Employee emp1 = new Employee("emp1", "address emp1", "0000001", "1234501", 8.5);
		Employee emp2 = new Employee("emp2", "address emp2", "0000002", "1234502", 10);
		Employee emp3 = new Employee("emp3", "address emp3", "0000003", "1234503", 15);

		// Creation de Hourly
		Hourly h1 = new Hourly("h1", "address h1", "0000001", "12345001", 11);
		h1.addHours(35);
		Hourly h2 = new Hourly("h2", "address h2", "0000002", "12345002", 11);
		h2.addHours(70);
		Hourly h3 = new Hourly("h3", "address h3", "0000003", "12345003", 14);
		h3.addHours(17);

		// Creation de Executive
		Executive exe1 = new Executive("exe1", "address exe1", "0000001", "123450001", 6);
		exe1.awardBonus(5);
		Executive exe2 = new Executive("exe2", "address exe2", "0000002", "123450002", 12);
		exe2.awardBonus(10);
		Executive exe3 = new Executive("exe3", "address exe3", "0000003", "123450003", 9);
		exe3.awardBonus(2.1);

		s.addNewMember(v1);
		s.addNewMember(v2);
		s.addNewMember(v3);

		s.addNewMember(emp1);
		s.addNewMember(emp2);
		s.addNewMember(emp3);

		s.addNewMember(h1);
		s.addNewMember(h2);
		s.addNewMember(h3);

		s.addNewMember(exe1);
		s.addNewMember(exe2);
		s.addNewMember(exe3);

		s.payday();
	}
}