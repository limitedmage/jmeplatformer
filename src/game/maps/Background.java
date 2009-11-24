package game.maps;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import main.Paintable;

/**
 * A background for the game
 */
public class Background implements Paintable {

	private Image image;
	private int x, y;

	/**
	 * Creates a new background
	 * @throws IOException If background image could not be loaded
	 */
	public Background(Image img) throws IOException {
		this.image = img;
		this.setPosition(0, 0);
	}

	/**
	 * Changes the background position
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the background
	 * @param dx
	 * @param dy
	 */
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	/**
	 * Return horizontal position of the background
	 * @return x position
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Return vertical position of the background
	 * @return y position
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Return width of the background
	 * @return width
	 */
	public int getWidth() {
		return this.image.getWidth();
	}

	/**
	 * Return height of the background
	 * @return height
	 */
	public int getHeight() {
		return this.image.getHeight();
	}

	/**
	 * Paints the background
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(this.image, this.x, this.y, 0);
	}
}
