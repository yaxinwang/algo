package yaxin.graph

import org.junit.Test

class TriangleTest {

	@Test
	public void testTriangle() {
		Graph g = new Graph();
		
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		def d = new Vertex("D")
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, b);
		
		List<Vertex> triangle = Triangle.findTriangle(g);
		println triangle
		
		triangle.each {
			println it.name;
		}
	}
	
	@Test
	public void testRectangle() {
		Graph g = new Graph();
		
		def a = new Vertex("A");
		def b = new Vertex("B");
		def c = new Vertex("C");
		def d = new Vertex("D")
		def e = new Vertex("E")
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, e);
		new Edge(e, b);
		
		List<Vertex> triangle = Triangle.findRectangle(g);
		println triangle
		
		triangle.each {
			println it.name;
		}
	}
}
