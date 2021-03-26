package entity;

import java.awt.Graphics;

/**
 * GameWorld.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents the world of the game object when generating the map.
 */

public interface GameWorld {

	/**
	 * update
	 * Interface method update().
	 */
	public void update();

	/**
	 * display
	 * Interface method display().
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void display(Graphics g);
}
