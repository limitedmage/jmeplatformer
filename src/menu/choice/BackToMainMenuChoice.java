package menu.choice;

import main.GameMidlet;

/**
 * A menu choice to return to main menu.
 */
public class BackToMainMenuChoice extends MenuChoice
{
	/**
	 * Creates new choice.
	 */
	public BackToMainMenuChoice()
	{
		super("Back to main menu");
	}

	/**
	 * Excecutes choice by telling midlet to load
	 * main menu.
	 *
	 * @param midlet - GameMidlet that loads the main menu
	 */
	public void execute(GameMidlet midlet)
	{
		midlet.startMainMenu();
	}

}
