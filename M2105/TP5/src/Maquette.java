package view;

import controleur.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * j is the class ihmMaquetteAccueil which impliments JFrame.
 * j class Intyalize they componenents of the application.
 * @author m.hervé, a.gicquel
 * @version 1.0
 */

public class Maquette extends JFrame {

	private static JPanel partit;
	private static int numberPage;
	private static Accueil a;
	private static Etape1 e1;
	
	public Maquette() 

	{

		super("CinemAlzheimer");

		a = new Accueil(this);
		e1 = new Etape1(this);

		numberPage = 1;

    	this.getContentPane().setLayout(new BorderLayout());

    	this.setBackground(new Color(0,255,255));

    	this.setPreferredSize(new Dimension(640, 960));

    	this.setLocationRelativeTo(null);

    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

   		this.setVisible(true);

   		this.setResizable(false);
   		
   
		this.add(a, BorderLayout.CENTER);

		
		//this.add(e1, BorderLayout.CENTER);

		this.repaint();
		System.out.println("1");
		System.out.println(this.numberPage);

		this.pack();


	} 

	public static void main(String[] args) 

	{
	
		Maquette m = new Maquette();
	}

	public Accueil getAccueil() { return this.a; }
	public Etape1 getEtape1() { return this.e1; }
	public int getNumberPage() { return this.numberPage; }
	public void incNumberPage() { this.numberPage++; }

}