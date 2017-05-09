import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;


public class Player extends MapObject{
	public Player (int x, int y){
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
			URL url = getClass().getResource("res/RamboFaceFront.png");
			FaceFront = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: res/RamboFaceFront.png");
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
			URL url = getClass().getResource("res/RamboFaceRight.png");
			FaceRight = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: res/RamboFaceRight.png");
			e.printStackTrace();
		}
	}
}
