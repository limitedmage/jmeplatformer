package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Class for the EnemyBullet
 */
public class EnemyBulletSprite extends BulletSprite {

	public static Image img;
	public static final String imgPath = "/img/bulletEnemy.png";

	/**
	 * Creates a new EnemyBullet
	 * @param posX - x position of the bullet
	 * @param posY - y position of the bullet
	 * @param goingRight - true if going right, false if going left
	 * @throws IOException when sprite cannot be created
	 */
	public EnemyBulletSprite(int posX, int posY, boolean goingRight) throws IOException {
		super(img, 4, 4, posX, posY, goingRight);

		this.setPosition(posX, posY);
	}
}
