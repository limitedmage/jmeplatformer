package main;

import game.*;
import menu.Credits;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import menu.MainMenu;

/**
 * Main MIDlet class that manages which GameScreen is shown and executed
 */
public class GameMidlet extends MIDlet
{
	private Game game;
	private MainMenu menu;
	private Credits credits;

	/**
	 * Creates all the GameScreen objects
	 */
	public GameMidlet()
	{
		this.game = new Game(this);
		this.menu = new MainMenu(this);
		this.credits = new Credits(this);
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
		reset();
		
		this.game.start();
		Display.getDisplay(this).setCurrent(game);
	}

	/**
	 * Creates main menu and displays it
	 */
	public void startMainMenu()
	{
		reset();
		
		this.menu.start();
		Display.getDisplay(this).setCurrent(menu);
	}

	/**
	 * Creates credits screen and displays it
	 */
	public void startCredits()
	{
		reset();

		this.credits.start();
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
		this.game.stop();
		this.credits.stop();
		this.menu.stop();
	}
}
