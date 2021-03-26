package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utils.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Stores some miscellaneous methods used in the game.
 *
 */

public class Utils {

	/**
	 * loadFile
	 * Loads a file given the file path.
	 * @param path The file path.
	 * @return A string with the contents of the text file written to it.
	 */
	public static String loadFile(String path) {
		StringBuilder builder = new StringBuilder();

		try {

			// Efficiently and quickly reads from a text file.
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;

			// Reads through all the lines in the text file, and appends it to the StringBuilder.
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
