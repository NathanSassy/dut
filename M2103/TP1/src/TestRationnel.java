public class TestRationnel
{
	public static void main(String[] args)
	{
		testInstantiation();
		testAttributs();
		//testReduce();
		testOperations();
		testEquals();
	}

	public static void testInstantiation()
	{
		System.out.println("\n=== Methode testInstantiation ===\n");

		System.out.println("Creation et affichage avec denominateur = 0");
		Rationnel rat1 = new Rationnel(1,0);
		System.out.println(rat1.toString());
		System.out.println("Creation et affichage avec denominateur < 0");
		rat1 = new Rationnel(1,-2);
		System.out.println(rat1.toString());
		System.out.println("Creation et affichage avec denominateur && numerateur < 0");
		rat1 = new Rationnel(-1,-2);
		System.out.println(rat1.toString());
		System.out.println("Creation et affichage avec denominateur && numerateur > 0");
		rat1 = new Rationnel(1,2);
		System.out.println(rat1.toString());

		System.out.println("\n=== Fin methode testInstantiation ===\n");
	}

	public static void testAttributs()
	{
		System.out.println("\n=== Methode testAttributs ===\n");

		System.out.println("Creation d'un rationnel tel que : ");
		Rationnel rat1 = new Rationnel(4,2);
		System.out.println(rat1.toString());

		System.out.println("\nTest acces des getters");
		System.out.println("getDenominateur = " + rat1.getDenominateur());
		System.out.println("getNumerateur = " + rat1.getNumerateur());

		System.out.println("\nTest acces des setters");
		System.out.println("Execution de : setDenominateur(10) et setNumerateur(5)");
		rat1.setNumerateur(5);
		rat1.setDenominateur(10);
		System.out.println(rat1.toString());

		System.out.println("\n=== Fin methode testAttributs ===\n");
	}


	// you have to put reduce() in public to run this method
	/*public static void testReduce()
	{
		System.out.println("\n=== Methode testReduce ===\n");

		Rationnel rat1 = new Rationnel(20,25);
		Rationnel rat2 = new Rationnel(9,3);
		Rationnel rat3 = new Rationnel(156,16);
		Rationnel rat4 = new Rationnel(40,4);

		System.out.println("Reduction de rat1 tel que : ");
		System.out.println(rat1.toString());
		rat1.reduce();
		System.out.println(rat1.toString());

		System.out.println("Reduction de rat2 tel que : ");
		System.out.println(rat2.toString());
		rat2.reduce();
		System.out.println(rat2.toString());

		System.out.println("Reduction de rat3 tel que : ");
		System.out.println(rat3.toString());
		rat3.reduce();
		System.out.println(rat3.toString());

		System.out.println("Reduction de rat4 tel que : ");
		System.out.println(rat4.toString());
		rat4.reduce();
		System.out.println(rat4.toString());

		System.out.println("\n=== Fin methode testReduce ===\n");
	}*/

	public static void  testOperations()
	{
		System.out.println("\n=== Methode testOperations ===\n");

		System.out.println("Creation du rationnel n° 1 tel que : ");
		Rationnel rat1 = new Rationnel(8,5);
		System.out.println(rat1.toString());
		System.out.println("Creation du rationnel n° 2 tel que : ");
		Rationnel rat2 = new Rationnel(7,3);
		System.out.println(rat2.toString());

		System.out.println("\nTest addition des deux rationnels : ");
		Rationnel rat3 = rat1.ajoute(rat2);
		System.out.println(rat3.toString());

		System.out.println("\nTest soustraction des deux rationnels : ");
		rat3 = rat1.soustrait(rat2);
		System.out.println(rat3.toString());

		System.out.println("\nTest multiplication des deux rationnels : ");
		rat3 = rat1.multiplie(rat2);
		System.out.println(rat3.toString());

		System.out.println("\nTest inverse du premier rationnel : ");
		rat3 = rat1.inverse();
		System.out.println(rat3.toString());		

		System.out.println("\n=== Fin methode testOperations ===\n");
	}

	public static void testEquals()
	{
		System.out.println("\n=== Methode testEquals ===\n");

		System.out.println("Creation du rationnel n° 1 tel que : ");
		Rationnel rat1 = new Rationnel(1,4);
		System.out.println(rat1.toString());
		System.out.println("Creation du rationnel n° 2 tel que : ");
		Rationnel rat2 = new Rationnel(25,100);
		System.out.println(rat2.toString());
		System.out.println("Creation du rationnel n° 3 tel que : ");
		Rationnel rat3 = new Rationnel(5,3);
		System.out.println(rat3.toString());

		System.out.println("\nLes rationnels 1 et 2 sont ils egaux ? " + rat1.equals(rat2));
		System.out.println("Les rationnels 1 et 3 sont ils egaux ? " + rat1.equals(rat3));

		System.out.println("\n=== Fin methode testEquals ===\n");
	}

}