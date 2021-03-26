package core;

import entity.GameObject;

/**
 * MapMovement.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Moves the map when the player tank moves around.
 */
public class MapMovement {

	private Handler handler;

	// Gets the x and y offsets of the window depending on how much the player tank has moved.
	private double xOffset;
        private double yOffset;

	/**
	 * MapMovement
	 * @param handler Manages all the important objects in the game.
	 * @param xOffset The x amount of how much the window has moved.
	 * @param yOffset The y amount of how much the window has moved.
	 */
	public MapMovement(Handler handler, double xOffset, double yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * update
	 * Updates the map movement to move with the player.
	 */
	public void update() {
		centerOnPlayer(handler.getPlayerTank());
	}

	/**
	 * centerOnPlayer
	 * Centers the player in the middle of the screen when the player is moving.
	 * @param gameObject Gets the game object, depending on which one you want to center on (in this case, the player).
	 */
	public void centerOnPlayer(GameObject gameObject) {
		xOffset = gameObject.getX() - handler.getWidth() / 2 + gameObject.getWidth() / 2;
		yOffset = gameObject.getY() - handler.getHeight() / 2 + gameObject.getHeight() / 2;
		checkWhiteSpace();
	}

	/**
	 * checkWhiteSpace
	 * Checks if there is any blank canvas space when the map is moving with the player.
	 */
	private void checkWhiteSpace() {

		// Checks the x movement of the tank, and detects whitespace.
		if (xOffset < 0) { // Moving left.
			xOffset = 0;
		} else if (xOffset > handler.getLevel().getWidth() * handler.getTileManager().getTileWidth() - handler.getWidth()) { // Moving right.
			xOffset = handler.getLevel().getWidth() * handler.getTileManager().getTileWidth() - handler.getWidth();
		}

		// Checks the y movement of the tank, and detects whitespace.
		if (yOffset < 0) { // Moving up.
			yOffset = 0;
		} else if (yOffset > handler.getLevel().getHeight() * handler.getTileManager().getTileHeight() - handler.getHeight()) { // Moving down.
			yOffset = handler.getLevel().getHeight() * handler.getTileManager().getTileHeight() - handler.getHeight();
		}
	}

	/**
	 * move
	 * Moves the game object given an x and y shift.
	 * @param xShift The x increment or decrement of the player tank.
	 * @param yShift The y increment or decrement of the player tank.
	 */
	public void move(double xShift, double yShift) {
		xOffset += xShift;
		yOffset += yShift;
		checkWhiteSpace();
	}

	/**
	 * getXOffset
	 * Gets the x offset of the window.
	 * @return The x offset of the window.
	 */
	public double getXOffset() {
		return xOffset;
	}

	/**
	 * setXOffset
	 * Sets the x offset of the window.
	 * @param xOffset The x offset of the window.
	 */
	public void setXOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * getYOffset
	 * Gets the y offset of the window.
	 * @return The y offset of the window.
	 */
	public double getYOffset() {
		return yOffset;
	}

	/**
	 * setYOffset
	 * Sets the y offset of the window.
	 * @param yOffset The y offset of the window.
	 */
	public void setYOffset(double yOffset) {
		this.yOffset = yOffset;
	}
}
