package main;

import game.*;
import menu.Credits;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import menu.MainMenu;

/**
 * Main MIDlet class that manages which GameCanvas is shown.
 */
public class GameMidlet extends MIDlet
{
	private Game game;
	private MainMenu menu;
	private Credits credits;

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
		reset();
		game = new Game(this);

		game.start();
		Display.getDisplay(this).setCurrent(game);
	}

	/**
	 * Creates main menu and displays it
	 */
	public void startMainMenu()
	{
		reset();

		menu = new MainMenu(this);
		
		menu.start();
		Display.getDisplay(this).setCurrent(menu);
	}

	/**
	 * Creates credits screen and displays it
	 */
	public void startCredits()
	{
		reset();

		credits = new Credits(this);

		credits.start();
		Display.getDisplay(this).setCurrent(credits);
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
		game = null;
		credits = null;
		menu = null;
	}
}
