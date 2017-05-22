import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

import javax.imageio.ImageIO;
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
	private Timer t, t2, t3;
	private boolean upPressed;
	private boolean downPressed;
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean meleePressed;
	private boolean checkStart = false;
	private boolean checkStartMelee = false;
	private int level = 1;
	private int score = 0;
	private int hutTime = 10;
	private int meleeTime = 2;
	private Random rand = new Random();
	private Image map;
	public ZombiePanel() {
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		myPlayer = new Player(10, 20);
		enemies = new ArrayList();
		blockers = new ArrayList();
		openMap();
		addEnemies();
		addBlockers();
		setUpTimer();
		setUpKeyBindings();
	}

	private void openMap() {
		// TODO Auto-generated method stub
		try {
			URL url = getClass().getResource("res/map.png");
			map = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: res/map.png");
			e.printStackTrace();
		}
	}

	private void addBlockers() {
		// TODO Auto-generated method stub
		int num = (int) ((Math.random() * 10) + 1);
		for (int i = 0; i < num; i++) {
			Hut temp = new Hut((int) (Math.random() * 1050), (int) (Math.random() * 550) + 100);
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
			int randX = rand.nextInt(1200);
			int randY = rand.nextInt(800);
			Zombie enemy = new Zombie(randX, randY);
			boolean toAdd = true;
			// for (int j = 0; j < blockers.size(); j++) {
			// if (enemy.getRect().intersects(blockers.get(j).getRect())) {
			// toAdd = false;
			// val++;
			// break;
			// }
			// }
			// for (int i = 0; i < enemies.size(); i++) {
			// if (enemy.getRect().intersects(enemies.get(i).getRect())) {
			// toAdd = false;
			// val++;
			// break;
			// }
			// }
			if (toAdd) {
				enemies.add(enemy);
			}
		}

	}

	private void setUpKeyBindings() {
		// TODO Auto-generated method stub
		//this.getInputMap().put(KeyStroke.getKeyStroke("shift"), "beginMelee");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed X"), "beginMelee");
		this.getInputMap().put(KeyStroke.getKeyStroke("released X"), "endMelee");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed Z"), "beginShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("released Z"), "endShoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "beginUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "endUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"), "beginDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "endDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "beginRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "endRight");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "beginLeft");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "endLeft");
		
		this.getActionMap().put("beginMelee", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				meleePressed = true;
			}
		});
		
		this.getActionMap().put("endMelee", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				meleePressed = false;
			}
		});
		
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
		t2 = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hutTime--;
				if (hutTime < 0) {
					myPlayer.gotHit();
				}
			}
		});
		t3 = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				meleeTime--;
				if (meleeTime < 0) {
					myPlayer.gotHit();
				}
			}
		});
		t.start();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(map, 0, 0, null);
		
		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).draw(g, myPlayer.getX(), myPlayer.getY());
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		myPlayer.draw(g);
		for (int i = 0; i < blockers.size(); i++) {
			blockers.get(i).draw(g);
		}
		Font font = new Font("Verdana", Font.BOLD, 12);
		g.setColor(Color.white);
		g.drawString("Score: " + score, 1000, 10);
		g.drawString("Level: " + level, 1000, 25);
		g.drawString("Ammo: " + bullets.showCapacity(), 1000, 40);
		g.drawString("Health: " + myPlayer.getHealth(), 1000, 55);
		if (checkStart) {
			g.drawString("Time Left in Safe Zone: " + hutTime, 1000, 70);
		}
		if(checkStartMelee && !checkStart){
			g.drawString("Time Left to Melee: " + meleeTime, 1000, 70);
		}else if(checkStartMelee){
			g.drawString("Time Left to Melee: " + meleeTime, 1000, 85);
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
		if (meleePressed) {
			melee();
			myPlayer.setMeleeTrue();
		}else{
			myPlayer.setMeleeFalse();
		}
		for (int i = 0; i < enemies.size(); i++) {
			zombieFollow(enemies.get(i));
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
		}
		// bullet vs bounds of game
		boundBullets();
		// bullet vs zombies
		bulletVSZombies();
		// player vs zombie
		playerVSZombie();
		// player/house relationship
		playerInHouse();
		//check if player is meleeing too long
		playerIsMelee();
		checkHealth();
		incrementTimes();
		if (enemies.size() < 1) {
			newLevel();
		}
	}

	private void playerInHouse() {
		// TODO Auto-generated method stub
		if (!checkStart) {
			for (int i = 0; i < blockers.size(); i++) {
				if (blockers.get(i).getRect().intersects(myPlayer.getRect())) {
					hutTime = 10;
					t2.start();
					checkStart = true;
					break;

				}
			}
		} else {
			boolean checkToEnd = true;
			for (int i = 0; i < blockers.size(); i++) {
				if (blockers.get(i).getRect().intersects(myPlayer.getRect())) {
					checkToEnd = false;
					break;

				}
			}
			if (checkToEnd) {
				t2.stop();
				checkStart = false;
			}
		}
	}
	
	public void playerIsMelee(){
		if(myPlayer.getMeleeStatus() && !checkStartMelee){
			meleeTime = 2;
			t3.start();
			checkStartMelee = true;
		}
		
		if(!myPlayer.getMeleeStatus() && checkStartMelee){
			t3.stop();
			checkStartMelee = false;
		}
	}

	private void bulletVSZombies() {
		// TODO Auto-generated method stub
		for (int i = bullets.size() - 1; i >= 0; i--) {
			for (int j = enemies.size() - 1; j >= 0; j--) {
				if (i < bullets.size() && j < enemies.size()) {
					Rectangle tempBull = bullets.get(i).getRect();
					Rectangle tempEnem = enemies.get(j).getRect();
					if (tempBull.intersects(tempEnem)) {
						score += 100;
						bullets.remove(i);
						enemies.remove(j);

					}
				}

			}
		}
	}

	private void checkHealth() {
		// TODO Auto-generated method stub
		if (myPlayer.getHealth() <= 0) {
			t.stop();
			JOptionPane.showMessageDialog(null, "Score: " + score + "\nLevel: " + level, "You Lose!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void boundBullets() {
		// TODO Auto-generated method stub
		for (int i = bullets.size() - 1; i >= 0; i--) {
			if (bullets.get(i).getX() <= 0 || bullets.get(i).getX() > 1200) {
				bullets.remove(i);
			} else if (bullets.get(i).getY() <= 0 || bullets.get(i).getY() > 800) {
				bullets.remove(i);
			}
		}
	}

	private void playerVSZombie() {
		// TODO Auto-generated method stub
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (enemies.get(i).getRect().intersects(myPlayer.getRect())) {
				// enemies.get(i).knockBack();
				enemies.remove(i);
				myPlayer.gotHit();
			}
		}
	}

	private void newLevel() {
		// TODO Auto-generated method stub
		level++;
		addEnemies();
		bullets.addAmmo(level);
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

	public void melee() {
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (myPlayer.melee().intersects(enemies.get(i).getRect())) {
				enemies.remove(i);
				score += 100;
			}
		}
	}

}
