package menu.choice;

import main.MainMidlet;

public class HighScoresChoice extends MenuChoice {

	public HighScoresChoice() {
		super("High Scores", "/img/menu/choices/highscores.png", "/img/menu/choices/highscores-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.startHighScores();
	}
}
