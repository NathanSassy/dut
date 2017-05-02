package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class EleveNoteGUI extends JFrame
{
	private PanelGauche panelGauche;
	private PanelBas panelBas;
	private EntreeInfoEleve entreeInfoEleve;
	private EntreeNoteEleve entreeNoteEleve;

	public EleveNoteGUI(Configuration c)
	{
		super("Le titre de la fenetre");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		panelGauche = new PanelGauche(this, c.getIcons());
		panelBas = new PanelBas(this, c.getUsername());
		entreeInfoEleve = new EntreeInfoEleve(this);
		entreeNoteEleve = new EntreeNoteEleve(this);

		Container centre = new Container();
		centre.setLayout(new GridLayout(1,2));
		centre.add(entreeInfoEleve);
		centre.add(entreeNoteEleve);

		getContentPane().add(panelGauche, BorderLayout.WEST);
		getContentPane().add(panelBas, BorderLayout.SOUTH);
		getContentPane().add(centre, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
}