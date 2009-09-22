package game;

import java.io.IOException;
import javax.microedition.lcdui.game.LayerManager;

public class GameManager extends LayerManager
{
	private int sWidth, sHeight;

	/**
	 * Initailizes a new GameManager
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 */
	public GameManager(int sWidth, int sHeight)
	{
		this.sWidth = sWidth;
		this.sHeight = sHeight;

		try
		{
			append(new VampSprite(sWidth, sHeight));
			append(new Background());
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

}
