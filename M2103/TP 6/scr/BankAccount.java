package bank;

public class BankAccount
{
	private int accountNumber;
	private double balance;
	private double authorizedOverdraft;

	public BankAccount(int anAccountNumber, double overdraft)
	{
		this.accountNumber = anAccountNumber;
		this.balance = 0;
		setOverdraft(overdraft);	
	}

	public double getOverdraft()
	{
		return this.authorizedOverdraft;
	}

	public void setOverdraft( double n)
	{
		if(n <= 0)
		{
			this.authorizedOverdraft = n;
		}
		else
		{
			this.authorizedOverdraft = 0;
		}
	}

	public void deposit(double amount)
	{
		if(amount >= 0)
		{
			this.balance += amount;
		}
		else
		{
			System.out.println("Montant du depot doit etre positif");
		}
	}

	public void withdraw(double amount)
	{
		if(amount < 0)
		{
			this.balance -= amount;
		}
		else
		{
			System.out.println("Montant du retrait doit etre negatif");
		}	
	}

	public double getBalance()
	{
		return this.balance;
	}

	public int getAccountNumber()
	{
		return this.accountNumber;
	}

	public void transfer(double amount, BankAccount other)
	{
		if(amount >= 0)
		{
			this.withdraw(amount);
			other.deposit(amount);
		}
		else
		{
			System.out.println("Le montant du transfert doit etre positif");
		}
	}

	public String toString()
	{
		String ret = "\nNuméro du compte : " + this.accountNumber;
		ret += "\nMontant sur le compte : " + this.balance;
		ret += "\nDécouvert autorisé : " + this.authorizedOverdraft;
		return ret;
	}
}