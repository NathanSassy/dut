import java.util.*;
import java.io.*;

public class BattleShip
{
	private ArrayList<Ship> ships;
	
	public BattleShip()
	{
		this.ships = new ArrayList<Ship>();
	}	
	
	public static void main(String args[])
	{
		BattleShip bs = new BattleShip();
		System.out.println("Fichier de configuration : " + args[0]);
		bs.stockShip(args[0]);
	}
	
	public void stockShip(String location)
	{
		Scanner s = null;
		try
		{
			s = new Scanner(new File(location)).useDelimiter("\\s*:\\s*");
			String name = null;
			int size = 0;
			
			while(s.hasNext())
			{
				name = s.next(new Patter();
				size = Integer.parseInt(s.next());
				System.out.println(name);
				//this.ships.add(new Ship(name, size));	
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}	
	}
}
