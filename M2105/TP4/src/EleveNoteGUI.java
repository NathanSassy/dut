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
	private Controller control;

	public EleveNoteGUI(Configuration c, Controller control)
	{
		super("Le titre de la fenetre");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		this.control = control;

		panelGauche = new PanelGauche(control, c.getIcons());
		panelBas = new PanelBas(control, c.getUsername());
		entreeInfoEleve = new EntreeInfoEleve(control);
		entreeNoteEleve = new EntreeNoteEleve(control);

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