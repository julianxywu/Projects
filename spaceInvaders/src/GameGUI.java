import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

// Author Julian Wu, Co-Author Cathy Wu, November 2018
// Game GUI Window for ASTEROIDS Game

public class GameGUI extends DrawingGUI {
	private GameInteractions check; // Checks the interactions between bullets and ship.
	private Ship ship; // User's monster-killing ship.
	private BulletFlurry shipBulletFlurry;
	public BulletFlurry monsterBulletFlurry;
	private BulletFlurry turretBulletFlurry;
	private BulletFlurry shipMissileFlurry;
	private Monster monster, monsterMinion;
	private int totalMonsterNumber;
	private Horde horde, horde2, horde3, horde4, horde5, horde6;
	private ArrayList<Horde> hordeGroups;
	private Money userMoney;
	private Point newPoint;
	private GameShop currShop;
	private boolean nextStageButton=false, shopButton=false, canOpenShop=false;
	private int stageLevel=1, shipLevel = 1;
	private int maxStageLevel = 30;
	//for update upgrades
	private int shipStartingHealth=3, shipStartingShield=0, shieldStartingRecovery=0;
	private int shipSpeed = 4;
	private int bulletSpeed = 1, bulletDamage = 1;
	private int counter1=0, counter2=0, counter3=0, counter4=0, counter5=0, counter6=0, counter7=0;
	private boolean verticalMovement = false;
	private SpaceTurret turret1, turret2, turret3;
	private int numOfTurrets = 0, usedTurrets = 0;
	private ArrayList<SpaceTurret> turretList;
	private int numOfMissiles = 0, usedMissiles = 0;
	private BlackHole hole = new BlackHole();
	private boolean blackHoleActive=false, iceFieldActive=false, burninBladeActive=false, reincarnationActive=false;
	private Color ice = new Color(85, 194, 204);
	private Color darken = new Color(0, 0, 0, 127);
	private int iceFieldCounter = 15;
	private boolean iceDoOnce, bladeDoOnce, holeDoOnce; // booleans to make the timers run only once.
	private SwordSpecial swordSpecial;
	private BufferedImage img1, img2, img3;
	Timer timer = new Timer();
	Timer cactusTimer;
	Timer iceTimer, iceCDTimer, bladeTimer, bladeCDTimer, holeCDTimer;
	TimerTask task, cactusSpeakTask;
	private String cactusSpeak;
	
	public int count = 0;
	
	private static final int width=800, height=600;		// setup: size of the "world" (window)
	private int delay = 100;							// delay for the timer to fire (and repeat) in milliseconds
		
	public GameGUI() throws InterruptedException {
		super("Asteroids!", width, height); //set up graphics "world"
		
		//INSTRUCTIONS
		System.out.println("WELCOME TO SPACE INVADERS");
		System.out.println("If you forget the controls, just press '0'.");
		
		
		// Create ship.
		ship = new Ship(shipLevel, shipStartingHealth, shipStartingShield, shieldStartingRecovery);
		// Create horde of monsters.
		hordeGroups = new ArrayList<Horde>();
		horde = new Horde(1, 1, 200);
		hordeGroups.add(horde);
		monster = new Monster(1, 0, 0, 0, false); // test monster
//		monsterMinion = new Monster(1, 400, 400, 5, false);
//		horde.horde.add(monster);
		// Create ship bullets and missiles.
		shipBulletFlurry = new BulletFlurry();
		shipMissileFlurry = new BulletFlurry();
		// Create monster bullets.
		monsterBulletFlurry = new BulletFlurry();
		// Create turret list and turret bullets.
		turretList = new ArrayList<SpaceTurret>();
		turretBulletFlurry = new BulletFlurry();
		// Create in-game checks.
		check = new GameInteractions();
		// Create money.
		userMoney = new Money(1000);
		// Create sword special.
		swordSpecial = new SwordSpecial();
		// Create shop.
		currShop = new GameShop(stageLevel + 1, userMoney, nextStageButton);
		currShop.itemsList.get("ICEBORN GAUNTLET").setCoolDown(45);
		currShop.itemsList.get("EXCALIBUR").setCoolDown(60);
		currShop.itemsList.get("DARK HOLE").setCoolDown(90);
		currShop.itemsList.get("ICEBORN GAUNTLET").startingCoolDown = 45;
		currShop.itemsList.get("EXCALIBUR").startingCoolDown = 60;
		currShop.itemsList.get("DARK HOLE").startingCoolDown = 90;
		currShop.itemsList.get("TELLTALE HEART").setCoolDown(5);
		currShop.itemsList.get("TELLTALE HEART").startingCoolDown = 5;
		// Timer drives the animation.
		setTimerDelay(delay);
		startTimer();
	}
	
