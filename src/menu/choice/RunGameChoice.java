package menu.choice;

import main.MainMidlet;

public class RunGameChoice extends MenuChoice {

	public RunGameChoice() {
		super("Start game", "/img/menu/choices/newgame.png", "/img/menu/choices/newgame-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.startGame();
	}
}
