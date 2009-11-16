package menu.choice;

import main.MainMidlet;

public class NewGameChoice extends MenuChoice {

	public NewGameChoice() {
		super("Start game", "/img/menu/choices/newgame.png", "/img/menu/choices/newgame-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.startGame();
	}
}
