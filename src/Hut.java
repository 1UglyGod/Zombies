import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Hut extends MapObject {
	private Image hutImage;
	public Hut(int x, int y, int width, int height) {
		super(x, y);
		setDim(width, height);
		try {
			URL url = getClass().getResource("res/TempTile.png");
			hutImage = ImageIO.read(url);
			setMyImage(hutImage);
		} catch (Exception e) {
			System.out.println("Image could not be opened: " + "res/Hut.png");
			e.printStackTrace();
		}
		// if(hutImage == null){
		// try {
		// URL url = getClass().getResource("res/Hut.png");
		// hutImage = ImageIO.read(url);
		// setMyImage(hutImage);
		// } catch (Exception e) {
		// System.out.println("Image could not be opened: " + "res/Hut.png");
		// e.printStackTrace();
		// }
		// }else{
		// setMyImage(hutImage);
		// }
	}

	@Override
	public void draw(Graphics g) {
		try {
			g.drawImage(hutImage, this.x, this.y,this.width, this.height, null);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
