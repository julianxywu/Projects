import java.awt.Color;
import java.awt.Graphics;
import java.math.*;
import java.util.Timer;
import java.util.TimerTask;

public class Monster {
	int x, y, newDX, newDY, state, startingHealth, currentHealth, shootingChance=10, moneyWorth;
	double dx=3, dy=0, trollDX = 15;
	boolean trigger, isBoss=false, turnRed = false, specialAttack6IsOnScreen= false;
	int boss1Health=200, boss2Health=800, boss3Health=2000, trollBossHealth=1000;
	int level;
	MonsterBody body;
	MonsterLegs legs;
	MonsterEyes eyes;
	public Monster(int level, int positionX, int positionY, int health, boolean isBoss) {
		body = new MonsterBody(level, positionX, positionY);
		legs = new MonsterLegs(level, body.getX(), body.getY(), body.getWidth(), body.getHeight());
		eyes = new MonsterEyes(level, body.getX(), body.getY(), body.getWidth(), body.getHeight());
		state = 1; // alive
		x = positionX;
		y = positionY;
		this.isBoss = isBoss;
		startingHealth = health;
		currentHealth = startingHealth;
		
		if (startingHealth == 1) {
			shootingChance = 2;
			moneyWorth = 10;
		}
		else if (startingHealth == 2) {
			shootingChance = 5;
			moneyWorth = 20;
			}
		else if (startingHealth == 3) {
			shootingChance = 8;
			moneyWorth = 30;
		}
		else if (startingHealth == 5) {
			shootingChance = 12;
			moneyWorth = 50;
		}
		else if (startingHealth == 8) {
			shootingChance = 15;
			moneyWorth = 80;
			}
		else if (startingHealth == 10) {
			shootingChance = 30;
			moneyWorth = 100;
		}
		// STAGE 10 BOSS
		else if (startingHealth == boss1Health) {
			shootingChance = 20;
			moneyWorth = 1000;
		}
		// STAGE 19 MINI BOSS
		else if (startingHealth == trollBossHealth) {
			setVelocityX(trollDX);
			shootingChance = 2;
			moneyWorth = 5000;
		// STAGE 20 BOSS
		}
		else if (startingHealth == boss2Health) {
			shootingChance = 30;
			moneyWorth = 3000;
		// STAGE 30 BOSS
		}
		else if (startingHealth == boss3Health) {
			shootingChance = 100;
			moneyWorth = 10000;
		}
		this.level = level;
	}
	
