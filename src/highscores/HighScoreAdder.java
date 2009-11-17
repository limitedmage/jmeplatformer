package highscores;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import main.MainMidlet;

/**
 * A form that asks for the player's name and adds the high score
 */
public class HighScoreAdder extends Form implements CommandListener {

	private int points;
	private MainMidlet midlet;
	private TextField name;

	/**
	 * Create a new form
	 * @param midlet Parent midlet
	 * @param points Poinst made by the player
	 */
	public HighScoreAdder( MainMidlet midlet, int points) {
		super("Add high score");

		this.points = points;
		this.midlet = midlet;

		this.name = new TextField("Name: ", null, HighScoreStore.NAME_SIZE, TextField.ANY);

		this.append(this.name);
		this.append(new String("Points: " + points));

		this.addCommand(new Command("Add", Command.OK, 1));
		this.addCommand(new Command("Cancel", Command.CANCEL, 2));

		this.setCommandListener(this);
	}

	/**
	 * Handle commands
	 * @param c
	 * @param d
	 */
	public void commandAction(Command c, Displayable d) {
		switch (c.getCommandType()) {
			case Command.OK:
				this.midlet.getScores().add(this.name.getString(), points);
				Alert a = new Alert("High score added!", "Want to challenge a friend through text message?", null, null);
				a.addCommand(new Command("Yes", Command.OK, 1));
				a.addCommand(new Command("No", Command.CANCEL, 1));
				a.setCommandListener(new CommandListener() {
					public void commandAction(Command c, Displayable d) {
						switch (c.getCommandType()) {
							case Command.OK:
								Display.getDisplay(midlet).setCurrent(new Challenge(midlet, name.getString(), points));
								break;
							case Command.CANCEL:
								midlet.startHighScores();
						}
					}
				});
				Display.getDisplay(midlet).setCurrent(a, this);
				break;

			case Command.CANCEL:
				this.midlet.startMainMenu();
				break;
		}
	}
}
