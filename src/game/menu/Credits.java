package game.menu;

import game.GameMidlet;
import game.GameScreen;
import javax.microedition.lcdui.Graphics;

public class Credits extends GameScreen
{
	private String text;
	private Thread t;

	public Credits(GameMidlet midlet)
	{
		super(midlet);

		text = "Our Game!\n\nProgramming:\nAlex\nCarlos\nJuliana\n\nGraphics:\nLADs del TEC-CEM\n";
	}

	public void run()
	{
		while (true)
		{
			update();
			repaint();

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{}
		}
	}

	protected void update()
	{
		// press any key to go back to main menu
		if ((getKeyStates()) != 0)
		{
			midlet.startMainMenu();
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(0x00ff0000);
		g.drawString(text, 0, 0, 0);
	}


}
