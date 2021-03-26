package entity;

import core.Handler;

/**
 * StaticGameObject
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for non-moveable objects in the game.
 */

public abstract class StaticGameObject extends GameObject {

	/**
	 * StaticGameObject
	 * Constructs a new StaticGameObject.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the object.
	 * @param y The y position of the object.
	 * @param width The width of the object.
	 * @param height The height of the object.
	 */
	public StaticGameObject(Handler handler, double x, double y, double width, double height) {
		super(handler, x, y, width, height);
	}
}
