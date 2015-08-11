package yaxin

class Node {

	String name;
	
	Node next;
	
	Node(String name) {
		this.name = name;
	}
	
	void insertAfter(Node n) {
		if(next == null) {
			next = n;
		} else {
			Node tmp = next;
			next = n;
			n.next = tmp;
		}
	}
}
