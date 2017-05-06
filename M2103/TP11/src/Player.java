package battle;

import java.util.ArrayList;

public abstract class Player
{
	protected String name;
	protected int width;
	protected int height;

	/**
	* Constructor of Player
	*
	*
	*
	*/
	public Player()
	{}

	/**
	*
	* @param fleet
	*/
	protected void createCopy(ArrayList<Ship> fleet)
	{}

	
}