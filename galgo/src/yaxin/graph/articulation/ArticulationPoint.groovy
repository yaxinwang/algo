package yaxin.graph.articulation

import org.junit.Test

import yaxin.ProcessingState;
import yaxin.graph.Cycle
import yaxin.graph.Edge
import yaxin.graph.Graph
import yaxin.graph.Vertex

class ArticulationPoint {
	
	@Test
	public void testOneCycle() {
		Graph g = makeGraphWithSimpleCycle();
		Cycle c = findOneCycle(g);
		
		if(c == null) {
			println ">>> no cycle";
			return;
		}
		
		println ">>> found cycle";
		println c.vertices.collect { v->
			v.name;
		}.iterator().join(',');
	
		Graph g2 = g.compressCycle(c);
		
		println g2;
	
	}
	
	@Test
	public void testArticulationPoint_SimpleCycle() {		
		Graph g = makeGraphWithSimpleCycle();		
		findArticulationPoints(g);
	}
	
	@Test
	public void testArticulationPoint_IntersectedCycles() {
		Graph g = makeGraphWithIntersectedCycles();
		findArticulationPoints(g);
	}
	
	@Test
	public void testArticulationPoint_TwoCycles() {
		Graph g = makeGraphWithTwoCycles();
		findArticulationPoints(g);
	}
	
	static void findArticulationPoints(Graph g) {
		while(true) {
			Cycle c = findOneCycle(g);
			if(c == null) {
				break;
			}
			
			g = g.compressCycle(c);
			
			println ">>>>>> created another graph";
			println g;
			println ">>>>>>>>>>>>";
		}
		
		g.edges.each { e ->
			if(e.left.isCompressedCycle()) {
				println "articulation point: ${e.originalLeft.name}";
			}
			if(e.right.isCompressedCycle()) {
				println "articulation point: ${e.originalRight.name}";
			}
		}
	}
	
	static Cycle findOneCycle(Graph g) {
		for(Vertex v : g.vertices) {
			if(v.state == null) {
				def cycle = findOneCycleV(null, v)
				if(cycle != null) {
					return cycle;
				}
			}
		}		
	}
	
	static Cycle findOneCycleV(Edge from, Vertex v) {
		println "discovered ${v.name}";
		
		v.state = ProcessingState.DISCOVERED;
		
		for(Edge e : v.edges) {		
			if(from == e) {
				continue;
			}
			
			Vertex n = e.getOtherVertex(v);
				
			if(n.state == ProcessingState.DISCOVERED) {
				//cycle is detected, create the cycle
				
				println ">>> ${v.name} is back to ${n.name}";
				
				Cycle c = new Cycle();
				c.convergeVertex = n;
				c.vertices << v;
				return c;
			}
			
			def cycle = findOneCycleV(e, n)
			if(cycle != null) {
				if(cycle.isCompleted) {
					//parent of the cycle, don't add to cycle
					println ">>> ${v.name} is parent of cycle";
				} else if(cycle.convergeVertex == v) {
					println ">>> ${v.name} is converge vertex of cycle";
					cycle.vertices << v;
					cycle.isCompleted = true;
				} else {
					println ">>> ${v.name} is in cycle";
					cycle.vertices << v;
				}
				
				return cycle;
			}
		}
		
		v.state = ProcessingState.PROCESSED;
		
		println "processed ${v.name}";
		
		return null;
	}
	
	static Graph makeGraphWithSimpleCycle() {
		Graph g = new Graph();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, e);
		
		new Edge(b, d);		
		
		return g;
	}

	static Graph makeGraphWithIntersectedCycles() {
		Graph g = new Graph();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, e);
		new Edge(e, f);
		
		new Edge(b, d);
		new Edge(c, e);
		
		return g;
	}
	
	static Graph makeGraphWithTwoCycles() {
		Graph g = new Graph();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, a);
		
		new Edge(d, e);
		new Edge(e, f);
		new Edge(f, d);
		
		new Edge(c, d);
		
		return g;
	}
	
}
