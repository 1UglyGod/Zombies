import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.List;

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

	public void moveLeft(List<Zombie> z) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x - 1, this.y, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		if (this.x > 0 && x) {
			this.x -= 1;

		}
	}

	public void moveRight(List<Zombie> z) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x + 1, this.y, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		if (this.x < 1200 && x) {
			this.x += 1;

		}
	}

	public void moveUp(List<Zombie> z) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x, this.y - 1, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		if (this.y > 0 && x) {
			this.y -= 1;

		}
	}

	public void moveDown(List<Zombie> z) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x, this.y + 1, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		if (this.y < 800 && x) {
			this.y += 1;
		}
	}
	@Override
	public boolean equals(Object o){
		if(((Zombie) o).getX() == this.x && ((Zombie) o).getY() == this.y){
			return true;
		}
		return false;
	}

}
