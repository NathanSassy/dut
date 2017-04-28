package tri;
import pays.*;

public class BinarySearch
{
	/**
	* Binary Searcher on pays array
	* @param pays the pays array
	* @param aRech the pays we have to find
	* @return the pays if found, null if not found
	*/
	public static Pays rechercheDicho(Pays[] pays, String aRech)
	{
		Pays ret = null;

		if(pays != null && pays.length > 0 && aRech != null)
		{
			triParSelectionAlpha tpsa =  new triParSelectionAlpha(pays);
			tpsa.trier();
			pays = tpsa.getTab();

			int indD = 0;
			int indM = 0;
			int indF = pays.length - 1;
			
			while(pays[indD].getNom().compareToIgnoreCase(pays[indF].getNom()) < 0 && ret == null)
			{
				indM = (indD + indF) / 2;
				
				if(aRech.compareToIgnoreCase(pays[indM].getNom()) == 0)
				{
					ret = pays[indM];
				}
				else if(pays[indM].getNom().compareToIgnoreCase(aRech) < 0)
				{
					indD = indM + 1;
				}
				else
				{
					indF = indM;
				}
			}
		}

		return ret;
	}

	/**
	* Binary Chercker on pays array
	* @param pays the pays array
	* @param aRech the pays we have to find
	* @return true if pays exists
	*/
	public static boolean existenceDicho(Pays[] pays, String aRech)
	{
		boolean ret = false;

		if(pays != null && pays.length > 0 && aRech != null)
		{
			triParSelectionAlpha tpsa =  new triParSelectionAlpha(pays);
			tpsa.trier();
			pays = tpsa.getTab();

			int indD = 0;
			int indM = 0;
			int indF = pays.length - 1;
			
			while(pays[indD].getNom().compareToIgnoreCase(pays[indF].getNom()) < 0 && !ret)
			{
				indM = (indD + indF) / 2;

				if(aRech.compareToIgnoreCase(pays[indM].getNom()) == 0)
				{
					ret = true;
				}
				else if(pays[indM].getNom().compareToIgnoreCase(aRech) < 0)
				{
					indD = indM + 1;
				}
				else
				{
					indF = indM;
				}
			}
		}

		return ret;
	}
}