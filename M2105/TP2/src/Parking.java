public class Parking
{
	private final int NB_PLACES = 2;
	private Voiture lesPlaces[];

	public Parking()
	{
		this.lesPlaces = new Voiture[this.NB_PLACES];
	}

	public String toString()
	{
		String ret = "****** PARKING ******";
		for(int i = 0; i < lesPlaces.length; i++)
		{
			if(lesPlaces[i] == null)
			{
				ret += "\nPlace n°" + i + " libre";
			}
			else
			{
				ret += "\nPlace n°" + i + " occupé par :";
				ret += lesPlaces[i].toString();
			}
		}
		ret += "\n*********************";
		return ret;
	}

	private void numeroValide(int numPlace) throws ExceptionParking
	{
		if(numPlace < 0 && numPlace >= this.NB_PLACES)
			throw new ExceptionParking("Numéro de place non valide");
	}

	public void garer(Voiture voit, int numPlace) throws ExceptionParking
	{
		this.numeroValide(numPlace);

		if(this.lesPlaces[numPlace] == null)
		{
			this.lesPlaces[numPlace] = voit;
		}
		else
		{
			throw new ExceptionParking("Place déjà occupée");
		}
	}

	public Voiture sortir(int numPlace) throws ExceptionParking
	{
		Voiture voit = null;
		this.numeroValide(numPlace);

		if(this.lesPlaces[numPlace] != null)
		{
			voit = this.lesPlaces[numPlace];
			this.lesPlaces[numPlace] = null;
		}
		else
		{
			throw new ExceptionParking("Pas de voiture à cette place");
		}
		
		return voit;
	}
}