package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * A marker for the end of a game level
 */
public class EndMarkerSprite extends GameSprite {

	public static Image img;
	public static final String imgPath = "/img/endMarker.png";

	/**
	 * Creates a new EndMarker
	 * @throws IOException if sprite could not be created
	 */
	public EndMarkerSprite() throws IOException {
		super(img, 45, 45);
		this.setPosition(1011, 35);
		//this.setPosition(45, 100);
	}

	/**
	 * Empty method.
	 */
	public void update() {
	}
}
