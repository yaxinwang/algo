package yaxin.btree

class TreeTraversal {
	
	static List<Node> preorder(Node n) {
		List<Node> nodes = [];
		nodes.add(n);
		if(n.left) {
			nodes.addAll(preorder(n.left));
		}
		if(n.right){
			nodes.addAll(preorder(n.right));
		} 
		return nodes;
	}
	
	static List<Node> inorder(Node n) {
		List<Node> nodes = [];
		if(n.left != null) {
			nodes.addAll(inorder(n.left));			
		}
		nodes.add(n);
		if(n.right != null) {
			nodes.addAll(inorder(n.right));
		}
		return nodes;
	}
	
	static List<Node> postorder(Node n) {
		List<Node> nodes = [];
		if(n.left != null) {
			nodes.addAll(postorder(n.left));
		}
		if(n.right != null) {
			nodes.addAll(postorder(n.right));
		}
		nodes.add(n);
		return nodes;
	}
	
	static List<Node> levelorder(Node n) {
		List<Node> nodes = [];
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(n);
		
		while(queue.size() > 0) {
			Node next = queue.remove();
			nodes.add(next);
			if(next.left != null) {				
				queue.add(next.left);
			}
			if(next.right != null) {
				queue.add(next.right);
			}
		}
		return nodes;
	}
	
}