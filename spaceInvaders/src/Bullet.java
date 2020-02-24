import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Create Bullets

public class Bullet {
	int height=3, width=3, missileHeight=100, missileWidth=60;
	int state, damage;
	int startingX, startingY;
	boolean isTurret = false, isMissile = false;
	int dx=0, dy=0;
	String name1, name2;
	public Bullet(int upgradeSpeed, int upgradeDamage, int cannonX, int cannonY, int cannonWidth, boolean isTurret, boolean isMissile) {
		state = 1; // alive
		startingX = cannonX + (cannonWidth / 2);
		startingY = cannonY - 2;
		damage = upgradeDamage;
		// FOR MISSILES
		if (Math.random() <= 0.5) {
			name1 = "LITTLE";
			name2 = "MAN";
		}
		else {
			name1 = "FAT";
			name2 = "BOY";
		}

		this.isTurret = isTurret;
		if (isTurret) {
			dx = (int)((Math.random() * 6) - 3);
			dy = -6;
		}
		
		this.isMissile = isMissile;
		if (isMissile) {
			dx = (int)((Math.random() * 4) - 2);
			dy = (int)(Math.random() * -4 - 6);
			damage = 20;
		}
		if (upgradeSpeed == 0) {
			dy = 5;
		}
		else if (upgradeSpeed == 1) {
			dy = -4;
		}
		else if (upgradeSpeed == 2) {
			dy = -5;
		}
		else if (upgradeSpeed == 3) {
			dy = -6;
		}
		else if (upgradeSpeed == 4) {
			dy = -7;
		}
		else if (upgradeSpeed == 100) {
			dy = 5;
			dx = (int)(Math.random() * 10);
			if (Math.random() * 10 <= 5) {
				dx = (int)(Math.random() * -10);
			}
		}

	}
	
	public int getX() {
		return startingX + dx;
	}
	
	public int getY() {
		return startingY + dy;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int newX) {
		this.startingX = newX;
		//this.dx = 0;
	}
	
	public void setY(int newY) {
		this.startingY = newY;
		//this.dy = 0;
	}
	
	public void setVelocityY(int newY) {
		this.dy = newY;
	}
	
	public boolean isAlive() {
		if (state == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void draw(Graphics g) {
		if (isMissile) {
			g.setColor(Color.WHITE);
			if (getX() < 0) {  // Negative X
				g.fillRect((getX() - (missileWidth/2)) % 800 + 800, getY(), missileWidth, missileHeight);
				g.setColor(Color.RED);
				g.fillArc((getX() - (missileWidth/2)) % 800 + 800, getY() - (missileWidth/2), missileWidth, missileWidth, 0, 180);
				g.setFont(new Font("Dialog", Font.BOLD, 30));
				g.drawString(name1, (getX() - 25 + (3 * (6 - name1.length()))) % 800 + 800, getY() + 10);
				g.drawString(name2, (getX() - 18) % 800 + 800, getY() + 20);
			}
			else { // Positive X
				g.fillRect((getX() - (missileWidth/2)) % 800, getY(), missileWidth, missileHeight);
				g.setColor(Color.RED);
				g.fillArc((getX() - (missileWidth/2)) % 800, getY() - (missileWidth/2), missileWidth, missileWidth, 0, 180);
				g.setFont(new Font("Dialog", Font.BOLD, 15));
				g.drawString(name1, (getX() - 25 + (3 * (6 - name1.length()))) % 800, getY() + 40);
				g.setFont(new Font("Dialog", Font.BOLD, 15));
				g.drawString(name2, (getX() - 18) % 800, getY() + 70);
			}
		}
		else {
			if (damage == 1) {
				g.setColor(Color.WHITE);
			}
			if (damage == 2) {
				g.setColor(Color.CYAN);
			}
			if (damage == 3) {
				g.setColor(Color.BLUE);
			}
			if (damage == 4) {
				g.setColor(Color.MAGENTA);
			}
			if (damage == 5) {
				g.setColor(Color.ORANGE);
			}
			if (isTurret) {
				g.setColor(Color.GREEN);
			}
			if (getX() < 0) {  // Negative X
				g.fillRect(getX() % 800 + 800, getY(), width, height);
			}
			else { // Positive X
				g.fillRect(getX() % 800, getY(), width, height);
			}
		}
	}
	
	// Making the bullet move
	public void move() {
		if (getX() < 0) {  // Negative X
			setX(getX() % 800 + 800 + (int)dx);
		}
		else { // Positive X
			setX(getX() % 800 + (int)dx);
		}
		
		// Bullet moves up
		setY(getY() + (int)dy);
	}
}
