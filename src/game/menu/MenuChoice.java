package game.menu;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public abstract class MenuChoice
{
	//private Image image;
	private String text;

	public MenuChoice(/*Image image,*/ String text)
	{
		//this.image = image;
		this.text = text;
	}



	//public Image getImage(){ return this.image; }
	public String getText(){ return this.text; }

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

	public abstract void execute(); // abstract class for menu action
}
