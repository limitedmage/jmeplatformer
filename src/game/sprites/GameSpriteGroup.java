package game.sprites;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import main.Paintable;
import main.Updateable;

/**
 * A class that represents a group of sprites that are
 * updated and drawn together
 * Internally it is wrapped around a Vector
 */
public class GameSpriteGroup implements Updateable, Paintable {

	// the vector where the sprites are stored
	private Vector spriteVec;

	/**
	 * Create a new GameSpriteGroup with initial capactity 10
	 */
	public GameSpriteGroup() {
		spriteVec = new Vector();
	}

	/**
	 * Create a new GameSpriteGroup
	 * @param capacity - Initial capacity
	 */
	public GameSpriteGroup(int capacity) {
		spriteVec = new Vector(capacity);
	}

	/**
	 * Adds a GameSprite to the GameSpriteGroup
	 * @param sprite GameSprite to add
	 */
	public void add(GameSprite sprite) {
		spriteVec.addElement(sprite);
	}

	/**
	 * Adds a GameSprite to the GameSpriteGroup in the specified index
	 * @param sprite GameSprite to add
	 * @param index Index to add the sprite to
	 */
	public void insertAt(GameSprite sprite, int index) {
		spriteVec.insertElementAt(sprite, index);
	}

	/**
	 * Updates all the GameSprites in the group
	 */
	public void update() {
		int len = this.size();

		for (int i = 0; i < len; i++) {
			((GameSprite) spriteVec.elementAt(i)).update();
		}
	}

	/**
	 * Paints all the GameSprites in the group
	 * @param g Graphics to paint with
	 */
	public void paint(Graphics g) {
		int len = this.size();

		for (int i = 0; i < len; i++) {
			((GameSprite) spriteVec.elementAt(i)).paint(g);
		}
	}

	/**
	 * Moves all the GameSprites in the group
	 * @param dx Horizontal movement
	 * @param dy Vertical movement
	 */
	public void move(int dx, int dy) {
		int len = spriteVec.size();

		for (int i = 0; i < len; i++) {
			((GameSprite) spriteVec.elementAt(i)).move(dx, dy);
		}
	}

	/**
	 * Removes an enemy from the group at the specified index
	 * @param index Index of the sprite to remove
	 */
	public void removeSpriteAt(int index) {
		spriteVec.removeElementAt(index);
	}

	/**
	 * Removes all sprites in the group
	 */
	public void removeAllSprites() {
		spriteVec.removeAllElements();
	}

	/**
	 * Returns the GameSprite at the specified inde
	 * @param index Index of the sprite to retrieve
	 * @return The GameSprite at the index
	 */
	public GameSprite getSpriteAt(int index) {
		return (GameSprite) spriteVec.elementAt(index);
	}

	/**
	 * Returns the index of a given sprite
	 * @param sprite GameSpriteSprite to search for
	 * @return The index of the sprite, or -1 if it is not found
	 */
	public int indexOf(GameSprite sprite) {
		return spriteVec.indexOf(sprite);
	}

	/**
	 * Returns the number of sprites in the group
	 * @return The number of sprites in the group
	 */
	public int size() {
		return spriteVec.size();
	}
}
