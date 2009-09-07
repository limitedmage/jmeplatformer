package game.menu;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Menu extends GameCanvas implements Runnable
{
	private Vector choices;
	private int selected;

	public Menu()
	{
		super(true);

		choices = new Vector();
		selected = 0;
	}

	public void addChoice(MenuChoice choice)
	{
		choices.addElement(choice);
	}

	public void nextChoice()
	{
		selected += 1;
		if (selected >= choices.size())
			selected = 0;
	}

	public void prevChoice()
	{
		selected -= 1;
		if (selected < 0)
			selected = choices.size();
	}

	public void select()
	{
		((MenuChoice)choices.elementAt(selected)).execute();
	}

	public void run()
	{
		while (true)
		{
			repaint();

			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{}
		}
	}

	public void paint(Graphics g)
	{
		int len = choices.size();
		for (int i = 0; i < len; i++)
		{
			((MenuChoice)choices.elementAt(i)).paint(g, 0, 0, i == selected);
		}
	}
}
