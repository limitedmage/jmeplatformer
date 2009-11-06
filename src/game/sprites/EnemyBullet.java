package game.sprites;

import java.io.IOException;

public class EnemyBullet extends Bullet
{

	public EnemyBullet(int posX, int posY, boolean goingRight) throws IOException
	{
		super("/img/bulletEnemy.png", 4, 4, posX, posY, goingRight);

		this.setPosition(posX, posY);
	}


}
