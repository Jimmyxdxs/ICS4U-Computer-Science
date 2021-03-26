package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages all the key actions and events that the player enters.
 */

public class KeyManager implements KeyListener {

	private boolean[] keys; // Boolean array stores the state of each key on the keyboard (true = pressed, false = not pressed).

	// Booleans to store the state of each key (true = pressed, false = not pressed).
	private boolean up;
        private boolean down;
        private boolean left;
        private boolean right;
        private boolean rotateCounterClockwise;
        private boolean rotateClockwise;
        private boolean shoot;
        private boolean pause;
	private boolean isSpacePressed;

	/**
	 * KeyManager
	 * Constructs a new KeyManager.
	 */
	public KeyManager() {
		keys = new boolean[256];
	}

	/**
	 * update
	 * Updates the key manager with the key actions.
	 */
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		rotateCounterClockwise = keys[KeyEvent.VK_A];
		rotateClockwise = keys[KeyEvent.VK_D];
		shoot = keys[KeyEvent.VK_S];
		pause = keys[KeyEvent.VK_ESCAPE];
		isSpacePressed = keys[KeyEvent.VK_SPACE];
	}

	/**
	 * keyPressed
	 * Checks whether a key is pressed or not.
	 * @param e The key event that detects key actions.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * keyReleased
	 * Checks whether a key has been released or not.
	 * @param e The key event that detects key actions.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * keyTyped
	 * Checks whether a key has been typed or not.
	 * @param e They key event that detects key actions.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * getUp
	 * Gets the boolean state of the up arrow key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getUp() {
		return up;
	}

	/**
	 * getDown
	 * Gets the boolean state of the down arrow key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getDown() {
		return down;
	}

	/**
	 * getLeft
	 * Gets the boolean state of the left arrow key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getLeft() {
		return left;
	}

	/**
	 * getRight
	 * Gets the boolean state of the right arrow key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getRight() {
		return right;
	}

	/**
	 * getRotateCounterClockwise
	 * Gets the boolean state of the 'a' key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getRotateCounterClockwise() {
		return rotateCounterClockwise;
	}

	/**
	 * getRotateClockwise
	 * Gets the boolean state of the 'd' key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getRotateClockwise() {
		return rotateClockwise;
	}

	/**
	 * getShoot
	 * Gets the boolean state of the 's' key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getShoot() {
		return shoot;
	}

	/**
	 * getPause
	 * Gets the boolean state of the escape key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getPause() {
		return pause;
	}

	/**
	 * getIsSpacePressed
	 * Gets the boolean state of the space key.
	 * @return A boolean, true, if the key is being pressed, or false, if it is not.
	 */
	public boolean getIsSpacePressed() {
		return isSpacePressed;
	}
}
