package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import core.Handler;

/**
 * PlayerTank.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a tank that the player controls in the game.
 */

public class PlayerTank extends Tank {

	private long lastShot = 0; // Timer to delay the time between shots.
	protected Smoke smoke; // Smoke particle effects when the player drives it.
	protected Handler handler;

	/**
	 * PlayerTank
	 * Constructs a new PlayerTank.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the tank.
	 * @param y The y position of the tank.
	 */
	public PlayerTank(Handler handler, double x, double y) {
		super(handler, x, y, Tank.DEFAULT_BODY_WIDTH, Tank.DEFAULT_BODY_HEIGHT, Tank.DEFAULT_PLAYER_SPEED);
		this.x = x;
		this.y = y;
		health = DEFAULT_PLAYER_HEALTH;
		this.handler = handler;
		smoke = new Smoke(this.handler, new PVector((float) (x + width - 15), (float) (y + height - 15)));
	}

	/**
	 * update
	 * Updates the player's tank.
	 */
	@Override
	public void update() {
		getBodyInput();
		getTurretInput();
		checkCollision();
		checkStaticObjectCollision();
		smoke.update();
	}

	/**
	 * getBodyInput
	 * Get the player's input to control the tank's body.
	 */
	private void getBodyInput() {

		// x and y increments or decrements when moving.
		moveX = 0;
		moveY = 0;

		// If the player is holding the up arrow key, move the tank up.
		if ((handler.getKeyManager().getUp()) && (!handler.getKeyManager().getRight()) && (!handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getDown())) {
			moveUp();
		}

		// If the player is holding the down arrow key, move the tank down.
		if ((handler.getKeyManager().getDown()) && (!handler.getKeyManager().getRight()) && (!handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getUp())) {
			moveDown();
		}

		// If the player is holding the left arrow key, move the tank left.
		if ((handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getDown()) && (!handler.getKeyManager().getUp()) && (!handler.getKeyManager().getRight())) {
			moveLeft();
		}

		// If the player is holding the right arrow key, move the tank right.
		if ((handler.getKeyManager().getRight()) && (!handler.getKeyManager().getDown()) && (!handler.getKeyManager().getUp()) && (!handler.getKeyManager().getLeft())) {
			moveRight();
		}

		// If the player is holding the up and right arrow keys, move the tank up and right.
		if ((handler.getKeyManager().getUp() && handler.getKeyManager().getRight()) && (!handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getDown())) {
			moveUpAndRight();
		}

		// If the player is holding the up and left arrow keys, move the tank up and left.
		if ((handler.getKeyManager().getUp()) && (handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getRight()) && (!handler.getKeyManager().getDown())) {
			moveUpAndLeft();
		}

		// If the player is holding the down and right arrow keys, move the tank down and right.
		if ((handler.getKeyManager().getDown()) && (handler.getKeyManager().getRight()) && (!handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getUp())) {
			moveDownAndRight();
		}

		// If the player is holding the down and left arrow keys, move the tank down and left.
		if ((handler.getKeyManager().getDown()) && (handler.getKeyManager().getLeft()) && (!handler.getKeyManager().getRight()) && (!handler.getKeyManager().getUp())) {
			moveDownAndLeft();
		}
	}

	/**
	 * getTurretInput
	 * Get the player's input to control the tank's turret.
	 */
	private void getTurretInput() {

		// Keep the turret angle between 0 - 360 degrees for efficient math calculations.
		if (turretAngle >= 360) {
			turretAngle = turretAngle % 360;
		} else if (turretAngle < 0) {
			turretAngle = (360 + turretAngle) % 360;
		}

		// If the player is holding the 'a' key, rotate the turret counterclockwise.
		if (handler.getKeyManager().getRotateCounterClockwise()) {
			rotateTurretCounterClockwise();
		}

		// If the player is holding the 'd' key, rotate the turret clockwise.
		if (handler.getKeyManager().getRotateClockwise()) {
			rotateTurretClockwise();
		}

		// If the player is holding the 's' key, shoot from the turret.
		if (handler.getKeyManager().getShoot()) {
			if (System.currentTimeMillis() - lastShot > 500) { // Create a half-second delay between shots.
				shoot();
				lastShot = System.currentTimeMillis();
			}
		}
	}

	/**
	 * display
	 * Draws the player's tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		smoke.display(g);
		displayBody(g);
		displayTurret(g);
	}

	/**
	 * displayBody
	 * Draw the body of the player's tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	private void displayBody(Graphics g) {
		g2d = (Graphics2D) g;
		backup = g2d.getTransform();
		transform = new AffineTransform();

		// Rotate the tank's body based on the angle given.
		transform.rotate(Math.toRadians(bodyAngle), (x + (bodyWidth / 2) - handler.getMapMovement().getXOffset()),
				(y + (bodyHeight / 2) - handler.getMapMovement().getYOffset()));
		g2d.transform(transform);

		// Draw the tank body's shadow, offset by map movement.
		g2d.drawImage(handler.getMediaAssets().getPlayerShadow(), (int) (x - handler.getMapMovement().getXOffset() - 1),
				(int) (y - handler.getMapMovement().getYOffset() - 1),
				(int) bodyWidth, (int) bodyHeight, null);

		// Draw the tank body, offset by map movement.
		g2d.drawImage(handler.getMediaAssets().getPlayerTank(), (int) (x - handler.getMapMovement().getXOffset()),
				(int) (y - handler.getMapMovement().getYOffset()),
				(int) bodyWidth, (int) bodyHeight, null);
		g2d.setTransform(backup);
	}

	/**
	 * displayTurret
	 * Draw the turret of the player's tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	private void displayTurret(Graphics g) {
		g2d = (Graphics2D) g;
		backup = g2d.getTransform();
		transform = new AffineTransform();

		// Rotate the player's turret based on the angle given.
		transform.rotate(Math.toRadians(turretAngle), (x + (bodyWidth / 2) - handler.getMapMovement().getXOffset()),
				(y + (bodyHeight / 2) - handler.getMapMovement().getYOffset()));
		g2d.transform(transform);

		// Draw the player's turret, offset by map movement.
		g2d.drawImage(handler.getMediaAssets().getPlayerTurret(), (int) (x + (bodyWidth / 2) - (turretWidth / 2) - handler.getMapMovement().getXOffset()),
				(int) (y + (bodyHeight / 2) - (turretHeight / 1.5) - handler.getMapMovement().getYOffset()),
				(int) turretWidth, (int) turretHeight, null);
		g2d.setTransform(backup);
	}
}
