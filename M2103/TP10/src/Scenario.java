import utilitaire.*;
import tri.*;
import pays.*;
import java.util.*;
import java.util.Scanner;

public class Scenario
{
	public static void main(String args[])
	{
		System.out.println("Création du tableau de pays");
		Population population = new Population("../data/worldpop.txt", "../data/worldarea.txt");
		Pays [] tabPays = new Pays [(population.getListePays()).size()];
		tabPays = (population.getListePays()).toArray(tabPays);

		// Choisi de facon arbitraire
		String aRech = tabPays[1].getNom();
		System.out.println("\nEst ce que '" + aRech + "' existe dans tableau de pays : " + BinarySearch.existenceDicho(tabPays, aRech));
		aRech = tabPays[3].getNom();
		System.out.println("\nOn souhaite récuperer l'objet pays '" + aRech + "'' : \n\n" + BinarySearch.rechercheDicho(tabPays, aRech));

		System.out.println("\nEntrez un nom de pays : ");
		Scanner sc = new Scanner(System.in);
		aRech = sc.nextLine();
		System.out.println("\nOn souhaite récuperer l'objet pays '" + aRech + "'' : \n\n" + BinarySearch.rechercheDicho(tabPays, aRech));

	}
}