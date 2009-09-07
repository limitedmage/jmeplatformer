package game.menu;

import game.GameMidlet;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
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
