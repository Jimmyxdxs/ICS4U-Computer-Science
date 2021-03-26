package core;

import java.io.Serializable;

/**
 * PlayerScore.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Stores a player score, which includes the username and the actual score.
 */

public class PlayerScore implements Serializable {

	private String username;
	private int score;

	/**
	 * PlayerScore
	 * @param username The player's name.
	 * @param score The player's score.
	 */
	public PlayerScore(String username, int score) {
		this.username = username;
		this.score = score;
	}

	/**
	 * getUsername
	 * Gets the player's name.
	 * @return The player's name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * getScore
	 * Gets the player's score
	 * @return The player's score.
	 */
	public int getScore() {
		return score;
	}
}
