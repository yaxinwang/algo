package yaxin

class Graph {

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
		for(Vertex v : vertices) {
			if(v.getState() == null) {
				reverse(v, null);
			}
		}
	}

	public void reverse(Vertex v, Vertex oldParent) {
		v.setState(ProcessingState.DISCOVERED);

		for(Edge edge : v.getEdges()) {
			if(edge.getTo().getState() == null) {
				reverse(edge.getTo(), v);
			} else {
				edge.getTo().connect(v);
			}
		}

		v.getEdges().clear();

		if(oldParent != null) {
			v.connect(oldParent);
		}

		v.setState(ProcessingState.PROCESSED);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		vertices.each() {
			buf.append(it).append('\n');				
		};
		return buf.toString();
	}
}
