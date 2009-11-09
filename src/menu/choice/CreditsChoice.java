package menu.choice;

import main.MainMidlet;

public class CreditsChoice extends MenuChoice {

	public CreditsChoice() {
		super("Credits");
	}

	public void execute(MainMidlet midlet) {
		midlet.startCredits();
	}
}
