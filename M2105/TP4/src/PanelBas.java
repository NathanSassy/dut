package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;

public class PanelBas extends Container
{
	private JLabel message;
	private JLabel afficheDate;
	private Controller control;

	public PanelBas(Controller control, String username)
	{
		super();
		setLayout(new BorderLayout());
		this.control = control;

		String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now());
		afficheDate = new JLabel(date);
		message = new JLabel("Bonjour " + username);

		add(message, BorderLayout.WEST);
		add(afficheDate, BorderLayout.EAST);
	}
}