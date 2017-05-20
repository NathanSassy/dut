package controller;

import view.*;
import model.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;


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
		else if(e.getSource() == ihm.getPanelGauche().getDeleteFile())
		{
			if(ihm.getController().getEleves().size() <= 1)
			{
				ihm.getController().resetEleves();
				ihm.getController().updateIhmEleve(ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()));
			}
			else
			{
				int index = ihm.getController().getIndexCurrentEleve();
				ihm.getController().supprEleve(index);

				if(index != 0)
					ihm.getController().setIndexCurrentEleve(index - 1);

				ihm.getController().updateIhmEleve(ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()));
				ihm.getController().getEntreeInfoEleveListener().updateIndicateurEleve();
			}
		}
		else if((e.getSource() == ihm.getPanelGauche().getSaveFile() && ihm.getController().getLocation() == null) || e.getSource() == ihm.getPanelGauche().getSaveAsFile())
		{
			String location = null;

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filterCVS = new FileNameExtensionFilter("CVS", "cvs");
			chooser.addChoosableFileFilter(filterCVS);

			int returnVal = chooser.showOpenDialog(new JPanel());
	    	if(returnVal == JFileChooser.APPROVE_OPTION)
	    	{
	            location = chooser.getSelectedFile().getPath();
	            if(chooser.getFileFilter() == filterCVS)
	            	location += ".cvs";
	    	}

	    	ihm.getController().setLocation(location);
	    	ihm.getController().saveIntoFile();
		}
	}
}