package core;

import java.awt.Graphics;

/**
 * HomePageState.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Contains the home page state required to play the game.
 */

public class HomePageState extends State {

 /**
  * HomePageState
  * Constructs a new HomePageState.
  * @param handler Manages all the important objects in the game.
  */
 public HomePageState(Handler handler) {
  super(handler);
  uiManager = new UIManager(handler);
  handler.getMouseManager().setUIManager(uiManager);
  handler.getMediaAssets().getIntroBackgroundMusic().loop();

  int x = handler.getWidth() / 2 - 250;
  int y = handler.getHeight() * 3 / 5;

  // Creates a new UI Image button to start the game.
  uiManager.addObject(new UIImageButton(x, y, 512, 64, handler.getMediaAssets().getStartGameButton(), new ClickListener() {

   /**
    * onClick
    * When this start game button is pressed, create a new game state.
    */
   @Override
   public void onClick() {
    handler.getMouseManager().setUIManager(null);
    handler.getStateManager().setCurrentState(new GameState(handler));
   }
  }));

  // Creates a new UI Image button to go to the settings state of the game.
  uiManager.addObject(new UIImageButton(x, y + 70, 512, 64, handler.getMediaAssets().getSettingsButton(), new ClickListener() {

   /**
    * onClick
    * When this settings button is pressed, create a new settings state.
    */
   @Override
   public void onClick() {
    handler.getMouseManager().setUIManager(null);
    handler.getStateManager().setCurrentState(new SettingsState(handler));
   }
  }));

  // Creates a new UI Image button to go to the help state.
  uiManager.addObject(new UIImageButton(x, y + 140, 512, 64, handler.getMediaAssets().getHelpButton(), new ClickListener() {

   /**
    * onClick
    * When this help button is pressed, create a new help state.
    */
   @Override
   public void onClick() {
    handler.getMouseManager().setUIManager(null);
    handler.getStateManager().setCurrentState(new HelpState(handler));
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
  * Draws this state to the screen.
  * @param g Allows the drawing of graphics to the screen.
  */
 @Override
 public void display(Graphics g) {
  g.drawImage(handler.getMediaAssets().getCover(), 0, 0, 1400, 960, null);
  uiManager.display(g);
 }
}
