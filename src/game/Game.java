package game;

import game.sprites.Bullet;
import game.sprites.CharacterBullet;
import game.sprites.EndMarker;
import game.sprites.CharacterSprite;
import game.sprites.EnemyBullet;
import game.sprites.GameSpriteGroup;
import game.sprites.EnemySprite;
import game.sprites.HittingEnemySprite;
import game.sprites.ShootingEnemySprite;
import highscores.HighScoreStore;
import main.Screen;
import main.MainMidlet;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
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
	private EndMarker endMarker;

    // variables for fps calculation
	private int entries;
	private long startTime;
	private int fps;

	private int points;

	// flag for jumping input handling
	private boolean jumping;
	// flag for attacking input handling
	private boolean attacking;

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

		try
		{
			// initialize static game objetcs
			this.foreground = new Foreground();
			this.background = new Background();
			this.endMarker = new EndMarker();

			// initialize bullets group
			this.bullets = new GameSpriteGroup();

			// initialize enemies group
			this.enemies = new GameSpriteGroup();

			// initialize enemies
			this.enemies.add(new HittingEnemySprite(11, 147));
			this.enemies.add(new HittingEnemySprite(100, 259));
			this.enemies.add(new HittingEnemySprite(410, 259));
			this.enemies.add(new HittingEnemySprite(650, 243));
			this.enemies.add(new HittingEnemySprite(350, 179));
			this.enemies.add(new HittingEnemySprite(920, 259));
			this.enemies.add(new HittingEnemySprite(850, 131));
			this.enemies.add(new HittingEnemySprite(899, 67));
			this.enemies.add(new HittingEnemySprite(550, 52));
			this.enemies.add(new HittingEnemySprite(300, 35));

			this.enemies.add(new ShootingEnemySprite(200, 100, this.bullets));
			this.enemies.add(new ShootingEnemySprite(100, 100, this.bullets));

			// initialize character
			this.mainChar = new CharacterSprite(this, this.foreground, this.bullets);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		this.hWidth = this.getWidth() / 2;
		this.hHeight = this.getHeight() / 2;

		this.jumping = false;
		this.attacking = false;
		
		this.walkSpeed = 5;

		this.points = 0;

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
		this.endMarker.paint(g);
		
		this.enemies.paint(g);
		this.bullets.paint(g);

		this.mainChar.paint(g);

		// calculate fps
		this.calculateFps(g);

		this.paintHud(g);
	}

	/**
	 * Paints the hud like life, points, etc
	 * @param g
	 */
	private void paintHud(Graphics g)
	{
		g.setColor(0);
		Font f = g.getFont();
		g.drawString("Life: " + this.mainChar.getLife(), this.getWidth(), 0, Graphics.TOP | Graphics.RIGHT);
		g.drawString("Points: " + this.points, this.getWidth(), f.getHeight(), Graphics.TOP | Graphics.RIGHT);

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

		if ((keys & FIRE_PRESSED) != 0)
		{
			if (!this.attacking)
			{
				this.mainChar.attack();
				this.attacking = true;
			}
		}
		else
		{
			this.attacking = false;
		}

		this.checkCharacterDamage();
		this.checkEnemyDamage();

		this.checkWon();
		this.checkLost();
		
		
		this.removeOutOfRangBullet();

		this.mainChar.update();
		this.enemies.update();
		this.bullets.update();

	}

	/**
	 * Checks if character has been hit by bullets or enemies
	 */
	public void checkCharacterDamage()
	{
		this.mainChar.defineCollisionRectangle(0, 0, this.mainChar.getWidth(), this.mainChar.getHeight());

		// collide with enemies
		EnemySprite enemy;
		int numEnemies = this.enemies.size();
		for (int i = 0; i < numEnemies; i++)
		{
			enemy = (EnemySprite) this.enemies.getSpriteAt(i);
			if (this.mainChar.collidesWith(enemy, true))
			{
				this.mainChar.reduceLife();
			}
		}

		// collide with bullets
		Bullet bullet;
		int numBullets = this.bullets.size();
		for (int i = 0; i < numBullets; i++)
		{
			bullet = (Bullet) this.bullets.getSpriteAt(i);
			if (bullet instanceof EnemyBullet && this.mainChar.collidesWith(bullet, true))
			{
				this.mainChar.reduceLife();
			}
		}

		this.mainChar.resetCollisionRectangle();
	}

	/**
	 * Checks if enemies have been hit by bullets
	 */
	public void checkEnemyDamage()
	{
		EnemySprite enemy;
		Bullet bullet;
		for (int enemyIdx = 0; enemyIdx < this.enemies.size(); enemyIdx++)
		{
			enemy = (EnemySprite) this.enemies.getSpriteAt(enemyIdx);
			for (int bulletIdx = 0; bulletIdx < this.bullets.size(); bulletIdx++)
			{
				bullet = (Bullet) this.bullets.getSpriteAt(bulletIdx);
				if (bullet instanceof CharacterBullet && enemy.collidesWith(bullet, true))
				{
					this.bullets.removeSpriteAt(bulletIdx);
					bulletIdx--;

					this.enemies.removeSpriteAt(enemyIdx);
					enemyIdx--;

					this.points += 100;

					continue;
				}
			}
		}
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

	/**
	 * Scrolls horizontally
	 * @param dx Distance to scroll
	 */
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
			this.bullets.move(-dx, 0);
			this.endMarker.move(-dx, 0);
		}
	}

	/**
	 * Scrolls vertically
	 * @param dy Distance to scroll
	 */
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
			this.bullets.move(0, -dy);
			this.endMarker.move(0, -dy);
		}
	}

	/**
	 * Removes bullets out of the screen range
	 */
	private void removeOutOfRangBullet()
	{
		int i = 0;
		while (i < this.bullets.size())
		{
			Bullet b = (Bullet) this.bullets.getSpriteAt(i);
			if (b.outsideScreen(this.getWidth()))
			{
				this.bullets.removeSpriteAt(i);
			}
			else
			{
				i++;
			}
		}
	}

	/**
	 * Checks if game has been lost
	 */
	public void checkWon()
	{
		if (this.mainChar.collidesWith(this.endMarker, false))
		{
			this.stop();

			boolean scoreCanBeAdded = this.points > this.midlet.getScores().getLowestScore() || this.midlet.getScores().size() < HighScoreStore.MAX_SCORES;

			if (scoreCanBeAdded)
			{
				Alert a = new Alert("Game won!! High Score!", "Add to high scores?", null, AlertType.INFO);

				a.setTimeout(Alert.FOREVER);
				a.addCommand(new Command("Yes", Command.OK, 1));
				a.addCommand(new Command("No", Command.CANCEL, 2));
				a.setCommandListener(new CommandListener(){
					public void commandAction(Command c, Displayable d)
					{
						if (c.getCommandType() == Command.OK)
						{
							Game.this.midlet.getScores().add("Name Here", Game.this.points);
							Game.this.midlet.startMainMenu();
						}
						else if (c.getCommandType() == Command.CANCEL)
						{
							Game.this.midlet.startMainMenu();
						}
					}
				});

				Display.getDisplay(midlet).setCurrent(a, this);
			}
			else
			{
				Alert a = new Alert("Game won!!", "", null, AlertType.INFO);

				a.setTimeout(Alert.FOREVER);
				a.setCommandListener(new CommandListener(){
					public void commandAction(Command c, Displayable d)
					{
						if (c.getCommandType() == Command.OK)
						{
							Game.this.midlet.startMainMenu();
						}
					}
				});

				Display.getDisplay(midlet).setCurrent(a, this);
			}
		}
	}

	/**
	 * Checks if game has been lost
	 */
	public void checkLost()
	{
		if (this.mainChar.getLife() <= 0)
		{
			this.stop();
			Alert a = new Alert("Game Lost", "", null, AlertType.INFO);
			a.setTimeout(Alert.FOREVER);
			a.setCommandListener(new CommandListener(){
				public void commandAction(Command c, Displayable d)
				{
					if (c.getCommandType() == Command.OK)
					{
						Game.this.midlet.startMainMenu();
					}
				}
			});
			Display.getDisplay(midlet).setCurrent(a, this);

		}
	}
}
