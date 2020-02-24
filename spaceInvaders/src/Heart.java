import java.awt.Color;
import java.awt.Graphics;


public class Heart {
	int xPoints[], yPoints[], nPoints;
	public Heart(int xPoints[], int yPoints[], int nPoints) {
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.nPoints = nPoints;
	}
	public void drawGreen(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	public void drawBlue(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	public void drawRed(Graphics g) {
		g.setColor(Color.RED);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
}
