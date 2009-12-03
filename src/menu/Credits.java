package menu;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import main.MainMidlet;

/**
 * Credits screen
 */
public class Credits extends Form implements CommandListener {

	private MainMidlet midlet;
	private static String text =
			"Juliana Peña\n" +
			"A01165536@item.mx\n" +
			"Carlos Meléndez\n" +
			"A00963806@itesm.mx\n" +
			"Alejando Cruz\n" +
			"A00469848@itesm.mx\n\n" +
			"Tecnológico de Monterrey\n" +
			"Campus Estado de México\n" +
			"TC1006 Proyecto Integrador ISC\n\n";
	private static String url = "http://code.google.com/p/jmeplatformer/";

	/**
	 * Creates a new credits screen
	 * @param midlet Parent midlet
	 */
	public Credits(MainMidlet midlet) {
		super("Credits");
		this.midlet = midlet;

		try {
			this.append(new ImageItem(null, Image.createImage("/img/menu/titles/credits.png"), ImageItem.LAYOUT_CENTER, null));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		this.append(text);

		StringItem urlItem = new StringItem(null, url, Item.HYPERLINK);
		urlItem.setDefaultCommand(new Command("Open", Command.ITEM, 1));
		urlItem.setItemCommandListener(new ItemCommandListener() {
			public void commandAction(Command c, Item item) {
				switch (c.getCommandType()) {
					case Command.ITEM:
						try {
							Credits.this.midlet.platformRequest(url); // try to open url
							Credits.this.midlet.notifyDestroyed();
						}
						catch (Exception e) {
							e.printStackTrace();
							// fail silently
						}
						break;
				}
			}
		});

		this.append(urlItem);

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
				break;
		}
	}
}
