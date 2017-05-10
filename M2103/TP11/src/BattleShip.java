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
		//this.gamePlay = new Game(fleet, playerName1, playerName2, width, height);
		configure(fileName);
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

			System.out.println(s.nextInt());
			System.out.println(s.nextInt());

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
		String ret = "\nWidth : " + this.width;
		ret += "\nHeight : " + this.height;
		return ret;
	}
}
