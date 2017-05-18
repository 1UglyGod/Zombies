import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

public class Zombie extends MapObject {
	private int direction = 0;
	private static Image WalkBack;
	private static Image WalkFront;
	private static Image WalkLeft;
	private static Image WalkRight;
	private final int moveSpeed = 1;
	public Zombie(int x, int y) {
		super(x, y);
		setDim(50, 50);
		// find way to reference a player
		initImages();
	}

	private void initImages() {
		if (WalkBack == null) {
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

	public void moveLeft(List<Zombie> z, List<MapObject> o) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x - moveSpeed, this.y, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		for(int i = 0; i < o.size(); i++){
			if(!this.equals(o.get(i))){
				if(new Rectangle(this.x - moveSpeed, this.y, 40, 40).intersects(new Rectangle(o.get(i).getX(), o.get(i).getY(), o.get(i).getWidth(), o.get(i).getHeight()))){
					x = false;
				}
			}
		}
		if (this.x > 0 && x) {
			this.x -= moveSpeed;
			direction = 1;
		}
	}

	public void moveRight(List<Zombie> z, List<MapObject> o) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x + moveSpeed, this.y, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		for(int i = 0; i < o.size(); i++){
			if(!this.equals(o.get(i))){
				if(new Rectangle(this.x + moveSpeed, this.y, 40, 40).intersects(new Rectangle(o.get(i).getX(), o.get(i).getY(), o.get(i).getWidth(), o.get(i).getHeight()))){
					x = false;
				}
			}
		}
		if (this.x < 1200 && x) {
			this.x += moveSpeed;
			direction = 3;
		}
	}

	public void moveUp(List<Zombie> z, List<MapObject> o) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x, this.y - moveSpeed, 40, 40).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		for(int i = 0; i < o.size(); i++){
			if(!this.equals(o.get(i))){
				if(new Rectangle(this.x, this.y - moveSpeed, 40, 40).intersects(new Rectangle(o.get(i).getX(), o.get(i).getY(), o.get(i).getWidth(), o.get(i).getHeight()))){
					x = false;
				}
			}
		}
		if (this.y > 0 && x) {
			this.y -= moveSpeed;
			direction = 2;
		}
	}

	public void moveDown(List<Zombie> z, List<MapObject> o) {
		boolean x = true;
		for(int i = 0; i < z.size(); i++){
			if(!this.equals(z.get(i))){
				if(new Rectangle(this.x, this.y + moveSpeed, 40, 50).intersects(new Rectangle(z.get(i).getX(), z.get(i).getY(), 40, 40))){
					x = false;
				}
			}
		}
		for(int i = 0; i < o.size(); i++){
			if(!this.equals(o.get(i))){
				if(new Rectangle(this.x, this.y + moveSpeed, 40, 50).intersects(new Rectangle(o.get(i).getX(), o.get(i).getY(), o.get(i).getWidth(), o.get(i).getHeight()))){
					x = false;
				}
			}
		}
		if (this.y < 750 && x) {
			this.y += moveSpeed;
			direction = 0;
		}
	}
	@Override
	public boolean equals(Object o){
		if(((MapObject) o).getX() == this.x && ((MapObject) o).getY() == this.y){
			return true;
		}
		return false;
	}
	
	public int getDirection(){
		return direction;
	}

}
