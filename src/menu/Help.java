package menu;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import main.MainMidlet;

public class Help extends Form implements CommandListener {

	private MainMidlet midlet;
	private static String text =
			"Destroy enemies and get to the final door in the level.\n" +
			"Beat all three levels.\n" +
			"Challenge a friend by SMS when you win.\n" +
			"If you fall or run out of life, you lose.\n" +
			"Walk with 4 and 6, jump with 2, shoot with 5, pause with 1" +
			"(keys may change depending on your phone)";


	public Help(MainMidlet midlet) {
		super("Help");
		this.midlet = midlet;

		
		try {
			this.append(new ImageItem(null, Image.createImage("/img/menu/titles/help.png"), ImageItem.LAYOUT_CENTER, null));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		this.append(text);

		this.addCommand(new Command("Back", Command.BACK, 1));
		this.setCommandListener(this);
	}

	/**
	 * Method required by command listener interface
	 * @param c Command excecuted
	 * @param d Displayable
	 */
	public void commandAction(Command c, Displayable d) {
		switch (c.getCommandType()) {
			case Command.BACK:
				this.midlet.startMainMenu();
		}
	}
}
