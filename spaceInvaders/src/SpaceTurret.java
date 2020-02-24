import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SpaceTurret {
	int x = 400, y = 550, width=20, height=20, health = 10;
	int bulletDamage, bulletSpeed, shootingChance = 30;
	int state;
	ShipCannon cannon;
	
	public SpaceTurret(int shipX, int shipY, int bulletDamage, int bulletSpeed) {
		state = 1; // alive
		x = shipX - 11;
		y = shipY + height + 5;
		cannon = new ShipCannon(1, x+1, y+1, width, height);
		this.bulletSpeed = bulletSpeed;
		this.bulletDamage = bulletDamage;
	}
	
	// Contains function
	public boolean contains(Bullet bullet) {
		if (x <= bullet.getX() & bullet.getX() <= (x + width) &   // if they intersect 
				y <= bullet.getY() & bullet.getY() <= (y + height)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isAlive() {
		if (state == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void deductHealth() {
		health--;
		if (health == 0) {
			state = 0;
		}
	}
	
	Bullet newBullet;
	public void shoot() {
		 if ((int)(Math.random() * 100) <= shootingChance) {
			newBullet = new Bullet(47, bulletDamage, getX(), getY(), getWidth(), true, false);
		}
		else {
			newBullet = null;
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public void setX(int newX) {
		x = newX;
	}
	public void setY(int newY) {
		y = newY;
	}
	public void draw(Graphics g) {
		if (health > 0) {
			g.fillArc(x, y, width, height, 0, 180);
			g.setFont(new Font("Dialog", Font.PLAIN, 10));
			g.drawString(health + "HP", x-1, y + 20);
			cannon.draw(g);
		}
	}
}
