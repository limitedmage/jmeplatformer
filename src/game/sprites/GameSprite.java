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
	public GameSprite(Image img, int fWidth, int fHeight) throws IOException {
		super(img, fWidth, fHeight);
	}

	/**
	 * Checks if the sprite is in the screen
	 * @param sWidth Screen width
	 * @param sHeight Screen height
	 * @return true is sprite is in the screen, false otherwise
	 */
	public boolean inScreen(int sWidth, int sHeight) {
		if (this.getX() + this.getWidth() >= 0 && this.getX() < sWidth
			&& this.getY() + this.getHeight() >= 0 && this.getY() < sHeight) {
			return true;
		}
		else {
			return false;
		}
	}
}
