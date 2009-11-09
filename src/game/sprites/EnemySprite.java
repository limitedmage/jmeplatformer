package game.sprites;

import java.io.IOException;

/**
 * An abstract class for an enemy sprite.
 * Implementing classes should have the attack() method.
 */
public abstract class EnemySprite extends GameSprite {

	public EnemySprite(String imgPath, int posX, int posY) throws IOException {
		super(imgPath, 45, 45);
		this.setPosition(posX, posY);

		this.defineReferencePixel(23, 23);
	}

	public abstract void attack();
}
