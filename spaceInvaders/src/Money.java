import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.TimerTask;

public class Money {
	int startingAmount, currentAmount;
	ArrayList <Point> tempPair = new ArrayList();
	Point point = new Point();
	
	public Money(int startingAmount)
	{
		this.startingAmount = startingAmount;
		currentAmount = startingAmount;
	}
	
	public void removeAllTempPairs() {
		int i = 0;
		while (i < tempPair.size()) {
			tempPair.remove(i);
		}
	}
	
	public void addMoney(int amount) {
		currentAmount += amount;
	}
	
	public void deductMoney(int amount) {
		currentAmount -= amount;
	}
	
	public String howMuchMoney() {
		return ("$$$: " + Integer.toString(currentAmount));
	}
	
	public void draw(Graphics g, int tempX, int tempY, Monster currentMonster) {
		g.setColor(Color.WHITE);
		if (currentMonster != null) {
			g.drawString("+" + Integer.toString(currentMonster.moneyWorth), tempX, tempY);
		}
	}
}
