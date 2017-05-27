package view;

import controleur.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Graphics.*;
import java.awt.*;
import java.io.*;

public class Accueil extends JPanel {

	JButton suivant;
	JPanel panIcon;
	JLabel icon, cinema;
	Controleur controle;

	public Accueil (Maquette m) 

	{

		super(new BorderLayout());
		this.setBackground(new Color(0, 255, 255));


		icon = new JLabel(new ImageIcon("C:/Users/maxim/OneDrive/Documents/DUT/INFO/Algo - Prog/M2104/TP_Maquette/ws/Logo.png"));
		

    	panIcon = new JPanel();

    	panIcon.setLayout(new GridLayout(2,1));
    	panIcon.setBackground(new Color(0,255,255));

    	panIcon.add(icon);

    	controle = new Controleur(m);  

		suivant = new JButton("Etape suivante");
		suivant.addActionListener(controle);

		this.add(panIcon, BorderLayout.CENTER); 
		this.add(suivant, BorderLayout.SOUTH);

	}

	public JButton getSuivant() { return this.suivant; }

 }