package menu;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import main.MainMidlet;
import main.Screen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A credits screen aknowledging all contributors
 */
public class Credits extends Screen implements CommandListener {

	// Credits text to be shown
	private String[] text;
	private Image title;

	/**
	 * Creates a new Credits screen
	 * @param midlet
	 */
	public Credits(MainMidlet midlet) {
		super(midlet);
		this.setFullScreenMode(false);

		this.text = new String[]{
			"Alejando Cruz",
			"alex_onish@hotmail.com",
			"Carlos Meléndez",
			"carlosmega106@hotmail.com",
			"Juliana Peña",
			"jpenao@gmail.com",
			"",
			"ITESM CEM",
			"TC1006",
			"http://code.google.com/p",
			"/jmeplatformer/"
		};

		this.addCommand(new Command("Back", Command.BACK, 1));
		this.setCommandListener(this);

		try {
			this.title = Image.createImage("/img/menu/titles/credits.png");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Updates the credits screen
	 * Pressing any key exits back to main menu
	 */
	public void update() {
		// press any key to go back to main menu
		if ((getKeyStates()) != 0) {
			this.midlet.startMainMenu();
		}
	}

	/**
	 * Paints the game screen
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		int x = getWidth() / 2;

		g.drawImage(this.title, x, 0, Graphics.HCENTER | Graphics.TOP);

		int len = this.text.length;

		if (len > 0) {
			int y = this.title.getHeight() * 2;

			Font f = g.getFont();

			int dy = getHeight() / len;
			g.setColor(0x00ffffff);
			for (int i = 0; i < len; i++) {
				g.drawString(this.text[i], x, y, Graphics.HCENTER | Graphics.TOP);
				y += f.getHeight();
			}
		}
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
