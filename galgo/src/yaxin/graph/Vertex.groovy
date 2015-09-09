package yaxin.graph

import yaxin.ProcessingState;

class Vertex {

	String name;

	List<Vertex> neighbors = [];

	ProcessingState state;
	
	Vertex(String name) {
		this.name = name;
		Graph.g.vertices << this;
	}
	
	String toString() {
		if(neighbors.size() == 0) {
			return this.name;
		} else {
			def names = neighbors.collect{ it.name };
			return "${name}->${names.iterator().join(',')}";
		}
	}
}