package menu.choice;

import main.GameMidlet;

public class RunGameChoice extends MenuChoice
{
	public RunGameChoice()
	{
		super("Start game");
	}

	public void execute(GameMidlet midlet)
	{
		midlet.startGame();
	}
}
