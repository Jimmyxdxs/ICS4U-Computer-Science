package core;

import java.awt.Graphics;

/**
 * HelpState.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the help state required to play the game.
 */

public class HelpState extends State {

	/**
	 * HelpState
	 * Constructs a new HelpState.
	 * @param handler Manages all the important objects in the game.
	 */
	public HelpState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		// When this back button is pressed, return to the home page state.
		uiManager.addObject(new UIImageButton(10, 820, 128, 64, handler.getMediaAssets().getBackButton(), new ClickListener() {

			/**
			 * onClick
			 * When this back button is pressed, create a new HomePageState.
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
	 * Updates this state's UI manager.
	 */
	@Override
	public void update() {
		uiManager.update();
	}

	/**
	 * display
	 * Displays this state's UI manager and instructions.
	 * @param g Allows the drawing of graphics to the screen.
	 */
	@Override
	public void display(Graphics g) {
		g.drawImage(handler.getMediaAssets().getCover(), 0, 0, 1400, 960, null);
		g.drawImage(handler.getMediaAssets().getHelp(), handler.getWidth() / 2 - 1000 / 2, handler.getHeight() / 2 - 760 / 2, 1000, 760, null);
		uiManager.display(g);
	}
}
