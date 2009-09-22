package menu.choice;

import main.GameMidlet;

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
