public class ScenarioPromotion
{
	public static void main(String[] args)
	{
		scenario1();
		scenario2();
	}

	public static void scenario1()
	{
		System.out.println("\n=== Methode scenario1 ===\n");

		Etudiant liste[] = new Etudiant[3];
		liste[0] = new Etudiant("Etu1", 8);
		liste[1] = new Etudiant("Etu2", 8);
		liste[2] = new Etudiant("Etu3", 8);

		Promotion promo = new Promotion("promo1", liste);

		System.out.println("info promotion :\n" + promo);

		System.out.println("\nModification de liste\n");

		liste[0] = new Etudiant("Etu1", 8);
		liste[1] = new Etudiant("Etu2", 8);
		liste[2] = new Etudiant("Etu3", 8);
		System.out.println("info promotion après modification:\n" + promo);

		System.out.println("\n=== Fin methode scenario1 ===\n");
	}

	public static void scenario2()
	{
		System.out.println("\n=== Methode scenario2 ===\n");

		Promotion promo = new Promotion("promo1", 5);
		Etudiant e1 = new Etudiant("Etu1", 8);
		Etudiant e2 = new Etudiant("Etu2", 8);
		Etudiant e3 = new Etudiant("Etu3", 8);

		System.out.println("\ninfo promotion :\n" + promo);

		promo.ajouteEtudiant(e1);
		promo.ajouteEtudiant(e2);
		promo.ajouteEtudiant(e3);

		System.out.println("\ninfo promotion après modification:\n" + promo);

		System.out.println("\n=== Fin methode scenario2 ===\n");
	}
}