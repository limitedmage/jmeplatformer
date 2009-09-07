package game.menu;

import game.Game;
import game.GameMidlet;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
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
