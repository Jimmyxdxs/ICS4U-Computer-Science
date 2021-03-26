package core;

import java.awt.image.BufferedImage;

/**
 * ItemSheet.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the sprite sheet for efficient image loading.
 */

public class ItemSheet {
	private BufferedImage itemSheet; // Image holds all the sprites in a single sprite sheet.

	/**
	 * ItemSheet
	 * Constructs a new ItemSheet.
	 * @param itemSheet A BufferedImage object that holds all the sprite images in one sprite sheet.
	 */
	public ItemSheet(BufferedImage itemSheet) {
		this.itemSheet = itemSheet;
	}

	/**
	 * crop
	 * Crops an image from the sprite sheet.
	 * @param x The x position of the cropped image on the sheet.
	 * @param y The y position of the cropped image on the sheet.
	 * @param width The width of the cropped image on the sheet.
	 * @param height The height of the cropped image on the sheet.
	 * @return A BufferedImage with the cropped sprite.
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		return itemSheet.getSubimage(x, y, width, height);
	}
}
