package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * PlayerScoreManager.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 19, 2021
 * Manages and sorts the game's high scores.
 */

public class PlayerScoreManager implements Serializable {

	private LinkedList<PlayerScore> scoreList; // Stores the player scores.

	private String fileName = System.getProperty("user.dir").replace("\\", "/") + "/highscores.txt";
	private File file;

	/**
	 * PlayerScoreManager
	 * Constructs a new PlayerScoreManager object.
	 * @param handler Manages all the important objects in the game.
	 */
	public PlayerScoreManager(Handler handler) {
		scoreList = new LinkedList<PlayerScore>();
		file = new File(fileName);
	}

	/**
	 * load
	 * Loads the scores in the text file.
	 * @throws ClassNotFoundException Thrown if the specified class cannot be found in the classpath.
	 */
	public void load() throws ClassNotFoundException {
		boolean fileExist = file.exists(); // Checks if the file already exists.
		if (fileExist) {
			try {
				readScoreBoardFromFile(fileName); // Load the scores from the file into the score list.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * save
	 * Saves the scores to the text file.
	 * @throws ClassNotFoundException Thrown if the specified class cannot be found in the classpath.
	 */
	public void save() throws ClassNotFoundException {
		try {
			writeScoreBoardToFile(fileName); // Load the scores from the score list into the file.
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * addScore
	 * Adds a score to the score list and sorts the list.
	 * @param playerScore The new player score to be added.
	 */
	public void addScore(PlayerScore playerScore) {
		scoreList.add(playerScore);
		if (scoreList.size() > 1) {
			quickSort(scoreList, 0, scoreList.size() - 1);
		}
	}

	/**
	 * removeScore
	 * Removes a score from the score list.
	 * @param playerScore The player score to be removed.
	 */
	public void removeScore(PlayerScore playerScore) {
		scoreList.remove(playerScore);
	}

	/**
	 * writeScoreBoardToFile
	 * Read the scores in the score list to a text file.
	 * @param file The text file that the scores are being written to.
	 * @throws IOException Thrown during a file reading, writing, or searching failure.
	 */
	public void writeScoreBoardToFile(String file) throws IOException {
		try {

			// Creating a new object output stream to write to text file.
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			int scoreCount = scoreList.size();
			objectOutputStream.writeInt(scoreCount);

			// Iterate through the list of player scores.
			for (PlayerScore playerScore : scoreList) {
				objectOutputStream.writeObject(playerScore);
			}
			fileOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * readScoreBoardFromFile
	 * Reads the scores from a text file and loads it into the player score manager.
	 * @param file The file that the scores are being read from.
	 * @throws IOException Thrown during a file reading, writing, or searching failure.
	 * @throws ClassNotFoundException Thrown if the specified class cannot be found in the classpath.
	 */
	public void readScoreBoardFromFile(String file) throws IOException, ClassNotFoundException {
		try {

			// Creates a new object input stream to read from a text file.
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			int scoreCount = objectInputStream.readInt();
			scoreList.removeAll(scoreList);

			// Iterate through the text file and read to high score list.
			for (int i = 0; i < scoreCount; i++) {
				PlayerScore playerScore = (PlayerScore) objectInputStream.readObject();
				if (playerScore != null) {
					scoreList.add(playerScore);
				}
			}
			fileInputStream.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * quickSort
	 * Uses a quick sort algorithm to sort the scores in the high score list from least to greatest.
	 * @param scores The list of high scores.
	 * @param start The starting index of the list.
	 * @param end The ending index of the list.
	 */
	public void quickSort(LinkedList<PlayerScore> scores, int start, int end) {
		if (start < end) {
			int partitionIndex = partition(scores, start, end);

			quickSort(scores, start, partitionIndex - 1);
			quickSort(scores, partitionIndex + 1, end);
		}
	}

	/**
	 * partition
	 * Reordering the list by quick sort given a list, a start and end index.
	 * @param scores The list of high scores.
	 * @param start The starting index of the list.
	 * @param end The ending index of the list.
	 * @return The index to partition at.
	 */
	private int partition(LinkedList<PlayerScore> scores, int start, int end) {
		PlayerScore swapTemp;
		PlayerScore pivotScore = scores.get(end);
		int i = (start - 1);

		for (int j = start; j < end; j++) {
			if (scores.get(j).getScore() >= pivotScore.getScore()) {
				i++;

				swapTemp = scores.get(i);
				scores.set(i, scores.get(j));
				scores.set(j, swapTemp);
			}
		}

		swapTemp = scores.get(i + 1);
		scores.set(i + 1, scores.get(end));
		scores.set(end, swapTemp);

		return i + 1;
	}

	/**
	 * getScoreList
	 * Gets the list of high scores.
	 * @return The list of high scores.
	 */
	public LinkedList<PlayerScore> getScoreList() {
		return scoreList;
	}

	/**
	 * getFile
	 * Gets the file of high scores.
	 * @return The file of high scores.
	 */
	public File getFile() {
		return file;
	}
}
