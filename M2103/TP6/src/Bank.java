package bank;

import java.util.ArrayList;
import java.util.Iterator;

/**
* This class allow you to manipulate Bank object
* @author Antoine Gicquel
* @version 1.0
*/
public class Bank
{
	/**
	* accounts contains all the bank accounts
	*/
	private ArrayList<BankAccount>  accounts;


	/**
	* Create the bank object containing bank accounts
	*/
	public Bank()
	{
		this.accounts = new ArrayList<BankAccount>();
	}

	/**
	* Adding an account in the bank
	* @param a the bank account
	*/
	public void addAccount(BankAccount a)
	{
		if(a == null)
		{
			System.out.println("L'objet BankAccount est null");
		}
		else if(find(a.getAccountNumber()) != null)
		{
			System.out.println("Le numero de compte est déjà utilisé");
		}
		else
		{
			this.accounts.add(a);
		}
	}

	/**
	* Getting the total amount of balance of every bank accounts
	* @return the total amount of balance
	*/
	public double getTotalBalance()
	{
		double total = 0.0;
		for(BankAccount bc : this.accounts)
		{
			total += bc.getBalance();
		}
		return total;
	}

	/**
	* Counter of account having a balance bigger than atLeast
	* @param atLeast the minimum amount
	* @return the number of account
	*/
	public int count(double atLeast)
	{
		int counter = 0;
		for(BankAccount bc : this.accounts)
		{
			if(bc.getBalance() >= atLeast)
				counter++;
		}
		return counter;
	}

	/**
	* Finding an account with the number
	* @param  accountNumber the account number
	* @return the bank account if exising nor null
	*/
	public BankAccount find(int accountNumber)
	{
		BankAccount bc = null;
		Iterator<BankAccount> iter = this.accounts.iterator();
		boolean found = false;

		while(iter.hasNext() && !found)
		{
			BankAccount tmp = iter.next();
			if(tmp.getAccountNumber() == accountNumber)
			{
				bc = tmp;
				found = true;
			}
		}

		return bc;
	}

	/**
	* Getting the bank account containing the biggest amount
	* @return the biggest bank account
	*/
	public BankAccount getMaximum()
	{
		BankAccount bc = null;
		double maxAmount = 0.0;

		for(BankAccount tmp : this.accounts)
		{
			if(tmp.getBalance() >= maxAmount)
			{
				maxAmount = tmp.getBalance();
				bc = tmp;
			}
		}

		return bc;
	}

}