package game;

import main.GameScreen;
import java.io.IOException;
import main.GameMidlet;
import javax.microedition.lcdui.Graphics;

/**
 * The actual game!
 */
public class Game extends GameScreen
{
	private VampSprite sprite;
	private Background background;

    // variables for fps calculation
	private int entries;
	private long startTime;
	private int fps;


	private boolean jumping;

	public Game(GameMidlet midlet)
	{
		super(midlet);

		int width = getWidth();
		int height = getHeight();

		try
		{
			sprite = new VampSprite(width, height);
			background = new Background();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		jumping = false;

		entries = 0;
		startTime = System.currentTimeMillis();
	}

	/**
	 * Paints the game
	 * @param g
	 */
	public void paint(Graphics g)
	{

		// clear screen
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		// paint background
		background.paint(g);

		// paint sprite
		sprite.paint(g);

		// calculate fps
		calculateFps(g);
	}

    /**
     * Updates game input and physics
     */
	protected void update()
	{
		int keys = this.getKeyStates();

		if ((keys & LEFT_PRESSED) != 0)
		{
			sprite.walkLeft();
		}
		else if ((keys & RIGHT_PRESSED) != 0)
		{
			sprite.walkRight();
		}
		else
		{
			sprite.idle();
		}

		if ((keys & UP_PRESSED) != 0)
		{
			if (!jumping)
			{
				sprite.jump();
				jumping = true;
			}
		}
		else
		{
			jumping = false;
		}
        
		sprite.update();
	}

    /**
     * Calculates the FPS the game is running at
     * and draws them on the screen
     * @param g
     */
	private void calculateFps(Graphics g)
	{
		entries++;
		if (startTime + 1000 <= System.currentTimeMillis())
		{
			fps = entries;
			entries = 0;
			startTime = System.currentTimeMillis();
		}
		g.drawString("FPS: " + fps, 0, 0, 0);
	}
}
