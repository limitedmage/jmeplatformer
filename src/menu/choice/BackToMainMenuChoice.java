package menu.choice;

import main.MainMidlet;

/**
 * A menu choice to return to main menu.
 */
public class BackToMainMenuChoice extends MenuChoice {

	/**
	 * Creates new choice.
	 */
	public BackToMainMenuChoice() {
		super("Back to main menu", "/img/menu/choices/backtomenu.png", "img/menu/choices/backtomenu-selected.png");
	}

	/**
	 * Excecutes choice by telling midlet to load
	 * main menu.
	 *
	 * @param midlet - GameMidlet that loads the main menu
	 */
	public void execute(MainMidlet midlet) {
		midlet.startMainMenu();
	}
}
