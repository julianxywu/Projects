import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ShopHighlighter {
	Node node;
	int xOffset, yOffset;
	
	public ShopHighlighter(Node node, int xOffset, int yOffset) {
		this.node = node;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void draw(Graphics2D g, Node currentNode) {
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(5));
		g.drawRect(currentNode.xCoor + xOffset, currentNode.yCoor + yOffset, currentNode.width, currentNode.height);
	}
	
	public static void main(String[] args) {
	}
}
