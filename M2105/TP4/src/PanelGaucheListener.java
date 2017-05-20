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
			int dialogResult = JOptionPane.showConfirmDialog (new JPanel(), "Attention, toutes données non enrengistrées seront perdus. Continuez ?","Warning", JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				ihm.getController().resetEleves();
				ihm.getController().getEntreeInfoEleveListener().updateIndicateurEleve();
				ihm.getController().updateIhmEleve(ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()));
				ihm.getController().setLocation(null);
			}
		}
	}
}