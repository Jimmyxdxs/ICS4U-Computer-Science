package entity;

import core.Handler;
import core.Rect;

/**
 * GameObject.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Contains all the attributes and methods of the elements in the game.
 */

public abstract class GameObject implements GameWorld {

	protected Handler handler;
	protected double x, y; // Holds the x and y positions of the object.
	protected double width;
        protected double height; // Holds the width and height of the object.
	protected Rect rect; // Contains the hitbox of the object.
	protected boolean active; // Holds whether or not the object is active.

	/**
	 * GameObject
	 * Constructs a new GameObject object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the object.
	 * @param y The y position of the object.
	 * @param width The width of the object.
	 * @param height The height of the object.
	 */
	public GameObject(Handler handler, double x, double y, double width, double height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rect(x, y, width, height);
	}

	/**
	 * collisionWithTile
	 * Checks the collision with a tile.
	 * @param x The x position of the collision.
	 * @param y The y position of the collision.
	 * @return A boolean true, if there is collision, or false, if there is not.
	 */
	protected boolean collisionWithTile(int x, int y) {
		return handler.getLevel().getGrid().getTile(x, y).isSolid();
	}

	/**
	 * getRect
	 * Gets the hitbox of the object.
	 * @return The hitbox of the object.
	 */
	public Rect getRect() {
		return rect;
	}

	/**
	 * getX
	 * Gets the x position of the object.
	 * @return The x position of the object.
	 */
	public double getX() {
		return x;
	}

	/**
	 * setX
	 * Sets the x position of the object.
	 * @param x The x position of the object.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * getY
	 * Gets the y position of the object.
	 * @return The y position of the object.
	 */
	public double getY() {
		return y;
	}

	/**
	 * setY
	 * Sets the y position of the object.
	 * @param y The y position of the object.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * getWidth
	 * Gets the width of the object.
	 * @return The width of the object.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * setWidth
	 * Sets the width of the object.
	 * @param width The width of the object.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * getHeight
	 * Gets the height of the object.
	 * @return The height of the object.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * setHeight
	 * Sets the height of the object.
	 * @param height The height of the object.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * getActive
	 * Gets whether or not the object is active.
	 * @return A boolean true, if the object is active, or false, if it is not.
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * setActive
	 * Sets whether or not the object is active.
	 * @param active If the object is active, true, or not, false.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
