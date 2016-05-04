package yaxin.str;

import java.util.LinkedList;
import java.util.List;

public class Node {

	String text;
	List<Node> children = new LinkedList<Node>();
	
	Node(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
