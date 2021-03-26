package core;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * SettingsState.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the settings state required to play the game.
 */

public class SettingsState extends State implements ActionListener {

	private JTextField input;
	private String playerName;
	private JFrame frame;
	private boolean cursor;
	private String playerNameCursor;
	private long cursorTime;

	/**
	 * SettingsState
	 * Constructs a new SettingsState.
	 * @param handler Manages all the important objects in the game.
	 */
	public SettingsState(Handler handler) {
		super(handler);
		playerName = handler.getTankGame().getPlayerName();
		cursor = true;
		playerNameCursor = playerName;
		cursorTime = System.currentTimeMillis();
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		// Create labels to allow user to change their username in the settings state.
		uiManager.addObject(new UILabel(handler.getWidth() / 4, handler.getHeight() / 4, 400, 50, handler.getMediaAssets().getUILabel(), "Player Name"));
		uiManager.addObject(new UILabel(handler.getWidth() / 4, handler.getHeight() / 4 + 70, 400, 50, handler.getMediaAssets().getUILabel(), " "));
		uiManager.addObject(new UILabel(handler.getWidth() / 4, handler.getHeight() / 4 + 150, 200, 50, handler.getMediaAssets().getUILabel(), "Map"));
		uiManager.addObject(new UILabel(handler.getWidth() / 4, handler.getHeight() / 4 + 300, 200, 50, handler.getMediaAssets().getUILabel(), "Level"));

		// Creates a new UI Image Button to set the map to regular size.
		uiManager.addObject(new UIImageButton(handler.getWidth() / 4, handler.getHeight() / 4 + 220, 350, 64, handler.getMediaAssets().getRegularButton(), new ClickListener() {

			/**
			 * onClick
			 * When this regular button is clicked, set the map to the regular size.
			 */
			@Override
			public void onClick() {
				handler.getTankGame().setMapSize(1);
			}
		}));

		// Creates a new UI Image Button to set the map to a larger size.
		uiManager.addObject(new UIImageButton(handler.getWidth() / 4 + 360, handler.getHeight() / 4 + 220, 350, 64, handler.getMediaAssets().getLargeButton(), new ClickListener() {

			/**
			 * onClick
			 * When this large button is pressed, set the map to the large size.
			 */
			@Override
			public void onClick() {
				handler.getTankGame().setMapSize(2);
			}
		}));

		// Creates a new UI Image Button to set the game difficulty to easy.
		uiManager.addObject(new UIImageButton(handler.getWidth() / 4, handler.getHeight() / 4 + 370, 350, 64, handler.getMediaAssets().getEasyButton(), new ClickListener() {

			/**
			 * onClick
			 * When this easy button is pressed, set the game difficulty to easy.
			 */
			@Override
			public void onClick() {
				handler.getTankGame().setDifficulty(1);
			}
		}));

		// Creates a new UI Image Button to set the game difficulty to hard.
		uiManager.addObject(new UIImageButton(handler.getWidth() / 4 + 360, handler.getHeight() / 4 + 370, 350, 64, handler.getMediaAssets().getHardButton(), new ClickListener() {

			/**
			 * onClick
			 * When this hard button is pressed, set the game difficulty to hard.
			 */
			@Override
			public void onClick() {
				handler.getTankGame().setDifficulty(2);
			}
		}));

		// Creates a new UI Image Button to go back to the home page state.
		uiManager.addObject(new UIImageButton(10, 820, 128, 64, handler.getMediaAssets().getBackButton(), new ClickListener() {

			/**
			 * onClick
			 * When this back button is pressed, create a new home page state.
			 */
			@Override
			public void onClick() {
				handler.getTankGame().setPlayerName(playerName);
				frame.dispose();
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setCurrentState(new HomePageState(handler));
			}
		}));

		frame = new JFrame("");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		input = new JTextField(playerName, 40);
		input.setFont(new Font("Arial", Font.PLAIN, 40));
		input.setBounds(0, 0, 2, 2);
		input.setEditable(true);
		input.requestFocus();
		frame.add(input);
		frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);
	}

	/**
	 * update
	 * Updates the settings state.
	 */
	@Override
	public void update() {
		uiManager.update();
		playerName = input.getText();
		input.requestFocus();
	}

	/**
	 * display
	 * Draws the settings state to the screen
	 * @param g Alows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {

		// Draw the background image.
		g.drawImage(handler.getMediaAssets().getCover(), 0, 0, 1400, 960, null);
		uiManager.display(g);

		// Creates a flashing cursor after the user's name.
		if (System.currentTimeMillis() - cursorTime > 400) {
			cursorTime = System.currentTimeMillis();
			if (cursor) {
				playerNameCursor = playerName + "_";
			} else {
				playerNameCursor = playerName;
			}
			cursor = !cursor;
		}

		// Draws the player's entered username onto the label on the screen.
		g.setColor(Color.BLACK);
		g.drawString(playerNameCursor, handler.getWidth() / 4 + 20 + 2, handler.getHeight() / 4 + 100 + 2);
		g.setColor(Color.WHITE);
		g.drawString(playerNameCursor, handler.getWidth() / 4 + 20, handler.getHeight() / 4 + 100);
		g.draw3DRect(handler.getWidth() / 4, handler.getHeight() / 4 + 70, 400, 50, true);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);

		// Sets the map size.
		if (handler.getTankGame().getMapSize() == 1) {

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 4 * 0.1f));
			g2d.fillRect(handler.getWidth() / 4 + 360, handler.getHeight() / 4 + 220, 350, 64);
		} else {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 4 * 0.1f));
			g2d.fillRect(handler.getWidth() / 4, handler.getHeight() / 4 + 220, 350, 64);

		}

		// Sets the game difficulty.
		if (handler.getTankGame().getDifficulty() == 1) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 4 * 0.1f));
			g2d.fillRect(handler.getWidth() / 4 + 360, handler.getHeight() / 4 + 370, 350, 64);

		} else {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 4 * 0.1f));
			g2d.fillRect(handler.getWidth() / 4, handler.getHeight() / 4 + 370, 350, 64);

		}
	}

	/**
	 * actionPerformed
	 * Detects if there was an action performed on screen or not.
	 * @param e The action event that detects on-screen actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
