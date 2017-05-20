package controller;

import javax.swing.*;
import java.awt.Image;

import model.*;
import view.*;

public class Configuration
{
	private ImageIcon icons[];
	private String username;

	static final int ICON_SIZE = 32;

	public Configuration(String param[]) throws ConfigurationException
	{
		if(param == null)
		{
			throw new ConfigurationException("Parametre null");
		}
		else if(param.length != 6)
		{
			throw new ConfigurationException("Nombre de parametre incorrecte : username icon1 ... icon5");
		}
		else
		{
			this.username = param[0];
			this.icons = new ImageIcon[5];
			try
			{
				for(int i = 0; i < this.icons.length; i++)
					this.icons[i] = new ImageIcon(new ImageIcon(param[i+1]).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
			}
			catch(Exception e)
			{
				throw e;
			}
		}
	}

	public String getUsername()
	{
		return this.username;
	}

	public ImageIcon[] getIcons()
	{
		return this.icons;
	}
}