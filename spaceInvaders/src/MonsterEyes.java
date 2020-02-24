import java.awt.Color;
import java.awt.Graphics;

public class MonsterEyes {
	int radius, x1, x2, y, upgrade;

	public MonsterEyes(int upgrade, int bodyX, int bodyY, int bodyWidth, int bodyHeight) {
		// BOSSES
		if (upgrade == 0) { // STAGE 10 BOSS
			radius = 10;
		}
		if (upgrade == -1) { // STAGE 19 MINI-TROLL BOSS
			radius = 5;
		}
		if (upgrade == -2) { // STAGE 20 BOSS
			radius = 15;
		}
		if (upgrade == -3) { // STAGE 30 BOSS
			radius = 10;
		}
		// Normal Monsters
		else if (upgrade > 0) {
			if (upgrade < 10) {
				radius = 4;
			}
			else if (upgrade < 20) {
				radius = 6;
			}
		}
		
		x1 = bodyX + (bodyWidth/3) - (radius/2);
		x2 = bodyX + (bodyWidth * 2/3) - (radius/2);
		y = bodyY + bodyHeight/4;
		this.upgrade = upgrade;
	}

	//Setters and Getters
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public void setX1(int newX) {
		this.x1 = newX;
	}
	
	public void setX2(int newX) {
		this.x2 = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	//Draw
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		if (upgrade == -3) {
			g.setColor(Color.MAGENTA);
			g.fillOval(getX1(), getY() + 20, radius, radius);
			g.fillOval(getX2(), getY() + 20, radius, radius);
			g.fillOval((getX1() + getX2()) / 2, getY(), radius, radius);
		}
		else if (upgrade == -1) {
			g.fillOval(getX1(), getY(), radius, radius);
			g.fillOval(getX2(), getY(), radius, radius);
			g.fillOval((getX1() + getX2()) / 2, getY() + 10, radius, radius + 4);
		}
		else {
			g.fillOval(getX1(), getY(), radius, radius);
			g.fillOval(getX2(), getY(), radius, radius);
		}
	}
}
