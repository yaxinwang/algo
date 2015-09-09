package yaxin.graph

class Edge {

	Vertex left;
	Vertex right;
	
	Edge(Vertex left, Vertex right) {
		this.left = left;
		this.right = right;
		
		left.neighbors << right;
		right.neighbors << left;
		
		Graph.g.edges << this;
	}
	
	String toString() {
		return "(${left.name}, ${right.name})";
	}
}
