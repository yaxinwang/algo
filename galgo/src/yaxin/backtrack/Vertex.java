package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	
	String name;
	
	String alias;
	
	Vertex(String name) {
		this.name = name;
		Graph.g.vertices.add(this);
	}
	
	List<Edge> edges = new ArrayList<Edge>();
	
	List<Vertex> getNeighbors() {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for(Edge edge : edges) {
			Vertex other = edge.getOther(this);
			neighbors.add(other);
		}
		return neighbors;
	}
	
	public String toString() {
		return name;
	}
}
