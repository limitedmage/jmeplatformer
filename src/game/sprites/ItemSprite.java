package game.sprites;

import java.io.IOException;

public abstract class ItemSprite extends GameSprite
{
	private int points, recovery;

	public ItemSprite(String imgPath, int fWidth, int fHeight, int posX, int poxY, int points, int recovery) throws IOException
	{
		super(imgPath, fWidth, fHeight);
		this.setPosition(posX, poxY);

		this.points = points;
		this.recovery = recovery;
	}

	public int getPoints()
	{
		return this.points;
	}

	public int getRecovery()
	{
		return this.recovery;
	}

	public void update()
	{
	}
}
