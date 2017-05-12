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
		deleteFile = new JButton(icons[1]);
		loadFile = new JButton(icons[2]);
		saveFile = new JButton(icons[3]);
		saveAsFile = new JButton(icons[4]);

		saveFile.addActionListener(new SavePressed());

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