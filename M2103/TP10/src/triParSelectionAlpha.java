package tri;
import pays.*;

/**
* This class allows you to sort alphabetically the density hashmap 
*/
public class triParSelectionAlpha
{
	private Pays tab[];

	/**
	* The constructor of triParSelectionAlpha
	* @param tab the array of Pays
	*/
	public triParSelectionAlpha(Pays tab[])
	{
		if(tab != null)
		{
			this.tab = tab;
		}
	}

	/**
	* This method gives the position of the country
	* having the lowest habitation number
	* @param debut the begining of the array
	* @return the index
	*/
	public int minimumPosition(int debut)
	{
		int pos = debut;
		if(debut <= tab.length && debut >= 0)
		{
			for(int i = debut; i < this.tab.length; i++)
			{
				if(tab[i].getNom().compareToIgnoreCase(this.tab[pos].getNom()) < 0)
					pos = i;
			}
		}
		return pos;
	}

	/**
	* This method allows you to swap
	* to object in the Pays array
	* @param i first index
	* @param j second index
	*/
	public void swap(int i, int j)
	{
		Pays k = this.tab[i];
		this.tab[i] = this.tab[j];
		this.tab[j] = k;
	}

	/**
	* This method allows you to sort the Pays array
	* by the min habitation number to the max
	*/
	public void trier()
	{
		int pos = 0;
		for(int i = 0; i < this.tab.length-1; i++)
		{
			pos = minimumPosition(i);
			swap(i, pos);
		}
	}

	/**
	* @return the pays array
	*/
	public Pays[] getTab()
	{
		return this.tab;
	}
}