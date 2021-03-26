package core;

import entity.GameWorld;

/**
 * State.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a template for the other states in the game.
 */

public abstract class State implements GameWorld {

	protected Handler handler;
	protected UIManager uiManager; // UI Manager deals with any UI components in each state.

	/**
	 * State
	 * Constructs a new State object.
	 * @param handler Manages all the important objects in the game.
	 */
	public State(Handler handler) {
		this.handler = handler;
	}

	/**
	 * getUIManager
	 * Gets the UI manager of the current state.
	 * @return The UI manager of the current state.
	 */
	public UIManager getUIManager(){
		return uiManager;
	}
}
