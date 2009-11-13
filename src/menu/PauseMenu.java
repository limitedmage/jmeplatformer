package menu;

import main.MainMidlet;
import menu.choice.BackToMainMenuChoice;
import menu.choice.ContinueGameChoice;

/**
 * A pause menu with 2 options:
 * continue game or exit to menu.
 */
public class PauseMenu extends Menu {

	/**
	 * Creates new PauseMenu
	 * @param midlet - Parent MIDlet
	 */
	public PauseMenu(MainMidlet midlet) {
		super(midlet, "/img/menu/titles/pause.png");

		this.addChoice(new ContinueGameChoice());
		this.addChoice(new BackToMainMenuChoice());
	}
}
