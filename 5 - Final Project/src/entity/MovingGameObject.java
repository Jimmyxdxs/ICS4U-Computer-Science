package entity;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import ai.AITank;
import core.Handler;
import core.Rect;

/**
 * MovingGameObject.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Represents a game object that has movement.
 */

public abstract class MovingGameObject extends GameObject {

	protected double moveX;
        protected double moveY; // The x and y increment or decrement.
	protected double speed; // The speed of the movement.
	protected double angle; // The angle for the direction of travel.
	protected long sameSpotTime = 0;
	protected Queue<Point> path;

	/**
	 * MovingGameObject
	 * Constructs a new MovingGameObject.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the moving game object.
	 * @param y The y position of the moving game object.
	 * @param width The width of the moving game object.
	 * @param height The height of the moving game object.
	 * @param speed The speed of the moving game object.
	 * @param angle The angle of the moving game object.
	 */
	public MovingGameObject(Handler handler, double x, double y, double width, double height, double speed, double angle) {
		super(handler, x, y, width, height);
		moveX = 0;
		moveY = 0;
		this.speed = speed;
		active = true;
		this.handler = handler;
		path = new LinkedList<Point>();
	}

	/**
	 * checkCollision
	 * Detects collisions with solid or moving objects.
	 * @return A boolean, true, if there is collision, or false, if there is not.
	 */
	public boolean checkCollision() {
		Point checkDetour;

		// Checks for collisions with other moving game objects.
		if (!checkMovingObjectCollision()) {
			checkDetour = checkTileCollision(); // Detours upon collision

			// Checks if the moving game object collides with a non-solid tile.
			if ((checkDetour.x == -1) && (checkDetour.y == -1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checkTileCollision
	 * Checks if the moving game object collides with a solid, static tile.
	 * @return A boolean, true, if there is collision, or false, if there is not.
	 */
	public Point checkTileCollision() {
		Point point = new Point(-1, -1);
		int tempX = (int) (x + moveX);
		int tempY = (int) (y + moveY);

		// Checks all corners of the moving object colliding to see if there is a collision with a tile.
		if (collisionWithTile(tempX / handler.getTileManager().getTileWidth(), (int) ((tempY + rect.getHeight() / 2) / handler.getTileManager().getTileHeight()))) {
			point.x = tempX;
			point.y = (int) (tempY + rect.getHeight() / 2);
		} else if (collisionWithTile((int) ((tempX + rect.getWidth() / 2) / handler.getTileManager().getTileWidth()), tempY / handler.getTileManager().getTileHeight())) {
			point.x = (int) (tempX + rect.getWidth() / 2);
			point.y = tempY;
		} else if (collisionWithTile((int) ((tempX + rect.getWidth()) / handler.getTileManager().getTileWidth()), (int) ((tempY + rect.getHeight() / 2) / handler.getTileManager().getTileHeight()))) {
			point.x = (int) (tempX + rect.getWidth());
			point.y = (int) (tempY + rect.getHeight() / 2);
		} else if (collisionWithTile((int) ((tempX + rect.getWidth() / 2) / handler.getTileManager().getTileWidth()), (int) ((tempY + rect.getHeight()) / handler.getTileManager().getTileHeight()))) {
			point.x = (int) (tempX + rect.getWidth() / 2);
			point.y = (int) (tempY + rect.getHeight());
		} else if (collisionWithTile((int) ((tempX + rect.getWidth() / 2) / handler.getTileManager().getTileWidth()), (int) ((tempY + rect.getHeight() / 2) / handler.getTileManager().getTileHeight()))) {
			point.x = (int) (tempX + rect.getWidth() / 2);
			point.y = (int) (tempY + rect.getHeight() / 2);
		} else if (collisionWithTile(tempX / handler.getTileManager().getTileWidth(), tempY / handler.getTileManager().getTileHeight())) {
			point.x = tempX;
			point.y = tempY;
		} else if (collisionWithTile((int) ((tempX + rect.getWidth()) / handler.getTileManager().getTileWidth()), tempY / handler.getTileManager().getTileHeight())) {
			point.x = (int) (tempX + rect.getWidth());
			point.y = tempY;
		} else if (collisionWithTile(tempX / handler.getTileManager().getTileWidth(), (int) ((tempY + rect.getHeight()) / handler.getTileManager().getTileHeight()))) {
			point.x = tempX;
			point.y = (int) (tempY + rect.getHeight());
		} else if (collisionWithTile((int) ((tempX + rect.getWidth()) / handler.getTileManager().getTileWidth()), (int) ((tempY + rect.getHeight()) / handler.getTileManager().getTileHeight()))) {
			point.x = (int) (tempX + rect.getWidth());
			point.y = (int) (tempY + rect.getHeight());
		} else {
			x += moveX;
			y += moveY;
			rect.setOrigin(x, y);
			path.add(new Point((int) x, (int) y));
			if (path.size() > 100) {
				path.remove();
			}
		}
		return point;
	}

	/**
	 * checkMovingObjectCollision
	 * Detects if there is collision with another moving game object.
	 * @return A boolean, true, if there is collision, or false, if there is not.
	 */
	public boolean checkMovingObjectCollision() {
		double tx = (x + moveX); // Get the predicted x location
		double ty = (y + moveY); // Get the predicted y location

		Rect tempRect = new Rect(tx, ty, width, height); // Get the hitbox of the predicted moving game object.

		// Checks if the current moving object hitbox intersects the player tank's hitbox.
		if ((!(this.equals(handler.getPlayerTank()))) && (tempRect.intersects(handler.getPlayerTank().getRect()))) {
			return true;
		}

		// Iterate through the list of AI tanks.
		for (AITank ai : handler.getAITankManager().getAITanks()) {

			// Checks if the current moving object hitbox intersects another AI tank's hitbox.
			if ((!(this.equals(ai))) && (tempRect.intersects(ai.getRect()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checkStaticObjectCollision
	 * Detects if there is a collision with a powerup.
	 */
	public void checkStaticObjectCollision() {

		Iterator<GameObject> it = handler.getGameObjectManager().getGameObjects().iterator();

		// Iterate through the list of game objects.
		while (it.hasNext()) {
			GameObject p = it.next();
			if (rect.intersects(p.getRect())) {

				// Checks if the current moving game object's hitbox intersects the diamond powerup's hitbox.
				if ("DiamondPowerUp".equals(p.getClass().getSimpleName())) {
					p.setActive(false);
					handler.getMediaAssets().getPowerUpSound().play();
					handler.getGameObjectManager().destroyAll();
				}

				// Checks if the current moving game object's hitbox intersects the health powerup's hitbox.
				if ("HealthPowerUp".equals(p.getClass().getSimpleName())) {
					handler.getPlayerTank().setHealth(Tank.DEFAULT_PLAYER_HEALTH);
					p.setActive(false);
					handler.getMediaAssets().getPowerUpSound().play();
				}
			}
		}
	}

	/**
	 * collisionWithTile
	 * Checks if the moving game object has collided with a solid tile.
	 * @param x The x position of the tile.
	 * @param y The y position of the tile.
	 */
	@Override
	protected boolean collisionWithTile(int x, int y) {
		return handler.getLevel().getGrid().getTile(x, y).isSolid();
	}

	/**
	 * getMoveX
	 * Get the x increment or decrement of the movement.
	 * @return The x increment or decrement.
	 */
	public double getMoveX() {
		return moveX;
	}

	/**
	 * setMoveX
	 * Sets the x increment or decrement of the movement.
	 * @param x The x increment or decrement.
	 */
	public void setMoveX(double x) {
		moveX = x;
	}

	/**
	 * getMoveY
	 * Gets the y increment or decrement of the movement.
	 * @return The y increment or decrement of the movement.
	 */
	public double getMoveY() {
		return moveX;
	}

	/**
	 * setMoveY
	 * Sets the y increment or decrement of the movement.
	 * @param y The y increment or decrement of the movement.
	 */
	public void setMoveY(double y) {
		moveY = y;
	}

	/**
	 * getActive
	 * Gets whether the moving game object is active or not.
	 * @return A boolean, true, if the moving game object is active, or false, if it is not.
	 */
	@Override
	public boolean getActive() {
		return active;
	}

	/**
	 * setActive
	 * Sets whether the moving game object is active or not.
	 * @param active If the moving game object is active or not.
	 */
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
}
