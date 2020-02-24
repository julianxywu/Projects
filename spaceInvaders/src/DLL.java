
public class DLL {
	Node start, end;
	int size;
	public DLL() {
		start = null;
		end = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return start == null;
	}
	
	public int getSize() {
		return size;
	}
	
	public Node get(String nodeName) {
		Node currentNode = start;
		for(int i = 0; i < getSize(); i++) {
			if (currentNode.name == nodeName) {
				return currentNode;
			}
			else {
				currentNode = currentNode.getLinkNext();
			}
		}
		return null;
	}
	
	public void append(String name, int x, int y, int width, int height, DLL subCategory, int[] cost, String description) {
		Node newNode = new Node(name, x, y, width, height, null, null, null, null, cost, description);
		newNode.currentUpgrade = 0;
		if (subCategory != null) {
			newNode.setLinkIn(subCategory.start);
			Node currentSubCategoryNode = subCategory.start;
			for (int i=0; i < subCategory.getSize(); i++) {
				currentSubCategoryNode.setLinkOut(newNode);
				currentSubCategoryNode = currentSubCategoryNode.getLinkNext();
			}
		}
		if (start == null) {
			newNode.setLinkNext(newNode);
			newNode.setLinkPrev(newNode);
			start = newNode;
			end = start;
		}
		else {
			newNode.setLinkPrev(end);
			end.setLinkNext(newNode);
			start.setLinkPrev(newNode);
			//newNode.setLinkIn(start);
			newNode.setLinkNext(start);
			end = newNode;
		}
		size++;
	}
	
	public void deleteAtPos(int pos) {
		if (pos == 1) {
			if (size == 1) {
				start = null;
				end = null;
				size = 0;
				return;
			}
			start = start.getLinkNext();
			start.setLinkPrev(end);
			end.setLinkNext(start);
			size--;
			return;
		}
		if (pos == size) {
			end = end.getLinkPrev();
			end.setLinkNext(start);
			start.setLinkPrev(end);
			size--;
		}
		Node temp = start.getLinkNext();
		for (int i = 2; i <= size; i++) {
			if (i == pos) {
				Node p = temp.getLinkPrev();
				Node n = temp.getLinkNext();
				
				p.setLinkNext(n);
				n.setLinkPrev(p);
				size--;
				return;
			}
			temp = temp.getLinkNext();
		}
	}
	
	public void printlist(Node node) {
		Node last = null;
		System.out.println("Traversal in forward Direction");
		while (node != null) {
			System.out.print(node.name + ": " + node.xCoor + ", " + node.yCoor);
			last = node;
			node = node.next;
		}
	}
}
