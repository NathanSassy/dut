package controller;

import model.*;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;

public class SavePressed implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("oui!");
		new Eleve();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CVS", "cvs");
		chooser.setFileFilter(filter);
		//chooser.addChoosableFileFilter(filter);

		int returnVal = chooser.showOpenDialog(new JPanel());
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
       		System.out.println("You chose to open this file: " +
            chooser.getSelectedFile().getPath() + chooser.getFileFilter().getDescription());
    	}

	}
}