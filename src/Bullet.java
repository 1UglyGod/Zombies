import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Bullet extends MapObject {
	private static Image bulletImage;
	private int direction;
	private int moveSpeed = 7;

	public Bullet(int x, int y, int dir) {
		super(x, y);
		direction = dir;
		setDim(5, 5);
		if (bulletImage == null) {
			try {
				URL url = getClass().getResource("res/Bullet.png");
				bulletImage = ImageIO.read(url);
				setMyImage(bulletImage);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/Bullet.png");
				e.printStackTrace();
			}
		} else {
			setMyImage(bulletImage);
		}
	}

	public void move() {
		switch (direction) {
		case 0:
			y += moveSpeed;
			break;
		case 1:
			x -= moveSpeed;
			break;
		case 2:
			y -= moveSpeed;
			break;
		case 3:
			x += moveSpeed;
			break;
		default:
			System.err.println("Error moving bullet");
			break;
		}

	}
}
