import javax.swing.*;
import java.awt.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PanelBas extends Container
{
	private JLabel bonjour;
	private JLabel afficheDate;

	public PanelBas(String username)
	{
		super();
		setLayout(new BorderLayout());

		SimpleDateFormat formater = new SimpleDateFormat("EEEE, d MMM yyyy");
		afficheDate = new JLabel(formater.format(new Date()));
		bonjour = new JLabel("Bonjour " + username);

		add(bonjour, BorderLayout.WEST);
		add(afficheDate, BorderLayout.EAST);
	}
}