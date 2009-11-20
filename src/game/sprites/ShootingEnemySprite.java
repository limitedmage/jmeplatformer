package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * A class for a shooting enemy
 */
public class ShootingEnemySprite extends EnemySprite {

	public static Image img;
	public static final String imgPath = "/img/characters/Lanzador.png";

	//sprite animation definitions
	private static final int[]
			attack = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};

	// sprite states
	private static final short ATTACK = 2;

	// current state
	private short state;

	// bullets group
	private GameSpriteGroup bullets;

	/**
	 * Creates a new ShootingEnemy
	 * @param posX
	 * @param posY
	 * @param bullets GameSpriteGroup to add bullets to
	 * @throws IOException
	 */
	public ShootingEnemySprite(int posX, int posY, GameSpriteGroup bullets) throws IOException {
		super(img, 45, 45);

		this.setPosition(posX, posY);
		this.defineReferencePixel(30, 30);

		this.setState(ATTACK);

		this.bullets = bullets;
	}

	/**
	 * Changes the state of the sprite
	 * @param state
	 */
	private void setState(short state) {
		switch (state) {
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
	public void update() {
		// loads next frame in sequence
		this.nextFrame();

		// if at last frame of attacking, stop attacking
		if (this.state == ATTACK && this.getFrame() == attack.length - 1) {
			this.attack();
		}
	}

	/**
	 * Makes the enemy attack if it is idle
	 * If enemy is not idle, does nothing
	 *
	 * Attacking creates a new bullet that
	 */
	public void attack() {
		try {
			bullets.add(new EnemyBulletSprite(this.getX() + 5, this.getY() + 5, true));
		}
		catch (IOException ex) {
		}
	}
}
