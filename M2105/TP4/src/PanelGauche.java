package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class PanelGauche extends JPanel
{
	private JButton newFile;
	private JButton deleteFile;
	private JButton loadFile;
	private JButton saveFile;
	private JButton saveAsFile;
	private EleveNoteGUI ihm;

	public PanelGauche(EleveNoteGUI ihm, ImageIcon icons[])
	{
		super();
		this.ihm = ihm;
		setLayout(new GridLayout(5, 1));

		newFile = new JButton(icons[0]);
		newFile.addActionListener(ihm.getController().getPanelGaucheListener());
		//newFile.setOpaque(false);
		//newFile.setContentAreaFilled(false);
		//newFile.setBorderPainted(false);
		loadFile = new JButton(icons[1]);
		deleteFile = new JButton(icons[2]);
		deleteFile.addActionListener(ihm.getController().getPanelGaucheListener());
		saveFile = new JButton(icons[3]);
		saveFile.addActionListener(ihm.getController().getPanelGaucheListener());
		saveAsFile = new JButton(icons[4]);
		saveAsFile.addActionListener(ihm.getController().getPanelGaucheListener());

		add(newFile);
		add(deleteFile);
		add(loadFile);
		add(saveFile);
		add(saveAsFile);
	}

	public JButton getNewFile()
	{
		return newFile;
	}

	public JButton getDeleteFile()
	{
		return deleteFile;
	}

	public JButton getLoadFile()
	{
		return loadFile;
	}

	public JButton getSaveFile()
	{
		return saveFile;
	}

	public JButton getSaveAsFile()
	{
		return saveAsFile;
	}
}