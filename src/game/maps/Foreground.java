package game.maps;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

/**
 * A Foreground for the game
 */
public class Foreground extends TiledLayer {

	/**
	 * Creates new image for background from imagePath
	 * 
	 * @exception IOException when images fail to load
	 */
	public Foreground(short[][] tileData, Image img, int tileSize, int rows, int cols) throws IOException {
		super(cols, rows, img, tileSize, tileSize);

		// fill tile data
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				this.setCell(col, row, tileData[row][col]);
			}
		}
		
		this.setPosition(0, 0);
	}
}
