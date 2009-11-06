package game.sprites;

import java.io.IOException;

public class EndMarker extends GameSprite
{

	public EndMarker(int sWidth, int sHeight) throws IOException
	{
		super("img/endMarker.png", sWidth, sHeight, 45, 45);
		this.setPosition(1011, 35);
	}

	public void update()
	{
	}

}
