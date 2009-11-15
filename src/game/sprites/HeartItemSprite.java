package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

public class HeartItemSprite extends ItemSprite {

	public static Image img;
	public static final String imgPath = "/img/items/Corazon.png";

	public HeartItemSprite(int posX, int posY) throws IOException {
		super(img, 10, 10, posX, posY, 100, 1);
	}
}
