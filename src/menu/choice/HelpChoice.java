package menu.choice;

import main.MainMidlet;


public class HelpChoice extends MenuChoice {

	public HelpChoice() {
		super("Help", "/img/menu/choices/help.png", "/img/menu/choices/help-selected.png");
	}

	public void execute(MainMidlet midlet) {
		midlet.startHelp();
	}
}
