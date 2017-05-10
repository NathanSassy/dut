import battle.BattleShip;

/**
* Launcher of the battle ship game
*/
public class LaunchBattle
{
	public static void main(String args[])
	{
		if(args == null || args.length != 3)
		{
			System.out.println("Erreur de parametres : > java LaunchBattle fichier_config playername1 playername2");
		}
		else
		{
			BattleShip bs = new BattleShip(args[0], args[1], args[2]);
			System.out.println("Fichier de configuration : " + args[0]);
		}
	}
}