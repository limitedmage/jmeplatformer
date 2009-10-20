package menu.choice;

import main.MainMidlet;

public class EndGameChoice extends MenuChoice
{
	public EndGameChoice()
	{
		super("Exit game");
	}

	public void execute(MainMidlet midlet)
	{
		midlet.terminate();
	}

}
