package main;

import highscores.HighScoreStore;
import game.Game;
import highscores.HighScoreScreen;
import menu.Credits;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import menu.MainMenu;
import menu.PauseMenu;

/**
 * Main MIDlet class that manages which Screen is shown and executed
 */
public class MainMidlet extends MIDlet
{
	// screen objects
	private Game game;
	private MainMenu mainMenu;
	private Credits credits;
	private PauseMenu pauseMenu;
	private HighScoreScreen highScores;
	private SplashScreen splash;

	// screen stored when game interrupted
	private Screen interruptedScreen;

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
	 * Initialized game
	 * If game exists, pause game and show pause menu.
	 * Else, if there is a screen stored when interrupted, show that screen.
	 * Else, show the main menu.
	 */
    public void startApp()
	{
		if (this.game != null)
		{
			this.pauseGame();
		}
		else if (this.interruptedScreen != null)
		{
			Display.getDisplay(this).setCurrent(this.interruptedScreen);
		}
		else
		{
			this.startSplash();
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
		Display.getDisplay(this).setCurrent(this.game);
	}

	/**
	 * Creates main menu and displays it
	 */
	public void startMainMenu()
	{
		this.reset();

		this.mainMenu = new MainMenu(this);
		this.mainMenu.start();
		Display.getDisplay(this).setCurrent(this.mainMenu);
	}

	/**
	 * Creates and displays the splash screen
	 */
	public void startSplash()
	{
		this.reset();

		this.splash = new SplashScreen(this);
		this.splash.start();
		Display.getDisplay(this).setCurrent(this.splash);
	}

	/**
	 * Creates credits screen and displays it
	 */
	public void startCredits()
	{
		this.reset();

		this.credits = new Credits(this);
		this.credits.start();
		Display.getDisplay(this).setCurrent(this.credits);
	}

	public void startHighScores()
	{
		this.reset();

		this.highScores = new HighScoreScreen(this);
		this.highScores.start();
		Display.getDisplay(this).setCurrent(this.highScores);
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
			Display.getDisplay(this).setCurrent(this.pauseMenu);
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
		this.interruptedScreen = (Screen)Display.getDisplay(this).getCurrent();
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

	/*
	 * Resets Screen objects to null
	 */
	private void reset()
	{
		if (this.game != null)
		{
			this.game.stop();
			this.game = null;
		}

		if (this.mainMenu != null)
		{
			this.mainMenu.stop();
			this.mainMenu = null;
		}
		
		if (this.credits != null)
		{
			this.credits.stop();
			this.credits = null;
		}

		if (this.pauseMenu != null)
		{
			this.pauseMenu.stop();
			this.pauseMenu = null;
		}

		if (this.highScores != null)
		{
			this.highScores.stop();
			this.highScores = null;
		}

		if (this.splash != null)
		{
			this.splash.stop();
			this.splash = null;
		}
		
		this.interruptedScreen = null;
	}

	public HighScoreStore getScores()
	{
		return this.scoresStore;
	}
}
