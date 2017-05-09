package battle;

import java.util.ArrayList;

/**
* Game of object of Battle Ship
*/
public class Game implements IGame
{
	private ArrayList<Ship> fleet;
	private ShotResult result;

	/**
	* Constructor of game
	* @param fleet the array list of sheeps
	* @param playerName1 the player name 1
	* @param playerName2 the player name 2
	* @param width the width
	* @param height the height
	*/
	public Game(ArrayList<Ship> fleet, String playerName1, String playerName2, int width, int height)
	{
		this.fleet = fleet;
		// cration des 2 players
	}

	/**
	* @return return info on the game
	*/
	public String description()
	{
		String ret = "";
		return ret;
	}
	
	/**
	* Start the game
	*/
	public void start(){}
	
	/**
	* End the game
	*/
	public void endOfGame(){}
}