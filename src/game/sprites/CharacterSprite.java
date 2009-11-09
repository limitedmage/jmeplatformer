package game.sprites;

import game.Foreground;
import game.Game;
import java.io.IOException;
import javax.microedition.lcdui.game.Sprite;

/**
 * Sprite class that represents a vampire
 */
public class CharacterSprite extends GameSprite
{
	// Foreground for collision detection
	private Foreground foreground;

	// parent game
	private Game game;

	// Sprite frame animation definitions
	private static final int[] // frame animation sequences
			idle       = {0},
			walk       = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8},
			jump       = {9},
			attack	   = {10, 10, 10, 11, 11, 11};

	// Possible sprite states
	private static final short // sprite states
			IDLE       = 1,
			WALK       = 2,
			JUMP       = 3,
			ATTACK     = 4;

	private static final int INIT_LIFE = 6;
	private int life;

	private static final long INVULNERABLE_TIME = 1000;
	private long starInvulnetableTime;
	private boolean invulnerable;

	// Current state of the sprite
	private short state;

	// Gravity, how fast the sprite falls when in the air
	private static final int gravity = 5;

	// Initial jumping speed
	private static final int jumpSpeed = -30;

	// Horizontal and vertical speed
	private int dx, dy;

	private GameSpriteGroup bullets;

	private boolean goingRight;
	
	/**
	 * Initialize the sprite, load the image
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 *
	 * @exception IOException when images fail to load
	 */
	public CharacterSprite(Game game, Foreground foreground, GameSpriteGroup bullets) throws IOException
	{
		super("/img/characters/CharSprite.png", 45, 45);

		// start at an arbitrary position
		this.setPosition(45, 147);

		// define reference pixel as center of sprite
		this.defineReferencePixel(22, 22);

		this.resetCollisionRectangle();

		this.setState(IDLE);

		this.foreground = foreground;
		this.game = game;
		this.bullets = bullets;

		this.life = INIT_LIFE;
		this.goingRight = true;
	}

	/**
	 * Loads the next frame in the sprite
	 */
	public void update()
    {
		this.nextFrame();

		if (this.invulnerable)
		{
			if (this.starInvulnetableTime + INVULNERABLE_TIME <= System.currentTimeMillis())
			{
				this.invulnerable = false;
			}
		}
	}

	/**
	 * Define collision rectangle as character's feet
	 */
	public void resetCollisionRectangle()
	{
		this.defineCollisionRectangle(9, 40, 23, 5);
	}

	/**
	 * Returns character's current life
	 * @return
	 */
	public int getLife()
	{
		return this.life;
	}

	/**
	 * Reduces the character's life by 1 point
	 */
	public void reduceLife()
	{
		if (!this.invulnerable)
		{
			this.life -= 1;
			this.invulnerable = true;
			this.starInvulnetableTime = System.currentTimeMillis();
		}
	}

	/**
	 * Restores the character's life by 1 point
	 */
	public void recoverLife()
	{
		this.life += 1;
	}

	/**
	 * Changes the frame sequence in the spirte
	 *
	 * @param state IDLE, WALK, JUMP or LAND
	 */
	private void setState(short state)
	{
		switch (state)
		{
			case IDLE:
				this.setFrameSequence(idle);
				break;
			case WALK:
				this.setFrameSequence(walk);
				break;
			case JUMP:
				this.setFrameSequence(jump);
				break;
			case ATTACK:
				this.setFrameSequence(attack);
				break;
			default:
				break;
		}

		this.state = state;
	}

	/**
	 * Makes sprite walk to the left
	 */
	public void walkLeft()
	{
		if (this.state != WALK && this.state != JUMP)
			this.setState(WALK);

		this.setTransform(Sprite.TRANS_MIRROR);
		this.goingRight = false;

		if (this.state == JUMP)
		{
			this.jump();
			return;
		}

		if (!this.onPlatform())
		{
			this.dy = 0;
			this.fall();
		}
	}

	/**
	 * Makes sprite walk to the right
	 */
	public void walkRight()
	{
		if (this.state != WALK && this.state != JUMP)
			this.setState(WALK);
		
		this.setTransform(Sprite.TRANS_NONE);
		this.goingRight = true;

		if (this.state == JUMP)
		{
			this.jump();
			return;
		}

		if (!this.onPlatform())
		{
			this.dy = 0;
			this.fall();
		}
	}

	/**
	 * Stops sprite form moving
     * If sprite is jumping, calls this.jump()
	 */
	public void idle()
	{
        // if sprite is not jumping, use idle animation
		if (this.state != IDLE && this.state != JUMP && this.state != ATTACK)
			this.setState(IDLE);

        // if sprite is jumping, use jump animation and calculate jump physics
		if (this.state == JUMP)
		{
			this.jump();
			return;
		}

		if (!this.onPlatform())
		{
			this.dy = 0;
			this.fall();
		}

		if (this.state == ATTACK && this.getFrame() == attack.length - 1)
			this.setState(IDLE);

	}

	/**
	 * Returns true if character is touching a platform
	 * @return
	 */
	public boolean onPlatform()
	{
		boolean belowForeground = this.getY() > foreground.getY() + foreground.getHeight() - this.getHeight();

		boolean before = this.collidesWith(foreground, false);

		// move one pixel down
		this.move(0, 1);

		boolean on = this.collidesWith(foreground, false);

		//restore position
		this.move(0, -1);

		return (!before && on) || belowForeground;
	}

	/**
	 * Makes sprite jump and land
	 */
	public void jump()
	{
		// if first jumping
		if (this.state != JUMP)
		{
			// change animation to jump
			this.setState(JUMP);

			// negative vertical velocity for moving up
			this.dy = CharacterSprite.jumpSpeed;
		}

		this.fall();
	}

	/**
	 * Updates character's vertical speed
	 */
	public void fall()
	{
		// if first jumping
		if (this.state != JUMP)
		{
			// change animation to jump
			this.setState(JUMP);
		}

		// calculate gravity physics
		this.dy += CharacterSprite.gravity;
		
		if (this.dy > 0)
		{
			// if moving down, check if collided with platform by falling pixel by pixel
			for (int pixels = 0; pixels < this.dy; pixels++)
			{
				this.game.verticalScroll(1);
				if (this.onPlatform())
				{
					this.dy = 0;
					this.setState(IDLE);
					break;
				}
			}
		}
		else if (this.dy < 0)
		{
			// if moving up, just move sprite vertically
			for (int pixels = 0; pixels < -this.dy; pixels++)
			{
				this.game.verticalScroll(-1);
			}
		}

	}

	/**
	 * Attacks by adding a new CharacterSprite to the bullets group
	 */
	public void attack()
	{
		if (this.state != ATTACK)
			this.setState(ATTACK);
		
		try
		{
			this.bullets.add(new CharacterBulletSprite(this.getRefPixelX(), this.getRefPixelY(), this.goingRight));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
