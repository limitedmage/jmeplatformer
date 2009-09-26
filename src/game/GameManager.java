package game;

import game.sprites.CharacterSprite;
import java.io.IOException;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import main.Updateable;

public class GameManager extends LayerManager implements Updateable
{
	private int sWidth, sHeight; // screen width and height
	private int hWidth, hHeight; // half of screen width and height

	private GameCanvas canvas; // parent gamecanvas

	private CharacterSprite mainChar;
	private Background background;

	// flags for input detection
	private boolean jumping;


	/**
	 * Initailizes a new GameManager
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 */
	public GameManager(GameCanvas canvas, int sWidth, int sHeight)
	{
		this.canvas = canvas;

		this.sWidth = sWidth;
		this.sHeight = sHeight;
		this.hWidth = this.sWidth / 2;
		this.hHeight = this.sHeight / 2;

		try
		{
			mainChar = new CharacterSprite(sWidth, sHeight);
			background = new Background();

			append(mainChar);
			append(background);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		this.setViewWindow(0, 0, sWidth, sHeight);


		this.jumping = false;
	}

	public void update()
	{

		int keys = canvas.getKeyStates();

		if ((keys & GameCanvas.LEFT_PRESSED) != 0)
		{
			this.moveLeft();
		}
		else if ((keys & GameCanvas.RIGHT_PRESSED) != 0)
		{
			this.moveRight();
		}
		else
		{
			mainChar.idle();
		}

		if ((keys & GameCanvas.UP_PRESSED) != 0)
		{
			if (!jumping)
			{
				mainChar.jump();
				jumping = true;
			}
		}
		else
		{
			jumping = false;
		}

		mainChar.update();
		
	}

	private void moveLeft()
	{
		mainChar.walkLeft();

		if (background.getX() + mainChar.getX() < hWidth)
		{
			this.setViewWindow(0, background.getY(), sWidth, sHeight);
		}
		else if (background.getX() + mainChar.getX() > background.getWidth() - hWidth)
		{
			this.setViewWindow(background.getWidth() - sWidth, background.getY(), sWidth, sHeight);
		}
		else
		{
			mainChar.setPosition(hWidth, mainChar.getY());
			background.move(5, 0);
		}

	}

	private void moveRight()
	{
		mainChar.walkRight();

		if (mainChar.getX() < hWidth)
		{
			this.setViewWindow(0, background.getY(), sWidth, sHeight);
		}
		else if (mainChar.getX() > background.getWidth() - hWidth)
		{
			this.setViewWindow(background.getWidth() - sWidth, background.getY(), sWidth, sHeight);
		}
		else
		{
			mainChar.setPosition(hWidth, mainChar.getY());
			background.move(-5, 0);
		}
	}



}
