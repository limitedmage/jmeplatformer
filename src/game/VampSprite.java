package game;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Sprite class that represents a vampire
 */
public class VampSprite extends GameSprite
{
	/*
	 * Sprite frame animation definitions
	 */
	private static final int[] // frame animation sequences
			idle       = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3},
			walk       = {9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26},
			jump       = {27, 28, 29, 30, 31, 32, 33},
			land       = {36, 37, 38, 39, 40, 41, 42, 43};

	/*
	 * Possible sprite states
	 */
	private static final short // sprite states
			IDLE       = 1,
			WALK       = 2,
			JUMP       = 3,
			LAND       = 4;

	/*
	 * Current state of the sprite
	 */
	private short state;

	/*
	 * Gravity, how fast the sprite falls when in the air
	 */
	private int gravity = 10;

	/*
	 * Horizontal and vertical speed
	 */
	private int dx, dy;
	
	/**
	 * Initialize the sprite, load the image
	 * Exits game if image cannot be loaded
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 *
	 * @exception IOException when images fail to load
	 */
	public VampSprite(int sWidth, int sHeight) throws IOException
	{
		super("/img/characters/BigVampireSpriteSheet02.png", sWidth, sHeight, 60, 60);

		this.setPosition(0, sHeight - fHeight);
		this.defineReferencePixel(30, 30);

		this.setState(IDLE);
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
			case LAND:
				this.setFrameSequence(land);
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
		if (this.state != WALK && this.state != JUMP && this.state != LAND)
			this.setState(WALK);

		this.setTransform(Sprite.TRANS_MIRROR);
		this.move(-5, 0);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	/**
	 * Makes sprite walk to the right
	 */
	public void walkRight()
	{
		if (this.state != WALK && this.state != JUMP && this.state != LAND)
			this.setState(WALK);
		
		this.setTransform(Sprite.TRANS_NONE);
		this.move(5, 0);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	/**
	 * Stops sprite form moving
     * If sprite is jumping, calls this.jump()
	 */
	public void idle()
	{
        // if sprite is not jumping, use idle animation
		if (this.state != IDLE && this.state != JUMP && this.state != LAND)
			this.setState(IDLE);

        // if sprite is jumping, use jump animation and calculate jump physics
		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	/**
	 * Makes sprite jump and land
	 */
	public void jump()
	{
		// if first jumping
		if (this.state != JUMP && this.state != LAND)
		{
			// change animation to jump
			this.setState(JUMP);

			// negative vertical velocity for moving up
			dy = -50;
		}

		// calculate gravity physics
		dy += gravity;

		// if done going up
		if (dy > 0 && this.state != LAND)
		{
			// change animation to landing
			this.setState(LAND);
		}

		// move sprite vertically
		this.move(0, dy);

		// if done going down
		if (this.getY() > sHeight - fHeight)
		{
			// reset positon to ground
			this.setPosition(this.getX(), sHeight - fHeight);

			// change animation to idle
			this.setState(IDLE);
		}
	}


}
