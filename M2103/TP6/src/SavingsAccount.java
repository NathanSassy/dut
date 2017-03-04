package bank;

/**
* This class allow you to manipulate SavingsAccount object
* @author Antoine Gicquel
* @version 1.0
*/
public class SavingsAccount extends BankAccount
{
	private double interestRate;

	/**
	* Create a saving account
	* @param accountNumber the number of the account
	* @param rate the rate in %
	*/
	public SavingsAccount(int accountNumber, double rate)
	{
		super(accountNumber, 0);
		this.interestRate = rate;
	}

	/**
	* Add the interest on the saving account
	*/
	public void addInterest()
	{
		deposit(getBalance() * (interestRate/100));
	}

	/**
	* @return Informations about the cheking account
	*/
	public String toString()
	{
		String ret = super.toString();
		ret += "\nLe taux d'interet est de : " + interestRate;
		return ret;
	}
}