package game;

import game.sprites.Bullet;
import game.sprites.CharacterSprite;
import game.sprites.GameSpriteGroup;
import game.sprites.HittingEnemySprite;
import game.sprites.ShootingEnemySprite;
import main.Screen;
import main.MainMidlet;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * The actual game!
 */
public class Game extends Screen
{
	// elements of the game
	private CharacterSprite mainChar;
	private Foreground foreground;
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

	// bullets group
	private GameSpriteGroup bullets;

	/**
	 * Starts a new game
	 * @param midlet - Parent MIDlet
	 */
	public Game(MainMidlet midlet)
	{
		super(midlet);

		int sWidth = this.getWidth(), sHeight = this.getHeight();

		try
		{
			this.foreground = new Foreground(this.getWidth(), this.getHeight());
			this.background = new Background();

			this.mainChar = new CharacterSprite(this.getWidth(), this.getHeight(), this, this.foreground);

			// initialize enemies
			this.enemies = new GameSpriteGroup();
			//this.enemies.add(new HittingEnemySprite(sWidth, sHeight));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 20, 30));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 60, 30));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 80, 80));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 200, 60));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 300, 60));
			this.enemies.add(new HittingEnemySprite(sWidth, sHeight, 600, 80));

			this.bullets = new GameSpriteGroup();

			this.enemies.add(new ShootingEnemySprite(sWidth, sHeight, bullets));
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

		// draw all game objects
		this.background.paint(g);
		this.foreground.paint(g);
		this.mainChar.paint(g);
		this.enemies.paint(g);
		this.bullets.paint(g);

		// calculate fps
		this.calculateFps(g);
	}

    /**
     * Updates game input and physics
     */
	public void update()
	{
		int keys = this.getKeyStates();

		// pausing?
		if ((keys & GAME_A_PRESSED) != 0)
		{
			this.midlet.pauseGame();
		}

		
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
		this.bullets.update();

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

		horizontalScroll(-this.walkSpeed);
	}

	/**
	 * Moves game screen to the right
	 */
	private void moveRight()
	{
		this.mainChar.walkRight();

		horizontalScroll(this.walkSpeed);
	}

	/**
	 * Scrolls the game screen relative to the character
	 * @param dx - horizontal speed
	 * @param dy - vertical speed
	 */
	public void scroll(int dx, int dy)
	{
		horizontalScroll(dx);
		verticalScroll(dy);
	}
	
	public void horizontalScroll(int dx)
	{
		/** Horizontal Scroll **/

		// get sprite horizontal position relative to the background
		int spritePosX = this.mainChar.getX() - this.foreground.getX();

		// if at first half of screen
		if (spritePosX < this.hWidth)
		{
			// if going right
			if (mainChar.getX() >= 0 || dx > 0)
			{
				this.mainChar.move(dx, 0);
			}
		}
		// if at last half of screen
		else if (spritePosX > this.foreground.getWidth() - this.hWidth)
		{
			// if going left
			if (spritePosX + this.mainChar.getWidth() <= this.foreground.getWidth() || dx < 0)
			{
				this.mainChar.move(dx, 0);
			}
		}
		else
		{
			this.mainChar.setPosition(this.hWidth, this.mainChar.getY());
			this.foreground.move(-dx, 0);
			this.background.move(-dx, 0);
			this.enemies.move(-dx, 0);
		}
	}

	public void verticalScroll(int dy)
	{
		/** Vertical Scroll **/
		// get sprite horizontal position relative to the background
		int spritePosY = this.mainChar.getY() - this.foreground.getY();
		if (spritePosY < this.hHeight)
		{
			if (spritePosY >= 0 || dy > 0)
			{
				this.mainChar.move(0, dy);
			}
		}
		else if (spritePosY > this.foreground.getHeight() - this.hHeight)
		{
			if (spritePosY + this.mainChar.getHeight() <= this.foreground.getHeight() || dy < 0)
			{
				this.mainChar.move(0, dy);
			}
		}
		else
		{
			this.mainChar.setPosition(this.mainChar.getX(), this.hHeight);
			this.foreground.move(0, -dy);
			this.background.move(0, -dy);
			this.enemies.move(0, -dy);
		}
	}
}
