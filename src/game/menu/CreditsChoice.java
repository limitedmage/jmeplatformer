package game.menu;

import game.GameMidlet;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
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
