package game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import main.Paintable;

public class Background implements Paintable
{
	private Image image;
	private int x, y;

	public Background() throws IOException
	{
		this.image = Image.createImage("/img/backgrounds/nivel1_fondo.png");

		this.setPosition(0, 0);
	}

	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void move(int dx, int dy)
	{
		this.x += dx;
		this.y += dy;
	}

	public void paint(Graphics g)
	{
		g.drawImage(this.image, this.x, this.y, 0);
	}
}
