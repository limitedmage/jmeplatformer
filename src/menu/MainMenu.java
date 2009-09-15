package menu;

import game.GameMidlet;
import menu.choice.CreditsChoice;
import menu.choice.EndGameChoice;
import menu.choice.RunGameChoice;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class MainMenu extends Menu
{

    public MainMenu(GameMidlet midlet)
    {
        super(midlet);
        this.addChoice(new RunGameChoice());
		this.addChoice(new CreditsChoice());
		this.addChoice(new EndGameChoice());
    }


}
