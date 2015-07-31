package yaxin

import org.junit.Test

class GraphTest {

	@Test
	public void testReverseGraph() {
		println "testReverseGraph";
		
		DirectedGraph g = buildG3();
		println g;
		
		g.reverse();
		println g;
	}
	
	@Test
	public void testFindRoots() {
		DirectedGraph g = buildGraph();
		List<Vertex> roots = g.findRoots();
		println roots.size();
		
		roots.each { r ->
			println r;
		}
	}
	
	@Test
	public void testFindCycle() {
		DirectedGraph g = buildGraph();
		println "has cycle? ${g.hasCycle()}";
	}
		
	DirectedGraph buildG3() {
		DirectedGraph graph = new DirectedGraph();
		
		Vertex a = graph.newVertex("A");
		Vertex b = graph.newVertex("B");
		Vertex c = graph.newVertex("C");
		
		a.connect(b);
		b.connect(c);
		c.connect(a);
		
		return graph;
	}
	
	DirectedGraph buildGraph() {
		DirectedGraph graph = new DirectedGraph();
		
		Vertex a = graph.newVertex("A");
		Vertex b = graph.newVertex("B");
		Vertex c = graph.newVertex("C");
		Vertex d = graph.newVertex("D");
		Vertex e = graph.newVertex("E");
		Vertex f = graph.newVertex("F");
		Vertex g = graph.newVertex("G");
		Vertex h = graph.newVertex("H");
		Vertex i = graph.newVertex("I");
		Vertex j = graph.newVertex("J");
		
		a.connect(b, d);
		b.connect(c, d, e);
		c.connect(f);
		d.connect(e,g);
		e.connect(c, f, g);
		f.connect(h);
		g.connect(f, i);
		//g.connect(i);
		h.connect(g, j);
		i.connect(j);
		
		return graph;
	}
}
