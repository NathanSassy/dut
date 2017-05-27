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
		player1 = new HumanPlayer(fleet, playerName1, width, height);
		player2 = new AutoPlayer(fleet, playerName2, width, height);

		player1.opponentGrid = player2.myGrid;
		player2.opponentGrid = player1.myGrid;
	}

	public int[] readShot(Player player)
	{
		return player.newShot();
	}

	public ShotResult analyzeShot(String playerName, int shot[])
	{
		ShotResult ret = ShotResult.MISS;

		if(shot != null && shot.length == 2)
		{
			int x = shot[0];
			int y = shot[1];
			Player p = null;

			if(playerName.equals(player1.getName()))
				p = player1;
			else if(playerName.equals(player2.getName()))
				p = player2;

			if(p != null)
			{
				if(!p.opponentGrid[x][y].isFree() && !p.opponentGrid[x][y].isHit())
				{
					p.opponentGrid[x][y].setHit();
					ret = ShotResult.HIT;
					Ship s = null;
					int i = 0;

					for(i = 0; i < p.fleet.size() && s == null; i++)
					{
						if(p.fleet.get(i).getDirection() == Direction.VERTICAL)
						{
							if(x == p.fleet.get(i).getXOrigin() && y >= p.fleet.get(i).getYOrigin() && y <= (p.fleet.get(i).getYOrigin() + p.fleet.get(i).getSize()))
								s = p.fleet.get(i);
						}
						else if(p.fleet.get(i).getDirection() == Direction.HORIZONTAL)
						{
							if(y == p.fleet.get(i).getYOrigin() && x >= p.fleet.get(i).getXOrigin() && x <= (p.fleet.get(i).getXOrigin() + p.fleet.get(i).getSize()))
								s = p.fleet.get(i);
						}
					}

					if(s != null)
					{
						i--;
						s.addHit();
						p.fleet.set(i, s);
						if(p.fleet.get(i).getHitNumber() == p.fleet.get(i).getSize())
							ret = ShotResult.SUNK;
					}
				}
				else
					p.opponentGrid[x][y].setHit();	
			}
			else
			{
				System.out.println("coordonnes invalides");
			}
		}

		return ret;	
	}

	/**
	*
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
		//SimpleFrame sf = new SimpleFrame();
		//sf.showIt();

		//player1.shipPlacement();
		//player1.showMyGrid();
		player2.shipPlacement();
		
		player1.showMyGrid();
		player1.showOpponentGrid();

		System.out.println(analyzeShot("player1", player1.newShot()));
		System.out.println(analyzeShot("player1", player1.newShot()));
		System.out.println(analyzeShot("player1", player1.newShot()));
		System.out.println(analyzeShot("player1", player1.newShot()));

		player1.displayOpponentGrid();

		//player2.showMyGrid();
		//player2.showOpponentGrid();

		//player2.displayMygrid();
		//player2.displayOpponentGrid();

		/*while(!allSunk(player2))
		{
			//player2.showMyGrid();
			//player2.displayMygrid();
			//player1.displayOpponentGrid();
			System.out.println(analyzeShot("player1", player1.newShot()));
		}*/
	}
	
	/**
	* End the game
	*/
	public void endOfGame(){}
}