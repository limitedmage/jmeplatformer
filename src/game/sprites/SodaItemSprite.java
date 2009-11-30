package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Class for a Soda item
 */
public class SodaItemSprite extends ItemSprite {

	public static Image img;
	public static final String imgPath = "/img/items/Refresco.png";

	/**
	 * Create a new Soda item
	 * @param posX X position
	 * @param posY Y position
	 * @throws IOException If sprite could not be created
	 */
	public SodaItemSprite(int posX, int posY) throws IOException {
		super(img, 9, 10, posX, posY, 100, 0);
	}
}
