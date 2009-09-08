package game.menu;

import game.GameMidlet;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Credits extends GameCanvas implements Runnable
{
	private String text;
	private Thread t;
	private GameMidlet midlet;

	public Credits(GameMidlet midlet)
	{
		super(true);
		this.midlet = midlet;

		t = new Thread(this);

		text = "Our Game!\n\nProgramming:\nAlex\nCarlos\nJuliana\n\nGraphics:\nLADs del TEC-CEM\n";

		this.setFullScreenMode(true);
	}

	public void start()
	{
		t.start();
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

	public void update()
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
