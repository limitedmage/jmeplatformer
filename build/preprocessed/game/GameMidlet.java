package game;

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
		menu = new Menu(this);
		menu.addChoice(new RunGameChoice());

		menu.start();
		Display.getDisplay(this).setCurrent(menu);
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

    public void pauseApp()
	{
    }

    public void destroyApp(boolean unconditional)
	{
    }
}
