package entity;


import core.Handler;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * ExplosionManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Manages all the explosion animations in the game.
 */

public class ExplosionManager implements GameWorld {

	private LinkedList<Animation> explosionList; // Stores all the explosion animations.
	private Animation tempAnimation;

	/**
	 * ExplosionManager
	 * Constructs a new ExplosionManager object.
	 * @param handler Manages all the important objects in the game.
	 */
	public ExplosionManager(Handler handler) {
		explosionList = new LinkedList<>();
	}

	/**
	 * update
	 * Updates the ExplosionManager
	 */
	@Override
	public void update() {

		// Iterates through the explosion list.
		for (int i = 0; i < explosionList.size(); i++) {
			tempAnimation = explosionList.get(i);
			if (!tempAnimation.getActive()) { // Checks if the explosion animation is active.
				explosionList.remove(i); // Remove the animation from the list.
			} else {
				tempAnimation.update(); // Update the animation.
			}
		}
	}

	/**
	 * display
	 * Draws the explosion animation to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Iterates through the explosion list and draws them to the screen.
		for (int i = 0; i < explosionList.size(); i++) {
			tempAnimation = explosionList.get(i);
			tempAnimation.display(g);
		}
	}

	/**
	 * addAnimation
	 * Adds an explosion animation to the list.
	 * @param explosion The explosion object to be added.
	 */
	public void addAnimation(Animation explosion) {
		explosionList.add(explosion);
	}

	/**
	 * removeAnimation
	 * Removes an explosion animation from the list.
	 * @param explosion The explosion object to be removed.
	 */
	public void removeAnimation(Animation explosion) {
		explosionList.remove(explosion);
	}

	/**
	 * getExplosionList
	 * Gets the list of explosions.
	 * @return The LinkedList containing the explosion animations.
	 */
	public LinkedList<Animation> getExplosionList() {
		return explosionList;
	}
}