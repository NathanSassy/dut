import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Evaluation
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
	
	
}
