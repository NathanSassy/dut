package staff;

public class Hourly extends Employee
{
	private int hoursWorked;

	public Hourly(String eName, String eAddress, String ePhone, String socSecNumber, double rate)
	{
		super(eName, eAddress, ePhone, socSecNumber, rate);
		this.hoursWorked = 0;
	}

	public void addHours(int moreHours)
	{
		if(moreHours >= 0)
		{
			this.hoursWorked += moreHours;
		}
	}

	public double pay()
	{
		return (this.hoursWorked * super.pay());
	}

	public String toString()
	{
		String ret =  super.toString();
		ret += "\nSalaire : " + this.pay();
		return ret;
	}
}