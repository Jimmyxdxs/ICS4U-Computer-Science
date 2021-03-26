package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ImageLoader.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Loads images from the textures folder.
 */

public class ImageLoader {

	/**
	 * loadImage
	 * Loads an image from the textures folder.
	 * @param path The file path of the image.
	 * @return A BufferedImage object containing the image.
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(System.getProperty("user.dir").replace("\\", "/") + path));
		} catch (IOException e) {
			System.exit(1);
		}
		return null;
	}
}