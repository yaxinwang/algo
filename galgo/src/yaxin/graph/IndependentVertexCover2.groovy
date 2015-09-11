package yaxin.graph

import org.codehaus.groovy.classgen.asm.CompileStack.StateStackElement;
import org.junit.Test

import yaxin.ProcessingState;

class IndependentVertexCover2 {

	//TODO: use the built-in call stack
	static List<Vertex> stateStack = [];
	
	static List<Vertex> findCover(Graph g) {
		Vertex v = nextVertex(g);
		if(v == null) {
			//all vertices are processed
			return getCover(g);
		}

		//include v
		def cover = findCover(g, v);
		if(cover != null) {
			return cover;
		}

		popState(v);

		//exclude v, one of v's neighbor has to be included in cover
		v.state = ProcessingState.EXCLUDED;
		stateStack.push(v);

		for(Vertex n : v.neighbors) {			
			cover = findCover(g, n);
			if(cover != null) {
				return cover;
			}
			
			popState(n);			
		}

		//one of the neighbor needs to be in cover but none of them is in cover
		return null;
	}

	static List<Vertex> findCover(Graph g, Vertex v) {
		v.state = ProcessingState.INCLUDED;
		stateStack.push(v);

		for(Vertex n : v.neighbors) {
			if(n.state == ProcessingState.INCLUDED) {
				//not allowed
				return null;
			} else if(n.state == ProcessingState.EXCLUDED) {
				//already excluded
			} else {
				n.state = ProcessingState.EXCLUDED;
				stateStack.push(n);
			}
		}

		return findCover(g);
	}
	
	static Vertex nextVertex(Graph g) {
		for(Vertex v : g.vertices) {
			if(v.state == null) {
				return v;
			}
		}
		return null;
	}
	
	static List<Vertex> getCover(Graph g) {
		List<Vertex> cover = [];
		g.vertices.each {
			if(it.state == ProcessingState.INCLUDED) {
				cover << it;
			}
		}
		return cover;
	}
	
	static void popState(Vertex v) {
		while(stateStack.size() > 0) {
			if(stateStack.pop() == v) {
				return;
			}
		}
	}

}
