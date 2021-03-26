package entity;

import java.awt.Graphics;

import core.Handler;

/**
 * HealthPowerUp.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents a health power up that a player can use to gain health in the game.
 */

public class HealthPowerUp extends PowerUp {

	private Animation ring; // The health power up is represented by the ring animation.

	/**
	 * HealthPowerUp
	 * Constructs a new HealthPowerUp.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the health power up.
	 * @param y The y position of the health power up.
	 * @param width The width of the health power up.
	 * @param height The height of the health power up.
	 */
	public HealthPowerUp(Handler handler, double x, double y, double width, double height) {
		super(handler, x, y, width, height);
		ring = new Animation(handler, x, y, 20, true, handler.getMediaAssets().getRing());
		this.width = width;
		this.height = height;
		active = true;
	}

	/**
	 * update
	 * Updates the health power up.
	 */
	@Override
	public void update() {
		ring.update();
	}

	/**
	 * display
	 * Displays the health power up.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		if (active) { // Checks if the power up is active before drawing it.
			ring.display(g);
		}
	}
}
