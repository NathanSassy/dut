package view;

import javax.swing.*;
import java.awt.*;
import battle.Square;

public class guiBattleShip extends JFrame
{
	private JTable myGrid;
	private JTable opponentGrid;

	public guiBattleShip(Square myGrid[][], Square opponentGrid[][])
	{
		this.myGrid =  new JTable(new GridTableModel(myGrid));
		this.opponentGrid = new JTable(new GridTableModel(opponentGrid));

		this.myGrid.setDragEnabled(false);
		this.myGrid.setShowGrid(true);
		this.myGrid.setGridColor(Color.BLUE);
		this.myGrid.setBackground(Color.LIGHT_GRAY);
		this.myGrid.setRowHeight(50);

		this.opponentGrid.setShowGrid(true);
		this.opponentGrid.setGridColor(Color.BLUE);
		this.opponentGrid.setBackground(Color.LIGHT_GRAY);
		this.opponentGrid.setRowHeight(50);

		for(int i = 0; i < this.opponentGrid.getRowCount(); i++)
	      	for(int j = 0; j < this.opponentGrid.getColumnCount(); j++)
	        {
	          String s = this.opponentGrid.getValueAt(i, j).toString();
	          System.out.println(s);
	          this.opponentGrid.setValueAt(new JLabel("new ImageIcon(s)"), i, j);
	        }
    

		Container container = new Container();
		container.setLayout(new GridLayout(1,2));

		container.add(this.myGrid);

		//JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
		//sep.setBackground(Color.BLACK);
		//container.add(sep);

		container.add(this.opponentGrid);

		getContentPane().add(container);

		pack();
		setVisible(true);
	}
}