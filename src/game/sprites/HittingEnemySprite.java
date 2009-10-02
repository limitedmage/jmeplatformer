package game.sprites;

import java.io.IOException;

public class HittingEnemySprite extends GameSprite
{
	//sprite animation definitions
	private static final int[]
			idle = {0, 0, 1, 1, 2, 2, 3, 3},
			attack = {4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
			stopAttack = {24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

	// sprite states
	private static final short
			IDLE = 1,
			ATTACK = 2,
			STOP_ATTACK = 3;

	// current state
	private short state;

	// time for attack timing
	private long startTime;

	/**
	 * Creates a new enemy
	 * @param sWidth - Screen width
	 * @param sHeight - Screen height
	 * @param posX - X position of the enemy
	 * @param posY - Y position of the enemy
	 * @throws IOException
	 */
	public HittingEnemySprite(int sWidth, int sHeight, int posX, int posY) throws IOException
	{
		super("/img/characters/icecreamsheet.png", sWidth, sHeight, 60, 60);

		this.setPosition(posX, posY);
		this.defineReferencePixel(30, 30);

		this.setState(IDLE);

		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Creates a new enemy in the top right corner
	 * @param sWidth - Screen width
	 * @param sHeight - Screen height
	 * @throws IOException - If enemy image data could not be loaded
	 */
	public HittingEnemySprite(int sWidth, int sHeight) throws IOException
	{
		this(sWidth, sHeight, sWidth - 60, 0);
	}

	/**
	 * Changes the state of the sprite
	 * @param state
	 */
	private void setState(short state)
	{
		switch (state)
		{
			case IDLE:
				this.setFrameSequence(idle);
				break;
			case ATTACK:
				this.setFrameSequence(attack);
				break;
			case STOP_ATTACK:
				this.setFrameSequence(stopAttack);
				break;
			default:
				return;
		}

		this.state = state;
	}

	/**
	 * Updates the enemy
	 * Loads next frame
	 * Attacks every 5 seconds
	 * Updates state
	 */
	public void update()
	{
		this.nextFrame();

		// attack every 5 sec
		if (this.startTime + 5000 <= System.currentTimeMillis())
		{
			this.attack();
			this.startTime = System.currentTimeMillis();
		}

		// if at last frame of attacking, stop attacking
		if (this.state == ATTACK && this.getFrame() == attack.length - 1)
		{
			this.setState(STOP_ATTACK);
		}

		// if at last frame of stop attacking, idle
		if (this.state == STOP_ATTACK && this.getFrame() == stopAttack.length - 1)
		{
			this.setState(IDLE);
		}
	}

	/**
	 * Makes the enemy attack if it is idle
	 * If enemy is not idle, does nothing
	 */
	public void attack()
	{
		if (this.state == IDLE)
		{
			this.setState(ATTACK);
		}
	}
}
