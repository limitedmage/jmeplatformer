package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Juliana Peña <jpenao@gmail.com>
 */
public class Game extends GameCanvas implements Runnable
{

	private VampSprite sprite;
	private Background background;
	
	private int entries;
	private long startTime;
	private int fps;
	private boolean jumping;

	public Game()
	{
		super(true);

		int width = getWidth();
		int height = getHeight();

		sprite = new VampSprite(width, height);
		jumping = false;

		background = new Background("/fond.jpg");

		entries = 0;
		startTime = System.currentTimeMillis();

		setFullScreenMode(true);
	}

	public void start()
	{
		Thread t = new Thread(this);
		t.start();		// llama a this.run()
	}

	/**
	 * Método heredado de Runnable, NO SE DEBE LLAMAR
	 */
	public void run()
	{
		while (true)
		{
			//System.out.println("Dibujando");
			repaint();
			update();

			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
			}
		}
	}

	/**
	 * Método heredado de GameCanvas
	 * Sólo debe dibujar, no debe contener lógica
	 * @param g
	 */
	public void paint(Graphics g)
	{

		// clear screen
		g.setColor(0x00000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		// paint background
		background.paint(g);

		// paint sprite
		sprite.draw(g);

		// calculate fps
		calculateFps(g);
	}

	private void update()
	{
		int keys = this.getKeyStates();

		if ((keys & LEFT_PRESSED) != 0)
		{
			sprite.walkLeft();
		}
		else if ((keys & RIGHT_PRESSED) != 0)
		{
			sprite.walkRight();
		}
		else
		{
			sprite.idle();
		}

		if ((keys & UP_PRESSED) != 0)
		{
			if (!jumping)
			{
				sprite.jump();
				jumping = true;
			}
		}
		else
		{
			jumping = false;
		}



		sprite.update();
	}

	private void calculateFps(Graphics g)
	{
		entries++;
		if (startTime + 1000 <= System.currentTimeMillis())
		{
			fps = entries;
			entries = 0;
			startTime = System.currentTimeMillis();
		}
		g.drawString("FPS: " + fps, 0, 0, 0);
	}
}
