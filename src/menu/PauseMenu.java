package menu;

import main.GameMidlet;
import menu.choice.BackToMainMenuChoice;
import menu.choice.ContinueGameChoice;

public class PauseMenu extends Menu
{
	public PauseMenu(GameMidlet midlet)
	{
		super(midlet);
		
		this.addChoice(new ContinueGameChoice());
		this.addChoice(new BackToMainMenuChoice());
	}
}
