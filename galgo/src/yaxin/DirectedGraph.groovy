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

	public String toString() {
		StringBuffer buf = new StringBuffer();
		vertices.each() {
			buf.append(it).append('\n');				
		};
		return buf.toString();
	}
}
