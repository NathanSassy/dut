package controller;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
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
		int index = ihm.getController().getIndexCurrentEleve();

		if(e.getSource() == ihm.getEntreeNoteEleve().getNouvelleNote())
		{
			String note = (String)JOptionPane.showInputDialog(
   				new JFrame(),
   				"Note : ",
   				"Ajouter une note",
   				JOptionPane.QUESTION_MESSAGE,
   				null,
   				null,
   				null);

			String matiere = (String)JOptionPane.showInputDialog(
   				new JFrame(),
   				"Matiere : ",
   				"Ajouter une note",
   				JOptionPane.QUESTION_MESSAGE,
   				null,
   				null,
   				null);

			try
			{
				Eleve eleve  = ihm.getController().getEleves().get(index);
				eleve.ajouteEvaluation(new Evaluation(Double.parseDouble(note), matiere));
				ihm.getController().getEleves().set(index, eleve);
				updateMoyGen();
				ihm.getEntreeNoteEleve().getModel().addRow(new Object[]{note, matiere});
				ihm.getPanelBas().getMessage().setText("Note ajoutée");
			}
			catch(Exception err)
			{
				JOptionPane d = new JOptionPane();
				d.showMessageDialog(new JFrame(), err.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == ihm.getEntreeNoteEleve().getSupprNote())
		{
			int ligne = ihm.getEntreeNoteEleve().getTableau().getSelectedRow();
			ihm.getEntreeNoteEleve().getModel().removeRow(ligne);
			Eleve eleve  = ihm.getController().getEleves().get(index);
			eleve.getEvaluations().remove(ligne);
			updateMoyGen();
			ihm.getPanelBas().getMessage().setText("Note supprimée");
		}
	}

	public void updateMoyGen()
	{
		ihm.getEntreeNoteEleve().getMoyGenLabel().setText("Moyenne : " + ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()).getMoyGen());
	}
}