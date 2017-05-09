import model.*;
import view.*;
import controller.*;

public class EleveNote
{
	private Configuration conf;
	private Controller controller;
	private EleveNoteGUI gui;
	private Eleve eleve;

	public EleveNote(String args[])
	{
		try
		{
			this.conf = new Configuration(args);
		}
		catch(ConfigurationException ce)
		{
			System.out.println("Erreur de configuration : " + ce.getMessage());
		}

		this.gui = new EleveNoteGUI(this.conf);
	}

	public static void main(String args[])
	{
		new EleveNote(args);
	}
}