import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameLauncher {
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		JOptionPane.showMessageDialog(null, "Use 'Z' to melee, 'X' to shoot and the arrow keys to move around. \n100 points are awarded for each zombie killed. \nThe largest building is a safe zone for which there is a 10 second timer. \nYou can only melee for 2 seconds at a time. \nViolation of timing rules leads to a loss of health. \nPress OK to begin.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		JFrame myFrame = new JFrame("Survive!");
		ZombiePanel myPanel = new ZombiePanel();
		myFrame.setSize(1200, 800);
		myFrame.add(myPanel);
	//	myFrame.setResizable(false);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
