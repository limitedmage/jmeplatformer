package highscores;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import main.MainMidlet;
import main.Screen;

/**
 * Screen to show high scores.
 * High scores are loades from the constructor
 * agrument midlet's HighScoreStore
 *
 * Implements CommandListener and allows to clear scores
 */
public class HighScoreScreen extends Screen implements CommandListener {

	private HighScoreStore scores;

	/**
	 * Creates a new HighScoreScreen
	 * @param midlet Parent midlet
	 */
	public HighScoreScreen(MainMidlet midlet) {
		super(midlet);

		this.scores = midlet.getScores();
		this.setFullScreenMode(false);

		this.addCommand(new Command("Back", Command.BACK, 1));
		this.addCommand(new Command("Clear Scores", Command.SCREEN, 2));
		this.setCommandListener(this);
	}

	/**
	 * Paints the screen
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		int len = this.scores.size();

		if (len > 0) {
			Font f = g.getFont();
			g.setColor(0x00000000);
			g.fillRect(0, 0, getWidth(), getHeight());

			int x = this.getWidth() / 2;
			int y = 0;

			int dy = f.getHeight();

			g.setColor(0x00ff0000);

			for (int i = 0; i < len; i++) {
				String score = this.scores.elementAt(i);
				g.drawString((i + 1) + ". " + score, x, y, Graphics.HCENTER | Graphics.TOP);
				y += dy;
			}
		}
		else {
			g.setColor(0x00ff0000);
			g.drawString("No high scores yet", this.getWidth() / 2, 0, Graphics.HCENTER | Graphics.TOP);
		}
	}

	/**
	 * When any button is pressed, return to menu
	 */
	public void update() {
		// press any key to go back to main menu
		if ((getKeyStates()) != 0) {
			this.midlet.startMainMenu();
		}
	}

	/**
	 * Handles 2 commands: Back and Clear records.
	 * @param c
	 * @param d
	 */
	public void commandAction(Command c, Displayable d) {
		switch (c.getCommandType()) {
			case Command.BACK:
				// back to menu
				this.midlet.startMainMenu();
				return;

			case Command.SCREEN:
				// clear scores and back to menu
				Alert a = new Alert("Are you sure?", "All high scores will be deleted", null, AlertType.WARNING);
				a.setTimeout(Alert.FOREVER);
				a.addCommand(new Command("Yes", Command.OK, 1));
				a.addCommand(new Command("No", Command.CANCEL, 2));
				a.setCommandListener(new CommandListener() {

					public void commandAction(Command c, Displayable d) {
						switch (c.getCommandType()) {
							case Command.OK:
								HighScoreScreen.this.scores.deleteAll();
								HighScoreScreen.this.midlet.startHighScores();
								break;

							case Command.CANCEL:
								HighScoreScreen.this.midlet.startHighScores();
								break;
						}
					}
				});
				Display.getDisplay(this.midlet).setCurrent(a, this);
				break;

			default:
				break;
		}

	}
}
