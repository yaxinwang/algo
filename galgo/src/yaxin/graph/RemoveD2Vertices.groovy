package yaxin.graph

import org.junit.Test

import yaxin.ProcessingState

class RemoveD2Vertices {

	@Test
	void testRemoveD2() {
		Graph g = new Graph();
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		def d = new Vertex("D")
		def e = new Vertex("E");

		new Edge(a, b);
		new Edge(c, d);
		new Edge(b, e);
		new Edge(d, e);

		def vertices = removeD2(g);
		println g;
	}

	static void removeD2(Graph g) {
		List<Vertex> d2 = new LinkedList<Vertex>();
		d2.addAll(g.vertices.findAll {
			it.degree() == 2;
		});

		_removeD2(d2);
	}

	static void _removeD2(LinkedList<Vertex> d2) {
		if(d2.size() == 0) {
			return;
		}

		Vertex v = d2.pop();
		if(v.degree() == 2) {
			Vertex v1 = v.neighbors[0];
			Vertex v2 = v.neighbors[1];
			if(v1.neighbors.contains(v2)) {
				//v1-v2 is connected already, do not add dup edge
			} else {
				def e = new Edge(v1, v2);
				println "new edge $e";
			}

			d2.offer(v1);
			d2.offer(v2);

			v.delete();
			println "delete ${v.name}";
		}

		_removeD2(d2);
	}
}
