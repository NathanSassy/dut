package mail;

import java.util.ArrayList;
import java.util.Iterator;

/**
* This class allow you to manipulate MailServer object
* @author Antoine Gicquel
* @version 1.0
*/
public class MailServer
{

	/**
	* Attribute of the MailServer class
	*/
	private ArrayList<MailItem> items;
	private AntiSpam antiSpam;

	/**
	* The constructor of the MailServer class
	* @param filtres Array list of words for the antispam
	*/
	public MailServer(ArrayList<String> filtres)
	{
		items = new ArrayList<MailItem>();
		antiSpam = new AntiSpam(filtres);
	}

	/**
	* Method to add a mail on the server
	* @param item the mail to add
	*/
	public void post(MailItem item)
	{
		if(item != null && !antiSpam.scan(item.getMessage()))
		{
			this.items.add(item);
		}
	}

	/**
	* Method to count mail of a user on the server
	* @param who the recipient
	* @return the nomber of items
	*/
	public int howManyItems(String who)
	{
		int nb = 0;
		
		if(who != null)
		{
			for(MailItem item : items)
			{
				if(item.getTo().equals(who))
				{
					nb++;
				}
			}
		}

		return nb;
	}

	/**
	* Method to get the last mail of a user on the server
	* @param who the recipient
	* @return the next mail item 
	*/
	public MailItem getNextMailItem(String who)
	{
		MailItem item = null;
		
		if(who != null)
		{
			boolean found = false;
			Iterator<MailItem> it = items.iterator();

			while(!found && (it.hasNext()))
			{
				MailItem unItem = it.next();
				if(unItem.getTo().equals(who))
				{
					it.remove();
					item = unItem;
					found = true;
				}
			}
		}

		return item;
	}
}