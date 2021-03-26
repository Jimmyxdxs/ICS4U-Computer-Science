package core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Sound.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Loads sound into the game from the audio folder.
 */

public class Sound {

	private String path; // File path of the sound effect or music track.
	private File audioFile; // Load the file in as an audio file.
	private AudioInputStream audioInputStream; // Create an audio input stream to play music.
	private Clip clip;

	// Change the volume of the music.
	private FloatControl gainControl;
	private int volume;

	/**
	 * Sound
	 * Constructs a new Sound object.
	 * @param path The file path of the audio file.
	 * @param volume The desired volume of the audio.
	 */
	public Sound(String path, int volume) {
		this.path = path;
		this.volume = volume;
	}

	/**
	 * play
	 * Plays the given audio clip once.
	 */
	public void play() {
		try {

			// Load the audio file.
			audioFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/" + path);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Change the volume of the audio file.
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

			clip.start(); // Start the audio file.

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loop
	 * Plays the selected audio clip continuously on loop.
	 */
	public void loop() {
		play();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * stop
	 * Stops the selected audio file.
	 */
	public void stop() {
		clip.stop();
	}
}