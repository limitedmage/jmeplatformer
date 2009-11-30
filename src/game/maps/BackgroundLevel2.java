package game.maps;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Background for Level 2
 */
public class BackgroundLevel2 extends Background {

	/**
	 * Initialize background for level 2
	 * @throws IOException
	 */
	public BackgroundLevel2() throws IOException {
		super(Image.createImage("/img/backgrounds/nivel2_fondo.png"));
	}
}
