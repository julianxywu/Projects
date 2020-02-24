import java.awt.Graphics;
import java.util.ArrayList;

public class BulletFlurry {
	public ArrayList <Bullet> onScreenBullets = new ArrayList <Bullet> ();
	public ArrayList <Bullet> currentBullets = new ArrayList <Bullet> ();
	public int bulletNumber;
	
	public BulletFlurry() {
	}
	
	//Checks to see if the bullets are on the screen
	public boolean onScreen(Bullet bullet) {
		if ((bullet.getY() < 0) || (bullet.getY() > 600)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void bulletCheck() {
		ArrayList <Bullet> currentBullets = new ArrayList <Bullet> ();
		for (Bullet bullet : onScreenBullets) {
			if (onScreen(bullet)) {
				bullet.move();
				currentBullets.add(bullet);
			}
			onScreenBullets = currentBullets;
			bulletNumber = onScreenBullets.size();
		}
	}
	
	public void deleteBullets() {
		int i = 0;
		while (i < onScreenBullets.size()) {
			if (!onScreenBullets.get(i).isAlive()) {
				onScreenBullets.remove(i);
			}
			i++;
		}		
		bulletNumber = onScreenBullets.size();
	}
	
	public int getBulletNumber() {
		return bulletNumber;
	}
	
	public void draw(Graphics g) {
		for (Bullet bullet : onScreenBullets) {
			if (bullet.state == 1) {
				bullet.draw(g);
			}
		}
	}
	
	public void move() {
		for (Bullet bullet : onScreenBullets) {
			bullet.move();
		}
	}
}

