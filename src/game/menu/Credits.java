package game.menu;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Credits extends GameCanvas implements Runnable
{
	private boolean running;
	private String text;
	private Thread t;

	public Credits()
	{
		super(true);

		t = new Thread(this);

		running = true;
		text = "Our Game!\n\nProgramming:\nAlex\nCarlos\nJuliana\n\nGráficos:\nLADs del TEC-CEM\n";

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
			repaint();

			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{}
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
