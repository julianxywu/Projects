import java.awt.Color;
import java.awt.Graphics;

public class MonsterLegs {
	int height, width, x1, x2, y;
	int upgrade;

	public MonsterLegs(int upgrade, int bodyX, int bodyY, int bodyWidth, int bodyHeight) {
		// BOSSES
		if (upgrade == 0) { // STAGE 10 BOSS
			height = 20;
			width = 10;
		}
		if (upgrade == -1) { // STAGE 19 MINI-TROLL BOSS
			width = 5;
			height = 20;
		}
		if (upgrade == -2) { // STAGE 20 BOSS
			height = 20;
			width = 12;
		}
		if (upgrade == -3) { // STAGE 30 BOSS
			height = 20;
			width = 12;
		}
		// Normal Monsters.
		else if (upgrade > 0) {
			if (upgrade < 10) {
				height = 8;
				width = 3;
			}
			else if (upgrade < 20) {
				height = 8;
				width = 4;
			}
		}
		
		x1 = bodyX + (bodyWidth/4) - (width/2);
		x2 = bodyX + (bodyWidth * 3/4) - (width/2);
		y = bodyY + bodyHeight/2;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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
		if (upgrade == -2) {
			// 4 legs
			g.fillRect(getX1() - 10, getY(), width, height);
			g.fillRect(getX1() + 15, getY() - 5, width, height);
			g.fillRect(getX2() - 15, getY() - 5, width, height);
			g.fillRect(getX2() + 10, getY(), width, height);
			// 2 horns
			g.fillRect(getX1(), getY() - 60, width, height + 10);
			g.fillRect(getX2(), getY() - 60, width, height + 10);
		}
		else if (upgrade == -3) {
			// 3 legs
			g.fillRect(getX1(), getY(), width, height + 10);
			g.fillRect(getX2(), getY(), width, height + 10);
			g.fillRect((getX1() + getX2()) / 2, getY() -10, width, height);
		}
		else if (upgrade == -1) {
			g.setColor(Color.GREEN);
			// left leg
			g.fillRect(getX1(), getY() + 20, width, height - 5);
			g.fillRect(getX1() - height + 5, getY() + 10 + height, height - 5, width);
			// right leg
			g.fillRect(getX2(), getY() + 20, height - 5, width);
			g.fillRect(getX2() + height - 5, getY() + 20, width, height);
			// left arm
			g.fillRect(getX1() - height, getY(), width, height - 5);
			g.fillRect(getX1() - height, getY(), height, width);
			// right arm
			g.fillRect(getX2() + 5, getY(), height, width);
			g.fillRect(getX2() + height, getY() - 12, width, height - 3);
		}
		else {
			g.fillRect(getX1(), getY(), width, height);
			g.fillRect(getX2(), getY(), width, height);
		}
		
	}
}
