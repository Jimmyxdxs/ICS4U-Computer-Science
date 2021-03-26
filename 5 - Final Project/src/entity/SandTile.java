package entity;

import core.Handler;

/**
 * SandTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the data for the game's sand tile.
 */

public class SandTile extends Tile {

	/**
	 * SandTile
	 * Constructs a new SandTile.
	 * @param handler Manages all the important objects in the game.
	 * @param tileID The ID number of the tile.
	 */
	public SandTile(Handler handler, int tileID) {
		super(handler, handler.getMediaAssets().getSandTile(), tileID);
	}
}
