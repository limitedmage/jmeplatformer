package main;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * GameScreen includes all the methods every screen in the game,
 * from menus to levels, should have.
 *
 * Includes methods for painting, updating, and managing the GameScreen thread.
 */
public abstract class GameScreen extends GameCanvas implements Runnable, Updateable
{
    // Parent MIDlet
    protected GameMidlet midlet;

    // Game keeps running while this is true
    protected boolean running;

	// Thread that runs the screen
	protected Thread thread;

    /**
     * Initializes the screen
     * Sets full screen mode on
     *
     * @param midlet Parent MIDlet
     */
    public GameScreen(GameMidlet midlet)
    {
        super(true);

        this.midlet = midlet;
        this.running = true;

        setFullScreenMode(true);
    }

    /**
     * Starts the execution of the screen
     * by running a new thread
     */
    public final void start()
    {
		this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Contains the logic to keep the game running
     * First updates the game logic,
     * then repaints the screen.
     * Sleeps for 50 ms every cycle.
     */
    public final void run()
    {
        while (running)
		{
			update();
            repaint();

			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				return;
			}
		}		
    }

	public final void stop()
	{
		this.running = false;
		if (this.thread != null)
		{
			this.thread.interrupt();
		}
	}

    /**
     * Method that should draw the screen.
     * It should not contain any game logic.
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * Method that should update all game physics, input, etc
     * Should handle all game logic.
     */
    public abstract void update();
}
