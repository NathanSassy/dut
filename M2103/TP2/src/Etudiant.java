/**
* This class allow you to manipulate student object
*/
public class Etudiant
{
	/**
	* Attributes of the Etudiant class
	*/
	private String nom;
	private double notes[];

	/**
	* The constructor of the Etudiant class
	* @param nom the name of the student
	* @param n the number of score
	*/
	public Etudiant(String nom, int n)
	{
		if(nom == null)
		{
			System.out.println("le nom est null");
		}
		else if(n <= 0)
		{
			System.out.println("le nombre de notes doit etre strictement positif");
		}
		else
		{
			this.nom = nom;
			this.notes = new double[n];
			this.initialisation();
		}
	}

	/**
	* Method to get the name of the student
	* @return return the student name
	*/
	public String getNom()
	{
		return this.nom;
	}

	/**
	* Method to set the name of the student
	* @param nom the new name of the student
	*/
	public void setNom(String nom)
	{
		if(nom == null)
		{
			System.out.println("le nom est null");
		}
		else
		{
			this.nom = nom;
		}
	}

	/**
	* Methode to get the number of scores
	* @return the number of scores
	*/
	public int getNbNotes()
	{
		int ret = 0;

		if(this.notes != null)
		{
			ret = this.notes.length;
		}

		return ret;
	}

	/** Method to get one score in the scores array
	* @param i the index of the scores array
	* @return the value of score
	*/
	public double getUneNote(int i)
	{
		double ret = 0;
		i--;

		if(i >= this.notes.length || i < 0)
		{
			System.out.println("Indice en dehors des bornes");
			ret = -1;
		}
		else
		{
			ret = this.notes[i];
		}

		return ret;
	}

	/**
	* Method to initialyse the score of the score 
	* array with a number between 0 and 20
	*/
	private void initialisation()
	{
		for(int i = 0; i < this.notes.length; i++)
		{
			this.notes[i] = Math.random() * 20;
		}
	}

	/**
	* Method to get the avarage of the student scores
	* @return the average
	*/
	public double moyenne()
	{
		double moy = 0;

		if(this.getNbNotes() <= 0)
		{
			System.out.println("Nombre de notes doit etre strictement positif");
			moy = -1;
		}
		else
		{
			for(int i = 0; i < this.notes.length; i++)
			{
				moy += this.notes[i];
			}
			moy /= this.notes.length;
		}

		return moy;
	}

	/**
	* Method to introduce the student by giving his name and his scores
	* @return the string to introduce the student
	*/
	public String toString()
	{
		String ret = "L'etudiant s'appelle : " + this.nom;
		ret += "\nIl a une moyenne de : " + this.moyenne();
		
		for(int i = 0; i < this.notes.length; i++)
		{
			ret += "\nNote n°" + i + " = " + this.notes[i];
		}

		return ret;
	}

}