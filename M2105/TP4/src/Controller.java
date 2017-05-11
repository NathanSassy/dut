package controller;

import model.*;
import view.*;

import java.util.*;

public class Controller
{
	private ArrayList<Eleve> eleves;
	private int indexCurrentEleve;
	private EntreeInfoEleveListener eiel;
	private EleveNoteGUI ihm;
	
	public Controller(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
		this.eleves = new ArrayList<Eleve>();
		this.eleves.add(new Eleve());
		this.indexCurrentEleve = 0;

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

	public int getIndexCurrentEleve()
	{
		return this.indexCurrentEleve;
	}

	public void setIndexCurrentEleve(int i)
	{
		this.indexCurrentEleve = i;
	}

	public ArrayList<Eleve> getEleves()
	{
		return this.eleves;
	}
}