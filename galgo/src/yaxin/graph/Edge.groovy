package yaxin.graph

class Edge {

	Vertex left;
	Vertex right;
	
	Edge(Vertex left, Vertex right) {
		this.left = left;
		this.right = right;
		
		left.neighbors << right;
		left.edges << this;
		
		right.neighbors << left;
		right.edges << this;
		
		Graph.g.edges << this;
	}
	
	void delete() {
		left.edges.remove(this);
		right.edges.remove(this);
		Graph.getG().edges.remove(this);
		
		boolean hasEdgeToRight = false;
		left.edges.each { e ->
			if(e.left == right || e.right == right) {
				hasEdgeToRight = true;
			}
		}
		
		if(!hasEdgeToRight) {
			left.neighbors.remove(right);
		}
		
		boolean hasEdgeToLeft = false;
		right.edges.each { e ->
			if(e.left == left || e.right == left) {
				hasEdgeToLeft = true;
			}
		}
		
		if(!hasEdgeToLeft) {
			right.neighbors.remove(left);
		}
	}
	
	String toString() {
		return "(${left.name}, ${right.name})";
	}
}
