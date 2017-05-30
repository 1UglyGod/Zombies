import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private List<BlockTiles> tiles;
	private List<MapObject> blockers;
	private int hitTime = 0;
	private Weapon bullets = new Weapon();
	private Player myPlayer;
	private Background map;

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

	public ZombiePanel() {
		this.setSize(1200, 800);
		this.setBackground(Color.black);
		map = new Background(0, 0);
		myPlayer = new Player(900, 500);
		enemies = new ArrayList();
		blockers = new ArrayList();
		tiles = new ArrayList();
		// tile = new BlockTiles(0,0);
		addBlockTiles();
		addBlockers();
		addEnemies();
		setUpTimer();
		setUpKeyBindings();
		//this.addMouseListener(new Click());
	}

//	private class Click extends MouseAdapter {
//		private Color background;
//
//		public Click() {
//			super();
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// put knight at pressed location in 2d array
//
//			int row = (e.getY()) / 10;
//			int col = (e.getX()) / 10;
//
//			System.out.println(col + "," + row);
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// kill mouse listener
//
//		}
//	}

	private void addBlockers() {
		// TODO Auto-generated method stub
		blockers.add(new Hut(865, 13, 330, 260));

		//blockers.add(new Hut(10, 10, 10,10));
//		int num = (int) ((Math.random() * 10) + 1);
//		for (int i = 0; i < num; i++) {
//			Hut temp = new Hut((int) (Math.random() * 1050),
//					(int) (Math.random() * 550) + 100);
//			boolean toAdd = true;
//			for (int j = 0; j < blockers.size(); j++) {
//				if (temp.getRect().intersects(blockers.get(j).getRect())) {
//					toAdd = false;
//					break;
//				}
//			}
//			if (toAdd) {
//				// blockers.add(temp);
//			}
//		}

	}

	private void addEnemies() {
		// TODO Auto-generated method stub

		int val = 6 + level * 4;

        for (int z = 0; z < val / 3; z++) {
            int randX = rand.nextInt((99 - 48) * 10) + (48 * 10);// spawn region
                                                                    // 1
            int randY = rand.nextInt((74 - 60) * 10) + (60 * 10);
            Zombie enemy = new Zombie(randX, randY);
            if (checkEnemies(enemies, tiles, myPlayer, enemy)) {
                enemies.add(enemy);
            }
        }
        for (int u = 0; u < val / 3; u++) {
            int randX2 = rand.nextInt((12 - 3) * 10) + (3 * 10);// spawn
                                                                    // region 2
            int randY2 = rand.nextInt((35 - 25) * 10) + (25 * 10);
            Zombie enemy2 = new Zombie(randX2, randY2);
            if (checkEnemies(enemies, tiles, myPlayer, enemy2)) {
                enemies.add(enemy2);
            }
        }
        for (int u = 0; u < val / 3; u++) {
            int randX3 = rand.nextInt((79 - 61) * 10) + (61 * 10);// spawn
                                                                    // region 2
            int randY3 = rand.nextInt((14 - 2) * 10) + (2 * 10);
            Zombie enemy3 = new Zombie(randX3, randY3);
            if (checkEnemies(enemies, tiles, myPlayer, enemy3)) {
                enemies.add(enemy3);
            }
        }


	}

	public boolean checkEnemies(List<Zombie> zomb, List<BlockTiles> t,
			Player p, Zombie a) {
		if (zomb.size() == 0) {
			return true;
		}
		for (int i = 0; i < zomb.size(); i++) {
			if (a.getRect().intersects(zomb.get(i).getRect())) {
				return false;
			}
		}
		for (int b = 0; b < t.size(); b++) {
			if (a.getRect().intersects(t.get(b).getRect())) {
				return false;
			}
		}
		if (a.getRect().intersects(p.getRect())) {
			return false;
		}
		return true;

	}

	// for (int i = 0; i < enemies.size(); i++) {//kills the same zombie if
	// spawned in same spot
	// for (int b = 0; b < enemies.size(); b++) {
	// if (b != i &&
	// enemies.get(i).getRect().contains(enemies.get(b).getRect())) {
	// enemies.remove(b);
	// }
	// }
	// }

	private void addBlockTiles() {
		// water regions
		for (int i = 0; i < 13; i++) {
			BlockTiles region = new BlockTiles(0 + (i * 40), 0, 1);
			tiles.add(region);
		}
		for (int i = 0; i < 12; i++) {
			for (int y = 0; y < 2; y++) {
				BlockTiles region2 = new BlockTiles(0 + (i * 40),
						(40 + (y * 40)), 1);
				tiles.add(region2);
			}
		}
		for (int i = 0; i < 11; i++) {
			BlockTiles region3 = new BlockTiles(0 + (i * 40), (40 * 3), 1);
			tiles.add(region3);// box y=4
		}
		for (int i = 0; i < 9; i++) {
			BlockTiles region4 = new BlockTiles(0 + (i * 40), (40 * 4), 1);
			tiles.add(region4);
		}
		for (int i = 4; i < 9; i++) {
			for (int y = 8; y < 10; y++) {
				BlockTiles region5 = new BlockTiles((i * 40), (40 * 5), 1);
				BlockTiles region7 = new BlockTiles((i * 40), (40 * y), 1);
				tiles.add(region5);
				tiles.add(region7);
			}
		}
		for (int i = 4; i < 8; i++) {
			BlockTiles region6 = new BlockTiles((i * 40), (40 * 7), 1);
			tiles.add(region6);
		}
		for (int i = 3; i < 11; i++) {
			for (int y = 10; y < 12; y++) {
				BlockTiles region8 = new BlockTiles((i * 40), (40 * y), 1);
				tiles.add(region8);
			}
		}
		for (int i = 5; i < 11; i++) {
			BlockTiles region9 = new BlockTiles((i * 40), (40 * 12), 1);
			tiles.add(region9);
		}
		for (int i = 8; i < 11; i++) {
			for (int y = 13; y < 19; y++) {
				BlockTiles region10 = new BlockTiles((i * 40), (40 * y), 1);
				tiles.add(region10);
			}
		}
		for (int i = 0; i < 11; i++) {
			BlockTiles region11 = new BlockTiles((i * 40), (40 * 19), 1);
			tiles.add(region11);
		}
		// building regions
		for (int a = 74; a < 88; a++) {// glass
			for (int i = 42; i < 52; i++) {
				BlockTiles region12 = new BlockTiles((a * 10), (i * 10), 2);
				tiles.add(region12);
			}
		}
		for (int a = 96; a < 103; a++) {// hut
			for (int i = 41; i < 52; i++) {
				BlockTiles region13 = new BlockTiles((a * 10), (i * 10), 2);
				tiles.add(region13);
			}
		}
//		for (int a = 87; a < 119; a++) {// big building
//			for (int i = 9; i < 27; i++) {
//				BlockTiles region14 = new BlockTiles((a * 10), (i * 10), 2);
//				tiles.add(region14);
//			}
//		}
//		for (int a = 98; a < 108; a++) {// big building
//			for (int i = 1; i < 9; i++) {
//				BlockTiles region15 = new BlockTiles((a * 10), (i * 10), 2);
//				tiles.add(region15);
//			}
//		}
		for (int y = 0; y < 14; y++) {// first part of mud
			BlockTiles region16 = new BlockTiles((54 * 10), (10 * y), 2);
			tiles.add(region16);
		}
		for (int y = 13; y < 16; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((56 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 15; y < 20; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((58 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 19; y < 22; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((64 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 21; y < 24; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((70 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 23; y < 26; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((72 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 25; y < 27; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((74 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 28; y < 31; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((72 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 32; y < 47; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((70 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 45; y < 49; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((88 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 45; y < 49; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((90 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int x = 65; x < 70; x++) {// second part of mud
			BlockTiles region17 = new BlockTiles((x * 10), (10 * 21), 2);
			tiles.add(region17);
		}
		for (int x = 98; x < 106; x++) {// second part of mud
			BlockTiles region17 = new BlockTiles((10 * x), (59 * 10), 2);
			tiles.add(region17);
		}
		for (int y = 50; y < 58; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((94 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 56; y < 60; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((98 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 56; y < 79; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((106 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int y = 56; y < 79; y++) {// second part of mud
			BlockTiles region17 = new BlockTiles((117 * 10), (10 * y), 2);
			tiles.add(region17);
		}
		for (int x = 48; x < 53; x++) {// second part of mud
            BlockTiles region18 = new BlockTiles((x * 10), (10 * 11), 2);
            tiles.add(region18);
        }
	}

	private void setUpKeyBindings() {
		// TODO Auto-generated method stub
		// this.getInputMap().put(KeyStroke.getKeyStroke("shift"),
		// "beginMelee");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed X"),
				"beginMelee");
		this.getInputMap()
				.put(KeyStroke.getKeyStroke("released X"), "endMelee");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed Z"),
				"beginShoot");
		this.getInputMap()
				.put(KeyStroke.getKeyStroke("released Z"), "endShoot");
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

		this.getActionMap().put("beginMelee", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				meleePressed = true;
			}
		});

		this.getActionMap().put("endMelee", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
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
				bullets.add(new Bullet(myPlayer.getX() + 25,
						myPlayer.getY() + 25, myPlayer.getDirection()));
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
		map.draw(g);
		g.setFont(new Font("Helvetica", Font.PLAIN, 30)); 
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(g);
		}
		/*
		 * for (int i = 0; i < blockers.size(); i++) { blockers.get(i).draw(g);
		 * }
		 */
		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).draw(g, myPlayer.getX(), myPlayer.getY());
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		myPlayer.draw(g);
		g.setColor(Color.white);
		g.drawString("Score: " + score, 10, 20);
		g.drawString("Level: " + level, 10, 50);
		g.drawString("Ammo: " + bullets.showCapacity(), 10, 80);
		g.drawString("Health: " + myPlayer.getHealth(), 10, 110);
		if (checkStart) {
			g.drawString("Time Left in Safe Zone: " + hutTime, 10, 140);
		}
		if (checkStartMelee && !checkStart) {
			g.drawString("Time Left to Melee: " + meleeTime, 10, 140);
		} else if (checkStartMelee) {
			g.drawString("Time Left to Melee: " + meleeTime, 10, 170);
		}
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).draw(g);
		}
	}

	private void tick(Timer t) {
		// TODO Auto-generated method stub
		if (upPressed) {
			myPlayer.moveUp(tiles);
		}
		if (downPressed) {
			myPlayer.moveDown(tiles);
		}
		if (rightPressed) {
			myPlayer.moveRight(tiles);
		}
		if (leftPressed) {
			myPlayer.moveLeft(tiles);
		}
		if (!leftPressed && !rightPressed && !downPressed && !upPressed) {
			myPlayer.setWalkingFalse();
		}
		if (meleePressed) {
			melee();
			myPlayer.setMeleeTrue();
		} else {
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
		// check if player is meleeing too long
		playerIsMelee();
		// check if bullet hits obstacle
		bulletVSObstacles();
		
		checkHealth();
		incrementTimes();
		if (enemies.size() < 1) {
			newLevel();
		}
	}

	private void bulletVSObstacles() {
		// TODO Auto-generated method stub
		for (int i = 0; i < tiles.size(); i++) {
			for (int x = bullets.size() - 1; x >= 0; x--) {
				if (bullets.get(x).getRect().intersects(tiles.get(i).getRect())) {
					bullets.remove(x);
				}
			}
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

	public void playerIsMelee() {
		if (myPlayer.getMeleeStatus() && !checkStartMelee) {
			meleeTime = 2;
			t3.start();
			checkStartMelee = true;
		}

		if (!myPlayer.getMeleeStatus() && checkStartMelee) {
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
			JOptionPane.showMessageDialog(null, "Score: " + score + "\nLevel: "
					+ level, "You Lose!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void boundBullets() {
		// TODO Auto-generated method stub
		for (int i = bullets.size() - 1; i >= 0; i--) {
			if (bullets.get(i).getX() <= 0 || bullets.get(i).getX() > 1200) {
				bullets.remove(i);
			} else if (bullets.get(i).getY() <= 0
					|| bullets.get(i).getY() > 800) {
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
			z.moveLeft(enemies, blockers, tiles);

		} else if (myPlayer.getX() > z.getX()) {
			z.moveRight(enemies, blockers, tiles);

		}

		if (myPlayer.getY() < z.getY()) {
			z.moveUp(enemies, blockers, tiles);

		} else if (myPlayer.getY() > z.getY()) {
			z.moveDown(enemies, blockers, tiles);

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
