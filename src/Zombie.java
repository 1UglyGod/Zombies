import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Zombie extends MapObject {
	private int count = 0;
	private int direction = 0;
	private static Image WalkBack;
	private static Image WalkFront;
	private static Image WalkLeft;
	private static Image WalkRight;
	public Zombie(int x, int y) {
		super(x, y);
		// find way to reference a player
		initImages();
	}

	private void initImages() {
		if (WalkBack == null) {
			count++;
			System.out.println("Loading zombie images..." + count);
			try {
				URL url = getClass().getResource("res/skeleBack.png");
				WalkBack = ImageIO.read(url);
			} catch (Exception e) {
				System.out
						.println("Image could not be opened:res/skeleBack.png");
				e.printStackTrace();
			}

			try {
				URL url = getClass().getResource("res/skeleFront.png");
				WalkFront = ImageIO.read(url);
			} catch (Exception e) {
				System.out
						.println("Image could not be opened:res/skeleFront.png");
				e.printStackTrace();
			}

			try {
				URL url = getClass().getResource("res/skeleLeft.png");
				WalkLeft = ImageIO.read(url);
			} catch (Exception e) {
				System.out
						.println("Image could not be opened:res/skeleLeft.png");
				e.printStackTrace();
			}

			try {
				URL url = getClass().getResource("res/skeleRight.png");
				WalkRight = ImageIO.read(url);
			} catch (Exception e) {
				System.out
						.println("Image could not be opened:res/skeleRight.png");
				e.printStackTrace();
			}
		}
	}

	public void draw(Graphics g, int x, int y) {
		if (Math.abs(y - this.getY()) > Math.abs(x - this.getX())) {
			if (y > this.getY()) {
				g.drawImage(WalkFront, this.x, this.y, null);

			} else {
				g.drawImage(WalkBack, this.x, this.y, null);
			}
		}

		if (Math.abs(y - this.getY()) < Math.abs(x - this.getX())) {
			if (x > this.getX()) {
				g.drawImage(WalkRight, this.x, this.y, null);
			} else {
				g.drawImage(WalkLeft, this.x, this.y, null);
			}
		}

	}

	public void moveLeft() {
		if (this.x > 0) {
			this.x -= 1;

		}
	}

	public void moveRight() {
		if (this.x < 1200) {
			this.x += 1;

		}
	}

	public void moveUp() {
		if (this.y > 0) {
			this.y -= 1;

		}
	}

	public void moveDown() {
		if (this.y < 800) {
			this.y += 1;
		}
	}

}
