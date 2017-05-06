import battle.BattleShip;

public class LaunchBattle
{
	public static void main(String args[])
	{
		BattleShip bs = new BattleShip(args[0], args[1], args[2]);
		System.out.println("Fichier de configuration : " + args[0]);
	}
}