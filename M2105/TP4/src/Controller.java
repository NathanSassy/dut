package controller;

import model.*;
import view.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Controller
{
	private ArrayList<Eleve> eleves;
	private int indexCurrentEleve;
	private EntreeInfoEleveListener eiel;
	private EntreeNoteEleveListener ene;
	private PanelGaucheListener pgl;
	private EleveNoteGUI ihm;
	private File location;
	
	public Controller(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
		this.eleves = new ArrayList<Eleve>();
		this.eleves.add(new Eleve());
		this.indexCurrentEleve = 0;
		this.location = null;

		this.eiel = new EntreeInfoEleveListener(ihm);
		this.ene = new EntreeNoteEleveListener(ihm);
		this.pgl = new PanelGaucheListener(ihm);
	}

	public void updateIhmEleve(Eleve eleve)
	{
		ihm.getEntreeInfoEleve().getNomTextField().setText(eleve.getNom());
		ihm.getEntreeInfoEleve().getNomTextField().setBackground(Color.white);

		ihm.getEntreeInfoEleve().getPrenomTextField().setText(eleve.getPrenom());
		ihm.getEntreeInfoEleve().getPrenomTextField().setBackground(Color.white);

		if(eleve.getAnneNaissance() == null)
			ihm.getEntreeInfoEleve().getDateNaissanceTextField().setText("");
		else
			ihm.getEntreeInfoEleve().getDateNaissanceTextField().setText(eleve.getAnneNaissance().toString());
		ihm.getEntreeInfoEleve().getDateNaissanceTextField().setBackground(Color.white);

		ihm.getEntreeInfoEleve().getCourrielTextField().setText(eleve.getMail());
		ihm.getEntreeInfoEleve().getCourrielTextField().setBackground(Color.white);

		ihm.getEntreeInfoEleve().getBg().clearSelection();
		if(eleve.getSexe() == "homme")
		{
			ihm.getEntreeInfoEleve().getHommeRadioButton().setSelected(true);
		}
		else if(eleve.getSexe() == "femme")
		{
			ihm.getEntreeInfoEleve().getFemmeRadioButton().setSelected(true);
		}

		ihm.getEntreeInfoEleve().getR1aCheckBox().setSelected(false);
		ihm.getEntreeInfoEleve().getR2aCheckBox().setSelected(false);
		if(eleve.getR1a())
			ihm.getEntreeInfoEleve().getR1aCheckBox().setSelected(true);
		if(eleve.getR2a())
			ihm.getEntreeInfoEleve().getR2aCheckBox().setSelected(true);

		// on vide le tableau
		while (ihm.getEntreeNoteEleve().getModel().getRowCount() > 0)
		{
			ihm.getEntreeNoteEleve().getModel().removeRow(0);
		}

		String note, matiere;
		for(int i = 0; i < eleve.getEvaluations().size(); i++)
		{
			note = "" + eleve.getEvaluations().get(i).getNote();
			matiere = "" + eleve.getEvaluations().get(i).getMatiere();
			ihm.getEntreeNoteEleve().getModel().addRow(new Object[]{note, matiere});
		}

		ene.updateMoyGen();
	}

	public EleveNoteGUI getIhm()
	{
		return ihm;
	}

	public EntreeInfoEleveListener getEntreeInfoEleveListener()
	{
		return this.eiel;
	}

	public EntreeNoteEleveListener getEntreeNoteEleveListener()
	{
		return this.ene;
	}

	public PanelGaucheListener getPanelGaucheListener()
	{
		return this.pgl;
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

	public void resetEleves()
	{
		this.eleves = new ArrayList<Eleve>();
		this.eleves.add(new Eleve());
		this.indexCurrentEleve = 0;
	}

	public void ajoutEleve(Eleve e)
	{
		this.eleves.add(e);
	}

	public File getLocation()
	{
		return this.location;
	}

	public void setLocation(File newLoc)
	{
		this.location = newLoc;
	}
}