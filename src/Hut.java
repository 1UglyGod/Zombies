import java.awt.Image;


public class Hut extends MapObject{
	private static Image hutImage;
	public Hut(int x, int y){
		super(x, y);
		if(getMyImage() == null){
			initImage("Hut");
			System.out.println("hut image is null");
		}else{
			setMyImage(hutImage);
		}
	}
}
