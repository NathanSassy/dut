package controller;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EntreeInfoEleveListener implements ActionListener, KeyListener
{
	private EleveNoteGUI ihm;

	public EntreeInfoEleveListener(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int index = ihm.getController().getIndexCurrentEleve();

		if(e.getSource() == ihm.getEntreeInfoEleve().getHommeRadioButton())
		{
			Eleve eleve  = ihm.getController().getEleves().get(index);
			eleve.setSexe("homme");
			ihm.getController().getEleves().set(index, eleve);
		}
		else if(e.getSource() == ihm.getEntreeInfoEleve().getFemmeRadioButton())
		{
			Eleve eleve  = ihm.getController().getEleves().get(index);
			eleve.setSexe("femme");
			ihm.getController().getEleves().set(index, eleve);
		}
		else if(e.getSource() == ihm.getEntreeInfoEleve().getR1aCheckBox())
		{
			Eleve eleve  = ihm.getController().getEleves().get(index);
			if(ihm.getEntreeInfoEleve().getR1aCheckBox().isSelected())
				eleve.setR1a(true);
			else
				eleve.setR1a(false);
			ihm.getController().getEleves().set(index, eleve);
		}
		else if(e.getSource() == ihm.getEntreeInfoEleve().getR2aCheckBox())
		{
			Eleve eleve  = ihm.getController().getEleves().get(index);
			if(ihm.getEntreeInfoEleve().getR2aCheckBox().isSelected())
				eleve.setR2a(true);
			else
				eleve.setR2a(false);
			ihm.getController().getEleves().set(index, eleve);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		JTextField jtf = null;
		int index = ihm.getController().getIndexCurrentEleve();

		try
		{
			if(e.getSource() == ihm.getEntreeInfoEleve().getNomTextField())
			{
				jtf = ihm.getEntreeInfoEleve().getNomTextField();
				String entree = jtf.getText();
				Eleve eleve  = ihm.getController().getEleves().get(index);
				eleve.setNom(entree);
				ihm.getController().getEleves().set(index, eleve);
			}
			else if(e.getSource() == ihm.getEntreeInfoEleve().getPrenomTextField())
			{
				jtf = ihm.getEntreeInfoEleve().getPrenomTextField();
				String entree = jtf.getText();
				Eleve eleve  = ihm.getController().getEleves().get(index);
				eleve.setPrenom(entree);
				ihm.getController().getEleves().set(index, eleve);
			}
			else if(e.getSource() == ihm.getEntreeInfoEleve().getDateNaissanceTextField())
			{
				jtf = ihm.getEntreeInfoEleve().getDateNaissanceTextField();
				String entree = jtf.getText();
				Eleve eleve  = ihm.getController().getEleves().get(index);
				eleve.setAnneeNaissance(LocalDate.parse(entree));
				ihm.getController().getEleves().set(index, eleve);
			}
			else if(e.getSource() == ihm.getEntreeInfoEleve().getCourrielTextField())
			{
				jtf = ihm.getEntreeInfoEleve().getCourrielTextField();
				String entree = jtf.getText();
				Eleve eleve  = ihm.getController().getEleves().get(index);
				eleve.setMail(entree);
				ihm.getController().getEleves().set(index, eleve);
			}
			jtf.setBackground(Color.green);
		}
		catch(Exception err)
		{
			jtf.setBackground(Color.red);
			System.out.println(err.getMessage());
		}
	}

	public void keyPressed(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}