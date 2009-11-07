package game.sprites;

import java.io.IOException;

public abstract class EnemySprite extends GameSprite
{
	public EnemySprite(String imgPath, int posX, int posY) throws IOException
	{
		super(imgPath, 45, 45);
		this.setPosition(posX, posY);

		this.defineReferencePixel(23, 23);
	}

	public abstract void attack();
}
