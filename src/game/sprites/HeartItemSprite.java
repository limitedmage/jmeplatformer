package game.sprites;

import java.io.IOException;

public class HeartItemSprite extends ItemSprite {

	public HeartItemSprite(int posX, int posY) throws IOException {
		super("/img/items/Corazon.png", 10, 10, posX, posY, 100, 1);
	}
}
