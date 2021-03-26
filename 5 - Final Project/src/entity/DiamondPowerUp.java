package entity;

import java.awt.Graphics;

import core.Handler;

/**
 * DiamondPowerUp.java
 *
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Represents a powerup that kills all entities on the screen.
 */

public class DiamondPowerUp extends PowerUp {

	private Animation diamond; // Animation object for the powerup.

	/**
	 * DiamondPowerUp
	 * Constructs a new DiamondPowerUp object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the powerup.
	 * @param y The y position of the powerup.
	 * @param width The width of the powerup.
	 * @param height The height of the powerup.
	 */
	public DiamondPowerUp(Handler handler, double x, double y, double width, double height) {
		super(handler, x, y, width, height);
		active = true;
		diamond = new Animation(handler, x, y, 20, true, handler.getMediaAssets().getDiamond());
	}

	/**
	 * update
	 * Updates the diamond powerup.
	 */
	@Override
	public void update() {
		diamond.update();
	}

	/**
	 * display
	 * Displays the diamond powerup.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		if (active) { // Checks if the powerup is active before displaying.
			diamond.display(g);
		}
	}
}
