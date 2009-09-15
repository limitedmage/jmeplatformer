package menu.choice;

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
