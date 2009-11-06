package menu;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import main.MainMidlet;
import main.Screen;
import javax.microedition.lcdui.Graphics;

/**
 * A credits screen aknowledging all contributors
 */
public class Credits extends Screen implements CommandListener
{
    /**
     * Credits text to be shown
     */
	private String[] text;

    /**
     * Creates a new Credits screen
     * @param midlet
     */
	public Credits(MainMidlet midlet)
	{
		super(midlet);
		this.setFullScreenMode(false);

		this.text = new String[] {"Our Game!", "Alex", "Carlos", "Juliana", "", "ITESM CEM", "TC2003", "Fall 2009"};

		this.addCommand(new Command("Back", Command.BACK, 1));
		this.setCommandListener(this);
	}

    /**
     * Updates the credits screen
     * Pressing any key exits back to main menu
     */
	public void update()
	{
		// press any key to go back to main menu
		if ((getKeyStates()) != 0)
		{
			this.midlet.startMainMenu();
		}
	}

    /**
     * Paints the game screen
     * @param g
     */
	public void paint(Graphics g)
	{
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		int len = this.text.length;

		if (len > 0)
		{
			int y = 0;
			int x = getWidth() / 2;

			Font f = g.getFont();

			int dy = getHeight() / len;
			g.setColor(0x00ff0000);
			for (int i = 0; i < len; i++)
			{
				g.drawString(this.text[i], x, y, Graphics.HCENTER | Graphics.TOP);
				y += f.getHeight();
			}
		}
	}

	public void commandAction(Command c, Displayable d)
	{
		switch (c.getCommandType())
		{
			case Command.BACK:
				this.midlet.startMainMenu();
		}
	}


}
