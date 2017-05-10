package battle;

import java.util.*;

public class AutoPlayer extends Player
{
	/**
	* Constructor of Player
	* @param fleet fleet the array list of sheeps
	* @param name the player name
	* @param width the width
	* @param height the height
	*/
	public AutoPlayer(ArrayList<Ship> fleet, String name, int width, int height)
	{
		super(fleet, name, width, height);
	}

	/**
	* Do a new shot
	* @return return pos of the new shot (x and y)
	*/
	public int[] newShot()
	{
		int ret[] = new int[2];
		return ret;
	}

	/**
	* Place ship in the grid
	*/
	public void shipPlacement()
	{
		
	}
}