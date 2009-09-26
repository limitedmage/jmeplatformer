package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;


public class HittingEnemySprite extends GameSprite
{
	//sprite animation definitions
	private static final int[]
			idle = {0, 1, 2, 3},
			attack = {4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
			stopAttack = {24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

	private static final short
			IDLE = 1,
			ATTACK = 2,
			STOP_ATTACK = 3;

	private short state;

	private int dx, dy;

	public HittingEnemySprite(int sWidth, int sHeight) throws IOException
	{
		super("/img/characters/icecreamsheet.png", sWidth, sHeight, 60, 60);

		this.setPosition(sWidth - fWidth, 0);
		this.defineReferencePixel(30, 30);

		this.setState(IDLE);
	}

	private void setState(short state)
	{
		switch (state)
		{
			case IDLE:
				this.setFrameSequence(idle);
				break;
			case ATTACK:
				this.setFrameSequence(attack);
				break;
			case STOP_ATTACK:
				this.setFrameSequence(stopAttack);
				break;
			default:
				return;
		}

		this.state = state;
	}

	public void update()
	{
		this.nextFrame();
	}

	public void attack()
	{
		this.setState(ATTACK);
	}

	

	

}
