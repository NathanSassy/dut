package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;

public class PanelBas extends JPanel
{
	private JLabel message;
	private JLabel afficheDate;
	private EleveNoteGUI ihm;

	public PanelBas(EleveNoteGUI ihm, String username)
	{
		super();
		setLayout(new BorderLayout());
		this.ihm = ihm;

		String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now());
		afficheDate = new JLabel(date);
		message = new JLabel("Bonjour " + username);

		add(message, BorderLayout.WEST);
		add(afficheDate, BorderLayout.EAST);
	}

	public JLabel getMessage()
	{
		return message;
	}
}