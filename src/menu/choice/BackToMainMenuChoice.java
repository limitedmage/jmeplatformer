package menu.choice;

import main.GameMidlet;

public class BackToMainMenuChoice extends MenuChoice
{

	public BackToMainMenuChoice()
	{
		super("Back to main menu");
	}

	public void execute(GameMidlet midlet)
	{
		midlet.startMainMenu();
	}

}
