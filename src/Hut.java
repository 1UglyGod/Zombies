import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;


public class Hut extends MapObject{
	private static Image hutImage;
	public Hut(int x, int y){
		super(x, y);
		setDim(145, 145);
		if(hutImage == null){
			try {
				URL url = getClass().getResource("res/Hut.png");
				hutImage = ImageIO.read(url);
				setMyImage(hutImage);
			} catch (Exception e) {
				System.out.println("Image could not be opened: " + "res/Hut.png");
				e.printStackTrace();
			}
		}else{
			setMyImage(hutImage);
		}
	}
	
//	@Override
//	public void draw(Graphics g){
//		try{
//			g.drawImage(hutImage, this.x, this.y, null);
//		}catch (Exception e){
//			try {
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//	}
}
