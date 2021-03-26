package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import core.Handler;

/**
 * Smoke.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 22, 2021
 *
 */

public class Smoke implements GameWorld {

	private LinkedList<Particle> particles;
	private PVector origin;
	private Handler handler;

	/**
	 * Smoke
	 * Constructs a new Smoke object.
	 * @param handler Manages all the important objects in the game.
	 * @param location The location of the smoke particle in vector form.
	 */
	public Smoke(Handler handler, PVector location) {
		origin = location.get();
		particles = new LinkedList<>();
		this.handler = handler;
	}

	/**
	 * update
	 * Updates the smoke particles.
	 */
	@Override
	public void update() {
		origin.set(new PVector((float) (handler.getPlayerTank().getX()), (float) (handler.getPlayerTank().getY())));
		for (int i = 0; i < 15; i++) {
			addParticle();
		}
	}

	/**
	 * addParticle
	 * Add a particle to the particle system.
	 */
	private void addParticle() {
                //Create a new particle with location, acceleration,and velocity.
		particles.add(new Particle(handler, new PVector((float) (origin.getX() + Tank.DEFAULT_BODY_WIDTH / 2 - 5), (float) (origin.getY() + Tank.DEFAULT_BODY_HEIGHT / 2 - 5)), new PVector((float) (Math.random() * 3 - 1), (float) Math.random() * 3 - 1), new PVector(0, 0.05f), 6, 20.0f, new Color(80, 80, 80), handler.getMediaAssets().getSmoke()));
	}

	/**
	 * display
	 * Displays the smoke particles to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		Iterator<Particle> it = particles.iterator();

		// Iterates through the list of smoke particles to update and display.
		while (it.hasNext()) {
			Particle p = it.next();
			p.update();
			p.display(g);
			if (p.isDead()) {
				it.remove();
			}
		}
	}
}
