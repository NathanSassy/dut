package bank;

public class SavingsAccount extends BankAccount
{
	private double interestRate;

	public SavingsAccount(int accountNumber, double rate)
	{
		super(accountNumber, 0);
		this.interestRate = rate;
	}

	public void addInterest()
	{
		deposit(getBalance() * interestRate);
	}

	public String toString()
	{
		String ret = super.toString();
		ret += "\nLe taux d'interet est de : " + interestRate;
		return ret;
	}
}