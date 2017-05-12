package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class EntreeInfoEleve extends JPanel
{
	private EleveNoteGUI ihm;
	// Label
	private JLabel nomLabel;
	private JLabel prenomLabel;
	private JLabel dateNaissanceLabel;
	private JLabel courrielLabel;
	private JLabel redoublantLabel;
	// Text Field
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField dateNaissanceTextField;
	private JTextField courrielTextField;
	// Radio Button
	private JRadioButton hommeRadioButton;
	private JRadioButton femmeRadioButton;
	private ButtonGroup bg;
	// Check Box
	private JCheckBox r1aCheckBox;
	private JCheckBox r2aCheckBox;
	// Bouton de navigation
	private JButton prevEleve;
	private JButton nextEleve;
	private JLabel indicateurEleve;

	public EntreeInfoEleve(EleveNoteGUI ihm)
	{
		super();
		this.ihm = ihm;
		setLayout(new GridLayout(8, 1));

		// ligne 1
		nomLabel = new JLabel("Nom : ");
		nomTextField = new JTextField();
		nomTextField.addKeyListener(ihm.getController().getEntreeInfoEleveListener());
		Container nomEdit = new Container();
		nomEdit.setLayout(new GridLayout(1,2));
		nomEdit.add(nomLabel);
		nomEdit.add(nomTextField);
		add(nomEdit);

		// ligne 2
		prenomLabel = new JLabel("Prenom : ");
		prenomTextField = new JTextField();
		prenomTextField.addKeyListener(ihm.getController().getEntreeInfoEleveListener());
		Container prenomEdit = new Container();
		prenomEdit.setLayout(new GridLayout(1,2));
		prenomEdit.add(prenomLabel);
		prenomEdit.add(prenomTextField);
		add(prenomEdit);

		// ligne 3
		dateNaissanceLabel = new JLabel("Date de naissance : ");
		dateNaissanceTextField = new JTextField();
		dateNaissanceTextField.addKeyListener(ihm.getController().getEntreeInfoEleveListener());
		dateNaissanceTextField.setToolTipText("yyyy-mm-dd");
		Container dateNaissanceEdit = new Container();
		dateNaissanceEdit.setLayout(new GridLayout(1,2));
		dateNaissanceEdit.add(dateNaissanceLabel);
		dateNaissanceEdit.add(dateNaissanceTextField);
		add(dateNaissanceEdit);

		// ligne 4
		courrielLabel = new JLabel("Courriel : ");
		courrielTextField = new JTextField();
		courrielTextField.addKeyListener(ihm.getController().getEntreeInfoEleveListener());
		Container courrielEdit = new Container();
		courrielEdit.setLayout(new GridLayout(1,2));
		courrielEdit.add(courrielLabel);
		courrielEdit.add(courrielTextField);
		add(courrielEdit);

		// ligne 5
		hommeRadioButton = new JRadioButton("HOMME");
		hommeRadioButton.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		femmeRadioButton = new JRadioButton("FEMME");
		femmeRadioButton.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		bg = new ButtonGroup();
		bg.add(hommeRadioButton);
		bg.add(femmeRadioButton);
		Container sexeEdit = new Container();
		sexeEdit.setLayout(new FlowLayout(FlowLayout.LEFT));
		sexeEdit.add(hommeRadioButton);
		sexeEdit.add(femmeRadioButton);
		add(sexeEdit);

		// ligne 6
		redoublantLabel = new JLabel("Redoublant : ");
		add(redoublantLabel);

		// ligne 7
		r1aCheckBox = new JCheckBox("1ere année");
		r1aCheckBox.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		r2aCheckBox = new JCheckBox("2eme année");
		r2aCheckBox.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		Container redoublementEdit = new Container();
		redoublementEdit.setLayout(new GridLayout(2,1));
		redoublementEdit.add(r1aCheckBox);
		redoublementEdit.add(r2aCheckBox);
		add(redoublementEdit);

		// ligne 8
		prevEleve = new JButton("Precedent");
		prevEleve.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		nextEleve = new JButton("Suivant");
		nextEleve.addActionListener(ihm.getController().getEntreeInfoEleveListener());
		indicateurEleve = new JLabel("1/" + ihm.getController().getEleves().size());
		Container eleveNav = new Container();
		eleveNav.setLayout(new FlowLayout());
		eleveNav.add(prevEleve);
		eleveNav.add(indicateurEleve);
		eleveNav.add(nextEleve);
		add(eleveNav);
	}

	public JTextField getNomTextField()
	{
		return nomTextField;
	}

	public JTextField getPrenomTextField()
	{
		return prenomTextField;
	}
	
	public JTextField getDateNaissanceTextField()
	{
		return dateNaissanceTextField;
	}

	public JTextField getCourrielTextField()
	{
		return courrielTextField;
	}

	public JRadioButton getHommeRadioButton()
	{
		return hommeRadioButton;
	}

	public JRadioButton getFemmeRadioButton()
	{
		return femmeRadioButton;
	}

	public ButtonGroup getBg()
	{
		return bg;
	}

	public JCheckBox getR1aCheckBox()
	{
		return r1aCheckBox;
	}

	public JCheckBox getR2aCheckBox()
	{
		return r2aCheckBox;
	}

	public JButton getPrevEleve()
	{
		return prevEleve;
	}

	public JButton getNextEleve()
	{
		return nextEleve;
	}

	public JLabel getIndicateurEleve()
	{
		return indicateurEleve;
	}
}