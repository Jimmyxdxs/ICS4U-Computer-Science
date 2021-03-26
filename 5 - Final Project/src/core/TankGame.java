package core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * TankGame.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Manages all the controls and mechanics behind the game.
 */

public class TankGame implements Runnable {

	private Window window; // Window to contain all of the game elements.
	private String title; // Title of the window.
	private int width, height; // Width and height of the window.
	private boolean running; // Boolean to check if the current thread is running or not.
	private Thread thread; // Thread to run my application on.
	private BufferStrategy bufferStrategy; // BufferStrategy is used to draw off-screen images before displaying to the user.
	private Graphics g; // Graphics object to be able to draw to the screen.
	private KeyManager keyManager; // KeyManager to handle the key events inputted by the user.
	private MouseManager mouseManager; // MouseManager to handle the mouse events inputted by the user.
	private MapMovement mapMovement; // MapMovement object to move the map when the player moves.
	private String playerName; // Stores the player's username for high score purposes.
	private int mapSize; // Stores the size of the map.
	private int difficulty; // Stores the difficulty of the game.
	private Handler handler; // Manages all the important objects in the game.
	private StateManager stateManager; // Manages all the game states.
	private MediaAssets mediaAssets; // Manages all the assets, including images and sound.

	/**
	 * TankGame
	 * Constructs a new TankGame object.
	 * @param title The title of the window.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 */
	public TankGame(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		mapSize = 1;
		difficulty = 1;
		playerName = "Anonymous";
		mediaAssets = new MediaAssets();
		running = false;
	}

	/**
	 * init
	 * Initializes all the key components required for the game to start.
	 */
	private void init() {
		window = new Window(title, width, height); // Sets the window with the desired title, width, and height.

		// Set the key and mouse managers to focus within the window and canvas.
		window.getFrame().addKeyListener(keyManager);
		window.getFrame().addMouseListener(mouseManager);
		window.getFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);

		handler = new Handler(this);
		mapMovement = new MapMovement(handler, 0, 0); // Create a new MapMovement object for the player.
		stateManager = new StateManager(handler); // Create a new StateManager to manages the states.
		stateManager.setCurrentState(new HomePageState(handler)); // Set the default state as the home page state.
	}

	/**
	 * update
	 * Updates the key manager and the state manager in TankGame.
	 */
	private void update() {
		keyManager.update();
		stateManager.update();
	}

	/**
	 * display
	 * Displays the contents of TankGame.
	 */
	private void display() {
		bufferStrategy = window.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) { // Checks if the BufferStrategy does not exist before creating a new one.
			window.getCanvas().createBufferStrategy(3);
			return;
		}

		// Draw and clear graphics from the BufferStrategy.
		g = bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		stateManager.display(g);
		bufferStrategy.show();
		g.dispose();
	}

	/**
	 * run
	 * Runs the game.
	 */
	@Override
	public void run() {

		init(); // Initialize everything in TankGame.

		int framePerSecond = 60; // Number of frames per second.
		double timePerUpdate = 1000000000 / framePerSecond; // Amount of frames required per second.
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); // Gets the current time in nanoseconds.
		long timer = 0; // Stores how much time has elapsed.

		// Makes sure that the game can run consistently with 60 FPS on every computer.
		while (running) {
			now = System.nanoTime(); // Get the current time in nanoseconds.
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime; // Adds to how much time has passed.
			lastTime = now; // Reset past time to current time.

			// Timer to check when to call the update and display methods again.
			if (delta >= 1) {
				update();
				display();
				delta--;
			}

			// Check if the timer has reached 1 second and reset.
			if (timer >= 1000000000) {
				timer = 0;
			}
		}
		stop(); // Stop the game thread if running is false.
	}

	/**
	 * start
	 * Starts the game thread.
	 */
	public synchronized void start() {
		if (running) { // Checks if the game thread is already running.
			return;
		}

		// Start the game thread.
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * stop
	 * Stops the game thread.
	 */
	public synchronized void stop() {
		if (!running) { // Checks if the game thread has already stopped.
			return;
		}

		// Stop the game thread.
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getWidth
	 * Gets the width of the window.
	 * @return The width of the window.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * getHeight
	 * Gets the height of the window.
	 * @return The height of the window.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * getKeyManager
	 * Gets the key manager that deals with key events inputted by the user.
	 * @return The key manager that deals with key events.
	 */
	public KeyManager getKeyManager() {
		return keyManager;
	}

	/**
	 * getMouseManager
	 * Gets the mouse manager that deals with mouse events inputted by the user.
	 * @return The mouse manager that deals with mouse events.
	 */
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	/**
	 * getMapMovement
	 * Gets the map movement object that allows the map to move with the player.
	 * @return The map movement that allows the map to move with the player.
	 */
	public MapMovement getMapMovement() {
		return mapMovement;
	}

	/**
	 * getStateManager
	 * Gets the manager that manages the game states.
	 * @return The manager that manages the game states.
	 */
	public StateManager getStateManager() {
		return stateManager;
	}

	/**
	 * getWindow
	 * Gets the window of the game.
	 * @return The window of the game.
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * setPlayerName
	 * Sets the player's username.
	 * @param playerName The player's username.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * getPlayerName
	 * Gets the player's username.
	 * @return The player's username.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * getMapSize
	 * Gets the size of the map.
	 * @return The size of the map.
	 */
	public int getMapSize() {
		return mapSize;
	}

	/**
	 * getDifficulty
	 * Gets the difficulty of the selected level.
	 * @return The difficulty of the game.
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * setDifficulty
	 * Sets the difficulty of the game.
	 * @param difficulty The difficulty of the game.
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * setMapSize
	 * Sets the map size.
	 * @param i The map size.
	 */
	public void setMapSize(int i) {
		this.mapSize = i;
	}

	/**
	 * setStatus
	 * Sets the status of the game loop.
	 * @param running A boolean, true, if the game is running, or false, if it is not.
	 */
	public void setStatus(boolean running) {
		this.running = running;
	}

	/**
	 * getStatus
	 * Gets the status of the game loop.
	 * @return A boolean, true, if the game is running, or false, if it is not.
	 */
	public boolean getStatus() {
		return running;
	}

	/**
	 * getThread
	 * Gets the thread of the game.
	 * @return The thread of the game.
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * getMediaAssets
	 * Gets the assets, including sound and images, that are used in the game.
	 * @return The assets that are used in the game.
	 */
	public MediaAssets getMediaAssets() {
		return mediaAssets;
	}
}
