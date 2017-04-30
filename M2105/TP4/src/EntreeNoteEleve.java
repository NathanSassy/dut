package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class EntreeNoteEleve extends Container
{
	private Controller control;
	private JLabel titreLabel;
	private JLabel moyGenLabel;
	private static String ENTETE[] = {"Note", "Matiere", "Date"};
	private JTable tableau;
	//private Object contenu[][];

	public EntreeNoteEleve(Controller control)
	{
		super();
		this.control = control;
		setLayout(new BorderLayout());

		// ligne 1
		titreLabel = new JLabel("Liste des évaluations");
		moyGenLabel = new JLabel(this.control.infoMoyGen());
		Container labelEdit = new Container();
		labelEdit.setLayout(new BorderLayout());
		labelEdit.add(titreLabel, BorderLayout.WEST);
		labelEdit.add(moyGenLabel, BorderLayout.EAST);
		add(labelEdit, BorderLayout.NORTH);

		// ligne 2
		Object contenu[][] = {
      {"Cysboy", "28 ans", "1.80 m"},
      {"BZHHydde", "28 ans", "1.80 m"},
      {"IamBow", "24 ans", "1.90 m"},
      {"FunMan", "32 ans", "1.85 m"}
    };
		tableau = new JTable(contenu, ENTETE);
		add(new JScrollPane(tableau), BorderLayout.CENTER);
	}
}