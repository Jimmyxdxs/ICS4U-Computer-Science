package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.Handler;

/**
 * Tile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for other tiles in the game.
 */

public class Tile {

	// Constants for the tile width and height.
	private final int TILE_WIDTH = 32;
        private final int TILE_HEIGHT = 32;
	protected BufferedImage tileImage; // Texture of the tile
	protected int tileID; // ID number of the tile to diffrentiate each one.
	protected Handler handler;

	/**
	 * Tile
	 * Constructs a new Tile.
	 * @param handler Manages all the objects in the game.
	 * @param tileImage The texture of the tile.
	 * @param tileID The ID number of the tile.
	 */
	public Tile(Handler handler, BufferedImage tileImage, int tileID) {
		this.handler = handler;
		this.tileImage = tileImage;
		this.tileID = tileID;
	}

	/**
	 * update
	 * Updates the tile.
	 */
	public void update() {
	}

	/**
	 * display
	 * Draws the tile to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 * @param x The x position of the tile.
	 * @param y The y position of the tile.
	 */
	public void display(Graphics g, int x, int y) {
		g.drawImage(tileImage, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	/**
	 * getTileID
	 * Gets the ID number of a tile.
	 * @return The ID number of the tile.
	 */
	public int getTileID() {
		return tileID;
	}

	/**
	 * getTileWidth
	 * Gets the tile width.
	 * @return The tile width.
	 */
	public int getTileWidth() {
		return TILE_HEIGHT;
	}

	/**
	 * getTileHeight
	 * Gets the tile height.
	 * @return The tile height.
	 */
	public int getTileHeight() {
		return TILE_WIDTH;
	}

	/**
	 * isSolid
	 * Checks whether a tile is solid or not; By default, it is not solid.
	 * @return A boolean, true, if it is solid, or false, if it is not.
	 */
	public boolean isSolid() {
		return false;
	}
}
