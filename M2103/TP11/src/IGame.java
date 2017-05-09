package battle;

public interface IGame
{
	/**
	* @return return info on the game
	*/
	public String description();

	/**
	* Start the game
	*/
	public void start();

	/**
	* End the game
	*/
	public void endOfGame();
}