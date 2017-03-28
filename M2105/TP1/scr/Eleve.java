import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Eleve
{
	private String nom;
	private String prenom;
	private float moyenne;
	ArrayList<Evaluation> notes;
	
	public Eleve(String nom, String prenom)
	{
		this.notes = new ArrayList<Evaluation>();
		
		if(nom != null && prenom != null)
		{
			this.nom = nom;
			this.prenom = prenom;
		}
		else
		{
			System.out.println("Nom et prenom exigés");
		}
	}
	
	public void ajouterNote(Evaluation eva)
	{
		if(eva != null)
			this.notes.add(eva);
		else
			System.out.println("Evaluation pas valide");
	}
	
	public void setMoyenne()
	{
		float coefTotal = 0;
		float noteTotal = 0;
		
		for(Evaluation eva : notes)
		{
			coefTotal += eva.getCoef();
			noteTotal += eva.getNote() * eva.getCoef();
		}
		
		if(coefTotal == 0)
		{
			this.moyenne = 0;
		}
		else
		{
			this.moyenne = noteTotal / coefTotal;
		}
	}
	
	public void lectureNoteFichier(String fichierLecture)
	{
		this.notes = new ArrayList<Evaluation>();
		
		try
		{
			BufferedReader fichier = new BufferedReader(new FileReader(fichierLecture));
			String lecture = fichier.readLine();
			
			while(lecture != null)
			{
				StringTokenizer st=new StringTokenizer(lecture, "/");
				
				String lectureNote = st.nextToken();
				String lectureCoef = st.nextToken();
							
				float note = Float.parseFloat(lectureNote);
				float coef = Float.parseFloat(lectureCoef);
				this.ajouterNote(new Evaluation(note, coef));
				
				lecture = fichier.readLine();
			}

			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println("Probleme de lecture de fichier");
			System.out.println(ex.getMessage());
		}
	}

	public void sauvegarderElevesFichier()
	{
		try
		{
			PrintWriter fichier = new PrintWriter(new FileWriter(this.nom + "-" + this.prenom));
			fichier.println(this.nom + "/" + this.prenom + "/" + this.moyenne);
			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public String toString()
	{
		String ret = "\nNom : " + this.nom;
		ret += "\nPrenom : " + this.prenom;
		ret += "\nMoyenne : " + this.moyenne;
		return ret;
	}
}
