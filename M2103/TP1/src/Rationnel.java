/**
* This class allow you to manipulate rational numbers
*/
public class Rationnel
{
	/**
	* Attributes of the Rationnel class
	*/
	private int numerateur;
	private int denominateur;

	/**
	* The constructor of the Rationnel class
	* @param n the numerator
	* @param d the denominator
	*/
	public Rationnel(int n, int d)
	{
		if(d == 0)
		{
			System.out.println("Le denominateur ne peut pas etre nul");
		}
		else if(d < 0)
		{
			this.numerateur = n * -1;
			this.denominateur = d * -1;
		}
		else
		{
			this.numerateur = n;
			this.denominateur = d;
		}

	}

	/**
	* Method to get the numerator value
	* @return return the numerator
	*/
	public int getNumerateur()
	{
		return this.numerateur;
	}

	/**
	* Method to set the numerator value
	* @param n the new value of the numerator
	*/
	public void setNumerateur(int n)
	{
		this.numerateur = n;
	}

	/**
	* Method to get the denominator value
	* @return return the denominator
	*/
	public int getDenominateur()
	{
		return this.denominateur;
	}

	/**
	* Method to set the denominator value
	* @param d the new value of the denominator
	*/
	public void setDenominateur(int d)
	{
		this.denominateur = d;
	}

	/**
	* Method that calculate the pgcd
	* @param a the numerator
	* @param b the denominator
	* @return return the pgcd
	*/
	private int pgcd(int a, int b)
	{
		int resultat;
		
		if(b == 0)
		{
			resultat = a;
		}
		else
		{
			resultat = pgcd(b, a%b);
		}

		return resultat;
	}

	/**
	* Reduce the rational number
	*/
	private void reduce()
	{
		int div = pgcd(this.numerateur, this.denominateur);
		this.numerateur /= div;
		this.denominateur /= div;
	}

	/**
	* Method that calculate the reverse of the number 
	* @return return the reverse
	*/
	public Rationnel inverse()
	{
		Rationnel resultat = new Rationnel(this.denominateur, this.numerateur);

		return resultat;
	}

	/**
	* Method that calculate an addition of the object and the parameter
	* @param unNR the rational number to add
	* @return the result of the addition
	*/
	public Rationnel ajoute(Rationnel unNR)
	{
		Rationnel resultat = new Rationnel(1, 1);

		resultat.setNumerateur(unNR.getNumerateur()*this.denominateur+this.numerateur*unNR.getDenominateur());
		resultat.setDenominateur(unNR.getDenominateur()*this.denominateur);

		resultat.reduce();
		return resultat;
	}

	/**
	* Method that calculate an substraction of the object and the parameter
	* @param unNR the rational number to substracte
	* @return the result of the substraction
	*/
	public Rationnel soustrait(Rationnel unNR)
	{
		Rationnel resultat = new Rationnel(1, 1);

		resultat.setNumerateur(unNR.getNumerateur()*this.denominateur-this.numerateur*unNR.getDenominateur());
		resultat.setDenominateur(unNR.getDenominateur()*this.denominateur);

		resultat.reduce();
		return resultat;
	}

	/**
	* Method that calculate an multiplication of the object and the parameter
	* @param unNR the rational number to multiplicate
	* @return the result of the multiplication
	*/
	public Rationnel multiplie(Rationnel unNR)
	{
		Rationnel resultat = new Rationnel(1, 1);
		resultat.setDenominateur(this.denominateur * unNR.getDenominateur());
		resultat.setNumerateur(this.numerateur * unNR.getNumerateur());
		resultat.reduce();
		return resultat;
	}

	/**
	* Method that return true if both national numbers are equals
	* @param nr2 the rational number to compare
	* @return true if equaled
	*/
	public boolean equals(Rationnel nr2)
	{
		boolean resultat = false;

		this.reduce();
		nr2.reduce();

		if(this.denominateur == nr2.getDenominateur() && this.numerateur == nr2.getNumerateur())
		{
			resultat = true;
		}

		return resultat;
	}

	/**
	* Method that give information about the rational number
	* @return the String with contain the informations
	*/
	public String toString()
	{
		String information = "denominateur = " + this.denominateur + ", numerateur = " + this.numerateur;
		return information;
	}
}