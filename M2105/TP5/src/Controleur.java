package controleur;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Controleur implements ActionListener
{
	private int numberPage;
	private Maquette m;

	public Controleur(Maquette m) 
	{
		this.m = m;
		this.numberPage = this.m.getNumberPage();
	}

	public void actionPerformed(ActionEvent e) 
	{

		Object source = e.getSource();

		if( source == m.getAccueil().getSuivant())
		{
			
			System.out.println("succed");
			m.add(m.getEtape1(), BorderLayout.CENTER);
		}

	}

}



