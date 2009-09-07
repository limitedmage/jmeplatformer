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

	/**
	 * Creates a new menu
	 */
	public Menu()
	{
		super(true);

		choices = new Vector();
		selected = 0;
	}

	/**
	 * Adds a choice to the menu
	 * @param choice
	 */
	public void addChoice(MenuChoice choice)
	{
		choices.addElement(choice);
	}

	/**
	 * Selects the next choice in the menu
	 * If at the end of the menu, it will select the first item
	 */
	public void nextChoice()
	{
		selected += 1;
		if (selected >= choices.size())
			selected = 0;
	}

	/**
	 * Selects the previous choice in the menu
	 * If at the beginning of the menu, it will select the las item
	 */
	public void prevChoice()
	{
		selected -= 1;
		if (selected < 0)
			selected = choices.size();
	}

	/**
	 * Excecutes the currently selected menu choice
	 */
	public void select()
	{
		((MenuChoice)choices.elementAt(selected)).execute();
	}

	/**
	 * Runs the menu
	 */
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

	/**
	 * Draws the menu
	 * @param g
	 */
	public void paint(Graphics g)
	{
		int len = choices.size();
		for (int i = 0; i < len; i++)
		{
			((MenuChoice)choices.elementAt(i)).paint(g, 0, 0, i == selected);
		}
	}
}
