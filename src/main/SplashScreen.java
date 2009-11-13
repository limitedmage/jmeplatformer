package main;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Splash screen shown at the start of the game
 */
public class SplashScreen extends Screen {

	// splash image
	private Image imgMvp;
	private Image imgTec;

	// for timing
	private long startTime;
	private static final long DURATION = 2000; // 2 seconds

	/**
	 * Create a new splash screen
	 * @param midlet
	 */
	public SplashScreen(MainMidlet midlet) {
		super(midlet);

		this.startTime = System.currentTimeMillis();

		try {
			this.imgMvp = Image.createImage("/img/logo-mvp.png");
			this.imgTec = Image.createImage("/img/logo-tec.png");
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
		// if splash duration has passed, show the menu
		if (this.startTime + DURATION <= System.currentTimeMillis()) {
			this.midlet.startMainMenu();
		}
	}

	/**
	 * Paints the splash image
	 * @param g
	 */
	public void paint(Graphics g) {
		if (this.startTime + DURATION / 2 >= System.currentTimeMillis()) {
			// during first half of the splash, show Tec logo
			g.setColor(0x00ffffff);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.drawImage(this.imgTec, this.getWidth() / 2, this.getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
		}
		else {
			// during second half of the splash, show Game logo
			g.setColor(0);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.drawImage(this.imgMvp, this.getWidth() / 2, this.getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
		}
	}
}
