package entity;

import core.Handler;

/**
 * WaterTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the data for the game's water tile.
 */

public class WaterTile extends Tile {

	/**
	 * WaterTile
	 * Constructs a new WaterTile.
	 * @param handler Manages all of the important objects in the game.
	 * @param tileID The ID number of the tile.
	 */
	public WaterTile(Handler handler, int tileID) {
		super(handler, handler.getMediaAssets().getWaterTile(), tileID);
	}

	/**
	 * isSolid
	 * Checks whether a tile is solid or not
	 * @return A boolean true, if the tile is solid, or false, if it is not.
	 */
	@Override
	public boolean isSolid() {
		return true;
	}
}
