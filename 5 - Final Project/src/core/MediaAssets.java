package core;

import java.awt.image.BufferedImage;

/**
 * MediaAssets.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Stores and initializes all the game assets including images and audio.
 */

public class MediaAssets {

	// Image loading width and height constants
	private final int TANK_WIDTH = 64;
	private final int TANK_HEIGHT = 64;
	private final int TURRET_WIDTH = 94;
	private final int TURRET_HEIGHT = 212;
	private final int TILE_WIDTH = 64;
	private final int TILE_HEIGHT = 64;
	private final int BUTTON_WIDTH = 512;
	private final int BUTTON_HEIGHT = 64;

	// BufferedImages for the tiles.
	private BufferedImage brickTile;
	private BufferedImage metalTile;
	private BufferedImage sandTile;
	private BufferedImage grassTile;
	private BufferedImage waterTile;
	private BufferedImage holeTile;

	// BufferedImages for the player and AI tanks, as well as smoke and bullet particles.
	private BufferedImage playerTank;
	private BufferedImage playerShadow;
	private BufferedImage playerTurret;
	private BufferedImage aiTank;
	private BufferedImage aiTurret;
	private BufferedImage smoke;
	private BufferedImage fire;

	// BufferedImages for the static UI images.
	private BufferedImage cover, help, uiLabel;

	// BufferedImage arrays for UI Buttons
	private BufferedImage[] easyButton;
	private BufferedImage[] exitButton;
	private BufferedImage[] hardButton;
	private BufferedImage[] helpButton;
	private BufferedImage[] largeButton;
	private BufferedImage[] regularButton;
	private BufferedImage[] restartButton;
	private BufferedImage[] settingsButton;
	private BufferedImage[] startGameButton;
	private BufferedImage[] backButton;

	// BufferedImage arrays for the in-game animations.
	private BufferedImage[] explosions;
	private BufferedImage[] diamond;
	private BufferedImage[] ring;

	// Sounds effects and background music for the game.
	private Sound shooting;
	private Sound explosion;
	private Sound clank;
	private Sound crumble;
	private Sound playerDamage;
	private Sound introBackgroundMusic;
	private Sound gameMusic;
	private Sound powerUpSound;

