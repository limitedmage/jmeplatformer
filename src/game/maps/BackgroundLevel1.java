package game.maps;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Background for Level 1
 */
public class BackgroundLevel1 extends Background {

	/**
	 * Initialize background for level 1
	 * @throws IOException
	 */
	public BackgroundLevel1() throws IOException {
		super(Image.createImage("/img/backgrounds/nivel1_fondo.png"));
	}
}
