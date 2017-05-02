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
	// Check Box
	private JCheckBox r1aCheckBox;
	private JCheckBox r2aCheckBox;

	public EntreeInfoEleve(EleveNoteGUI ihm)
	{
		super();
		this.ihm = ihm;
		setLayout(new GridLayout(7, 1));

		// ligne 1
		nomLabel = new JLabel("Nom : ");
		nomTextField = new JTextField();
		Container nomEdit = new Container();
		nomEdit.setLayout(new GridLayout(1,2));
		nomEdit.add(nomLabel);
		nomEdit.add(nomTextField);
		add(nomEdit);

		// ligne 2
		prenomLabel = new JLabel("Prenom : ");
		prenomTextField = new JTextField();
		Container prenomEdit = new Container();
		prenomEdit.setLayout(new GridLayout(1,2));
		prenomEdit.add(prenomLabel);
		prenomEdit.add(prenomTextField);
		add(prenomEdit);

		// ligne 3
		dateNaissanceLabel = new JLabel("Date de naissance : ");
		dateNaissanceTextField = new JTextField();
		Container dateNaissanceEdit = new Container();
		dateNaissanceEdit.setLayout(new GridLayout(1,2));
		dateNaissanceEdit.add(dateNaissanceLabel);
		dateNaissanceEdit.add(dateNaissanceTextField);
		add(dateNaissanceEdit);

		// ligne 4
		courrielLabel = new JLabel("Courriel : ");
		courrielTextField = new JTextField();
		Container courrielEdit = new Container();
		courrielEdit.setLayout(new GridLayout(1,2));
		courrielEdit.add(courrielLabel);
		courrielEdit.add(courrielTextField);
		add(courrielEdit);

		// ligne 5
		hommeRadioButton = new JRadioButton("HOMME");
		femmeRadioButton = new JRadioButton("FEMME");
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
		r2aCheckBox = new JCheckBox("2eme année");
		Container redoublementEdit = new Container();
		redoublementEdit.setLayout(new GridLayout(2,1));
		redoublementEdit.add(r1aCheckBox);
		redoublementEdit.add(r2aCheckBox);
		add(redoublementEdit);
	}
}