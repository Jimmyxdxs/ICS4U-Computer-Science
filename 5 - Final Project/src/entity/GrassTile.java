package entity;

import core.Handler;

/**
 * GrassTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Contains the data for the game's grass tile.
 */

public class GrassTile extends Tile {

 /**
  * GrassTile
  * Constructs a new grass tile object.
  * @param handler Manages all the important objects in the game.
  * @param tileID The ID number of the tile.
  */
 public GrassTile(Handler handler, int tileID) {
  super(handler, handler.getMediaAssets().getGrassTile(), tileID);
 }
}
