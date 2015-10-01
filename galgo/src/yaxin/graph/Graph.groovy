package yaxin.graph

import yaxin.ProcessingState;

class Graph {

	List<Vertex> vertices = [];
	List<Edge> edges = [];

	static Graph g;

	Graph() {
		g = this;
	}

	boolean contains(String vertexName) {
		return vertices.find {
			it.name.equals(vertexName);
		} != null;
	}
	
	Vertex getVertexByName(String name) {
		return vertices.find {
			it.name.equals(name);
		}
	}

	void resetState() {
		vertices.each { it.state = null; }
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

	Graph compressCycle(Cycle c) {
		Graph g2 = new Graph();

		List<Edge> candidateEdges = [];

		vertices.each { v ->
			if(c.contains(v.name)) {
				//will be compressed
			} else {
				Vertex v2 = new Vertex(v.name);

				if(v.state == ProcessingState.PROCESSED) {
					v2.state = ProcessingState.PROCESSED;
				}

				candidateEdges.addAll(v.edges);
			}
		}

		String compressedVertexName = '[' + c.vertices.collect { v-> v.name; }.iterator().join(',') + ']';
		Vertex compressedVertex = new Vertex(compressedVertexName);

		//include e if both vertices are in new graph
		//connect e to compressed vertex if one vertex is in cycle
		this.edges.each { e ->
			if(g2.contains(e.left.name) && g2.contains(e.right.name)) {
				Edge e2 = new Edge(g2.getVertexByName(e.left.name), g2.getVertexByName(e.right.name));
				e2.originalLeft = e.originalLeft != null ? e.originalLeft : e.left;
				e2.originalRight = e.originalRight != null ? e.originalRight : e.right;
			} else if(g2.contains(e.left.name) && c.contains(e.right.name)) {
				Edge e2 = new Edge( g2.getVertexByName(e.left.name), compressedVertex);
				e2.originalLeft = e.originalLeft != null ? e.originalLeft : e.left;
				e2.originalRight = e.originalRight != null ? e.originalRight : e.right;
			} else if(g2.contains(e.right.name) && c.contains(e.left.name)) {
				Edge e2 = new Edge(compressedVertex, g2.getVertexByName(e.right.name));
				e2.originalLeft = e.originalLeft != null ? e.originalLeft : e.left;
				e2.originalRight = e.originalRight != null ? e.originalRight : e.right;
			}
		}

		return g2;
	}

	String toString() {
		return edges.iterator().join('\n');
	}
}
