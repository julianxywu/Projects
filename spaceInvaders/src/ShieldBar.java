import java.awt.Color;
import java.awt.Graphics;


public class ShieldBar {
	int xPoint, yPoint, width=3, height=12;
	public ShieldBar(int xPoint, int yPoint) {
		this.xPoint = xPoint;
		this.yPoint = yPoint;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(xPoint, yPoint, width, height);
	}
}