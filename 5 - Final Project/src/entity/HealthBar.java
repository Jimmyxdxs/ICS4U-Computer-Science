package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import core.GameState;
import core.Handler;

/**
 * HealthBar.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Represents a health bar that keeps track of the player's health.
 */

public class HealthBar extends GameObject {

	private double healthWidth;

	/**
	 * HealthBar
	 * Constructs a new HealthBar.
	 * @param handler Manages all the important objects in the game.
	 * @param x The x position of the health bar.
	 * @param y The y position of the health bar.
	 * @param width The width of the health bar.
	 * @param height The height of the health bar.
	 */
	public HealthBar(Handler handler, double x, double y, double width, double height) {
		super(handler, x, y, width, height);
		this.width = width;
		healthWidth = width;
	}

	/**
	 * update
	 * Updates the health bar.
	 */
	@Override
	public void update() {
		healthWidth = width * ((double) handler.getPlayerTank().getHealth() / Tank.DEFAULT_PLAYER_HEALTH);
	}

	/**
	 * display
	 * Displays the health bar to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Display the health bar.
		g.setColor(Color.BLACK);
		g.fillRect((int) (x - 2), (int) (y - 2), (int) (width + 8), (int) (height + 8));
		g.setColor(new Color(255, 255, 0, 180));
		g.fillRect((int) (x - 5), (int) (y - 5), (int) (width + 10), (int) (height + 10));
		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		g.setColor(new Color(255, 0, 0, 180));
		g.fillRect((int) x, (int) y, (int) healthWidth, (int) height);
		g.setColor(Color.BLACK);

		// Display the player's score.
		g.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		g.drawString(Integer.toString(handler.getGameObjectManager().getPlayerScore()), 273, 63);
		g.setColor(Color.RED);
		g.drawString(Integer.toString(handler.getGameObjectManager().getPlayerScore()), 275, 65);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.WHITE);

		// Display the time remaining.
		if ("GameState".equals(handler.getState().getClass().getSimpleName())) {
			g.drawString("0" + Integer.toString((int) ((GameState) handler.getState()).getTimeRemaining() / 1000 / 60) + ":" + String.format("%02d", (int) ((GameState) handler.getState()).getTimeRemaining() / 1000 % 60), (int) x - 1, (int) y - 15 - 1);
			g.setColor(Color.RED);
			g.drawString("0" + Integer.toString((int) ((GameState) handler.getState()).getTimeRemaining() / 1000 / 60) + ":" + String.format("%02d", (int) ((GameState) handler.getState()).getTimeRemaining() / 1000 % 60), (int) x, (int) y - 15);
		}
	}
}
