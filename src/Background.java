import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.net.URL;

import javax.imageio.ImageIO;




public class Background  {
	
	private Image myImage;

	private int height;
	private int width;
	private int x;
	private int y;
	final static public int SQUARE_SIZE=10;
	
	private int[][]grid;
		//private double dx;
	//private double dy;
	
//	private double moveScale;
	
	public Background(int x, int y) {
		//double ms
	this.x=x;
    this.y=y;	
	setDim(1200, 800);
	initImages();
	grid=new int[120][80];
	

	}
	



	public void setDim(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	private void initImages() {
			// TODO Auto-generated method stub
		try {
			URL url = getClass().getResource("res/zombieMapFinal.png");
			myImage = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened:res/skeleBack.png");
			e.printStackTrace();
		}
		}

	
	public void draw(Graphics g) {
		
		g.drawImage(myImage, x, y, 1200, 800, null);
		
	/*
		for(int y=0;y<grid[x].length;y++){
	//		
				g.fillRect(0,SQUARE_SIZE*y,1200,1);		
	//			g.clearRect(SQUARE_SIZE*x, SQUARE_SIZE*y, SQUARE_SIZE, SQUARE_SIZE);
	//		 
			}
		for(int x=0;x<grid.length;x++){
			g.fillRect(SQUARE_SIZE*x,0,1,800);		
		}*/
	

	}
	

	
}



