package game;

import game.sprites.CharacterSprite;
import main.GameScreen;
import main.GameMidlet;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * The actual game!
 */
public class Game extends GameScreen
{
	private CharacterSprite mainChar;
	private Background background;

    // variables for fps calculation
	private int entries;
	private long startTime;
	private int fps;

	private boolean jumping;

	private int hWidth, hHeight; // half of screen width and height, used to calculate scrolling.

	private final int walkSpeed;

	public Game(GameMidlet midlet)
	{
		super(midlet);

		try
		{
			this.mainChar = new CharacterSprite(getWidth(), getHeight());
			this.background = new Background();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		this.hWidth = getWidth() / 2;
		this.hHeight = getHeight() / 2;

		this.jumping = false;
		
		this.walkSpeed = 5;

		this.entries = 0;
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Paints the game
	 * @param g
	 */
	public void paint(Graphics g)
	{

		// clear screen
		g.setColor(0);
		g.fillRect(0, 0, getWidth(), getHeight());

		this.background.paint(g);
		this.mainChar.paint(g);

		// calculate fps
		this.calculateFps(g);
	}

    /**
     * Updates game input and physics
     */
	public void update()
	{
		int keys = this.getKeyStates();

		if ((keys & LEFT_PRESSED) != 0)
		{
			this.moveLeft();
		}
		else if ((keys & RIGHT_PRESSED) != 0)
		{
			this.moveRight();
		}
		else
		{
			this.mainChar.idle();
		}

		if ((keys & UP_PRESSED) != 0)
		{
			if (!this.jumping)
			{
				this.mainChar.jump();
				this.jumping = true;
			}
		}
		else
		{
			this.jumping = false;
		}

		this.mainChar.update();

	}

    /**
     * Calculates the FPS the game is running at
     * and draws them on the screen
     */
	private void calculateFps(Graphics g)
	{
		this.entries++;
		if (this.startTime + 1000 <= System.currentTimeMillis())
		{
			this.fps = this.entries;
			this.entries = 0;
			this.startTime = System.currentTimeMillis();
		}
		g.setColor(0xffffff);
		g.drawString("FPS: " + this.fps, 0, 0, 0);
	}

	private void moveLeft()
	{
		this.mainChar.walkLeft();

		scroll(-this.walkSpeed);
	}

	private void moveRight()
	{
		this.mainChar.walkRight();

		scroll(this.walkSpeed);
	}

	private void scroll(int speed)
	{
		if (this.mainChar.getX() - this.background.getX() < this.hWidth)
		{
			this.mainChar.move(speed, 0);
		}
		else if (this.mainChar.getX() - this.background.getX() > this.background.getWidth() - this.hWidth)
		{
			this.mainChar.move(speed, 0);
		}
		else
		{
			this.mainChar.setPosition(this.hWidth, this.mainChar.getY());
			this.background.move(-speed, 0);
		}
	}
}
