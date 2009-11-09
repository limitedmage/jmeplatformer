package game.sprites;

import java.io.IOException;

/**
 * A marker for the end of a game level
 */
public class EndMarkerSprite extends GameSprite {

	/**
	 * Creates a new EndMarker
	 * @throws IOException if sprite could not be created
	 */
	public EndMarkerSprite() throws IOException {
		super("img/endMarker.png", 45, 45);
		this.setPosition(1011, 35);
		//this.setPosition(45, 100);
	}

	/**
	 * Empty method.
	 */
	public void update() {
	}
}
