package game;

import game.sprites.CharacterSprite;
import game.sprites.GameSpriteGroup;
import game.sprites.HittingEnemySprite;
import main.GameScreen;
import main.GameMidlet;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * The actual game!
 */
public class Game extends GameScreen
{
	// elements of the game
	private CharacterSprite mainChar;
	private Background background;

    // variables for fps calculation
	private int entries;
	private long startTime;
	private int fps;

	// flag for jumping input handling
	private boolean jumping;

	// half of screen width and height, used to calculate scrolling.
	private int hWidth, hHeight; 

	// speed to move sprite
	private final int walkSpeed;

	// enemies group
	private GameSpriteGroup enemies;

	/**
	 * Starts a new game
	 * @param midlet - Parent MIDlet
	 */
	public Game(GameMidlet midlet)
	{
		super(midlet);

		int sWidth = this.getWidth(), sHeight = this.getHeight();

		try
		{
			this.mainChar = new CharacterSprite(this.getWidth(), this.getHeight());
			this.background = new Background();

			// initialize enemies
			this.enemies = new GameSpriteGroup();
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 20, 30));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 60, 30));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 80, 80));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 200, 60));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 300, 60));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 600, 80));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		this.hWidth = this.getWidth() / 2;
		this.hHeight = this.getHeight() / 2;

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
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		this.background.paint(g);
		this.mainChar.paint(g);
		this.enemies.paint(g);

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

		this.enemies.update();

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

	/**
	 * Moves game screen to the left
	 */
	private void moveLeft()
	{
		this.mainChar.walkLeft();

		scroll(-this.walkSpeed, 0);
	}

	/**
	 * Moves game screen to the right
	 */
	private void moveRight()
	{
		this.mainChar.walkRight();

		scroll(this.walkSpeed, 0);
	}

	/**
	 * Scrolls the game screen relative to the character
	 * @param dx - horizontal speed
	 * @param dy - vertical speed
	 */
	private void scroll(int dx, int dy)
	{
		// get sprite horizontal position relative to the background
		int spritePos = this.mainChar.getX() - this.background.getX();

		// Scroll horizontally
		if (spritePos < this.hWidth)
		{
			if (mainChar.getX() >= 0 || dx > 0)
			{
				this.mainChar.move(dx, 0);
			}
		}
		else if (spritePos > this.background.getWidth() - this.hWidth)
		{
			if (spritePos + this.mainChar.getWidth() <= this.background.getWidth() || dx < 0)
			{
				this.mainChar.move(dx, 0);
			}
		}
		else
		{
			this.mainChar.setPosition(this.hWidth, this.mainChar.getY());
			this.background.move(-dx, 0);
			this.enemies.move(-dx, 0);
		}

		// TODO: Scroll vertically
	}
}
