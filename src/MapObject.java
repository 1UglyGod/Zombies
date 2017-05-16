import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class MapObject {
	protected int x;
	protected int y;
	
	private Image myImage;
	

	public MapObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g, Image thisImage) {
		g.drawImage(thisImage, x, y, null);
	}
	
	public void draw(Graphics g){
		g.drawImage(myImage, x, y, null);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public Image getMyImage(){
		return myImage;
	}

	public void setMyImage(Image i){
		myImage = i;
	}
	protected void initImage(String src) {
		try {
			URL url = getClass().getResource("res/" + src + ".png");
			myImage = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: " + "res/" + src
					+ ".png");
			e.printStackTrace();
		}
	}
}
