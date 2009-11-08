package game.sprites;

import java.io.IOException;

/**
 * Class for the EnemyBullet
 */
public class EnemyBullet extends Bullet
{
	/**
	 * Creates a new EnemyBullet
	 * @param posX - x position of the bullet
	 * @param posY - y position of the bullet
	 * @param goingRight - true if going right, false if going left
	 * @throws IOException when sprite cannot be created
	 */
	public EnemyBullet(int posX, int posY, boolean goingRight) throws IOException
	{
		super("/img/bulletEnemy.png", 4, 4, posX, posY, goingRight);

		this.setPosition(posX, posY);
	}
}
