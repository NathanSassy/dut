package controller;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class EntreeNoteEleveListener implements ActionListener
{
	private EleveNoteGUI ihm;

	public EntreeNoteEleveListener(EleveNoteGUI ihm)
	{
		this.ihm = ihm;
	}
	
	public void actionPerformed(ActionEvent e)
	{

	}

	public void updateMoyGen()
	{
		ihm.getEntreeNoteEleve().getMoyGenLabel().setText("" + ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()).getMoyGen());
	}
}