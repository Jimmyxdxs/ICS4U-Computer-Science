package ai;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import core.Handler;
import core.Rect;
import entity.Tank;

/**
 * AITank.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Represents an AI Tank object.
 */

public class AITank extends Tank {

	private double targetX;
        private double targetY; // Contains the current x and y positions of the player's tank.
	private double targetBodyAngle; // Contains the angle from the AI tank to the player's tank.
	private AICommander aiCommander; // Contains the queue of AI commands to execute.
	private boolean stuck = false; //Status of getting stuck

	/**
	 * AITank
	 * Constructs a new AITank object.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the AI tank.
	 * @param y The y position of the AI tank.
	 */
	public AITank(Handler handler, double x, double y) {
		super(handler, x, y, Tank.DEFAULT_BODY_WIDTH, Tank.DEFAULT_BODY_HEIGHT, Tank.DEFAULT_AI_SPEED);
		speed = Tank.DEFAULT_AI_SPEED - 1;
		targetX = handler.getPlayerTank().getX();
		targetY = handler.getPlayerTank().getY();
		rect.setWidth(Tank.DEFAULT_BODY_WIDTH);
		rect.setHeight(Tank.DEFAULT_BODY_HEIGHT);
		rect.setOrigin(x, y);
		active = true;
		sameSpotTime = System.currentTimeMillis();
		aiCommander = new AICommander();
		angle = 0;
		bodyAngle = 90;
		moveX = speed * Math.cos(Math.toRadians(angle));
		moveY = speed * Math.sin(Math.toRadians(angle));
		path.add(new Point((int) x, (int) y));
		aiCommander.init();
	}

	/**
	 * update
	 * Updates the AI tank.
	 */
	@Override
	public void update() {
		aiCommander.update();
		execute();
		breakDeadLoopPath();
	}

	/**
	 * execute
	 * Executes the commands in the AI command list.
	 */
	public void execute() {

		// Checks for the command currently being executed and executes the action.
		switch (aiCommander.executeCommand()) {
		case "adjust": {
			adjust();
			break;
		}
		case "move": {
			move();
			break;
		}
		case "aim": {
			aim();
			break;
		}
		case "shoot": {
			shoot();
			break;
		}
		case "detour": {
			detour();
			break;
		}
		case "stop": {
		}
		}
	}

	/**
	 * breakDeadLoopPath
	 * Detects whether the AI tank is getting stuck. If so, find any open direction without optimization.
	 */
	public void breakDeadLoopPath() {
		int newAngle = 0;
		Point oldPoint;
                 
		if ((System.currentTimeMillis() - sameSpotTime > 800) && (!path.isEmpty())) {
			stuck = false;
			oldPoint = path.remove();
			path.add(new Point((int) x, (int) y));
			sameSpotTime = System.currentTimeMillis();
                        //When AI tank gets stuck, find an open space without optimazation.
			if ((Math.abs(oldPoint.x - x) < 20) && (Math.abs(oldPoint.y - y) < 20)) {
				stuck = true;

				newAngle = checkOpenSpace();
				if (newAngle != -1) {
					angle = newAngle;
					bodyAngle = (angle + 90) % 360;
					moveX = speed * Math.cos(Math.toRadians(angle));
					moveY = speed * Math.sin(Math.toRadians(angle));
				}
			}
		}
	}

	/**
	 * adjust
	 * Determines the direction that the AI tank should travel towards.
	 */
	public void adjust() {

		// Determines the exact angle that the player tank is relative to the AI tank.
		targetX = handler.getPlayerTank().getX();
		targetY = handler.getPlayerTank().getY();
		targetBodyAngle = Math.toDegrees(Math.atan2(targetY - y, targetX - x));

		if (targetBodyAngle < 0) {
			targetBodyAngle += 360;
		}

		// Determine the closest multiple of 45 that the AI tank should travel in.
		angle = (Math.round(targetBodyAngle / 45) * 45 + 360) % 360;
		bodyAngle = (angle + 90) % 360;
	}


