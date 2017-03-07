package staff;

/**
* Employee class allows you to manipulate Employee
*/
public class Employee extends StaffMember
{
	protected String socialSecurityNumber;
	protected double payRate;

	/**
	* Constructor of Volunteer
	* @param eName the name
	* @param eAddress the address
	* @param ePhone the phone number
	* @param socSecNumber Social Security Number
	* @param rate earning amount by hours
	*/
	public Employee(String eName, String eAddress, String ePhone, String socSecNumber, double rate)
	{
		super(eName, eAddress, ePhone);
		if(socSecNumber != null && rate >= 0)
		{
			this.socialSecurityNumber = socSecNumber;
			this.payRate = rate;
		}
		else
		{
			System.out.println("Les parametres doivent être non null");
		}
	}

	/**
	* Give the pay
	* @return return the payRate
	*/
	public double pay()
	{
		return this.payRate;
	}

	/**
	* Give some infos on the worker
	* @return String containing infos
	*/
	public String toString()
	{
		String ret = super.toString();
		ret += "\nCode de Securite Sociale : " + this.socialSecurityNumber;
		ret += "\nEuros / Heure :  " + this.payRate;
		return ret;
	}
}