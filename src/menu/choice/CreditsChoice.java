package menu.choice;

import main.MainMidlet;

public class CreditsChoice extends MenuChoice {

	public CreditsChoice() {
		super("Credits", "/img/menu/choices/credits.png", "/img/menu/choices/credits-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.startCredits();
	}
}
