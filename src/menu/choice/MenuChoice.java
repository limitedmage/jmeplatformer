package menu.choice;

import java.io.IOException;
import main.MainMidlet;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class MenuChoice {
	private String text;

	private Image normalImage, selectedImage;

	/**
	 * Creates a new menu choice
	 * @param text Text to be shown when menu text is drawn
	 */
	public MenuChoice(String text, String normalImagePath, String selectedImagePath) {
		this.text = text;
		try {
			this.normalImage = Image.createImage(normalImagePath);
			this.selectedImage = Image.createImage(selectedImagePath);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
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
			g.drawImage(this.normalImage, posX, posY, Graphics.HCENTER | Graphics.TOP);
		}
		else {
			g.drawImage(this.selectedImage, posX, posY, Graphics.HCENTER | Graphics.TOP);
		}
	}

	/**
	 * Abstract method that MenuChoice child classes should override.
	 * Should implement what the menu choice does when selected.
	 */
	public abstract void execute(MainMidlet midlet);
}
