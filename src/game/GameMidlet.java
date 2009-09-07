package game;

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

    public void startApp()
	{
		startMainMenu();
    }

	public void startGame()
	{

		if (game == null)
		{
			game = new Game();
		}

		game.start();
		Display.getDisplay(this).setCurrent(game);
	}

	public void startMainMenu()
	{
		if (menu == null)
		{
			menu = new Menu(this);
			menu.addChoice(new RunGameChoice());
			menu.addChoice(new EndGameChoice());
		}
		
		menu.start();
		Display.getDisplay(this).setCurrent(menu);
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
}
