package battle;

import java.util.*;
import java.io.*;

/**
* Main class of the BattleShip Game
*/
public class BattleShip
{
	private int width;
	private int height;
	private final String delimiter = "\\s*:\\s*";
	private ArrayList<Ship> fleet;
	private Game gamePlay;


	/**
	* Constructor of BattleShip
	* @param fileName the path to the config file
	* @param playerName1 the player name 1
	* @param playerName2 the player name 2
	*/
	public BattleShip(String fileName, String playerName1, String playerName2)
	{
		this.fleet = new ArrayList<Ship>();
		configure(fileName);
		this.gamePlay = new Game(fleet, playerName1, playerName2, width, height);
	}
	
	/**
	* Configure attributes from config file
	* @param fileName the path to the config file
	*/
	private void configure(String fileName)
	{
		Scanner s = null;
		try
		{
			s = new Scanner(new File(fileName)).useDelimiter(delimiter);
			String name = null;
			int size = 0;

			this.width = s.nextInt();
			this.height = s.nextInt();

			while(s.hasNext())
			{
				name = s.next();
				size = Integer.parseInt(s.next());
				this.fleet.add(new Ship(name, size));
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			s.close();
		}
	}

	/**
	* @return return a string containing info of the current config
	*/
	public String printConfiguration()
	{
		String ret = "\nConfiguration du jeu : ";
		ret += "\nWidth : " + this.width;
		ret += "\nHeight : " + this.height + "\n";
		return ret;
	}

	/**
	* @return Return the gameplay attribute
	*/
	public Game getPlay()
	{
		return this.gamePlay;
	}
}
