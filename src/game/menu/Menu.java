package game.menu;

import java.util.Vector;
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

	public void select()
	{
		((MenuChoice)choices.elementAt(selected)).execute();
	}

	public void run()
	{
	}
}
