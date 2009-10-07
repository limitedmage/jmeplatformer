package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.game.Sprite;

/**
 * Sprite class that represents a vampire
 */
public class CharacterSprite extends GameSprite
{
	/*
	 * Sprite frame animation definitions
	 */
	private static final int[] // frame animation sequences
			idle       = {0},
			walk       = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8},
			jump       = {9},
			attack	   = {10, 11};

	/*
	 * Possible sprite states
	 */
	private static final short // sprite states
			IDLE       = 1,
			WALK       = 2,
			JUMP       = 3,
			ATTACK     = 4;

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
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 *
	 * @exception IOException when images fail to load
	 */
	public CharacterSprite(int sWidth, int sHeight) throws IOException
	{
		super("/img/characters/CharSprite.png", sWidth, sHeight, 45, 45);

		this.setPosition(0, sHeight - fHeight);
		this.defineReferencePixel(22, 22);

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
		//this.move(-5, 0);

		if (this.state == JUMP)
			this.jump();
	}

	/**
	 * Makes sprite walk to the right
	 */
	public void walkRight()
	{
		if (this.state != WALK && this.state != JUMP)
			this.setState(WALK);
		
		this.setTransform(Sprite.TRANS_NONE);
		//this.move(5, 0);

		if (this.state == JUMP)
			this.jump();
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
			this.jump();
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
			dy = -50;
		}

		// calculate gravity physics
		dy += gravity;

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
