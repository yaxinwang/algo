package yaxin.btree

import org.junit.Before
import org.junit.Test

class BTreeTest {
	
	Node root;
	
	@Before
	void buildTree() {
		Node a = new Node('A');
		Node b = new Node('B');
		Node c = new Node('C');
		Node d = new Node('D');
		Node e = new Node('E');
		Node f = new Node('F');
		Node g = new Node('G');
		Node h = new Node('H');
		Node i = new Node('I');
		
		f.left = b;
		f.right = g;
		
		b.left = a;
		b.right = d;
		
		g.right = i;
		
		d.left = c;
		d.right = e;
		
		i.left = h;
		
		root = f;		
	}
	
	@Test
	void preorder() {
		println 'preorder';
		
		def nodes = TreeTraversal.preorder(root);
		nodes.each {
			print "${it},"
		}
	}
	
	@Test
	void inorder() {
		println 'inorder';
		
		def nodes = TreeTraversal.inorder(root);
		nodes.each {
			print "${it},"
		}
	}
	
	@Test
	void postorder() {
		println 'postorder';
		def nodes = TreeTraversal.postorder(root);
		nodes.each {
			print "${it},"
		}
	}
	
	@Test
	void levelorder() {
		println 'levelorder';
		def nodes = TreeTraversal.levelorder(root);
		nodes.each {
			print "${it},"
		}
	}
	
}