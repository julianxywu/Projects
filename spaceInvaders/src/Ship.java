import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Ship {
	int x, y, currLevel;
	double dx=0, dy=0;
	ShipBody body;
	ShipCannon missileCannon, cannon1, cannon2a, cannon2b, cannon3x, cannon3a, cannon3b, cannon4a, cannon4b, cannon4aa, cannon4bb, cannon5x, cannon5a, cannon5b, cannon5aa, cannon5bb;
	ArrayList <ShipCannon> cannonList1, cannonList2, cannonList3;
	ShipWings wings;
	ShipHealth shipHealth;
	ShipShield shield;
	
	public Ship(int level, int startingHealth, int shieldUpgrade, int shieldRecoveryUpgrade) {
		body = new ShipBody(level);
		wings = new ShipWings(level, body.getX(), body.getY(), body.getWidth(), body.getHeight());
		shipHealth = new ShipHealth(startingHealth);
		shield = new ShipShield(level, shieldUpgrade, shieldRecoveryUpgrade, body.getX(), body.getY(), body.getWidth(), body.getHeight());
		x = body.getX() + (body.getWidth() / 2);
		//y = body.getY() + ((body.getHeight() + cannon1.getHeight1()) / 2);
		y = body.getY();
		currLevel = level;
		missileCannon = new ShipCannon(1, body.getX(), body.getY(), body.getWidth(), body.getHeight());
		if (level == 1) {
			cannonList1 = new ArrayList <ShipCannon>();
			cannon1 = new ShipCannon(1, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannonList1.add(cannon1);
			cannonList1.add(missileCannon);
		}
		else if (level == 2) {
			cannonList1 = new ArrayList <ShipCannon>();
			cannon1 = null;
			cannon2a = new ShipCannon(21, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon2b = new ShipCannon(22, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannonList1.add(cannon2a);
			cannonList1.add(cannon2b);
			cannonList1.add(missileCannon);
		}
		else if (level == 3) {
			cannonList1 = new ArrayList <ShipCannon>();
			cannonList2 = new ArrayList <ShipCannon>();
			cannon2a = null; cannon2b = null;
			cannon3x = new ShipCannon(30, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon3a = new ShipCannon(31, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon3b = new ShipCannon(32, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannonList1.add(cannon3x);
			cannonList1.add(missileCannon);
			cannonList2.add(cannon3a);
			cannonList2.add(cannon3b);
		}
		else if (level == 4) {
			cannonList1 = new ArrayList <ShipCannon>();
			cannonList2 = new ArrayList <ShipCannon>();
			cannon3x = null; cannon3a = null; cannon3b = null;
			cannon4a = new ShipCannon(41, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon4b = new ShipCannon(42, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon4aa = new ShipCannon(411, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon4bb = new ShipCannon(422, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannonList1.add(cannon4a);
			cannonList1.add(cannon4b);
			cannonList1.add(missileCannon);
			cannonList2.add(cannon4aa);
			cannonList2.add(cannon4bb);
		}
		else if (level == 5) {
			cannonList1 = new ArrayList <ShipCannon>();
			cannonList2 = new ArrayList <ShipCannon>();
			cannonList3 = new ArrayList <ShipCannon>();
			cannon4a = null; cannon4b = null; cannon4aa = null; cannon4bb = null;
			cannon5x = new ShipCannon(50, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon5a = new ShipCannon(51, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon5b = new ShipCannon(52, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon5aa = new ShipCannon(511, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannon5bb = new ShipCannon(522, body.getX(), body.getY(), body.getWidth(), body.getHeight());
			cannonList1.add(cannon5x);
			cannonList1.add(missileCannon);
			cannonList2.add(cannon5a);
			cannonList2.add(cannon5b);
			cannonList3.add(cannon5aa);
			cannonList3.add(cannon5bb);
		}
	}
	
	// Contains function.
	public boolean contains(Bullet bullet) {
		//ship body
		if (currLevel == 1) {
			if (body.contains(bullet) || cannon1.contains(bullet) || wings.contains(bullet)) {
				return true;
			}
			else {
				return false;
			}
		}
		if (currLevel == 2) {
			if (body.contains(bullet) || cannon2a.contains(bullet) || cannon2b.contains(bullet) || wings.contains(bullet)) {
				return true;
			}
			else {
				return false;
			}
		}
		if (currLevel == 3) {
			if (body.contains(bullet) || cannon3x.contains(bullet) || cannon3a.contains(bullet) || cannon3b.contains(bullet) || 
				wings.contains(bullet)) {
					return true;
			}
			else {
				return false;
			}
		}
		if (currLevel == 4) {
			if (body.contains(bullet) || cannon4a.contains(bullet) || cannon4b.contains(bullet) || cannon4aa.contains(bullet) || 
				cannon4bb.contains(bullet) || wings.contains(bullet)) {
					return true;
			}
			else {
				return false;
			}
		}
		if (currLevel == 5) {
			if (body.contains(bullet) || cannon5x.contains(bullet) || cannon5a.contains(bullet) || cannon5b.contains(bullet) || 
				cannon5aa.contains(bullet)  || cannon5bb.contains(bullet) || wings.contains(bullet)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// Setters and Getters
	public int getX() {
		if (x < 0) {
			return x % 800 + 800;
		}
		else {
			return x % 800;
		}
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
	public void setVelocityX(int dx) {
		this.dx = dx;
	}
	
	public void setVelocityY(int dy) {
		this.dy = dy;
	}
	
	public double getdX() {
		return dx;
	}
	
	public double getdY() {
		return dy;
	}
	
	// Making the ship move
	public void move() {
		
		// move wings
		if (wings.getY() + wings.getHeight() > 600) { 
			wings.setY(600 - wings.getHeight());
		}
		if (wings.getY() + wings.getHeight() > 600 - 12) {
			if (currLevel == 5) {
				wings.setY(600 - wings.getHeight() - 12);
			}
		}	
		
		// move cannons
		for (ShipCannon cannon : cannonList1) {
			if (cannon.getY() + cannon.getHeight1() > 600 - wings.getHeight() - 2) {
				if (currLevel == 1 || currLevel == 2 || currLevel == 4) {
					cannon.setY(600 - cannon.getHeight1() - wings.getHeight() - 2);
				}
			}
			if (cannon.getY() + cannon.getHeight1() > 600 - wings.getHeight() - 4) {
				if (currLevel == 3) {
					cannon.setY(600 - cannon.getHeight1() - wings.getHeight() - 4);
				}
			}
			if (cannon.getY() + cannon.getHeight1() > 600 - wings.getHeight() - 14) {
				if (currLevel == 5) {
					cannon.setY(600 - cannon.getHeight1() - wings.getHeight() - 14);
				}
			}
		}
		if (cannonList2 != null) {
			for (ShipCannon cannon : cannonList2) {
				if (cannon.getY() + cannon.getHeight1() > 600 - wings.getHeight()) {
					if (currLevel == 3) {
						cannon.setY(600 - cannon.getHeight1() - wings.getHeight());
					}
					if (currLevel == 4) {
						cannon.setY(600 - cannon.getHeight2() - 8);
					}
				}
				if (cannon.getY() + cannon.getHeight2() > 600 - 7) {
					if (currLevel == 5) {
						cannon.setY(600 - cannon.getHeight2() - 7);
					}
				}
			}
		}
		if (cannonList3 != null) {
			for (ShipCannon cannon : cannonList3) {
				if (cannon.getY() + cannon.getHeight3() > 600) {
					if (currLevel == 5) {
						cannon.setY(600 - cannon.getHeight3());
					}
				}
			}
		}
		
		//move body and shield
		if (body.getY() + body.getHeight() > 600 - 4) {
			if (currLevel == 1) {
				body.setY(600 - body.getHeight() - 4);
				shield.setY(600 - shield.getHeight() + 10);
			}
		}
		if (body.getY() + body.getHeight() > 600 - 2) {
			if (currLevel == 2 || currLevel == 3 || currLevel == 4) {
				body.setY(600 - body.getHeight() - 2);
				if (currLevel == 3) {
					shield.setY(600 - shield.getHeight() + 12);
				}
				if (currLevel == 2 || currLevel == 4) {
					shield.setY(600 - shield.getHeight() + 14);
				}
			}
		}
		if (body.getY() + body.getHeight() > 600 - 6) {
			if (currLevel == 5) {
				body.setY(600 - body.getHeight() - 6);
				shield.setY(600 - shield.getHeight() + 12);
			}
		}
		
		// if not off-screen
		setX(getX() + (int)dx);
		setY(getY() + (int)dy);
		body.setX(body.getX() + (int)dx);
		body.setY(body.getY() + (int)dy);
		wings.setX1(wings.getX1() + (int)dx);
		wings.setX2(wings.getX2() + (int)dx);
		wings.setY(wings.getY() + (int)dy);
		shield.setX(shield.getX() + (int)dx);
		shield.setY(shield.getY() + (int)dy);
		missileCannon.setX(missileCannon.getX() + (int)dx);
		missileCannon.setY(missileCannon.getY() + (int)dy);
		if (cannon1 != null) {
			cannon1.setX(cannon1.getX() + (int)dx);
			cannon1.setY(cannon1.getY() + (int)dy);
		}
		if (cannon2a != null && cannon2b != null) {
			cannon2a.setX(cannon2a.getX() + (int)dx);
			cannon2a.setY(cannon2a.getY() + (int)dy);
			cannon2b.setX(cannon2b.getX() + (int)dx);
			cannon2b.setY(cannon2b.getY() + (int)dy);
		}
		if (cannon3x != null && cannon3a != null && cannon3b != null) {
			cannon3a.setX(cannon3a.getX() + (int)dx);
			cannon3a.setY(cannon3a.getY() + (int)dy);
			cannon3b.setX(cannon3b.getX() + (int)dx);
			cannon3b.setY(cannon3b.getY() + (int)dy);
			cannon3x.setX(cannon3x.getX() + (int)dx);
			cannon3x.setY(cannon3x.getY() + (int)dy);
		}
		if (cannon4a != null && cannon4b != null && cannon4aa != null && cannon4bb != null) {
			cannon4a.setX(cannon4a.getX() + (int)dx);
			cannon4a.setY(cannon4a.getY() + (int)dy);
			cannon4b.setX(cannon4b.getX() + (int)dx);
			cannon4b.setY(cannon4b.getY() + (int)dy);
			cannon4aa.setX(cannon4aa.getX() + (int)dx);
			cannon4aa.setY(cannon4aa.getY() + (int)dy);
			cannon4bb.setX(cannon4bb.getX() + (int)dx);
			cannon4bb.setY(cannon4bb.getY() + (int)dy);
		}
		if (cannon5x != null && cannon5a != null && cannon5b != null && cannon5aa != null && cannon5bb != null) {
			cannon5x.setX(cannon5x.getX() + (int)dx);
			cannon5x.setY(cannon5x.getY() + (int)dy);
			cannon5a.setX(cannon5a.getX() + (int)dx);
			cannon5a.setY(cannon5a.getY() + (int)dy);
			cannon5b.setX(cannon5b.getX() + (int)dx);
			cannon5b.setY(cannon5b.getY() + (int)dy);
			cannon5aa.setX(cannon5aa.getX() + (int)dx);
			cannon5aa.setY(cannon5aa.getY() + (int)dy);
			cannon5bb.setX(cannon5bb.getX() + (int)dx);
			cannon5bb.setY(cannon5bb.getY() + (int)dy);
		}
	}
	
	
	// Draw Ship
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		body.draw(g);
		wings.draw(g);
		shipHealth.draw(g);
		shield.draw(g);
		if (cannon1 != null) {
			cannon1.draw(g);
		}
		if (cannon2a != null && cannon2b != null) {
			cannon2a.draw(g);
			cannon2b.draw(g);
		}
		if (cannon3x != null && cannon3a != null && cannon3b != null) {
			cannon3x.draw(g);
			cannon3a.draw(g);
			cannon3b.draw(g);
		}
		if (cannon4a != null && cannon4b != null && cannon4aa != null && cannon4bb != null) {
			cannon4a.draw(g);
			cannon4b.draw(g);
			cannon4aa.draw(g);
			cannon4bb.draw(g);
		}
		if (cannon5x != null && cannon5a != null && cannon5b != null && cannon5aa != null && cannon5bb != null) {
			cannon5x.draw(g);
			cannon5a.draw(g);
			cannon5b.draw(g);
			cannon5aa.draw(g);
			cannon5bb.draw(g);
		}
	}
}
