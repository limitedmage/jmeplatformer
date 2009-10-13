package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Bullet extends GameSprite
{
	private boolean goingRight;

	private static final int SPEED = 10;

	public Bullet(int sWidth, int sHeight, boolean goingRight, int posX, int posY) throws IOException
	{
		super("/img/bullet.png", sWidth, sHeight, 2, 2);

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


}
