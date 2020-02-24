import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Node {
	int xCoor, yCoor, width, height, barWidth=150, barHeight=20;
	String name, description;
	Node next, prev, in, out;
	int currentUpgrade=0, tempVar, coolDown, startingCoolDown, usedAmount;
	boolean onCoolDown=false;
	int[] cost;

	public Node(String name, int x, int y, int width, int height, Node n, Node p, Node i, Node o, int[] cost, String description) { 
		xCoor = x;
		yCoor = y;
		this.width = width;
		this.height = height;
		this.name = name;
		next = n;
		prev = p;
		in = i;
		out = o;
		this.cost = cost;
		this.description = description;
	}
	
	public boolean onCoolDown() {
		return onCoolDown;
	}
	
	public void setOnCoolDown(boolean b) {
		onCoolDown = b;
	}
	
	public int getNumber() {
		return currentUpgrade - usedAmount;
	}
	
	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}
	
	public int getCoolDown() {
		return coolDown;
	}
	
	public void drawName(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString(name, xCoor, yCoor);
	}
	
	public void drawCost(Graphics2D g) {
		if (cost.length != 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			if (currentUpgrade <= cost.length - 1) {
				g.drawString("($" +Integer.toString(cost[currentUpgrade]) + ")", xCoor + 170, yCoor + 30);
			}
			else {
				g.drawString("MAX", xCoor + 170, yCoor + 30);
			}
		}
	}
	
	public void upgrade() {
		currentUpgrade += 1;
	}
	
	public void enhancementBar(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(3));
		g.drawRect(xCoor, yCoor + 15, barWidth, barHeight);
		if (cost.length == 0) {
			Node start = this.in;
			int counter=0;
			while (start.currentUpgrade == start.cost.length) {
				start = start.next;
				counter++;
				if (counter == 10) {
					g.setColor(Color.MAGENTA);
					g.fillRect(xCoor + 2, yCoor + 17, barWidth-3, barHeight-3);
					break;
				}
			
			}
		}
		else {
			int segmentWidth = barWidth / cost.length;
			int segmentX = xCoor;
			int fillSegmentX = xCoor + 2;
			for (int upgrade : cost) {
				g.drawRect(segmentX, yCoor + 16, segmentWidth, barHeight);
				segmentX += segmentWidth;
			}
			g.setColor(Color.MAGENTA);
			for (int i=0; i < currentUpgrade; i++) {
				g.fillRect(fillSegmentX, yCoor + 17, segmentWidth-3, barHeight-3);
				fillSegmentX += segmentWidth;
			}
		}
	}
	
	public int getCurrentUpgrade() {
		return currentUpgrade;
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void drawDescription(Graphics2D g) {
		int x = 420, y = 50;
		if (description.length() > 20) {
			String lines[] = {"", "", "", "", "", "", "", "", "", ""};
			String wordList[] = description.split(" ");
			int i = 0;
			for (String word : wordList) {
				String tempString = lines[i] + word + " ";
				if (tempString.length() <= 32) {
					lines[i] += (word + " ");
				}
				else {
					i++;
					lines[i] += (word + " ");
				}
			}
			for (String line : lines) {
				if (line != "") {
					g.drawString(line, x, y);
					y += 25;
				}
			}
		}
		else {
			g.drawString(description, x, y);
		}
	}
	
	public void setLinkNext(Node n) {
		next = n;
	}

	public void setLinkPrev(Node p) {
		prev = p;
	}
	
	public void setLinkIn(Node i) {
		in = i;
	}
	
	public void setLinkOut(Node o) {
		out = o;
	}
	
	public Node getLinkNext() {
		return next;
	}

	public Node getLinkPrev() {
		return prev;
	}
	
	public Node getLinkIn() {
		return in;
	}
	
	public Node getLinkOut() {
		return out;
	}
}

