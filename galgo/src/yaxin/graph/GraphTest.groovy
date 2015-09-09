package yaxin.graph

import org.junit.Test

class GraphTest {

	@Test
	void testGraph() {
		Graph g = makeTriangle();
		
		println g.vertices.get(0);
		
		println g;
	}
	
	@Test
	void testFindCover_Trangle() {
		Graph g = makeTriangle();
		
		def cover = IndependentVertexCover.findCover(g);
		cover.each {
			println it;
		}
	}
	
	@Test
	void testFindCover_Rectangle() {
		Graph g = makeRectangle();
		
		def cover = IndependentVertexCover.findCover(g);
		cover.each {
			println it;
		}
	}
	
	@Test
	void testFindCover_Trangle2() {
		Graph g = makeTriangle();
		
		def cover = IndependentVertexCover2.findCover(g);
		cover.each {
			println it;
		}
	}
	
	@Test
	void testFindCover_Rectangle2() {
		Graph g = makeRectangle();
		
		def cover = IndependentVertexCover2.findCover(g);
		cover.each {
			println it;
		}
	}
	
	Graph makeTriangle() {
		Graph g = new Graph();
		
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, a);
		
		return g;
	}
	
	Graph makeRectangle() {
		Graph g = new Graph();
		
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		def d = new Vertex("D");
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, a);
		
		return g;
	}
	
}
