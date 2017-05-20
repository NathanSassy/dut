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
	private String location;
	
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

	public void saveIntoFile()
	{
		if(location != null)
		{
			PrintWriter fichier = null;
			try
			{
				fichier = new PrintWriter(new FileWriter(location));
				for(Eleve eleve : this.eleves)
				{
					fichier.print(eleve.getNom() + ":");
					fichier.print(eleve.getPrenom() + ":");
					fichier.print(eleve.getAnneNaissance() + ":");
					fichier.print(eleve.getMail() + ":");
					fichier.print(eleve.getSexe() + ":");
					fichier.print(eleve.getR1a() + ":");
					fichier.print(eleve.getR2a() + ":");
					
					for(Evaluation eva : eleve.getEvaluations())
					{
						fichier.print(eva.getNote() + ":");
						fichier.print(eva.getMatiere() + ":");
					}

					fichier.print("\n");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			finally
			{
				fichier.close();
			}
		}
		// sinon lancer saveAsIntoFile()
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

	public void supprEleve(int index)
	{
		this.eleves.remove(index);
	}

	public String getLocation()
	{
		return this.location;
	}

	public void setLocation(String newLoc)
	{
		this.location = newLoc;
	}
}