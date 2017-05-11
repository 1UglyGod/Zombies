import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;
public class ZombiePanel extends JPanel {
	//Remember system properties in main
	private List<Zombie> enemies = new ArrayList();
	private Player myPlayer;
	private Timer t;
	public ZombiePanel(){
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		myPlayer = new Player(10, 20);
		setUpTimer();
	}
	
	private void setUpTimer() {
		// TODO Auto-generated method stub
		t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick(t);
				repaint();
			}

			
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		myPlayer.draw(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
	}
	
	private void tick(Timer t) {
		// TODO Auto-generated method stub
		
	}
	
}
