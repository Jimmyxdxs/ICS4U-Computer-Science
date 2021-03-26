package ai;

import java.util.LinkedList;

/**
 * AICommander.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Manages the commands that an AI tank executes.
 */

public final class AICommander {

	private LinkedList<String> commands; // Linked list of commands.

	/**
	 * AICommander
	 * Constructs a new AICommander object.
	 */
	public AICommander() {
		commands = new LinkedList<>();
		reload();
	}

	/**
	 * update
	 * Updates the AI command list.
	 */
	public void update() {
		if (commands.size() <= 1) {
			reload();
		}
	}

	/**
	 * executeCommand
	 * Remove and return commands from the queue.
	 * @return The first command in the command list.
	 */
	public String executeCommand() {
		return commands.remove();
	}

	/**
	 * detourOnCollision
	 * Insert the detour commands to the head of queue.
	 */
	public void detourOnCollision() {
		commands.add(0, "detour");
		for (int i = 1; i < 20; i++) {
			commands.add(i, "move");
		}
		commands.add("adjust");
		commands.add("aim");
	}


	/**
	 * init
	 * Initializes the AI command list.
	 */
	public void init() {
		for (int i = 0; i < 30; i++) {
			commands.add("move");
		}
	}

	/**
	 * reload
	 * Reloads the AI command list.
	 */
	public void reload() {
		commands.add("adjust");
		commands.add("aim");
		for (int i = 0; i < 100; i++) {
			commands.add("move");
		}
		commands.add("adjust");
		commands.add("aim");
		commands.add("shoot");
		for (int i = 0; i < 100; i++) {
			commands.add("move");
		}
	}

	/**
	 * getCommands
	 * Gets the queue of AI commands.
	 * @return The list of commands.
	 */
	public LinkedList<String> getCommands() {
		return commands;
	}
}