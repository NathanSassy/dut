package staff;

/**
* Executive class allows you to manipulate Executive
*/
public class Executive extends Employee
{
	private double bonus;

	/**
	* Constructor of Executive
	* @param eName the name
	* @param eAddress the address
	* @param ePhone the phone number
	* @param socSecNumber Social Security Number
	* @param rate earning amount by hours
	*/
	public Executive(String eName, String eAddress, String ePhone, String socSecNumber, double rate)
	{
		super(eName, eAddress, ePhone, socSecNumber, rate);
		this.bonus = 0;
	}

	/**
	* Adding bonus to the bonus variable
	* @param execBonus Amount of bonus you want to add
	*/
	public void awardBonus(double execBonus)
	{
		if(execBonus >= 0)
		{
			this.bonus += execBonus;
		}
	}

	/**
	* Give the pay
	* @return return the payRate and the bonus
	*/
	public double pay()
	{
		return (super.pay() + this.bonus);
	}

	/**
	* Give some infos on the worker
	* @return String containing infos
	*/
	public String toString()
	{
		String ret =  super.toString();
		ret += "\nBonus : " + this.bonus;
		return ret;
	}

}