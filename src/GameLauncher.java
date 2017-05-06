import java.awt.Dimension;

import javax.swing.JFrame;


public class GameLauncher {
	public static void main(String[] args){
		JFrame myFrame = new JFrame("Survive!");
		ZombiePanel myPanel = new ZombiePanel();
		myFrame.setSize(1200, 800);
		myFrame.add(myPanel);
		myFrame.setVisible(true);
	}
}
