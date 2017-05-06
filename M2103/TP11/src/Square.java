package battle;

public class Square
{
	private int x;
	private int y;
	private boolean free;
	private boolean hit;

	/**
	* Constructor of Square
	* @param x the absciss x
	* @param y the ordinate y
	*/
	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.free = true;
		this.hit = false;
	}

	/**
	* Set the square hit
	*/
	public void setHit()
	{
		this.hit = true;
	}

	/**
	* Set the square busy
	*/
	public void setBusy()
	{
		this.free = false;
	}

	/**
	* @return return string containing info about the square
	*/
	public String toString()
	{
		String ret = "\n***** Square *****";
		ret += "\nx: " + x;
		ret += "\ny : " + y;
		ret += "\nfree : " + free;
		ret += "\nhit : " + hit;
		return ret;
	}

	/**
	* @return the x attribute
	*/
	public int getX()
	{
		return x;
	}

	/**
	* Set the x attribute
	* @param x the new x
	*/
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	* @return the y attribute
	*/
	public int getY()
	{
		return y;
	}

	/**
	* Set the y attribute
	* @param y the new y
	*/
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	* @return return true if the square is free
	*/
	public boolean isFree()
	{
		return this.free;
	}

	/**
	* @return return true if the square is hit
	*/
	public boolean isHit()
	{
		return this.hit;
	}
}