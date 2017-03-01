package mail;

/**
* This class allow you to manipulate MailClient object
* @author Antoine Gicquel
* @version 1.0
*/
public class MailClient
{
	/**
	* Attribute of the MailServer class
	*/
	private String user;
	private MailServer server;

	/**
	* The constructor of the MailClient class
	* @param server the server connected to the client
	* @param user the user connected to the client
	*/
	public MailClient(MailServer server, String user)
	{
		if(server != null && user != null)
		{
			this.user = user;
			this.server = server;
		}
		else
		{
			System.out.println("Constructeur mal renseigné");
		}
	}

	/**
	* Method to get the last mail of the user on the server
	* @return the next mail item 
	*/
	public MailItem getNextMailItem()
	{
		return this.server.getNextMailItem(this.user);
	}

	/**
	* Method to print the last mail of the user on the server
	*/
	public void printNextMailItem()
	{
		MailItem item = this.server.getNextMailItem(this.user);

		if(item != null)
		{
			item.print();
		}
		else
		{
			System.out.println("Pas de mail");
		}
	}

	/**
	* Method to send a mail on the server
	* @param to destination of the mail
	* @param message content of the mail
	*/
	public void sendMailItem(String to, String message)
	{
		if(to != null && message != null)
		{
			MailItem item = new MailItem(this.user, to, message);
			this.server.post(item);
		}
	}
}