package battle;

import java.util.*;

public class HumanPlayer extends Player
{
	/**
	* Constructor of Player
	* @param fleet fleet the array list of sheeps
	* @param name the player name
	* @param width the width
	* @param height the height
	*/
	public HumanPlayer(ArrayList<Ship> fleet, String name, int width, int height)
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
		Scanner in = new Scanner(System.in);
		System.out.printf("*** Nouveau Tir ***");
		do
		{
			System.out.println("Entez son abscisse : ");
			ret[0] = in.nextInt();
			System.out.println("Entez son ordonné : ");
			ret[2] = in.nextInt();
		} while(ret[0] < width && ret[0] > 0 && ret[1] < height && ret[1] > 0);
		return ret;
	}

	/**
	* Place ship in the grid
	*/
	public void shipPlacement()
	{
		
	}
}