package mail;

/**
* This class allow you to manipulate MailItem object
* @author Antoine Gicquel
* @version 1.0
*/
public class MailItem
{
	/**
	* Attributes of the MailItem class
	*/
    private String from;
    private String to;
    private String message;

    /**
	* The constructor of the MailItem class
	* @param from source of the mail
	* @param to destination of the mail
	* @param message content of the mail
	*/
	public MailItem(String from, String to, String message)
	{
		if(from == null)
		{
			this.from = "";
		}
		else
		{
			this.from = from;
		}

		if(to == null)
		{
			this.to = "";
		}
		else
		{
			this.to = to;
		}

		if(message == null)
		{
			this.message = "";
		}
		else
		{
			this.message = message;
		}
	}

	/**
	* Method to get the source of the mail
	* @return return the from field
	*/
	public String getFrom()
	{
		return this.from;
	}

	/**
	* Method to get the destination of the mail
	* @return return the to field
	*/
	public String getTo()
	{
		return this.to;
	}

	/**
	* Method to get the content of the mail
	* @return return the message field
	*/
	public String getMessage()
	{
		return this.message;
	}

	/**
	* Method to print the informations about a mail
	*/
	public void print()
	{
		System.out.println("\n-------Message-------");
		System.out.println("From : " + this.from);
		System.out.println("to : " + this.to);
		System.out.println("Message : " + this.message + "\n");
	}

}