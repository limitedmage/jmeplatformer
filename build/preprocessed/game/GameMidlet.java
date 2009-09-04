package game;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class GameMidlet extends MIDlet //comentariooooo
{
	private Game game;

    public void startApp()
	{
		if (game == null) // juego inicializado
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
