package menu;

import menu.choice.MenuChoice;
import main.GameMidlet;
import main.GameScreen;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class Menu extends GameScreen
{
	private Vector choices;
	private int selected;

	private boolean menuChanged;

	/**
	 * Creates a new menu
	 */
	public Menu(GameMidlet midlet)
	{
		super(midlet);

		menuChanged = false;

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
		((MenuChoice)choices.elementAt(selected)).execute(midlet);
	}

	/**
	 * Draws the menu
	 * @param g
	 */
	public void paint(Graphics g)
	{
		// clear screen

		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		int y = 0;
		int x = 0;


		int len = choices.size();

		int dy = getHeight() / len;

		for (int i = 0; i < len; i++)
		{
			((MenuChoice)choices.elementAt(i)).paint(g, x, y, i == selected);
			y += dy;
		}
	}

	/**
	 * Handles menu input
	 * If Up pressed, selects previous choice
	 * If Down pressed, selects next choice
	 * If Fire pressed, executes currently selected choice
	 */
	public void update()
	{
		int keys = this.getKeyStates();
		
		if ((keys & DOWN_PRESSED) != 0)
		{
			if (!menuChanged)
			{
				this.nextChoice();
				menuChanged = true;
			}
		}

		else if ((keys & UP_PRESSED) != 0)
		{
			if (!menuChanged)
			{
				this.prevChoice();
				menuChanged = true;
			}
		}

		else if ((keys & FIRE_PRESSED) != 0)
		{
			this.select();
		}

		else
		{
			menuChanged = false;
		}
	}
}
