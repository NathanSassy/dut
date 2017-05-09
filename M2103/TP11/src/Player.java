package battle;

import java.util.ArrayList;

public abstract class Player
{
	protected String name;
	protected int width;
	protected int height;

	protected ArrayList<Ship> fleet;
	protected Square myGrid[][];
	protected Square oponentGrid[][];

	/**
	* Constructor of Player
	* @param fleet
	* @param name
	* @param width
	* @param height
	*/
	public Player(ArrayList<Ship> fleet, String name, int width, int height)
	{
		this.name = name;
		this.width = width;
		this.height = height;
		this.myGrid = new Square[width][height];
		this.oponentGrid = new Square[width][height];
	}

	/**
	*
	* @param fleet www
	*/
	protected void createCopy(ArrayList<Ship> fleet)
	{}

	/**
	* Initialize the human player grid
	*/
	public abstract  void initializeMyGrid();

	/**
	* Initialize the ia player grid
	*/
	public abstract  void initializeOponentGrid();

	/**
	* @return return the name of the player
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* @return return the width attribute
	*/
	public int getWidth()
	{
		return this.width;
	}

	/**
	* @return return the height attribute
	*/
	public int getHeight()
	{
		return this.height;
	}
}