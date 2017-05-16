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
import javax.xml.stream.Location;
import java.util.Random;

public class ZombiePanel extends JPanel {
	// Remember system properties in main
	private List<Zombie> enemies;
	private List<MapObject> blockers;
	private Zombie enemie;
	private Player myPlayer;
	private Timer t;
	private boolean upPressed;
	private boolean downPressed;
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean spacePressed;
	Random rand = new Random();

	public ZombiePanel() {
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		myPlayer = new Player(10, 20);
		enemies = new ArrayList();
		blockers = new ArrayList();
		addEnemies();
		addBlockers();
		setUpTimer();
		setUpKeyBindings();
	}

	private void addBlockers() {
		// TODO Auto-generated method stub
		int num = (int) ((Math.random() * 10) + 1);
		for (int i = 0; i < num; i++){
			new Hut((int) (Math.random() * 1200), (int) (Math.random() * 800));
		}
	}

	private void addEnemies() {
		// TODO Auto-generated method stub

		for (int z = 0; z < 6; z++) {
			int randX = rand.nextInt(1150) + 1;
			System.out.println("randX: " + randX);
			int randY = rand.nextInt(750) + 5;
			System.out.println("randY: " + randY);
			enemie = new Zombie(randX, randY);
			enemies.add(enemie);
		}
		
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
				// create later
				repaint();
			}
		});

		this.getActionMap().put("endShoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create later
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
		// enemie.draw(g,myPlayer.getX(),myPlayer.getY());
		for (int i = 0; i < enemies.size(); i++) {
	
			enemies.get(i).draw(g, myPlayer.getX(), myPlayer.getY());
		}
		for (int i = 0; i < blockers.size(); i++){
			blockers.get(i).draw(g);
		}
	}

	private void tick(Timer t) {
		// TODO Auto-generated method stub
		if (upPressed) {
			myPlayer.moveUp();
		}
		if (downPressed) {
			myPlayer.moveDown();
		}
		if (rightPressed) {
			myPlayer.moveRight();
		}
		if (leftPressed) {
			myPlayer.moveLeft();
		}
		if (!leftPressed && !rightPressed && !downPressed && !upPressed) {
			myPlayer.setWalkingFalse();
		}
		for (int i = 0; i < enemies.size(); i++){
			zombieFollow(enemies.get(i));
		}
			//zombieFollow(enemies.get(i));
	}

	public void zombieFollow(Zombie z) {
		if(myPlayer.getX() < z.getX()){
			z.moveLeft();
		}else if(myPlayer.getX() > z.getX()){
			z.moveRight();
		}
		
		if(myPlayer.getY() < z.getY()){
			z.moveUp(); 
		}else if(myPlayer.getY() > z.getY()){
			z.moveDown();
		}
	}

}
