package pays;

/**
* This class allows you to manipulate country object
*/
public class Pays implements Comparable<Pays>
{
	private String nom;
	private double surface;
	private double population;

	/**
	* Constructor of Pays
	* @param nom The name of the country
	* @param population The population of the country
	* @param surface The surface of the country
	*/
	public Pays(String nom, double population, double surface)
	{
		if(nom != null && population >= 0 && surface >= 0)
		{
			this.nom = nom;
			this.population = population;
			this.surface = surface;
		}
	}

	/**
	* Compare the population with their population
	* @param pays The country you want to compare
	*/
	public int compareTo(Pays pays)
	{
		int ret = -1;
		if(this.surface == pays.getSurface())
			ret = 0;
		else if (this.surface > pays.getSurface())
			ret = 1;
		return ret;
	}

	/**
	* @return the name of the country
	*/
	public String getNom()
	{
		return this.nom;
	}

	/**
	* @param nom the new name of the country
	*/
	public void setNom(String nom)
	{
		if(nom != null)
		{
			this.nom = nom;
		}
	}

	/**
	* @return the surface of the country
	*/
	public double getSurface()
	{
		return this.surface;
	}

	/**
	* @param surface the new surface of the country
	*/
	public void setSurface(double surface)
	{
		if(surface >= 0)
		{
			this.surface = surface;
		}
	}

	/**
	* @return the population of the country
	*/
	public double getPopulation()
	{
		return this.population;
	}

	/**
	* the new population of the country
	*/
	public void setPopulation(double population)
	{
		if(population >= 0)
		{
			this.population = population;
		}
	}

	/**
	* @return String describing the country
	*/
	public String toString()
	{
		String ret = "----- " + this.nom + " -----";
		ret += "\nsurface : " + this.surface;
		ret += "\npopulation : " + this.population;
		ret += "\n\n";
		return ret;
	}
}