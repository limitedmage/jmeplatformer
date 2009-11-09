package game.sprites;

import java.io.IOException;

public class SodaItemSprite extends ItemSprite {

	public SodaItemSprite(int posX, int posY) throws IOException {
		super("/img/items/Refresco.png", 9, 10, posX, posY, 100, 0);
	}
}
