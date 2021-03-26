package core;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import entity.GameWorld;

/**
 * UIObject.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for the other UI objects in the game.
 */

public abstract class UIObject implements GameWorld {

	protected double x, y; // Contains the x and y coordinates of the object.
	protected int width;
        protected int height; // Contains the width and height of the object.
	protected boolean hovering; // Boolean contains whether the mouse is hovering over the object or not.
	protected Rectangle bounds; // Rectangle object checks the bounding box of the object.

	/**
	 * UIObject
	 * Constructs a new UIObject.
	 * @param x The x position of the object.
	 * @param y The y position of the object.
	 * @param width The width of the object.
	 * @param height The height of the object.
	 */
	public UIObject(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hovering = false;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}

	/**
	 * onClick
	 * Decides what will happen when the UI object is clicked.
	 */
	public abstract void onClick();

	/**
	 * onMouseMove
	 * Checks if the mouse cursor is in the bounding box of a UI object.
	 * @param e The mouse event that detects mouse actions.
	 */
	public void onMouseMove(MouseEvent e) {
		hovering = bounds.contains(e.getX(), e.getY()); // True = hovering, false = not hovering
	}

	/**
	 * onMouseRelease
	 * Checks if the UI object has been clicked.
	 * @param e The mouse event that detects mouse actions.
	 */
	public void onMouseRelease(MouseEvent e) {
		if (hovering) { // If the cursor is hovering over the UI object, perform the onClick() method.
			onClick();
		}
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
	public int getWidth() {
		return width;
	}

	/**
	 * setWidth
	 * Sets the width of the object.
	 * @param width The width of the object.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * getHeight
	 * Gets the height of the object.
	 * @return The height of the object.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * setHeight
	 * Sets the height of the object.
	 * @param height The height of the object.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}