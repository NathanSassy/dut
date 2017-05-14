package battle;

/**
* Ship object used for the battle ship
*/
public class Ship
{
	private int size;
	private int hitNumber;
	private Direction direction;
	private int xOrigin;
	private int yOrigin;
	private String name;
	
	/**
	* Constructor of Ship
	* @param name the name of the ship
	* @param size the of the ship
	*/
	public Ship(String name, int size)
	{
		this.name = name;
		this.size = size;
		this.hitNumber = 0;
	}

	/**
	* Add a hit to the hit counter
	*/
	public void addHit()
	{
		this.hitNumber++;
	}

	/**
	* @return return string containing info about the ship
	*/
	public String toString()
	{
		String ret = "\n***** Ship *****";
		ret += "\nName : " + name;
		ret += "\nSize : " + size;
		ret += "\nDirection : " + direction;
		ret += "\nhitNumber : " + hitNumber;
		ret += "\nx = " + xOrigin + ", y = " + yOrigin;
		return ret;
	}

	/**
	* @return return the name of the ship
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Set the name of the ship
	* @param name the new name
	*/
	public void setName(String name)
	{
		if(name != null)
			this.name = name;
	}

	/**
	* @return return the size of the ship
	*/
	public int getSize()
	{
		return this.size;
	}

	/**
	* Set the size of the ship
	* @param size the new size
	*/
	public void setSize(int size)
	{
		if(size >= 0)
			this.size = size;
	}

	/**
	* @return return the xOrigin
	*/
	public int getXOrigin()
	{
		return this.xOrigin;
	}

	/**
	* Set the xOrigin of the ship
	* @param xOrigin the new xOrigin
	*/
	public void setXOrigin(int xOrigin)
	{
		this.xOrigin = xOrigin;
	}

	/**
	* @return return the yOrigin
	*/
	public int getYOrigin()
	{
		return this.yOrigin;
	}

	/**
	* Set the yOrigin of the ship
	* @param yOrigin the new yOrigin
	*/
	public void setYOrigin(int yOrigin)
	{
		this.yOrigin = yOrigin;
	}

	/**
	* @return return the hitNumber
	*/
	public int getHitNumber()
	{
		return this.hitNumber;
	}

	/**
	* Set the hitNumber of the ship
	* @param hitNumber the new hitNumber
	*/
	public void setHitNumber(int hitNumber)
	{
		this.hitNumber = hitNumber;
	}

	/**
	* @return return the direction of the ship
	*/
	public Direction getDirection()
	{
		return this.direction;
	}

	/**
	* Set the direction of the ship
	* @param d the new direction
	*/
	public void setDirection(Direction d)
	{
		this.direction = d;
	}
}
