package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	static Graph g;
	
	static void newGraph() {
		g = new Graph();
	}
	
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
	
	Vertex getVertex(String name) {
		for(Vertex v : vertices) {
			if(v.name.equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
}
