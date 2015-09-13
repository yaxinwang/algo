package yaxin.graph

import org.junit.Test

import yaxin.ProcessingState

class RemoveDegree2 {
	
	@Test
	void testRemoval() {
		Graph g = new Graph();
		
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		def d = new Vertex("D")
		
		new Edge(a, b);
		new Edge(a, c);
		new Edge(a, d);
		
		new Edge(b, c);
		new Edge(c, d);
		
		RemoveDegree2.removeDegree2(g);
		
		println g;
		
	}
	
	static void removeDegree2(Graph g) {
		while(true) {						
			Vertex v = getUndiscoveredVertex(g);
			if(v == null) {
				break;
			}
			
			process(g, v);
		}
	}
	
	static Vertex getUndiscoveredVertex(Graph g) {
		for(Vertex v : g.vertices) {
			if(v.state == null) {
				return v;
			}
		}
		
		return null;
	}
	
	static void process(Graph g, Vertex v) {
		println "To process ${v.name}";
		
		v.state = ProcessingState.DISCOVERED;
		
		List<Vertex> changedVertices = [];
		v.dupEdges.each { ls ->
			for(int i=1; i<ls.size(); i++) {
				ls[i].delete();
			}
			changedVertices << ls[0];
		}
		
		if(v.degree() == 2) {
			Vertex n1 = v.neighbors[0];
			Vertex n2 = v.neighbors[1];
			v.edges.each { e ->
				e.delete();
				println "Delete edge $e";
			}
			g.vertices.remove(v);
			
			println "Delete vertex ${v.name}";
			
			Edge newEdge = new Edge(n1, n2);
			println "Add new edge ${newEdge}";
			
			changedVertices << n1;
			changedVertices << n2;
		}
		
		changedVertices.each {
			process(g, v);
		}
		
		v.state = ProcessingState.PROCESSED;
	}

}
