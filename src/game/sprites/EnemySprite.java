package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * An abstract class for an enemy sprite.
 * Implementing classes should have the attack() method.
 */
public abstract class EnemySprite extends GameSprite {

	/**
	 * Initialize the enemy sprite
	 * @param img Image for the sprite
	 * @param posX X position
	 * @param posY Y position
	 * @throws IOException If img cannot be loaded
	 */
	public EnemySprite(Image img, int posX, int posY) throws IOException {
		super(img, 45, 45);
		this.setPosition(posX, posY);

		this.defineReferencePixel(23, 23);
	}

	/**
	 * Abstract method all enemies should implement
	 */
	public abstract void attack();
}
