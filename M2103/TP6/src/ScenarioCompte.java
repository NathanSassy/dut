package scenario;
import bank.*;

public class ScenarioCompte
{
	public static void main(String[] args)
	{
		// Test des banks account
		System.out.println("\n------ Test des banks account ------");
		BankAccount bc1 = new BankAccount(1, 200);
		BankAccount bc2 = new BankAccount(2, 200);
		BankAccount bc3 = new BankAccount(3, 0);
		BankAccount bc4 = new BankAccount(4, 150);

		System.out.println(bc1);
		bc2.deposit(300);
		bc3.deposit(500);
		bc3.withdraw(100);
		System.out.println(bc3);
		bc2.transfer(50, bc4);

		// Test des checking accounts
		System.out.println("\n\n------ Test des checking accounts ------");
		CheckingAccount ca1 = new CheckingAccount(5);
		CheckingAccount ca2 = new CheckingAccount(6);
		CheckingAccount ca3 = new CheckingAccount(7);
		CheckingAccount ca4 = new CheckingAccount(8);

		ca1.deposit(300);
		ca2.deposit(500);
		ca1.withdraw(50);
		System.out.println(ca2);
		ca1.deposit(30);
		ca1.deposit(30);
		ca1.deposit(30);
		ca1.deductFees();
		System.out.println(ca1);

		// Test des savings accounts
		System.out.println("\n\n------ Test des savings accounts ------");
		SavingsAccount sa1 = new SavingsAccount(9, 5);
		SavingsAccount sa2 = new SavingsAccount(10, 3);
		SavingsAccount sa3 = new SavingsAccount(11, 4);
		SavingsAccount sa4 = new SavingsAccount(12, 0.5);

		sa1.deposit(500);
		System.out.println(sa1);
		sa1.addInterest();
		sa2.addInterest();
		System.out.println(sa1);
	}
}