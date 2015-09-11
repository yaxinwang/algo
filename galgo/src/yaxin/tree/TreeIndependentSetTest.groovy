package yaxin.tree

import org.junit.Test

class TreeIndependentSetTest {

	@Test
	public void testTriangle() {
		Node a = new Node('a');
		Node b = new Node('b');
		Node c = new Node('c');

		a.children << b;
		a.children << c;

		List<Node> nodes = TreeIndependentSet.maxSet(a);
		println nodes.join(',');
	}

	@Test
	public void testZigZag() {
		Node a = new Node('a');
		Node b = new Node('b');
		Node c = new Node('c');
		Node d = new Node('d');
		Node e = new Node('e');
		Node f = new Node('f');
		
		a.children << b;
		a.children << c;
		a.children << d;
		
		b.children << e;
		b.children << f;
		
		List<Node> nodes = TreeIndependentSet.maxSet(a);
		println nodes.join(',');
	}
}
