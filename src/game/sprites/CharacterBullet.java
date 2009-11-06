package game.sprites;

import java.io.IOException;

public class CharacterBullet extends Bullet
{
	public CharacterBullet(int posX, int posY, boolean goingRight) throws IOException
	{
		super("/img/bulletCharacter.png", 4, 4, posX, posY, goingRight);

		this.setPosition(posX, posY);
	}
}
