package main;

import javax.microedition.lcdui.Graphics;

public interface Updateable
{
	/**
     * Method that should update game physics, input, etc
     * Should handle only game logic.
     */
    public void update();
}
