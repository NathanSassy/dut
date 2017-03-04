package scenario;
import bank.*;

public class ScenarioBank
{
	public static void main(String[] args)
	{
		// Creation des banks
		Bank cmb = new Bank();
		Bank bnp = new Bank();

		// Création de comptes banquaires
		BankAccount bc1 = new BankAccount(1, 200);
		BankAccount bc2 = new BankAccount(2, 200);
		CheckingAccount ca1 = new CheckingAccount(5);
		ca1.deposit(300);
		CheckingAccount ca2 = new CheckingAccount(6);
		ca2.deposit(350);
		SavingsAccount sa1 = new SavingsAccount(9, 5);
		sa1.deposit(500);
		SavingsAccount sa2 = new SavingsAccount(10, 3);
		sa2.deposit(400);

		// ajout des comptes
		cmb.addAccount(bc1);
		cmb.addAccount(ca1);
		cmb.addAccount(sa1);
		bnp.addAccount(bc2);
		bnp.addAccount(ca2);
		bnp.addAccount(sa2);

		System.out.println("Montant total de cmb : " + cmb.getTotalBalance());
		System.out.println("Montant total de bnp : " + bnp.getTotalBalance());

		System.out.println("Nombre de comptes possedant au moins 200€ chez cmb : " + cmb.count(200));
		System.out.println("Nombre de comptes possedant au moins 200€ chez bnp : " + bnp.count(200));
	}
}