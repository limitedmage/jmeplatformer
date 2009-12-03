package menu;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import main.MainMidlet;

public class CreditsForm extends Form implements CommandListener {

	private MainMidlet midlet;
	private static String text =
			"Alejando Cruz\n" +
			"A00469848@itesm.mx\n" +
			"Carlos Meléndez\n" +
			"A00963806@itesm.mx\n" +
			"Juliana Peña\n" +
			"A01165536@item.mx\n\n" +
			"Tecnológico de Monterrey\n" +
			"Campus Estado de México\n" +
			"TC1006 Proyecto Integrador ISC\n\n" +
			"http://code.google.com/p/jmeplatformer/";


	public CreditsForm(MainMidlet midlet) {
		super("Credits");
		this.midlet = midlet;


		try {
			this.append(new ImageItem(null, Image.createImage("/img/menu/titles/credits.png"), ImageItem.LAYOUT_CENTER, null));
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
