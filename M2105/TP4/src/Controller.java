package controller;

import model.*;
import view.*;

import java.util.*;

public class Controller
{
	private ArrayList<Eleve> eleves;
	private Eleve currentEleve;
	private EntreeInfoEleveListener eiel;
	private EleveNoteGUI ihm;
	
	public Controller(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
		this.eleves = new ArrayList<Eleve>();
		currentEleve = new Eleve();
		this.eleves.add(currentEleve);
		this.eiel = new EntreeInfoEleveListener(ihm);
	}

	public EleveNoteGUI getIhm()
	{
		return ihm;
	}

	public EntreeInfoEleveListener getEntreeInfoEleveListener()
	{
		return this.eiel;
	}

	public ArrayList<Eleve> getEleve()
	{
		return this.eleves;
	}


}