package menu.choice;

import main.MainMidlet;

public class ContinueGameChoice extends MenuChoice {

	public ContinueGameChoice() {
		super("Continue");
	}

	public void execute(MainMidlet midlet) {
		midlet.resumeGame();
	}
}
