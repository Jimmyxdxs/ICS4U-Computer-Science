package ai;

import java.awt.Graphics;
import java.util.LinkedList;

import core.Handler;
import core.Rect;

/**
 * AITankManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Manages all the AITank objects in the game.
 */

public class AITankManager {

	private Handler handler;
	private LinkedList<AITank> aiTanks;
	private AITank tempAITank;
	private long lastTankSpawned;
	private int difficulty;

	/**
	 * AITankManager
	 * Constructs a new AITankManager object.
	 * @param handler Manages all the important objects in the game.
	 */
	public AITankManager(Handler handler) {
		this.handler = handler;
		aiTanks = new LinkedList<>();
		lastTankSpawned = 0;

		// Check the difficulty of the game, and set the delay for tanks spawning.
		if (handler.getTankGame().getDifficulty() == 1) {
			difficulty = 3500;
		} else {
			difficulty = 1500;
		}
	}

	/**
	 * update
	 * Updates the AI tank manager.
	 */
	public void update() {
		addAITank();

		// Iterate through the list of AITank objects.
		for (int i = 0; i < aiTanks.size(); i++) {
			tempAITank = aiTanks.get(i);

			// Checks if the AI tank is still active.
			if (!tempAITank.getActive()) {

				// Remove the current AI tank from the list and record the score.
				aiTanks.remove(i);
				handler.getGameObjectManager().setPlayerScore(handler.getGameObjectManager().getPlayerScore() + 100);
				handler.getGameObjectManager().createPowerUp();
			} else {

				// Update the current tank.
				tempAITank.update();
			}
		}
	}

	/**
	 * display
	 * Draws the AI tanks in the AITankManager to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void display(Graphics g) {

		// Iterate through the AI tank list and display the AI tanks.
		for (int i = 0; i < aiTanks.size(); i++) {
			tempAITank = aiTanks.get(i);
			tempAITank.display(g);
		}
	}

	/**
	 * addAITank
	 * Adds a new AITank object to the AITankManager.
	 */
	private void addAITank() {
		int tankX = 80;
		int j;
		Rect tempRect;
		boolean needNew = true;

		// Checks if there is more than 20 AI tanks currently in the game.
		if (aiTanks.size() > 20){
			return;
		}

		// Checks if it is possible to spawn a new tank based on the time delay and difficulty.
		if (System.currentTimeMillis() - lastTankSpawned > difficulty) {
			do {
				j = (int) (Math.random() * 700) + 40;
				needNew = false;

				// Avoid overlapping existing objects
				if ((!(handler.getLevel().getGrid().getTile(tankX, j).isSolid())
						|| (handler.getLevel().getGrid().getTile((int) (tankX + handler.getPlayerTank().getWidth()), (int) (j + handler.getPlayerTank().getHeight())).isSolid())
						|| (handler.getLevel().getGrid().getTile(tankX, (int) (j + handler.getPlayerTank().getHeight())).isSolid())
						|| (handler.getLevel().getGrid().getTile((int) (tankX + handler.getPlayerTank().getWidth()), j).isSolid()))) {

					tempRect = new Rect(tankX - 20, j - 20, handler.getPlayerTank().getWidth() + 40, handler.getPlayerTank().getHeight() + 40);
					if (tempRect.intersects(handler.getPlayerTank().getRect())) {
						needNew = true;
					} else {
						for (AITank ai : aiTanks) {
							if (tempRect.intersects(ai.getRect())) {
								needNew = true;
								break;
							}
						}
					}
				}
			} while (needNew);
			tempAITank = new AITank(handler, tankX, j);
			aiTanks.add(tempAITank);
			lastTankSpawned = System.currentTimeMillis();
		}
	}

	/**
	 * removeAITank
	 * Removes an AITank object from the AITankManager.
	 */
	public void removeAITank() {
		aiTanks.remove();
	}

	/**
	 * removeAll
	 * Removes all the AITank objects from the AITankManager.
	 */
	public void removeAll() {
		aiTanks.clear();
	}

	/**
	 * getAITanks
	 * Gets the LinkedList of AITanks in the AITankManager.
	 * @return The LinkedList of AITank objects in the AITankManager.
	 */
	public LinkedList<AITank> getAITanks() {
		return aiTanks;
	}

	/**
	 * setAITanks
	 * Sets a new AITankManager of AITank objects.
	 * @param aiTanks The LinkedList that stores all of the AITank objects.
	 */
	public void setAITanks(LinkedList<AITank> aiTanks) {
		this.aiTanks = aiTanks;
	}
}