package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

/**
 * GameOverState.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 18, 2021
 * Represents the state in the game when play is over.
 */

public class GameOverState extends State {

	/**
	 * GameOverState
	 * Constructs a new GameOverState object.
	 * @param handler Manages all the important objects in the game.
	 * @throws IOException Thrown during a file reading, writing, or searching failure.
	 * @throws ClassNotFoundException Thrown if the specified class cannot be found in the classpath.
	 */
	public GameOverState(Handler handler) throws IOException, ClassNotFoundException {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		handler.getLevel().getPlayerScoreManager().load(); // Loads scores from a text file to a score list.

		// Adds and sorts score in the score list
		handler.getLevel().getPlayerScoreManager().addScore(new PlayerScore(handler.getTankGame().getPlayerName(), handler.getGameObjectManager().getPlayerScore()));
		handler.getLevel().getPlayerScoreManager().save(); // Saves the scores from the score list to a text file.

		// Creates new UI labels to display the scoreboard, including player name, score, and rank.
		uiManager.addObject(new UILabel(380, 300, 400, 50, handler.getMediaAssets().getUILabel(), "Player Name"));
		uiManager.addObject(new UILabel(780, 300, 200, 50, handler.getMediaAssets().getUILabel(), "Score"));
		uiManager.addObject(new UILabel(980, 300, 100, 50, handler.getMediaAssets().getUILabel(), "Rank"));

		// Displays the top 1-5 scores.
		for (int i = 0; i < Math.min(handler.getLevel().getPlayerScoreManager().getScoreList().size(), 5); i++) {
			PlayerScore playerScore = handler.getLevel().getPlayerScoreManager().getScoreList().get(i);
			uiManager.addObject(new UILabel(380, 350 + i * 50, 400, 50, handler.getMediaAssets().getUILabel(), playerScore.getUsername()));
			uiManager.addObject(new UILabel(780, 350 + i * 50, 200, 50, handler.getMediaAssets().getUILabel(), Integer.toString(playerScore.getScore())));
			uiManager.addObject(new UILabel(980, 350 + i * 50, 100, 50, handler.getMediaAssets().getUILabel(), Integer.toString(i + 1)));
		}

		// Creates a new UI Image Button to restart the game.
		uiManager.addObject(new UIImageButton(455, 650, 512, 64, handler.getMediaAssets().getRestartButton(), new ClickListener() {

			/**
			 * onClick
			 * When this restart button is pressed, create a new GameState.
			 */
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setCurrentState(new GameState(handler));
			}
		}));

		// Creates a new UI Image Button to exit the game.
		uiManager.addObject(new UIImageButton(455, 650 + 70, 512, 64, handler.getMediaAssets().getExitButton(), new ClickListener() {

			/**
			 * onClick
			 * When this exit button is pressed, create a new HomePageState.
			 */
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setCurrentState(new HomePageState(handler));
			}
		}));
	}

	/**
	 * update
	 * Updates this state's UI Manager.
	 */
	@Override
	public void update() {
		uiManager.update();
	}

	/**
	 * display
	 * Displays this state's UI Manager and "Game Over" messages.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		handler.getLevel().display(g);

		// Displays the "Game Over" message.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Bauhaus 93", Font.PLAIN, 120));
		g.drawString("Game Over", handler.getTankGame().getWidth() / 2 - 300 + 5, handler.getTankGame().getHeight() / 2 - 200 + 5);
		g.setColor(Color.RED);
		g.drawString("Game Over", handler.getTankGame().getWidth() / 2 - 300, handler.getTankGame().getHeight() / 2 - 200);

		uiManager.display(g);
	}
}