	/**
	 * MediaAssets
	 * Initializes all of the assets used in the game.
	 */
	public MediaAssets() {

		// Loading images from the textures folder.
		ItemSheet coverSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Cover.jpg"));
		ItemSheet helpSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Help.jpg"));
		ItemSheet tankSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Tank Sheet.png"));
		ItemSheet shadowSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Tank Sheet shadow.png"));
		ItemSheet turretSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Turret Sheet.png"));
		ItemSheet tileSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Tile Sheet.png"));
		ItemSheet uiSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/UI Sheet.png"));
		ItemSheet explosionSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Explosion Sheet.png"));
		ItemSheet smokeSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/51.jpg"));
		ItemSheet fireSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Fire.png"));
		ItemSheet powerUpSheet = new ItemSheet(ImageLoader.loadImage("/res/textures/Powerups.png"));

		// Cropping images and setting game sprites.
		cover = coverSheet.crop(300, 0, 1620, 1080);
		help = helpSheet.crop(0, 0, 469, 380);
		playerTank = tankSheet.crop(TANK_WIDTH, 0, TANK_WIDTH, TANK_HEIGHT);
		playerShadow = shadowSheet.crop(TANK_WIDTH * 1, 0, TANK_WIDTH, TANK_HEIGHT);
		playerTurret = turretSheet.crop(TURRET_WIDTH, 0, TURRET_WIDTH, TURRET_HEIGHT);
		aiTank = tankSheet.crop(TANK_WIDTH * 2, 0, TANK_WIDTH, TANK_HEIGHT);
		aiTurret = turretSheet.crop(TURRET_WIDTH * 2, 0, TURRET_WIDTH, TURRET_HEIGHT);
		metalTile = tileSheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
		sandTile = tileSheet.crop(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
		waterTile = tileSheet.crop(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
		grassTile = tileSheet.crop(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT);
		brickTile = tileSheet.crop(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT);
		holeTile = tileSheet.crop(TILE_WIDTH * 5, 0, TILE_WIDTH, TILE_HEIGHT);
		smoke = smokeSheet.crop(0, 0, 10, 10);
		fire = fireSheet.crop(0, 0, 18, 18);
		uiLabel = uiSheet.crop(BUTTON_WIDTH * 18, 0, BUTTON_WIDTH, 172);

		// Creating BufferedImage arrays for button animations.
		easyButton = new BufferedImage[2];
		exitButton = new BufferedImage[2];
		hardButton = new BufferedImage[2];
		helpButton = new BufferedImage[2];
		largeButton = new BufferedImage[2];
		regularButton = new BufferedImage[2];
		restartButton = new BufferedImage[2];
		settingsButton = new BufferedImage[2];
		startGameButton = new BufferedImage[2];
		backButton = new BufferedImage[2];

		// Creating BufferedImage arrays for in-game animations.
		explosions = new BufferedImage[25];
		diamond = new BufferedImage[16];
		ring = new BufferedImage[16];

		// Loading the explosion sprite sheet into a BufferedImage array.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				explosions[i * 5 + j] = explosionSheet.crop(j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
			}
		}

		// Loading the diamond sprite sheet into a BufferedImage array.
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				diamond[i * 8 + j] = powerUpSheet.crop(j * TILE_WIDTH / 2, i * TILE_HEIGHT / 2, TILE_WIDTH / 2, TILE_HEIGHT / 2);
			}
		}

		// Loading the ring sprite sheet into a BufferedImage array.
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				ring[i * 8 + j] = powerUpSheet.crop(j * TILE_WIDTH / 2, (i + 2) * TILE_HEIGHT / 2, TILE_WIDTH / 2, TILE_HEIGHT / 2);
			}
		}

		// Loading the BufferedImages for the UI buttons into BufferedImage arrays.
		easyButton[0] = uiSheet.crop(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		easyButton[1] = uiSheet.crop(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		exitButton[0] = uiSheet.crop(BUTTON_WIDTH * 2, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		exitButton[1] = uiSheet.crop(BUTTON_WIDTH * 3, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		hardButton[0] = uiSheet.crop(BUTTON_WIDTH * 4, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		hardButton[1] = uiSheet.crop(BUTTON_WIDTH * 5, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		helpButton[0] = uiSheet.crop(BUTTON_WIDTH * 6, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		helpButton[1] = uiSheet.crop(BUTTON_WIDTH * 7, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		largeButton[0] = uiSheet.crop(BUTTON_WIDTH * 8, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		largeButton[1] = uiSheet.crop(BUTTON_WIDTH * 9, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		regularButton[0] = uiSheet.crop(BUTTON_WIDTH * 10, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		regularButton[1] = uiSheet.crop(BUTTON_WIDTH * 11, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		restartButton[0] = uiSheet.crop(BUTTON_WIDTH * 12, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		restartButton[1] = uiSheet.crop(BUTTON_WIDTH * 13, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		settingsButton[0] = uiSheet.crop(BUTTON_WIDTH * 14, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		settingsButton[1] = uiSheet.crop(BUTTON_WIDTH * 15, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		startGameButton[0] = uiSheet.crop(BUTTON_WIDTH * 16, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		startGameButton[1] = uiSheet.crop(BUTTON_WIDTH * 17, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton[0] = uiSheet.crop(BUTTON_WIDTH * 19, 0, BUTTON_WIDTH / 4, BUTTON_HEIGHT);
		backButton[1] = uiSheet.crop(BUTTON_WIDTH * 19 + BUTTON_WIDTH / 4, 0, BUTTON_WIDTH / 4, BUTTON_HEIGHT);

		// Loading background music and sound effects.
		shooting = new Sound("res/audio/Shooting SFX.wav", 0);
		explosion = new Sound("res/audio/Explosion SFX.wav", 0);
		clank = new Sound("res/audio/Metal SFX.wav", 6);
		crumble = new Sound("res/audio/Crumble SFX.wav", 2);
		playerDamage = new Sound("res/audio/Player Damage SFX.wav", 0);
		powerUpSound = new Sound("res/audio/PowerUps SFX.wav", 2);
		introBackgroundMusic = new Sound("res/audio/Intro Background Music.wav", -20);
		gameMusic = new Sound("res/audio/Game Music.wav", 2);
	}

	/**
	 * getBrickTile
	 * Gets the texture for the brick tile.
	 * @return The texture for the brick tile.
	 */
	public BufferedImage getBrickTile() {
		return brickTile;
	}

	/**
	 * getMetalTile
	 * Gets the texture for the metal tile.
	 * @return The texture for the metal tile.
	 */
	public BufferedImage getMetalTile() {
		return metalTile;
	}

	/**
	 * getSandTile
	 * Gets the texture for the sand tile.
	 * @return The texture for the sand tile.
	 */
	public BufferedImage getSandTile() {
		return sandTile;
	}

	/**
	 * getGrassTile
	 * Gets the texture for the grass tile.
	 * @return The texture for the grass tile.
	 */
	public BufferedImage getGrassTile() {
		return grassTile;
	}

	/**
	 * getWaterTile
	 * Gets the texture for the water tile.
	 * @return The texture for the water tile.
	 */
	public BufferedImage getWaterTile() {
		return waterTile;
	}

	/**
	 * getHoleTile
	 * Gets the texture for the hole tile.
	 * @return The texture for the hole tile.
	 */
	public BufferedImage getHoleTile() {
		return holeTile;
	}

	/**
	 * getPlayerTank
	 * Gets the texture for the player tank's body.
	 * @return The texture for the player tank's body.
	 */
	public BufferedImage getPlayerTank() {
		return playerTank;
	}

	/**
	 * getPlayerShadow
	 * Gets the texture for the player tank's shadow.
	 * @return The texture for the player tank's shadow.
	 */
	public BufferedImage getPlayerShadow() {
		return playerShadow;
	}

	/**
	 * getPlayerTurret
	 * Gets the texture for the player tank's turret.
	 * @return The texture for the player tank's turret.
	 */
	public BufferedImage getPlayerTurret() {
		return playerTurret;
	}

	/**
	 * getAITank
	 * Gets the texture for the AI tank's body.
	 * @return The texture for the AI tank's body.
	 */
	public BufferedImage getAITank() {
		return aiTank;
	}

	/**
	 * getPlayerTank
	 * Gets the texture for the AI tank's turret.
	 * @return The texture for the player tank's turret.
	 */
	public BufferedImage getAITurret() {
		return aiTurret;
	}

	/**
	 * getSmoke
	 * Gets the texture for the player's smoke.
	 * @return The texture for the player's smoke.
	 */
	public BufferedImage getSmoke() {
		return smoke;
	}

	/**
	 * getFire
	 * Gets the texture for the bullet's trail.
	 * @return The texture for the bullet's trail.
	 */
	public BufferedImage getFire() {
		return fire;
	}

	/**
	 * getCover
	 * Gets the picture for the home page state.
	 * @return The picture for the home page state.
	 */
	public BufferedImage getCover() {
		return cover;
	}

	/**
	 * getHelp
	 * Gets the picture for the help state.
	 * @return The picture for the help state.
	 */
	public BufferedImage getHelp() {
		return help;
	}

	/**
	 * getExitButton
	 * Gets the texture for the exit button.
	 * @return The texture for the exit button.
	 */
	public BufferedImage[] getExitButton() {
		return exitButton;
	}

	/**
	 * getHelpButton
	 * Gets the texture for the help button.
	 * @return The texture for the help button.
	 */
	public BufferedImage[] getHelpButton() {
		return helpButton;
	}

	/**
	 * getRegularButton
	 * Gets the texture for the regular level button.
	 * @return The texture for the regular level button.
	 */
	public BufferedImage[] getRegularButton() {
		return regularButton;
	}

	/**
	 * getLargeButton
	 * Gets the texture for the large map button.
	 * @return The texture for the large map button.
	 */
	public BufferedImage[] getLargeButton() {
		return largeButton;
	}

	/**
	 * getEasyButton
	 * Gets the texture for the easy level button.
	 * @return The texture for the easy level button.
	 */
	public BufferedImage[] getEasyButton() {
		return easyButton;
	}

	/**
	 * getHardButton
	 * Gets the texture for the hard level button.
	 * @return The texture for the hard level button.
	 */
	public BufferedImage[] getHardButton() {
		return hardButton;
	}

	/**
	 * getRestartButton
	 * Gets the texture for the restart button.
	 * @return The texture for the restart button.
	 */
	public BufferedImage[] getRestartButton() {
		return restartButton;
	}

	/**
	 * getSettingsButton
	 * Gets the texture for the settings button.
	 * @return The texture for the settings button.
	 */
	public BufferedImage[] getSettingsButton() {
		return settingsButton;
	}

	/**
	 * getStartGameButton
	 * Gets the texture for the start game button.
	 * @return The texture for the start game button.
	 */
	public BufferedImage[] getStartGameButton() {
		return startGameButton;
	}

	/**
	 * getBackButton
	 * Gets the texture for the back button.
	 * @return The texture for the back button.
	 */
	public BufferedImage[] getBackButton() {
		return backButton;
	}

	/**
	 * getExplosions
	 * Gets the array of BufferedImages for the explosion animation.
	 * @return The array of BufferedImages for the explosion.
	 */
	public BufferedImage[] getExplosions() {
		return explosions;
	}

	/**
	 * getDiamond
	 * Gets the array of BufferedImages for the diamond powerup.
	 * @return The array of BufferedImages for the diamond powerup.
	 */
	public BufferedImage[] getDiamond() {
		return diamond;
	}

	/**
	 * getRing
	 * Gets the array of BufferedImages for the ring/health powerup.
	 * @return The array of BufferedImages for the ring/health powerup.
	 */
	public BufferedImage[] getRing() {
		return ring;
	}

	/**
	 * getUILabel
	 * Gets the image for the UI label.
	 * @return The image for the UI label.
	 */
	public BufferedImage getUILabel() {
		return uiLabel;
	}

	/**
	 * getShooting
	 * Gets the sound of the tanks shooting.
	 * @return The sound of the tanks shooting.
	 */
	public Sound getShooting() {
		return shooting;
	}

	/**
	 * getExplosion
	 * Gets the sound of the tanks exploding.
	 * @return The sound of the tanks exploding.
	 */
	public Sound getExplosion() {
		return explosion;
	}

	/**
	 * getClank
	 * Gets the sound of a bullet hitting a metal wall.
	 * @return The sound of a bullet hitting a metal wall.
	 */
	public Sound getClank() {
		return clank;
	}

	/**
	 * getCrumble
	 * Gets the sound of a brick wall crumbling.
	 * @return The sound of a brick wall crumbling.
	 */
	public Sound getCrumble() {
		return crumble;
	}

	/**
	 * getPlayerDamage
	 * Gets the sound of the player tank taking damage.
	 * @return The sound of the player tank taking damage.
	 */
	public Sound getPlayerDamage() {
		return playerDamage;
	}

	/**
	 * getIntroBackgroundMusic
	 * Gets the background music for the home page screen.
	 * @return The background music for the home page screen.
	 */
	public Sound getIntroBackgroundMusic() {
		return introBackgroundMusic;
	}

	/**
	 * getGameMusic
	 * Gets the background music for the game state.
	 * @return The background music for the game state.
	 */
	public Sound getGameMusic() {
		return gameMusic;
	}

	/**
	 * getPowerUpSound
	 * Gets the sound of a powerup when used.
	 * @return The sound of a powerup when used.
	 */
	public Sound getPowerUpSound() {
		return powerUpSound;
	}

	/**
	 * getTileWidth
	 * Gets the tile width.
	 * @return The tile Width
	 */
	public int getTileWidth() {
		return TILE_WIDTH;
	}

	/**
	 * getTileHeight
	 * Gets the tile height.
	 * @return The tile height
	 */
	public int getTileHeight() {
		return TILE_HEIGHT;
	}
}
