package core;

import ai.AITankManager;
import entity.ExplosionManager;
import entity.GameObjectManager;
import entity.PlayerTank;
import entity.TileManager;

/**
 * Handler.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Manages all of the important objects in the game.
 */

public class Handler {

	private TankGame tankGame;
	private Level level;

	/**
	 * Handler
	 * Constructs a new Handler object.
	 * @param tankGame Stores the game mechanics and controls the game.
	 */
	public Handler(TankGame tankGame) {
		this.tankGame = tankGame;
	}

	/**
	 * getWidth
	 * Gets the width of the game window.
	 * @return The game window width.
	 */
	public int getWidth() {
		return tankGame.getWidth();
	}

	/**
	 * getHeight
	 * Gets the height of the game window.
	 * @return The game window height.
	 */
	public int getHeight() {
		return tankGame.getHeight();
	}

	/**
	 * getKeyManager
	 * Gets the key manager, which deals with key controls in the game.
	 * @return The key manager, which deals with key controls.
	 */
	public KeyManager getKeyManager() {
		return tankGame.getKeyManager();
	}

	/**
	 * getMouseManager
	 * Gets the mouse manager, which deals with mouse controls in the game.
	 * @return The mouse manager, which deals with mosue controls.
	 */
	public MouseManager getMouseManager() {
		return tankGame.getMouseManager();
	}

	/**
	 * getMapMovement
	 * Gets the map movement object, which deals with player map movement in the game.
	 * @return The map movement object, which deals with player map movement.
	 */
	public MapMovement getMapMovement() {
		return tankGame.getMapMovement();
	}

	/**
	 * getTankGame Gets the object which stores the game mechanics and controls the game.
	 * @return The object that stores game mechanics and controls the game.
	 */
	public TankGame getTankGame() {
		return tankGame;
	}

	/**
	 * setTankGame
	 * Sets the object which stores the game mechanics and controls the game.
	 * @param tankGame The object that stores the game mechanics and controls the game.
	 */
	public void setTankGame(TankGame tankGame) {
		this.tankGame = tankGame;
	}

	/**
	 * getLevel
	 * Gets the object pertaining to the current level in the game.
	 * @return The level of the game.
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * setLevel
	 * Sets the object pertaining to the current level in the game.
	 * @param level The level of the game.
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * getGameObjectManager
	 * Gets the manager that controls the GameObjects.
	 * @return The manager that controls the GameObjects.
	 */
	public GameObjectManager getGameObjectManager() {
		return level.getGameObjectManager();
	}

	/**
	 * getPlayerTank
	 * Gets the player's tank data.
	 * @return The player's tank data.
	 */
	public PlayerTank getPlayerTank() {
		return level.getGameObjectManager().getPlayerTank();
	}

	/**
	 * getAITankManager
	 * Gets the manager that controls the AI tanks.
	 * @return The manager that controls the AI tanks.
	 */
	public AITankManager getAITankManager() {
		return level.getGameObjectManager().getAITankManager();
	}

	/**
	 * getExplosionManager
	 * Gets the manager that controls the explosion animations.
	 * @return The manager that controls the explosions.
	 */
	public ExplosionManager getExplosionManager() {
		return level.getGameObjectManager().getExplosionManager();
	}

	/**
	 * getStateManager
	 * Gets the manager that controls the game states.
	 * @return The manager that controls the game states.
	 */
	public StateManager getStateManager() {
		return tankGame.getStateManager();
	}

	/**
	 * getState
	 * Gets the current game state.
	 * @return The current game state.
	 */
	public State getState() {
		return tankGame.getStateManager().getCurrentState();
	}

	/**
	 * getMediaAssets
	 * Gets the assets of the game.
	 * @return The assets of the game.
	 */
	public MediaAssets getMediaAssets() {
		return tankGame.getMediaAssets();
	}

	/**
	 * getTileManager
	 * Gets the tile manager of the game.
	 * @return The tile manager of the game.
	 */
	public TileManager getTileManager() {
		return level.getGrid().getTileManager();
	}
}
