package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * UIImageButton.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents a UI object that can be clicked on, and a state switch is performed.
 */

public class UIImageButton extends UIObject {

	private BufferedImage[] images; // Array of images for the button hover animation.
	private ClickListener clickListener; // Click listener to decide the action of each button press.

	/**
	 * UIImageButton
	 * Constructs a new UIImageButton.
	 * @param x The x position of the button.
	 * @param y The y position of the button.
	 * @param width The width of the button.
	 * @param height The height of the button.
	 * @param images A BufferedImage array that holds the animation of hovering buttons.
	 * @param clickListener Decides the action of each button press.
	 */
	public UIImageButton(double x, double y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
		super(x, y, width, height);
		this.images = images;
		this.clickListener = clickListener;
	}

	/**
	 * update
	 * Updates the button.
	 */
	@Override
	public void update() {
	}

	/**
	 * display
	 * Displays the button to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		if (hovering) { // Checks if the cursor is hovering over a button.
			g.drawImage(images[1], (int) x, (int) y, width, height, null); // Display the second image in the array, which is darker.
		} else {
			g.drawImage(images[0], (int) x, (int) y, width, height, null); // Display the first image in the array, which is lighter.
		}
	}

	/**
	 * onClick
	 * When this method is called, a state switch will be performed, depending on the button being clicked and the current state.
	 */
	@Override
	public void onClick() {
		clickListener.onClick();
	}
}
