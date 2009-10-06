package menu.choice;

import main.GameMidlet;

public class ContinueGameChoice extends MenuChoice
{

	public ContinueGameChoice()
	{
		super("Continue");
	}

	public void execute(GameMidlet midlet)
	{
		midlet.resumeGame();
	}

}
