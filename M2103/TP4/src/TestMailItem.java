public class TestMailItem
{
	static private int nbTest = 0;
	static private int nbTestOk = 0;

	public static void main(String[] args)
	{
		testConstructeurEtGetteur();

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

	public static void testConstructeurEtGetteur()
	{
		System.out.println("\n=== Methode testConstructeurEtGetteur ===\n");

		String to = "quelqu'un";
		String from = "moi";
		String message = "test";

		// cas normal
		MailItem i1 = new MailItem(from, to, message);
		nbTest++;
		if(i1 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet i1 crée");
			validationAttributs(i1, from, to, message);
		}
		else
		{
			System.out.println("[Failed] Objet i1 null");
		}

		// cas avec un attribut null
		MailItem i2 = new MailItem(null, to, message);
		nbTest++;
		if(i2 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet i2 crée");
			validationAttributs(i2, "", to, message);
		}
		else
		{
			System.out.println("[Failed] Objet i2 null");
		}

		MailItem i3 = new MailItem(from, null, message);
		nbTest++;
		if(i3 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet i3 crée");
			validationAttributs(i3, from, "", message);
		}
		else
		{
			System.out.println("[Failed] Objet i3 null");
		}

		MailItem i4 = new MailItem(from, to, null);
		nbTest++;
		if(i4 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet i4 crée");
			validationAttributs(i4, from, to, "");
		}
		else
		{
			System.out.println("[Failed] Objet i4 null");
		}

		MailItem i5 = new MailItem(null, null, null);
		nbTest++;
		if(i5 != null)
		{
			nbTestOk++;
			System.out.println("[OK] Objet i5 crée");
			validationAttributs(i5, "", "", "");
		}
		else
		{
			System.out.println("[Failed] Objet i5 null");
		}

		System.out.println("\n=== Fin methode testConstructeurEtGetteur ===\n");
	}

	public static void validationAttributs(MailItem item, String from, String to, String message)
	{
		nbTest++;
		if (item.getTo().equals(to))
		{
			System.out.println("[OK] Attributs valides");
			nbTestOk++;
		}
		else
		{
			System.out.println("[Failed] Attributs invalides");
		}

		nbTest++;
		if (item.getFrom().equals(from))
		{
			System.out.println("[OK] Attributs valides");
			nbTestOk++;
		}
		else
		{
			System.out.println("[Failed] Attributs invalides");
		}

		nbTest++;
		if (item.getMessage().equals(message))
		{
			System.out.println("[OK] Attributs valides");
			nbTestOk++;
		}
		else
		{
			System.out.println("[Failed] Attributs invalides");
		}
	}

}