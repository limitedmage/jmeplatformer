package game.sprites;

import java.io.IOException;

public abstract class EnemySprite extends GameSprite
{
	public EnemySprite(String imgPath, int sWidth, int sHeight, int posX, int posY) throws IOException
	{
		super(imgPath, sWidth, sHeight, 45, 45);
		this.setPosition(posX, posY);

		this.defineReferencePixel(23, 23);
	}
}
