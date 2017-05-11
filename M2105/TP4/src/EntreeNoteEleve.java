package view;

import controller.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class EntreeNoteEleve extends JPanel
{
	private EleveNoteGUI ihm;
	private JLabel titreLabel;
	private JLabel moyGenLabel;
	private ModelTable model;
	private JTable tableau;
	private JButton nouvelleNote;
	private JButton supprNote;

	public EntreeNoteEleve(EleveNoteGUI ihm)
	{
		super();
		this.ihm = ihm;
		setLayout(new BorderLayout());

		// ligne 1
		titreLabel = new JLabel("Liste des évaluations");
		moyGenLabel = new JLabel("" + ihm.getController().getEleves().get(ihm.getController().getIndexCurrentEleve()).getMoyGen());
		Container labelEdit = new Container();
		labelEdit.setLayout(new BorderLayout());
		labelEdit.add(titreLabel, BorderLayout.WEST);
		labelEdit.add(moyGenLabel, BorderLayout.EAST);
		add(labelEdit, BorderLayout.NORTH);

		model = new ModelTable();
		model.addColumn("Note");
		model.addColumn("Matiere");
		tableau = new JTable(model);
		add(new JScrollPane(tableau), BorderLayout.CENTER);

		nouvelleNote = new JButton("Ajouter note");
		supprNote = new JButton("Supprimer note");
		Container noteEdit = new Container();
		noteEdit.setLayout(new FlowLayout());
		noteEdit.add(nouvelleNote);
		noteEdit.add(supprNote);
		add(noteEdit, BorderLayout.SOUTH);
	}

	public JLabel getMoyGenLabel()
	{
		return moyGenLabel;
	}

	public JTable getTableau()
	{
		return tableau;
	}

	public JButton getNouvelleNote()
	{
		return nouvelleNote;
	}

	public JButton getSupprNote()
	{
		return supprNote;
	}
}