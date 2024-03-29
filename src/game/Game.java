package game;

import game.maps.*;
import game.sprites.*;
import highscores.HighScoreAdder;
import highscores.HighScoreStore;
import main.Screen;
import main.MainMidlet;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * The actual game!
 */
public class Game extends Screen {

	private static final short LEVEL1 = 1,
			LEVEL2 = 2,
			LEVEL3 = 3;
	private short currentLevel;
	// elements of the game
	private CharacterSprite mainChar;
	private Foreground foreground;
	private Background background;
	private EndMarkerSprite endMarker;
	// game sprite groups for game objects
	private GameSpriteGroup enemies,
			bullets,
			items;
	// points in the game
	private int points;
	// start time
	private long startTime;
	// image for showing life
	private Image lifeImg;
	// flag for jumping input handling
	private boolean jumping;
	// flag for attacking input handling
	private boolean attacking;
	// half of screen width and height, used to calculate scrolling.
	private int hWidth, hHeight;
	// variables for FPS timing
	private FpsCounter fpsCounter;

	/**
	 * Starts a new game
	 * @param midlet - Parent MIDlet
	 */
	public Game(MainMidlet midlet) {
		super(midlet);

		try {
			// initialize all images
			ImageHelper.initImages();

			// initialize Level 1
			initLevel1();

			// initialize character
			//this.mainChar = new CharacterSprite(this, this.foreground, this.bullets);

			// initialize life image
			this.lifeImg = Image.createImage("/img/items/Corazon.png");



		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

		this.hWidth = this.getWidth() / 2;
		this.hHeight = this.getHeight() / 2;

		this.jumping = false;
		this.attacking = false;

		this.points = 0;

		this.startTime = System.currentTimeMillis();

		this.fpsCounter = new FpsCounter();
	}

	/**
	 * Initialize level 1 objects
	 * @throws IOException
	 */
	public void initLevel1() throws IOException {
		// initialize static game objetcs
		this.foreground = new ForegroundLevel1();
		this.background = new BackgroundLevel1();
		this.endMarker = new EndMarkerSprite();

		// initialize bullets group
		this.bullets = new GameSpriteGroup();

		// initialize enemies group
		this.enemies = new GameSpriteGroup();

		// initialize enemies
		this.enemies.add(new HittingEnemySprite(11, 147));
		this.enemies.add(new HittingEnemySprite(100, 259));
		this.enemies.add(new HittingEnemySprite(650, 243));
		this.enemies.add(new HittingEnemySprite(350, 179));
		this.enemies.add(new HittingEnemySprite(850, 131));
		this.enemies.add(new ShootingEnemySprite(410, 259, this.bullets));
		this.enemies.add(new ShootingEnemySprite(920, 259, this.bullets));
		this.enemies.add(new ShootingEnemySprite(899, 67, this.bullets));
		this.enemies.add(new ShootingEnemySprite(550, 52, this.bullets));
		this.enemies.add(new ShootingEnemySprite(300, 35, this.bullets));

		// initialize items group
		this.items = new GameSpriteGroup();

		// initialize items
		this.items.add(new SodaItemSprite(45, 294));
		this.items.add(new SodaItemSprite(225, 70));
		this.items.add(new SodaItemSprite(370, 294));
		this.items.add(new SodaItemSprite(1037, 294));
		this.items.add(new SodaItemSprite(650, 86));
		this.items.add(new HeartItemSprite(400, 70));
		this.items.add(new HeartItemSprite(455, 292));
		this.items.add(new HeartItemSprite(1039, 190));
		this.items.add(new HeartItemSprite(630, 276));

		// reposition character
		this.mainChar = new CharacterSprite(this, this.foreground, this.bullets);

		// set current level
		this.currentLevel = LEVEL1;

		// start music
		this.midlet.getMusic().playGame1Music();
	}

	/**
	 * Initialize level 2 objects
	 * @throws IOException
	 */
	public void initLevel2() throws IOException {
		// initialize static game objetcs
		this.foreground = new ForegroundLevel2();
		this.background = new BackgroundLevel2();
		this.endMarker = new EndMarkerSprite();

		// initialize bullets group
		this.bullets = new GameSpriteGroup();

		// initialize enemies group
		this.enemies = new GameSpriteGroup();

		// initialize enemies
		this.enemies.add(new HittingEnemySprite(100, 260));
		this.enemies.add(new HittingEnemySprite(920, 259));
		this.enemies.add(new HittingEnemySprite(850, 131));
		this.enemies.add(new HittingEnemySprite(899, 67));
		this.enemies.add(new HittingEnemySprite(550, 52));
		this.enemies.add(new HittingEnemySprite(240, 35));
		this.enemies.add(new ShootingEnemySprite(445, 85, this.bullets));
		this.enemies.add(new ShootingEnemySprite(500, 150, this.bullets));
		this.enemies.add(new ShootingEnemySprite(400, 200, this.bullets));
		this.enemies.add(new ShootingEnemySprite(670, 55, this.bullets));

		// initialize items group
		this.items = new GameSpriteGroup();

		// initialize items
		this.items.add(new SodaItemSprite(100, 147));
		this.items.add(new HeartItemSprite(400, 50));
		this.items.add(new HeartItemSprite(720, 294));
		this.items.add(new SodaItemSprite(90, 294));
		this.items.add(new SodaItemSprite(349, 294));
		this.items.add(new HeartItemSprite(723, 147));
		this.items.add(new HeartItemSprite(1039, 190));

		// reposition character
		this.mainChar = new CharacterSprite(this, foreground, bullets, this.mainChar.getLife());

		// set current level
		this.currentLevel = LEVEL2;

		// start music
		this.midlet.getMusic().playGame2Music();
	}

	/**
	 * Initialize level 3 objects
	 * @throws IOException
	 */
	public void initLevel3() throws IOException {
		// initialize static game objetcs
		this.foreground = new ForegroundLevel3();
		this.background = new BackgroundLevel3();
		this.endMarker = new EndMarkerSprite();

		// initialize bullets group
		this.bullets = new GameSpriteGroup();

		// initialize enemies group
		this.enemies = new GameSpriteGroup();

		// initialize enemies
		this.enemies.add(new HittingEnemySprite(100, 260));
		this.enemies.add(new HittingEnemySprite(920, 259));
		this.enemies.add(new HittingEnemySprite(899, 67));
		this.enemies.add(new HittingEnemySprite(550, 52));
		this.enemies.add(new HittingEnemySprite(200, 35));

		this.enemies.add(new ShootingEnemySprite(100, 145, this.bullets));
		this.enemies.add(new ShootingEnemySprite(445, 85, this.bullets));
		this.enemies.add(new ShootingEnemySprite(500, 150, this.bullets));
		this.enemies.add(new ShootingEnemySprite(400, 200, this.bullets));
		this.enemies.add(new ShootingEnemySprite(670, 55, this.bullets));
		this.enemies.add(new ShootingEnemySprite(223, 263, this.bullets));
		this.enemies.add(new ShootingEnemySprite(315, 263, this.bullets));
		this.enemies.add(new ShootingEnemySprite(860, 131, this.bullets));

		// initialize items group
		this.items = new GameSpriteGroup();

		// initialize items
		this.items.add(new SodaItemSprite(100, 147));
		this.items.add(new HeartItemSprite(540, 70));
		this.items.add(new SodaItemSprite(190, 250));
		this.items.add(new SodaItemSprite(290, 250));
		this.items.add(new HeartItemSprite(730, 160));
		this.items.add(new SodaItemSprite(865, 160));

		// reposition character
		this.mainChar = new CharacterSprite(this, foreground, bullets, this.mainChar.getLife());

		// set current level
		this.currentLevel = LEVEL3;

		// start music
		this.midlet.getMusic().playGame3Music();
	}

	/**
	 * Paints the game
	 * @param g
	 */
	public void paint(Graphics g) {
		// clear screen
		g.setColor(0);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// draw all game objects
		this.background.paint(g);
		this.foreground.paint(g);
		this.endMarker.paint(g);

		this.enemies.paint(g);
		this.bullets.paint(g);
		this.items.paint(g);

		this.mainChar.paint(g);

		// paint fps
		//this.fpsCounter.paint(g);

		// paint HUD
		this.paintHud(g);
	}

	/**
	 * Paints the hud like life, points, etc
	 * @param g
	 */
	private void paintHud(Graphics g) {
		g.setColor(0);

		g.drawString(this.points + "", 0, 0, Graphics.TOP | Graphics.LEFT);

		int life = this.mainChar.getLife();
		for (int i = 0; i < life; i++) {
			g.drawImage(this.lifeImg, this.getWidth() - (this.lifeImg.getWidth() * i), 0, Graphics.TOP | Graphics.RIGHT);
		}
	}

	/**
	 * Updates game input and physics
	 */
	public void update() {
		this.fpsCounter.update();

		this.handleInput();

		this.checkCharacterDamage();
		this.checkEnemyDamage();
		this.checkItems();

		this.checkWon();
		this.checkLost();

		this.removeOutOfRangeBullets();

		this.mainChar.update();
		this.enemies.update();
		this.bullets.update();
	}

	/**
	 * Handles input for the game
	 */
	private void handleInput() {
		int keys = this.getKeyStates();

		// pausing?
		if ((keys & GAME_A_PRESSED) != 0) {
			this.midlet.pauseGame();
		}

		if ((keys & LEFT_PRESSED) != 0) {
			this.mainChar.walkLeft();
		}
		else if ((keys & RIGHT_PRESSED) != 0) {
			this.mainChar.walkRight();
		}
		else {
			this.mainChar.idle();
		}

		if ((keys & UP_PRESSED) != 0) {
			if (!this.jumping) {
				this.mainChar.jump();
				this.jumping = true;
			}
		}
		else {
			this.jumping = false;
		}

		if ((keys & FIRE_PRESSED) != 0) {
			if (!this.attacking) {
				this.mainChar.attack();
				this.attacking = true;
			}
		}
		else {
			this.attacking = false;
		}
	}

	/**
	 * Checks if character has been hit by bullets or enemies
	 */
	public void checkCharacterDamage() {
		this.mainChar.defineCollisionRectangle(0, 0, this.mainChar.getWidth(), this.mainChar.getHeight());

		// collide with enemies
		EnemySprite enemy;
		int numEnemies = this.enemies.size();
		for (int i = 0; i < numEnemies; i++) {
			enemy = (EnemySprite) this.enemies.getSpriteAt(i);
			if (enemy.inScreen(this.getWidth(), this.getHeight()) && this.mainChar.collidesWith(enemy, true)) {
				this.mainChar.reduceLife();
			}
		}

		// collide with bullets
		BulletSprite bullet;
		int numBullets = this.bullets.size();
		for (int i = 0; i < numBullets; i++) {
			bullet = (BulletSprite) this.bullets.getSpriteAt(i);
			if (bullet instanceof EnemyBulletSprite && this.mainChar.collidesWith(bullet, true)) {
				this.mainChar.reduceLife();
			}
		}

		this.mainChar.resetCollisionRectangle();
	}

	/**
	 * Checks if enemies have been hit by bullets
	 */
	public void checkEnemyDamage() {
		EnemySprite enemy;
		BulletSprite bullet;

		for (int enemyIdx = 0; enemyIdx < this.enemies.size(); enemyIdx++) {
			enemy = (EnemySprite) this.enemies.getSpriteAt(enemyIdx);
			for (int bulletIdx = 0; bulletIdx < this.bullets.size(); bulletIdx++) {
				bullet = (BulletSprite) this.bullets.getSpriteAt(bulletIdx);
				if (enemy.inScreen(getWidth(), getHeight()) && bullet instanceof CharacterBulletSprite && enemy.collidesWith(bullet, true)) {
					this.bullets.removeSpriteAt(bulletIdx);
					bulletIdx--;

					this.enemies.removeSpriteAt(enemyIdx);
					enemyIdx--;

					this.points += 100;
				}
			}
		}
	}

	/**
	 * Checks if character has collided with items
	 * and uses them if necessary
	 */
	public void checkItems() {
		// set collision rectangle to character's rectangle
		this.mainChar.defineCollisionRectangle(0, 0, this.mainChar.getWidth(), this.mainChar.getHeight());

		// collide with items
		ItemSprite item;
		for (int itemIdx = 0; itemIdx < this.items.size(); itemIdx++) {
			// for every item in the group
			item = (ItemSprite) this.items.getSpriteAt(itemIdx);
			if (item.inScreen(getWidth(), getHeight()) && this.mainChar.collidesWith(item, true)) {
				// if item in scree and character collides with the item
				// add points
				this.points += item.getPoints();
				// recover life
				this.mainChar.recoverLife(item.getRecovery());

				// delete item from group
				this.items.removeSpriteAt(itemIdx);
				itemIdx--;
			}
		}

		// reset collision rectangle to default
		this.mainChar.resetCollisionRectangle();
	}

	/**
	 * Scrolls the game screen relative to the character
	 * @param dx - horizontal speed
	 * @param dy - vertical speed
	 */
	public void scroll(int dx, int dy) {
		horizontalScroll(dx);
		verticalScroll(dy);
	}

	/**
	 * Scrolls horizontally
	 * @param dx Distance to scroll
	 */
	public void horizontalScroll(int dx) {
		// get sprite horizontal position relative to the background
		int spritePosX = this.mainChar.getX() - this.foreground.getX();

		if (spritePosX < this.hWidth) {
			// if at first half screen
			if (mainChar.getX() >= 0 || dx > 0) {
				// if going right
				// move the character
				this.mainChar.move(dx, 0);
			}
		}
		else if (spritePosX > this.foreground.getWidth() - this.hWidth) {
			// if at last half screen
			if (spritePosX + this.mainChar.getWidth() <= this.foreground.getWidth() || dx < 0) {
				// if going left
				// move the character
				this.mainChar.move(dx, 0);
			}
		}
		else {
			// if in a center screen, just keep the character in the middle
			this.mainChar.setPosition(this.hWidth, this.mainChar.getY());

			// and move objects
			this.foreground.move(-dx, 0);
			this.background.move(-dx, 0);
			this.enemies.move(-dx, 0);
			this.bullets.move(-dx, 0);
			this.items.move(-dx, 0);
			this.endMarker.move(-dx, 0);
		}
	}

	/**
	 * Scrolls vertically
	 * @param dy Distance to scroll
	 */
	public void verticalScroll(int dy) {
		// get sprite vertical position relative to the foreground
		int spritePosY = this.mainChar.getY() - this.foreground.getY();

		if (spritePosY < this.hHeight) {
			// if at first half screen

			// since character can go above foreground, this test is unnecesary
			/*
			if (spritePosY >= 0 || dy > 0) {
			this.mainChar.move(0, dy);
			}
			 */

			// move the character
			this.mainChar.move(0, dy);
		}
		else if (spritePosY > this.foreground.getHeight() - this.hHeight) {
			// if at last half screen

			// since character can go bellow foreground, this test is unnecesary
			/*
			if (spritePosY + this.mainChar.getHeight() <= this.foreground.getHeight() || dy < 0) {
			this.mainChar.move(0, dy);
			}
			 */

			// move the caracter
			this.mainChar.move(0, dy);
		}
		else {
			// if in a center screen, just keep the character in the midle
			this.mainChar.setPosition(this.mainChar.getX(), this.hHeight);

			// and move objects
			this.foreground.move(0, -dy);
			this.background.move(0, -dy);
			this.enemies.move(0, -dy);
			this.items.move(0, -dy);
			this.bullets.move(0, -dy);
			this.endMarker.move(0, -dy);
		}
	}

	/**
	 * Removes bullets out of the screen range
	 */
	private void removeOutOfRangeBullets() {
		int i = 0;
		while (i < this.bullets.size()) {
			BulletSprite b = (BulletSprite) this.bullets.getSpriteAt(i);
			if (b.outsideScreen(this.getWidth())) {
				this.bullets.removeSpriteAt(i);
			}
			else {
				i++;
			}
		}
	}

	/**
	 * Checks if game has been lost and acts accordingly.
	 * Game is won when character touches end marker
	 * If game has been won and there is space in the high scores,
	 * as the user if he wants to add the score.
	 * Otherwise, just show the regular message.
	 */
	public void checkWon() {
		if (this.mainChar.collidesWith(this.endMarker, false)) {
			// if character has touched end marker

			if (currentLevel == LEVEL1 || currentLevel == LEVEL2) {
				// if not at last level, show level complete alert and go to next level.
				levelComplete();
			}
			else {
				// if at level 3, game is complete
				gameComplete();
			}
		}
	}

	/**
	 * Called by checkWon() when a not final level is finished
	 */
	private void levelComplete() {
		this.stop();
		// calculate score with time and life bonus
		int timeBonus = 1000 - ((int) (System.currentTimeMillis() - this.startTime) / 1000); // reduce 1 point for every second
		int lifeBonus = this.mainChar.getLife() * 100; // 100 points per life left
		int totalPoints = this.points + timeBonus + lifeBonus;
		// write the message
		StringBuffer wonMessage = new StringBuffer();
		wonMessage.append("Points: " + this.points + "\n");
		wonMessage.append("Time Bonus: " + timeBonus + "\n");
		wonMessage.append("Life Bonus: " + lifeBonus + "\n");
		wonMessage.append("Total: " + totalPoints);
		// total the points
		this.points += timeBonus + lifeBonus;
		// Show level complete alert
		Alert a = new Alert("Level Complete!!", wonMessage.toString(), null, AlertType.INFO);
		a.setTimeout(Alert.FOREVER);
		a.addCommand(new Command("Continue", Command.OK, 1));
		a.setCommandListener(new CommandListener() {

			public void commandAction(Command c, Displayable d) {
				if (c.getCommandType() == Command.OK) {
					Display.getDisplay(midlet).setCurrent(Game.this);
					
					try {
						if (currentLevel == LEVEL1) {
							initLevel2();
							start();
						}
						else {
							initLevel3();
							start();
						}
					}
					catch (IOException ex) {
					}
				}
			}
		});
		Display.getDisplay(midlet).setCurrent(a, this);
	}

	/**
	 * Called by checkWon() when the final level is finished
	 */
	private void gameComplete() {
		// stop the game
		this.stop();

		// calculate score with time and life bonus
		int timeBonus = 1000 - ((int) (System.currentTimeMillis() - this.startTime) / 1000); // reduce 1 point for every second
		int lifeBonus = this.mainChar.getLife() * 100; // 100 points per life left
		int totalPoints = this.points + timeBonus + lifeBonus;

		// write the message
		StringBuffer wonMessage = new StringBuffer();
		wonMessage.append("Points: " + this.points + "\n");
		wonMessage.append("Time Bonus: " + timeBonus + "\n");
		wonMessage.append("Life Bonus: " + lifeBonus + "\n");
		wonMessage.append("Total: " + totalPoints);

		// total the points
		this.points += timeBonus + lifeBonus;

		// can the score be added to high scores?
		// score can be added when this game's points are higher than the points
		// of the lowest ranked game in the store,
		// or if there are less than the maximum number of points in
		// the store
		boolean scoreCanBeAdded = this.points > this.midlet.getScores().getLowestScore() || this.midlet.getScores().size() < HighScoreStore.MAX_SCORES;

		if (scoreCanBeAdded) {
			// if yes, ask to user if he wants to add them
			wonMessage.append("\nHigh Score!! Register?");
			Alert a = new Alert("Game won!!", wonMessage.toString(), null, AlertType.INFO);

			a.setTimeout(Alert.FOREVER);
			a.addCommand(new Command("Yes", Command.OK, 1));
			a.addCommand(new Command("No", Command.CANCEL, 2));
			a.setCommandListener(new CommandListener() {

				public void commandAction(Command c, Displayable d) {
					if (c.getCommandType() == Command.OK) {
						Display.getDisplay(Game.this.midlet).setCurrent(new HighScoreAdder(Game.this.midlet, Game.this.points));
					}
					else if (c.getCommandType() == Command.CANCEL) {
						Game.this.midlet.startMainMenu();
					}
				}
			});
			Display.getDisplay(midlet).setCurrent(a, this);
		}
		else {
			// else, just display the won message
			Alert a = new Alert("Game won!!", wonMessage.toString(), null, AlertType.INFO);
			a.setTimeout(Alert.FOREVER);
			a.addCommand(new Command("Back", Command.OK, 1));
			a.setCommandListener(new CommandListener() {

				public void commandAction(Command c, Displayable d) {
					if (c.getCommandType() == Command.OK) {
						Game.this.midlet.startMainMenu();
					}
				}
			});
			Display.getDisplay(midlet).setCurrent(a, this);
		}
	}

	/**
	 * Checks if game has been lost and acts accordingly.
	 * Game is lost when either
	 * a. Character's life is less than or equal to 0
	 * b. Character is bellow the height of the foreground
	 */
	public void checkLost() {
		boolean lifeOver = this.mainChar.getLife() <= 0;
		boolean bellowForeground = this.mainChar.getY() > this.getHeight();

		if (lifeOver || bellowForeground) {
			this.stop();
			Alert a = new Alert("Game Lost", "", null, AlertType.INFO);
			a.setTimeout(Alert.FOREVER);
			a.addCommand(new Command("Back", Command.OK, 1));
			a.setCommandListener(new CommandListener() {

				public void commandAction(Command c, Displayable d) {
					if (c.getCommandType() == Command.OK) {
						Game.this.midlet.startMainMenu();
					}
				}
			});
			Display.getDisplay(midlet).setCurrent(a, this);
		}
	}
}
