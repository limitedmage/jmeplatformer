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
	private int x, y;//comentario
	private double time;
        Image temp = null;

	private VampSprite sprite;

	private int entries;
	private long startTime;
	private int fps;

	public Game()
	{
		super(true);

		int width = getWidth();
		int height = getHeight();

		sprite = new VampSprite(width, height);
		
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
			{}
		}
	}

	/**
	 * Método heredado de GameCanvas
	 * Sólo debe dibujar, no debe contener lógica
	 * @param g
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		  Image imgf = createImage("/fond.jpg");
                  g.drawImage(imgf, 0, 0, 0);

		sprite.draw(g);

		calculateFps(g);



	}

	private void update()
	{


		if ((this.getKeyStates() & LEFT_PRESSED) != 0)
		{
			sprite.walkLeft();
		}
		else if ((this.getKeyStates() & RIGHT_PRESSED) != 0)
		{
			sprite.walkRight();
		}
		else
		{
			sprite.idle();
		}

		if ((this.getKeyStates() & UP_PRESSED) != 0)
		{
			sprite.jump();
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

    private Image createImage(String string) {
        try{
            temp = Image.createImage(string);


        } catch(Exception exc){
         exc.printStackTrace();
        }
        return temp;
    }
}
