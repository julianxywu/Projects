import java.awt.Color;
import java.awt.Graphics;


// Create the Ship Body.

public class ShipBody {
	int height, width, upgrade;
	int startingX=400, startingY=550;
	int dx=0, dy=0;
	public ShipBody(int upgrade) {
		if (upgrade == 1 || upgrade == 3) {
			height = 10;
			width = 10;	
		}
		else if (upgrade == 2 || upgrade == 4) {
			height = 15;
			width = 15;
		}
		else if (upgrade == 5) {
			height = 14;
			width = 12;
		}
		this.upgrade = upgrade;
	}
	
	// Contains function.
		public boolean contains(Bullet bullet) {
			if ((getX() <= bullet.getX() &
					bullet.getX() <= (getX() + width) &
					getY() <= bullet.getY() &
					bullet.getY() <= (getY() + height))) {
				return true;
			}
			else {
				return false;
			}
		}
	
	public int getX() {
		if ((startingX + dx) < 0) {
			return (startingX + dx) % 800 + 800;
		}
		else {
			return (startingX + dx) % 800;
		}
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
		if (upgrade == 5) {
			g.setColor(Color.LIGHT_GRAY);
		}
		if (getX() < 0) {  // Negative X
			g.fillRect(getX() % 800 + 800, getY(), width, height);
		}
		else { // Positive X
			g.fillRect(getX() % 800, getY(), width, height);
		}
	}
}
