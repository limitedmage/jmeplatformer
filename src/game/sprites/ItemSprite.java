package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Class that represents an item
 */
public abstract class ItemSprite extends GameSprite {

	private int points, recovery;

	/**
	 * Create a new Item
	 * @param img Image
	 * @param fWidth Frame width
	 * @param fHeight Frame height
	 * @param posX X position
	 * @param poxY Y position
	 * @param points Points given by picking the item
	 * @param recovery Life given by picking the item
	 * @throws IOException If img could not be loaded
	 */
	public ItemSprite(Image img, int fWidth, int fHeight, int posX, int poxY, int points, int recovery) throws IOException {
		super(img, fWidth, fHeight);
		this.setPosition(posX, poxY);

		this.points = points;
		this.recovery = recovery;
	}

	/**
	 * Gets the points given by the item
	 * @return Points given by the item
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Gets the life given by the item
	 * @return Life given by the item
	 */
	public int getRecovery() {
		return this.recovery;
	}

	/**
	 * Empty method
	 */
	public void update() {
	}
}