	// Percent Chance boolean function.
	public boolean percentChance(double n) {
		if ((Math.random() * 100) <= n) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Contains Point function.
	public boolean containsPoint(int x, int y) {
		if (body.getX() <= x & // if they intersect
				x <= (body.getX() + body.getWidth()) &
				body.getY() <= y &
				y <= (body.getY()) + body.getHeight() / 2) {
			return true;
		}
		else {
			return false;
		}
	}
	// Contains bullet.
	public boolean containsBullet(Bullet bullet) {
		if (bullet.isMissile) {
			int tempX = bullet.getX() - 30;
			int tempY = bullet.getY();
			while (tempX < bullet.getX() + bullet.missileWidth + 30) {
				if (containsPoint(tempX, bullet.getY())) {
					return true;
				}
				tempX++;
			}
			return false;
		}
		else if (containsPoint(bullet.getX(), bullet.getY())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Contains sword.
		public boolean containsSword(Sword sword) {
			if (containsPoint(sword.getX(), sword.getY())) {
				return true;
			}
			else {
				return false;
			}
		}
	
	// deduct health
	public void deductHealth() {
		currentHealth--;
		if (currentHealth == 0) {
			state = 0;
		}
	}
	
	// Setters and Getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	// Setting X and Y Velocities
	public void setVelocityX(double dx) {
		this.dx = dx;
	}
	
	public void setVelocityY(double dy) {
		this.dy = dy;
	}
	
	public double getdX() {
		return dx;
	}
	
	public double getdY() {
		return dy;
	}
	
	public boolean isAlive() {
		if (state == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// create new bullet
	Bullet newBullet;
	
	public void normalAttack(BulletFlurry bulletFlurry, int straightOrSlant) {
		newBullet = new Bullet(straightOrSlant, 1, body.getX(), body.getY(), body.getWidth(), false, false);
		bulletFlurry.onScreenBullets.add(newBullet);
	}
	
	public void specialAttack1(BulletFlurry bulletFlurry) { // shooting (------   ------)
		int tempX = body.getX() - 300;
		int tempY = body.getY();
		while (tempX < body.getX() - 30) {
			newBullet = new Bullet(0, 1, tempX, tempY, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempX += 6;
		}
		tempX = body.getX() + 300;
		tempY = body.getY();
		while (tempX > body.getX() + 30) {
			newBullet = new Bullet(0, 1, tempX, tempY, body.getWidth(), false ,false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempX -= 6;
		}
	}
	
	public void specialAttack2(BulletFlurry bulletFlurry) { // shooting (^)
		int tempX = body.getX() - 15;
		int tempY = body.getY();
		while (tempX < body.getX()) {
			newBullet = new Bullet(0, 1, tempX, tempY, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempX += 4;
			tempY -= 3;
		}
		tempX = body.getX() + 15;
		tempY = body.getY();
		while (tempX > body.getX()) {
			newBullet = new Bullet(0, 1, tempX, tempY, body.getWidth(), false ,false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempX -= 6;
			tempY -= 5;
		}
	}
	
	public void specialAttack3(BulletFlurry bulletFlurry) { // shooting ( | )
		int tempX = body.getX();
		int tempY = body.getY() + 30;
		while (tempY > body.getY()) {
			newBullet = new Bullet(0, 1, tempX, tempY, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempY -= 6;
		}
	}	
	
	public void specialAttack4(BulletFlurry bulletFlurry) { // shooting ( |  | )
		int tempX1 = body.getX() - 40;
		int tempX2 = body.getX() + 40;
		int tempY = body.getY() + 80;
		while (tempY > body.getY()) {
			newBullet = new Bullet(0, 1, tempX1, tempY, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			newBullet = new Bullet(0, 1, tempX2, tempY, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			tempY -= 6;
		}
	}
	
	public void specialAttack5(BulletFlurry bulletFlurry) { // shooting ( <> )
		int tempX = body.getX() - 20;
		int tempY1 = body.getY();
		int tempY2 = body.getY();
		while (tempX < body.getX()) {
			tempY1 += 5;
			tempY2 -= 5;
			tempX += 6;
			newBullet = new Bullet(0, 1, tempX, tempY1, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			newBullet = new Bullet(0, 1, tempX, tempY2, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
		}
		tempX = body.getX() + 20;
		tempY1 = body.getY();
		tempY2 = body.getY();
		while (tempX > body.getX()) {
			tempY1 += 5;
			tempY2 -= 5;
			tempX -= 6;
			newBullet = new Bullet(0, 1, tempX, tempY1, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
			newBullet = new Bullet(0, 1, tempX, tempY2, body.getWidth(), false, false);
			bulletFlurry.onScreenBullets.add(newBullet);
		}
	}
	
	public void specialAttack6(BulletFlurry bulletFlurry) { // shooting tunnels
		Timer timer = new Timer();
		
		TimerTask attack6counter = new TimerTask() {
			public void run() {
				specialAttack6IsOnScreen = false;
			}
		};
		
		TimerTask task = new TimerTask() {
			public void run() {
				int tempX1 = body.getX() - 300;
				int tempX2 = body.getX() + 300;
				int tempY = body.getY() + 100;
				
				while (tempX1 < body.getX() - 60) {
					newBullet = new Bullet(0, 1, tempX1, tempY, body.getWidth(), false, false);
					bulletFlurry.onScreenBullets.add(newBullet);
					newBullet = new Bullet(0, 1, tempX2, tempY, body.getWidth(), false, false);
					bulletFlurry.onScreenBullets.add(newBullet);
					tempX1 += 3;
					tempX2 -= 3;
					tempY -= 1;
				}
				while (tempY >= -200) {
					newBullet = new Bullet(0, 1, tempX1, tempY, body.getWidth(), false, false);
					bulletFlurry.onScreenBullets.add(newBullet);
					newBullet = new Bullet(0, 1, tempX2, tempY, body.getWidth(), false, false);
					bulletFlurry.onScreenBullets.add(newBullet);
					tempY -=3;
				}
				turnRed = false;
				timer.schedule(attack6counter, 10000);
			}
		};
		if (!specialAttack6IsOnScreen) {
			specialAttack6IsOnScreen = true;
			turnRed = true;
			timer.schedule(task, 3000);
		}
		
	}
	
//	public void creationAttack(Monster monster, Horde horde) {
//		// monster = new Monster(1, 400, 400, 5, false);
//		horde.horde.add(monster);
//	}
	
	// Make the monster shoot on occasion.
	public void shoot(boolean iceFieldActive, BulletFlurry bulletFlurry) {
		if (iceFieldActive && !isBoss) {
			newBullet = null;
		}
		// normal monsters
		else if ((int)(Math.random() * 100) <= shootingChance) {
			if (startingHealth == 10) {
				normalAttack(bulletFlurry, 100);
			}
			else if (!isBoss) {
				normalAttack(bulletFlurry, 0);
			}
		}
		else {
			newBullet = null;
		}
		
		if (isBoss) {
			if (startingHealth == boss1Health) { // STAGE 10 BOSS
				if (percentChance(2)) { // 2% chance
					specialAttack1(bulletFlurry);
				}
				else if (percentChance(5)) { // 5% chance
					specialAttack2(bulletFlurry);
				}
				else if (percentChance(shootingChance)) {
					normalAttack(bulletFlurry, 100);
				}
			}
			else if (startingHealth == trollBossHealth) // STAGE 19 MINI BOSS
			{
				
			}
			else if (startingHealth == boss2Health) // STAGE 20 BOSS
			{
				if (percentChance(8)) {
					specialAttack2(bulletFlurry);
				}
				else if (percentChance(7)) {
					specialAttack4(bulletFlurry);
				}
				else if (percentChance(5)) {
					specialAttack5(bulletFlurry);
				}
				else if (percentChance(shootingChance)) {
					normalAttack(bulletFlurry, 100);
				}
			}
			else if (startingHealth == boss3Health) // STAGE 30 BOSS
			{
				if (percentChance(10)) {
					specialAttack3(bulletFlurry);
				}
				else if (percentChance(8)) {
					specialAttack5(bulletFlurry);
				}
				else if (percentChance(4)) {
					specialAttack6(bulletFlurry);
				}
				else if (percentChance(shootingChance)) {
					normalAttack(bulletFlurry, 100);
				}
			}
		}
	}
	
	public void moveTowards(int xCoor, int yCoor) {
		newDX = (xCoor - body.getX()) / 18;
		newDY = (yCoor - body.getY()) / 20;
	}
	
	// Make the monster move.
	public void move(boolean blackHoleActive, boolean iceFieldActive) {
		
		if (blackHoleActive && !isBoss) {
			setVelocityX(newDX);
			setVelocityY(newDY);
			body.height -= 3;
			body.width -= 3;
			legs.height--;
			legs.width--;
			eyes.radius--;	
		}
		else if (iceFieldActive && level != -1) {
			trigger = true;
			setVelocityX(0);
			setVelocityY(0);
		}
		else {
			if (trigger) {
				setVelocityX(3);
				trigger = false;
			}
			if (!isBoss) {
				if (Math.abs(x - body.getX()) > 35) {
					setVelocityX(-1 * dx);
				}
			}
			else {
				if (Math.abs(x - body.getX()) > 150) {
					setVelocityX(-1 * dx);
				}
			}
		}
		body.setX(body.getX() + (int)dx);
		body.setY(body.getY() + (int)dy);
		legs.setX1(legs.getX1() + (int)dx);
		legs.setX2(legs.getX2() + (int)dx);
		legs.setY(legs.getY() + (int)dy);
		eyes.setX1(eyes.getX1() + (int)dx);
		eyes.setX2(eyes.getX2() + (int)dx);
		eyes.setY(eyes.getY() + (int)dy);
	}
	
	// Draw Monster
	public void draw(Graphics g) {
		if (startingHealth == 1) {
			g.setColor(Color.WHITE);
		}
		else if (startingHealth == 2) {
			g.setColor(Color.RED);
		}
		else if (startingHealth == 3) {
			g.setColor(Color.CYAN);
		}
		else if (startingHealth == 5) {
			g.setColor(Color.BLUE);
		}
		else if (startingHealth == 8) {
			g.setColor(Color.MAGENTA);
		}
		else if (startingHealth == 10) {
			g.setColor(Color.ORANGE);
		}
		else if (startingHealth == trollBossHealth) {
			g.setColor(Color.GREEN);
		}
		else if (turnRed) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.WHITE);
		}
		body.draw(g);
		legs.draw(g);
		eyes.draw(g);
	}
}
