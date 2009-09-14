package game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class VampSprite
{

	/**
	 * Image of sprite
	 */
	private Image img;

	/**
	 * Sprite data
	 */
	private Sprite sprite;

	/**
	 * Sprite frame animation definitions
	 */
	private static final int[] // frame animation sequences
			idle       = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3},
			walk       = {9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26},
			jump       = {27, 28, 29, 30, 31, 32, 33},
			land       = {36, 37, 38, 39, 40, 41, 42, 43};

	/**
	 * Possible sprite states
	 */
	private static final short // sprite states
			IDLE       = 1,
			WALK       = 2,
			JUMP       = 3,
			LAND       = 4;

	/**
	 * Current state of the sprite
	 */
	private short state;

	/**
	 * Width and height of frame
	 */
	private static final int // frame size in pixels
			fWidth  = 60,
			fHeight = 60;

	/**
	 * Width and height of screen
	 */
	private int sWidth, sHeight;

	/**
	 * Gravity, how fast the sprite falls when jumping
	 */
	private int gravity = 10;

	/**
	 * Horizontal and vertical speed
	 */
	private int dx, dy;
	


	/**
	 * Initialize the sprite, load the image
	 * Exits game if image cannot be loaded
	 *
	 * @param sWidth width of screen
	 * @param sHeight height of screen
	 */
	public VampSprite(int sWidth, int sHeight)
	{
		try
		{
			// assign parameters to instance variables
			this.sWidth  = sWidth;
			this.sHeight = sHeight;

			// load image and create sprite
			img = Image.createImage("/img/BigVampireSpriteSheet02.png");
			sprite = new Sprite(img, fWidth, fHeight);
			sprite.setPosition(0, sHeight - fHeight);
			sprite.defineReferencePixel(30, 30);

			// set initial state to idle
			this.setState(IDLE);
		}
		catch (IOException ex)
		{
			// error loading image! quit game
			System.exit(1);
		}
	}

	/**
	 * Paints the sprite
	 *
	 * @param g Graphics to draw on
	 */
	public void paint(Graphics g)
	{
		sprite.paint(g);
	}

	/**
	 * Loads the next frame in the sprite
	 */
	public void update()
	{

		sprite.nextFrame();
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
				sprite.setFrameSequence(idle);
				break;
			case WALK:
				sprite.setFrameSequence(walk);
				break;
			case JUMP:
				sprite.setFrameSequence(jump);
				break;
			case LAND:
				sprite.setFrameSequence(land);
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

		sprite.setTransform(Sprite.TRANS_MIRROR);
		sprite.move(-5, 0);

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
		
		sprite.setTransform(Sprite.TRANS_NONE);
		sprite.move(5, 0);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	/**
	 * Stops sprite form moving
	 */
	public void idle()
	{
		if (this.state != IDLE && this.state != JUMP && this.state != LAND)
			this.setState(IDLE);

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
		sprite.move(0, dy);

		// if done going down
		if (sprite.getY() > sHeight - fHeight)
		{
			// reset positon to ground
			sprite.setPosition(sprite.getX(), sHeight - fHeight);

			// change animation to idle
			this.setState(IDLE);
		}
	}


}
