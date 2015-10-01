package yaxin.graph

import yaxin.ProcessingState;

class Vertex {

	String name;

	List<Vertex> neighbors = [];
	List<Edge> edges = [];

	ProcessingState state;
	
	Vertex(String name) {
		this.name = name;
		Graph.g.vertices << this;
	}

	int degree() {
		return edges.size();
	}

	List<List<Edge>> getDupEdges() {
		Map<Vertex, List<Edge>> edgeMap = [:];
		edges.each {e ->
			def ls = edgeMap[e.right];
			if(ls == null) {
				ls = [];
				edgeMap[e.right] = ls;
			}
			ls << e;
		}

		def dupEdges = [];
		edgeMap.values().each { ls ->
			if(ls.size() > 1) {
				dupEdges << ls;
			}
		}
		return dupEdges;
	}
	
	boolean isCompressedCycle() {
		return name.indexOf('[') > -1 && name.indexOf(']') > 0;
	}

	void delete() {
		Graph.g.vertices.remove(this);

		edges.each { e ->
			Graph.g.edges.remove(e);

			Vertex otherVertex = e.getOtherVertex(this);
			otherVertex.neighbors.remove(this);
			otherVertex.edges.remove(e);
		};

		this.neighbors.clear();
		this.edges.clear();
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