	/**
	 * move
	 * Moves the AI tank.
	 */
	public void move() {

		// Checks if the AI tank moves into a solid object, and detours the tank.
		if (checkCollision()) {

			// Detour the tank.
			aiCommander.detourOnCollision();
		} else {

			// Continue the tank on its original trajectory.
			moveX = speed * Math.cos(Math.toRadians(angle));
			moveY = speed * Math.sin(Math.toRadians(angle));
			x += moveX;
			y += moveY;
			rect.setOrigin(x, y);
			path.add(new Point((int) x, (int) y));
			if (path.size() > 100) {
				path.remove();
			}
		}
	}

	/**
	 * aim
	 * Aims the turret on the AI tank.
	 */
	public void aim() {
		// Finds the angle of the player tank relative to the AI tank.
		targetX = handler.getPlayerTank().getX();
		targetY = handler.getPlayerTank().getY();
		turretAngle = Math.toDegrees(Math.atan2(targetY - y, targetX - x)) + 90;
	}

	/**
	 * detour
	 * Detours the AI tank with the best direction upon collision with a solid object.
	 */
	public void detour() {
		Point checkDetour = checkTileCollision();
		double firstAngle, secondAngle, firstDelta, secondDelta, deltaAngle;
		boolean finalCheck = false;
		double originalAngle = (angle + 360) % 360;
		double originalX = x;
		double originalY = y;
		int newAngle;
		// Randomly choose clockwise or couterclockwise direction to check first.
		firstAngle = 180;
		if ((int) (Math.random() * 2) == 0) {
			deltaAngle = 45;
		} else {
			deltaAngle = -45;
		}
		// If AI tank collides with other tanks, find an open space to move.
		if (checkMovingObjectCollision()) {
			newAngle = checkOpenSpace();
			if (newAngle != -1) {
				angle = newAngle;
			} else {
				angle += deltaAngle * 3;
			}
			// If AI tank collides with a solid object, get directions checking clockwise and counterclockwise.
			// Subtract each value from the original direction. The direction with smaller detla value is chosen as the final.
		} else {
			do {
				// Set current checking angle by adding the delta angle.
				angle = (angle + deltaAngle + 360) % 360;
				moveX = speed * Math.cos(Math.toRadians(angle));
				moveY = speed * Math.sin(Math.toRadians(angle));
				checkDetour = checkTileCollision();
				// When tank doesn't collide with a sold object
				if ((checkDetour.x == -1) && (checkDetour.y == -1)) {
					// Set the first angle and switch to check the second angle.
					if (!finalCheck) {
						firstAngle = angle;
						finalCheck = true;
						angle = originalAngle;
						x = originalX;
						y = originalY;
						rect.setOrigin(x, y);
						path.add(new Point((int) x, (int) y));
						if (path.size() > 100) {
							path.remove();
						}
						deltaAngle = -deltaAngle;
						// Set the second angle and compare delta value. Set the final angle.
					} else {
						secondAngle = angle;
						firstDelta = Math.abs(firstAngle - originalAngle);
						secondDelta = Math.abs(secondAngle - originalAngle);
						if (firstDelta > 180) {
							firstDelta = 360 - firstDelta;
						}
						if (secondDelta > 180) {
							secondDelta = 360 - secondDelta;
						}
						if (firstDelta < secondDelta) {
							angle = firstAngle;
						}
						break;
					}
				}
			} while (true);
		}
		moveX = speed * Math.cos(Math.toRadians(angle));
		moveY = speed * Math.sin(Math.toRadians(angle));
		bodyAngle = (angle + 90) % 360;
	}

