package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

public class SodaItemSprite extends ItemSprite {

	public static Image img;
	public static final String imgPath = "/img/items/Refresco.png";

	public SodaItemSprite(int posX, int posY) throws IOException {
		super(img, 9, 10, posX, posY, 100, 0);
	}
}
