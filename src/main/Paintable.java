package main;

import javax.microedition.lcdui.Graphics;

/**
 * Interface for an object that should be painted
 */
public interface Paintable
{
	/**
	 * Paints the object, should not do anything logic or physics related
	 * @param g
	 */
	public void paint(Graphics g);

}
