package game;

import game.menu.Credits;
import game.menu.CreditsChoice;
import game.menu.EndGameChoice;
import game.menu.Menu;
import game.menu.RunGameChoice;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class GameMidlet extends MIDlet
{
	private Game game;
	private Menu menu;
	private Credits credits;

    public void startApp()
	{
		startMainMenu();
    }

	public void startGame()
	{
		reset();
		game = new Game(this);

		game.start();
		Display.getDisplay(this).setCurrent(game);
	}

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
