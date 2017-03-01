/**
* This class allow you to manipulate promotion object
*/
public class Promotion
{
	/**
	* Attributes of the Promotion class
	*/
	private String nom;
	private Etudiant listeEtudiants[];

	/**
	* number of students
	*/
	private int nbEtud;

	/**
	* The constructor of the Promotion class
	* @param nom the name of the promotion
	* @param liste the array containing students
	*/
	public Promotion(String nom, Etudiant liste[])
	{
		if(nom == null && liste != null)
		{
			System.out.println("le nom est null");
			this.nom = null;
			this.listeEtudiants = liste;
		}
		else if(nom != null && liste == null)
		{
			this.nom = nom;
			System.out.println("la liste est null");
		}
		else if(nom == null && liste == null)
		{
			System.out.println("le nom et la liste sont null");
		}
		else
		{
			this.nom = nom;
			this.listeEtudiants = liste;
			this.nbEtud = liste.length;
		}
	}

	/**
	* The constructor of the Promotion class
	* @param nom the name of the promotion
	* @param n maximum number of student
	*/
	public Promotion(String nom, int n)
	{
		if(nom == null)
		{
			System.out.println("le nom est null");
			this.nom = null;
		}
		if(n < 0)
		{
			System.out.println("le nombre d'etudiants doit etre positif");
			n = 0;
		}
		this.listeEtudiants = new Etudiant[n];
		this.nbEtud = 0;
	}

	/**
	* Method that add a student to the student array
	* @param e the student to add
	*/
	public void ajouteEtudiant(Etudiant e)
	{
		if(e == null)
		{
			System.out.println("impossible d'ajouter un etudiant null");
		}
		else if(e.getNbNotes() == 0)
		{
			System.out.println("impossible d'ajouter un etudiant sans note");
		}
		else if (this.nbEtud >= this.listeEtudiants.length)
		{
			System.out.println("impossible d'ajouter etudiant car le tableau d'etudiant est plein");
		}
		else
		{
			this.listeEtudiants[this.nbEtud] = e;
			this.nbEtud++;
		}
	}

	/**
	* Method to get the name of the promotion
	* @return return the promotion name
	*/
	public String getNom()
	{
		return this.nom;
	}

	/**
	* Method to set the name of the promotion
	* @param nom the new name of the promotion
	*/
	public void setNom(String nom)
	{
		if(nom == null)
		{
			System.out.println("le nom est null");
			this.nom = null;
		}
		else
		{
			this.nom = nom;
		}
	}

	/**
	* Method to get the avarage of all the student scores
	* @return the average
	*/
	public double moyenne()
	{
		double moy = 0;

		if(this.listeEtudiants == null)
		{
			System.out.println("La liste d'etudiants est null");
			moy = -1;
		}
		else if(this.nbEtud == 0)
		{
			System.out.println("La liste d'etudiants est vide");
			moy = -1;
		}
		else
		{
			for(int i = 0; i < this.nbEtud; i++)
			{
				moy += this.listeEtudiants[i].moyenne();
			}
			moy /= this.nbEtud;
		}
		return moy;
	}

	/**
	* Method to get the biggest avarage of all the student scores
	* @return the biggest average
	*/
	public double moyenneMax()
	{
		double max = 0;
		if(this.listeEtudiants == null)
		{
			System.out.println("La liste d'etudiants est null");
			max = -1;
		}
		else if(this.nbEtud == 0)
		{
			System.out.println("La liste d'etudiants est vide");
			max = -1;
		}
		else
		{
			for(int i = 0; i < this.nbEtud; i++)
			{
				if(this.listeEtudiants[i].moyenne() > max)
				{
					max = this.listeEtudiants[i].moyenne();
				}
			}
		}

		return max;
	}

	/**
	* Method to get the lower avarage of all the student scores
	* @return the lower average
	*/
	public double moyenneMin()
	{
		double min = 0;
		if(this.listeEtudiants == null)
		{
			System.out.println("La liste d'etudiants est null");
			min = -1;
		}
		else if(this.nbEtud == 0)
		{
			System.out.println("La liste d'etudiants est vide");
			min = -1;
		}	
		else
		{
			min = this.listeEtudiants[0].moyenne();
			for(int i = 0; i < this.nbEtud; i++)
			{
				if(this.listeEtudiants[i].moyenne() < min)
				{
					min = this.listeEtudiants[i].moyenne();
				}
			}
		}
		return min;
	}

	/**
	* Method to get the student who have the biggest score average
	* @return the major
	*/
	public Etudiant getMajor()
	{
		Etudiant major = null;
		double moyMax = this.moyenneMax();

		if(moyMax != -1)
		{
			int i = 0;
			while(moyMax != this.listeEtudiants[i].moyenne())
			{
				i++;
			}
			major = this.listeEtudiants[i];
		}

		return major;
	}

	/**
	* Method to introduce the promotion by giving the averages scores
	* @return the string to introduce the promotion
	*/
	public String toString()
	{
		String ret = "La promotion = " + this.nom;
		
		if (this.moyenne() != -1)
		{
			ret += "\nMoyenne = " + this.moyenne();
			ret += "\nMoyenne Min = " + this.moyenneMin();
			ret += "\nMoyenne Max = " + this.moyenneMax();
		}

		if(this.getMajor() == null)
		{
			ret += "\nIl n'y a pas de major";
		}
		else
		{
			Etudiant major = this.getMajor();
			ret += "\nLe major s'appelle : " + major.getNom();
		}
		return ret;
	}
}