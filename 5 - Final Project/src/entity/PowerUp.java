package entity;

import core.Handler;

/**
 * PowerUp.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for other powerups in the game.
 */

public abstract class PowerUp extends StaticGameObject {

	/**
	 * PowerUp
	 * Constructs a new PowerUp object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the powerup.
	 * @param y The y position of the powerup.
	 * @param width The width of the powerup.
	 * @param height The height of the powerup.
	 */
	public PowerUp(Handler handler, double x, double y, double width, double height) {
		super(handler, x, y, width, height);
	}
}
