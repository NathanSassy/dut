package controller;

import model.*;
import view.*;

public class Controller
{
	private Configuration conf;
	private EleveNoteGUI gui;

	public Controller(String args[])
	{
		try
		{
			this.conf = new Configuration(args);
		}
		catch(ConfigurationException ce)
		{
			System.out.println("Erreur de configuration : " + ce.getMessage());
		}

		//this.eleve = new Eleve();
		this.gui = new EleveNoteGUI(this.conf);
	}

	/*public String infoMoyGen()
	{
		return ("Moyenne Generale : " + eleve.getMoyGen());
	}*/


}