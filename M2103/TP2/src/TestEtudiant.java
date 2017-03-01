public class TestEtudiant
{
	static private int nbTest = 0;
	static private int nbTestOk = 0;

	public static void main(String[] args)
	{
		testConstructeur();
		testNom();
		testGetNbNotes();
		testGetUneNote();
		testInitialisation();
		testMoyenne();

		System.out.println("\n=== Bilan des tests ===\n");
		if(nbTest > 0)
		{
			System.out.println("Nombre de tests realisés : " + nbTest);
			System.out.println("Nombre de tests reussis : " + nbTestOk);
			int taux = (int)(100.0*(double)nbTestOk/nbTest);
			System.out.println("Taux de reussite : " + taux + "%");
		}
		else
		{
			System.out.println("Aucun test realisé");
		}
	}

	public static void testConstructeur()
	{
		System.out.println("\n=== Methode testConstructeur ===\n");

		// test nom = null
		nbTest++;
		Etudiant e1 = new Etudiant(null, 5);
		if(e1.getNom() == null)
		{
			nbTestOk++;
			System.out.println("[OK] Test nom = null");
		}
		else
		{
			System.out.println("[Failed] Test nom = null");
		}

		// test nom = "toto"
		nbTest++;
		Etudiant e2 = new Etudiant("toto", 5);
		if(e2.getNom() == "toto")
		{
			nbTestOk++;
			System.out.println("[OK] Test nom = valeur");
		}
		else
		{
			System.out.println("[Failed] Test nom = valeur");
		}

		// test nbNote > 0 (10)
		nbTest++;
		Etudiant e3 = new Etudiant("toto", 10);
		if(e3.getNbNotes() == 10)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote > 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote > 0");
		}

		// test nbNote = 0
		nbTest++;
		Etudiant e4 = new Etudiant("toto", 0);
		if(e4.getNbNotes() == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote = 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote = 0");
		}

		// test nbNote < 0
		nbTest++;
		Etudiant e5 = new Etudiant("toto", -5);
		if(e5.getNbNotes() == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote < 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote < 0");
		}

		System.out.println("\n=== Fin methode testConstructeur ===\n");
	}

	public static void testNom()
	{
		System.out.println("\n=== Methode testNom ===\n");

		// test du getter
		nbTest++;
		Etudiant e1 = new Etudiant("toto", 5);
		if(e1.getNom() == "toto")
		{
			nbTestOk++;
			System.out.println("[OK] Test getNom()");
		}
		else
		{
			System.out.println("[Failed] Test getNom()");
		}

		// test du setter
		nbTest++;
		Etudiant e2 = new Etudiant("toto", 5);
		e2.setNom("toto2");
		if(e2.getNom() == "toto2")
		{
			nbTestOk++;
			System.out.println("[OK] Test setNom()");
		}
		else
		{
			System.out.println("[Failed] Test setNom()");
		}

		System.out.println("\n=== Fin methode testNom ===\n");
	}

	public static void testGetNbNotes()
	{
		System.out.println("\n=== Methode testGetNbNotes ===\n");

		// test nbNotes > 0
		nbTest++;
		Etudiant e1 = new Etudiant("toto", 5);
		if(e1.getNbNotes() == 5)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote > 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote > 0");
		}

		// test nbNotes = 0
		nbTest++;
		Etudiant e2 = new Etudiant("toto", 0);
		if(e2.getNbNotes() == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote = 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote = 0");
		}

		// test nbNotes < 0
		nbTest++;
		Etudiant e3 = new Etudiant("toto", -5);
		if(e3.getNbNotes() == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Test nbNote < 0");
		}
		else
		{
			System.out.println("[Failed] Test nbNote < 0");
		}

		System.out.println("\n=== Fin methode testGetNbNotes ===\n");
	}

	public static void testGetUneNote()
	{
		System.out.println("\n=== Methode testGetUneNote ===\n");

		// test indice dans les bornes
		nbTest++;
		Etudiant e1 = new Etudiant("toto", 5);
		if(e1.getUneNote(3) != -1)
		{
			nbTestOk++;
			System.out.println("[OK] Test cas normal");
		}
		else
		{
			System.out.println("[Failed] Test cas normal");
		}

		// test indice max
		nbTest++;
		if(e1.getUneNote(5) != -1)
		{
			nbTestOk++;
			System.out.println("[OK] Test indice max");
		}
		else
		{
			System.out.println("[Failed] Test indice max");
		}

		// test indice = 1
		nbTest++;
		if(e1.getUneNote(1) != -1)
		{
			nbTestOk++;
			System.out.println("[OK] Test indice = 1");
		}
		else
		{
			System.out.println("[Failed] Test indice = 1");
		}

		// test en dehors des bornes
		nbTest++;
		if(e1.getUneNote(-5) == -1)
		{
			nbTestOk++;
			System.out.println("[OK] Test en dehors des bornes");
		}
		else
		{
			System.out.println("[Failed] Test en dehors des bornes");
		}

		System.out.println("\n=== Fin methode testGetUneNote ===\n");
	}

	private static void testInitialisation()
	{
		System.out.println("\n=== Methode testInitialisation ===\n");

		// test si les notes sont entre 0 et 20
		nbTest++;
		Etudiant e1 = new Etudiant("toto", 10);
		boolean ok = true;
		for(int i = 1; i < 10; i++)
		{
			if(e1.getUneNote(i) > 20)
			{
				ok = false;
			}
			if(e1.getUneNote(i) < 0)
			{
				ok = false;
			}
		}

		if(ok)
		{
			nbTestOk++;
			System.out.println("[OK] Test notes entre 0 et 20");
		}
		else
		{
			System.out.println("[Failed] Test notes entre 0 et 20");
		}

		System.out.println("\n=== Fin methode testInitialisation ===\n");
	}

	private static void testMoyenne()
	{
		System.out.println("\n=== Methode testMoyenne ===\n");

		// test cas normal
		nbTest++;
		Etudiant e1 = new Etudiant("toto", 3);
		if(e1.moyenne() <= 20 && e1.moyenne() >= 0)
		{
			nbTestOk++;
			System.out.println("[OK] Test cas normal");
		}
		else
		{
			System.out.println("[Failed] Test cas normal");
		}

		// test notes null
		nbTest++;
		Etudiant e2 = new Etudiant("toto", -5);
		if(e2.moyenne() == -1)
		{
			nbTestOk++;
			System.out.println("[OK] Test notes null");
		}
		else
		{
			System.out.println("[Failed] Test notes null");
		}


		System.out.println("\n=== Fin methode testMoyenne ===\n");
	}
}