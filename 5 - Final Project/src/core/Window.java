package core;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Window
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a new window for the game to display the graphics and play the game in.
 */

public class Window {

	private JFrame frame;
	private Canvas canvas;

	// Stores the title, width, and height of the window.
	private String title;
	private int width;
        private int height;

	/**
	 * Window
	 * Constructs a new Window object.
	 * @param title The title of the window.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 */
	public Window (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createWindow();
	}

	/**
	 * createWindow
	 * Creates a window composed of a JFrame and a Canvas.
	 */
	private void createWindow() {

		// Create a new JFrame and set all the standard properties.
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Set a new Canvas with all the standard properties.
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}

	/**
	 * getFrame
	 * Gets the frame of the window.
	 * @return The frame of the window.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * getCanvas
	 * Gets the canvas in the window.
	 * @return The canvas in the window.
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * getWidth
	 * Gets the width of the window.
	 * @return The width of the window.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * getHeight
	 * Gets the height of the window.
	 * @return The height of the window.
	 */
	public int getHeight(){
		return height;
	}
}
