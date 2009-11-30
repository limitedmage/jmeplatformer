package game.maps;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Background for Level 3
 */
public class BackgroundLevel3 extends Background {

	/**
	 * Initialize background for level 3
	 * @throws IOException
	 */
	public BackgroundLevel3() throws IOException {
		super(Image.createImage("/img/backgrounds/nivel3_fondo.png"));
	}
}
