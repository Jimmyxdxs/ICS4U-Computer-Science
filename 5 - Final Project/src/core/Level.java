package core;

import java.awt.Graphics;

import entity.GameObjectManager;
import entity.PlayerTank;

/**
 * Level.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages the frame of the game object loading in the game.
 */

public class Level {

	private int width, height;
	private Grid grid;
	private GameObjectManager gameObjectManager; // Creating a new manager to deal with GameObjects.
	private PlayerScoreManager playerScoreManager; // Creating a manager to deal with player scores.

	/**
	 * Level
	 * @param handler Manages all the important objects in the game.
	 * @param path The file path of the high score file.
	 */
	public Level(Handler handler, String path) {
		gameObjectManager = new GameObjectManager(handler, new PlayerTank(handler, 400, 400));
		playerScoreManager = new PlayerScoreManager(handler);
		grid = new Grid(handler, path);
		width = grid.getWidth();
		height = grid.getHeight();
		gameObjectManager.getPlayerTank().setX(grid.getPlayerSpawnX());
		gameObjectManager.getPlayerTank().setY(grid.getPlayerSpawnY());
	}

	/**
	 * update
	 * Updates the GameObjectManager.
	 */
	public void update() {
		gameObjectManager.update();
	}

	/**
	 * display
	 * Displays the grid and the GameObjectManager.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void display(Graphics g) {
		grid.display(g);
		gameObjectManager.display(g);
	}

	/**
	 * getGameObjectManager
	 * Gets the game's GameObjectManager.
	 * @return The game's GameObjectManager.
	 */
	public GameObjectManager getGameObjectManager() {
		return gameObjectManager;
	}

	/**
	 * getGrid
	 * Gets the game's grid.
	 * @return The game's grid.
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * getWidth
	 * Gets the width of the grid.
	 * @return The width of the grid.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * getHeight
	 * Gets the height of the grid.
	 * @return The height of the grid.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * getPlayerScoreManager
	 * Gets the game's PlayerScoreManager.
	 * @return The game's PlayerScoreManager.
	 */
	public PlayerScoreManager getPlayerScoreManager() {
		return playerScoreManager;
	}
}
