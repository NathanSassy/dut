package bank;

/**
* This class allow you to manipulate CheckingAccount object
* @author Antoine Gicquel
* @version 1.0
*/
public class CheckingAccount extends BankAccount
{
	private int transactionCount;
	static final int FREE_TRANSACTIONS = 3;
	static final double TRANSACTION_FEE = 2.0;

	/**
	* Creating cheking account
	* @param accountNumber the number of the account
	*/
	public CheckingAccount(int accountNumber)
	{
		super(accountNumber, 0);
		this.transactionCount = 0;
	}

	/**
	* Depositing an amount of money
	* @param amount the amount
	*/
	public void deposit(double amount)
	{
		super.deposit(amount);
		this.transactionCount++;
	}

	/**
	* Withdrawing an amount of money
	* @param amount the amount
	*/
	public void withdraw(double amount)
	{
		super.withdraw(amount);
		this.transactionCount++;
	}

	/**
	* Deducting fees on the checking account
	*/
	public void deductFees()
	{
		if(this.transactionCount > this.FREE_TRANSACTIONS)
		{
			double tax = this.TRANSACTION_FEE * (this.transactionCount - this.FREE_TRANSACTIONS);
			super.withdraw(tax);
		}

		this.transactionCount = 0;
	}

	/**
	* @return Informations about the cheking account
	*/
	public String toString()
	{
		String ret = super.toString();
		ret += "\nLe nombre de transaction est de : " + transactionCount;
		return ret;
	}
}