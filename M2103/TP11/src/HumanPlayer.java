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
		System.out.println("*** Nouveau Tir ***");
		do
		{
			System.out.println("Entez son abscisse : ");
			ret[0] = in.nextInt();
			System.out.println("Entez son ordonné : ");
			ret[1] = in.nextInt();
		} while(!(ret[0] < this.width && ret[0] >= 0 && ret[1] < this.height && ret[1] >= 0));
		return ret;
	}

	/**
	* Place ship in the grid
	*/
	public void shipPlacement()
	{
		System.out.println("*** Placemen des bateaux ***");
		System.out.println("Nombre de bateau: " + this.fleet.size());
		Scanner in = new Scanner(System.in);
		int x, y;
		String orientation;
		boolean ok;

		for(int i = 0; i < this.fleet.size(); i++)
		{
			System.out.println("\nNom : " + this.fleet.get(i).getName());
			System.out.println("Taille : " + this.fleet.get(i).getSize() + "\n");
			ok = false;
			do
			{
				System.out.println("Entez son abscisse : ");
				x = in.nextInt();
				System.out.println("Entez son ordonné : ");
				y = in.nextInt();
				System.out.println("Entez son orientation (H ou V) : ");
				orientation = in.next();

				if(orientation.equals("H"))
				{
					if(x >= 0 && x + this.fleet.get(i).getSize() < this.height && y >= 0 && y < this.width)
						ok = true;
					else
						System.out.println("abscisse incorrecte");

					for(int k = x - 1; k < x + this.fleet.get(i).getSize() + 1 && ok; k++)
					{
						if(k >= 0 && k < this.width)
						{
							if(this.myGrid[k][y].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + k +", " + y + ")");
								ok = false;
							}
							else if(y - 1 >= 0 && this.myGrid[k][y-1].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + k +", " + (y-1) + ")");
								ok = false;
							}
							else if(y + 1 < this.width && this.myGrid[k][y+1].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + k +", " + (y+1) + ")");
								ok = false;
							}
						}
					}
				}
				else if(orientation.equals("V"))
				{
					if(x >= 0 && x < this.height && y >= 0 && y + this.fleet.get(i).getSize() < this.width)
						ok = true;
					else
						System.out.println("ordonné incorrecte");

					for(int k = y - 1; k < y + this.fleet.get(i).getSize() + 1 && ok; k++)
					{
						if(k >= 0 && k < this.height)
						{
							if(this.myGrid[x][k].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + x +", " + k + ")");
								ok = false;
							}
							else if(x - 1 >= 0 && this.myGrid[x-1][k].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + (x-1) +", " + k + ")");
								ok = false;
							}
							else if(x + 1 < this.height && this.myGrid[x+1][k].isFree() == false)
							{
								System.out.println("bateau à proximité en (" + (x+1) +", " + k + ")");
								ok = false;
							}
						}
					}
				}
				else
				{
					System.out.println("orientation mal renseignée");
				}

			} while(!ok);

			Ship s = this.fleet.get(i);
			s.setXOrigin(x);
			s.setYOrigin(y);
			if(orientation.equals("H"))
			{
				s.setDirection(Direction.HORIZONTAL);
				for(int k = x; k < x + this.fleet.get(i).getSize(); k++)
					this.myGrid[k][y].setBusy();
			}
			else if(orientation.equals("V"))
			{
				s.setDirection(Direction.VERTICAL);
				for(int k = y; k < y + this.fleet.get(i).getSize(); k++)
					this.myGrid[x][k].setBusy();
			}
			this.fleet.set(i, s);

			System.out.println("\nOK !");
		}
	}
}