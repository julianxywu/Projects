import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class ShipHealth {
	int X1=80, X2=90, X3=85, Y1=55, Y2=55, Y3=45;
	int X4=80, X5=90, X6=85, X7=80, X8=90, X9=85;
	int nPoints = 3;
	Heart newHeart;
	int numOfLives;
	ArrayList <Heart> lives = new ArrayList();
	
	public ShipHealth(int numOfLives) {
		this.numOfLives = numOfLives;
		shipLives(this.numOfLives);
	}
	
	public void shipLives(int numOfLives) {
		int i = 0;
		while (i < numOfLives) {
			int LifeRedX[] = {X1, X2, X3};
			int LifeBlueX[] = {X4, X5, X6};
			int LifeGreenX[] = {X7, X8, X9};
			int LifeY[] = {Y1, Y2, Y3};
			
			//Add a new heart to the "lives" list.
			if (i < 5) {
				newHeart = new Heart(LifeRedX, LifeY, nPoints);
				lives.add(newHeart);
				// Increment the distance between each red heart.
				this.X1 += 15;
				this.X2 += 15;
				this.X3 += 15;
			}
			else if (i < 10) {
				newHeart = new Heart(LifeBlueX, LifeY, nPoints);
				lives.add(newHeart);
				// Increment the distance between each blue heart.
				this.X4 += 15;
				this.X5 += 15;
				this.X6 += 15;
			}
			else {
				newHeart = new Heart(LifeGreenX, LifeY, nPoints);
				lives.add(newHeart);
				// Increment the distance between each heart.
				this.X7 += 15;
				this.X8 += 15;
				this.X9 += 15;
			}
			i++;
		}
	}
	
	public void addHealth() {
		numOfLives += 1;
	}
	
	public void draw(Graphics g) {
		if (lives.size() <= 5) {
			for (Heart heart : lives) {
				heart.drawRed(g);
			}
		}
		else if (lives.size() <= 10) {
			for (int i=0; i < 5; i++) {
				lives.get(i).drawRed(g);
			}
			for (int i=5; i < lives.size(); i++) {
				lives.get(i).drawBlue(g);
			}
		}
		else if (lives.size() >= 10) {
			for (int i=0; i < 5; i++) {
				lives.get(i).drawRed(g);
			}
			for (int i=5; i < 10; i++) {
				lives.get(i).drawBlue(g);
			}
			for (int i=10; i < lives.size(); i++) {
				lives.get(i).drawGreen(g);
			}
		}
	}
}
