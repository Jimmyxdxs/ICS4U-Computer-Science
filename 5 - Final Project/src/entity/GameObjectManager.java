package entity;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import ai.AITank;
import ai.AITankManager;
import core.Handler;

/**
 * GameObjectManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Manages all the GameObject objects in the game.
 */

public class GameObjectManager implements GameWorld {

	private Handler handler;
	private int playerScore; // Records the player's score.
	private PlayerTank playerTank; // Represents a PlayerTank object.
	private LinkedList<GameObject> gameObjects; // ArrayList holds all the GameObjects in the game.
	private BulletManager bulletManager; // BulletManager manages all the bullets in the game.
	private AITankManager aiTankManager; // AITankManager manages all the AI Tanks in the game.
	private HealthBar healthBar; // Represents the health bar in the game.
	private ExplosionManager explosionManager; // ExplosionManager manages the explosion animations in the game.
	private HealthPowerUp healthPowerUp;
	private DiamondPowerUp diamondPowerUp;

	/**
	 * GameObjectManager
	 * Constructs a new GameObjectManager object.
	 * @param handler Manages all the important objects in the game.
	 * @param playerTank Represents a player's tank.
	 */
	public GameObjectManager(Handler handler, PlayerTank playerTank) {
		this.handler = handler;
		this.playerTank = playerTank;
		aiTankManager = new AITankManager(handler);
		gameObjects = new LinkedList<>();
		bulletManager = new BulletManager(handler);
		explosionManager = new ExplosionManager(handler);
		healthBar = new HealthBar(handler, 50, 45, 200, 8);
		playerScore = 0;
	}

	/**
	 * update
	 * Updates the GameObjectManager
	 */
	@Override
	public void update() {
		Iterator<GameObject> it = gameObjects.iterator();
		while (it.hasNext()) { // Iterates through the list of GameObjects.
			GameObject go = it.next();
			if (!go.getActive()) { // Checks if the current GameObject is active.
				it.remove(); // Remove the current GameObject.
			} else {
				go.update(); // Updates the current GameObject.
			}
		}

		// Updates the other managers, player's tank, healthbar, and map.
		bulletManager.update();
		aiTankManager.update();
		playerTank.update();
		explosionManager.update();
		healthBar.update();
		handler.getMapMovement().update();

	}

	/**
	 * display
	 * Draws the GameObjectManager elements to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		Iterator<GameObject> it = gameObjects.iterator();
		while (it.hasNext()) { // Iterates through the list of GameObjects and displays the items.
			GameObject go = it.next();
			go.display(g);
		}

		// Displays the other managers, player's tank, and healthbar.
		bulletManager.display(g);
		aiTankManager.display(g);
		playerTank.display(g);
		explosionManager.display(g);
		healthBar.display(g);
	}

	/**
	 * addGameObject
	 * Adds a GameObject to the list.
	 * @param gameObject The GameObject to be added.
	 */
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(0, gameObject);
	}

	/**
	 * getHandler
	 * Gets the handler of the game.
	 * @return The handler of the game.
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * setHandler
	 * Sets the handler of the game.
	 * @param handler The handler of the game.
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * getAITankManager
	 * Gets the AITankManager.
	 * @return The AITankManager.
	 */
	public AITankManager getAITankManager() {
		return aiTankManager;
	}

	/**
	 * setAITankManager
	 * Sets the AITankManager
	 * @param aiTankManager The AITankManager.
	 */
	public void setAITankManager(AITankManager aiTankManager) {
		this.aiTankManager = aiTankManager;
	}

	/**
	 * getPlayerTank
	 * Gets the player tank.
	 * @return The player tank.
	 */
	public PlayerTank getPlayerTank() {
		return playerTank;
	}

	/**
	 * setPlayerTank
	 * Sets the player tank.
	 * @param playerTank The player tank.
	 */
	public void setPlayerTank(PlayerTank playerTank) {
		this.playerTank = playerTank;
	}

	/**
	 * getGameObjects
	 * Gets the list of GameObjects.
	 * @return The list of GameObjects.
	 */
	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}

	/**
	 * setGameObjects
	 * Sets the list of GameObjects.
	 * @param gameObjects The list of GameObjects.
	 */
	public void setGameObjects(LinkedList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	/**
	 * getBulletManager
	 * Gets the list of bullets.
	 * @return The list of bullets.
	 */
	public BulletManager getBulletManager() {
		return bulletManager;
	}

	/**
	 * setBulletManager
	 * Sets the bullet list.
	 * @param bulletManager The list of bullets.
	 */
	public void setBulletManager(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	/**
	 * getExplosionManager
	 * Gets the list of explosion animations.
	 * @return The list of explosion animations.
	 */
	public ExplosionManager getExplosionManager() {
		return explosionManager;
	}

	/**
	 * setExplosionManager
	 * Sets the list of explosion animations.
	 * @param explosionManager The list of explosion animations.
	 */
	public void setExplosionManager(ExplosionManager explosionManager) {
		this.explosionManager = explosionManager;
	}

	/**
	 * destroyAll
	 * Remove all bullets and AI tanks from their respective lists.
	 */
	public void destroyAll() {

		// Iterate through the AI tank list and display an explosion animation at the x and y positions.
		for (AITank aiTank : aiTankManager.getAITanks()) {
			explosionManager.addAnimation(new Animation(handler, aiTank.getX(), aiTank.getY(), 10, false, handler.getMediaAssets().getExplosions()));
		}

		// Iterate through the bullet list and display an explosion animation at the x and y positions.
		for (Bullet bullet : bulletManager.getBullets()) {
			explosionManager.addAnimation(new Animation(handler, bullet.getX(), bullet.getY(), 10, false, handler.getMediaAssets().getExplosions()));
		}
		playerScore += aiTankManager.getAITanks().size() * 100;

		// Remove all AI tanks and bullets from their respective lists.
		aiTankManager.removeAll();
		bulletManager.removeAll();

		// Play the explosion sound effect.
		for (int i = 0; i < 2; i++) {
			handler.getMediaAssets().getExplosion().play();
		}
	}

	/**
	 * getPlayerScore
	 * Gets the player's current score.
	 * @return The player's current score.
	 */
	public int getPlayerScore(){
		return playerScore;
	}

	/**
	 * setPlayerScore
	 * Sets the player's currentScore.
	 * @param playerScore The player's current score.
	 */
	public void setPlayerScore(int playerScore){
		this.playerScore = playerScore;
	}

	/**
	 * createPowerUp
	 * Creates a new powerup at a random location based on the current scores.
	 */
	public void createPowerUp() {
		if ((playerScore % 2000 == 0) && (playerScore != 0)) {
			diamondPowerUp = new DiamondPowerUp(handler, 200 * ((playerScore / 2000) % 4 + 1), 300, 32, 32);
			addGameObject(diamondPowerUp);
		} else if ((playerScore % 1000 == 0) && (playerScore != 0)) {
			healthPowerUp = new HealthPowerUp(handler, 1270, 60 * ((playerScore / 1000) % 13 + 1), 32, 32);
			addGameObject(healthPowerUp);
		}
	}
}
