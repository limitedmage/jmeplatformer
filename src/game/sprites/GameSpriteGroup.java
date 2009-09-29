package game.sprites;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import main.Updateable;

public class GameSpriteGroup implements Updateable
{
	private Vector spriteVec;

	public GameSpriteGroup()
	{
		spriteVec = new Vector();
	}

	public GameSpriteGroup(int capacity)
	{
		spriteVec = new Vector(capacity);
	}

	public void add(GameSprite sprite)
	{
		spriteVec.addElement(sprite);
	}

	public void update()
	{
		int len = spriteVec.size();

		for (int i = 0; i < len; i++)
		{
			((GameSprite)spriteVec.elementAt(i)).update();
		}
	}

	public void paint(Graphics g)
	{
		int len = spriteVec.size();

		for (int i = 0; i < len; i++)
		{
			((GameSprite)spriteVec.elementAt(i)).paint(g);
		}
	}

	public void move(int dx, int dy)
	{
		int len = spriteVec.size();

		for (int i = 0; i < len; i++)
		{
			((GameSprite)spriteVec.elementAt(i)).move(dx, dy);
		}
	}

	public void remove(int index)
	{
		spriteVec.removeElementAt(index);
	}

	public GameSprite get(int index)
	{
		return (GameSprite)spriteVec.elementAt(index);
	}
}
