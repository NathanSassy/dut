package bank;

public class CheckingAccount extends BankAccount
{
	private int transactionCount;
	static final int FREE_TRANSACTIONS = 3;
	static final double TRANSACTION_FEE = 2.0;

	public CheckingAccount(int accountNumber)
	{
		super(accountNumber, 0);
		this.transactionCount = 0;
	}

	public void deposit(double amount)
	{
		super.deposit(amount);
		this.transactionCount++;
	}

	public void withdraw(double amount)
	{
		super.withdraw(amount);
		this.transactionCount++;
	}

	public void deductFees()
	{
		if(this.transactionCount > this.FREE_TRANSACTIONS)
		{
			double tax = this.TRANSACTION_FEE * (this.transactionCount - this.FREE_TRANSACTIONS);
			super.withdraw(tax);
		}

		this.transactionCount = 0;
	}

	public String toString()
	{
		String ret = super.toString();
		ret += "\nLe nombre de transaction est de : " + transactionCount;
		return ret;
	}
}