package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * A bullet projectile for the game
 */
public class BulletSprite extends GameSprite {

	private boolean goingRight;
	private static final int SPEED = 10;

	/**
	 * Creates a new bullet
	 * @param imgPath Path to the image
	 * @param fWidth frame width
	 * @param fHeight frame height
	 * @param posX x position
	 * @param posY y position
	 * @param goingRight true if going right, false if going left
	 * @throws IOException If sprite could not be created
	 */
	public BulletSprite(Image img, int fWidth, int fHeight, int posX, int posY, boolean goingRight) throws IOException {
		super(img, fWidth, fHeight);
		this.setPosition(posX, posY);
		this.goingRight = goingRight;
	}

	/**
	 * Moves the sprite according to direction and speed
	 */
	public void update() {
		if (this.goingRight) {
			this.move(SPEED, 0);
		}
		else {
			this.move(-SPEED, 0);
		}
	}

	/**
	 * Return true if bullet is outside the screen
	 * @param sWidth Screen width
	 * @return true if bullet is outside the screen, false otherwise
	 */
	public boolean outsideScreen(int sWidth) {
		if ((this.getX() >= sWidth) || (this.getX() <= 0)) {
			return true;
		}
		return false;
	}
}
