package core;

import java.awt.Graphics;

import entity.GameWorld;
import entity.Tile;
import entity.TileManager;

/**
 * Grid.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Manages all the tiles in the game in a grid format, as well as loading the grid.
 */

public class Grid implements GameWorld {

	private Handler handler;
	private int width;
        private int height; // Contains the width and height of the map.
	private int playerSpawnX, playerSpawnY; // Contains the x and y position of the player's original spawn point.
	private int[][] grid; // 2D integer array stores the ID numbers of the tiles.
	private TileManager tileManager;

	/**
	 * Grid
	 * Constructs a new Grid object.
	 * @param handler Manages all the important objects in the game.
	 * @param path A string containing the file path of the map file.
	 */
	public Grid(Handler handler, String path) {
		this.handler = handler;
		tileManager = new TileManager(handler);
		loadGrid(path);
	}

	/**
	 * loadGrid
	 * Loads the map from a text file into a 2D integer grid array.
	 * @param path A string containing the file path of the map file.
	 */
	private void loadGrid(String path) {
		String file = Utils.loadFile(path);

		/* Gets the first line of the text file, which contains the width
		 * and height of the map, and the player spawn x and y positions.
		 */
		String[] worldInfo = file.split("\\s+");

		width = Integer.parseInt(worldInfo[0]);
		height = Integer.parseInt(worldInfo[1]);

		// Sets the width and height of the 2D grid array.
		setWidth(width);
		setHeight(height);
		grid = new int[width][height];

		// Gets the player spawn x and y positions.
		playerSpawnX = Integer.parseInt(worldInfo[2]);
		playerSpawnY = Integer.parseInt(worldInfo[3]);

		// Loads the rest of the map into the 2D integer array.
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = Integer.parseInt(worldInfo[(i + j * width) + 4]);
			}
		}
	}

	/**
	 * remove
	 * Removes a tile from the map given an x and y position and replaces it with a sand/default tile.
	 * @param x The x position of the tile.
	 * @param y The y position of the tile.
	 */
	public void remove(int x, int y) {
		int tileX = x / tileManager.getTileWidth();
		int tileY = y / tileManager.getTileHeight();
		grid[tileX][tileY] = tileManager.getDefaultTileID();
	}

	/**
	 * update
	 * Updates the grid.
	 */
	@Override
	public void update() {
	}

	/**
	 * display
	 * Draws the map to the screen given tile IDs.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Start and end variables used for map rendering efficiency and map movement.
		int xStart = (int) Math.max(0, handler.getMapMovement().getXOffset() / tileManager.getTileWidth());
		int xEnd = (int) Math.min(width, (handler.getMapMovement().getXOffset() + handler.getWidth()) / tileManager.getTileWidth() + 1);
		int yStart = (int) Math.max(0, handler.getMapMovement().getYOffset() / tileManager.getTileHeight());
		int yEnd = (int) Math.min(height, (handler.getMapMovement().getYOffset() + handler.getHeight()) / tileManager.getTileHeight() + 1);

		// Display the grid to the screen, given the indexed location fo the grid.
		for (int i = xStart; i < xEnd; i++) {
			for (int j = yStart; j < yEnd; j++) {
				getTile(i, j).display(g, (int) (i * tileManager.getTileWidth() - handler.getMapMovement().getXOffset()),
						(int) (j * tileManager.getTileHeight() - handler.getMapMovement().getYOffset()));
			}
		}
	}

	/**
	 * getTile
	 * Gets the tile given the indexed location.
	 * @param i The row index of the tile in the 2D grid array.
	 * @param j The column index of the tile in the 2D grid array.
	 * @return The tile at the given location.
	 */
	public Tile getTile(int i, int j) {

		// Return as a sand tile if the player is outside of the map.
		if ((i < 0) || (i >= width) || (j < 0) || (j >= height)) {
			return tileManager.getTile(tileManager.getDefaultTileID());
		}

		Tile t = tileManager.getTile(grid[i][j]);

		// Returns as a sand tile if the Tile object returns null.
		if (t == null) {
			return tileManager.getTile(tileManager.getDefaultTileID());
		}
		return t;
	}

	/**
	 * getGrid
	 * Gets the map grid as a 2D integer array.
	 * @return The grid, as a 2D integer array.
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * setGrid
	 * Sets a tile given the x and y position.
	 * @param x The x position of the tile to be replaced.
	 * @param y The y position of the tile to be replaced.
	 * @param tileID The new tile's ID number.
	 */
	public void setGrid(int x, int y, int tileID){
		int tileX = x / tileManager.getTileWidth();
		int tileY = y / tileManager.getTileHeight();
		grid[tileX][tileY] = tileID;
	}

	/**
	 * getWidth
	 * Gets the width of the map.
	 * @return The width of the map.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * setWidth
	 * Sets the width of the map.
	 * @param width The width of the map.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * getHeight
	 * Gets the height of the map.
	 * @return The height of the map.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * setHeight
	 * Sets the height of the map.
	 * @param height The height of the map.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * getPlayerSpawnX
	 * Gets the original x position of the player when they spawn.
	 * @return The x position of the player when they spawn.
	 */
	public int getPlayerSpawnX() {
		return playerSpawnX;
	}

	/**
	 * getPlayerSpawnY
	 * Gets the original y position of the player when they spawn.
	 * @return The y position of the player when they spawn.
	 */
	public int getPlayerSpawnY() {
		return playerSpawnY;
	}

	/**
	 * getTileManager
	 * Gets the tile manager of the game.
	 * @return The tile manager of the game.
	 */
	public TileManager getTileManager() {
		return tileManager;
	}
}