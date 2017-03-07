package staff;

public class Executive extends Employee
{
	private double bonus;

	public Executive(String eName, String eAddress, String ePhone, String socSecNumber, double rate)
	{
		super(eName, eAddress, ePhone, socSecNumber, rate);
		this.bonus = 0;
	}

	public void awardBonus(double execBonus)
	{
		if(execBonus >= 0)
		{
			this.bonus += execBonus;
		}
	}

	public double pay()
	{
		return (super.pay() + this.bonus);
	}

	public String toString()
	{
		String ret =  super.toString();
		ret += "\nBonus : " + this.bonus;
		return ret;
	}

}