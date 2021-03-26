package entity;

import core.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * BulletTail.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 22, 2021
 * Creates a new bullet trail when a bullet is fired.
 */

public class BulletTail implements GameWorld {
	private LinkedList<Particle> particles; // List of bullet trail particles.
	private PVector origin; // The origin point of the bullet particles.
	private Handler handler;

	/**
	 * BulletHandler
	 * Constructs a new BulletHandler.
	 * @param handler Manages all the important objects in the game.
	 * @param location The location of the bullet particles.
	 */
	BulletTail(Handler handler, PVector location) {
		origin = location.get();
		particles = new LinkedList<>();
		this.handler = handler;
	}

	/**
	 * update
	 * Updates the bullet handler.
	 */
	@Override
	public void update(){
		for (int i = 0; i < 4; i++){
			addParticle();
		}
	}

	/**
	 * addParticle
	 * Adds a smoke particle to the bullet tail list.
	 */
	private void addParticle() {
		particles.add(new Particle(handler, new PVector((origin.getX()), (origin.getY())), new PVector((float)(Math.random() * 3-1)/2,(float)(Math.random() * 3-1)/4), new PVector(0,0), 10, 20.0f, new Color(255,0,0), handler.getMediaAssets().getFire()));
	}

	/**
	 * display
	 * Draws the bullet tail to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		Iterator<Particle> it = particles.iterator();

		// Iterate through the list of particles to update and display them.
		while (it.hasNext()) {
			Particle p = it.next();
			p.update();
			p.display(g);
			if (p.isDead()) {
				it.remove();
			}
		}
	}

	/**
	 * getOrigin
	 * Gets the particle origin.
	 * @return The particle origin.
	 */
	public PVector getOrigin(){
		return origin;
	}

	/**
	 * setOrigin
	 * Sets the particle origin.
	 * @param origin The particle origin.
	 */
	public void setOrigin(PVector origin){
		this.origin = origin;
	}
}