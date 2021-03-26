package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import core.Handler;

/**
 * Tank.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for other tank objects in the game.
 */

public abstract class Tank extends MovingGameObject {

	// Tank body, turret, health, and speed constants.
	protected static final int DEFAULT_PLAYER_HEALTH = 5;
	protected static final double DEFAULT_BODY_WIDTH = 56;
        protected static final double DEFAULT_BODY_HEIGHT = 56;
	protected static final double DEFAULT_TURRET_WIDTH = DEFAULT_BODY_WIDTH / 2.7234;
	protected static final double DEFAULT_TURRET_HEIGHT = DEFAULT_BODY_HEIGHT / 1.20754;
	protected static final double DEFAULT_PLAYER_SPEED = 5;
	protected static final double DEFAULT_AI_SPEED = 2;

	protected int health;
	protected double bodyWidth = DEFAULT_BODY_WIDTH;
        protected double bodyHeight = DEFAULT_BODY_HEIGHT;
	protected double bodyAngle;
	protected double turretWidth = DEFAULT_TURRET_WIDTH;
        protected double turretHeight = DEFAULT_TURRET_HEIGHT;
	protected double turretAngle = 0;
	protected double turretMove = 5;
	protected long reloadTime;

	protected Graphics2D g2d;
	protected AffineTransform transform, backup;

	/**
	 * Tank
	 * Constructs a new Tank.
	 * @param handler Manages all of the important objects in the game.
	 * @param x The x position of the tank.
	 * @param y The y position of the tank.
	 * @param width The width of the tank.
	 * @param height The height of the tank.
	 * @param speed The speed of the tank.
	 */
	public Tank(Handler handler, double x, double y, double width, double height, double speed) {
		super(handler, x, y, width, height, speed, 0);
		bodyAngle = angle;
		angle = bodyAngle - 90;
		active = true;
		reloadTime = System.currentTimeMillis();
	}

	/**
	 * moveUp
	 * Moves the body of the tank up.
	 */
	public void moveUp() {
		moveY = -speed;
		bodyAngle = 0;
		angle = bodyAngle - 90;
	}

	/**
	 * moveDown
	 * Moves the body of the tank down.
	 */
	public void moveDown() {
		moveY = speed;
		bodyAngle = 180;
		angle = bodyAngle - 90;
	}

	/**
	 * moveLeft
	 * Moves the body of the tank left.
	 */
	public void moveLeft() {
		moveX = -speed;
		bodyAngle = 270;
		angle = bodyAngle - 90;
	}

	/**
	 * moveRight
	 * Moves the body of the tank right.
	 */
	public void moveRight() {
		moveX = speed;
		bodyAngle = 90;
		angle = bodyAngle - 90;
	}

	/**
	 * moveUpAndLeft
	 * Moves the body of the tank up and left.
	 */
	public void moveUpAndLeft() {
		moveX = -speed * 0.75;
		moveY = -speed * 0.75;
		bodyAngle = 315;
		angle = bodyAngle - 90;
	}

	/**
	 * moveUpAndRight
	 * Moves the body of the tank up and right.
	 */
	public void moveUpAndRight() {
		moveX = speed * 0.75;
		moveY = -speed * 0.75;
		bodyAngle = 45;
		angle = bodyAngle - 90;
	}

	/**
	 * moveDownAndLeft
	 * Moves the body of the tank down and left.
	 */
	public void moveDownAndLeft() {
		moveX = -speed * 0.75;
		moveY = speed * 0.75;
		bodyAngle = 225;
		angle = bodyAngle - 90;
	}

	/**
	 * moveDownAndRight
	 * Moves the body of the tank down and right.
	 */
	public void moveDownAndRight() {
		moveX = speed * 0.75;
		moveY = speed * 0.75;
		bodyAngle = 135;
		angle = bodyAngle - 90;
	}

	/**
	 * rotateTurretCounterClockwise
	 * Rotates the turret counterclockwise.
	 */
	public void rotateTurretCounterClockwise() {
		turretAngle -= turretMove;
	}

	/**
	 * rotateTurretClockwise
	 * Rotates the turret clockwise.
	 */
	public void rotateTurretClockwise() {
		turretAngle += turretMove;
	}

	/**
	 * shoot
	 * Shoots a bullet.
	 */
	public void shoot() {

		// x and y positions of the original bullet.
		int bulletX = (int) (x + width / 2 + 55 * Math.cos(Math.toRadians((270 + turretAngle) % 360)));
		int bulletY = (int) (y + height / 2 + 55 * Math.sin(Math.toRadians((270 + turretAngle) % 360)));

		// Checks if the player is up against a solid tile. If they are, they cannot fire.
		if (!((Math.ceil((bulletY - 5) / handler.getTileManager().getTileHeight()) < 1) || (((((bulletY + 40) / handler.getTileManager().getTileHeight())) > handler.getLevel().getHeight() - 1))
				|| (Math.ceil((bulletX - 5) / handler.getTileManager().getTileWidth()) < 1) || ((bulletX + 40) / handler.getTileManager().getTileWidth() > handler.getLevel().getWidth() - 1))) {
			if ((System.currentTimeMillis() - reloadTime > 1000)) {
				reloadTime = System.currentTimeMillis();
				handler.getLevel().getGameObjectManager().getBulletManager().addBullet(new Bullet(handler, bulletX, bulletY, (270 + turretAngle) % 360));
			}
		}
	}

	/**
	 * getHealth
	 * Get the health of the player.
	 * @return The player's health.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * setHealth
	 * Sets the health of the player.
	 * @param health The player's health.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
}