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
		Random randomGenerator = new Random();


		ret[0] = randomGenerator.nextInt(this.width);
		ret[1] = randomGenerator.nextInt(this.height);


		return ret;
	}

	/**
	* Place ship in the grid
	*/
	public void shipPlacement()
	{
		Random randomGenerator = new Random();
		int counterTry = 0;
		final int MAX_TRY = 500;
		int counterReTry = 0;
		final int MAX_RETRY = 3;
		int i = 0;
		int x, y;
		int orientation; // 0 = hor, 1 = ver
		boolean ok;

		while(i < this.fleet.size())
		{
			ok = true;
			counterTry = 0;
			do
			{
				x = randomGenerator.nextInt(this.width-this.fleet.get(i).getSize() + 2);
				y = randomGenerator.nextInt(this.height-this.fleet.get(i).getSize() + 2);
				orientation = randomGenerator.nextInt(2);

				if(orientation == 0)
				{
					if(x >= 0 && x + this.fleet.get(i).getSize() < this.height && y >= 0 && y < this.width + y)
						ok = true;
					else
						ok = false;

					for(int k = x - 1; k < x + this.fleet.get(i).getSize() + 1 && ok; k++)
					{
						if(k >= 0 && k < this.width)
						{
							if(this.myGrid[k][y].isFree() == false)
							{
								ok = false;
							}
							else if(y - 1 >= 0 && this.myGrid[k][y-1].isFree() == false)
							{
								ok = false;
							}
							else if(y + 1 < this.width && this.myGrid[k][y+1].isFree() == false)
							{
								ok = false;
							}
						}
					}
				}
				else if(orientation == 1)
				{
					if(x >= 0 && x < this.height + x && y >= 0 && y + this.fleet.get(i).getSize() < this.width)
						ok = true;
					else
					{
						ok = false;
					}

					for(int k = y - 1; k < y + this.fleet.get(i).getSize() + 1 && ok; k++)
					{
						if(k >= 0 && k < this.height)
						{
							if(this.myGrid[x][k].isFree() == false)
							{
								ok = false;
							}
							else if(x - 1 >= 0 && this.myGrid[x-1][k].isFree() == false)
							{
								ok = false;
							}
							else if(x + 1 < this.height && this.myGrid[x+1][k].isFree() == false)
							{
								ok = false;
							}
						}
					}
				}

				counterTry++;
			} while(!ok && counterTry < MAX_TRY);

			if(counterTry >= MAX_TRY)
			{
				counterReTry++;
				i = 0;
				this.initializeMyGrid();
			}
			else
			{
				Ship s = this.fleet.get(i);
				s.setXOrigin(x);
				s.setYOrigin(y);
				if(orientation == 0)
				{
					s.setDirection(Direction.HORIZONTAL);
					for(int k = x; k < x + this.fleet.get(i).getSize(); k++)
						this.myGrid[k][y].setBusy();
				}
				else if(orientation == 1)
				{
					s.setDirection(Direction.VERTICAL);
					for(int k = y; k < y + this.fleet.get(i).getSize(); k++)
						this.myGrid[x][k].setBusy();
				}
				this.fleet.set(i, s);
			}

			if(counterReTry == MAX_RETRY)
			{
				i = this.fleet.size();
				System.out.println("impossible de placer les bateaux de l'autoplayer");
			}

			i++;
		}
	}
}