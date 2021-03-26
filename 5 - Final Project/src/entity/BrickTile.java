package entity;

import core.Handler;

/**
 * BrickTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Represents a brick tile object.
 */

public class BrickTile extends Tile {
	/**
	 * BrickTile
	 * Constructs a new BrickTile object.
	 * @param handler Manages all the important objects in the game.
	 * @param tileID The ID number of the tile.
	 */
	public BrickTile(Handler handler, int tileID) {
		super(handler, handler.getMediaAssets().getBrickTile(), tileID);
	}

	/**
	 * isSolid
	 * Returns the solid state of the brick tile, which is true
	 * @return A boolean, true, which states that the BrickTile object is solid.
	 */
	@Override
	public boolean isSolid() {
		return true;
	}
}
