package game;

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
	 * @throws IOException If background could not be created
	 */
	public Background() throws IOException {
		this.image = Image.createImage("/img/backgrounds/nivel1_fondo.png");

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
	 * Paints the background
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(this.image, this.x, this.y, 0);
	}
}
