package game;

import game.menu.Credits;
import game.menu.CreditsChoice;
import game.menu.EndGameChoice;
import game.menu.Menu;
import game.menu.RunGameChoice;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class GameMidlet extends MIDlet
{
	private Game game;
	private Menu menu;
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

		menu = new Menu(this);
		menu.addChoice(new RunGameChoice());
		menu.addChoice(new CreditsChoice());
		menu.addChoice(new EndGameChoice());
		
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
