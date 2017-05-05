public class Ship
{
	private int size;
	private int cellDestr;
	//private char orientation;
	//private int x, y;
	private String name;
	
	public Ship(String name, int size)
	{
		this.name = name;
		this.size = size;
		this.cellDestr = 0;
	}
	
	public boolean alive()
	{
		boolean alive = true;
		if(cellDestr >= size)
			alive = false;
		return alive;
	}
}
