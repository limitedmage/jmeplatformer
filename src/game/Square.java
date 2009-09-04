package game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Square
{

	private int x, y;	// position
	private int dx, dy; // increment in x and y
	private int width, height; // size of square
	private int color; // color of square

	private int sWidth, sHeight; // size of screen


	private Image image;

	public Square(int sWidth, int sHeight)
	{
		this(0, 0, 20, 20, 0x00ffff00, sWidth, sHeight);
	}

	public Square(int x, int y, int width, int height, int color, int sWidth, int sHeight)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;

		this.dx = 1;
		this.dy = 1;

		this.sWidth = sWidth;
		this.sHeight = sHeight;

		/*
		try
		{
			image = Image.createImage("/firefox32.png");
			width = image.getWidth();
			height = image.getHeight();
		}
		catch (IOException e)
		{
			System.out.println("Unable to load");
		}
		*/
		


	}

	public void moveLeft(int pixels)
	{
		x -= pixels;
		if (x + width > sWidth || x < 0)
			moveRight(pixels);
	}

	public void moveRight(int pixels)
	{
		x += pixels;
		if (x + width > sWidth || x < 0)
			moveLeft(pixels);
	}

	public void moveUp(int pixels)
	{
		y -= dy;
		if (y + height > sHeight || y < 0)
			moveDown(pixels);
	}

	public void moveDown(int pixels)
	{
		y += dy;
		if (y + height > sHeight || y < 0)
			moveUp(pixels);
	}

	public void bounce()
	{
		x += dx;
		y += dy;

		if (x + width > sWidth || x < 0)
			dx = -dx;

		if (y + height > sHeight || y < 0)
			dy = -dy;
	}

	public void scale(double factor)
	{
		width *= factor;
		height *= factor;

		if (width == 0)
		{
			width = 1;
		}

		if (height == 0)
		{
			height = 1;
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);

		if (image != null)
		{
			g.drawImage(image, x, y, 0);
		}
	}

	public int getX() { return this.x; }
	public void setX(int x) { this.x = x; }

	public int getY() { return this.y; }
	public void setY(int y) { this.y = y; }
}
