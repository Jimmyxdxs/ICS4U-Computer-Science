package core;

/**
 * Rect.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents the hitbox of any game object that has collision.
 */

public class Rect {

	// Coordinates of two corners of the hitbox (top left and bottom right).
	private double x1;
        private double y1;
        private double x2;
        private double y2;
	private double width;
        private double height; 

	/**
	 * Rect
	 * Constructs a new Rect object.
	 * @param x1 The x position of the hitbox.
	 * @param y1 The y position of the hitbox.
	 * @param width The width of the hitbox.
	 * @param height The height of the hitbox.
	 */
	public Rect(double x1, double y1, double width, double height) {
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;
		this.x2 = x1 + width - 1;
		this.y2 = y1 + height + 1;
	}

	/**
	 * intersects
	 * Checks to see if the current hitbox is intersecting another hitbox.
	 * @param r The Rect hitbox object
	 * @return A boolean, true, if the hitboxes are intersecting, or false, if they are not.
	 */
	public boolean intersects(Rect r) {
		return !((x1 > r.x2) || (r.x1 > x2) || (y2 < r.y1) || (r.y2 < y1));
	}

	/**
	 * setOrigin
	 * Sets the two corners of the hitbox (top left and bottom right).
	 * @param x1 The x coordinate of the hitbox.
	 * @param y1 The y coordinate of the hitbox.
	 */
	public void setOrigin(double x1, double y1) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width - 1;
		this.y2 = y1 + height - 1;
	}

	/**
	 * getX1
	 * Gets the x-coordinate of the top left corner of the hitbox.
	 * @return The x-coordinate of the top left corner of the hitbox.
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * setX1
	 * Sets the x-coordinate of the top left corner of the hitbox.
	 * @param x1 The x-coordinates of the top left corner of the hitbox.
	 */
	public void setX1(double x1) {
		this.x1 = x1;
	}

	/**
	 * getX2
	 * Gets the x-coordinate of the bottom right corner of the hitbox.
	 * @return The x-coordinate of the bottom right corner of the hitbox.
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * setX2
	 * Sets the x-coordinate of the bottom right corner of the hitbox.
	 * @param x2 The x-coordinates of the bottom right corner of the hitbox.
	 */
	public void setX2(double x2) {
		this.x2 = x2;
	}

	/**
	 * getY1
	 * Gets the y-coordinate of the top left corner of the hitbox.
	 * @return The y-coordinate of the top left corner of the hitbox.
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * setY1
	 * Sets the y-coordinate of the top left corner of the hitbox.
	 * @param y1 The y-coordinates of the top left corner of the hitbox.
	 */
	public void setY1(double y1) {
		this.y1 = y1;
	}

	/**
	 * getY2
	 * Gets the y-coordinate of the bottom right corner of the hitbox.
	 * @return The y-coordinate of the bottom right corner of the hitbox.
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * setY2
	 * Sets the y-coordinate of the bottom right corner of the hitbox.
	 * @param y2 The y-coordinates of the bottom right corner of the hitbox.
	 */
	public void setY2(double y2) {
		this.y2 = y2;
	}

	/**
	 * getWidth
	 * Gets the width of the hitbox.
	 * @return The width of the hitbox.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * setWidth
	 * Sets the width of the hitbox.
	 * @param width The width of the hitbox.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * getHeight
	 * Gets the height of the hitbox.
	 * @return The height of the hitbox.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * setHeight
	 * Sets the height of the hitbox
	 * @param height The height of the hitbox.
	 */
	public void setHeight(double height) {
		this.height = height;
	}
}
