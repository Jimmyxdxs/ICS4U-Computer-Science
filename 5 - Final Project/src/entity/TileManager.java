package entity;

import core.Handler;

/**
 * TileManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages all the tiles in the game.
 */

public class TileManager {

	private Tile[] tileArray = new Tile[6]; // Array to store the different types of tiles.
	private final int DEFAULT_TILE_ID = 1;

	/**
	 * TileManager
	 * Constructs a new TileManager.
	 * @param handler Manages all of the important objects in the game.
	 */
	public TileManager(Handler handler) {

		// Create different tiles and add them to the manager.
		Tile grassTile = new GrassTile(handler, 0);
		addTile(grassTile);
		Tile sandTile = new SandTile(handler, 1);
		addTile(sandTile);
		Tile waterTile = new WaterTile(handler, 2);
		addTile(waterTile);
		Tile metalTile = new MetalTile(handler, 3);
		addTile(metalTile);
		Tile brickTile = new BrickTile(handler, 4);
		addTile(brickTile);
		Tile holeTile = new HoleTile(handler, 5);
		addTile(holeTile);
	}

	/**
	 * addTile
	 * Adds the tile to the tile array based on its ID number.
	 * @param tile The tile to be added.
	 */
	private void addTile(Tile tile) {
		tileArray[tile.getTileID()] = tile;
	}

	/**
	 * getTileWidth
	 * Gets the tile width.
	 * @return The tile width.
	 */
	public int getTileWidth(){
		return tileArray[0].getTileWidth();
	}

	/**
	 * getTileHeight
	 * Gets the tile height.
	 * @return The tile height.
	 */
	public int getTileHeight(){
		return tileArray[0].getTileHeight();
	}
	/**
	 * getTileArray
	 * Gets the array of tiles in the game.
	 * @return The array of tiles in the game.
	 */
	public Tile[] getTileArray() {
		return tileArray;
	}

	/**
	 * getTile
	 * Gets the tile based on its ID number.
	 * @param tileID The ID number of the tile.
	 * @return The tile corresponding to the given ID number.
	 */
	public Tile getTile(int tileID) {

		// Checks if the tile does not exist, becomes the default sand tile.
		if ((tileID < 0) || (tileID > tileArray.length - 1)) {
			return tileArray[DEFAULT_TILE_ID];
		} else {
			return tileArray[tileID];
		}
	}

	/**
	 * getDefaultTileID
	 * Gets the default tile ID number.
	 * @return The default tile ID number.
	 */
	public int getDefaultTileID() {
		return DEFAULT_TILE_ID;
	}
}
