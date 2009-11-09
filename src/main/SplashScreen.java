package main;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Splash screen shown at the start of the game
 */
public class SplashScreen extends Screen {

	// splash image
	private Image img;

	// for timing
	private long startTime;

	/**
	 * Create a new splash screen
	 * @param midlet
	 */
	public SplashScreen(MainMidlet midlet) {
		super(midlet);

		this.startTime = System.currentTimeMillis();

		try {
			this.img = Image.createImage("/img/logo.png");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Counts the time till the splash screen disappears.
	 * Calls MainMidlet.startMainMenu() when done
	 */
	public void update() {
		// if 2 seconds have passed, show the menu
		if (this.startTime + 2000 <= System.currentTimeMillis()) {
			this.midlet.startMainMenu();
		}
	}

	/**
	 * Paints the splash image
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(0);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.drawImage(this.img, this.getWidth() / 2, this.getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
	}
}
