package yaxin.graph;

import yaxin.graph.Vertex

class Cycle {
	
	Vertex convergeVertex;
	List<Vertex> vertices = [];
	boolean isCompleted;
	
	boolean contains(String vertexName) {
		return vertices.find {
			it.name.equals(vertexName)
		} != null;
	}

}
