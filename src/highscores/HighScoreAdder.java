package highscores;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import main.MainMidlet;

public class HighScoreAdder extends Form implements CommandListener
{
	private int points;
	private MainMidlet midlet;

	private TextField name;

	public HighScoreAdder(int points, MainMidlet midlet)
	{
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

	public void commandAction(Command c, Displayable d)
	{
		switch (c.getCommandType())
		{
			case Command.OK:
				this.midlet.getScores().add(this.name.getString(), points);
				this.midlet.startHighScores();
				break;
				
			case Command.CANCEL:
				this.midlet.startMainMenu();
		}
	}
}
