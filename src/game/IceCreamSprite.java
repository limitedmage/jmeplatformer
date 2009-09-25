package game;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;


public class IceCreamSprite extends Sprite
{

	public IceCreamSprite() throws IOException
	{
		super(Image.createImage("/img/characters/icecreamsheet.png"));
	}


}
