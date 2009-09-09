package game.menu;

import game.GameMidlet;

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
