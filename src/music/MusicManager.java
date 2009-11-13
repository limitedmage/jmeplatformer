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
			gameMusicPath = "/music/game.mid";

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
	 * Play game.mid
	 */
	public void playGameMusic() {
		this.playMidiFromPath(gameMusicPath);
	}

	/**
	 * Play menu.mid
	 */
	public void playMenuMusic() {
		this.playMidiFromPath(menuMusicPath);
	}

	/**
	 * Play tone for when a choice is selected in the menu
	 */
	public void playSelectedTone() {
		try {
			Manager.playTone(50, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play tone for when shooting in the game
	 */
	public void playShootTone() {
		try {
			Manager.playTone(80, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play tone for when hitting in the game
	 */
	public void playHitTone() {
		try {
			Manager.playTone(100, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play tone for when collecting items in the game
	 */
	public void playItemTone() {
		try {
			Manager.playTone(100, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play tone for when winning the game
	 */
	public void playWonTone() {
		try {
			Manager.playTone(100, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Play tone for when losing the game
	 */
	public void playLostTone() {
		try {
			Manager.playTone(100, 500, 70);
		}
		catch (MediaException ex) {
			ex.printStackTrace();
		}
	}
}
