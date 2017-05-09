package controller;

import view.*;

import java.awt.event.*;

public class EntreeInfoEleveListener implements ActionListener, KeyListener
{
	private EleveNoteGUI ihm;

	public EntreeInfoEleveListener(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ihm.getEntreeInfoEleve().getHommeRadioButton())
		{
			ihm.getEntreeInfoEleve().getFemmeRadioButton().setEnabled(false);
		}
		else if(e.getSource() == ihm.getEntreeInfoEleve().getFemmeRadioButton())
		{
			ihm.getEntreeInfoEleve().getHommeRadioButton().setEnabled(false);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getSource() == ihm.getEntreeInfoEleve().getNomTextField())
		{
		}
	}

	public void keyPressed(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}