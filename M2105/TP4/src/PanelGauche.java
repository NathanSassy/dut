import javax.swing.*;
import java.awt.*;

public class PanelGauche extends Container
{

	private JButton buttons[];

	public PanelGauche()
	{
		super();
		setLayout(new GridLayout(5, 1));

		buttons = new JButton[5];
		for(int i = 0; i < 5; i++)
		{
			buttons[i] = new JButton(i + "");
			add(buttons[i]);
		}
	}
}