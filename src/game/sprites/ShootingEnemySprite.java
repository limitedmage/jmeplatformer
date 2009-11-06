package game.sprites;

import java.io.IOException;

public class ShootingEnemySprite extends EnemySprite
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

	// bullets group
	GameSpriteGroup bullets;

	/**
	 * Creates a new ShootingEnemy
	 * @param posX
	 * @param posY
	 * @param bullets GameSpriteGroup to add bullets to
	 * @throws IOException
	 */
	public ShootingEnemySprite(int posX, int posY, GameSpriteGroup bullets) throws IOException
	{
		super("/img/characters/icecreamsheet.png", 60, 60);

		this.setPosition(posX, posY);
		this.defineReferencePixel(30, 30);

		this.setState(IDLE);

		this.startTime = System.currentTimeMillis();

		this.bullets = bullets;
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
	 *
	 * Attacking creates a new bullet that
	 */
	public void attack()
	{
		if (this.state == IDLE)
		{
			this.setState(ATTACK);
		}
		
		try
		{
			bullets.add(new EnemyBullet(this.getX(), this.getY(), true));
		}
		catch (IOException ex)
		{
		}
	}
}
