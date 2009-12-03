package menu;

import main.MainMidlet;
import menu.choice.CreditsChoice;
import menu.choice.ExitChoice;
import menu.choice.HelpChoice;
import menu.choice.HighScoresChoice;
import menu.choice.NewGameChoice;

/**
 * Class for the main menu.
 * Has 4 menu choices:
 * <li>RunGameChoice</li>
 * <li>HighScoresChoice</li>
 * <li>CreditsChoice</li>
 * <li>EndGameChoice</li>
 */
public class MainMenu extends Menu {

	public MainMenu(MainMidlet midlet) {
		super(midlet, "/img/menu/titles/maintitle.png");
		this.addChoice(new NewGameChoice());
		this.addChoice(new HighScoresChoice());
		this.addChoice(new HelpChoice());
		this.addChoice(new CreditsChoice());
		this.addChoice(new ExitChoice());
	}
}
