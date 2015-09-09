package yaxin.graph

import yaxin.ProcessingState;

class Graph {

	List<Vertex> vertices = [];
	List<Edge> edges = [];

	static Graph g;

	Graph() {
		g = this;
	}
	
	void resetState() {
		vertices.each {
			it.state = null;
		}
	}
	
	Map<Vertex, ProcessingState> getState() {
		def m = new HashMap<Vertex, ProcessingState>();
		vertices.each {
			if(it.state != null) {
				m[it] = it.state;
			}
		}
		return m;
	}
	
	void setState(Map<Vertex, ProcessingState> states) {
		vertices.each {
			it.state = states[it];
		}
	}
	
	String toString() {
		return edges.iterator().join('\n');
	}
}
