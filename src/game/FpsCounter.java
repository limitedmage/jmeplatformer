package game;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import main.Paintable;
import main.Updateable;

/**
 * Class for an FPS counter in the game
 */
class FpsCounter implements Updateable, Paintable {
	private int entries;
	private long startTime;
	private int count;

	/**
	 * Create a new FPS counter
	 */
	public FpsCounter() {
		this.entries = 0;
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Update the FPS counter for another frame
	 */
	public void update() {
		this.entries++;
		if (this.startTime + 1000 <= System.currentTimeMillis()) {
			this.count = this.entries;
			this.entries = 0;
			this.startTime = System.currentTimeMillis();
		}

	}

	/**
	 * Paint the FPS counter
	 * @param g Graphics to draw on
	 */
	public void paint(Graphics g) {
		Font f = g.getFont();
		g.setColor(0);
		g.drawString("FPS: " + this.count, 0, f.getHeight(), 0);
	}

}
