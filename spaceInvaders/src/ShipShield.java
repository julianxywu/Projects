import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class ShipShield {
	int shieldHealth, maxShieldHealth, currentUpgrade;
	int xVisual=95, yVisual=65; // to show on screen
	int x, y, xHit, yHit, width, height;
	ShieldBar newBar;
	Timer timer = new Timer();
	TimerTask task;
	ArrayList <ShieldBar> bars = new ArrayList();
	boolean gotStuff = false;
	String recoveryRate[] = {"EASTER EGG", "5 secs", "4 secs", "3 secs", "2 secs", "1/2 sec"};
	
	Color transparentBlink = new Color(0, 190, 0, 127);
	Color shieldBorder = new Color(0, 190, 0);
	
	public ShipShield(int shipLevel, int upgradeHealth, int upgradeRecovery, int bodyX, int bodyY, int bodyWidth, int bodyHeight) {
		if (shipLevel == 1) {
			width = 40;
			height = 40;
			this.x = bodyX - 2 * bodyWidth + 5;
			this.y = bodyY - 2 * bodyHeight + 5;
		}
		else if (shipLevel == 2) {
			width = 50;
			height = 50;
			this.x = bodyX - bodyWidth - 3;
			this.y = bodyY - bodyHeight - 3;
		}
		else if (shipLevel == 3) {
			width = 40;
			height = 40;
			this.x = bodyX - 2 * bodyWidth + 5;
			this.y = bodyY - 2 * bodyHeight + 3;		
		}
		else if (shipLevel == 4) {
			width = 50;
			height = 50;
			this.x = bodyX - bodyWidth - 3;
			this.y = bodyY - bodyHeight - 3;
		}
		else if (shipLevel == 5) {
			width = 60;
			height = 50;
			this.x = bodyX - 2 * bodyWidth;
			this.y = bodyY - bodyHeight - 5;
		}
		
		shieldHealth = upgradeHealth * 2;
		maxShieldHealth = upgradeHealth * 2;
		shieldBars(shieldHealth);
		currentUpgrade = upgradeRecovery;
		
		
		//shieldRecovery(upgradeRecovery);
	}
	
	// Contains function.
	public boolean contains(Bullet bullet) {
		if (x <= bullet.getX() & bullet.getX() <= (x + width) &   // if they intersect 
				y <= bullet.getY() & bullet.getY() <= (y + height)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//shield recovery
//	public void shieldRecovery(int upgrade) {
//		while (shieldHealth < maxShieldHealth) {
//			if (upgrade == 1) {
//				addHealth();
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	// To show on-screen
	public void shieldBars(int shieldHealth) {
		int i = 0;
		while (i < shieldHealth) {
			int shieldX = xVisual;
			int shieldY = yVisual;
			
			//Add a new bar to the "bars" list.
			newBar = new ShieldBar(shieldX, shieldY);
			bars.add(newBar);
			
			// Increment the distance between each heart.
			xVisual += (3 + newBar.getWidth());
			i++;
		}
	}
	
	public boolean stillRunning() {
		if (gotStuff) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addShield() {
		task = new TimerTask() {
			public void run() {
				shieldHealth++;
				newBar = new ShieldBar(xVisual, yVisual);
				bars.add(newBar);
				xVisual += (3 + newBar.getWidth());
				gotStuff = false;
			}
		};
		gotStuff = true;
		if (currentUpgrade == 1) {
			timer.schedule(task, 5000);
		}
		else if (currentUpgrade == 2) {
			timer.schedule(task, 4000);
		}
		else if (currentUpgrade == 3) {
			timer.schedule(task, 3000);
		}
		else if (currentUpgrade == 4) {
			timer.schedule(task, 2000);
		}
		else if (currentUpgrade == 5) {
			timer.schedule(task, 500);
		}
		else {
			timer.schedule(task, 7777);
		}
	}
	
	public void deductShield() {
		shieldHealth--;
		xVisual -= (3 + newBar.getWidth());
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void draw(Graphics g) {
		for (ShieldBar bar : bars) {
			bar.draw(g);
		}
		if (shieldHealth > 0) {
			g.setColor(shieldBorder);
			if (x < 0) {
				g.drawOval(x % 800 + 800, y, width, height);
			}
			else {
				g.drawOval(x % 800, y, width, height);
			}
			if (xHit != 0 & yHit != 0) {
				g.setColor(transparentBlink);
				g.fillOval(xHit, yHit, width, height);
			}
		}
	}
}
