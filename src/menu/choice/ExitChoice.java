package menu.choice;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import main.MainMidlet;
import main.Screen;

/**
 * MenuChoice for ending game
 */
public class ExitChoice extends MenuChoice {

	public ExitChoice() {
		super("Exit game", "/img/menu/choices/exit.png", "/img/menu/choices/exit-selected.png");
	}

	/**
	 * Creates an alert to ask the user if he wants to quit
	 * If user selects Yes, Midlet will quit
	 * If user selects No, main menu is shown
	 * @param midlet
	 */
	public void execute(MainMidlet midlet) {
		Alert a = new Alert("Are you sure you want to exit?", null, null, AlertType.CONFIRMATION);
		a.addCommand(new Command("Yes", Command.OK, 1));
		a.addCommand(new Command("No", Command.CANCEL, 2));
		a.setCommandListener(new ExitAlertCommandListener(midlet));
		Screen oldScreen = (Screen) Display.getDisplay(midlet).getCurrent();
		Display.getDisplay(midlet).setCurrent(a, oldScreen);
	}

	/**
	 * Local class for handling end game commands.
	 * If yes, game quits
	 * If no, main menu is shown
	 */
	class ExitAlertCommandListener implements CommandListener {

		private MainMidlet midlet;

		public ExitAlertCommandListener(MainMidlet midlet) {
			this.midlet = midlet;
		}

		public void commandAction(Command c, Displayable d) {
			switch (c.getCommandType()) {
				case Command.OK:
					this.midlet.terminate();
					break;
				case Command.CANCEL:
					this.midlet.startMainMenu();
					break;
			}
		}
	}
}
