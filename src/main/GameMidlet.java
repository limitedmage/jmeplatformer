package main;

import game.*;
import javax.microedition.io.PushRegistry;
import menu.Credits;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import menu.MainMenu;
import menu.PauseMenu;

/**
 * Main MIDlet class that manages which GameScreen is shown and executed
 */
public class GameMidlet extends MIDlet
{
	private Game game;
	private MainMenu mainMenu;
	private Credits credits;
	private PauseMenu pauseMenu;

	/**
	 * Creates all the GameScreen objects
	 */
	public GameMidlet()
	{
		/*this.game = new Game(this);
		this.mainMenu = new MainMenu(this);
		this.credits = new Credits(this);
		this.pauseMenu = new PauseMenu(this);
		 */
	}

	/**
	 * Initialize game with main menu
	 */
    public void startApp()
	{
		startMainMenu();
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

	public void pauseGame()
	{
		if (game == null)
		{
			throw new NullPointerException();
		}

		this.game.stop();

		this.pauseMenu = new PauseMenu(this);
		this.pauseMenu.start();
		Display.getDisplay(this).setCurrent(pauseMenu);
	}

	public void resumeGame()
	{
		if (pauseMenu == null)
		{
			this.pauseMenu.stop();
			this.pauseMenu = null;
		}

		if (game == null)
		{
			throw new NullPointerException();
		}

		this.game.start();
		Display.getDisplay(this).setCurrent(game);
	}

    public void pauseApp()
	{
    }

    public void destroyApp(boolean unconditional)
	{
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
		//this.game.stop();
		this.game = null;

		//this.mainMenu.stop();
		this.mainMenu = null;

		//this.credits.stop();
		this.credits = null;

		//this.pauseMenu.stop();
		this.pauseMenu = null;
	}
}
