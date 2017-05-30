import java.awt.Graphics;

public class BlockTile extends Hut {
	public BlockTile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getMyImage(), this.x, this.y, this.width, this.height, null);
	}
}
