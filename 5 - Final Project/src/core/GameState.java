package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * GameState.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Contains the game state required to play the game.
 */

public class GameState extends State {

	private Level level;
	private boolean pause;
	private long timeStart;
	private long timeNow;
	private long timeRemaining;
	private final String PAUSE_INFO = "Game paused. Press Space key to continue...";

	/**
	 * GameState
	 * Constructs a new GameState object.
	 * @param handler Manages all the important objects in the game.
	 */
	public GameState(Handler handler) {
		super(handler);
		pause = false;
		timeStart = System.currentTimeMillis();

		// Selects a map based on the difficulty selected.
		if (handler.getTankGame().getMapSize() == 1) {
			level = new Level(handler, System.getProperty("user.dir").replace("\\", "/") + "/res/levels/Level1.txt");
		} else {
			level = new Level(handler, System.getProperty("user.dir").replace("\\", "/") + "/res/levels/Level2.txt");
		}
		handler.setLevel(level);
	}

	/**
	 * update
	 * Updates the game state.
	 */
	@Override
	public void update() {
		if (handler.getKeyManager().getPause()) { // Checks if the player has paused the game.
			pause = true;
		}

		if (pause) { // Checks if the game is paused.
			if (handler.getKeyManager().getIsSpacePressed()) { // Checks if the player has pressed the spacebar to resume.
				pause = false;

			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					Logger.getLogger(GameState.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				}
			}
		} else {
			timeNow = System.currentTimeMillis();
			level.update();
		}
		timeRemaining = 300000 - (timeNow - timeStart);

		if ((handler.getPlayerTank().getHealth() <= 0) || (timeRemaining <= 0)) {
			try {
				handler.getStateManager().setCurrentState(new GameOverState(handler));
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * getTimeRemaining
	 * Gets the time remaining.
	 * @return A long, how much time is remaining in milliseconds.
	 */
	public long getTimeRemaining() {
		return timeRemaining;
	}

	/**
	 * display Draws the game state to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		level.display(g);

		// Checks if the game is paused.
		if (pause) {

			// Draws the pause message to the screen.
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString(PAUSE_INFO, handler.getTankGame().getHeight() / 3 + 3, handler.getTankGame().getHeight() / 2 - 200 + 3);
			g.setColor(Color.WHITE);
			g.drawString(PAUSE_INFO, handler.getTankGame().getHeight() / 3, handler.getTankGame().getHeight() / 2 - 200);
		}
	}
}
