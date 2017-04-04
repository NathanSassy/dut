import java.io.*;
import java.util.Scanner;

public class Tableau
{
	private int longueur;
	private int tab[];

	public Tableau(int longueur) throws ErrTableau
	{
		if(longueur >= 0)
		{
			this.longueur = longueur;
			this.tab = new int [longueur];
		}
		else
		{
			throw new ErrTableau(longueur);
		}
	}

	public Tableau(String fichierNom) throws ErrTableau, FileNotFoundException
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File(fichierNom));

			int taille = scanner.nextInt();
			if(taille < 0)
				throw new ErrTableau(taille);

			this.tab = new int[taille];
			int i = 0;
			
			while(scanner.hasNextInt() && i < taille)
			{
				this.tab[i] = scanner.nextInt();
				i++;
			}
		}
		catch(FileNotFoundException e)
		{
			throw new ErrTableau("Probleme d'ouverture du fichier"); 
		}
		finally
		{
			scanner.close();
		}
	}

	/*
	public Tableau(String fichierNom) throws IOException
	{
		Scanner scanner = new Scanner(new File(fichierNom));

		int taille = scanner.nextInt();
		this.tab = new int[taille];
		int i = 0;
		while(scanner.hasNextInt() && i < taille)
		{
			this.tab[i] = scanner.nextInt();
			i++;
		}
	}
	*/

	public int getLongueur()
	{
		return this.longueur;
	}

	public int getTab(int i)
	{
		int ret = -1;
		//if(i >= 0 && i < this.longueur)
			ret = this.tab[i];
		return ret;
	}

	public void setTab(int i, int value)
	{
		if(i >= 0 && i < this.longueur)
			this.tab[i] = value;
	}
}