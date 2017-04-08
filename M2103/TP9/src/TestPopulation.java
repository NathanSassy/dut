import utilitaire.*;
import tri.*;
import pays.*;
import java.util.*;

public class TestPopulation
{
	public static void main(String args[])
	{
		System.out.println("Création d'un objet population");
		Population population = new Population("../data/worldpop.txt", "../data/worldarea.txt");

		Pays [] tabPays = new Pays [(population.getListePays()).size()];
		tabPays = (population.getListePays()).toArray(tabPays);

		System.out.println("\n\n*****************************************\n\n");
		System.out.println("Tableau de Pays non trié : ");
		System.out.println (Arrays.toString(tabPays));

		System.out.println("\n\n*****************************************\n\n");
		System.out.println("Tableau de Pays trié avec tri par selection: ");

		long startTimeTPS = System.nanoTime();
		TriParSelection tps = new TriParSelection(tabPays);
		tps.trier();
		long estimatedTimeTPS = System.nanoTime()- startTimeTPS;
		String t1 = "\nTemps du TriParSelection = "+ estimatedTimeTPS + " nanoseconde";
		System.out.println (Arrays.toString(tabPays));


		System.out.println("\n\n*****************************************\n\n");
		System.out.println("Tableau de Pays trié avec tri rapide : ");

		tabPays = (population.getListePays()).toArray(tabPays);

		long startTimeTR = System.nanoTime();
		TriRapide tr = new TriRapide(tabPays);
		tr.trier();
		long estimatedTimeTR = System.nanoTime()- startTimeTR;
		String t2 = "\nTemps du TriRapide = "+ estimatedTimeTR + " nanoseconde";
		System.out.println (Arrays.toString(tabPays));

		System.out.println("\n\n*****************************************\n\n");
		System.out.println("Bilan des temps : " + t1 + t2);

	}

}