	public void newStage() {
		// Shop NEXT LEVEL indication
		currShop.level = stageLevel + 1;
		// Create ship.
		ship = new Ship(shipLevel, shipStartingHealth, shipStartingShield, shieldStartingRecovery);
		// Create ship bullets.
		shipBulletFlurry = new BulletFlurry();
		shipMissileFlurry = new BulletFlurry();
		// Create monster bullets.
		monsterBulletFlurry = new BulletFlurry();
		// Create turret bullets.
		usedTurrets = 0;
		turretList= new ArrayList<SpaceTurret>();
		turretBulletFlurry = new BulletFlurry();
		// Reset missiles;
		usedMissiles = 0;
		// Create in-game checks.
		check = new GameInteractions();
		// Reset black hole, ice field, and burninBlade boolean.
		blackHoleActive = false;
		iceFieldActive = false;
		burninBladeActive = false;
		swordSpecial = new SwordSpecial();
		// Create horde of monsters.
		hordeGroups = new ArrayList<Horde>();
		if (stageLevel <= 5) {
			horde = new Horde(stageLevel, 1, 200);
		}
		if (stageLevel == 6) {
			horde = new Horde(2, 2, 200);
		}
		if (stageLevel == 7) {
			horde = new Horde(1, 2, 150);
			horde2 = new Horde(2, 1, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 8) {
			horde = new Horde(2, 2, 150);
			horde2 = new Horde(4, 1, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 9) {
			horde = new Horde(1, 3, 150);
			horde2 = new Horde(3, 1, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 10) {
			horde = new Horde(0, monster.boss1Health, 150);
		}
		if (stageLevel == 11) {
			horde = new Horde(2, 3, 150);
			horde2 = new Horde(1, 2, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 12) {
			horde = new Horde(3, 3, 150);
			horde2 = new Horde(4, 2, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 13) {
			horde = new Horde(1, 5, 150);
		}
		if (stageLevel == 14) {
			horde = new Horde(1, 5, 150);
			horde2 = new Horde(4, 1, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 15) {
			horde = new Horde(2, 5, 150);
			horde2 = new Horde(3, 3, 200);
			horde3 = new Horde(4, 2, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
		}
		if (stageLevel == 16) {
			horde = new Horde(4, 5, 150);
			horde2 = new Horde(3, 3, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 17) {
			horde = new Horde(4, 5, 150);
			horde2 = new Horde(5, 3, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 18) {
			horde = new Horde(3, 5, 100);
			horde2 = new Horde(3, 3, 150);
			horde3 = new Horde(4, 2, 200);
			horde4 = new Horde(4, 1, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
			hordeGroups.add(horde4);
		}
		//TROLL LEVEL
		if (stageLevel == 19) {
			String taunts[] = {"'HAHA YOU SUCK'", "'TROLOLOL'", "'CAN'T TOUCH THIS'", "'NOOB'", "'...'", "'%$(#!&%!!$#@!'", "':) :) :)'", "'2 + 2 is 4'", "'JUST GIVE UP'"};
			cactusTimer = new Timer();
			cactusSpeakTask = new TimerTask() {
				public void run() {
					cactusSpeak = taunts[(int)(Math.random() * (taunts.length + 1))];
				}
			};
			cactusTimer.scheduleAtFixedRate(cactusSpeakTask, 20000, 20000);
			horde = new Horde(-1, monster.trollBossHealth, 150);
		}
		// 2nd BOSS
		if (stageLevel == 20) {
			horde = new Horde(-2, monster.boss2Health, 150);
		}
		if (stageLevel == 21) {
			horde = new Horde(2, 8, 150);
			horde2 = new Horde(3, 5, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 22) {
			horde = new Horde(2, 8, 150);
			horde2 = new Horde(3, 5, 200);
			horde3 = new Horde(4, 3, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
		}
		if (stageLevel == 23) {
			horde = new Horde(4, 8, 150);
			horde2 = new Horde(4, 3, 200);
			horde3 = new Horde(5, 2, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
		}
		if (stageLevel == 24) {
			horde = new Horde(2, 10, 150);
		}
		if (stageLevel == 25) {
			horde = new Horde(1, 10, 100);
			horde2 = new Horde(3, 3, 150);
			horde3 = new Horde(4, 2, 200);
			horde4 = new Horde(4, 1, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
			hordeGroups.add(horde4);
		}
		if (stageLevel == 26) {
			horde = new Horde(1, 10, 100);
			horde2 = new Horde(3, 5, 150);
			horde3 = new Horde(4, 3, 200);
			horde4 = new Horde(4, 2, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
			hordeGroups.add(horde4);
		}
		if (stageLevel == 27) {
			horde = new Horde(2, 10, 100);
			horde2 = new Horde(2, 8, 150);
			horde3 = new Horde(4, 3, 200);
			horde4 = new Horde(4, 2, 250);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
			hordeGroups.add(horde4);
		}
		if (stageLevel == 28) {
			horde = new Horde(3, 10, 150);
			horde2 = new Horde(4, 8, 200);
			hordeGroups.add(horde2);
		}
		if (stageLevel == 29) {
			horde = new Horde(1, 10, 100);
			horde2 = new Horde(2, 8, 150);
			horde3 = new Horde(3, 5, 200);
			horde4 = new Horde(4, 3, 250);
			horde5 = new Horde(5, 2, 300);
			hordeGroups.add(horde2);
			hordeGroups.add(horde3);
			hordeGroups.add(horde4);
			hordeGroups.add(horde5);
			
		}
		// 3RD BOSS
		if (stageLevel == 30) {
			horde = new Horde(-3, monster.boss3Health, 150);
		}
		hordeGroups.add(horde);
		for (Horde horde : hordeGroups) {
			totalMonsterNumber += horde.getMonsterNumber();
		}
	}
	
	@Override
	public void handleMousePress(int x, int y) {
//		if (ship.contains(x, y)) {
//			System.out.println("back off!");
//			return;
		
		repaint();
	}
	
	@Override
	public void handleKeyPress(char k) {
		if (!gameOver()) {
			ship.setVelocityX(0);
			ship.setVelocityY(0);
			if (k == 'a') { // move left
				ship.setVelocityX(-shipSpeed);
				System.out.println("You pressed A!");
			}
			if (k == 'd') { // move right
				ship.setVelocityX(shipSpeed);
				System.out.println("You pressed D!");
			}
			if (k == 'w') { // move up
				if (verticalMovement) {
					ship.setVelocityY(-shipSpeed);
					System.out.println("You pressed W!");
				}
				else {
					ship.setVelocityY(0);
				}
			}
			if (k == 's') { // move down
				if (verticalMovement) {
					ship.setVelocityY(shipSpeed);
					System.out.println("You pressed S!");
				}
				else {
					ship.setVelocityY(0);
				}
			}
			else if (k == ' ') { // shoot
				if (ship.cannon1 != null) {
					Bullet newBullet1 = new Bullet(bulletSpeed, bulletDamage, ship.cannon1.getX(), ship.cannon1.getY(), ship.cannon1.getWidth(), false, false);
					shipBulletFlurry.onScreenBullets.add(newBullet1);
				}
				if (ship.cannon2a != null && ship.cannon2b != null) {
					Bullet newBullet1 = new Bullet(bulletSpeed, bulletDamage, ship.cannon2a.getX(), ship.cannon2a.getY(), ship.cannon2a.getWidth(), false, false);
					Bullet newBullet2 = new Bullet(bulletSpeed, bulletDamage, ship.cannon2b.getX(), ship.cannon2b.getY(), ship.cannon2b.getWidth(), false, false);
					shipBulletFlurry.onScreenBullets.add(newBullet1);
					shipBulletFlurry.onScreenBullets.add(newBullet2);
				}
				if (ship.cannon3x != null && ship.cannon3a != null && ship.cannon3b != null) {
					Bullet newBullet1 = new Bullet(bulletSpeed, bulletDamage, ship.cannon3a.getX(), ship.cannon3a.getY(), ship.cannon3a.getWidth(), false, false);
					Bullet newBullet2 = new Bullet(bulletSpeed, bulletDamage, ship.cannon3b.getX(), ship.cannon3b.getY(), ship.cannon3b.getWidth(), false, false);
					Bullet newBullet3 = new Bullet(bulletSpeed, bulletDamage, ship.cannon3x.getX(), ship.cannon3x.getY(), ship.cannon3x.getWidth(), false, false);
					shipBulletFlurry.onScreenBullets.add(newBullet1);
					shipBulletFlurry.onScreenBullets.add(newBullet2);
					shipBulletFlurry.onScreenBullets.add(newBullet3);
				}
				if (ship.cannon4a != null && ship.cannon4b != null && ship.cannon4aa != null && ship.cannon4bb != null) {
					Bullet newBullet1 = new Bullet(bulletSpeed, bulletDamage, ship.cannon4a.getX(), ship.cannon4a.getY(), ship.cannon4a.getWidth(), false, false);
					Bullet newBullet2 = new Bullet(bulletSpeed, bulletDamage, ship.cannon4b.getX(), ship.cannon4b.getY(), ship.cannon4b.getWidth(), false, false);
					Bullet newBullet3 = new Bullet(bulletSpeed, bulletDamage, ship.cannon4aa.getX(), ship.cannon4aa.getY(), ship.cannon4aa.getWidth(), false, false);
					Bullet newBullet4 = new Bullet(bulletSpeed, bulletDamage, ship.cannon4bb.getX(), ship.cannon4bb.getY(), ship.cannon4bb.getWidth(), false, false);
					shipBulletFlurry.onScreenBullets.add(newBullet1);
					shipBulletFlurry.onScreenBullets.add(newBullet2);
					shipBulletFlurry.onScreenBullets.add(newBullet3);
					shipBulletFlurry.onScreenBullets.add(newBullet4);
				}
				if (ship.cannon5x != null && ship.cannon5a != null && ship.cannon5b != null && ship.cannon5aa != null && ship.cannon5bb != null) {
					Bullet newBullet1 = new Bullet(bulletSpeed, bulletDamage, ship.cannon5a.getX(), ship.cannon5a.getY(), ship.cannon5a.getWidth(), false, false);
					Bullet newBullet2 = new Bullet(bulletSpeed, bulletDamage, ship.cannon5b.getX(), ship.cannon5b.getY(), ship.cannon5b.getWidth(), false, false);
					Bullet newBullet3 = new Bullet(bulletSpeed, bulletDamage, ship.cannon5aa.getX(), ship.cannon5aa.getY(), ship.cannon5aa.getWidth(), false, false);
					Bullet newBullet4 = new Bullet(bulletSpeed, bulletDamage, ship.cannon5bb.getX(), ship.cannon5bb.getY(), ship.cannon5bb.getWidth(), false, false);
					Bullet newBullet5 = new Bullet(bulletSpeed, bulletDamage, ship.cannon5x.getX(), ship.cannon5x.getY(), ship.cannon5x.getWidth(), false, false);
					shipBulletFlurry.onScreenBullets.add(newBullet1);
					shipBulletFlurry.onScreenBullets.add(newBullet2);
					shipBulletFlurry.onScreenBullets.add(newBullet3);
					shipBulletFlurry.onScreenBullets.add(newBullet4);
					shipBulletFlurry.onScreenBullets.add(newBullet5);
				}
				System.out.println("SHOOT!!!");
			}
		}
		if (k == 't') { // turret
			int tempX = ship.getX();
			int tempY = ship.getY();
			if (usedTurrets < numOfTurrets) {
				if (usedTurrets == 0) {
					turret1 = new SpaceTurret(tempX, tempY, bulletSpeed, bulletDamage);
					turretList.add(turret1);
					usedTurrets++;
				}
				else if (usedTurrets == 1) {
					turret2 = new SpaceTurret(tempX, tempY, bulletSpeed, bulletDamage);
					turretList.add(turret2);
					usedTurrets++;
				}
				else if (usedTurrets == 2) {
					turret3 = new SpaceTurret(tempX, tempY, bulletSpeed, bulletDamage);
					turretList.add(turret3);
					usedTurrets++;
				}
			}
		}
		
		if (k == 'k') { // next stage
			nextStageButton = true;
			shopButton = false;
			System.out.println("NEXT STAGE!");
		}
		if (k == 'p' & canOpenShop) {	// go to shop
			shopButton = true;
			System.out.println("SHOP!");
		}
		if (k == 'o') { // dark hole (kill all)
			if (currShop.itemsList.get("DARK HOLE").getNumber() > 0 && !nextStage() && !currShop.itemsList.get("DARK HOLE").onCoolDown) {
				blackHoleActive = true;
				iceFieldActive = false;
				burninBladeActive = false;
				currShop.itemsList.get("DARK HOLE").usedAmount++;
				holeCDTimer = new Timer();
				currShop.itemsList.get("DARK HOLE").setOnCoolDown(true);
				hole = new BlackHole();
				for (Horde horde : hordeGroups) {
					for (Monster monster : horde.horde) {
						if (!monster.isBoss) {
							monster.moveTowards(-100, -100);
						}
					}
				}
			}
			for (Horde horde : hordeGroups) { // ERASE LATER
				horde.killAll();
			}
			System.out.println("KILLED ALL!!");
		}
		if (k == 'i') { // freeze all enemies for 15 sec
			if (currShop.itemsList.get("ICEBORN GAUNTLET").getNumber() > 0 && !nextStage() && !currShop.itemsList.get("ICEBORN GAUNTLET").onCoolDown) {
				iceFieldActive = true;
				blackHoleActive = false;
				burninBladeActive = false;
				currShop.itemsList.get("ICEBORN GAUNTLET").usedAmount++;
				iceTimer = new Timer();
				iceCDTimer = new Timer();
				currShop.itemsList.get("ICEBORN GAUNTLET").setOnCoolDown(true);
			}
		}
		
		if (k == 'j') { // excalibur
			if (currShop.itemsList.get("EXCALIBUR").getNumber() > 0 && !nextStage() && !currShop.itemsList.get("EXCALIBUR").onCoolDown) {
				burninBladeActive = true;
				iceFieldActive = false;
				blackHoleActive = false;
				currShop.itemsList.get("EXCALIBUR").usedAmount++;
				bladeTimer = new Timer();
				bladeCDTimer = new Timer();
				currShop.itemsList.get("EXCALIBUR").setOnCoolDown(true);
			}
		}
		
		if (k == 'f') { // missiles
			if (usedMissiles < numOfMissiles) {
				Bullet newBullet = new Bullet(777, bulletDamage, ship.missileCannon.getX(), ship.missileCannon.getY(), ship.missileCannon.getWidth(), false, true);
				shipMissileFlurry.onScreenBullets.add(newBullet);
				usedMissiles++;
			}
		}
		
		if (shopButton) {
			currShop.HandleKeyPress(k);
		}
	}
	
	@Override
	public void handleKeyRelease(char k) {
		if (k == 'd' || k == 'a') {
//			ship.setVelocityX(0);
		}
		if (k == 'w' || k == 's') {
//			ship.setVelocityY(0);
		}
		if (k == 'k') {
			nextStageButton = false;
		}
		if (k == 'p') {
			//shopButton = false;
		}
		if (shopButton) {
			currShop.HandleKeyRelease(k);
		}
	}
	
	public void drawBackground(Graphics g) {
		// Draw the background.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		// Draw iceField if active.
		if (iceFieldActive) {
			//g.setColor(ice);
			//g.fillRect(0, 0, 800, 600);
			img3 = loadImage("C:\\Users\\julian\\eclipse-workspace\\spaceInvaders\\src\\IceBackGround.jpg");
			g.drawImage(img3, 0, 0, null);
			g.setColor(darken);
			g.fillRect(0, 0, 800, 600);
		}
		if (burninBladeActive) {
			img1 = loadImage("C:\\Users\\julian\\eclipse-workspace\\spaceInvaders\\src\\SwordWinterIsComing.jpg");
			img2 = loadImage("C:\\Users\\julian\\eclipse-workspace\\spaceInvaders\\src\\SwordKingArthur.jpg");
//			img3 = loadImage()
			g.drawImage(img1, 0, 0, null);
			g.drawImage(img2, 520, 300, null);
			g.drawImage(img2, 48, 300, null);

		//	g.setColor(Color.BLUE);
			//g.fillRect(500, 300, 232, 300);
		}
	}
	
	public void drawObjects(Graphics2D g) {
		// Draw the ship.
		ship.draw(g);
		// Draw the bullets.
		shipBulletFlurry.draw(g);
		shipMissileFlurry.draw(g);
		monsterBulletFlurry.draw(g);
		turretBulletFlurry.draw(g);
		for (Horde horde : hordeGroups) {
			horde.draw(g);
			if (horde.horde.size() != 0) {
				// DELETE LATER
//				g.setColor(Color.RED);
//				g.fillRect(horde.horde.get(0).body.getX(), horde.horde.get(0).body.getY(), 3, 3);
//				g.fillRect(horde.horde.get(0).body.getX(), horde.horde.get(0).body.getY() + horde.horde.get(0).body.getHeight(), 3, 3);
			}
		}
		// TROLL BOSS
		if (cactusSpeak != null && stageLevel == 19) {
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.setColor(Color.WHITE);
			g.drawString(cactusSpeak, 350, 130);
		}
		
		// Draw the turrets.
		int i = 0;
		while (i < turretList.size()) {
			if (i == 0) {
				g.setColor(Color.RED);
			}
			else if (i == 1) {
				g.setColor(Color.GREEN);
			}
			else if (i == 2) {
				g.setColor(Color.CYAN);
			}
			turretList.get(i).draw(g);
			i++;
		}
		// Draw the black hole, if active.
		if (blackHoleActive) {
			hole.draw(g);
			if (hole.whiteHole) {
				hole.drawWhiteHole(g);
			}
			if (hole.deleteBlackHole) {
				blackHoleActive = false;
			}
			g.setStroke(new BasicStroke(1));
		}
		// Draw the blades, if active.
		if (burninBladeActive) {
			swordSpecial.draw(g);
		}
	}
	
	public void drawText(Graphics g) {
		// Draw the adding-money amount.
		check.draw(g, userMoney);
		// ON SCREEN : Level, Number of Bullets, Number of Enemies, Lives, Money, Shield Strength
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", Font.BOLD, 16));
		g.drawString("LEVEL " + Integer.toString(stageLevel), 370, 30);
		g.drawString(userMoney.howMuchMoney(), 20, 30);
		g.setFont(new Font("Dialog", Font.PLAIN, 12));
		g.drawString("Enemies: " + Integer.toString(totalMonsterNumber), 370, 45);
	//	g.drawString("Number of Bullets: " + Integer.toString(shipBulletFlurry.getBulletNumber() + turretBulletFlurry.getBulletNumber()), 650, 50);
		g.drawString("LIVES(" + Integer.toString(ship.shipHealth.lives.size()) + "): ", 20, 55);
		g.drawString("SHIELD(" + Integer.toString(ship.shield.shieldHealth) + "): ", 20, 75);
		
		if (ship.shield.currentUpgrade > 0) {
			g.drawString("RECOVERY RATE: " + ship.shield.recoveryRate[ship.shield.currentUpgrade], 20, 95);
		}
		if (currShop.specialEnhancements.get("SPACE TURRET").currentUpgrade > 0 ) {
			g.drawString("TURRETS LEFT: " + Integer.toString(numOfTurrets - usedTurrets), 20, 115);
		}
		if (currShop.specialEnhancements.get("MEGA MISSILES").currentUpgrade > 0 ) {
			g.drawString("MISSILES LEFT: " + Integer.toString(numOfMissiles - usedMissiles), 20, 135);
		}
		
		// BAG
		g.setFont(new Font("Dialog", Font.BOLD, 16));
		g.drawString("BAG", 630, 30);
		
		if (iceFieldActive) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			g.drawString("TIME LEFT: " + iceFieldCounter, 100, 500);
		}
		
		// draw each item in the bag
		boolean bagEmpty = true;
		int bagY = 40;
		for (Node item : currShop.itemsBag) {

			if (item.getNumber() > 0) {
				bagEmpty = false;
				g.setFont(new Font("Dialog", Font.PLAIN, 12));
				g.setColor(Color.WHITE);
				g.drawString(item.name + "(" + Integer.toString(item.getNumber()) + ")", 650, bagY + 10);
				g.drawOval(630, bagY, 10, 10);

				if (item.onCoolDown()) {
					g.fillArc(630, bagY, 10, 10, 90, (-360 / item.startingCoolDown) * (item.startingCoolDown - item.getCoolDown()));
				}
				if (!item.onCoolDown()) {
					g.setColor(Color.GREEN);
					g.fillOval(630, bagY, 10, 10);
				}
				bagY += 15;
			}
		}
		if (bagEmpty) {
			g.setFont(new Font("Dialog", Font.PLAIN, 12));
			g.drawString("-- EMPTY -- ", 620, 50);
		}
	}
	
	public void drawScreenChange(Graphics g) {
		// NEXT STAGE
		if (nextStage() && !playerWins()) {
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.setColor(Color.GREEN);
			g.drawString("Press 'K' to move on to the next stage.", 260, 420);
			g.drawString("Press 'P' to go to the store.", 290, 440);
		}
		// GAME OVER
		if (gameOver()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Dialog", Font.BOLD, 50));
			g.drawString("GAME OVER", 260, 450);
		}
		
		revive();
		// PLAYER WINS
		if (playerWins()) {
			nextStageButton = false;
			g.setColor(Color.GREEN);
			g.setFont(new Font("Dialog", Font.BOLD, 60));
			g.drawString("CONGRATS!", 230, 300);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			g.drawString("YOU HAVE COMPLETED ALL 30 STAGES!", 100, 350);
			
		}
	}
	
	public void updateObjects() {
		// FIRST STAGE.
		// Make the ship able to move.
		ship.move();
		for (Horde horde : hordeGroups) {
			// Make the monsters move.
			horde.move(blackHoleActive, iceFieldActive);
			// Make the horde shoot.
			horde.shoot(iceFieldActive, monsterBulletFlurry);
		}
		// Make the turrets shoot.
		for (SpaceTurret turret : turretList) {
			turret.shoot();
		}
		// BurninBlade Special
		if (burninBladeActive) {
			swordSpecial.move();
		}
	}
	
	public void updateTimers() {
		// Check Iceborn Gauntlet timer
		if (iceFieldActive) {
			TimerTask iceTask = new TimerTask() {
				public void run() {
					iceFieldActive = false;
					iceTimer.cancel();
					iceFieldCounter = 15;
					iceDoOnce = true;
				}
			};
			TimerTask iceCounterTask = new TimerTask() {
				public void run() {
					iceFieldCounter--;
				}
			};
			TimerTask iceCDTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("ICEBORN GAUNTLET").setOnCoolDown(false);
					iceCDTimer.cancel();
					currShop.itemsList.get("ICEBORN GAUNTLET").setCoolDown(currShop.itemsList.get("ICEBORN GAUNTLET").startingCoolDown);
				}
			};
			TimerTask iceCDCounterTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("ICEBORN GAUNTLET").setCoolDown(currShop.itemsList.get("ICEBORN GAUNTLET").getCoolDown() - 1);
				}
			};
			if (iceDoOnce) { // schedule event only once.
				iceTimer.schedule(iceTask, 15000); // freeze monsters for 15 secs
				iceTimer.scheduleAtFixedRate(iceCounterTask, 1000, 1000);
				iceCDTimer.schedule(iceCDTask, currShop.itemsList.get("ICEBORN GAUNTLET").getCoolDown() * 1000); // start CD
				iceCDTimer.scheduleAtFixedRate(iceCDCounterTask, 1000, 1000);
				System.out.println("HEREICE");
				iceDoOnce = false;
			}
		}

		// Check Excalibur timer
		if (burninBladeActive) {
			swordSpecial.addSwords();	// Check number of blades
			TimerTask bladeTask = new TimerTask() {
				public void run() {
					burninBladeActive = false;
					bladeTimer.cancel();
					iceFieldCounter = 15;
					bladeDoOnce = true;
				}
			};
			TimerTask bladeCDTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("EXCALIBUR").setOnCoolDown(false);
					bladeCDTimer.cancel();
					currShop.itemsList.get("EXCALIBUR").setCoolDown(currShop.itemsList.get("EXCALIBUR").startingCoolDown);
				}
			};
			TimerTask bladeCDCounterTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("EXCALIBUR").setCoolDown(currShop.itemsList.get("EXCALIBUR").getCoolDown() - 1);
				}
			};
			if (bladeDoOnce) { // schedule event only once.
				bladeTimer.schedule(bladeTask, 20000); // lasts 20 secs
				bladeCDTimer.schedule(bladeCDTask, currShop.itemsList.get("ICEBORN GAUNTLET").getCoolDown() * 1000);
				bladeCDTimer.scheduleAtFixedRate(bladeCDCounterTask, 1000, 1000);
				System.out.println("HERE");
				bladeDoOnce = false;
			}
		}
		
		// Check DarkHole timer
		if (blackHoleActive) {
			TimerTask holeCDTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("DARK HOLE").setOnCoolDown(false);
					currShop.itemsList.get("DARK HOLE").setCoolDown(currShop.itemsList.get("DARK HOLE").startingCoolDown);
					holeCDTimer.cancel();
				}
			};
			TimerTask holeCDCounterTask = new TimerTask() {
				public void run() {
					currShop.itemsList.get("DARK HOLE").setCoolDown(currShop.itemsList.get("DARK HOLE").getCoolDown() - 1);
				}
			};
			if (holeDoOnce) { // schedule event only once.
				holeCDTimer.schedule(holeCDTask, currShop.itemsList.get("DARK HOLE").getCoolDown() * 1000);
				holeCDTimer.scheduleAtFixedRate(holeCDCounterTask, 1000, 1000);
				holeDoOnce = false;
			}
		}
	}
	
	public void updateChecks() {
		// Add each monster bullet to the monster bullet ArrayList.
//		for (Horde horde : hordeGroups) {
//			check.monsterShoot(monsterBulletFlurry, horde.horde);
//		}
		// Add each turret bullet to the turret bullet ArrayList.
		check.turretShoot(turretBulletFlurry, turretList);
		// Check to see if the bullets and missiles are on screen.
		shipBulletFlurry.bulletCheck();
		shipMissileFlurry.bulletCheck();
		monsterBulletFlurry.bulletCheck();
		turretBulletFlurry.bulletCheck();
		// Check to see if the bullets intersect with the monster or ship.
		for (Horde horde : hordeGroups) {
			check.intersect(shipBulletFlurry.onScreenBullets, swordSpecial.swordList, horde.horde, userMoney, newPoint);
			check.intersect(shipMissileFlurry.onScreenBullets, swordSpecial.swordList, horde.horde, userMoney, newPoint);
			check.intersect(turretBulletFlurry.onScreenBullets, swordSpecial.swordList, horde.horde, userMoney, newPoint);
			check.monsterBlackHole(blackHoleActive, hole, horde.horde);
		}
		// Check if monster bullet hit the ship or turrets.
		check.shipIntersect(monsterBulletFlurry.onScreenBullets, ship);
		check.turretIntersect(monsterBulletFlurry.onScreenBullets, turretList);
		// Update total number of monsters alive on screen.
		totalMonsterNumber = 0;
		for (Horde horde : hordeGroups) {
			totalMonsterNumber += horde.getMonsterNumber();
		}
		// check extra lives
		if (currShop.itemsList.get("TELLTALE HEART").getNumber() > 0) {
			reincarnationActive = true;
		}
		else {
			reincarnationActive = false;
		}
	}
	
	public void updateDeletes() {
		// Delete swords that go off-screen.
		if (burninBladeActive) {
			swordSpecial.deleteSwords();
		}
		// Delete monsters and bullets that intersect.
		for (Horde horde : hordeGroups) {
			horde.deleteMonsters(userMoney);	
		}
		// Delete turret if it lost all its lives.
		int i = 0;
		while (i < turretList.size()) {
			if (!turretList.get(i).isAlive()) {
				turretList.remove(i);
			}
			i++;
		}
		shipBulletFlurry.deleteBullets();
		shipMissileFlurry.deleteBullets();
		monsterBulletFlurry.deleteBullets();
		turretBulletFlurry.deleteBullets();
	}
	
	public void updateShopUpgrades() {
		// SHIP HEALTH
		while (counter1 < currShop.shipEnhancements.get("SHIP HEALTH").getCurrentUpgrade()) {
			shipStartingHealth++;
			if (currShop.shipEnhancements.get("SHIP HEALTH").getCurrentUpgrade() == 10) {
				shipStartingHealth += 2;
			}
			counter1++;
		}
		// SHIELD HEALTH
		while (counter2 < currShop.shipEnhancements.get("SHIELD HEALTH").getCurrentUpgrade()) {
			shipStartingShield++;
			counter2++;
		}
		// SHIELD RECOVERY
		while (counter3 < currShop.shipEnhancements.get("SHIELD RECOVERY").getCurrentUpgrade()) {
			shieldStartingRecovery++;
			counter3++;
		}
		if (shieldStartingRecovery > 0) {
			if (ship.shield.shieldHealth < ship.shield.maxShieldHealth) {
				if (!ship.shield.stillRunning()) {
					ship.shield.addShield();
				}
			}
		}
		// SPEED
		while (counter4 < currShop.shipEnhancements.get("SPEED").getCurrentUpgrade()) {
			shipSpeed += 2;
			counter4++;
		}
		// VERTICAL MOVEMENT
		if (currShop.shipEnhancements.get("VERTICAL MVT").getCurrentUpgrade() == 1) {
			verticalMovement = true;
		}
		// # OF CANNONS
		shipLevel = currShop.cannonEnhancements.get("# OF CANNONS").getCurrentUpgrade() + 1;
		// CANNON BARREL ???
		// BULLET DAMAGE
		bulletDamage = currShop.bulletEnhancements.get("BULLET DAMAGE").getCurrentUpgrade() + 1;
		// BULLET SPEED
		bulletSpeed = currShop.bulletEnhancements.get("BULLET SPEED").getCurrentUpgrade() + 1;
		// SPACE TURRET
		numOfTurrets = currShop.specialEnhancements.get("SPACE TURRET").getCurrentUpgrade();
		// MISSILES
		if (currShop.specialEnhancements.get("MEGA MISSILES").getCurrentUpgrade() == 1) {
			numOfMissiles = 3; 
		}
		else if (currShop.specialEnhancements.get("MEGA MISSILES").getCurrentUpgrade() == 2) {
			numOfMissiles = 5;
		}
		else if (currShop.specialEnhancements.get("MEGA MISSILES").getCurrentUpgrade() == 3) {
			numOfMissiles = 8;
		}
		// ICEBORN GAUNTLET
		// EXCALIBUR
		// DARK HOLE
		// TELLTALE HEART
	}
	
	public void updateFutureStages() {
		// RESET FUTURE STAGES
			canOpenShop = false;
		if (nextStage()) {
			canOpenShop = true;
			// Remove any lingering addMoney text on-screen
			userMoney.removeAllTempPairs();
			// Reset ice Counters
			iceFieldActive=false;
			iceFieldCounter = 15;
			iceDoOnce = true;
		}
		if (nextStage() & nextStageButton) {
			burninBladeActive=false;
			blackHoleActive=false;
			bladeDoOnce = true;
			holeDoOnce = true;
			// Go to next level.
			stageLevel++;
			if (playerWins()) {
				
			}
			// Create next stage
			newStage();
		}
	}
	
	public boolean nextStage() {
		for (Horde horde : hordeGroups) {
			if (!horde.horde.isEmpty()) {
				return false;
			}
		}
		if (stageLevel == maxStageLevel) {
			return false;
		}
		return true;
	}
	
	public void revive() {
		if (ship.shipHealth.lives.size() == 0 && reincarnationActive) {
			currShop.itemsList.get("TELLTALE HEART").usedAmount++;
			shopButton = true;
			for (Horde horde : hordeGroups) {
				horde.killAll();
			}
			stageLevel--;
		}
	}
	
	public boolean gameOver() {
		if (ship.shipHealth.lives.size() == 0 && !reincarnationActive) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean playerWins() {
		if (stageLevel == maxStageLevel) {
			for (Horde horde : hordeGroups) {
				if (!horde.horde.isEmpty()) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void handleTimer() {
		updateObjects();
		updateTimers();
		updateChecks();
		updateDeletes();
		updateShopUpgrades();
		updateFutureStages();
//		if (shopButton) {
//			
//		}
		// Now update the GUI.
		repaint(); //repaint calls draw() in addition to a lot of other things
		
		//Testing speed decay
		ship.setVelocityX((int) (ship.getdX()-0.2*ship.getdX()));
		ship.setVelocityY((int) (ship.getdY()-0.2*ship.getdY()));
			
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (shopButton) {
			currShop.draw(g);
		}
		else {
			drawBackground(g);
			drawObjects(g);
			drawText(g);
			drawScreenChange(g);
		}
	}

	// MAIN
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new GameGUI();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}




















//public boolean stillRunning() {
//if (gotStuff) {
//	return true;
//}
//else {
//	return false;
//}
//}
//
//public void timer(int milliseconds) {
//if (!stillRunning()) {
//	task = new TimerTask() {
//		public void run() {
//			gotStuff = false;
//		}
//	};
//	gotStuff = true;
//	timer.schedule(task, milliseconds);
//}
//}
