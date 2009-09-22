package menu.choice;

import main.GameMidlet;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public abstract class MenuChoice
{
	//private Image image;
	private String text;

	/**
	 * Creates a new menu choice
	 * @param text Text to be shown when menu text is drawn
	 */
	public MenuChoice(/*Image image,*/ String text)
	{
		//this.image = image;
		this.text = text;
	}

	//public Image getImage(){ return this.image; }

	/**
	 * Return the text of the menu choice
	 * @return
	 */
	public String getText(){ return this.text; }

	/**
	 * Draws the menu choice text
	 * @param g
	 * @param posx x position of text
	 * @param posy y position of text
	 * @param selected if true, text will be red, else, it will be white.
	 */
	public void paint(Graphics g, int posx, int posy, boolean selected)
	{
		if (selected)
		{
			g.setColor(0xff0000);
		}
		else
		{
			g.setColor(0xffffff);
		}
		
		g.drawString(text, posx, posy, 0);

	}

	/**
	 * Abstract method that MenuChoice child classes should override.
	 * Should implement what the menu choice does when selected.
	 */
	public abstract void execute(GameMidlet midlet); // abstract class for menu action
}
