package main;

import highscores.HighScoreStore;
import game.Game;
import highscores.HighScoreScreen;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import menu.Credits;
import menu.Help;
import menu.MainMenu;
import menu.PauseMenu;
import music.MusicManager;

/**
 * Main MIDlet class that manages which Screen is shown and executed
 */
public class MainMidlet extends MIDlet {

	// screen objects
	private Screen game,
			mainMenu,
			pauseMenu,
			highScores,
			splash;

	// screen stored when game interrupted
	private Displayable interruptedScreen;

	private MusicManager music;

	// scores store
	private HighScoreStore scoresStore;

	/**
	 * Initializes all screens to null
	 * and opens the HighScoreStore
	 */
	public MainMidlet() {
		this.nullifyAll();

		// load scores
		this.scoresStore = new HighScoreStore();

		this.music = new MusicManager();
	}

	/**
	 * Initialized game
	 * If game exists, pause game and show pause menu.
	 * Else, if there is a screen stored when interrupted, show that screen.
	 * Else, show the main menu.
	 */
	public void startApp() {
		if (this.game != null) {
			this.pauseGame();
		}
		else if (this.interruptedScreen != null) {
			Display.getDisplay(this).setCurrent(this.interruptedScreen);
		}
		else {
			this.startSplash();
		}


	}

	/**
	 * Creates new game and displays it
	 */
	public void startGame() {
		this.music.playGameMusic();

		this.stopAll();
		this.nullifyAll();

		this.game = new Game(this);
		this.game.start();
		Display.getDisplay(this).setCurrent(this.game);

		this.music.playSelectedTone();
	}

	/**
	 * Creates main menu and displays it
	 */
	public void startMainMenu() {
		this.music.playMenuMusic();

		this.stopAll();

		if (this.mainMenu == null) {
			this.mainMenu = new MainMenu(this);
		}
		this.mainMenu.start();
		Display.getDisplay(this).setCurrent(this.mainMenu);
	}

	/**
	 * Creates and displays the splash screen
	 */
	public void startSplash() {
		this.stopAll();

		if (this.splash == null) {
			this.splash = new SplashScreen(this);
		}
		this.splash.start();
		Display.getDisplay(this).setCurrent(this.splash);
	}

	/**
	 * Creates credits screen and displays it
	 */
	public void startCredits() {
		Display.getDisplay(this).setCurrent(new Credits(this));
	}

	/**
	 * Creates the help screen and displays it
	 */
	public void startHelp() {
		this.stopAll();

		Display.getDisplay(this).setCurrent(new Help(this));
	}

	/**
	 * Creates high scores screen and displays it
	 */
	public void startHighScores() {
		this.stopAll();

		if (this.highScores == null) {
			this.highScores = new HighScoreScreen(this);
		}
		this.highScores.start();
		Display.getDisplay(this).setCurrent(this.highScores);
	}

	/**
	 * Stops game excecution (does not destroy game) and
	 * shows pause menu.
	 *
	 * @throws NullPointerException if there is no game to pause.
	 */
	public void pauseGame() {
		if (game != null) {
			// stop the game
			this.game.stop();

			// start the pause menu
			this.pauseMenu = new PauseMenu(this);
			this.pauseMenu.start();
			Display.getDisplay(this).setCurrent(this.pauseMenu);
		}
		else {
			throw new NullPointerException("Cannor pause a null game");
		}
	}

	/**
	 * Resumes game from pause and destroys pause menu.
	 *
	 * @throws NullPointerException if there is no game to resume.
	 */
	public void resumeGame() {
		if (game != null) {
			// stop the pause menu
			if (this.pauseMenu != null) {
				this.pauseMenu.stop();
			}

			// restart game
			this.game.start();
			Display.getDisplay(this).setCurrent(game);
		}
		else {
			throw new NullPointerException("Cannor resume a null game");
		}
	}

	/**
	 * Called when game interrupted by ie. calls
	 * Stores the interrupted screen for restoring later
	 */
	public void pauseApp() {
		this.interruptedScreen = Display.getDisplay(this).getCurrent();
	}

	/**
	 * Called when game exits
	 * Closes the HighScoreStore
	 * @param unconditional
	 */
	public void destroyApp(boolean unconditional) {
		this.scoresStore.close();
	}

	/**
	 * Quits app
	 */
	public void terminate() {
		this.destroyApp(true);
		this.notifyDestroyed();
	}

	/**
	 * Stops all running threads
	 */
	private void stopAll() {
		if (this.game != null) {
			this.game.stop();
		}

		if (this.mainMenu != null) {
			this.mainMenu.stop();
		}

		if (this.pauseMenu != null) {
			this.pauseMenu.stop();
		}

		if (this.highScores != null) {
			this.highScores.stop();
		}

		if (this.splash != null) {
			this.splash.stop();
		}
	}

	/**
	 * Resets all game objects to null
	 */
	private void nullifyAll() {
		this.game = null;
		this.mainMenu = null;
		this.pauseMenu = null;
		this.highScores = null;
		this.splash = null;
		this.interruptedScreen = null;
	}

	/**
	 * Returns the game's HighScoreStore object for managing scores.
	 * @return the game's HighScoreStore
	 */
	public HighScoreStore getScores() {
		return this.scoresStore;
	}

	/**
	 * Return the game's music object
	 * @return the game's music objects
	 */
	public MusicManager getMusic() {
		return this.music;
	}
}
