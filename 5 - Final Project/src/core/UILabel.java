package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * UILabel.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents a UI object that the user can read, with text on it.
 */

public class UILabel extends UIObject {

	private BufferedImage image; // The image of the UI label.
	private String text; // The text that goes on the UI label.

	/**
	 * UILabel
	 * Constructs a new UILabel.
	 * @param x The x position of the label.
	 * @param y The y position of the label.
	 * @param width The width of the label.
	 * @param height The height of the label.
	 * @param image The image of the UI label.
	 * @param text The text that goes on the label.
	 */
	public UILabel(double x, double y, int width, int height, BufferedImage image, String text) {
		super(x, y, width, height);
		this.image = image;
		this.text = text;
	}

	/**
	 * update
	 * Updates the label.
	 */
	@Override
	public void update() {
	}

	/**
	 * display
	 * Displays the label.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Draws the UI label image to the screen.
		g.drawImage(image, (int) x, (int) y, width, height, null);

		// Draws text with shadows to the screen.
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString(text, (int) (x + width / 2 - text.length() * 8 + 2), (int) (y + height / 2 + 10 + 2));
		g.setColor(Color.WHITE);
		g.drawString(text, (int) (x + width / 2 - text.length() * 8), (int) (y + height / 2 + 10));
	}

	/**
	 * onMouseMove
	 * Detects mouse movement actions.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void onMouseMove(MouseEvent e) {
	}

	/**
	 * onClick
	 * Decides what will happen when the label is clicked.
	 */
	@Override
	public void onClick() {
	}
}
