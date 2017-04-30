package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class PanelGauche extends Container
{
	private JButton newFile;
	private JButton deleteFile;
	private JButton loadFile;
	private JButton saveFile;
	private JButton saveAsFile;
	private Controller control;

	public PanelGauche(Controller control, ImageIcon icons[])
	{
		super();
		this.control = control;
		setLayout(new GridLayout(5, 1));

		newFile = new JButton(icons[0]);
		deleteFile = new JButton(icons[1]);
		loadFile = new JButton(icons[2]);
		saveFile = new JButton(icons[3]);
		saveAsFile = new JButton(icons[4]);

		add(newFile);
		add(deleteFile);
		add(loadFile);
		add(saveFile);
		add(saveAsFile);
	}
}