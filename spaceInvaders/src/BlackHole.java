import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class BlackHole {
	int x=0, y=0, width=600, height=500, whiteHoleWidth=600, whiteHoleHeight=500;
	Color fading;
	int transparency=255;
	Timer blackHoleTimer, tunnelTimer;
	TimerTask disappear, tunnelDisappear, deleteHole;
	boolean whiteHole = false, doOnce = true, doItOnce = true, deleteBlackHole;
	boolean drawIt;
	int adding=0;
	
	public BlackHole() {
		blackHoleTimer = new Timer();
		tunnelTimer = new Timer();
		disappear = new TimerTask() {
			public void run() {
				whiteHole = true;
				System.out.println(whiteHole);
			}
		};
		deleteHole = new TimerTask() {
			public void run() {
				deleteBlackHole = true;
			}
		};
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void draw(Graphics2D g) {
		if (height <= 10) {
			g.drawOval(x, y, width, height);
			transparency=255;
			if (doItOnce) {
				blackHoleTimer.schedule(disappear, 1000);
				blackHoleTimer.schedule(deleteHole, 3000);
				doItOnce = false;
			}
		}
		else {
			fading = new Color(255, 255, 255, transparency);
			g.setColor(fading);
			if (transparency >= 2) {
				transparency -= 2;
			}
			g.drawOval(x, y, width, height);
			width -= 3;
			height -= 3;
			draw(g);
		}
		width = 600;
		height = 500;
	}
	
	public void drawWhiteHole(Graphics2D g) {
		tunnelDisappear = new TimerTask() {
			public void run() {
				adding += 1;
			}
		};
		if (doOnce) {
			tunnelTimer.scheduleAtFixedRate(tunnelDisappear, 100, 10);
			doOnce = false;
		}
		
		int i = 0;
		while (i < adding) {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(3));
			g.drawOval(x, y, whiteHoleWidth, whiteHoleHeight);
			whiteHoleWidth -=3;
			whiteHoleHeight -=3;
			i++;
		
		}
		whiteHoleWidth = 600;
		whiteHoleHeight = 500;
	}
}
