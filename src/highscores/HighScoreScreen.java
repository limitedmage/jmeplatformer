package highscores;

import javax.microedition.lcdui.Graphics;
import main.MainMidlet;
import main.Screen;

public class HighScoreScreen extends Screen
{
	private HighScoreStore scores;


	public HighScoreScreen(MainMidlet midlet)
	{
		super(midlet);

		this.scores = midlet.getScores();
	}


	public void paint(Graphics g)
	{
		int len = this.scores.size();

		if (len > 0)
		{
			g.setColor(0x00000000);
			g.fillRect(0, 0, getWidth(), getHeight());

			int x = 0;
			int y = 0;

			int dy = this.getHeight() / len;

			g.setColor(0x00ff0000);

			for (int i = 0; i < len; i++)
			{
				g.drawString(this.scores.elementAt(i), x, y, Graphics.HCENTER);
				y += dy;
			}
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

}
