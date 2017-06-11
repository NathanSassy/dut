package battle;

import java.util.ArrayList;

public abstract class Player
{
	protected String name;
	protected int width;
	protected int height;

	protected ArrayList<Ship> fleet;
	protected Square myGrid[][];
	protected Square opponentGrid[][];

	/**
	* Constructor of Player
	* @param fleet fleet the array list of sheeps
	* @param name the player name
	* @param width the width
	* @param height the height
	*/
	public Player(ArrayList<Ship> fleet, String name, int width, int height)
	{
		this.name = name;
		this.width = width;
		this.height = height;
		createCopy(fleet);
		initializeMyGrid();
		initializeOpponentGrid();
	}

	/**
	* Make a deep copy of the array of ships in parameter
	* @param fleet the original array of ships
	*/
	protected void createCopy(ArrayList<Ship> fleet)
	{
		this.fleet = new ArrayList<Ship>();
		for(Ship s : fleet)
		{
			this.fleet.add(new Ship(s.getName(), s.getSize()));
		}
	}

	/**
	* Initialize the human player grid
	*/
	protected void initializeMyGrid()
	{
		this.myGrid = new Square[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				this.myGrid[i][j] = new Square(i,j);
			}
		}
	}

	/**
	* Initialize the ia player grid
	*/
	protected void initializeOpponentGrid()
	{
		this.opponentGrid = new Square[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				this.opponentGrid[i][j] = new Square(i,j);
			}
		}
	}

	/**
	* Do a new shot
	* @return return pos of the new shot (x and y)
	*/
	public abstract int[] newShot();

	/**
	* Place ship in the grid
	*/
	public abstract void shipPlacement();

	public void shipPlacement(int x, int y, String orientation, int i) throws Exception
	{
		Ship s = this.fleet.get(i);

		if(orientation.equals("HORIZONTAL"))
		{
			if(!(x >= 0 && x + this.fleet.get(i).getSize() < this.height && y >= 0 && y < this.width))
				throw new Exception("abscisse incorrecte");

			for(int k = x - 1; k < x + this.fleet.get(i).getSize() + 1; k++)
			{
				if(k >= 0 && k < this.width)
				{
					if(this.myGrid[k][y].isFree() == false)
						throw new Exception("bateau à proximité en (" + k +", " + y + ")");
					else if(y - 1 >= 0 && this.myGrid[k][y-1].isFree() == false)
						throw new Exception("bateau à proximité en (" + k +", " + (y-1) + ")");
					else if(y + 1 < this.width && this.myGrid[k][y+1].isFree() == false)
						throw new Exception("bateau à proximité en (" + k +", " + (y+1) + ")");
				}
			}

			s.setDirection(Direction.HORIZONTAL);
			for(int k = x; k < x + this.fleet.get(i).getSize(); k++)
				this.myGrid[k][y].setBusy();
				
		}
		else if(orientation.equals("VERTICAL"))
		{
			if(!(x >= 0 && x < this.height && y >= 0 && y + this.fleet.get(i).getSize() < this.width))
				throw new Exception("ordonné incorrecte");

			for(int k = y - 1; k < y + this.fleet.get(i).getSize() + 1; k++)
			{
				if(k >= 0 && k < this.height)
				{
					if(this.myGrid[x][k].isFree() == false)
						throw new Exception("bateau à proximité en (" + x +", " + k + ")");
					else if(x - 1 >= 0 && this.myGrid[x-1][k].isFree() == false)
						throw new Exception("bateau à proximité en (" + (x-1) +", " + k + ")");
					else if(x + 1 < this.height && this.myGrid[x+1][k].isFree() == false)
						throw new Exception("bateau à proximité en (" + (x+1) +", " + k + ")");
				}
			}

			s.setDirection(Direction.VERTICAL);
			for(int k = y; k < y + this.fleet.get(i).getSize(); k++)
				this.myGrid[x][k].setBusy();
		}

		s.setXOrigin(x);
		s.setYOrigin(y);

		this.fleet.set(i, s);
	}

	/**
	* @return return the name of the player
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* @return return the width attribute
	*/
	public int getWidth()
	{
		return this.width;
	}

	/**
	* @return return the height attribute
	*/
	public int getHeight()
	{
		return this.height;
	}

	/**
	* Display myGird in the terminal
	*/
	public void showMyGrid()
	{
		System.out.print("\n");
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				if(myGrid[j][i].isHit() && myGrid[j][i].isFree())
					System.out.print(" X ");
				else if(myGrid[j][i].isHit() && !myGrid[j][i].isFree())
					System.out.print(" Ø ");
				else if(myGrid[j][i].isFree())
					System.out.print(" o ");
				else
					System.out.print(" S ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	/**
	* Display opponentGrid in the terminal
	*/
	public void showOpponentGrid()
	{
		System.out.print("\n");
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				if(opponentGrid[j][i].isHit() && opponentGrid[j][i].isFree())
					System.out.print(" X ");
				else if(opponentGrid[j][i].isHit() && !opponentGrid[j][i].isFree())
					System.out.print(" Ø ");
				else if(opponentGrid[j][i].isFree())
					System.out.print(" o ");
				else
					System.out.print(" S ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	/**
	* @return return the myGird attribute
	*/
	public Square[][] getMyGrid()
	{
		return this.myGrid;
	}

	/**
	* @return return the opponentGird attribute
	*/
	public Square[][] getOpponentGrid()
	{
		return this.opponentGrid;
	}

	/**
	* @return return the fleet attribute
	*/
	public ArrayList<Ship> getFleet()
	{
		return fleet;
	}
}