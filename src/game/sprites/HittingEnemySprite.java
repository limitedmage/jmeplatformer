package game.sprites;

import java.io.IOException;

public class HittingEnemySprite extends EnemySprite
{
	//sprite animation definitions
	private static final int[]
			idle = {8},
			attack = {5, 5, 6, 6, 7, 7, 8, 8};

	// sprite states
	private static final short
			IDLE = 1,
			ATTACK = 2;

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
		super("/img/characters/Pegador.png", sWidth, sHeight, posX, posY);

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
		// loads next frame in sequence
		this.nextFrame();

		// if 5 seconds have passed since last attack, attack
		if (this.startTime + 5000 <= System.currentTimeMillis())
		{
			this.attack();
			this.startTime = System.currentTimeMillis();
		}

		// if at last frame of attacking, stop attacking
		if (this.state == ATTACK && this.getFrame() == attack.length - 1)
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
