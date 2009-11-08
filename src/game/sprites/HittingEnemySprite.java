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
	 * Creates a new hitting enemy
	 * @param posX - X position of the enemy
	 * @param posY - Y position of the enemy
	 * @throws IOException
	 */
	public HittingEnemySprite(int posX, int posY) throws IOException
	{
		super("/img/characters/Pegador.png", posX, posY);

		this.setState(IDLE);

		this.startTime = System.currentTimeMillis();
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
	 * Attacks every 2 seconds
	 * Updates state
	 */
	public void update()
	{
		// loads next frame in sequence
		this.nextFrame();

		// if 2 seconds have passed since last attack, attack
		if (this.startTime + 2000 <= System.currentTimeMillis())
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
