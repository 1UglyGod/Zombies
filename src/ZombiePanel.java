import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.xml.stream.Location;
import java.util.Random;

public class ZombiePanel extends JPanel {
	// Remember system properties in main
	private List<Zombie> enemies;
	private int hitTime = 0;
	private List<MapObject> blockers;
	private Weapon bullets = new Weapon();
	private Player myPlayer;
	private Timer t;
	private boolean upPressed;
	private boolean downPressed;
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean spacePressed;
	private int level = 1;
	private int score = 0;
	private Random rand = new Random();

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
		for (int i = 0; i < num; i++) {
			Hut temp = new Hut((int) (Math.random() * 1050), (int) (Math.random() * 650));
			boolean toAdd = true;
			for (int j = 0; j < blockers.size(); j++) {
				if (temp.getRect().intersects(blockers.get(j).getRect())) {
					toAdd = false;
					break;
				}
			}
			if (toAdd) {
				blockers.add(temp);
			}
		}

	}

	private void addEnemies() {
		// TODO Auto-generated method stub
		int val = 6 + level * 2;
		for (int z = 0; z < val; z++) {
			int randX = rand.nextInt(1150) + 1;
			int randY = rand.nextInt(750) + 5;
			Zombie enemy = new Zombie(randX, randY);
			boolean toAdd = true;
			for (int j = 0; j < blockers.size(); j++) {
				if (enemy.getRect().intersects(blockers.get(j).getRect())) {
					toAdd = false;
					val++;
					break;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				if (enemy.getRect().intersects(enemies.get(i).getRect())) {
					toAdd = false;
					val++;
					break;
				}
			}
			if (toAdd) {
				enemies.add(enemy);
			}
		}

	}

	private void setUpKeyBindings() {
		// TODO Auto-generated method stub

		this.getInputMap().put(KeyStroke.getKeyStroke("pressed SPACE"), "beginShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "endShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "beginUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "endUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"), "beginDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "endDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "beginRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "endRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "beginLeft");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "endLeft");

		this.getActionMap().put("beginShoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shoot();
				repaint();
			}

			private void shoot() {
				// TODO Auto-generated method stub
				bullets.add(new Bullet(myPlayer.getX() + 25, myPlayer.getY() + 25, myPlayer.getDirection()));
				bullets.removeAmmo();
			}
		});

		this.getActionMap().put("endShoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

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
		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).draw(g, myPlayer.getX(), myPlayer.getY());
		}
		for (int i = 0; i < blockers.size(); i++) {
			blockers.get(i).draw(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		myPlayer.draw(g);
		g.setColor(Color.white);
		g.drawString("Score: " + score, 1000, 10);
		g.drawString("Level: " + level, 1000, 25);
		g.drawString("Ammo: " + bullets.showCapacity(), 1000, 40);
		g.drawString("Lives: " + myPlayer.getHealth(), 1000, 55);

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
		if (spacePressed) {

		}
		for (int i = 0; i < enemies.size(); i++) {
			zombieFollow(enemies.get(i));
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
		}
		// bullet vs bounds of game
		for (int i = bullets.size() - 1; i >= 0; i--) {
			if (bullets.get(i).getX() <= 0 || bullets.get(i).getX() > 1200) {
				bullets.remove(i);
			} else if (bullets.get(i).getY() <= 0 || bullets.get(i).getY() > 800) {
				bullets.remove(i);
			}
		}
		// bullet vs zombies
		for (int i = bullets.size() - 1; i >= 0; i--) {
			for (int j = enemies.size() - 1; j >= 0; j--) {
				if (i < bullets.size() && j < enemies.size()) {
					Rectangle tempBull = bullets.get(i).getRect();
					Rectangle tempEnem = enemies.get(j).getRect();
					if (tempBull.intersects(tempEnem)) {
						score += 100;
						enemies.remove(j);
						bullets.remove(i);
					}
				}

			}
		}
		// player vs zombie
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getRect().intersects(myPlayer.getRect())) {
				int dir = enemies.get(i).getDirection();
				if (dir == 0) {
					for (int z = 0; z < 5; z++)
						enemies.get(i).moveUp(enemies, blockers);
				} else if (dir == 1) {
					for (int z = 0; z < 5; z++)
						enemies.get(i).moveRight(enemies, blockers);
				} else if (dir == 2) {
					for (int z = 0; z < 5; z++)
						enemies.get(i).moveDown(enemies, blockers);
				} else if (dir == 3) {
					for (int z = 0; z < 5; z++)
						enemies.get(i).moveLeft(enemies, blockers);
				}
				myPlayer.gotHit();
			}
		}
		if (myPlayer.getHealth() <= 0) {
			t.stop();
			JOptionPane.showMessageDialog(null, "Score: " + score + "\nLevel: " + level, "You Lose!",
					JOptionPane.ERROR_MESSAGE);
		}
		incrementTimes();
	}

	private void incrementTimes() {
		// TODO Auto-generated method stub
		// hitTime++;
	}

	public void zombieFollow(Zombie z) {
		if (myPlayer.getX() < z.getX()) {
			z.moveLeft(enemies, blockers);
		} else if (myPlayer.getX() > z.getX()) {
			z.moveRight(enemies, blockers);
		}

		if (myPlayer.getY() < z.getY()) {
			z.moveUp(enemies, blockers);
		} else if (myPlayer.getY() > z.getY()) {
			z.moveDown(enemies, blockers);
		}
	}

}
