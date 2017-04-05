public class TestParking
{
	private static Parking p = new Parking();
	private static Voiture v1 = new Voiture("A", "B", 42);
	private static Voiture v2 = new Voiture("C", "D", 86);

	public static void main(String[] args)
	{
		// Affichage de parking vide
		System.out.println(p);

		// Test de garer
		testGarer();

		// Test de sortir
		testSortir();
	}

	public static void testGarer()
	{
		// Garer avec numPlace non valide
		try
		{
			p.garer(v1, -2);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}

		// Garer sans prob
		try
		{
			p.garer(v1, 0);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}

		// Garer avec place non dispo
		try
		{
			p.garer(v1, 0);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void testSortir()
	{
		// Sorit avec numPlace non valide
		try
		{
			p.sortir(-2);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}

		// Sortir sans prob
		try
		{
			p.garer(v1, 0);
			p.sortir(0);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}

		// Sorit avec place non dispo
		try
		{
			p.sortir(0);
		}
		catch(ExceptionParking e)
		{
			System.out.println(e.getMessage());
		}

	}
}