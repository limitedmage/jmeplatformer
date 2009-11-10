package menu;

import java.io.IOException;
import menu.choice.MenuChoice;
import main.MainMidlet;
import main.Screen;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Describes a Menu as a list of MenuChoice
 */
public class Menu extends Screen {

	private Image title;

	private Vector choices;
	private int selected;
	private boolean menuChanged;

	/**
	 * Creates a new menu
	 */
	public Menu(MainMidlet midlet, String titleImagePath) {
		super(midlet);

		menuChanged = false;

		choices = new Vector();
		selected = 0;

		try {
			this.title = Image.createImage(titleImagePath);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Adds a choice to the menu
	 * @param choice
	 */
	public void addChoice(MenuChoice choice) {
		choices.addElement(choice);
	}

	/**
	 * Selects the next choice in the menu
	 * If at the end of the menu, it will select the first item
	 */
	public void nextChoice() {
		selected += 1;
		if (selected >= choices.size()) {
			selected = 0;
		}
	}

	/**
	 * Selects the previous choice in the menu
	 * If at the beginning of the menu, it will select the las item
	 */
	public void prevChoice() {
		selected -= 1;
		if (selected < 0) {
			selected = choices.size() - 1;
		}
	}

	/**
	 * Excecutes the currently selected menu choice
	 */
	public void select() {
		((MenuChoice) choices.elementAt(selected)).execute(midlet);
	}

	/**
	 * Draws the menu
	 * @param g
	 */
	public void paint(Graphics g) {
		// clear screen
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		int x = this.getWidth() / 2;

		g.drawImage(this.title, x, 0, Graphics.HCENTER | Graphics.TOP);

		int len = this.choices.size();

		if (len > 0) {
			int y = this.title.getHeight() * 2;
			
			int dy = (this.getHeight() - y) / len;

			for (int i = 0; i < len; i++) {
				((MenuChoice) choices.elementAt(i)).paint(g, x, y, i == selected);
				y += dy;
			}
		}
	}

	/**
	 * Handles menu input
	 * If Up pressed, selects previous choice
	 * If Down pressed, selects next choice
	 * If Fire pressed, executes currently selected choice
	 */
	public void update() {
		int keys = this.getKeyStates();

		if ((keys & DOWN_PRESSED) != 0) {
			if (!menuChanged) {
				this.nextChoice();
				menuChanged = true;
			}
		}
		else if ((keys & UP_PRESSED) != 0) {
			if (!menuChanged) {
				this.prevChoice();
				menuChanged = true;
			}
		}
		else if ((keys & FIRE_PRESSED) != 0) {
			this.select();
		}
		else {
			menuChanged = false;
		}
	}
}
