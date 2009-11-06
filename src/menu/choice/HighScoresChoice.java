package menu.choice;

import main.MainMidlet;

public class HighScoresChoice extends MenuChoice
{

	public HighScoresChoice()
	{
		super("High Scores");
	}


	public void execute(MainMidlet midlet)
	{
		midlet.startHighScores();
	}


}
