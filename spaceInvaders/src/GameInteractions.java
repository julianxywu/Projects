import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameInteractions {
	int tempX, tempY;
	Monster currentMonster, explodingMonster;
	boolean bulletExplode;
	
	//Constructor (Like __init__)
	public GameInteractions() {
	}
	
	public void intersect(ArrayList <Bullet> bulletList, ArrayList <Sword> swordList, ArrayList <Monster> monsterList, Money userMoney, Point newPoint) {
		Timer timer = new Timer();
		for (Monster monster : monsterList) { // for each monster
			// BULLETS
			for (Bullet bullet : bulletList) { // for each bullet
				if (monster.containsBullet(bullet)) {
					for (int i=0; i<bullet.damage; i++) {
						monster.deductHealth();
					}
					
					if (monster.currentHealth <= 0) { //???
						newPoint = new Point(); // create a new point where they intersect for text
						newPoint.setLocation(bullet.getX(), bullet.getY());
						userMoney.tempPair.add(newPoint);
						currentMonster = monster;
						
						TimerTask task = new TimerTask() {
							public void run() {
								if (userMoney.tempPair.size() > 0) {
									userMoney.tempPair.remove(0);
								}
							}
						};
						timer.schedule(task, 300); // delay the text by .3 seconds
					}
					
					if (bullet.isMissile) {
						bulletExplode = true;
						explodingMonster = monster;
					}
					
					bullet.state = 0; // delete bullet	
				}
			}
			// SWORDS
			for (Sword sword : swordList) { // for each bullet
				if (monster.containsSword(sword)) {
					if (monster.currentHealth <= 5) { //???
						newPoint = new Point(); // create a new point where they intersect for text
						newPoint.setLocation(sword.getX(), sword.getY());
						userMoney.tempPair.add(newPoint);
						currentMonster = monster;
						
						TimerTask task = new TimerTask() {
							public void run() {
								if (userMoney.tempPair.size() > 0) {
									userMoney.tempPair.remove(0);
								}
							}
						};
						timer.schedule(task, 300); // delay the text by .3 seconds
					}
					
					monster.deductHealth();				
				}
			}
			
		}
	}
	
	public void shipIntersect(ArrayList <Bullet> monsterBulletList, Ship ship) {
		Timer timer2 = new Timer();
		
		for (Bullet bullet : monsterBulletList) {
			//ship shield
			if (ship.shield.contains(bullet)) {
				// deduct ship health
				if (ship.shield.shieldHealth > 0) {
					ship.shield.deductShield();
					ship.shield.bars.remove(ship.shield.bars.size() - 1);
					// proc the blink effect
					ship.shield.xHit = ship.shield.getX();
					ship.shield.yHit = ship.shield.getY();
					
					TimerTask task = new TimerTask() {
						public void run() {
							ship.shield.xHit = 0;
							ship.shield.yHit = 0;
						}
					};
					bullet.state = 0;
					timer2.schedule(task, 20);
				}
			}
			if (ship.shield.shieldHealth == 0) {
				if (ship.contains(bullet)) {
					// if the ship still have lives left
					if (ship.shipHealth.lives.size() > 0) {
						ship.shipHealth.lives.remove(ship.shipHealth.lives.size() - 1);
	
					}
					// remove the enemy bullet
					bullet.state = 0;
				}
			}
		}
	}
	
	public void turretIntersect(ArrayList <Bullet> monsterBulletList, ArrayList <SpaceTurret> turretList) {
		
		for (Bullet bullet : monsterBulletList) {
			for (SpaceTurret turret : turretList) {
			//ship shield
				if (turret.contains(bullet)) {
				// deduct ship health
					if (turret.health > 0) {
						turret.deductHealth();
					}
					bullet.state = 0;
				}
			}
		}
	}
	
	// Black hole will kill all the monsters.
	public void monsterBlackHole(boolean blackHoleActive, BlackHole hole, ArrayList<Monster> monsterList) {
		if (blackHoleActive) {
			for (Monster monster : monsterList) {
				if (!monster.isBoss) {
					if (monster.body.getX() <= 0) { // if goes off screen
						while (monster.currentHealth > 0) {
							monster.deductHealth();
						}	
					}
				}
			}
		}
	}
	
	// Add  the bullets the monsters shoot to the monster bullet list.
//	public void monsterShoot(BulletFlurry bulletFlurry, ArrayList <Monster> horde) {
//		for (Monster monster : horde) {
//			if (monster.newBullet != null) {
//				bulletFlurry.onScreenBullets.add(monster.newBullet);
//			}
//		}
//	}
	
	// Add the bullets the turrets shoot to the turret bullet list.
	public void turretShoot(BulletFlurry bulletFlurry, ArrayList <SpaceTurret> turretList) {
		for (SpaceTurret turret : turretList) {
			if (turret.newBullet != null) {
				bulletFlurry.onScreenBullets.add(turret.newBullet);
			}
		}
	}
	
	public void draw(Graphics g, Money userMoney) {
		// draw adding money text
		if (!userMoney.tempPair.isEmpty()) {
			for (Point point : userMoney.tempPair) {
				if (currentMonster != null) {
					userMoney.draw(g, (int)point.getX(), (int)point.getY(), currentMonster);
				}
			}
		}
		
	}
	
}