import java.io.*;

public class Evaluation implements Serializable
{
	private float coef;
	private float note;
	
	public Evaluation(float note, float coef)
	{
		if(note <= 20 && note >= 0)
			this.note = note;
		else
			System.out.println("Note pas valide");
			
		if(coef <= 10 && coef >= 0)
			this.coef = coef;
		else
			System.out.println("Coefficient pas valide");
	}
	
	public float getNote()
	{
		return this.note;
	}
	
	public float getCoef()
	{
		return this.coef;
	}
	
	public void sauvegarderNotesFichier(String fichierEcriture)
	{
		try
		{
			PrintWriter fichier = new PrintWriter(new FileWriter(fichierEcriture, true));
			fichier.println(this.note + "/" + this.coef);
			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void sauvegarderNotesFichierBin(String fichierEcriture)
	{
		try
		{
			DataOutputStream fichier = new DataOutputStream(new FileOutputStream(fichierEcriture, true));
			fichier.writeFloat(this.note);
			fichier.writeFloat(this.coef);
			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public static void serialisationEvaluation(String fichier, Evaluation e)
	{
		if (e != null)
		{
			try
			{
				FileOutputStream fos = new FileOutputStream(fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(e) ;
				oos.close();
			}
			catch (Exception ex)
			{
				System.out.println("Probleme de Serialisation");
				System.out.println(ex.getMessage());
			}
		}
	}

	public static Evaluation deserialisationEvaluation(String fichier)
	{
		Evaluation retEva = null;
		try
		{
			FileInputStream fos = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fos);
			retEva = (Evaluation) ois.readObject();
		}
		catch (Exception ex)
		{
			System.out.println("Probleme de deserialisation");
			System.out.println(ex.getMessage());
		}
		return retEva;
	}

	public String toString()
	{
		String ret = "\nNote : " + this.note;
		ret += "\nCoef : " + this.coef;
		return ret;
	}
	
}
