package yaxin.btree

class Node {
	String name;
	Node(String name) {
		this.name = name;
	}
	Node left;
	Node right;

	String toString() {
		return name;
	}
}