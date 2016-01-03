package yaxin.graph

import org.junit.Test

import yaxin.ProcessingState

class ExtractCycle {
	
	@Test
	void testExtract() {
		println ">>>>>>>>>>>>>>";
		
		Graph g = new Graph();
		
		Vertex a = new Vertex('A');
		Vertex b = new Vertex('B');
		Vertex c = new Vertex('C');
		Vertex d = new Vertex('D');
		Vertex e = new Vertex('E');
		
		new Edge(a, b);
		new Edge(b, c);
		new Edge(c, d);
		new Edge(d, e);
		
		new Edge(d, b);
		
		List<Vertex> cycle = extract(g);
		
		println cycle.collect { it.name }.iterator().join(',');
	}

	public List<Vertex> extract(Graph g) {
		List<Vertex> stack = [];
		
		Vertex v = g.vertices[0];
		
		return extract(null, v, stack);		
	}
	
	List<Vertex> extract(Vertex parent, Vertex v, List<Vertex> stack) {
		stack.push(v);
		v.state = ProcessingState.DISCOVERED;
		
		for(Vertex n : v.neighbors) {
			if(n == parent) {
				
			} else if(n.state == null) {
				def cycle = extract(v, n, stack);
				if(cycle != null) {
					return cycle;
				}
			} else {
				//found cycle;
				return extractCycle(stack, n);
			}
		}
		
		stack.pop();
		v.state = ProcessingState.PROCESSED;
		
		return null;
	}
	
	List<Vertex> extractCycle(List<Vertex> stack, Vertex convergeVertex) {
		List<Vertex> cycle = [];
		
		for(int i=stack.size() - 1; i>=0; i--) {
			cycle << stack[i];
			if(stack[i] == convergeVertex) {
				break;
			}
		}
		
		return cycle;
	}
}
