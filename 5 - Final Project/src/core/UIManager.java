package core;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * UIManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages all the UI objects in the game.
 */

public class UIManager {

	private LinkedList<UIObject> uiObjects; // List stores all of the UI objects.

	/**
	 * UIManager
	 * Constructs a new UIManager.
	 * @param handler Manages all of the important objects in the game.
	 */
	public UIManager(Handler handler) {
		uiObjects = new LinkedList<>();
	}

	/**
	 * update
	 * Updates the elements in the UI manager.
	 */
	public void update() {

		// Iterate through the list of UI objects and update each one.
		for (UIObject uiObject: uiObjects) {
			uiObject.update();
		}
	}

	/**
	 * display
	 * Displays the elements in the UI manager to the screen.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	public void display(Graphics g) {

		// Iterates through the list of UI objects and display each one.
		for (UIObject uiObject: uiObjects) {
			uiObject.display(g);
		}
	}

	/**
	 * onMouseMove
	 * Detects mouse movement for each UI object.
	 * @param e The mouse event that detects mouse actions.
	 */
	public void onMouseMove(MouseEvent e) {

		// Iterate through the list of UI objects and detect mouse movement.
		for (UIObject uiObject: uiObjects) {
			uiObject.onMouseMove(e);
		}
	}

	/**
	 * onMouseRelease
	 * Detects mouse releasing for each UI object.
	 * @param e The mouse event that detects mouse actions.
	 */
	public void onMouseRelease(MouseEvent e) {

		// Iterates through the list of UI objects and check if a mouse has been released on one of them.
		for (UIObject uiObject: uiObjects) {
			uiObject.onMouseRelease(e);
		}
	}

	/**
	 * addUIObject
	 * Adds a UI object to the list.
	 * @param uiObject The UI object to be added.
	 */
	public void addObject(UIObject uiObject) {
		uiObjects.add(uiObject);
	}

	/**
	 * removeUIObject
	 * Removes a UI object from the list.
	 * @param uiObject The UI object to be removed.
	 */
	public void removeObject(UIObject uiObject) {
		uiObjects.remove(uiObject);
	}
}