import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameShop {
	DLL categoriesList, shipEnhancements, cannonEnhancements;
	DLL specialEnhancements, bulletEnhancements, itemsList;
	Node pointer, tempPointer, tempPointer2;
	Node categoriesCurrentNode, shipEnhancementsCurrentNode, bulletEnhancementsCurrentNode;
	Node cannonEnhancementsCurrentNode, specialEnhancementsCurrentNode, itemsListCurrentNode;
	int[] blank = {};
	Node blankNode = new Node("", 0, 0, 0, 0, null, null, null, null, blank, ""); // to reset temp values
	ShopHighlighter highlight;
	int highlightXOffset=-5, highlightYOffset=-30; // Rectangle highlight
	Money userMoney;
	int level;
	boolean nextStageButton;
	ArrayList <Node> itemsBag;
	
	public GameShop(int level, Money userMoney, boolean nextStageButton) {
		itemsBag = new ArrayList<Node>();
		this.level = level;
		this.userMoney = userMoney;
		this.nextStageButton = nextStageButton;
		highlight = new ShopHighlighter(pointer, highlightXOffset, highlightYOffset);
		
		// Cost list
		int[] iceBornGauntlet = {500, 500, 750, 750, 1000};
		int[] excalibur = {2000, 2000, 2500};
		int[] darkHole = {2500, 2500, 2500};
		int[] tellTaleHeart = {500, 600, 700, 800, 900, 1000, 1200, 1400, 1600, 10};
		
		int[] bulletDamage = {1000, 1200, 1500, 2000};
		int[] bulletSpeed = {1000, 1200, 1500};
		
		int[] maxHealth = {200, 200, 400, 400, 400, 400, 400, 800, 1000, 2000};
		int[] shieldHealth = {100, 200, 300, 400, 500, 800, 1000, 1200, 1500, 2000};
		int[] shieldRecovery = {2000, 1000, 1000, 1500, 1500};
		int[] speed = {200, 400, 800, 1600, 3000};
		int[] verticalMovement = {1000};
		
		int[] numOfCannons = {1000, 1200, 1500, 2000};
		int[] bullet = {};
		
		int[] spaceTurret = {2000, 4000, 6000};
		int[] megaMissiles = {1000, 2000, 4000};
		int[] spaceItems = {};
		
		int[] ship = {};
		int[] cannon = {};
		int[] special = {};
		
		// Items list
		itemsList = new DLL();
		itemsList.append("ICEBORN GAUNTLET", 560, 180, 180, 40, null, iceBornGauntlet, "Also known as 'freeze time' (Lasts 15secs / 45secs CD) (Press 'I' to activate).");
		itemsList.append("EXCALIBUR", 560, 260, 110, 40, null, excalibur, "Need an extra sword? How about an extra hundred swords (Lasts 1stage / 60secs CD) (Press 'J' to activate).");
		itemsList.append("DARK HOLE", 560, 340, 110, 40, null, darkHole, "NOM NOM NOM... doesn't work     on bosses, obviously (Lasts 1stage / 90sec CD) (Press 'O' to activate).");
		itemsList.append("TELLTALE HEART", 560, 420, 155, 40, null, tellTaleHeart, "Gives you a second chance if you die.");
		itemsListCurrentNode = itemsList.start;
		// Bullet Enhancements list
		bulletEnhancements = new DLL();
		bulletEnhancements.append("BULLET DAMAGE", 560, 180, 150, 40, null, bulletDamage, "You seriously need me to explain this? (+1 Damage)");
		bulletEnhancements.append("BULLET SPEED", 560, 260, 140, 40, null, bulletSpeed, "Refer to 'BULLET DAMAGE'     (+1 Speed)");
		bulletEnhancementsCurrentNode = bulletEnhancements.start;
		// Ship Enhancements list
		shipEnhancements = new DLL();
		shipEnhancements.append("SHIP HEALTH", 280, 180, 170, 40, null, maxHealth, "Live long and prosper.");
		shipEnhancements.append("SHIELD HEALTH", 280, 260, 205, 40, null, shieldHealth, "The bigger the shield, the more careless you are, right?");
		shipEnhancements.append("SHIELD RECOVERY", 280, 340, 240, 40, null, shieldRecovery, "Oh you'll need this. Trust me.");
		shipEnhancements.append("SPEED", 280, 420, 95, 40, null, speed, "FAST and steady wins the battle.");
		shipEnhancements.append("VERTICAL MVT", 280, 500, 190, 40, null, verticalMovement, "You can move Up and Down     (Using 'W' and 'S').");
		shipEnhancementsCurrentNode = shipEnhancements.start;
		// Cannon Enhancements list
		cannonEnhancements = new DLL();
		cannonEnhancements.append("# OF CANNONS", 280, 180, 190, 40, null, numOfCannons, "You'll always need more of these.");
		cannonEnhancements.append("BULLET", 280, 260, 110, 40, bulletEnhancements, bullet, "Those little white things that appear everytime you hit 'SpaceBar'.");
		cannonEnhancementsCurrentNode = cannonEnhancements.start;
		// Special Enhancements list
		specialEnhancements = new DLL();
		specialEnhancements.append("SPACE TURRET", 280, 180, 200, 40, null, spaceTurret, "Place one down and it will shoot forever until destroyed (Press 'T' to place turret).");
		specialEnhancements.append("MEGA MISSILES", 280, 260, 205, 40, null, megaMissiles, "Packs a punch (Press 'F' to use).");
		specialEnhancements.append("SPACE ITEMS", 280, 340, 180, 40, itemsList, spaceItems, "SINGLE-USE: USE THEM     WISELY. (LIMITED - Only one item can be activated at a time.)");
		specialEnhancementsCurrentNode = specialEnhancements.start;
		//Categories list: ship, cannon, specials
		categoriesList = new DLL();
		categoriesList.append("SHIP", 50, 200, 85, 40, shipEnhancements, ship, "Your lovely home in the vast and dark Unknown.");
		categoriesList.append("CANNON", 50, 300, 150, 40, cannonEnhancements, cannon, "How do you defend yourself? Maybe this will help.");
		categoriesList.append("SPECIAL", 50, 400, 150, 40, specialEnhancements, special, "Good stuff.");
		categoriesCurrentNode = categoriesList.start;
		
		tempPointer = blankNode;
		tempPointer2 = blankNode;
		pointer = categoriesList.start;
	}
	
	public void HandleKeyPress(char k) {
		if (k == 's') {
			pointer = pointer.getLinkNext();
		}
		if (k == 'w') {
			pointer = pointer.getLinkPrev();
		}
		if (pointer.getLinkIn() != null) {
			if (k == 'd') {	
				tempPointer = pointer;
				pointer = pointer.getLinkIn();
				if (tempPointer.getLinkOut() != null) {
					tempPointer2 = tempPointer.getLinkOut();
				}
			}
		}
		if (pointer.getLinkOut() != null) {
			if (k == 'a') {
				if (tempPointer.getLinkOut() != null) {
					tempPointer = tempPointer2;
					tempPointer2 = blankNode;	
				}
				else {
					tempPointer = blankNode;
				}
				pointer = pointer.getLinkOut();
			}
		}
		
		if (k == 'b') {
			if (pointer.cost.length != 0 & pointer.currentUpgrade < pointer.cost.length) {
				if (pointer.cost[pointer.currentUpgrade] <= userMoney.currentAmount) {
					userMoney.deductMoney(pointer.cost[pointer.currentUpgrade]);
					pointer.upgrade();
					if (pointer.coolDown != 0 && !itemsBag.contains(pointer)) {
						itemsBag.add(pointer);
					}
				}
			}
		}
		
		if (k == 'k') {
			nextStageButton = true;
		}
	}
	
	public void HandleKeyRelease(char k) {
		if (k == 'k') {
			nextStageButton = false;
		}
	}
	
	public void draw(Graphics2D g) {
		// set up window
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.WHITE);
		g.fillRect(0, 130, 800, 5); //horizontal line
		g.fillRect(250, 130, 5, 470); // vertical line1
		g.fillRect(525, 130, 5, 470); // vertical line2
		g.setStroke(new BasicStroke(4));
		g.drawRect(410, 20, 240, 110);
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.drawString("SHOP" , 700, 35);
		g.drawString("Press 'K' to continue to next level.", 50, 50);
		g.drawString("Press 'B' to buy upgrade/item.", 50, 80);
		g.drawString("Next level: " + level, 50, 110);
		g.drawString(userMoney.howMuchMoney(), 690, 90);
		
		
		g.setFont(new Font("Dialog", Font.PLAIN, 16));
		pointer.drawDescription(g);
		// "buttons"
		// draw the 3 main categories
		g.setFont(new Font("Dialog", Font.BOLD, 32));
		for(int i=0; i < categoriesList.getSize(); i++) {
			categoriesCurrentNode.drawName(g);
			categoriesCurrentNode = categoriesCurrentNode.getLinkNext();

		}
		// draw ship sub-categories
		if (pointer.name == "SHIP" || tempPointer.name == "SHIP") {
			for (int i=0; i < shipEnhancements.getSize(); i++) {
				g.setFont(new Font("Dialog", Font.BOLD, 24));
				shipEnhancementsCurrentNode.drawName(g);
				shipEnhancementsCurrentNode.enhancementBar(g);
				shipEnhancementsCurrentNode.drawCost(g);
				shipEnhancementsCurrentNode = shipEnhancementsCurrentNode.getLinkNext();
				
			}
		}
		// draw cannon sub-categories
		if (pointer.name == "CANNON" || tempPointer.name == "CANNON" || tempPointer2.name == "CANNON") {
			for (int i=0; i < cannonEnhancements.getSize(); i++) {
				g.setFont(new Font("Dialog", Font.BOLD, 24));
				cannonEnhancementsCurrentNode.drawName(g);
				cannonEnhancementsCurrentNode.enhancementBar(g);
				cannonEnhancementsCurrentNode.drawCost(g);
				cannonEnhancementsCurrentNode = cannonEnhancementsCurrentNode.getLinkNext();
			}
		}
		if (pointer.name == "BULLET" || tempPointer.name == "BULLET") {
			for (int i=0; i < bulletEnhancements.getSize(); i++) {
				g.setFont(new Font("Dialog", Font.BOLD, 16));
				bulletEnhancementsCurrentNode.drawName(g);
				bulletEnhancementsCurrentNode.enhancementBar(g);
				bulletEnhancementsCurrentNode.drawCost(g);
				bulletEnhancementsCurrentNode = bulletEnhancementsCurrentNode.getLinkNext();
			}
		}
		// draw special sub-categories
		if (pointer.name == "SPECIAL" || tempPointer.name == "SPECIAL" || tempPointer2.name == "SPECIAL") {
			for (int i=0; i < specialEnhancements.getSize(); i++) {
				g.setFont(new Font("Dialog", Font.BOLD, 24));
				specialEnhancementsCurrentNode.drawName(g);
				specialEnhancementsCurrentNode.enhancementBar(g);
				specialEnhancementsCurrentNode.drawCost(g);
				specialEnhancementsCurrentNode = specialEnhancementsCurrentNode.getLinkNext();
			}
		}
		if (pointer.name == "SPACE ITEMS" || tempPointer.name == "SPACE ITEMS") {
			for (int i=0; i < itemsList.getSize(); i++) {
				g.setFont(new Font("Dialog", Font.BOLD, 16));
				itemsListCurrentNode.drawName(g);
				itemsListCurrentNode.enhancementBar(g);
				itemsListCurrentNode.drawCost(g);
				itemsListCurrentNode = itemsListCurrentNode.getLinkNext();
			}
		}
		highlight.draw(g, pointer);
	}
}
