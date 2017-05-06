import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class MapObject {
	private int x;
	private int y;
	protected Image myImage;
	
	public MapObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		g.drawImage(myImage, x, y, null);
	}
	
	protected void initImage(String src){
		try {
			URL url = getClass().getResource("res/" + src + ".png");
			myImage = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: " + "res/" + src + ".png");
			e.printStackTrace();
		}
	}
}
