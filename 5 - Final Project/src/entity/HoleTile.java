package entity;

import core.Handler;

/**
 * HoleTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the data for the game's hole tile.
 */

public class HoleTile extends Tile {

	/**
	 * HoleTile
	 * Constructs a new HoleTile.
	 * @param handler Manages all the important objects in the game.
	 * @param tileID The ID number of the tile.
	 */
	public HoleTile(Handler handler, int tileID) {
		super(handler, handler.getMediaAssets().getHoleTile(), tileID);
	}
}
