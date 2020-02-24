import java.awt.Color;
import java.awt.Graphics;

public class MonsterBody {
	int height, width, startingX, startingY;
	int dx=0, dy=0;
	int startAngle=0, endAngle=180;
	int upgrade;
	public MonsterBody(int upgrade, int startingX, int startingY) {
		// BOSSES
		if (upgrade == 0) { // STAGE 10 BOSS
			height = 100;
			width = 98;
		}
		if (upgrade == -1) { // STAGE 19 MINI-TROLL BOSS
			height = 50;
			width = 20;
		}
		if (upgrade == -2) { // STAGE 20 BOSS
			height = 100;
			width = 120;
		}
		if (upgrade == -3) { // STAGE 30 BOSS
			height = 200;
			width = 80;
		}
		
		// Normal monsters
		else if (upgrade > 0) {
			if (upgrade < 3) { // 10 - 16 monsters
				height = 32;
				width = 30;	
			}
			else if (upgrade < 7) { // 18 - 20 monsters
				height = 29;
				width = 27;
			}
//			else if (upgrade == 5) { // 28 monsters -- two rows
//				height = 25;
//				width = 23;
//			}
		}
		
		this.startingX = startingX;
		this.startingY = startingY;
		this.upgrade = upgrade;
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
		this.dx = 0;
	}
	
	public void setY(int newY) {
		this.startingY = newY;
		this.dy = 0;
	}
	
	public void draw(Graphics g) {
		if (upgrade == -1) {
			g.setColor(Color.GREEN);
			g.fillRoundRect(getX(), getY(), width, height, 20, 20);
		}
		else {
			g.fillArc(getX(), getY(), width, height, startAngle, endAngle);
		}
	}
}
