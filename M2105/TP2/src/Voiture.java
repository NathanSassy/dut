public class Voiture
{
	private String marque;
	private String modele;
	private int puissance;

	public Voiture(String marque, String modele, int puissance)
	{
		if(marque != null && modele != null && puissance >= 0)
		{
			this.marque = marque;
			this.modele = modele;
			this.puissance = puissance;
		}
		else
		{
			System.out.println("Parametre invalide");
		}
	}

	public String toString()
	{
		String ret = "---- Voiture ----";
		ret += "\nMarque : " + this.marque;
		ret += "\nModele : " + this.modele;
		ret += "\nPuissance : " + this.puissance;
		return ret;
	}
}