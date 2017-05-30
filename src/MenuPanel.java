import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

//import MenuPanel.Click;



public class MenuPanel extends JPanel {
	
	private JButton start;
	//JButton storyline;
	
	
	public MenuPanel() {
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		this.addMouseListener(new Click());
	}
	
	private class Click extends MouseAdapter {
		private Color background;

		public Click() {
			super();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// put knight at pressed location in 2d array

			int row = (e.getY());
			int col = (e.getX());

			System.out.println(col+ "," + row);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// kill mouse listener
			System.out.println("yup");
		}
	}
}
