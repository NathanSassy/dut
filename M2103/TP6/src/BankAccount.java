package bank;

/**
* This class allow you to manipulate BankAccount object
* @author Antoine Gicquel
* @version 1.0
*/
public class BankAccount
{
	private int accountNumber;
	private double balance;
	private double authorizedOverdraft;

	/**
	* Create a bank account
	* @param anAccountNumber the number of the account
	* @param overdraft the maximum amount of overdraft
	*/
	public BankAccount(int anAccountNumber, double overdraft)
	{
		this.accountNumber = anAccountNumber;
		this.balance = 0;
		setOverdraft(overdraft);	
	}

	/**
	* @return the overdraft
	*/
	public double getOverdraft()
	{
		return this.authorizedOverdraft;
	}

	/**
	* Setting a new overdraft
	* @param n the new overdraft
	*/
	public void setOverdraft( double n)
	{
		if(n >= 0)
		{
			this.authorizedOverdraft = n;
		}
		else
		{
			this.authorizedOverdraft = 0;
		}
	}

	/**
	* Depositing an amount of money
	* @param amount the amount
	*/
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

	/**
	* Withdrawing an amount of money
	* @param amount the amount
	*/
	public void withdraw(double amount)
	{
		if(amount >= 0 && amout <= this.balance)
		{
			this.balance -= amount;
		}
		else
		{
			System.out.println("Montant du retrait doit etre negatif");
		}	
	}

	/**
	* @return Getting the balance of the bank account
	*/
	public double getBalance()
	{
		return this.balance;
	}

	/**
	* @return Getting the account number
	*/
	public int getAccountNumber()
	{
		return this.accountNumber;
	}

	/**
	* Transfer an amount of money to another bank account
	* @param amount the amount
	* @param other the recipient
	*/
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

	/**
	* @return Informations about the bank account
	*/
	public String toString()
	{
		String ret = "\nNuméro du compte : " + this.accountNumber;
		ret += "\nMontant sur le compte : " + this.balance;
		ret += "\nDécouvert autorisé : " + this.authorizedOverdraft;
		return ret;
	}
}