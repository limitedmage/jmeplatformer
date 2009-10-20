package main;

import highscores.HighScoreStore;
import game.Game;
import menu.Credits;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import menu.MainMenu;
import menu.PauseMenu;

/**
 * Main MIDlet class that manages which GameScreen is shown and executed
 */
public class MainMidlet extends MIDlet
{
	// game screen objects
	private Game game;
	private MainMenu mainMenu;
	private Credits credits;
	private PauseMenu pauseMenu;

	// scores store
	private HighScoreStore scoresStore;

	/**
	 * Initializes all objects to null
	 */
	public MainMidlet()
	{
		this.reset();

		// load scores
		this.scoresStore = new HighScoreStore();
	}

	/**
	 * If game does not exist, initialize game with main menu
	 * If game exists, show pause menu
	 */
    public void startApp()
	{
		if (game == null)
		{
			this.startMainMenu();
		}
		else
		{
			this.pauseGame();
		}
    }

	/**
	 * Creates new game and displays it
	 */
	public void startGame()
	{
		this.reset();

		this.game = new Game(this);
		this.game.start();
		Display.getDisplay(this).setCurrent(game);
	}

	/**
	 * Creates main menu and displays it
	 */
	public void startMainMenu()
	{
		this.reset();

		this.mainMenu = new MainMenu(this);
		this.mainMenu.start();
		Display.getDisplay(this).setCurrent(mainMenu);
	}

	/**
	 * Creates credits screen and displays it
	 */
	public void startCredits()
	{
		this.reset();

		this.credits = new Credits(this);
		this.credits.start();
		Display.getDisplay(this).setCurrent(credits);
	}

	/**
	 * Stops game excecution (does not destroy game) and
	 * shows pause menu.
	 *
	 * @throws GameDoesNotExistException if there is no game to pause.
	 */
	public void pauseGame()
	{
		if (game != null)
		{
			this.game.stop();

			this.pauseMenu = new PauseMenu(this);
			this.pauseMenu.start();
			Display.getDisplay(this).setCurrent(pauseMenu);
		}
		else
		{
			throw new GameDoesNotExistException();
		}
	}

	/**
	 * Resumes game from pause and destroys pause menu.
	 *
	 * @throws GameDoesNotExistException if there is no game to resume.
	 */
	public void resumeGame()
	{
		if (game != null)
		{
			// destroy pause menu
			if (pauseMenu == null)
			{
				this.pauseMenu = null;
			}

			// restart game
			this.game.start();
			Display.getDisplay(this).setCurrent(game);
		}
		else
		{
			throw new GameDoesNotExistException();
		}
	}

    public void pauseApp()
	{
    }

    public void destroyApp(boolean unconditional)
	{
		this.scoresStore.close();
    }

	/**
	 * Quits app
	 */
	public void terminate()
	{
		this.destroyApp(true);
		this.notifyDestroyed();
	}

	/**
	 * Resets GameCanvas objects to null
	 */
	private void reset()
	{
		this.game = null;
		this.mainMenu = null;
		this.credits = null;
		this.pauseMenu = null;
	}
}
