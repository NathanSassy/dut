public class TestMailServer
{
	static private int nbTest = 0;
	static private int nbTestOk = 0;

	public static void main(String[] args)
	{
		testConstructeur();
		testHowManyItems();
		testGetNextMailItem();

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
		MailServer s1 = new MailServer();
		if(s1 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet crée");
		}
		else
		{
			System.out.println("[Failed] Objet null");
		}

		System.out.println("\n=== Fin methode testConstructeur ===\n");
	}

	public static void testHowManyItems()
	{
		System.out.println("\n=== Methode testHowManyItems ===\n");

		MailServer s1 = new MailServer();
		String who1 = "foo1";
		String who2 = "foo2";

		// les items
		MailItem i1 = new MailItem("", who1, "");
		MailItem i2 = new MailItem("", who1, "");
		MailItem i3 = new MailItem("", who1, "");
		MailItem i4 = new MailItem("", who2, "");

		// post
		s1.post(i1);
		s1.post(i2);
		s1.post(i3);
		s1.post(i4);

		// cas normaux
		nbTest++;
		if(s1.howManyItems(who1) == 3)
		{
			nbTestOk++;
			System.out.println("[OK] Nombre de posts valide pour who1");
		}
		else
		{
			System.out.println("[Failed] Nombre de posts invalide pour who1");
		}

		nbTest++;
		if(s1.howManyItems(who2) == 1)
		{
			nbTestOk++;
			System.out.println("[OK] Nombre de posts valide pour who2");
		}
		else
		{
			System.out.println("[Failed] Nombre de posts invalide pour who2");
		}

		// cas limite
		nbTest++;
		if(s1.howManyItems("azerty") == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Nombre de posts = 0");
		}
		else
		{
			System.out.println("[Failed] Nombre de posts != 0");
		}

		// cas critique
		nbTest++;
		if(s1.howManyItems(null) == 0)
		{
			nbTestOk++;
			System.out.println("[OK] Nombre de posts = 0 pour who null");
		}
		else
		{
			System.out.println("[Failed] Nombre de posts != 0 pour who null");
		}

		System.out.println("\n=== Fin methode testHowManyItems ===\n");
	}

	public static void testGetNextMailItem()
	{
		System.out.println("\n=== Methode testGetNextMailItem ===\n");

		MailServer s1 = new MailServer();
		String who1 = "foo1";


		// les items
		MailItem i1 = new MailItem("", who1, "message 1");
		MailItem i2 = new MailItem("", who1, "message 2");
		MailItem i3 = new MailItem("", who1, "message 3");

		// post
		s1.post(i1);
		s1.post(i2);
		s1.post(i3);

		// cas normal
		nbTest++;
		if(s1.getNextMailItem(who1).equals(i1))
		{
			nbTestOk++;
			System.out.println("[OK] Post le plus ancien retrouvé");
		}
		else
		{
			System.out.println("[Failed] Post le plus ancien non retrouvé");
		}

		// cas limite
		nbTest++;
		if(s1.getNextMailItem("azerty") == null)
		{
			nbTestOk++;
			System.out.println("[OK] Renvoie null car destinataire inconnu");
		}
		else
		{
			System.out.println("[Failed] Ne renvoie pas null alorq que destinataire inconnu");
		}

		// cas critique
		nbTest++;
		if(s1.getNextMailItem(null) == null)
		{
			nbTestOk++;
			System.out.println("[OK] Renvoie null car destinataire null");
		}
		else
		{
			System.out.println("[Failed] Ne renvoie pas null alorq que destinataire null");
		}

		System.out.println("\n=== Fin methode testGetNextMailItem ===\n");
	}

}