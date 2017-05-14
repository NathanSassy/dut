package battle;

import java.util.ArrayList;

/**
* Game of object of Battle Ship
*/
public class Game implements IGame
{
	private ArrayList<Ship> fleet;
	private ShotResult result;
	private Player player1;
	private Player player2;

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
		player1 = new HumanPlayer(fleet, "p1", width, height);
		player2 = new AutoPlayer(fleet, "p1", width, height);
	}

	public int[] readShot(Player player)
	{
		return player.newShot();
	}

	public ShotResult analyzeShot(int shot[])
	{
		ShotResult ret = null;

		if(shot != null && shot.length == 2)
		{
			int x = shot[0];
			int y = shot[1];

			if(true)
			{
				//
			}
		}

		return ret;	
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
	public void start()
	{
		//player1.shipPlacement();
		//player1.showMyGrid();
		player2.shipPlacement();
		player2.showMyGrid();
	}
	
	/**
	* End the game
	*/
	public void endOfGame(){}
}