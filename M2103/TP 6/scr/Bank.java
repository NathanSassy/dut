package bank;

import java.util.ArrayList;
import java.util.Iterator;

public class Bank
{
	ArrayList<BankAccount>  accounts;

	public Bank()
	{
		accounts = new ArrayList<BankAccount>();
	}

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

	public double getTotalBalance()
	{
		double total = 0.0;
		for(BankAccount bc : this.accounts)
		{
			total += bc.getBalance();
		}
		return total;
	}

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