package menu;

import main.GameMidlet;
import menu.choice.BackToMainMenuChoice;
import menu.choice.ContinueGameChoice;

/**
 * A pause menu with 2 options:
 * continue game or exit to menu.
 */
public class PauseMenu extends Menu
{
	/**
	 * Creates new PauseMenu
	 * @param midlet - Parent MIDlet
	 */
	public PauseMenu(GameMidlet midlet)
	{
		super(midlet);
		
		this.addChoice(new ContinueGameChoice());
		this.addChoice(new BackToMainMenuChoice());
	}
}
