package menu.choice;

import main.GameMidlet;

public class EndGameChoice extends MenuChoice
{
	public EndGameChoice()
	{
		super("Exit game");
	}

	public void execute(GameMidlet midlet)
	{
		midlet.terminate();
	}

}
