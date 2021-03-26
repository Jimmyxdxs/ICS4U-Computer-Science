package entity;

import java.awt.Graphics;
import java.awt.Point;

import ai.AITank;
import core.Handler;

/**
 * Bullet.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Represents a bullet object.
 */

public class Bullet extends MovingGameObject {

	// Constants for the bullet.
	private static final double BULLET_WIDTH = 10;
	private static final double BULLET_HEIGHT = 10;
	private static final double BULLET_SPEED = 5;

	private BulletTail bulletTail;  //Particle system effect of bullet

	/**
	 * Bullet
	 * Constructs a new Bullet object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the bullet.
	 * @param y The y position of the bullet.
	 * @param bulletAngle The angle of travel of the bullet.
	 */
	public Bullet(Handler handler, double x, double y, double bulletAngle) {
		super(handler, x, y, BULLET_WIDTH, BULLET_HEIGHT, BULLET_SPEED, bulletAngle);
		angle = bulletAngle;
		speed = BULLET_SPEED;
		this.x = x;
		this.y = y;
		moveX = BULLET_SPEED * Math.cos(Math.toRadians(angle));
		moveY = BULLET_SPEED * Math.sin(Math.toRadians(angle));
		width = BULLET_WIDTH;
		height = BULLET_HEIGHT;
		rect.setWidth(width);
		rect.setHeight(height);
		rect.setOrigin(x, y);
		active = true;
		bulletTail = new BulletTail(handler, new PVector((float) (x + width), (float) (y + height)));
	}

	/**
	 * update
	 * Updates the bullet object.
	 */
	@Override
	public void update() {
		checkCollisionWithMovingObject();
		checkCollisionWithTile();
		bulletTail.setOrigin(new PVector((float) (x), (float) (y)));
		bulletTail.update();
	}

	/**
	 * checkCollisionWithMovingObject
	 * Checks if the bullet collides with a moving object, such as an AI or player tank.
	 */
	private void checkCollisionWithMovingObject() {

		// Checks if the bullet hitbox intersects with the player tank hitbox.
		if (rect.intersects(handler.getPlayerTank().getRect())) {
			active = false; // Remove the bullet from the screen.

			// Subtract 1 point from the player's health.
			handler.getPlayerTank().setHealth(handler.getPlayerTank().getHealth() - 1);

			// Display an explosion and play an explosion sound.
			handler.getExplosionManager().addAnimation(new Animation(handler, handler.getPlayerTank().getX(), handler.getPlayerTank().getY(), 5, false, handler.getMediaAssets().getExplosions()));
			handler.getMediaAssets().getExplosion().play();
		}

		// Iterate through the AITankManager LinkedList
		for (AITank ai : handler.getAITankManager().getAITanks()) {

			// Check if the bullet hitbox intersects with an AI tank's hitbox.
			if (rect.intersects(ai.getRect())) {
				active = false; // Remove the bullet from the screen.
				ai.setActive(false); // Remove the AI tank from the screen.

				// Display and explosion and play an explosion sound.
				handler.getExplosionManager().addAnimation(new Animation(handler, ai.getX(), ai.getY(), 10, false, handler.getMediaAssets().getExplosions()));
				handler.getMediaAssets().getExplosion().play();
			}
		}
	}

	/**
	 * checkCollisionWithTile
	 * Check if the bullet has collided with a solid tile, and determine the next action.
	 */
	private void checkCollisionWithTile() {
		Point tilePoint = checkTileCollision(); // Check the coordinates of the bullet collision with a tile.

		// Get the location of the tile that the bullet has collided with.
		Tile checkTile = handler.getLevel().getGrid().getTile(tilePoint.x / handler.getTileManager().getTileWidth(), tilePoint.y / handler.getTileManager().getTileHeight());

		// Checks if the bullet has collided with a sand, grass, or water tile (non-solid tiles).
		if ((checkTile.getTileID() == 0) || (checkTile.getTileID() == 1) || (checkTile.getTileID() == 2) || (checkTile.getTileID() == 5)) {

			// Bullet trajectory remains the same.
			x += moveX;
			y += moveY;
		} else {

			// Checks if the bullet has collided with a metal (border) tile.
			if (checkTile.getTileID() == 3) {
				if ((Math.ceil((y - 5) / handler.getTileManager().getTileHeight()) == 1) || (((int) (((y + 20) / handler.getTileManager().getTileHeight())) == handler.getLevel().getHeight() - 1))) {
					handler.getMediaAssets().getClank().play();
					angle = 360 - angle;
					moveX = speed * Math.cos(Math.toRadians(angle));
					moveY = speed * Math.sin(Math.toRadians(angle));
				}

				if ((Math.ceil((x - 5) / handler.getTileManager().getTileWidth()) == 1) || ((int) ((x + 20) / handler.getTileManager().getTileWidth()) == handler.getLevel().getWidth() - 1)) {
					handler.getMediaAssets().getClank().play();
					angle = 180 - angle;
					moveX = speed * Math.cos(Math.toRadians(angle));
					moveY = speed * Math.sin(Math.toRadians(angle));
				}
			}

			// Checks if the bullet has collided with a brick tile.
			if ((checkTile.getTileID() == 4)) {

				// Display an explosion animation and play an explosion sound.
				handler.getExplosionManager().addAnimation(new Animation(handler, tilePoint.x - handler.getTileManager().getTileWidth(), tilePoint.y - handler.getTileManager().getTileHeight(), 10, false, handler.getMediaAssets().getExplosions()));
				handler.getMediaAssets().getExplosion().play();
				handler.getMediaAssets().getCrumble().play();
				active = false;

				// Set the brick tile to a hole tile.
				handler.getLevel().getGrid().setGrid(tilePoint.x, tilePoint.y, 5);
			}
		}
	}

	/**
	 * display
	 * Draws the bullet to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		bulletTail.display(g);
	}

	/**
	 * getBulletWidth
	 * Gets the bullet width.
	 * @return The bullet width.
	 */
	public double getBulletWidth() {
		return BULLET_WIDTH;
	}

	/**
	 * getBulletHeight
	 * Gets the bullet height.
	 * @return The bullet height.
	 */
	public double getBulletHeight() {
		return BULLET_HEIGHT;
	}

	/**
	 * getBulletSpeed
	 * Gets the bullet speed.
	 * @return The bullet speed.
	 */
	public double getBulletSpeed() {
		return BULLET_SPEED;
	}
}
