package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.Handler;

/**
 * Particle.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates particle effects for certain game objects.
 */

public class Particle implements GameWorld {

	private PVector location; // The location of the particle.
	private PVector velocity; // The velocity of the particle.
	private PVector acceleration; // The acceleration of the particle.
	private float lifespan; // The lifespan in milliseconds of the particle.
	private int radius; // The radius of the particle.
	private BufferedImage img; // The image of the particle.
	private Handler handler;

	/**
	 * Particle
	 * Constructs a new Particle object.
	 * @param handler Manages all of the important objects in the game.
	 * @param location The location of the particle.
	 * @param velocity The velocity of the particle.
	 * @param acceleration The acceleration of the particle.
	 * @param radius The radius of the particle.
	 * @param lifespan The lifespan of the particle.
	 * @param color The color of the particle.
	 * @param img The image of the particle.
	 */
	public Particle(Handler handler, PVector location, PVector velocity, PVector acceleration, int radius, float lifespan, Color color, BufferedImage img) {
		this.handler = handler;
		this.location = location;
		this.velocity = velocity;
		this.lifespan = lifespan;
		this.acceleration = acceleration;
		this.radius = radius;
		this.img = img;
	}

	/**
	 * run
	 * Runs the particle sequence.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void run(Graphics g) {
		update();
		display(g);
	}

	/**
	 * update
	 * Updates the particle.
	 */
	@Override
	public void update() {
		velocity.add(acceleration);
		location.add(velocity);
		lifespan -= 2;
	}

	/**
	 * display
	 * Displays the particle.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
                g.drawImage(img, (int) (location.getX() - handler.getMapMovement().getXOffset()), (int) (location.getY() - handler.getMapMovement().getYOffset()), radius, radius, null);
	}

	/**
	 * isDead
	 * Checks if the particle is dead or not.
	 * @return A boolean, true, if the particle is dead, or false, if it is not.
	 */
	public boolean isDead() {
		if (lifespan < 0.0) {
			return true;
		} else {
			return false;
		}
	}
}
