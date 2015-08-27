package yaxin.tree

class Node {
	
	String name;
	
	Node parent;
	
	List<Node> children = [];
	
	Node(String name) {
		this.name = name;
	}
	
	boolean isLeaf() {
		return children.size() == 0;
	}
	
	String toString() {
		return name;
	}
}