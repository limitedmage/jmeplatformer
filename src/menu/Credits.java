package menu;

import game.GameMidlet;
import game.GameScreen;
import javax.microedition.lcdui.Graphics;

/**
 * A credits screen aknowledging all contributors
 */
public class Credits extends GameScreen
{
    /**
     * Credits text to be shown
     */
	private String text;

    /**
     * Creates a new Credits screen
     * @param midlet
     */
	public Credits(GameMidlet midlet)
	{
		super(midlet);

		text = "Our Game!\n\nProgramming:\nAlex\nCarlos\nJuliana\n\nGraphics:\nLADs del TEC-CEM\n";
	}

    /**
     * Updates the credits screen
     * Pressing any key exits back to main menu
     */
	protected void update()
	{
		// press any key to go back to main menu
		if ((getKeyStates()) != 0)
		{
			midlet.startMainMenu();
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

		g.setColor(0x00ff0000);
		g.drawString(text, 0, 0, 0);
	}


}
