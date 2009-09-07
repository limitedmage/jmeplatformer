package game.menu;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public abstract class MenuChoice
{
	private Image image;
	private String text;

	public MenuChoice(Image image, String text)
	{
		this.image = image;
		this.text = text;
	}



	public Image getImage(){ return this.image; }
	public String getText(){ return this.text; }

	public void paint(Graphics g)
	{

	}

	public abstract void execute(); // abstract class for menu action
}
