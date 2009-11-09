package menu.choice;

import main.MainMidlet;

public class RunGameChoice extends MenuChoice {

	public RunGameChoice() {
		super("Start game");
	}

	public void execute(MainMidlet midlet) {
		midlet.startGame();
	}
}
