package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CordDialog extends JDialog implements ActionListener
{
	private JSpinner xSpinner;
	private JSpinner ySpinner;
	private JButton valideBtn;
	private int x, y;
	private boolean lock;

	public CordDialog(int minX, int maxX, int minY, int maxY)
	{
		this.setTitle("Entrez une coordonné");
		this.setSize(300, 150);
		this.getContentPane().setLayout(new BorderLayout());
		this.lock = true;

		this.xSpinner = new JSpinner(new SpinnerNumberModel(minX, minX, maxX, 1));
		this.ySpinner = new JSpinner(new SpinnerNumberModel(minY, minY, maxY, 1));
		this.valideBtn = new JButton("Valider");
		this.valideBtn.addActionListener(this);

		Container cordContainer = new Container();
		cordContainer.setLayout(new GridLayout(2, 2));
		cordContainer.add(new JLabel("X : "));
		cordContainer.add(xSpinner);
		cordContainer.add(new JLabel("Y : "));
		cordContainer.add(ySpinner);

		this.getContentPane().add(cordContainer, BorderLayout.CENTER);
		this.getContentPane().add(valideBtn, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.x = (Integer) this.xSpinner.getValue();
		this.y = (Integer) this.ySpinner.getValue();
		setVisible(false);
		this.lock = false;
		notifyAll();
	}

	public void showIt()
	{
		this.lock = true;
		setVisible(true);
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public boolean getLock()
	{
		return lock;
	}

}