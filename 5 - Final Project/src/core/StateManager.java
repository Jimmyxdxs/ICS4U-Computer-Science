package core;

import java.awt.Graphics;

/**
 * StateManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages all of the states in the game.
 */

public class StateManager {

	private State currentState; // Set the current game state.

	/**
	 * StateManager
	 * Constructs a new StateManager object.
	 * @param handler Manages all the important objects in the game.
	 */
	public StateManager(Handler handler) {
	}

	/**
	 * update
	 * Updates the current state.
	 */
	public void update() {
		if (currentState != null) { // Checks if the current state exists or not before updating.
			currentState.update();
		}
	}

	/**
	 * display
	 * Draws the current state to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void display(Graphics g) {
		if (currentState != null) { // Checks if the current state exists or not before displaying.
			currentState.display(g);
		}
	}

	/**
	 * getCurrentState
	 * Gets the current game state.
	 * @return The current game state.
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * setCurrentState
	 * Sets the current game state.
	 * @param state The current game state.
	 */
	public void setCurrentState(State state) {
		currentState = state;
	}
}
