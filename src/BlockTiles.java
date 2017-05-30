import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;

public class BlockTiles extends MapObject {
	private static Image block;
	private int region;

	public BlockTiles(int x, int y, int region) {
		super(x, y);
		this.region = region;
		if (region == 1) {
			this.setDim(40, 40);
		} else {
			this.setDim(10, 10);
		}
		// this.setDim(50, 50);
		initImages();
	}

	private void initImages() {
		// TODO Auto-generated method stub
		if (this.region == 1) {
			try {
				URL url = getClass().getResource("res/BlockTile.png");
				block = ImageIO.read(url);
				this.setMyImage(block);
			} catch (Exception e) {
				System.out.println("Image could not be opened: " + "res/Tile.png");
				e.printStackTrace();
			}
		}
		if (this.region == 2) {
			try {
				URL url = getClass().getResource("res/BlockTile2.png");
				block = ImageIO.read(url);
				this.setMyImage(block);
			} catch (Exception e) {
				System.out.println("Image could not be opened: " + "res/Tile.png");
				e.printStackTrace();
			}
		}

	}

	public int getRegion() {
		return this.region;
	}

	@Override
	public Rectangle getRect() {
		if (region == 1) {
			return new Rectangle(x, y, 40, 40);
		}
		else {
			return new Rectangle(x, y, 10, 10);
		}
		//return new Rectangle(x, y, 40, 40);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(block, this.x, this.y, null);

	}
}