package staff.test;
import staff.*;

public class TestStaff
{
	static private int nbTest = 0;
	static private int nbTestOk = 0;

	public static void main(String args[])
	{
		testConstructeur();
		testAddNewMember();
		testGetMember();

		System.out.println("\n=== Bilan des tests ===\n");
		if(nbTest > 0)
		{
			System.out.println("Nombre de tests realisés : " + nbTest);
			System.out.println("Nombre de tests reussis : " + nbTestOk);
			int taux = (int)(100.0*(double)nbTestOk/nbTest);
			System.out.println("Taux de reussite : " + taux + "%\n");
		}
		else
		{
			System.out.println("Aucun test realisé\n");
		}
	}

	public static void testConstructeur()
	{
		System.out.println("\n=== Methode testConstructeur ===\n");
		nbTest++;
		Staff s1 = new Staff();
		if(s1 != null)
		{
			System.out.println("[OK] Constructeur");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] Constructeur");
		}

		System.out.println("\n=== Fin methode testConstructeur ===\n");
	}

	public static void testAddNewMember()
	{
		System.out.println("\n=== Methode testAddNewMember ===\n");

		Staff s1 = new Staff();
		nbTest++;

		if(s1.getNumberOfMember() == 0)
		{
			System.out.println("[OK] Staff vide");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] Staff vide");
		}

		nbTest++;
		Volunteer v1 = new Volunteer("v1", "address v1", "0000001");
		Volunteer v2 = new Volunteer("v2", "address v2", "0000002");
		s1.addNewMember(v1);
		s1.addNewMember(v2);

		if(s1.getNumberOfMember() == 2)
		{
			System.out.println("[OK] Ajout de membre");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] Ajout de membre");
		}
		
		nbTest++;
		Volunteer v3 = null;
		s1.addNewMember(v3);
		if(s1.getNumberOfMember() == 2)
		{
			System.out.println("[OK] Ajout de membre null");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] Ajout de membre null");
		}

		System.out.println("\n=== Fin methode testAddNewMember ===\n");
	}

	public static void testGetMember()
	{
		System.out.println("\n=== Methode testGetMember ===\n");

		Staff s1 = new Staff();
		Volunteer v1 = new Volunteer("v1", "address v1", "0000001");
		Volunteer v2 = new Volunteer("v2", "address v2", "0000002");
		s1.addNewMember(v1);
		s1.addNewMember(v2);
		nbTest++;

		if(s1.getMember(0) == v1 && s1.getMember(1) == v2)
		{
			System.out.println("[OK] getMember");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] getMember");
		}

		nbTest++;
		if(s1.getMember(-5) == null && s1.getMember(150) == null)
		{
			System.out.println("[OK] getMember avec index hors limite");
			nbTestOk++;
		}
		else
		{
			System.out.println("[FAILED] getMember avec index hors limite");
		}

		System.out.println("\n=== Fin methode testGetMember ===\n");
	}

}