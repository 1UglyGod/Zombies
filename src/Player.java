import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player extends MapObject {
	private int direction = 0; // 0 = front, 1 = left, 2 = back, 3 = right
	private int moveSpeed = 8;
	private int health = 15;
	private static Image FaceBack;
	private static Image FaceFront;
	private static Image FaceLeft;
	private static Image FaceRight;
	private boolean walking = false;
	private boolean melee = false;
	private static Image WalkBack;
	private static Image WalkFront;
	private static Image WalkLeft;
	private static Image WalkRight;
	private static Image MeleeBack;
	private static Image MeleeFront;
	private static Image MeleeRight;
	private static Image MeleeLeft;
	private static final int SIZE=40;


	public Player(int x, int y) {
		super(x, y);
		setDim(50, 50);
		initImages();
	}

	private void initImages() {
		if (FaceFront == null) {
			try {
				URL url = getClass().getResource("res/RamboFaceBack.png");
				FaceBack = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboFaceBack.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboWalkBack.png");
				WalkBack = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboWalkBack.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboFaceFront.png");
				FaceFront = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboFaceFront.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboWalkFront.png");
				WalkFront = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboWalkFront.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboFaceLeft.png");
				FaceLeft = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboFaceLeft.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboWalkLeft.png");
				WalkLeft = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboWalkLeft.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboFaceRight.png");
				FaceRight = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboFaceRight.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboWalkRight.png");
				WalkRight = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboWalkRight.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboMacheteRight.png");
				MeleeRight = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboMacheteRight.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboMacheteLeft.png");
				MeleeLeft = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboMacheteLeft.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboMacheteFront.png");
				MeleeFront = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboMacheteFront.png");
				e.printStackTrace();
			}
			try {
				URL url = getClass().getResource("res/RamboMacheteBack.png");
				MeleeBack = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println("Image could not be opened: res/RamboMacheteBack.png");
				e.printStackTrace();
			}

		}
	}

	public void draw(Graphics g) {
		switch (direction) {
		case 0:
			if (!walking && !melee) {
				g.drawImage(FaceFront, x, y, SIZE, SIZE, null);
			} else if (melee) {
				g.drawImage(MeleeFront, x, y, SIZE, SIZE, null);
			} else {
				g.drawImage(WalkFront, x, y, SIZE, SIZE, null);
			}
			break;
		case 1:
			if (!walking && !melee) {
				g.drawImage(FaceLeft, x, y, SIZE, SIZE, null);
			} else if (melee) {
				g.drawImage(MeleeLeft, x, y, SIZE, SIZE, null);
			} else {
				g.drawImage(WalkLeft, x, y, SIZE, SIZE, null);
			}
			break;
		case 2:
			if (!walking && !melee) {
				g.drawImage(FaceBack, x, y, SIZE, SIZE, null);
			} else if (melee) {
				g.drawImage(MeleeBack, x, y, SIZE, SIZE, null);
			} else {
				g.drawImage(WalkBack, x, y, SIZE, SIZE, null);
			}
			break;
		case 3:
			if (!walking && !melee) {
				g.drawImage(FaceRight, x, y, SIZE, SIZE, null);
			} else if (melee) {
				g.drawImage(MeleeRight, x, y, SIZE, SIZE, null);
			} else {
				g.drawImage(WalkRight, x, y, SIZE, SIZE, null);
			}
			break;
		default:
			System.err.println("Error drawing player");
			break;
		}
	}

	
	
	public void moveLeft(List<BlockTiles> t) {
		boolean walk=true;
		for (int i = 0; i < t.size(); i++) {
			if (!this.equals(t.get(i))) {
					if (new Rectangle((this.x - moveSpeed), this.y, 40, 40)
					.intersects(t.get(i).getRect())) {
					walk = false;
				}
			}
		}
				
		if (x > 0&&walk) {
			x -= moveSpeed;
			walking = true;
			direction = 1;
		}
	}

	public void moveRight(List<BlockTiles> t) {
		boolean walk=true;
		for (int i = 0; i < t.size(); i++) {
			if (!this.equals(t.get(i))) {
				if (new Rectangle((this.x + moveSpeed), this.y, 40, 40)
				.intersects(t.get(i).getRect())) {
					walk = false;
				}
			}
		}
		
		if (x < 1160&&walk) {
			x += moveSpeed;
			walking = true;
			direction = 3;
		}
	}

	public void moveUp(List<BlockTiles> t) {
		boolean walk=true;
		for (int i = 0; i < t.size(); i++) {
			if (!this.equals(t.get(i))) {
				if (new Rectangle(this.x,(this.y-moveSpeed), 40, 40)
				.intersects(t.get(i).getRect())) {
					walk = false;
				}
			}
		}
		
		if (y > 5&&walk) {
			y -= moveSpeed;
			walking = true;
			direction = 2;
		}
	}

	public void moveDown(List<BlockTiles> t) {
		boolean walk=true;
		for (int i = 0; i < t.size(); i++) {
			if (!this.equals(t.get(i))) {
				if (new Rectangle(this.x, (this.y+ moveSpeed), 40, 40)
				.intersects(t.get(i).getRect())) {
					walk = false;
				}
			}
		}
		
		if (y < 735&&walk) {
			y += moveSpeed;
			walking = true;
			direction = 0;
		}
	}
	 

	
	public boolean walking() {
		return walking;
	}

	public int getDirection() {
		return direction;
	}

	public void setWalkingFalse() {
		walking = false;
	}

	public void gotHit() {
		health--;
	}

	public void replenishHealth() {
		health++;
	}

	public int getHealth() {
		return health;
	}

	public Rectangle melee() {
		// TODO Auto-generated method stub
		if (direction == 0) {
			return new Rectangle(this.x, this.y + 50, getWidth(), 35);
		} else if (direction == 1) {
			return new Rectangle(this.x - 35, this.y, 35, getHeight());
		} else if (direction == 2) {
			return new Rectangle(this.x, this.y - 35, getWidth(), 35);
		} else if (direction == 3) {
			return new Rectangle(this.x + 50, this.y, 35, getHeight());
		} else {
			return null;
		}
	}

	public void setMeleeTrue() {
		// TODO Auto-generated method stub
		moveSpeed = 1;
		melee = true;
	}

	public void setMeleeFalse() {
		// TODO Auto-generated method stub
		moveSpeed = 5;
		melee = false;
	}
	public boolean getMeleeStatus(){
		return melee;
	}
}
