package staff;

public class Employee extends StaffMember
{
	protected String socialSecurityNumber;
	protected double payRate;

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

	public double pay()
	{
		return this.payRate;
	}

	public String toString()
	{
		String ret = super.toString();
		ret += "\nCode de Securite Sociale : " + this.socialSecurityNumber;
		ret += "\nEuros / Heure :  " + this.payRate;
		return ret;
	}
}