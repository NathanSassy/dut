package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* coordinator dialog
*/
public class CordDialog extends JFrame implements ActionListener
{
	private Container cordContainer;
	private JSpinner xSpinner;
	private JSpinner ySpinner;
	private JComboBox<String> dirChooser;
	private JButton valideBtn;
	private int x, y;
	private String direction;
	private boolean lock;

	/**
	* CordDialog Constructor
	* @param minX min value for absciss
	* @param maxX max value for absciss
	* @param minY min value for ordonate
	* @param maxY max value for ordonate
	*/
	public CordDialog(int minX, int maxX, int minY, int maxY)
	{
		this.setTitle("Entrez une coordonné");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.lock = true;

		this.xSpinner = new JSpinner(new SpinnerNumberModel(minX, minX, maxX, 1));
		this.ySpinner = new JSpinner(new SpinnerNumberModel(minY, minY, maxY, 1));
		this.dirChooser = null;
		this.valideBtn = new JButton("Valider");
		this.valideBtn.addActionListener(this);

		this.cordContainer = new Container();
		this.cordContainer.setLayout(new GridLayout(3, 2));
		this.cordContainer.add(new JLabel("X : "));
		this.cordContainer.add(this.xSpinner);
		this.cordContainer.add(new JLabel("Y : "));
		this.cordContainer.add(this.ySpinner);

		this.getContentPane().add(this.cordContainer, BorderLayout.CENTER);
		this.getContentPane().add(this.valideBtn, BorderLayout.SOUTH);
	}

	public synchronized void actionPerformed(ActionEvent e)
	{
		this.x = (Integer) this.xSpinner.getValue();
		this.y = (Integer) this.ySpinner.getValue();
		if(dirChooser != null)
			this.direction = (String)dirChooser.getSelectedItem();
		setVisible(false);
		this.lock = false;
		notifyAll();
	}

	public void showIt()
	{
		this.lock = true;
		setVisible(true);
	}

	public void addDirectionChooser()
	{
		String dirs[] = {"HORIZONTAL", "VERTICAL"};
		this.dirChooser = new JComboBox<String>(dirs);
		this.dirChooser.setSelectedIndex(0);
		this.cordContainer.add(dirChooser);
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public String getDirection()
	{
		return this.direction;
	}

	public boolean getLock()
	{
		return this.lock;
	}

}