import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class ZombiePanel extends JPanel {
	// Remember system properties in main
	private List<Zombie> enemies = new ArrayList();
	private Player myPlayer;
	private Timer t;
	private boolean upPressed;
	private boolean downPressed;
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean spacePressed;

	public ZombiePanel() {
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		myPlayer = new Player(10, 20);
		setUpTimer();
		setUpKeyBindings();
	}

	private void setUpKeyBindings() {
		// TODO Auto-generated method stub

		this.getInputMap().put(KeyStroke.getKeyStroke("pressed SPACE"),
				"beginShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"),
				"endShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "beginUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "endUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"),
				"beginDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"),
				"endDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"),
				"beginRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"),
				"endRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"),
				"beginLeft");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"),
				"endLeft");

		this.getActionMap().put("beginShoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//create later
				repaint();
			}
		});

		this.getActionMap().put("endShoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//create later
				repaint();
			}
		});

		this.getActionMap().put("beginUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upPressed = true;
				repaint();
			}
		});

		this.getActionMap().put("endUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upPressed = false;
				repaint();
			}
		});

		this.getActionMap().put("beginDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				downPressed = true;
				repaint();
			}
		});

		this.getActionMap().put("endDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				downPressed = false;
				repaint();
			}
		});

		this.getActionMap().put("beginRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPressed = true;
				repaint();
			}
		});

		this.getActionMap().put("endRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPressed = false;
				repaint();
			}
		});

		this.getActionMap().put("beginLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftPressed = true;
				repaint();
			}
		});

		this.getActionMap().put("endLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftPressed = false;
				repaint();
			}
		});

		this.requestFocusInWindow();

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
		t.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		myPlayer.draw(g);
		for (int i = 0; i < enemies.size(); i++) {System.out.println("tick!");
			enemies.get(i).draw(g);
		}
	}

	private void tick(Timer t) {
		// TODO Auto-generated method stub
		if(upPressed){
			myPlayer.moveUp();
		}
		if(downPressed){
			myPlayer.moveDown();
		}
		if(rightPressed){
			myPlayer.moveRight();
		}
		if(leftPressed){
			myPlayer.moveLeft();
		}
		if(!leftPressed && !rightPressed && !downPressed && !upPressed){
			myPlayer.setWalkingFalse();
		}
	}

}
