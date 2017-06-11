package battle;

import java.util.ArrayList;
import view.*;

/**
* Game of object of Battle Ship
*/
public class Game implements IGame
{
	private ArrayList<Ship> fleet;
	private ShotResult result;
	private Player player1;
	private Player player2;
	private String mode;

	/**
	* Constructor of game
	* @param fleet the array list of sheeps
	* @param playerName1 the player name 1
	* @param playerName2 the player name 2
	* @param width the width
	* @param height the height
	*/
	public Game(ArrayList<Ship> fleet, String playerName1, String playerName2, int width, int height, String mode)
	{
		this.fleet = fleet;
		this.mode = mode;
		// cration des 2 players
		player1 = new HumanPlayer(fleet, playerName1, width, height);
		player2 = new AutoPlayer(fleet, playerName2, width, height);

		player1.opponentGrid = player2.myGrid;
		player2.opponentGrid = player1.myGrid;
	}

	/**
	* read a new shot from a player
	* @param player the player who make the new shot
	* @return the shot values
	*/
	public int[] readShot(Player player)
	{
		return player.newShot();
	}

	/**
	* Give the result of a shot
	* @param p the player who made the shot
	* @param shot the shot values
	* @return the result of the shot
	*/
	public ShotResult analyzeShot(Player p, int shot[])
	{
		ShotResult ret = ShotResult.MISS;

		if(shot != null && shot.length == 2)
		{
			int x = shot[0];
			int y = shot[1];

			if(p != null &&!p.opponentGrid[x][y].isFree() && !p.opponentGrid[x][y].isHit())
			{
				p.opponentGrid[x][y].setHit();
				p.opponentGrid[x][y].setBusy();
				ret = ShotResult.HIT;
				Ship s = null;
				int i = 0;
				Player enn = null;
				if(p == player1)
					enn = this.player2;
				else if(p == player2)
					enn = this.player1;

				for(i = 0; i < enn.fleet.size() && s == null; i++)
				{
					if(enn.fleet.get(i).getDirection() == Direction.VERTICAL)
					{
						if(x == enn.fleet.get(i).getXOrigin() && y >= enn.fleet.get(i).getYOrigin() && y <= (enn.fleet.get(i).getYOrigin() + enn.fleet.get(i).getSize()))
							s = enn.fleet.get(i);
					}
					else if(enn.fleet.get(i).getDirection() == Direction.HORIZONTAL)
					{
						if(y == enn.fleet.get(i).getYOrigin() && x >= enn.fleet.get(i).getXOrigin() && x <= (enn.fleet.get(i).getXOrigin() + enn.fleet.get(i).getSize()))
							s = enn.fleet.get(i);
					}
				}

				if(s != null)
				{
					i--;
					s.addHit();
					enn.fleet.set(i, s);
					if(enn.fleet.get(i).getHitNumber() == enn.fleet.get(i).getSize())
						ret = ShotResult.SUNK;
				}
			}
			else
				p.opponentGrid[x][y].setHit();	
		}

		return ret;	
	}

	/**
	* @return return true if all the fleet is sunk
	*/
	public boolean allSunk(Player player)
	{
		boolean ret = true;

		for(int i = 0; i < player.fleet.size() && ret; i++)
		{
			if(player.fleet.get(i).getHitNumber() < player.fleet.get(i).getSize())
				ret = false;
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
		if(mode.equals("curses"))
		{
			player1.shipPlacement();
			player2.shipPlacement();
			while(!allSunk(player1) && !allSunk(player2))
			{
				System.out.println("Ma grille : ");
				player1.showMyGrid();
				System.out.println("Grille Adverse : ");
				player1.showOpponentGrid();
				System.out.println(analyzeShot(player1, player1.newShot()));
				System.out.println(analyzeShot(player2, player2.newShot()));
			}
		}
		else if(mode.equals("gui"))
		{
			new GraphicalGame(this);
		}
	}
	
	/**
	* End the game
	*/
	public void endOfGame(){}

	/**
	* @return get the player1
	*/
	public Player getPlayer1()
	{
		return player1;
	}

	/**
	* @return get the player2
	*/
	public Player getPlayer2()
	{
		return player2;
	}
}