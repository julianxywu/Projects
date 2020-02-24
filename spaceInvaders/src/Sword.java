import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sword {
	int x, y, bladeWidth, bladeHeight, handleWidth, handleHeight;
	int dx=0, dy=-20;
	Color randomColor;
	
	public Sword(int xCoor, int yCoor) {
		x = xCoor;
		y = yCoor;
		bladeWidth = 2;
		handleHeight = 2;
		bladeHeight = (int)(Math.random() * 6 + 30);
		handleWidth = (int)(Math.random() * 4 + 8);
		randomColor = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public void move() {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public void draw(Graphics g) {
		g.setColor(randomColor);
		g.fillRect(x, y, bladeWidth, bladeHeight);
		g.fillRect(x - (handleWidth/2), y + bladeHeight - 8, handleWidth, handleHeight);
	}
}
