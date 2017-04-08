package utilitaire;

import java.util.*;
import java.io.*;

/**
* Utility class containing static read / write of file methods
*/

public class RWFile
{
	/**
	* Make an ArrayList of String from a file
	* @param fileName the file name containing strings
	* @return the new ArrayList of Strings
	*/
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

	/**
	* Write String from an ArrayList into a file
	* @param liste the ArrayList
	* @param fileName the destination file name
	*/
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

	/**
	* Write infos from an HashMap into a file
	* @param map the HashMap
	* @param fileName the destination file name
	*/
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