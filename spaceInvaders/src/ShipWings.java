import java.awt.Color;
import java.awt.Graphics;

// Create the Ship Wings.

public class ShipWings {
	int height, width, x1, x2, y, upgrade;
	
	public ShipWings(int newUpgrade, int bodyX, int bodyY, int bodyWidth, int bodyHeight) {
		if (newUpgrade == 1) {
			height = 12;
			width = 4;
		}
		else if (newUpgrade == 2) {
			height = 15;
			width = 4;
		}
		else if (newUpgrade == 3) {
			height = 8;
			width = 4;
		}
		else if (newUpgrade == 4) {
			height = 15;
			width = 4;
		}
		else if (newUpgrade == 5) {
			height = 6;
			width = 4;
		}

		upgrade= newUpgrade;
		x1 = bodyX - width;
		y = bodyY + 2;
		x2 = bodyX + bodyWidth;
	}
	
	// Contains function.
		public boolean contains(Bullet bullet) {
			//ship wing1
			if (x1 <= bullet.getX() & bullet.getX() <= (x1 + width) &
					y <= bullet.getY() &
					bullet.getY() <= (y + height) ||
			//ship wings2
				(x2 <= bullet.getX() & bullet.getX() <= (x2 + width) &
					y <= bullet.getY() & bullet.getY() <= (y + height))) {
				return true;
			}
			else {
				return false;
			}
		}
	
	//Setters and Getters
	public int getX1() {
		if (x1 < 0) {
			return (x1 % 800) + 800;
		}
		else {
			return x1 % 800;
		}
	}

	public int getY() {
		return y;
	}
	
	public int getX2() {
		if (x2 < 0) {
			return (x2 % 800) + 800;
		}
		else {
			return x2 % 800;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setX1(int newX) {
		this.x1 = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setX2(int newX) {
		this.x2 = newX;
	}

	//Draw
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		if (upgrade == 1) {
			if (getX1() < 0) {  // Negative X1
				g.fillRect(getX1() % 800 + 800, getY(), width, height);
			}
			else { // Positive X1
				g.fillRect(getX1() % 800, getY(), width, height);
			}
			if (getX2() < 0) {  // Negative X2
				g.fillRect(getX2() % 800 + 800, getY(), width, height);
			}
			else { // Positive X2
				g.fillRect(getX2() % 800, getY(), width, height);
			}
		}
		else if (upgrade == 2) {
			if (getX1() < 0) {  // Negative X1
				g.fillRect(getX1() % 800 + 800, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800 + 800, getX1() % 800 + width + 800, getX1() % 800 + width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else { // Positive X1
				g.fillRect(getX1() % 800, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800, getX1() % 800 + width, getX1() % 800 + width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			if (getX2() < 0) {  // Negative X2
				g.fillRect(getX2() % 800 + 800, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + width + 800, getX2() % 800 + 800, getX2() % 800 + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else { // Positive X2
				g.fillRect(getX2() % 800, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + width, getX2() % 800, getX2() % 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
		}
		else if (upgrade == 3) {
			g.setColor(Color.MAGENTA);
			if (getX1() < 0) {  // Negative X1
				g.fillRect(getX1() % 800 - width + 800, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800 + 800, getX1() % 800 + width + 800, getX1() % 800 + width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else { // Positive X1
				g.fillRect(getX1() % 800 - width, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800, getX1() % 800 + width, getX1() % 800 + width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			if (getX2() < 0) {  // Negative X2
				g.fillRect(getX2() % 800 + width + 800, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + width + 800, getX2() % 800 + 800, getX2() % 800 + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else { // Positive X2
				g.fillRect(getX2() % 800 + width, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + width, getX2() % 800, getX2() % 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4};
				g.fillPolygon(xPoints, yPoints, 3);
			}
		}
		else if (upgrade == 4) {
			g.setColor(Color.RED);
			if (getX1() < 0) {  // Negative X1
				g.fillRect(getX1() % 800 - width + 800, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800 + 800, getX1() % 800 + width + 800, getX1() % 800 + width + 800,
								 getX1() % 800 - 2*width + 800, getX1() % 800 - width + 800, getX1() % 800 - width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			else { // Positive X1
				g.fillRect(getX1() % 800 - width, getY() + 4, width, height);
				int[] xPoints = {getX1() % 800, getX1() % 800 + width, getX1() % 800 + width,
								 getX1() % 800 - 2*width, getX1() % 800 - width, getX1() % 800 - width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			if (getX2() < 0) {  // Negative X2
				g.fillRect(getX2() % 800 + width + 800, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + 3*width + 800, getX2() % 800 + 2*width + 800, getX2() % 800 + 2*width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			else { // Positive X2
				g.fillRect(getX2() % 800 + width, getY() + 4, width, height);
				int[] xPoints = {getX2() % 800 + width, getX2() % 800, getX2() % 800,
								 getX2() % 800 + 3*width, getX2() % 800 + 2*width, getX2() % 800 + 2*width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
		}
		else if (upgrade == 5) {
			g.setColor(Color.ORANGE);
			if (getX1() < 0) {  // Negative X1
				g.fillRect(getX1() % 800 - width + 800, getY() + 4, width, height);
				g.fillRect(getX1() % 800 - 3*width + 800, getY() + 8, width, height);
				int[] xPoints = {getX1() % 800 + 800, getX1() % 800 + width + 800, getX1() % 800 + width + 800,
								 getX1() % 800 - 2*width + 800, getX1() % 800 - width + 800, getX1() % 800 - width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			else { // Positive X1
				g.fillRect(getX1() % 800 - width, getY() + 4, width, height + 5);
				g.fillRect(getX1() % 800 - 3 *width + 1, getY() - 10, width - 2, height + 10);
				int[] xPoints = {getX1() % 800, getX1() % 800 + width, getX1() % 800 + width,
								 getX1() % 800 - 2*width, getX1() % 800 - width, getX1() % 800 - width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			if (getX2() < 0) {  // Negative X2
				g.fillRect(getX2() % 800 + width + 800, getY() + 4, width, height);
				g.fillRect(getX2() % 800 + 3*width + 800, getY() + 8, width, height);
				int[] xPoints = {getX2() % 800 + 3*width + 800, getX2() % 800 + 2*width + 800, getX2() % 800 + 2*width + 800};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
			else { // Positive X2
				g.fillRect(getX2() % 800 + width, getY() + 4, width, height + 5);
				g.fillRect(getX2() % 800 + 3*width + 1, getY() - 10, width - 2, height + 10);
				int[] xPoints = {getX2() % 800 + width, getX2() % 800, getX2() % 800,
								 getX2() % 800 + 3*width, getX2() % 800 + 2*width, getX2() % 800 + 2*width};
				int[] yPoints = {getY() + 4, getY(), getY() + 4,
								 getY() + 8, getY() + 4, getY() + 8};
				g.fillPolygon(xPoints, yPoints, 6);
			}
		}
	}
}
