import java.awt.Graphics;
import java.util.ArrayList;

public class SwordSpecial {
	ArrayList <Sword> swordList;
	int totalSwords = 200, currentSwords;
	int randomXStart, randomYStart;
	Sword newSword;
	
	public SwordSpecial() {
		swordList = new ArrayList<Sword>();
	}
	
	public void addSwords() {
		while (currentSwords < totalSwords) {
			int randomXStart = (int)(Math.random() * 760 + 20);
			int randomYStart = (int)(Math.random() * 600 + 610);
			newSword = new Sword(randomXStart, randomYStart);
			swordList.add(newSword);
			currentSwords = swordList.size();
		}
	}
	
	public void deleteSwords() {
		int i = 0;
		while (i < swordList.size()) {
			if (swordList.get(i).getY() < -50) {
				swordList.remove(i);
				currentSwords = swordList.size();
			}
			i++;
		}
	}
	
	public void draw(Graphics g) {
		for (Sword sword : swordList) {
			sword.draw(g);
		}
	}
	
	public void move() {
		for (Sword sword : swordList) {
			sword.move();
		}
	}
}
