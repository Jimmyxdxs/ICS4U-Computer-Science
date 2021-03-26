package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.Handler;

/**
 * Animation.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Stores the animation mechanics of particles in the game.
 */

public class Animation implements GameWorld {

	private double x, y; // Stores the x and y positions of the animation
	private int speed; // Stores the time of each frame displayed.
	private int index; // Stores the index of the BufferedImage array for the animation.
	private long lastTime; //Stores last time of displaying.
        private long timer; // Stores the current time to cycle through the BufferedImage array.
	private BufferedImage[] frames; // Stores the images used in the animation
	private boolean active; // Stores whether the animation is active or not.
	private boolean repeat;  // Play animation in loop or once.
	private Handler handler; // Manages all the important objects in the game.

	/**
	 * Animation
	 * Constructs a new Animation object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the particle image.
	 * @param y The y position of the particle image.
	 * @param speed The speed at which the particle image appears for.
	 * @param repeat Loop animation.
	 * @param frames An array that stores the BufferedImages of
	 */
	public Animation(Handler handler, double x, double y, int speed, boolean repeat, BufferedImage[] frames) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		this.repeat = repeat;
		lastTime = System.currentTimeMillis();
		active = true;
	}

	/**
	 * update
	 * Updates the animation.
	 */
	@Override
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if ((!repeat) && (index == frames.length - 1)) {
			active = false;
		}

		if (timer > speed) {
			if ((!repeat) && (index < frames.length - 1)) {
				index++;
				timer = 0;
			}
			if (repeat) {
				index = (index + 1) % frames.length;
				timer = 0;
			}
		}
	}

	/**
	 * display
	 * Draws the animation to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		g.drawImage(getCurrentFrame(), (int) (x - handler.getMapMovement().getXOffset()), (int) (y - handler.getMapMovement().getYOffset()), handler.getMediaAssets().getTileWidth(), handler.getMediaAssets().getTileHeight(), null);
	}

	/**
	 * getCurrentFrame
	 * Gets the current image of the animation in the BufferedImage array.
	 * @return The current image at the given index in the BufferedImage array.
	 */
	private BufferedImage getCurrentFrame() {
		return frames[index];
	}

	/**
	 * getActive
	 * Returns whether the animation is active or not.
	 * @return A boolean true, if the animation is active, or false if it is not.
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * setActive
	 * Sets the active state of the animation.
	 * @param active A boolean to check whether the animation is displaying or not.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
