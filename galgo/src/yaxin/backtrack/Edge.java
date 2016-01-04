package yaxin.backtrack;

public class Edge {
	
	Vertex left;
	Vertex right;
	
	public Edge(Vertex left, Vertex right) {
		this.left = left;
		this.right = right;
		left.edges.add(this);
		//right.edges.add(this);
		Graph.g.edges.add(this);
	}

	Vertex getOther(Vertex v) {
		return left == v ? right : left;
	}
}
