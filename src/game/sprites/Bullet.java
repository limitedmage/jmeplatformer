package game.sprites;

import java.io.IOException;

public class Bullet extends GameSprite
{
	private boolean goingRight;

	private static final int SPEED = 10;

	
	public Bullet(String imgPath, int fWidth, int fHeight, int posX, int posY, boolean goingRight) throws IOException
	{
			super(imgPath, fWidth, fHeight);
			this.setPosition(posX, posY);
			this.goingRight = goingRight;
	}

	public void update()
	{
		if (this.goingRight)
		{
			this.move(SPEED, 0);
		}
		else
		{
			this.move(-SPEED, 0);
		}
	}

	public boolean outsideScreen(int sWidth)
	{
		if ((this.getX() >= sWidth) || (this.getX() <= 0))
		{
			return true;
		}
		return false;
	}
}