	/**
	 * checkOpenSpace
	 * Find the open space around and return the direction.
	 * @return An integer representing the angle of travel, or -1 if there is no open space.
	 */
	public int checkOpenSpace() {
		double tx, ty;
		Rect tempRect;
		double checkMoveX, checkMoveY;
		int checkAngle = (int) (angle + 135) % 360;
		boolean open = false;
		int deltaAngle;
		if ((int) (Math.random() * 2) == 0) {
			deltaAngle = 45;
		} else {
			deltaAngle = -45;
		}

		for (int i = 0; i < 8; i++) {
			if (checkAngle == (int) angle) {
				open = false;
			} else {
				checkMoveX = speed * Math.cos(Math.toRadians(checkAngle));
				checkMoveY = speed * Math.sin(Math.toRadians(checkAngle));
				open = true;

				tx = (x + 5 * checkMoveX);
				ty = (y + 5 * checkMoveY);
				tempRect = new Rect(tx, ty, width, height);

				if ((tempRect.intersects(handler.getPlayerTank().getRect()))) {
					getAiCommander().getCommands().add(0, "aim");
					getAiCommander().getCommands().add(1, "shoot");

					open = false;
				} else {
					for (AITank ai : handler.getAITankManager().getAITanks()) {
						if ((!(this.equals(ai))) && (tempRect.intersects(ai.getRect()))) {
							if (ai.getStuck()) {
								ai.getAiCommander().getCommands().add(0, "stop");
							}
							getAiCommander().getCommands().remove("stop");
							open = false;
							break;
						}
					}
					if (open) {
						for (int j = 1; j < 5; j++) {
							if (collisionWithTile((int) (x + j * (checkMoveX)) / handler.getTileManager().getTileWidth(), (int) (y + j * (checkMoveY)) / handler.getTileManager().getTileHeight())) {
								open = false;
								break;
							}
						}
					}

				}
				if (open) {
					return checkAngle;
				}
			}
			checkAngle = (checkAngle + deltaAngle + 360) % 360;
		}
		return -1;
	}

	/**
	 * display
	 * Draws the AI tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		displayBody(g);
		displayTurret(g);
	}

	/**
	 * displayBody
	 * Draws the body of the AI tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	private void displayBody(Graphics g) {
		g2d = (Graphics2D) g;
		backup = g2d.getTransform();
		transform = new AffineTransform();

		// Rotates the AI tank body based on the direction of travel.
		transform.rotate(Math.toRadians(bodyAngle), (int) (x + (bodyWidth / 2) - handler.getMapMovement().getXOffset()),
				(int) (y + (bodyHeight / 2)) - handler.getMapMovement().getYOffset());
		g2d.transform(transform);

		// Draws the AI tank body to the screen.
		g2d.drawImage(handler.getMediaAssets().getAITank(), (int) (x - handler.getMapMovement().getXOffset()), (int) (y - handler.getMapMovement().getYOffset()), (int) bodyWidth, (int) bodyHeight, null);
		g2d.setTransform(backup);
	}

	/**
	 * displayTurret
	 * Draws the turret of the AI tank to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	private void displayTurret(Graphics g) {
		g2d = (Graphics2D) g;
		backup = g2d.getTransform();
		transform = new AffineTransform();

		// Rotates the AI tank turret based on the direction of aim.
		transform.rotate(Math.toRadians(turretAngle), (int) (x + (bodyWidth / 2) - handler.getMapMovement().getXOffset()), (int) (y + (bodyHeight / 2) - handler.getMapMovement().getYOffset()));
		g2d.transform(transform);

		// Draws the AI tank turret to the screen.
		g2d.drawImage(handler.getMediaAssets().getAITurret(), (int) (x + (bodyWidth / 2) - (turretWidth / 2) - handler.getMapMovement().getXOffset()),
				(int) (y + (bodyHeight / 2) - (turretHeight / 1.5) - handler.getMapMovement().getYOffset()),
				(int) turretWidth, (int) turretHeight, null);

		g2d.setTransform(backup);
	}

	/**
	 * getAiCommander
	 * Returns the list of AI commands.
	 * @return The list of AI commands.
	 */
	public AICommander getAiCommander() {
		return aiCommander;
	}

	/**
	 * getStuck
	 * Returns a boolean on whether the AI Tank is spinning in a circle or not.
	 * @return A boolean true, if the AI tank is stuck, or false, if it is not.
	 */
	public boolean getStuck() {
		return stuck;
	}
}
