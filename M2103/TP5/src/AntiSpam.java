package mail;

import java.util.ArrayList;
import java.util.Iterator;

/**
* This class allow you to manipulate AntiSpam object
* @author Antoine Gicquel
* @version 1.0
*/
public class AntiSpam
{
	/**
	* Attribute of the AntiSpam class
	*/
	private ArrayList<String> filtres;

	/**
	* The constructor of the AntiSpam class
	* @param filtres Array List containing banned words
	*/
	public AntiSpam(ArrayList<String> filtres)
	{
		if(filtres != null)
		{
			this.filtres = filtres;
		}
		else
		{
			this.filtres = new ArrayList<String>();
		}
	}

	/**
	* Method to detect if the mail is a spam or not
	* @param message to scan
	* @return return true if the mail is a spam
	*/
	public boolean scan(String message)
	{
		boolean ret = false;

		if(this.filtres.isEmpty())
		{
			Iterator<String> it = filtres.iterator();
			while(it.hasNext() && !ret)
			{
				String mot = it.next();
				if(message.toUpperCase().contains(mot.toUpperCase()))
				{
					ret = true;
				}
			}
		}

		return ret;
	}

	/**
	* Method to add a word in the banned words list
	* @param mot the word to add
	*/
	public void add(String mot)
	{
		if(mot != null)
		{
			this.filtres.add(mot);
		}
	}
}