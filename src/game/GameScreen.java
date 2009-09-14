/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public abstract class GameScreen extends GameCanvas implements Runnable
{
    protected GameMidlet midlet;

    public GameScreen(GameMidlet midlet)
    {
        super(true);

        this.midlet = midlet;

        setFullScreenMode(true);
    }

    public void start()
    {
        Thread t = new Thread(this);
        t.start();
    }

    public abstract void run();

    public abstract void paint(Graphics g);

    protected abstract void update();
}
