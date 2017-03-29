import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;
import java.nio.ByteBuffer;

public class Eleve implements Serializable
{
	private String nom;
	private String prenom;
	private float moyenne;
	transient ArrayList<Evaluation> notes;
	
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
			fichier.println("\nNotes: ");
			for(Evaluation eva : notes)
			{
				fichier.println(eva.getNote() +"/" + eva.getCoef());
			}

			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void sauvegarderElevesFichierBin()
	{
		try
		{
			DataOutputStream fichier = new DataOutputStream(new FileOutputStream(this.nom + "-" + this.prenom + "-bin"));
			
			fichier.writeBytes(this.nom + "/" + this.prenom);
			fichier.writeFloat(this.moyenne);
			fichier.writeBytes("Notes:");
			for(Evaluation eva : notes)
			{
				fichier.writeFloat(eva.getNote());
				fichier.writeBytes("\n");
				fichier.writeFloat(eva.getCoef());
				fichier.writeBytes("\n");
			}

			/* niveau de taille de fichier ca ne change 	*
			 * peu de chose car les floats sont sur 4 bytes */

			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void lectureNoteFichierBin(String fichierLecture)
	{
		this.notes = new ArrayList<Evaluation>();
		
		try
		{
			DataInputStream fichier = new DataInputStream(new FileInputStream(fichierLecture));
			byte lecture[] = new byte[4];
			int fin = 0;
			float note = 0;
			float coef = 0;
			fin = fichier.read(lecture);
			
			while(fin != -1)
			{
				note = ByteBuffer.wrap(lecture).getFloat();
				fin = fichier.read(lecture);
				if(fin != -1)
				{
					coef = ByteBuffer.wrap(lecture).getFloat();
					this.ajouterNote(new Evaluation(note, coef));
					fin = fichier.read(lecture);
				}
			}

			fichier.close();
		}
		catch (Exception ex)
		{
			System.out.println("Probleme de lecture de fichier");
			System.out.println(ex.getMessage());
		}
	}

	public static void serialisationEleve(String fichier, Eleve e)
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

	public static Eleve deserialisationEleve(String fichier)
	{
		Eleve retE = null;
		try
		{
			FileInputStream fos = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fos);
			retE = (Eleve) ois.readObject();
		}
		catch (Exception ex)
		{
			System.out.println("Probleme de deserialisation");
			System.out.println(ex.getMessage());
		}
		return retE;
	}
	
	public String toString()
	{
		String ret = "\nNom : " + this.nom;
		ret += "\nPrenom : " + this.prenom;
		ret += "\nMoyenne : " + this.moyenne;
		return ret;
	}
}
