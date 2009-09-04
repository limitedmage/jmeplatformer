package game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class VampSprite
{

	private Image img;
	private Sprite sprite;

	private static final int[] // frame animation sequences
			idle       = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3},
			walk       = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35},
			run        = {38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56},
			jump       = {57, 58, 59, 60, 61, 62, 63},
			doubleJump = {},
			land       = {95, 96, 97, 98, 99, 100, 101, 102};

	public static final int // sprite states
			IDLE       = 1,
			WALK       = 2,
			RUN        = 3,
			JUMP       = 4,
			DOUBLEJUMP = 5,
			LAND       = 6;

	private int state;

	private static final int // frame size in pixels
			fWidth  = 60,
			fHeight = 60;

	private int sWidth, sHeight;

	private int gravity = 10;
	private int dx, dy;
	


	/**
	 * Initialize the sprite, load the image
	 */
	public VampSprite(int sWidth, int sHeight)
	{
		try
		{
			this.sWidth  = sWidth;
			this.sHeight = sHeight;


			img = Image.createImage("/BigVampireSpriteSheet02.png");
			sprite = new Sprite(img, fWidth, fHeight);
			sprite.setPosition(0, sHeight - fHeight);
			sprite.defineReferencePixel(30, 30);

			this.setState(IDLE);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public void draw(Graphics g)
	{
		sprite.paint(g);
	}

	public void update()
	{

		sprite.nextFrame();
	}


	public void setState(int state)
	{
		switch (state)
		{
			case IDLE:
				sprite.setFrameSequence(idle);
				break;
			case WALK:
				sprite.setFrameSequence(walk);
				break;
			case RUN:
				sprite.setFrameSequence(run);
				break;
			case JUMP:
				sprite.setFrameSequence(jump);
				break;
			case DOUBLEJUMP:
				sprite.setFrameSequence(doubleJump);
				break;
			case LAND:
				sprite.setFrameSequence(land);
				break;
			default:
				break;
		}

		this.state = state;
	}


	public void walkLeft()
	{
		if (this.state != WALK && this.state != JUMP && this.state != LAND)
			this.setState(WALK);

		sprite.setTransform(Sprite.TRANS_MIRROR);
		sprite.move(-5, 0);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}
	
	public void walkRight()
	{
		if (this.state != WALK && this.state != JUMP && this.state != LAND)
			this.setState(WALK);
		
		sprite.setTransform(Sprite.TRANS_NONE);
		sprite.move(5, 0);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	public void idle()
	{
		if (this.state != IDLE && this.state != JUMP && this.state != LAND)
			this.setState(IDLE);

		if (this.state == JUMP || this.state == LAND)
			this.jump();
	}

	public void jump()
	{
		if (this.state != JUMP && this.state != LAND)
		{
			this.setState(JUMP);
			dy = -50;
		}

		dy += gravity;
		if (dy > 0 && this.state != LAND)
			this.setState(LAND);

		sprite.move(0, dy);

		if (sprite.getY() > sHeight - fHeight)
		{
			sprite.setPosition(sprite.getX(), sHeight - fHeight);
			this.setState(IDLE);
		}
	}


}
