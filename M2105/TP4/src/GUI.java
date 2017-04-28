import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
	public GUI(String param[])
	{
		super("Le titre de la fenetre");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(new PanelGauche(), BorderLayout.WEST);
		getContentPane().add(new PanelBas("toto"), BorderLayout.SOUTH);

		// monCont.add(unComposant, BorderLayout.NORTH);
		// monCont.add(unComposant, BorderLayout.SOUTH);
		// monCont.add(unComposant, BorderLayout.EAST);
		// monCont.add(unComposant, BorderLayout.WEST);
		// monCont.add(unComposant, BorderLayout.CENTER); 

		pack();
		setVisible(true);
	}

	public static void main(String args[])
	{
		new GUI(args);
	}
}