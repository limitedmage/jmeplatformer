package music;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 * A class to handle music and sounds for the game
 */
public class MusicManager
{
	private Player player;

	private static final String
			menuMusicPath = "/music/menu.mid",
			game1MusicPath = "/music/game1.mid",
			game2MusicPath = "/music/game2.mid",
			game3MusicPath = "/music/game3.mid";

	private String current;

	/**
	 * Create a new MusicManager
	 */
	public MusicManager() {
	}

	/**
	 * Plays a Midi file from a string.
	 * If another file is playing, stops its first.
	 * @param path Path to the midi file
	 */
	public void playMidiFromPath(String path) {
		try {
			if (this.player != null) {
				this.player.stop();
				this.player.close();
			}

			InputStream music = this.getClass().getResourceAsStream(path);
			this.player = Manager.createPlayer(music, "audio/midi");
			this.player.setLoopCount(-1);
			this.player.start();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play game1.mid
	 */
	public void playGame1Music() {
		this.playMidiFromPath(game1MusicPath);
		current = game1MusicPath;
	}

	/**
	 * Play game2.mid
	 */
	public void playGame2Music() {
		this.playMidiFromPath(game2MusicPath);
		current = game2MusicPath;
	}

	/**
	 * Play game3.mid
	 */
	public void playGame3Music() {
		this.playMidiFromPath(game3MusicPath);
		current = game3MusicPath;
	}

	/**
	 * Play menu.mid
	 */
	public void playMenuMusic() {
		this.playMidiFromPath(menuMusicPath);
		current = menuMusicPath;
	}

	/**
	 * Stop music
	 */
	public void stop() {
		try {
			if (player != null) {
				player.stop();
				player.close();
				player = null;
			}
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Resume music
	 */
	public void resume() {
		if (current != null)
			playMidiFromPath(current);
	}
}
