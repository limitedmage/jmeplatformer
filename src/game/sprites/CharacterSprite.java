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
			attack	   = {10, 11};

	// Possible sprite states
	private static final short // sprite states
			IDLE       = 1,
			WALK       = 2,
			JUMP       = 3,
			ATTACK     = 4;

	// Current state of the sprite
	private short state;

	// Gravity, how fast the sprite falls when in the air
	private static final int gravity = 5;

	// Initial jumping speed
	private static final int jumpSpeed = -30;

	// Horizontal and vertical speed
	private int dx, dy;
	
	/**
	 * Initialize the sprite, load the image
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 *
	 * @exception IOException when images fail to load
	 */
	public CharacterSprite(Game game, Foreground foreground) throws IOException
	{
		super("/img/characters/CharSprite.png", 45, 45);

		// start at an arbitrary position
		this.setPosition(20, 20);

		// define reference pixel as center of sprite
		this.defineReferencePixel(22, 22);

		// define collision rectangle as character's feet
		this.defineCollisionRectangle(9, 40, 23, 5);

		this.setState(IDLE);

		this.foreground = foreground;
		this.game = game;
	}

	/**
	 * Loads the next frame in the sprite
	 */
	public void update()
    {
		this.nextFrame();
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
		if (this.state != IDLE && this.state != JUMP)
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

	}

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
			dy = jumpSpeed;
		}

		this.fall();
	}
	
	public void fall()
	{
		// if first jumping
		if (this.state != JUMP)
		{
			// change animation to jump
			this.setState(JUMP);
		}

		// calculate gravity physics
		dy += gravity;
		System.out.println(dy);
		
		if (dy > 0)
		{
			// if moving down, check if collided with platform by falling pixel by pixel
			for (int pixels = 0; pixels < dy; pixels++)
			{
				game.verticalScroll(1);
				if (this.onPlatform())
				{
					this.dy = 0;
					this.setState(IDLE);
					break;
				}
			}
		}
		else if (dy < 0)
		{
			// if moving up, just move sprite vertically
			for (int pixels = 0; pixels < -dy; pixels++)
			{
				game.verticalScroll(-1);
			}
		}

	}
}
