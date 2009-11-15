package game.sprites;

import java.io.IOException;
import javax.microedition.lcdui.Image;

public class ImageHelper {

	public static void initImages() {
		try {
			CharacterSprite.img = Image.createImage(CharacterSprite.imgPath);
			ShootingEnemySprite.img = Image.createImage(ShootingEnemySprite.imgPath);
			HittingEnemySprite.img = Image.createImage(HittingEnemySprite.imgPath);

			CharacterBulletSprite.img = Image.createImage(CharacterBulletSprite.imgPath);
			EnemyBulletSprite.img = Image.createImage(EnemyBulletSprite.imgPath);

			HeartItemSprite.img = Image.createImage(HeartItemSprite.imgPath);
			SodaItemSprite.img = Image.createImage(SodaItemSprite.imgPath);

			EndMarkerSprite.img = Image.createImage(EndMarkerSprite.imgPath);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
