package game.sprites;

import java.io.IOException;

/**
 * Class for the CharacterBullet
 */
public class CharacterBulletSprite extends BulletSprite
{
	/**
	 * Creates a new CharacterBullet
	 * @param posX - x position of the bullet
	 * @param posY - y position of the bullet
	 * @param goingRight - true if going right, false if going left
	 * @throws IOException when sprite cannot be created
	 */
	public CharacterBulletSprite(int posX, int posY, boolean goingRight) throws IOException
	{
		super("/img/bulletCharacter.png", 6, 6, posX, posY, goingRight);

		this.setPosition(posX, posY);
	}
}
