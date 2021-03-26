package entity;

import java.awt.Graphics;
import java.util.LinkedList;

import core.Handler;

/**
 * BulletManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Manages all of the bullets fired by AI and player tanks in the game.
 */

public class BulletManager implements GameWorld {

	private LinkedList<Bullet> bullets; // LinkedList stores all of the Bullet objects in the game.
	private Bullet tempBullet;
	private Handler handler;

	/**
	 * BulletManager
	 * Constructs a new BulletManager object.
	 * @param handler Manages all the important objects in the game.
	 */
	public BulletManager(Handler handler) {
		this.handler = handler;
		bullets = new LinkedList<>();
	}

	/**
	 * update
	 * Updates the BulletManager.
	 */
	@Override
	public void update() {
		// If the number of bullets in the game exceeds 40, remove bullets starting from the head of the LinkedList
		if (bullets.size() == 40){
			bullets.remove(0);
		}

		// Iterate through all the bullets in the list.
		for (int i = 0; i < bullets.size(); i++) {
			tempBullet = bullets.get(i);

			if (!tempBullet.getActive()) { // Checks if the current bullet is active.

				bullets.remove(i); // Remove the current bullet from the list.
			} else {
				tempBullet.update(); // Update the current bullet.
			}
		}
	}

	/**
	 * display
	 * Displays the bullets in the BulletManager to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Iterate through all the bullets in the list and displays them.
		for (int i = 0; i < bullets.size(); i++) {
			tempBullet = bullets.get(i);
			tempBullet.display(g);
		}
	}

	/**
	 * addBullet
	 * Adds a new bullet to the list.
	 * @param bullet The bullet to be added.
	 */
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
		handler.getMediaAssets().getShooting().play(); // Play the shooting sound when the bullet is added.
	}

	/**
	 * getBullets
	 * @return The LinkedList that holds all the bullets.
	 */
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * removeAll
	 * Clears the entire bullet list.
	 */
	public void removeAll() {
		bullets.clear();
	}
}