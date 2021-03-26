package entity;

import core.Handler;

/**
 * MetalTile.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Contains the data for the game's metal or border tile.
 */

public class MetalTile extends Tile {

 /**
  * MetalTile
  * Constructs a new metal tile object.
  * @param handler Manages all the important objects in the game.
  * @param tileID The ID number of the tile.
  */
 public MetalTile(Handler handler, int tileID) {
  super(handler, handler.getMediaAssets().getMetalTile(), tileID);
 }

 /**
  * isSolid
  * Checks whether or not the tile is solid.
  * @return A boolean, true, since metal tiles are solid.
  */
 @Override
 public boolean isSolid() {
  return true;
 }
}
