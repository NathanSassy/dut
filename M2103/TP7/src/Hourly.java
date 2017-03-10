package staff;

/**
* Hourly class allows you to manipulate Hourly
*/
public class Hourly extends Employee
{
	private int hoursWorked;

	/**
	* Constructor of Hourly
	* @param eName the name
	* @param eAddress the address
	* @param ePhone the phone number
	* @param socSecNumber Social Security Number
	* @param rate earning amount by hours
	*/
	public Hourly(String eName, String eAddress, String ePhone, String socSecNumber, double rate)
	{
		super(eName, eAddress, ePhone, socSecNumber, rate);
		this.hoursWorked = 0;
	}

	/**
	* Adding hours to hours worked variable
	* @param moreHours Number of hours you want to add
	*/
	public void addHours(int moreHours)
	{
		if(moreHours >= 0)
		{
			this.hoursWorked += moreHours;
		}
	}

	/**
	* Give the pay
	* @return calculated pay with the pay rate and the worked hours
	*/
	public double pay()
	{
		this.hoursWorked = 0;
		return (this.hoursWorked * super.pay());
	}

	/**
	* Give some infos on the worker
	* @return String containing infos
	*/
	public String toString()
	{
		String ret =  super.toString();
		ret += "\nSalaire : " + this.pay();
		return ret;
	}
}