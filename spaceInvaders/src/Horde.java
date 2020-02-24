import java.awt.Graphics;
import java.util.ArrayList;

public class Horde {
	ArrayList <Monster> horde = new ArrayList();
	int numOfMonsters; // number of monsters
	int spacing=5; // spacing between monsters
	Monster newMonster = new Monster(1, 1, 1, 1, false); // to get the monster's width for spacing reasons.
	int layoutXIncrement1;
	int layoutY;
	int groupHealth;
	boolean isBoss=false;
	
	public Horde(int level, int groupHealth, int layoutY) {
//		// EMPTY HORDE
//		if (level == -100) {
//			newMonster = new Monster(level, layoutXIncrement1, layoutY, groupHealth, isBoss);
//			horde.add(newMonster);
//		}
		// BOSSES
		if (level <= 0) {
			numOfMonsters = 1;
			isBoss = true;
		}
		// Each level, # of monsters
		if (level == 1) {
			numOfMonsters = 10;
		}
		if (level == 2) {
			numOfMonsters = 16;
		}
		if (level == 3) {
			numOfMonsters = 18;
		}
		if (level == 4) {
			numOfMonsters = 20;
		}
		if (level == 5) {
			numOfMonsters = 28;
		}
		
		this.groupHealth = groupHealth;
		this.layoutY = layoutY;
		
		// Create the spacing between monsters.
		if (numOfMonsters == 1) { // BOSSES
			layoutXIncrement1 = 300;
		}
		else if (numOfMonsters < 20) {
			layoutXIncrement1 = 400 - (numOfMonsters / 2 * (spacing + newMonster.body.getWidth()));
		}
		else {
			layoutXIncrement1 = 450 - (20 / 2 * (spacing + newMonster.body.getWidth()));
		}
		// Create the monsters.
		for (int i = 0; i < numOfMonsters; i++) {
			newMonster = new Monster(level, layoutXIncrement1, layoutY, groupHealth, isBoss);
			horde.add(newMonster);
			layoutXIncrement1 += (spacing + newMonster.body.getWidth());
			if (i % 20 == 0 & i >= 20) {
				// Reset X starting point for new line of monsters.
				layoutXIncrement1 = 450 - ((numOfMonsters - i) / 2 * (spacing + newMonster.body.getWidth()));
				layoutY += (spacing + newMonster.body.getHeight() + newMonster.legs.getHeight());
			}
		}
	}
	
	public int getMonsterNumber() {
		return numOfMonsters;
	}
	
	public void deleteMonsters(Money userMoney) {
		int i = 0;
		while (i < horde.size()) {
			if (!horde.get(i).isAlive()) {
				// Add money for each monster that's killed.
				userMoney.addMoney(horde.get(i).moneyWorth);
				horde.remove(i);

			}
			i++;
		}
		numOfMonsters = horde.size();
	}
	
	public void killAll() {
		int i = 0;
		while (i < horde.size()) {
			horde.remove(i);
		}
	}
	
	public void shoot(boolean iceFieldActive, BulletFlurry bulletFlurry) {
		for (Monster monster : horde) {
			monster.shoot(iceFieldActive, bulletFlurry);
		}
	}
	
	public void draw(Graphics g) {
		for (Monster monster : horde) {
			if (monster.state == 1) {
				monster.draw(g);
			}
		}
	}
	
	public void move(boolean blackHoleActive, boolean iceFieldActive) {
		for (Monster monster : horde) {
			monster.move(blackHoleActive, iceFieldActive);
		}
	}
}

