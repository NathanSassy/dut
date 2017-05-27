package view;

import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Graphics.*;
import java.awt.*;
import java.io.*;

public class Etape1 extends JPanel 
{

	JLabel icon, icon2;
	JButton suivant;

	public Etape1 (Maquette m) 
	{

		super(new GridLayout(3,1));

		icon = new JLabel(new ImageIcon("C:/Users/maxim/OneDrive/Documents/DUT/INFO/Algo - Prog/M2104/TP_Maquette/ws/Logo2.png"));
		icon2 = new JLabel(new ImageIcon("C:/Users/maxim/OneDrive/Documents/DUT/INFO/Algo - Prog/M2104/TP_Maquette/ws/choixFilm.png"));
		suivant = new JButton("Etape suivante");

		this.setBackground(new Color(0, 255, 255));

		this.add(icon);
		this.add(icon2);
		this.add(suivant);		


	}
}