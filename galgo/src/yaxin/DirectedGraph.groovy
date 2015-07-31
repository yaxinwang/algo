package yaxin

class DirectedGraph {

	List<Vertex> vertices = new ArrayList<Vertex>();

	public List<Vertex> getVertices() {
		return vertices;
	}

	public Vertex newVertex(String name) {
		Vertex v = new Vertex(name);
		vertices.add(v);
		return v;
	}
	
	public List<Vertex> findRoots() {
		vertices.each { v ->
			v.isRoot = true;
		}
		
		vertices.each { v ->
			v.edges.each { e ->
				e.to.isRoot = false;
			}
		}
		
		List<Vertex> roots = [];
		vertices.each { v ->
			if(v.isRoot) {
				roots << v;
			}
		}
		
		return roots;
	}

	public void reverse() {
		List<Edge> reversedEdges = [];
				
		vertices.each { v ->
			v.edges.each { e ->
				Edge reversed = new Edge();
				reversed.from = e.to;
				reversed.to = e.from;
				reversedEdges << reversed;
			}
		};
	
		vertices.each { v ->
			v.edges.clear();
		}
		
		reversedEdges.each { e -> 
			e.from.edges.add(e);
		}
	}
	
	public boolean hasCycle() {
		List<Vertex> roots = findRoots();
		if(roots.size() == 0) {
			return true;
		}
		
		for(Vertex root : roots) {
			if(hasCycle(root)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasCycle(Vertex v) {
		v.state = ProcessingState.DISCOVERED;
		
		for(Edge e : v.edges) {
			if(e.to.state == ProcessingState.PROCESSED) {
				//processed from other root
				continue;
			} else if(e.to.state == ProcessingState.DISCOVERED) {
				//back to discovered vertex, it is a cycle
				println "back to ${e.to.name}";
				return true;
			} else {
				if(hasCycle(e.to)) {
					return true;
				}
			}
		}
		
		v.state = ProcessingState.PROCESSED;
		
		return false;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		vertices.each() {
			buf.append(it).append('\n');				
		};
		return buf.toString();
	}
}
