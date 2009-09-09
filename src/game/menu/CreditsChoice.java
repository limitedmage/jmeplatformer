package game.menu;

import game.GameMidlet;

public class CreditsChoice extends MenuChoice
{

	public CreditsChoice()
	{
		super("Credits");
	}


	public void execute(GameMidlet midlet)
	{
		midlet.startCredits();
	}

}
