package controller;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class PanelGaucheListener implements ActionListener
{
	private EleveNoteGUI ihm;

	public PanelGaucheListener(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//int index = ihm.getController().getIndexCurrentEleve();

		if(e.getSource() == ihm.getPanelGauche().getNewFile())
		{
			ihm.getController().ajoutEleve(new Eleve());
			ihm.getController().getEntreeInfoEleveListener().updateIndicateurEleve();
		}
	}
}