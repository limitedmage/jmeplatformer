package menu.choice;

import main.MainMidlet;
import javax.microedition.lcdui.Graphics;

public abstract class MenuChoice {
	//private Image image;

	private String text;

	/**
	 * Creates a new menu choice
	 * @param text Text to be shown when menu text is drawn
	 */
	public MenuChoice(String text) {
		this.text = text;
	}

	/**
	 * Return the text of the menu choice
	 * @return The menu choice's text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Draws the menu choice text
	 * @param g Graphics to paint to
	 * @param posX X position of text
	 * @param posY Y position of text
	 * @param selected If true, text will be red, else, it will be white.
	 */
	public void paint(Graphics g, int posX, int posY, boolean selected) {
		if (selected) {
			g.setColor(0xff0000);
		}
		else {
			g.setColor(0xffffff);
		}

		g.drawString(text, posX, posY, Graphics.HCENTER | Graphics.TOP);

	}

	/**
	 * Abstract method that MenuChoice child classes should override.
	 * Should implement what the menu choice does when selected.
	 */
	public abstract void execute(MainMidlet midlet);
}
