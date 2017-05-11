import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Player extends MapObject {
	private int direction = 0; // 0 = front, 1 = left, 2 = back, 3 = right
	private boolean walking = false;
 
	public Player(int x, int y) {
		super(x, y);
		initImages();
	}

	private void initImages() {
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
	}

	public void draw(Graphics g) {
		switch (direction) {
		case 0:
			if(!walking){
				g.drawImage(FaceFront, x, y, null);
			}else{
				g.drawImage(WalkFront, x , y, null);
			}
			break;
		case 1:
			if(!walking){
				g.drawImage(FaceLeft, x, y, null);
			}else{
				g.drawImage(WalkLeft, x, y, null);
			}
			break;
		case 2:
			if(!walking){
				g.drawImage(FaceBack, x, y, null);
			}else{
				g.drawImage(WalkBack, x, y, null);
			}
			break;
		case 3:
			if(!walking){
				g.drawImage(FaceRight, x, y, null);
			}else{
				g.drawImage(WalkRight, x, y, null);
			}
			break;
		default:
			System.err.println("Error drawing player");
			break;
		}
	}
	
	public void moveLeft(){
		if(x > 50){
			x -= 5;
			walking = true;
			direction = 1;
		}
	}
	
	public void moveRight(){
		if(x < 1150){
			x += 5;
			walking = true;
			direction = 3;
		}
	}
	
	public void moveUp(){
		if(y > 50){
			y -= 5;
			walking = true;
			direction = 2;
		}
	}
	
	public void moveDown(){
		if(y < 750){
			y += 5;
			walking = true;
			direction = 0;
		}
	}
	
	public boolean walking(){
		return walking;
	}
	
	public void setWalkingFalse(){
		walking = false;
	}
}
