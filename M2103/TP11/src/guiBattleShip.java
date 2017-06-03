package view;

import javax.swing.*;
import java.awt.*;
import battle.Square;

public class guiBattleShip extends JFrame
{
	private GridTableFrame myGrid;
	private GridTableFrame opponentGrid;

	public guiBattleShip(GridTableFrame myGrid, GridTableFrame opponentGrid)
	{
		this.setSize(400,200);/*@\label{setsize:line}@*/
     	this.setLocation(200,200);/*@\label{setloc:line}@*/
     	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*@\label{closeOp:line}@*/

		this.myGrid =  myGrid;
		this.opponentGrid = opponentGrid;

		Container container = new Container();
		container.setLayout(new GridLayout(1,2));

		container.add(this.myGrid);
		container.add(this.opponentGrid);

		getContentPane().add(container);

		pack();
		setVisible(true);
	}
}