import java.awt.Color;
import java.awt.Graphics;

// Create the Ship Cannon.

public class ShipCannon {
	int height1, height2, height3, width, widthBody, x, y, numOfExtraCannons=0, upgrade;
	
	public ShipCannon(int newUpgrade, int bodyX, int bodyY, int bodyWidth, int bodyHeight) {
		widthBody = bodyWidth;
		
		if (newUpgrade == 1) {
			upgrade = newUpgrade;
			height1 = 6;
			width = 4;
			x = bodyX + (bodyWidth/2) - 2;
			y = bodyY - height1;
		}
		else if (newUpgrade == 21 || newUpgrade == 22) {
			upgrade = newUpgrade;
			height1 = 8;
			width = 4;
			y = bodyY - height1;
			if (newUpgrade == 21) {
				x = bodyX + 2;
			}
			else if (newUpgrade == 22) {
				x = bodyX + (bodyWidth / 2 + 2);
			}
		}
		else if (newUpgrade == 30 || newUpgrade == 31 || newUpgrade ==32 || newUpgrade == 41 || newUpgrade == 42 || newUpgrade == 411 || newUpgrade == 422) {
			upgrade = newUpgrade;
			height1 = 10;
			height2 = 15;
			width = 4;
			if (newUpgrade == 30) {
				y = bodyY - height1;
				x = bodyX + (bodyWidth/2) - 2;
			}
			if (newUpgrade == 31) {
				y = bodyY - height2 + 8;
				x = bodyX - 2 * width;
			}
			if (newUpgrade == 32) {
				y = bodyY - height2 + 8;
				x = bodyX + bodyWidth + width;
			}
			if (newUpgrade == 41) {
				x = bodyX + 2;
				y = bodyY - height1;
			}
			if (newUpgrade == 42) {
				x = bodyX + bodyWidth - width - 2;
				y = bodyY - height1;
			}
			if (newUpgrade == 411) {
				x = bodyX - 2 * width;
				y = bodyY - height2 + 8;
			}
			if (newUpgrade == 422) {
				x = bodyX + bodyWidth + width;
				y = bodyY - height2 + 8;
			}
		}
		else if (newUpgrade == 50 || newUpgrade == 51 || newUpgrade == 52 || newUpgrade == 511 || newUpgrade == 522) {
			upgrade = newUpgrade;
			height1 = 10;
			height2 = 15;
			height3 = 20;
			width = 4;
			if (newUpgrade == 50) {
				y = bodyY - height1;
				x = bodyX + (bodyWidth/2) - 2;
			}
			if (newUpgrade == 51) {
				y = bodyY - height2 + 12;
				x = bodyX - 2 * width;
						}
			if (newUpgrade == 52) {
				y = bodyY - height2 + 12;
				x = bodyX + bodyWidth + width;
			}
			if (newUpgrade == 511) {
				y = bodyY - height3 + 20;
				x = bodyX - 4 * width;
			}
			if (newUpgrade == 522) {
				y = bodyY - height3 + 20;
				x = bodyX + 2 * bodyWidth;
			}
		}
	}

	// Contains function.
		public boolean contains(Bullet bullet) {
			if (x <= bullet.getX() & bullet.getX() <= (x + width) &
					y <= bullet.getY() & bullet.getY() <= (y + height1)) {
				return true;
			}
			else {
				return false;
			}
		}
	
	//Setters and Getters
	public int getX() {
		if (x < 0) {
			return (x % 800) + 800;
		}
		else {
			return x % 800;
		}
	}

	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight1() {
		return height1;
	}
	
	public int getHeight2() {
		return height2;
	}
	
	public int getHeight3() {
		return height3;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	//Draw
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		if (upgrade == 1 || upgrade == 21 || upgrade == 22 || upgrade == 30 || upgrade == 41 || upgrade == 42 || upgrade == 50) {
			if (upgrade == 50) {
				g.setColor(Color.RED);
			}
			if (getX() < 0) {  // Negative X
				g.fillRect(getX() % 800 + 800, getY() + 800, width, height1);
			}
			else { // Positive X
				g.fillRect(getX() % 800, getY(), width, height1);
			}
		}
		else if (upgrade == 31 || upgrade == 32 || upgrade == 411 || upgrade == 422 || upgrade == 51 || upgrade == 52) {
			g.setColor(Color.LIGHT_GRAY);
			if (getX() < 0) {  // Negative X
				g.fillRect(getX() % 800 + 800, getY(), width, height2);
			}
			else { // Positive X
				g.fillRect(getX() % 800, getY(), width, height2);
			}
		}
		else if (upgrade == 511 || upgrade == 522) {
			g.setColor(Color.ORANGE);
			if (getX() < 0) {  // Negative X
				g.fillRect(getX() % 800 + 800, getY(), width, height3);
			}
			else { // Positive X
				g.fillRect(getX() % 800, getY(), width, height3);
			}
		}
	} 
}
