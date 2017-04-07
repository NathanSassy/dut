package utilitaire;

import java.util.*;
import java.io.*;

public class RWFile
{
	public static ArrayList<String> readFile(String fileName)
	{
		ArrayList<String> al = new ArrayList<String>();
		BufferedReader fichier = null;
		
		try
		{
			fichier = new BufferedReader(new FileReader(fileName));
			String lecture = fichier.readLine();
			
			while(lecture != null)
			{
				al.add(lecture);
				lecture = fichier.readLine();
			}

			fichier.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}

		return al;
	}

	public static void writeFile(ArrayList<String> liste, String fileName)
	{
		if(liste != null && fileName != null)
		{
			try
			{
				PrintWriter fichier = new PrintWriter(new FileWriter(fileName, true));

				for(String s : liste)
				{
					fichier.println(s);
				}

				fichier.close();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	public static void writeMap(HashMap<String, Double> map, String fileName)
	{
		if(map != null && fileName != null)
		{
			try
			{
				PrintWriter fichier = new PrintWriter(new FileWriter(fileName, true));

				for (Map.Entry<String, Double> m : map.entrySet())
				{
				    fichier.println(m.getKey() + " /t" + m.getValue());
				}

				fichier.close();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}