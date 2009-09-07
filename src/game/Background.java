package game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Background
{
	private Image image;

	/**
	 * Creates new image for background from imagePath
	 * @param imagePath path of background image
	 */
	public Background(String imagePath)
	{
		try
		{
			image = Image.createImage(imagePath);
		}
		catch (IOException ex)
		{}
	}

	/**
	 * Paints background image
	 * @param g
	 */
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, 0);
	}
}
