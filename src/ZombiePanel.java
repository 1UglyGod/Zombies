import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
public class ZombiePanel extends JPanel {
	//Remember system properties in main
	private List<Zombie> enemies = new ArrayList();
	private Player myPlayer;
	
	public ZombiePanel(){
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		myPlayer = new Player(10, 20);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		myPlayer.draw(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
	}
}
