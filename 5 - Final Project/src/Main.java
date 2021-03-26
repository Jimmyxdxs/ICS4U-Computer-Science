import core.TankGame;

/**
 * Main.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Launches the game and contains the main method.
 */
public class Main {

	/**
	 * main
	 * Launches the game and contains the main method.
	 * @param args A string array.
	 */
	public static void main(String[] args) {

		// Window constants.
		final int WIDTH = 1408;
		final int HEIGTH = 896;

		TankGame tankGame = new TankGame("Tank Game", WIDTH, HEIGTH);
		tankGame.start(); // Starts the thread for the game.
	}
}
