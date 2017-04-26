import javax.swing.*;

public class HelloWorldSwing
{
	/**
	* Create the GUI and show it.  For thread safety,
	* this method should be invoked from the
	* event- dispatching thread.
	*/
	private static void createAndShowGUI(String[] args)
	{
		//Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new java.awt.FlowLayout());	
		//Add the ubiquitous "Hello World" label.
		JLabel label[] = new JLabel[args.length];
		for(int i =0; i<args.length; i++)
		{
			label[i] = new JLabel(args[i] + " ");
			frame.getContentPane().add(label[i]);
		}
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		if(args != null)
		{
			createAndShowGUI(args);
		}
	}

}