package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * MouseManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Manages the mouse events and movements that the user inputs.
 */

public class MouseManager implements MouseListener, MouseMotionListener {

	// Checks whether or not the mouse buttons are being pressed.
	private boolean leftPressed;
        private boolean rightPressed;
	private int mouseX;
        private int mouseY; // Gets the x and y positions of the cursor.
	private UIManager uiManager;

	/**
	 * MouseManager
	 * Constructs a new mouse manager object.
	 */
	public MouseManager() {
	}

	/**
	 * mousePressed
	 * Checks whether or not the mouse buttons are pressed.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) { // Checks if the left mouse button is being pressed.
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) { // Checks if the right mouse button is being pressed.
			rightPressed = true;
		}
	}

	/**
	 * mouseReleased
	 * Checks whether or not the mouse buttons are released.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // Checks if the left mouse button is released.
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) { // Checks if the right mouse button is released.
			rightPressed = false;
		}

		// Updates if any UI objects are being mouse released.
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	/**
	 * mouseMoved
	 * Checks if the mouse cursor is being moved.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		// Get the x and y positions of the cursor.
		mouseX = e.getX();
		mouseY = e.getY();

		// Updates if any UI objects have a mouse moved over them.
		if (uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	/**
	 * getUIManager
	 * Gets the UI manager.
	 * @return The UI manager.
	 */
	public UIManager getUIManager() {
		return uiManager;
	}

	/**
	 * setUIManager
	 * Sets the UI manager.
	 * @param uiManager The UI manager.
	 */
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	/**
	 * isLeftPressed
	 * Gets whether or not the left mouse button is pressed.
	 * @return A boolean, true, if the left mouse button is pressed, or false, if it is not.
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}

	/**
	 * isRightPressed
	 * Gets whether or not the right mouse button is pressed.
	 * @return A boolean, true, if the right mouse button is pressed, or false, if it is not.
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}

	/**
	 * getMouseX
	 * Gets the x position of the cursor on the window.
	 * @return The x position of the cursor on the window.
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * getMouseY
	 * Gets the y position of the cursor on the window.
	 * @return The y position of the cursor on the window.
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * mouseDragged
	 * Detects whether or not the mouse is being dragged.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * mouseClicked
	 * Detects whether or not the mouse is being clicked.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * mouseEntered
	 * Detects whether or not the mouse is being entered.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * mouseExited
	 * Detects whether or not the mouse is being exited.
	 * @param e The mouse event that detects mouse actions.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
