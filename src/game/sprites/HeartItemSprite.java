package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Class for a Heart item
 */
public class HeartItemSprite extends ItemSprite {

	public static Image img;
	public static final String imgPath = "/img/items/Corazon.png";

	/**
	 * Create a new Heart item
	 * @param posX X position
	 * @param posY Y position
	 * @throws IOException If sprite could not be created
	 */
	public HeartItemSprite(int posX, int posY) throws IOException {
		super(img, 10, 10, posX, posY, 100, 1);
	}
}
