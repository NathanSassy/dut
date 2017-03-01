import mail.*;

import java.util.ArrayList;

public class MailTest
{
	public static void main(String[] args)
	{
		ArrayList<String> spam = new ArrayList<String>();
		spam.add("azerty");
		spam.add("uiop");
		spam.add("qsdfg");
		spam.add("hjklm");

		MailServer s1 = new MailServer(spam);

		MailClient c1 = new MailClient(s1, "c1");
		MailClient c2 = new MailClient(s1, "c2");
		MailClient c3 = new MailClient(s1, "c3");

		System.out.println("\n*** CAS NORMAUX ***\n");

		c1.sendMailItem("c2", "m1");
		c1.sendMailItem("c2", "m2");
		c1.sendMailItem("c3", "m3");

		c1.printNextMailItem();
		c2.printNextMailItem();
		c3.printNextMailItem();

		System.out.println("\n*** CAS SPÉCIAUX ***\n");

		c1.sendMailItem("c2", "azerty m1");
		c1.sendMailItem("c1", "uiom m2");
		c1.sendMailItem("c3", "hjklmp m3");

		c1.printNextMailItem();
		c2.printNextMailItem();
		c3.printNextMailItem();

	}
}