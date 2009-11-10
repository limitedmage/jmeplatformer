package menu.choice;

import main.MainMidlet;

public class ContinueGameChoice extends MenuChoice {

	public ContinueGameChoice() {
		super("Continue", "/img/menu/choices/continue.png", "/img/menu/choices/continue-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.resumeGame();
	}
}
