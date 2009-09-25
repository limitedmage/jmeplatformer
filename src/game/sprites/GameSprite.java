package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import main.Updateable;

public abstract class GameSprite extends Sprite implements Updateable
{
	// screen width and height
	protected int sWidth, sHeight;

	// frame width and height
	protected int fWidth, fHeight;

	public GameSprite(String imgPath, int sWidth, int sHeight, int fWidth, int fHeight) throws IOException
	{
		super(Image.createImage(imgPath), fWidth, fHeight);
		this.sWidth = sWidth;
		this.sHeight = sHeight;
		this.fWidth = fWidth;
		this.fHeight = fHeight;
	}
}
