package game.sprites;

import java.io.IOException;

public class EndMarker extends GameSprite
{

	public EndMarker() throws IOException
	{
		super("img/endMarker.png", 45, 45);
		this.setPosition(1011, 35);
	}

	public void update()
	{
	}

}
