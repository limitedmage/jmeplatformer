package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import main.Updateable;

/**
 * Class that defines the basic sprite in the game.
 */
public abstract class GameSprite extends Sprite implements Updateable {

	/**
	 * Creates a new GameSprite.
	 * 
	 * @param imgPath - path of the image the sprite should use
	 * @param fWidth - frame width
	 * @param fHeight - frame height
	 * @throws IOException - if image could not be loaded
	 */
	public GameSprite(String imgPath, int fWidth, int fHeight) throws IOException {
		super(Image.createImage(imgPath), fWidth, fHeight);
	}
}
