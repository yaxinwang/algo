package yaxin.tree

import org.junit.Test

class VertexCoverTest {

	@Test
	void testCover3() {
		Node a = new Node('a');
		Node b = new Node('b');
		Node c = new Node('c');
		
		a.children << b;
		a.children << c;
		
		def cover = TreeVertexCover.findMinCover(a);
		cover.each {
			print "${it},"
		}				
	}
	
	@Test
	void testCover7() {
		Node a = new Node('a');
		Node b = new Node('b');
		Node c = new Node('c');
		Node d = new Node('d');
		Node e = new Node('e');
		Node f = new Node('f');
		Node g = new Node('g');
		
		a.children << b;
		a.children << c;
		
		b.children << d;
		b.children << e;
		
		c.children << f;
		c.children << g;
		
		def cover = TreeVertexCover.findMinCover(a);
		cover.each {
			print "${it},"
		}
	}
	
	@Test
	void testCoverTrunk() {
		Node a = new Node('a');
		Node b = new Node('b');
		Node c = new Node('c');
		
		a.children.add(new Node('a1'));
		a.children.add(new Node('a2'));
		a.children.add(b);
		
		b.children.add(new Node('b1'));
		b.children.add(new Node('b2'));
		b.children.add(c);
		
		c.children.add(new Node('c1'));
		c.children.add(new Node('c2'));
		
		def cover = TreeVertexCover.findMinCover(a);
		cover.each {
			print "${it},"
		}
		
	}
}
