package game.sprites;

import game.maps.Foreground;
import game.Game;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Sprite class that represents a main character
 */
public class CharacterSprite extends GameSprite {

	public static Image img;
	public static final String imgPath = "/img/characters/CharSprite.png";

	// foreground for collision detection
	private Foreground foreground;

	// parent game
	private Game game;

	// Sprite frame animation definitions
	private static final int[]
			idle = {0},
			walk = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8},
			jump = {9},
			attack = {10, 10, 10, 11, 11, 11};

	// Possible sprite states
	private static final short // sprite states
			IDLE = 1,
			WALK = 2,
			JUMP = 3,
			ATTACK = 4;
	
	// life and invulnerability

	// initial life
	private static final int INIT_LIFE = 6;
	
	// current life
	private int life;

	// time to stay invulnerable
	private static final long INVULNERABLE_TIME = 1000;

	// time when invulnerability started
	private long startInvulnetableTime;

	// current invulnerability state
	private boolean invulnerable;

	// Current state of the sprite
	private short state;

	// Gravity, how fast the sprite falls when in the air
	private static final int gravity = 5;

	// Initial jumping speed
	private static final int jumpSpeed = -30;

	// walking speed
	private static final int walkSpeed = 5;

	// Horizontal and vertical speed
	private int dx, dy;
	
	// bullets group
	private GameSpriteGroup bullets;

	// direction for bullet creation
	private boolean goingRight;

	/**
	 * Initializes a CharacterSprite with a default life of 6
	 * @param game Parent game
	 * @param foreground Foreground to check collision with
	 * @param bullets Group to add new bullets to
	 * @throws IOException If sprite cannot be created
	 */
	public CharacterSprite(Game game, Foreground foreground, GameSpriteGroup bullets) throws IOException {
		super(img, 45, 45);

		// start at an arbitrary position
		this.setPosition(45, 147);

		// define reference pixel as center of sprite
		this.defineReferencePixel(22, 22);

		this.resetCollisionRectangle();

		this.setState(IDLE);

		this.foreground = foreground;
		this.game = game;
		this.bullets = bullets;

		this.life = INIT_LIFE;
		this.goingRight = true;
		this.invulnerable = false;
	}

	/**
	 * Initialize a CharacterSprite with a predetermined life
	 * @param game Parent game
	 * @param foreground Foreground to check collision with
	 * @param bullets Group to add new bullets to
	 * @param life Initial life of the character
	 * @throws IOException If sprite cannot be created
	 */
	public CharacterSprite(Game game, Foreground foreground, GameSpriteGroup bullets, int life) throws IOException {
		this(game, foreground, bullets);
		this.life = life;
	}

	/**
	 * Loads the next frame in the sprite.
	 * If sprite is invincible and invincibility time is over,
	 * remove incincibility.
	 * If sprite in invincible, toggle visibility to emulate blinking
	 *
	 */
	public void update() {
		this.nextFrame();

		if (this.invulnerable) {
			this.setVisible(!this.isVisible());

			if (this.startInvulnetableTime + CharacterSprite.INVULNERABLE_TIME <= System.currentTimeMillis()) {
				this.invulnerable = false;
				this.setVisible(true);
			}
		}
		else {
			this.setVisible(true);
		}
	}

	/**
	 * Define collision rectangle as character's feet
	 */
	public void resetCollisionRectangle() {
		this.defineCollisionRectangle(9, 40, 23, 5);
	}

	/**
	 * Returns character's current life
	 * @return The character's current life
	 */
	public int getLife() {
		return this.life;
	}

	/**
	 * Reduces the character's life by 1 point
	 */
	public void reduceLife() {
		this.reduceLife(1);
	}

	/**
	 * Reduces the character's life by the specified number of points
	 * @param p
	 */
	public void reduceLife(int p) {
		if (!this.invulnerable) {
			this.life -= p;
			this.invulnerable = true;
			this.startInvulnetableTime = System.currentTimeMillis();
		}
	}

	/**
	 * Restores the character's life by 1 point
	 */
	public void recoverLife() {
		this.recoverLife(1);
	}

	/**
	 * Restores the character's life by the specified number of points
	 * @param p
	 */
	public void recoverLife(int p) {
		this.life += p;
	}

	/**
	 * Changes the frame sequence in the spirte
	 *
	 * @param state IDLE, WALK, JUMP or LAND
	 */
	private void setState(short state) {
		switch (state) {
			case IDLE:
				this.setFrameSequence(idle);
				break;
			case WALK:
				this.setFrameSequence(walk);
				break;
			case JUMP:
				this.setFrameSequence(jump);
				break;
			case ATTACK:
				this.setFrameSequence(attack);
				break;
			default:
				break;
		}

		this.state = state;
	}

	/**
	 * Makes sprite walk to the left
	 */
	public void walkLeft() {
		this.game.horizontalScroll(-CharacterSprite.walkSpeed);

		if (this.state != WALK && this.state != JUMP) {
			this.setState(WALK);
		}

		this.setTransform(Sprite.TRANS_MIRROR);
		this.goingRight = false;

		if (this.state == JUMP) {
			this.jump();
			return;
		}

		if (!this.onPlatform()) {
			this.dy = 0;
			this.fall();
		}
	}

	/**
	 * Makes sprite walk to the right
	 */
	public void walkRight() {
		this.game.horizontalScroll(CharacterSprite.walkSpeed);
		
		if (this.state != WALK && this.state != JUMP) {
			this.setState(WALK);
		}

		this.setTransform(Sprite.TRANS_NONE);
		this.goingRight = true;

		if (this.state == JUMP) {
			this.jump();
			return;
		}

		if (!this.onPlatform()) {
			this.dy = 0;
			this.fall();
		}
	}

	/**
	 * Stops sprite form moving
	 * If sprite is jumping, calls this.jump()
	 * If sprite is not touching a platform, calls this.fall()
	 * If sprite is attacking and attack animation is over, sets animation to idle
	 */
	public void idle() {
		// if sprite is not jumping or attacking, use idle animation
		if (this.state != IDLE && this.state != JUMP && this.state != ATTACK) {
			this.setState(IDLE);
		}

		// if sprite is jumping, use jump animation and calculate jump physics
		if (this.state == JUMP) {
			this.jump();
			return;
		}

		// If sprite is not touching a platform, calls this.fall()
		if (!this.onPlatform()) {
			this.dy = 0;
			this.fall();
		}

		// If sprite is attacking and attack animation is over, sets animation to idle
		if (this.state == ATTACK && this.getFrame() == attack.length - 1) {
			this.setState(IDLE);
		}

	}

	/**
	 * Returns true if character is touching a platform
	 * @return true if the character is touching a platform, false otherwise
	 */
	public boolean onPlatform() {
		// store old visibility state
		boolean wasVisible = this.isVisible();

		// set visibility to true so collisions work
		this.setVisible(true);

		// check collision one pixel before
		boolean before = this.collidesWith(foreground, false);

		// move one pixel down
		this.move(0, 1);

		// check collision one pixel after
		boolean on = this.collidesWith(foreground, false);

		//restore position
		this.move(0, -1);

		// restore old visibility state
		this.setVisible(wasVisible);

		// return true when character is not touching the foreground before and is now.
		return !before && on;
	}

	/**
	 * Makes sprite jump and land
	 */
	public void jump() {
		// if first jumping
		if (this.state != JUMP) {
			// change animation to jump
			this.setState(JUMP);

			// negative vertical velocity for moving up
			this.dy = CharacterSprite.jumpSpeed;
		}

		this.fall();
	}

	/**
	 * Updates character's vertical speed
	 */
	public void fall() {
		// if first jumping
		if (this.state != JUMP) {
			// change animation to jump
			this.setState(JUMP);
		}

		// calculate gravity physics
		this.dy += CharacterSprite.gravity;

		if (this.dy > 0) {
			// if moving down, check if collided with platform by falling pixel by pixel
			for (int pixels = 0; pixels < this.dy; pixels++) {
				this.game.verticalScroll(1);
				if (this.onPlatform()) {
					this.dy = 0;
					this.setState(IDLE);
					break;
				}
			}
		}
		else if (this.dy < 0) {
			// if moving up, just move sprite vertically
			for (int pixels = 0; pixels < -this.dy; pixels++) {
				this.game.verticalScroll(-1);
			}
		}
	}

	/**
	 * Attacks by adding a new CharacterSprite to the bullets group
	 */
	public void attack() {
		if (this.state != ATTACK) {
			this.setState(ATTACK);
		}

		try {
			this.bullets.add(new CharacterBulletSprite(this.getRefPixelX(), this.getRefPixelY(), this.goingRight));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
