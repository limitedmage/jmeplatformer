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

	public Background(String path)
	{
		try
		{
			image = Image.createImage(path);
		}
		catch (IOException ex)
		{
			
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, 0);
	}
}
